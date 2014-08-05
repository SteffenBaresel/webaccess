/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.siv.script;

import de.siv.modules.Executions;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sbaresel
 */
public class TacticalOverview extends HttpServlet {
    Properties props = null;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response, String Uid)
            throws ServletException, IOException, FileNotFoundException, NamingException, SQLException {
        PrintWriter out = response.getWriter(); 
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "*");
        response.setContentType("application/javascript; charset=utf-8");
        
        int csw = 0; if(Executions.UserIsPermitted(Uid,"managed_services_csw")) { csw = 1; }
        int srvm = 0; if(Executions.UserIsPermitted(Uid,"service_menu")) { srvm = 1; }
        
        if(Executions.UserIsPermitted(Uid,"liveticker")) {
        
            out.println(""
                    + "function SummaryView(uid) {\n"
                    + "    $.ajax({\n"
                    + "        url: '/gateway/monitoring/BigTaov',\n"
                    + "        crossDomain: true,\n"
                    + "        success: function(json) {\n"
                    /*+ "            $('section#big-taov').html('<div id=\"big-taov-hosts\"><span style=\"float: left;\" id=\"big-taov-header\">Hosts</span><span style=\"float: left; margin-top: 0px;\" class=\"ui-icon ui-icon-triangle-1-s\"></span><br><span id=\"big-taov-hosts-content\"></span></div><div id=\"big-taov-services\"><span id=\"big-taov-header\">Services</span><span style=\"float: left; margin-top: 0px;\" class=\"ui-icon ui-icon-triangle-1-s\"></span><br><span id=\"big-taov-services-content\"></span></div>');\n"
                    + "            $.each(json.HOSTS, function() {\n"
                    + "                var csscl;\n"
                    + "                if (this.ACK == \"t\") { csscl = \"ack\"; } else { if (this.STATE == \"1\") { csscl = \"critical\"; } else if (this.STATE == \"2\") { csscl = \"unknown\"; } else { csscl = \"ok\"; } }\n"
                    + "                $('#big-taov-hosts-content').append('<span class=\"ui-taov-tile ' + csscl + '\" title=\"' + base64_decode( this.HOST_NAME ) + '\"></span>');\n"
                    + "            });\n"*/
                    + "            $('section#big-taov').html('<div id=\"big-taov-services\"><span id=\"big-taov-header\">Aktuelle Auff&auml;lligkeiten (' + json.SERVICES.length + ')</span><span style=\"float: left; margin-top: 0px;\" class=\"ui-icon ui-icon-triangle-1-s\"></span><br><span id=\"big-taov-services-content\"></span></div>');\n"
                    + "            $('#current-problems-content').html('');\n"
                    + "            $.each(json.SERVICES, function() {\n"
                    + "                var csscl; var dtmcl='';\n"
                    + "                if (this.ACK == \"t\") { csscl = \"ack\"; } else { if (this.STATE == \"1\") { csscl = \"warning\"; } else if (this.STATE == \"2\") { csscl = \"critical\"; } else if (this.STATE == \"3\") { csscl = \"unknown\"; } else { csscl = \"ok\"; } }\n"
                    + "                if (this.DTM == \"t\") { dtmcl='<img src=\"public/images/clock-small.png\" />'; } else { dtmcl=''; }\n"
                    + "                $('#big-taov-services-content').append('<span class=\"ui-taov-tile ' + csscl + '\" title=\"Service ' + base64_decode( this.SRV_NAME ) + ' auf ' + base64_decode( this.HOST_NAME ) + '\" onclick=\"ServiceMenu(\\\'' + this.HOST_NAME + '\\\',\\\'' + this.HOST_ID + '\\\',\\\'' + this.SRV_NAME + '\\\',\\\'' + this.SRV_ID + '\\\',\\\'' + this.INSTID + '\\\',\\\'' + this.ACK + '\\\',\\\'' + this.ACKID + '\\\',\\\'' + this.DTM + '\\\',\\\'' + this.DTMID + '\\\');\">' + dtmcl + '</span>');\n"
                    + "                $('#current-problems-content').append('<div id=\"current-problems-content-entry\" onclick=\"ServiceMenu(\\\'' + this.HOST_NAME + '\\\',\\\'' + this.HOST_ID + '\\\',\\\'' + this.SRV_NAME + '\\\',\\\'' + this.SRV_ID + '\\\',\\\'' + this.INSTID + '\\\',\\\'' + this.ACK + '\\\',\\\'' + this.ACKID + '\\\',\\\'' + this.DTM + '\\\',\\\'' + this.DTMID + '\\\');\"><table cellpadding=0 cellspacing=0 border=0><tr><td rowspan=2><span class=\"ui-taov-tile ' + csscl + '\">' + dtmcl + '</span></td><td>Service: <b>' + base64_decode( this.SRV_NAME ) + '</b> auf <i>' + base64_decode( this.HOST_NAME ) + '</i></td></tr><tr><td>' + base64_decode( this.OUTPUT ) + '</td></tr></table></div>');\n"
                    + "            });\n"
                    + "            $('#SlimTaov').html('<div id=\"Nodes\"></div><div id=\"div-liveticker\"></div>');\n"
                    + "\n"
                    + "            $('#last-comments-content').html('');\n"
                    + "            $.each(json.COMMENTS, function() {\n"
                    + "                var dn = new Date(base64_decode( this.TS ) * 1000);\n"
                    + "                var esk = base64_decode( this.ESK );\n"
                    + "                var eskt;\n"
                    + "                if (esk == '1') {\n"
                    + "                    eskt = '<span id=\"EskDscE\">Dieser Eintrag wurde in der Stufe 1 eskaliert.</span>';\n"
                    + "                } else if (esk == '2') {\n"
                    + "                    eskt = '<span id=\"EskDscE\">Dieser Eintrag wurde in der Stufe 2 eskaliert.</span>';\n"
                    + "                } else if (esk == '3') {\n"
                    + "                    eskt = '<span id=\"EskDscE\">Dieser Eintrag wurde in der Stufe 3 eskaliert.</span>';\n"
                    + "                } else {\n"
                    + "                    eskt = '<span id=\"EskDscD\">Dieser Eintrag wurde nicht eskaliert.</span>';\n"
                    + "                }\n"
                    + "                $('#last-comments-content').append('<div id=\"last-comments-content-entry\"><div id=\"last-comments-content-entry-top\"><span>Author: <b>' + base64_decode( this.NAME ) + '</b></span><span>Datum: <b>' + dn.format(\"yyyy-mm-dd HH:MM:ss\") + '</b></span></div><div id=\"last-comments-content-entry-title\"><span>Kunde: <b>' + base64_decode( this.CUNM ) + '</b></span><span><b>' + eskt + '</b></span></div><br><div id=\"last-comments-content-entry-text\">' + base64_decode( this.TEXT ) + '</div></div>');\n"
                    + "            });\n"
                    /*+ "            var ocolor; var ccolor; var ucolor;\n"
                    + "            var host_up = parseInt(json.SLIMTAOV[0].HOSTS[0].UP, 10);\n"
                    + "            var host_down = parseInt(json.SLIMTAOV[0].HOSTS[0].DOWN, 10);\n"
                    + "            var host_unr = parseInt(json.SLIMTAOV[0].HOSTS[0].UNREACHABLE, 10);\n"
                    + "            if (host_up != 0) { ocolor = ' taov_ok'; } else { ocolor = ' taov_default'; }\n"
                    + "            if (host_down != 0) { ccolor = ' taov_cr'; } else { ccolor = ' taov_default'; }\n"
                    + "            if (host_unr != 0) { ucolor = ' taov_un'; } else { ucolor = ' taov_default'; }\n"
                    + "            $('#Nodes').append('<span style=\"float: left;\"><table cellpadding=0 cellspacing=0 border=0><tr><td colspan=3><span style=\"float: left;\">Hosts</span><span style=\"float: left; margin-top: 0px;\" class=\"ui-icon ui-icon-triangle-1-s\"></span></td></tr><tr valign=middle><td class=\"' + ocolor + '\"><b><a href=\"\">' + host_up + '</a></b> Up</td><td class=\"' + ccolor + '\"><b><a href=\"\">' + host_down + '</a></b> Down</td><td class=\"' + ucolor + '\"><b><a href=\"\">' + host_unr + '</a></b> Nicht Erreichbar</td></tr></table></span>');\n"*/
                    + "\n"
                    + "            var socolor; var swcolor; var sccolor; var sucolor;\n"
                    + "            var service_ok = parseInt(json.SLIMTAOV[1].SERVICES[0].OK, 10);\n"
                    + "            var service_warn = parseInt(json.SLIMTAOV[1].SERVICES[0].WARNING, 10);\n"
                    + "            var service_crit = parseInt(json.SLIMTAOV[1].SERVICES[0].CRITICAL, 10);\n"
                    + "            var service_unk = parseInt(json.SLIMTAOV[1].SERVICES[0].UNKNOWN, 10);\n"
                    + "            if (service_ok != 0) { socolor = ' taov_ok'; } else { socolor = ' taov_default'; }\n"
                    + "            if (service_warn != 0) { swcolor = ' taov_wa'; } else { swcolor = ' taov_default'; }\n"
                    + "            if (service_crit != 0) { sccolor = ' taov_cr'; } else { sccolor = ' taov_default'; }\n"
                    + "            if (service_unk != 0) { sucolor = ' taov_un'; } else { sucolor = ' taov_default'; }\n"
                    + "            $('#Nodes').append('<span style=\"float: left;\"><table cellpadding=0 cellspacing=0 border=0><tr><td colspan=4><span style=\"float: left;\">Alle Services</span><span style=\"float: left; margin-top: 0px;\" class=\"ui-icon ui-icon-triangle-1-s\"></span></td></tr><tr valign=middle><td class=\"taov_default\"><b><a href=\"/webaccess/Monitoring?v=Q3VyUHJvYnM=\">' + json.SERVICES.length + '</b> Aktive Probleme</a></td><td class=\"' + socolor + '\"><b><a href=\"/webaccess/Monitoring?v=U2VydmljZVN0YXR1cw==&s=MA==\">' + service_ok + '</b> Ok</a></td><td class=\"' + swcolor + '\"><b><a href=\"/webaccess/Monitoring?v=U2VydmljZVN0YXR1cw==&s=MQ==\">' + service_warn + '</b> Warnung</a></td><td class=\"' + sccolor + '\"><b><a href=\"/webaccess/Monitoring?v=U2VydmljZVN0YXR1cw==&s=Mg==\">' + service_crit + '</b> Kritisch</a></td><td class=\"' + sucolor + '\"><b><a href=\"/webaccess/Monitoring?v=U2VydmljZVN0YXR1cw==&s=Mw==\">' + service_unk + '</b> Unbekannt</a></td></tr></table></span>');\n"
                    + "\n"
                    + "            var dbocolor; var dbccolor;\n"
                    + "            var db_open = parseInt(json.SLIMTAOV[2].DATABASES[0].OPEN, 10);\n"
                    + "            var db_stopped = parseInt(json.SLIMTAOV[2].DATABASES[0].STOPPED, 10);\n"
                    + "            if (db_open != 0) { dbocolor = ' taov_ok'; } else { dbocolor = ' taov_default'; }\n"
                    + "            if (db_stopped != 0) { dbccolor = ' taov_cr'; } else { dbccolor = ' taov_default'; }\n"
                    + "            $('#Nodes').append('<span style=\"float: left;\"><table cellpadding=0 cellspacing=0 border=0><tr><td colspan=3><span style=\"float: left;\">Datenbanken</span><span style=\"float: left; margin-top: 0px;\" class=\"ui-icon ui-icon-triangle-1-s\"></span></td></tr><tr valign=middle><td class=\"' + dbocolor + '\"><b><a href=\"/webaccess/Monitoring?v=RGJTdGF0dXM=&s=MA==\">' + db_open + '</b> Open</a></td><td class=\"' + dbccolor + '\"><b><a href=\"/webaccess/Monitoring?v=RGJTdGF0dXM=&s=Mg==\">' + db_stopped + '</b> Stopped</a></td></tr></table></span>');\n"
                    + "\n"
                    + "            var mwocolor; var mwccolor;\n"
                    + "            var mw_online = parseInt(json.SLIMTAOV[3].MIDDLEWARE[0].ONLINE, 10);\n"
                    + "            var mw_offline = parseInt(json.SLIMTAOV[3].MIDDLEWARE[0].OFFLINE, 10);\n"
                    + "            if (mw_online != 0) { mwocolor = ' taov_ok'; } else { mwocolor = ' taov_default'; }\n"
                    + "            if (mw_offline != 0) { mwccolor = ' taov_cr'; } else { mwccolor = ' taov_default'; }\n"
                    + "            $('#Nodes').append('<span style=\"float: left;\"><table cellpadding=0 cellspacing=0 border=0><tr><td colspan=3><span style=\"float: left;\">Middleware</span><span style=\"float: left; margin-top: 0px;\" class=\"ui-icon ui-icon-triangle-1-s\"></span></td></tr><tr valign=middle><td class=\"' + mwocolor + '\"><b><a href=\"\">' + mw_online + '</a></b> Online</td><td class=\"' + mwccolor + '\"><b><a href=\"\">' + mw_offline + '</a></b> Offline</td></tr></table></span>');\n"
                    + "\n"           
                    //+ "            /*\n"
                    //+ "             * Hosts\n"
                    //+ "             */\n"
                    + "\n"
                    /*+ "            var hge = host_up + host_down + host_unr;\n"
                    + "\n"
                    + "            ShowHostPie();\n"
                    + "            ShowHostPer();\n"
                    + "            \n"
                    + "            charthostper.addSeries({\n"
                    + "                name: \"OK\",\n"
                    + "                data: [ ((host_up*100)/hge) ]\n"
                    + "            });\n"
                    + "            charthostper.addSeries({\n"
                    + "                name: \"CR\",\n"
                    + "                data: [ ((host_down*100)/hge) ]\n"
                    + "            });\n"
                    + "            charthostper.addSeries({\n"
                    + "                name: \"UN\",\n"
                    + "                data: [ ((host_unr*100)/hge) ]\n"
                    + "            });\n"
                    + "\n"
                    + "            hostpie.addSeries({\n"
                    + "               type: \"pie\",\n"
                    + "               name: \"\",\n"
                    + "               data: [ ['Online',host_up], ['Offline',host_down], ['Nicht Erreichbar',host_unr] ]\n"
                    + "            });\n"*/
                    + "\n"
                    + "            /*\n"
                    + "             * Services\n"
                    + "             */\n"
                    + "\n"
                    + "            var sge = service_ok + service_warn + service_crit + service_unk;\n"
                    + "\n"
                    + "            ShowServicePie();\n"
                    + "            ShowServicePer();\n"
                    + "\n"
                    + "            chartserviceper.addSeries({\n"
                    + "                name: \"OK\",\n"
                    + "                data: [ ((service_ok*100)/sge) ]\n"
                    + "            });\n"
                    + "\n"
                    + "            chartserviceper.addSeries({\n"
                    + "                name: \"WA\",\n"
                    + "                data: [ (((service_warn)*100)/sge) ]\n"
                    + "            });\n"
                    + "\n"
                    + "            chartserviceper.addSeries({\n"
                    + "                name: \"CR\",\n"
                    + "                data: [ (((service_crit)*100)/sge) ]\n"
                    + "            });\n"
                    + "\n"
                    + "            chartserviceper.addSeries({\n"
                    + "                name: \"UN\",\n"
                    + "                data: [ (((service_unk)*100)/sge) ]\n"
                    + "            });\n"
                    + "\n"
                    + "            servicepie.addSeries({\n"
                    + "               type: \"pie\",\n"
                    + "               name: \"\",\n"
                    + "               data: [ ['Ok',service_ok], ['Warnung',(service_warn)], ['Kritisch',(service_crit)], ['Unbekannt',(service_unk)] ]\n"
                    + "            });\n"
                    + "\n"
                    + "            /*\n"
                    + "             * Liveticker\n"
                    + "             */\n"
                    + "            if(json.LIVETICKER.length > 1) {\n"
                    + "                if (!$.cookie('liveticker-count')) { $.cookie('liveticker-count',0); }\n"
                    + "                var lc = parseInt($.cookie('liveticker-count'));\n"
                    + "                $.cookie('liveticker-count', lc+1);\n"
                    + "                $('#div-liveticker').html('<img src=\"public/images/warning.png\" onclick=\"LivetickerDialog();\" title=\"' + json.LIVETICKER.length + ' Alarme aus dem Monitoring\"/>');\n"
                    + "                blink('div-liveticker img');\n"
                    + "                if ( lc+1 == 1 || parseInt($.cookie('liveticker-length')) != json.LIVETICKER.length ) { LivetickerDialog(); }\n"
                    + "                $.cookie('liveticker-length', json.LIVETICKER.length);\n"
                    + "            } else if(json.LIVETICKER.length > 0) {"
                    + "                if (!$.cookie('liveticker-count')) { $.cookie('liveticker-count',0); }\n"
                    + "                var lc = parseInt($.cookie('liveticker-count'));\n"
                    + "                $.cookie('liveticker-count', lc+1);\n"
                    + "                $('#div-liveticker').html('<img src=\"public/images/warning.png\" onclick=\"LivetickerDialog();\" title=\"' + json.LIVETICKER.length + ' Alarm aus dem Monitoring\"/>');\n"
                    + "                blink('div-liveticker img');\n"
                    + "                if ( lc+1 == 1 || parseInt($.cookie('liveticker-length')) != json.LIVETICKER.length ) { LivetickerDialog(); }\n"
                    + "                $.cookie('liveticker-length', json.LIVETICKER.length);\n"
                    + "            } else {\n"
                    + "                $.cookie('liveticker-length',0);\n"
                    + "                $.cookie('liveticker-count',0);\n"
                    + "            }\n"
                    + "        },\n"
                    + "        dataType: 'json',\n"
                    + "        cache: false\n"
                    + "    }); \n"
                    + "    setTimeout('SummaryView()', 20000);\n"
                    + "}\n"
                    + "");
            
            out.println(""
                    + "function blink(id) {\n"
                    + "    $('#' + id).delay(450).fadeTo(50,1).delay(450).fadeTo(50,0);\n"
                    + "    setTimeout('blink(\\'' + id + '\\')',1000);\n"
                    + "}\n"
                    + "");
            
            out.println(""
                    + "function LivetickerDialog() {\n"
                    + "    $('#LivetickerDialog').remove();\n"
                    + "    $('#LiveDialog').html('<div id=\"LivetickerDialog\" title=\"Liveticker Summary - \"><div id=\"LivetickerEContent\"></div></div>');"
                    + "    $('#LivetickerDialog').dialog({\n"
                    + "	       autoOpen: true,\n"
                    + "        dialogClass: 'ui-lt-dialog',\n"
                    + "	       height: 500,\n"
                    + "	       width: 800,\n"
                    + "	       draggable: false,\n"
                    + "	       resizable: false,\n"
                    + "	       modal: true,\n"
                    + "        open: function() {\n"
                    + "            $.ajax({\n"
                    + "                url: '/gateway/monitoring/LivetickerEntries',\n"
                    + "                crossDomain: true,\n"
                    + "                success: function(json) {\n"
                    + "                    if(json.LIVETICKER.length > 1) {\n"
                    + "                        $('.ui-dialog-title').append('' + json.LIVETICKER.length + ' Alarme aus dem Monitoring');\n"
                    + "                    } else {"
                    + "                        $('.ui-dialog-title').append('' + json.LIVETICKER.length + ' Alarm aus dem Monitoring');\n"
                    + "                    }"
                    + "                    $.each(json.LIVETICKER, function() {\n"
                    + "                        $('#LivetickerEContent').append('<div id=\"LivetickerELine\" class=\"ui-input-hofo\"><img src=\"public/images/' + base64_decode( this.HOST_TYPE ) + '-128.png\"  title=\"' + base64_decode( this.HOST_TYLN ) + '\" /><span class=\"HostName\">Host Name: ' + base64_decode( this.HOST_NAME ) + '</span><span class=\"ServiceName\">Service Name: ' + base64_decode( this.SERVICE_NAME ) + '</span><span class=\"Timestamp\">Erstellt: ' + this.CREATED_ISO + '</span><span class=\"Output\">' + base64_decode( this.OUTPUT ) + '</span></div>');\n"
                    + "                    });\n"
                    + "                },\n"
                    + "                dataType: 'json',\n"
                    + "                cache: false\n"
                    + "            });\n"
                    + "        },\n"
                    + "        buttons: { \n"
                    + "            OK: function() {\n"
                    + "                $(this).dialog('close');\n"
                    + "		       $('#LivetickerDialog').remove();\n"
                    + "            }\n"
                    + "	       }\n"
                    + "    }).parent().find('.ui-dialog-titlebar-close').hide();\n"
                    + "}\n"
                    + "");
            
        }
        
        
        if(Executions.UserIsPermitted(Uid,"sidebar")) {
            out.println(""
                    + "function SidebarBottom() {\n"
                    + "    $.Shortcuts.add({\n"
                    + "        type: 'down',\n"
                    + "        mask: 'b',\n"
                    + "        handler: function() {\n"
                    + "           if ($(\"#SidebarBottom\").is(\":hidden\")) {\n"
                    + "                $('#SidebarBottomSmall').animate({marginBottom: \"870px\"},350).css('zIndex',25).addClass('ui-bg-white').addClass('ui-corner-top');\n"
                    + "                $('#SidebarBottom').animate({height:'toggle'},350, function() {\n"
                    + "                    $('#SidebarBottomContent').fadeIn(100);\n"
                    + "                }).css('zIndex',25);\n"
                    + "            } else {\n"
                    + "                $('#SidebarBottomContent').fadeOut(100);\n"
                    + "                $('#SidebarBottom').animate({height:'toggle'},350).css('zIndex',10);\n"
                    + "                $('#SidebarBottomSmall').animate({marginBottom: \"0px\"},350).css('zIndex',10).removeClass('ui-bg-white').removeClass('ui-corner-top');\n"
                    + "            }\n"
                    + "        }\n"
                    + "    }).start();\n"
                    + "}"
                    + "");
        }
                
        if(Executions.UserIsPermitted(Uid,"bottombar")) {
            out.println("");
        }
        
        if(srvm == 1) {
        
        if(csw == 1) {
            
            out.println(""
                    + "function ActivateButton(state,text) {\n"
                    + "    if(state == 0) {\n"
                    + "        $(':button:contains(\\\'' + text + '\\\')').prop('disabled', true).addClass('ui-state-disabled');\n"
                    + "    } else {\n"
                    + "        $(':button:contains(\\\'' + text + '\\\')').prop('disabled', false).removeClass('ui-state-disabled');\n"
                    + "    }"
                    + "}\n"
                    + "");
            
            out.println(""
                    + "function ServiceReCheck(hstid,srvid,instid,id) {\n"
                    + "    $.ajax({\n"
                    + "        url: '/gateway/monitoring/ServiceReCheck?hstid=' + hstid + '&srvid=' + srvid + '&ts=' + base64_encode( $('#' + id).val() ) + '&instid=' + instid,\n"
                    + "        crossDomain: true,\n"
                    + "        success: function(json) {\n"
                    + "            if (json.EXEC == '1') {\n"
                    + "                DialogMailComplete(\"#DialogSuccess\",\"Aktion erfolgreich ausgef&uuml;hrt.\",\"Aktion erfolgreich ausgef&uuml;hrt.\");\n"
                    + "            } else {\n"
                    + "                DialogMailComplete(\"#DialogSuccess\",\"+++ Aktion nicht erfolgreich ausgef&uuml;hrt. +++\",\"<font color='#ff7777'>Aktion nicht erfolgreich ausgef&uuml;hrt.</font>\");\n"
                    + "            }\n"
                    + "        },\n"
                    + "        dataType: 'json',\n"
                    + "        cache: false\n"
                    + "    }); \n"
                    + "}\n"
                    + "");
            
            out.println(""
                    + "function ServiceDowntime(hstid,srvid,instid,dstart,dend,comment,cuid,ccid) {\n"
                    + "    $.ajax({\n"
                    + "        url: '/gateway/monitoring/ServiceDowntime?hstid=' + base64_encode( hstid ) + '&srvid=' + base64_encode( srvid ) + '&dstart=' + base64_encode( $('#' + dstart).val() ) + '&dend=' + base64_encode( $('#' + dend).val() ) + '&instid=' + base64_encode( instid ) + '&comment=' + base64_encode( $('#' + comment).val() ) + '&cuid=' + base64_encode( $('#' + cuid).val() ) + '&ccid=' + base64_encode( $('#' + ccid).val() ),\n"
                    + "        crossDomain: true,\n"
                    + "        success: function(json) {\n"
                    + "            if (json.EXEC == '1') {\n"
                    + "                DialogMailComplete(\"#DialogSuccess\",\"Aktion erfolgreich ausgef&uuml;hrt.\",\"Aktion erfolgreich ausgef&uuml;hrt.\");\n"
                    + "            } else {\n"
                    + "                DialogMailComplete(\"#DialogSuccess\",\"+++ Aktion nicht erfolgreich ausgef&uuml;hrt. +++\",\"<font color='#ff7777'>Aktion nicht erfolgreich ausgef&uuml;hrt.</font>\");\n"
                    + "            }\n"
                    + "        },\n"
                    + "        dataType: 'json',\n"
                    + "        cache: false\n"
                    + "    }); \n"
                    + "}\n"
                    + "");
            
            out.println(""
                    + "function ChangeSubject(id1,id2,host,service) {\n"
                    + "    var cu = $('#' + id1 + ' option:selected').text();\n"
                    + "    var a = $('#' + id2 + ' option:selected').text();\n"
                    + "    var an = a.substring(0,a.indexOf('\\('));\n"
                    + "    $('#InSub').val('Information aus dem Montoring: ' + cu + ' - ' + an + ' (Host: ' + base64_decode( host ) + ' Service: ' + base64_decode( service ) + ')');\n"
                    + "}\n"
                    + "");
            
            out.println(""
                    + "function ActivateMailing(id) {\n"
                    + "    if( $('#' + id).is(':checked') ) {\n"
                    + "        $('#CSEDscOverLay').css('display','none');\n"
                    + "        $('#CreateServiceEntryLeftMF').css('display','block');"
                    + "        $('#CreateServiceEntryLeftMH').css('display','block');"
                    + "        $('#CreateServiceEntryLeftMC div.jqte').css('height','361px');"
                    + "        $('#CreateServiceEntryLeftMC .jqte_editor').css('height','347px');"
                    + "        $('#CreateServiceEntryLeftMC .jqte_source').css('height','347px');"
                    + "        $('#CSEDsc2').css('color','green');\n"
                    + "    } else {\n"
                    + "        $('#CSEDscOverLay').css('display','block');\n"
                    + "        $('#CreateServiceEntryLeftMF').css('display','none');"
                    + "        $('#CreateServiceEntryLeftMH').css('display','none');"
                    + "        $('#CreateServiceEntryLeftMC div.jqte').css('height','630px');"
                    + "        $('#CreateServiceEntryLeftMC .jqte_editor').css('height','581px');"
                    + "        $('#CreateServiceEntryLeftMC .jqte_source').css('height','581px');"
                    + "        $('#CSEDsc2').css('color','#000');\n"
                    + "    }"
                    + "}\n"
                    + "");
            
            out.println(""
                    + "function GetSingleCustomerInfo(id,hstid) {\n"
                    + "    $.ajax({\n"
                    + "        url: '/gateway/monitoring/GetCustomerContractNumbers?cuid=' + base64_encode( $('#' + id).val() ) + '&hstid=' + hstid,\n"
                    + "        crossDomain: true,\n"
                    + "        success: function(json) {\n"
                    + "            $('#CreateServiceEntryAselect').html('<option value=\"0000\" selected>Bitte w&auml;hlen</option>');\n"
                    + "            $('#CreateServiceEntryDAselect').html('<option value=\"0000\" selected>Bitte w&auml;hlen</option>');\n"
                    + "            $.each(json.CONTRACT, function() {\n"
                    + "                $('#CreateServiceEntryAselect').append('<option value=\"' + base64_decode( this.CCID ) + '\">' + base64_decode( this.COTRLN ) + ' (' + base64_decode( this.CCNR ) + ')</option>');\n"
                    + "                $('#CreateServiceEntryDAselect').append('<option value=\"' + base64_decode( this.CCID ) + '\">' + base64_decode( this.COTRLN ) + ' (' + base64_decode( this.CCNR ) + ')</option>');\n"
                    + "            });\n"
                    + "        },\n"
                    + "        dataType: 'json',\n"
                    + "        cache: false\n"
                    + "    });\n"
                    + "    $.ajax({\n"
                    + "        url: '/gateway/exec/GetCustomerMailing?cuid=' + base64_encode( $('#' + id).val() ) + '&uuid=' + base64_encode( UUID ) + '&hstid=' + hstid,\n"
                    + "        crossDomain: true,\n"
                    + "        success: function(json) {\n"
                    + "            $('#InAN').val(base64_decode( json.TO ));\n"
                    + "            $('#InEsk1').val(base64_decode( json.ESK1 ));\n"
                    + "            $('#InEsk2').val(base64_decode( json.ESK2 ));\n"
                    + "            $('#InEsk3').val(base64_decode( json.ESK3 ));\n"
                    + "        },\n"
                    + "        dataType: 'json',\n"
                    + "        cache: false\n"
                    + "    });\n"
                    + "}\n"
                    + "");
            
            out.println(""
                    + "function ShowEscalationSubject(dsc,host,service) {\n"
                    + "    $('#InSub').val('!!! Wichtig !!! - Eskalation ' + dsc + ': Information aus dem Monitoring. (Host: ' + base64_decode( host ) + ' Service: ' + base64_decode( service ) + ')');\n"
                    + "}\n"
                    + "");
            
            }
            
            String line = "<div id=\"ServiceDetail\" title=\"Service Men&uuml;: ' + base64_decode( srvna ) + ' auf ' + base64_decode( hstna ) + '\">";
                   line+= "<div id=\"ServiceTabs\">";
                   line+= "<ul>";
                   line+= "<li><a href=\"#ServiceTabs1\" onclick=\"ActivateButton(\\\'0\\\',\\\'SPEICHERN\\\');\">Service Information</a></li>";
            
           if(csw == 1) {
               
                   line+= "<li class=\"ServiceTabs2LI\"><a href=\"#ServiceTabs2\" onclick=\"ActivateButton(\\\'1\\\',\\\'SPEICHERN\\\');\">Problem Bearbeitung</a></li>";
           
           }
           
                   line+= "</ul>";
                   line+= "<div id=\"ServiceTabs1\">";
                   line+= "    <div id=\"ServiceInfo\"></div>";
                   line+= "    <div id=\"ServiceReCheck\"><div id=\"ServiceReCheckH\"><span style=\"float: left\">Service erneut pr&uuml;fen</span><span style=\"float: left; margin-top: 0px;\" class=\"ui-icon ui-icon-triangle-1-s\"></span></div><div id=\"ServiceReCheckP\"><span>Zeitstempel: </span><input id=\"ServiceReCheckI\" class=\"ui-input-hofo ui-input-corner-all\" type=text value=\"' + now.format(\"yyyy-mm-dd HH:MM:ss\") + '\"/><button id=\"prfn\" onclick=\"ServiceReCheck(\\\'' + hstid + '\\\',\\\'' + srvid + '\\\',\\\'' + instid + '\\\',\\\'ServiceReCheckI\\\');\">Ausf&uuml;hren</button></div></div>";
                   line+= "    <div id=\"ServiceDowntime\"><div id=\"ServiceDowntimeH\"><span style=\"float: left\">Downtime</span><span style=\"float: left; margin-top: 0px;\" class=\"ui-icon ui-icon-triangle-1-s\"></span></div><select class=\"ui-input-hofo\" id=\"CreateServiceEntryDSselect\" onchange=\"GetSingleCustomerInfo(\\\'CreateServiceEntryDSselect\\\',\\\'\' + hstid + \'\\\');\"><option selected value=\"0000\">Kunde w&auml;hlen</option></select><select class=\"ui-input-hofo\" id=\"CreateServiceEntryDAselect\"><option selected value=\"0000\">-</option></select><textarea id=\"ServiceDowntimeT\" class=\"ui-input-hofo ui-input-corner-all\">Hier bitte einen Kommentar eintragen. z.B. Warum die Downtime angegeben wurde.</textarea><div id=\"ServiceDowntimeS\"><span>Start: </span><input id=\"ServiceDowntimeSi\" class=\"ui-input-hofo ui-input-corner-all\" type=text value=\"' + now.format(\"yyyy-mm-dd HH:MM:ss\") + '\"/></div><div id=\"ServiceDowntimeE\"><span>Ende: </span><input id=\"ServiceDowntimeEi\" class=\"ui-input-hofo ui-input-corner-all\" type=text value=\"' + now.format(\"yyyy-mm-dd HH:MM:ss\") + '\"/><button id=\"fstlgn\" onclick=\"ServiceDowntime(\\\'' + hstid + '\\\',\\\'' + srvid + '\\\',\\\'' + instid + '\\\',\\\'ServiceDowntimeSi\\\',\\\'ServiceDowntimeEi\\\',\\\'ServiceDowntimeT\\\',\\\'CreateServiceEntryDSselect\\\',\\\'CreateServiceEntryDAselect\\\');\">Festlegen</button></div></div>";
                   line+= "    <div id=\"ServiceAvailability\"><div id=\"ServiceAvailabilityH\"><span style=\"float: left\">Verf&uuml;gbarkeit</span><span style=\"float: left; margin-top: 0px;\" class=\"ui-icon ui-icon-triangle-1-s\"></span></div><br></div>";
                   line+= "    <div id=\"ServiceHistoryH\"><span style=\"float: left\">Historie</span><span style=\"float: left; margin-top: 0px;\" class=\"ui-icon ui-icon-triangle-1-s\"></span></div>";
                   line+= "    <div id=\"ServiceHistory\"></div>";
                   line+= "</div>";
                    
            if(csw == 1) {
            
                   line+= "        <div id=\"ServiceTabs2\">";
                   line+= "            <div id=\"CreateServiceEntryRight\">";
                   line+= "                <div id=\"CreateServiceEntryU\"><div id=\"CSEDsc\"><span style=\"float: left\">Arbeiten wurden ausgef&uuml;hrt durch</span><span style=\"float: left; margin-top: 0px;\" class=\"ui-icon ui-icon-triangle-1-s\"></span></div><input class=\"ui-input-hofo\" type=text value=\"' + FullName + '\" disabled/><input id=\"InFrom\" type=hidden value=\"' + UserMail + '\" /></div>";
                   line+= "                <div id=\"CreateServiceEntryC\"><div id=\"CSEDsc\"><span style=\"float: left\">Arbeiten wurden ausgef&uuml;hrt f&uuml;r</span><span style=\"float: left; margin-top: 0px;\" class=\"ui-icon ui-icon-triangle-1-s\"></span></div><div id=\"CreateServiceEntryCs\"><select class=\"ui-input-hofo\" id=\"CreateServiceEntryCselect\" onchange=\"GetSingleCustomerInfo(\\\'CreateServiceEntryCselect\\\',\\\'\' + hstid + \'\\\');\"><option selected value=\"0000\">Kunde w&auml;hlen</option></select></div></div>";
                   line+= "                <div id=\"CreateServiceEntryA\"><div id=\"CSEDsc\"><span style=\"float: left\">Auftragsnummer</span><span style=\"float: left; margin-top: 0px;\" class=\"ui-icon ui-icon-triangle-1-s\"></span></div><select class=\"ui-input-hofo\" id=\"CreateServiceEntryAselect\" onchange=\"ChangeSubject(\\\'CreateServiceEntryCselect\\\',\\\'CreateServiceEntryAselect\\\',\\\'' + hstna + '\\\',\\\'' + srvna + '\\\');\"><option selected value=\"0000\">-</option></select></div>";
                   line+= "                <div id=\"CreateServiceEntryT\"><div id=\"CSEDsc\"><span style=\"float: left\">Typ des Eintrags</span><span style=\"float: left; margin-top: 0px;\" class=\"ui-icon ui-icon-triangle-1-s\"></span></div><div id=\"CreateServiceEntryTs\"><select class=\"ui-input-hofo\" id=\"CreateServiceEntryTselect\"><option selected value=\"0000\">Bitte w&auml;hlen</option></select></div></div>";
                   line+= "                <div id=\"CreateServiceEntryS\"><div id=\"CSEDsc\"><span style=\"float: left\">Zeitstempel</span><span style=\"float: left; margin-top: 0px;\" class=\"ui-icon ui-icon-triangle-1-s\"></span></div><input class=\"ui-input-hofo\" id=\"CreateServiceEntryTime\" type=text value=\"' + now.format(\"yyyy-mm-dd HH:MM:ss\") + '\" disabled/></div>";
                   line+= "                <div id=\"CreateServiceEntryM\">";
                   line+= "                <div id=\"CSEDsc2\">Soll der Eintrag per Mail an den Kunden verschickt werden?";
                   line+= "                    <input id=\"CSEDscCB\" type=\"checkbox\" value=\"1\" name=\"mailingto\" onclick=\"ActivateMailing(\\\'CSEDscCB\\\');\">";
                   line+= "                </div>";
                   line+= "                <div id=\"CSEDscOverLay\"></div>";
                   line+= "                <div id=\"InANt\">An:</div><input class=\"ui-input-hofo\" id=\"InAN\" type=\"text\" />";
                   line+= "                <div id=\"InCCt\">Cc:</div><input class=\"ui-input-hofo\" id=\"InCC\" type=\"text\" />";
                   line+= "                <div id=\"InSubt\">Btr.</div><input class=\"ui-input-hofo\" id=\"InSub\" type=\"text\" value=\"\" />";
                   line+= "                <div id=\"CSEDsc2\">Soll der Eintrag eskaliert werden?</div>";
                   line+= "                <div id=\"CSEDsc3\" class=\"CSEDsc3a\"><span>Stufe 1 - erweitert den Betreff, CC bis in die FGL Ebene</span><input onclick=\"ShowEscalationSubject(\\\'Stufe 1\\\',\\\'' + hstna + '\\\',\\\'' + srvna + '\\\');\" type=\"radio\" value=\"1\" name=\"escalate\" /></div>";
                   line+= "                    <input class=\"ui-input-hofo\" id=\"InEsk1\" type=\"text\" value=\"\" />";
                   line+= "                <div id=\"CSEDsc3\" class=\"CSEDsc3b\">Stufe 2 - erweitert den Betreff, CC bis in die AL Ebene<input onclick=\"ShowEscalationSubject(\\\'Stufe 2\\\',\\\'' + hstna + '\\\',\\\'' + srvna + '\\\');\" type=\"radio\" value=\"2\" name=\"escalate\" /></div>";
                   line+= "                    <input class=\"ui-input-hofo\" id=\"InEsk2\" type=\"text\" value=\"\" />";
                   line+= "                <div id=\"CSEDsc3\" class=\"CSEDsc3c\">Stufe 3 - erweitert den Betreff, CC bis in die GF Ebene<input onclick=\"ShowEscalationSubject(\\\'Stufe 3\\\',\\\'' + hstna + '\\\',\\\'' + srvna + '\\\');\" type=\"radio\" value=\"3\" name=\"escalate\" /></div>";
                   line+= "                    <input class=\"ui-input-hofo\" id=\"InEsk3\" type=\"text\" value=\"\" />";
                   line+= "            </div></div>";
                   line+= "            <div id=\"CreateServiceEntryLeft\">";
                   line+= "                <div class=\"ui-input-hofo\" id=\"CreateServiceEntryLeftMH\"><div id=\"CreateServiceEntryLeftMHc\"></div></div>";
                   line+= "                <div class=\"ui-input-hofo\" id=\"CreateServiceEntryLeftMC\"><div id=\"CreateServiceEntryLeftMCc\"></div></div>";
                   line+= "                <div class=\"ui-input-hofo\" id=\"CreateServiceEntryLeftMF\"><div id=\"CreateServiceEntryLeftMFc\"></div></div>";
                   line+= "            </div>";
                   line+= "        </div>";
            
            }
            
                   line+= "    </div>";
                   line+= "</div>";
                    
            out.println(""
                    + "function ServiceMenu(hstna,hstid,srvna,srvid,instid,ack,ackid,dtm,dtmid) {\n"
                    + "    $.Shortcuts.stop();\n"
                    + "    var now = new Date();\n"
                    + "    $('#Dialog').html('" + line + "');\n"
                    + "    $('#ServiceTabs').tabs();\n"
                    + "    $('#CreateServiceEntryLeftMHc').jqte();\n"
                    + "    $('#CreateServiceEntryLeftMCc').jqte();\n"
                    + "    $('#CreateServiceEntryLeftMFc').jqte();\n"
                    + "    $('#prfn').button();\n"
                    + "    $('#fstlgn').button();\n"
                    + "    $('#ServiceReCheckI').datetimepicker();\n"
                    + "    $('#ServiceDowntimeSi').datetimepicker();\n"
                    + "    $('#ServiceDowntimeEi').datetimepicker();\n"
                    + "    $('#ServiceDetail').dialog({\n"
                    + "        autoOpen: true,\n"
                    + "        height: 820,\n"
                    + "        width: 1220,\n"
                    + "        draggable: false,\n"
                    + "        resizable: false,\n"
                    + "        modal: true,\n"
                    + "        open: function() {\n"
                    + "            $.ajax({\n"
                    + "                url: '/gateway/monitoring/ServiceAvail?srvid=' + srvid,\n"
                    + "                crossDomain: true,\n"
                    + "                success: function(json) {\n"
                    + "                    var ok = parseInt(json.OK);\n"
                    + "                    var wa = parseInt(json.WA);\n"
                    + "                    var cr = parseInt(json.CR);\n"
                    + "                    var un = parseInt(json.UN);\n"
                    + "                    var ges = ok + wa + cr + un;\n"
                    + "                    $('#ServiceAvailability').append('<span id=\"ServiceAvailabilityE\" class=\"ok\" title=\"Status: Ok\">' + ((ok*100)/ges).toFixed(3) + '%</span><span id=\"ServiceAvailabilityE\" class=\"warning\" title=\"Status: Warnung\">' + ((wa*100)/ges).toFixed(3) + '%</span><span id=\"ServiceAvailabilityE\" class=\"critical\" title=\"Status: Kritisch\">' + ((cr*100)/ges).toFixed(3) + '%</span><span id=\"ServiceAvailabilityE\" class=\"unknown\" title=\"Status: Unbekannt\">' + ((un*100)/ges).toFixed(3) + '%</span>');\n"
                    + "                },\n"
                    + "                dataType: 'json',\n"
                    + "                cache: false,\n"
                    + "                async: false"
                    + "            });\n"
                    + "            $.ajax({\n"
                    + "                url: '/gateway/monitoring/ServiceHistory?srvid=' + srvid,\n"
                    + "                crossDomain: true,\n"
                    + "                success: function(json) {\n"
                    + "                    $('#ServiceHistory').html('<table id=\"ServiceHistoryTable\"></table>');\n"
                    + "                    $.each(json, function() {\n"
                    + "                        var csscl;\n"
                    + "                        if (this.STATE == \"1\") { csscl = \"warning\"; } else if (this.STATE == \"2\") { csscl = \"critical\"; } else if (this.STATE == \"3\") { csscl = \"unknown\"; } else { csscl = \"ok\"; }\n"
                    + "                        $('#ServiceHistoryTable').append('<tr><td class=\"' + csscl + '\"></td><td>' + base64_decode( this.OUTPUT ) + '</td><td>' + this.CREATED_ISO + '</td></tr>');\n"
                    + "                    });\n"
                    + "                },\n"
                    + "                dataType: 'json',\n"
                    + "                cache: false,\n"
                    + "                async: false"
                    + "            });\n"
                    + "            $.ajax({\n"
                    + "                url: '/gateway/monitoring/HostInfo?hstid=' + hstid,\n"
                    + "                crossDomain: true,\n"
                    + "                success: function(json) {\n"
                    + "                    $('#ServiceInfo').append('<img class=\"img-smn\" src=\"public/images/' + base64_decode( json.HOST_ICON ) + '-128.png\"  title=\"' + base64_decode( json.HOST_TYPE ) + '\" />"
                    + "<div id=\"ServiceInfoHn\"><span style=\"float: left;\">Host Name</span><span style=\"float: left; margin-top: 0px;\" class=\"ui-icon ui-icon-triangle-1-s\"></span><br><span>' + base64_decode( json.HOST_NAME ) + '</span></div>"
                    + "<div id=\"ServiceInfoAd\"><span style=\"float: left;\">Adresse</span><span style=\"float: left; margin-top: 0px;\" class=\"ui-icon ui-icon-triangle-1-s\"></span><br><span>' + base64_decode( json.IP ) + '</span></div>"
                    + "<div id=\"ServiceInfoHt\"><span style=\"float: left;\">Host Typ</span><span style=\"float: left; margin-top: 0px;\" class=\"ui-icon ui-icon-triangle-1-s\"></span><br><span>' + base64_decode( json.HOST_TYPE ) + '</span></div>"
                    //+ "<span>' + json.STATE + '</span>"
                    + "<div id=\"ServiceInfoSn\"><span style=\"float: left;\">Service Name</span><span style=\"float: left; margin-top: 0px;\" class=\"ui-icon ui-icon-triangle-1-s\"></span><br><span>' + base64_decode( srvna ) + '</span></div>"
                    + "<div id=\"ServiceInfoCo\"><span style=\"float: left;\">Zugeordnete Kunden (Vertr&auml;ge)</span><span style=\"float: left; margin-top: 0px;\" class=\"ui-icon ui-icon-triangle-1-s\"></span><br><span id=\"ServiceInfoCoList\"></span></div>');"
                    + "                    $.each(json.CONTRACTS, function() {\n"
                    + "                        $('#ServiceInfoCoList').append('' + base64_decode( this.CUNM ) + ' (' + base64_decode( this.CUNR ) + ') -<br>' + base64_decode( this.CCNM ) + ' (' + base64_decode( this.CCNR ) + ')<br>');\n"
                    + "                    });\n"
                    + "                },\n"
                    + "                dataType: 'json',\n"
                    + "                cache: false,\n"
                    + "                async: false"
                    + "            });\n"
                    + "");
            
            if(csw == 1) {
            
            out.println(""
                    + "            ActivateButton('0','SPEICHERN');\n"
                    + "            if (ack == 't') {\n"
                    + "                $('.ServiceTabs2LI').hide();\n"
                    + "                $.ajax({\n"
                    + "                    url: '/gateway/monitoring/GetAcknowledgement?ackid=' + ackid,\n"
                    + "                    crossDomain: true,\n"
                    + "                    success: function(json) {\n"
                    + "                        $('#ServiceTabs1').append('<div id=\"AckMessage\" class=\"ui-state-error ui-corner-all\">Dieses Service Problem wurde bereits von ' + base64_decode( json.USER ) + ' (' + json.CREATED_ISO + ') bearbeitet.</div>');\n"
                    + "                    },\n"
                    + "                    dataType: 'json',\n"
                    + "                    cache: false\n"
                    + "                });\n"
                    + "            }\n"
                    + "            $.ajax({\n"
                    + "                url: '/gateway/monitoring/GetCustomer?hstid=' + hstid,\n"
                    + "                crossDomain: true,\n"
                    + "                success: function(json) {\n"
                    + "                    $.each(json.CUSTOMER, function() {\n"
                    + "                        $('#CreateServiceEntryCselect').append('<option value=\"' + base64_decode( this.CUID ) + '\">' + base64_decode( this.CUNM ) + '</option>');\n"
                    + "                        $('#CreateServiceEntryDSselect').append('<option value=\"' + base64_decode( this.CUID ) + '\">' + base64_decode( this.CUNM ) + '</option>');\n"
                    + "                    });\n"
                    + "                },\n"
                    + "                dataType: 'json',\n"
                    + "                cache: false\n"
                    + "            });\n"
                    + "            $.ajax({\n"
                    + "                url: '/gateway/exec/GetCommentTypes',\n"
                    + "                crossDomain: true,\n"
                    + "                success: function(json) {\n"
                    + "                    $.each(json.COMMENT_TYPES, function() {\n"
                    + "                        $('#CreateServiceEntryTselect').append('<option value=\"' + base64_decode( this.ID ) + '\">' + base64_decode( this.LN ) + '</option>');\n"
                    + "                    });\n"
                    + "                },\n"
                    + "                dataType: 'json',\n"
                    + "                cache: false\n"
                    + "            });\n"
                    + "            $.ajax({\n"
                    + "                url: '/gateway/exec/GetUserMailFormat?uuid=' + base64_encode( UUID ),\n"
                    + "                crossDomain: true,\n"
                    + "                success: function(json) {\n"
                    + "                    $('#CreateServiceEntryLeftMF div.jqte_editor').html(base64_decode( json.FOOTER ));\n"
                    + "                    $('#CreateServiceEntryLeftMH div.jqte_editor').html(base64_decode( json.HEADER ));\n"
                    + "                    $('#InCC').val(base64_decode( json.CC ));\n"
                    + "                },\n"
                    + "                dataType: 'json',\n"
                    + "                cache: false\n"
                    + "            });\n"
                    + "            $('#CreateServiceEntryLeftMC div.jqte_editor').html('<hr><b>Betroffene Dienste:</b><br><b>Host:</b> ' + base64_decode( hstna ) + '<br><b>Service:</b> ' + base64_decode( srvna ) + '<br><!--#ts:' + now.format(\"yyyy-mm-dd HH:MM:ss\") + '--><hr><br><br>');\n"
                    + "");
            
            }
            
            out.println(""
                    + "            PrepareHtmlPasteJqte('CreateServiceEntryLeftMH');"
                    + "            PrepareHtmlPasteJqte('CreateServiceEntryLeftMC');"
                    + "            PrepareHtmlPasteJqte('CreateServiceEntryLeftMF');"
                    + "        },"
                    + "        buttons: { \n"
                    + "");
            
            if(csw == 1) {
            
            out.println(""
                    + "            SPEICHERN: function() {\n"
                    + "                var esk='0';\n"
                    + "                var rep=0;\n"
                    + "                var err=0;\n"
                    + "                var msid=0;\n"
                    + "                var exec=0;\n"
                    + "                var content = base64_encode( $('#CreateServiceEntryLeftMC div.jqte_editor').html() ).replace(/\\+/g,'78');\n"
                    + "                var text = base64_encode( $('#CreateServiceEntryLeftMH div.jqte_editor').html() + '<br>' + $('#CreateServiceEntryLeftMC div.jqte_editor').html() + '<br>' + $('#CreateServiceEntryLeftMF div.jqte_editor').html() ).replace(/\\+/g,'78');\n"
                    + "                $('input:radio[name=\"escalate\"]').each( function() {\n"
                    + "                    if ($(this).is(':checked')) {\n"
                    + "                        var c = $(this).val();\n"
                    + "                        esk=c;\n"
                    + "                    }\n"
                    + "                });\n"
                    + "                $.ajax({\n"
                    + "                    url: '/gateway/exec/CreateServiceEntry?uuid=' + base64_encode( UUID ) + '&cuid=' + base64_encode( $('#CreateServiceEntryCselect').val() ) + '&ccid=' + base64_encode( $('#CreateServiceEntryAselect').val() ) + '&comtid=' + base64_encode( $('#CreateServiceEntryTselect').val() ) + '&tm=' + base64_encode( $('#CreateServiceEntryTime').val() ) + '&dl=' + base64_encode('0') + '&co=' + content + '&esk=' + base64_encode( esk ) + '',\n"
                    + "                    crossDomain: true,\n"
                    + "                    success: function(json) {\n"
                    + "                        msid = parseInt(json.MSID);\n"
                    + "                        exec = parseInt(json.EXEC);\n"
                    + "                        if (json.EXEC == '1') {\n"
                    + "                            DialogMailComplete(\"#DialogSuccess\",\"Serviceeintrag erfolgreich erstellt.\",\"Serviceeintrag wurde erfolgreich erstellt.\");\n"
                    + "                        } else {\n"
                    + "                            DialogMailComplete(\"#DialogSuccess\",\"+++ Serviceeintrag konnte nicht erstellt werden. +++\",\"<font color='#ff7777'>Serviceeintrag konnte nicht erstellt werden.</font>\");\n"
                    + "                        }\n"
                    + "                    },\n"
                    + "                    error: function(jqxhr,textStatus,errorThrown) {\n"
                    + "                        DialogMailComplete(\"#DialogSuccess\",\"+++ Serviceeintrag konnte nicht erstellt werden. +++\",\"<font color='#ff7777'>Serviceeintrag konnte nicht erstellt werden.</font>\");\n"
                    + "                    },\n"
                    + "                    dataType: 'json',\n"
                    + "                    cache: false,\n"
                    + "                    async: false\n"
                    + "                });\n"
                    + "                if (exec == 1 && msid > 0) {\n"
                    + "                  if( $('#CSEDscCB').is(':checked') ) {\n"
                    + "                    var cc = $('#InCC').val();\n"
                    + "                    $('input:radio[name=\"escalate\"]').each( function() {\n"
                    + "                        if ($(this).is(':checked')) {\n"
                    + "                            var c = $(this).val();\n"
                    + "                            cc += ',' + $('#InEsk' + c).val();\n"
                    + "                            esk=c;\n"
                    + "                        }\n"
                    + "                    });\n"
                    + "                    var an = $('#InAN').val();\n"
                    + "                    $.ajax({\n"
                    + "                        url: '/gateway/exec/CreateMailEntry?msid=' + msid + '&uuid=' + UUID + '&cuid=' + $('#CreateServiceEntryCselect').val() + '&ccid=' + $('#CreateServiceEntryAselect').val() + '&mto=' + base64_encode( an ) + '&mcc=' + base64_encode( cc ) + '&msubject=' + base64_encode( $('#InSub').val() ) + '&mbody=' + text + '&mesc=' + esk,\n"
                    + "                        crossDomain: true,\n"
                    + "                        success: function(json) {\n"
                    + "                            if (json.EXEC == '1') {\n"
                    + "                                DialogMailComplete(\"#MSDialogSuccess\",\"E-Mail erfolgreich in Queue eintragen.\",\"Die E-Mail wurde erfolgreich in die Queue eingetragen.\");\n"
                    + "                            } else {\n"
                    + "                                DialogMailComplete(\"#MSDialogSuccess\",\"+++ E-Mail konnte nicht in Queue eingetragen werden. +++\",\"<font color='#ff7777'>Die E-Mail konnte nicht in die Queue eingetragen werden.</font>\");\n"
                    + "                            }\n"
                    + "                        },\n"
                    + "                        dataType: 'json',\n"
                    + "                        cache: false,\n"
                    + "                        async: false\n"
                    + "                    });\n"
                    + "                  }\n"
                    + "                }\n"
                    + "                $.ajax({\n"
                    + "                    url: '/gateway/monitoring/AcknowledgeService?hstid=' + base64_encode( hstid ) + '&srvid=' + base64_encode( srvid ) + '&instid=' + base64_encode( instid ) + '&cuid=' + base64_encode( $('#CreateServiceEntryCselect').val() ) + '&ccid=' + base64_encode( $('#CreateServiceEntryAselect').val() ) + '&tm=' + base64_encode( $('#CreateServiceEntryTime').val() ) + '&co=' + content,\n"
                    + "                    crossDomain: true,\n"
                    + "                    success: function(json) {\n"
                    + "                        if (json.EXEC == '1') {\n"
                    + "                            DialogMailComplete(\"#DialogSuccess\",\"Serviceproblem erfolgreich bearbeiten.\",\"Serviceproblem wurde erfolgreich bearbeitet.\");\n"
                    + "                        } else {\n"
                    + "                            DialogMailComplete(\"#DialogSuccess\",\"+++ Serviceproblem konnte nicht bearbeitet werden. +++\",\"<font color='#ff7777'>Serviceproblem konnte nicht bearbeitet werden.</font>\");\n"
                    + "                        }\n"
                    + "                    },\n"
                    + "                    error: function(jqxhr,textStatus,errorThrown) {\n"
                    + "                        DialogMailComplete(\"#DialogSuccess\",\"+++ Serviceproblem konnte nicht bearbeitet werden. +++\",\"<font color='#ff7777'>Serviceproblem konnte nicht bearbeitet werden.</font>\");\n"
                    + "                    },\n"
                    + "                    dataType: 'json',\n"
                    + "                    cache: false,\n"
                    + "                    async: false\n"
                    + "                });\n"
                    + "                $(this).dialog('close');\n"
                    + "                $('#ServiceDetail').remove();\n"
                    + "                $.Shortcuts.start();\n"
                    //+ "                location.reload();\n"
                    + "            },\n"
                    + "");
            
            }
            
            out.println(""
                    + "            BEENDEN: function() {\n"
                    + "                $(this).dialog('close');\n"
                    + "                $('#ServiceDetail').remove();\n"
                    + "                $.Shortcuts.start();\n"
                    + "            }\n"
                    + "        }\n"
                    + "    }).parent().find('.ui-dialog-titlebar-close').hide();\n"
                    + "}\n"
                    + "");
            
        }
        
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, FileNotFoundException {
        try {
            processRequest(request, response, request.getRemoteUser());
        } catch (NamingException ex) {
            Logger.getLogger(TacticalOverview.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(TacticalOverview.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //
    }
}
