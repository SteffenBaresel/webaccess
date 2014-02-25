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
        
        if(Executions.UserIsPermitted(Uid,"liveticker")) {
        
            out.println(""
                    + "function SummaryView(uid) {\n"
                    + "    $.ajax({\n"
                    + "        url: '/gateway/monitoring/BigTaov',\n"
                    + "        crossDomain: true,\n"
                    + "        success: function(json) {\n"
                    + "            $('section#big-taov').html('<div id=\"big-taov-hosts\"><span style=\"float: left;\" id=\"big-taov-header\">Hosts</span><span style=\"float: left; margin-top: 0px;\" class=\"ui-icon ui-icon-triangle-1-s\"></span><br><span id=\"big-taov-hosts-content\"></span></div><div id=\"big-taov-services\"><span id=\"big-taov-header\">Services</span><span style=\"float: left; margin-top: 0px;\" class=\"ui-icon ui-icon-triangle-1-s\"></span><br><span id=\"big-taov-services-content\"></span></div>');\n"
                    + "            $.each(json.HOSTS, function() {\n"
                    + "                var csscl;\n"
                    + "                if (this.ACK == \"t\") { csscl = \"ack\"; } else { if (this.STATE == \"1\") { csscl = \"critical\"; } else if (this.STATE == \"2\") { csscl = \"unknown\"; } else { csscl = \"ok\"; } }\n"
                    + "                $('#big-taov-hosts-content').append('<span class=\"ui-taov-tile ' + csscl + '\" title=\"' + $.base64.decode( this.HOST_NAME ) + '\"></span>');\n"
                    + "            });\n"
                    + "            $.each(json.SERVICES, function() {\n"
                    + "                var csscl;\n"
                    + "                if (this.ACK == \"t\") { csscl = \"ack\"; } else { if (this.STATE == \"1\") { csscl = \"warning\"; } else if (this.STATE == \"2\") { csscl = \"critical\"; } else if (this.STATE == \"3\") { csscl = \"unknown\"; } else { csscl = \"ok\"; } }\n"
                    + "                $('#big-taov-services-content').append('<span class=\"ui-taov-tile ' + csscl + '\" title=\"Service ' + $.base64.decode( this.SRV_NAME ) + ' auf ' + $.base64.decode( this.HOST_NAME ) + '\"></span>');\n"
                    + "            });\n"
                    + "            $('#SlimTaov').html('<div id=\"Nodes\"></div><div id=\"div-liveticker\"></div>');\n"
                    + "\n"
                    + "            var ocolor; var ccolor; var ucolor;\n"
                    + "            var host_up = parseInt(json.SLIMTAOV[0].HOSTS[0].UP, 10);\n"
                    + "            var host_down = parseInt(json.SLIMTAOV[0].HOSTS[0].DOWN, 10);\n"
                    + "            var host_unr = parseInt(json.SLIMTAOV[0].HOSTS[0].UNREACHABLE, 10);\n"
                    + "            if (host_up != 0) { ocolor = ' taov_ok'; } else { ocolor = ' taov_default'; }\n"
                    + "            if (host_down != 0) { ccolor = ' taov_cr'; } else { ccolor = ' taov_default'; }\n"
                    + "            if (host_unr != 0) { ucolor = ' taov_un'; } else { ucolor = ' taov_default'; }\n"
                    + "            $('#Nodes').append('<span style=\"float: left;\"><table cellpadding=0 cellspacing=0 border=0><tr><td colspan=3><span style=\"float: left;\">Hosts</span><span style=\"float: left; margin-top: 0px;\" class=\"ui-icon ui-icon-triangle-1-s\"></span></td></tr><tr valign=middle><td class=\"' + ocolor + '\"><b><a href=\"\">' + host_up + '</a></b> Up</td><td class=\"' + ccolor + '\"><b><a href=\"\">' + host_down + '</a></b> Down</td><td class=\"' + ucolor + '\"><b><a href=\"\">' + host_unr + '</a></b> Nicht Erreichbar</td></tr></table></span>');\n"
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
                    + "            $('#Nodes').append('<span style=\"float: left;\"><table cellpadding=0 cellspacing=0 border=0><tr><td colspan=3><span style=\"float: left;\">Services</span><span style=\"float: left; margin-top: 0px;\" class=\"ui-icon ui-icon-triangle-1-s\"></span></td></tr><tr valign=middle><td class=\"' + socolor + '\"><b><a href=\"\">' + service_ok + '</a></b> Ok</td><td class=\"' + swcolor + '\"><b><a href=\"\">' + service_warn + '</a></b> Warnung</td><td class=\"' + sccolor + '\"><b><a href=\"\">' + service_crit + '</a></b> Kritisch</td><td class=\"' + sucolor + '\"><b><a href=\"\">' + service_unk + '</a></b> Unbekannt</td></tr></table></span>');\n"
                    + "\n"
                    + "            var dbocolor; var dbccolor;\n"
                    + "            var db_open = parseInt(json.SLIMTAOV[2].DATABASES[0].OPEN, 10);\n"
                    + "            var db_stopped = parseInt(json.SLIMTAOV[2].DATABASES[0].STOPPED, 10);\n"
                    + "            if (db_open != 0) { dbocolor = ' taov_ok'; } else { dbocolor = ' taov_default'; }\n"
                    + "            if (db_stopped != 0) { dbccolor = ' taov_cr'; } else { dbccolor = ' taov_default'; }\n"
                    + "            $('#Nodes').append('<span style=\"float: left;\"><table cellpadding=0 cellspacing=0 border=0><tr><td colspan=3><span style=\"float: left;\">Datenbanken</span><span style=\"float: left; margin-top: 0px;\" class=\"ui-icon ui-icon-triangle-1-s\"></span></td></tr><tr valign=middle><td class=\"' + dbocolor + '\"><b><a href=\"\">' + db_open + '</a></b> Open</td><td class=\"' + dbccolor + '\"><b><a href=\"\">' + db_stopped + '</a></b> Stopped</td></tr></table></span>');\n"
                    + "\n"
                    + "            var mwocolor; var mwccolor;\n"
                    + "            var mw_online = parseInt(json.SLIMTAOV[3].MIDDLEWARE[0].ONLINE, 10);\n"
                    + "            var mw_offline = parseInt(json.SLIMTAOV[3].MIDDLEWARE[0].OFFLINE, 10);\n"
                    + "            if (mw_online != 0) { mwocolor = ' taov_ok'; } else { mwocolor = ' taov_default'; }\n"
                    + "            if (mw_offline != 0) { mwccolor = ' taov_cr'; } else { mwccolor = ' taov_default'; }\n"
                    + "            $('#Nodes').append('<span style=\"float: left;\"><table cellpadding=0 cellspacing=0 border=0><tr><td colspan=3><span style=\"float: left;\">Middleware</span><span style=\"float: left; margin-top: 0px;\" class=\"ui-icon ui-icon-triangle-1-s\"></span></td></tr><tr valign=middle><td class=\"' + mwocolor + '\"><b><a href=\"\">' + mw_online + '</a></b> Online</td><td class=\"' + mwccolor + '\"><b><a href=\"\">' + mw_offline + '</a></b> Offline</td></tr></table></span>');\n"
                    + "\n"           
                    + "            /*\n"
                    + "             * Hosts\n"
                    + "             */\n"
                    + "\n"
                    + "            var hge = host_up + host_down + host_unr;\n"
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
                    + "            });\n"
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
                    + "                        $('#LivetickerEContent').append('<div id=\"LivetickerELine\" class=\"ui-input-hofo\"><img src=\"public/images/' + $.base64.decode( this.HOST_TYPE ) + '-128.png\"  title=\"' + $.base64.decode( this.HOST_TYLN ) + '\" /><span class=\"HostName\">Host Name: ' + $.base64.decode( this.HOST_NAME ) + '</span><span class=\"ServiceName\">Service Name: ' + $.base64.decode( this.SERVICE_NAME ) + '</span><span class=\"Timestamp\">Erstellt: ' + this.CREATED_ISO + '</span><span class=\"Output\">' + $.base64.decode( this.OUTPUT ) + '</span></div>');\n"
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
