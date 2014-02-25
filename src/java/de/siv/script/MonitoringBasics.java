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
public class MonitoringBasics extends HttpServlet {
    Properties props = null;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response, String Uid)
            throws ServletException, IOException, FileNotFoundException, NamingException, SQLException {
        PrintWriter out = response.getWriter(); 
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "*");
        response.setContentType("application/javascript; charset=utf-8");
        
        int csw = 0;
        
        if(Executions.UserIsPermitted(Uid,"managed_services_csw")) { csw = 1; }
        
        if(Executions.UserIsPermitted(Uid,"monitoring")) {
        
            out.println(""
                    + "function ShowMonitoringFull(uid) {\n"
                    + "    $.ajax({\n"
                    + "        url: '/gateway/monitoring/MonitoringFull',\n"
                    + "        crossDomain: true,\n"
                    + "        success: function(json) {\n"
                    + "            $('#div-monitoring').html('<div id=\"div-monitoring-content\"></div>');\n"
                    + "            $.each(json, function() {\n"
                    + "                var hstid = this.HOST_ID;\n"
                    + "                var hstna = this.HOST_NAME;\n"
                    + "                $('#div-monitoring-content').append('<div id=\"div-monitoring-entry\" class=\"ui-input-hofo\"><img class=\"img-h\" src=\"public/images/' + $.base64.decode( this.HOST_TYPE ) + '-128.png\"  title=\"' + $.base64.decode( this.HOST_TYLN ) + '\" /><span class=\"head-h\">' + $.base64.decode( this.HOST_NAME ) + '</span><span id=\"c' + hstid + '\" class=\"content-h\"></span></div>');\n"
                    + "                $.each(this.SERVICES, function() {\n"
                    + "                    var csscl;\n"
                    + "                    if (this.ACK == \"t\") { csscl = \"ack\"; } else { if (this.STATE == \"1\") { csscl = \"warning\"; } else if (this.STATE == \"2\") { csscl = \"critical\"; } else if (this.STATE == \"3\") { csscl = \"unknown\"; } else { csscl = \"ok\"; } }\n"
                    + "                    $('#c' + hstid).append('<span style=\"cursor: pointer;\" class=\"ui-taov-tile ' + csscl + '\" title=\"' + $.base64.decode( this.SERVICE_NAME ) + '\" onclick=\"ServiceMenu(\\\'' + hstna + '\\\',\\\'' + hstid + '\\\',\\\'' + this.SERVICE_NAME + '\\\',\\\'' + this.SERVICE_ID + '\\\',\\\'' + this.INSTID + '\\\',\\\'' + this.ACK + '\\\',\\\'' + this.ACKID + '\\\');\"></span>');\n"
                    + "                });\n"
                    + "            });\n"
                    + "        },\n"
                    + "        dataType: 'json',\n"
                    + "        cache: false\n"
                    + "    }); \n"
                    + "    setTimeout('ShowMonitoringFull()', 20000);\n"
                    + "}\n"
                    + "");
            
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
                    + "        url: '/gateway/monitoring/ServiceReCheck?hstid=' + hstid + '&srvid=' + srvid + '&ts=' + $.base64.encode( $('#' + id).val() ) + '&instid=' + instid,\n"
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
                    + "    $('#InSub').val('Information aus dem Montoring: ' + cu + ' - ' + an + ' (Host: ' + $.base64.decode( host ) + ' Service: ' + $.base64.decode( service ) + ')');\n"
                    + "}\n"
                    + "");
            
            out.println(""
                    + "function ActivateMailing(id) {\n"
                    + "    if( $('#' + id).is(':checked') ) {\n"
                    + "        $('#CSEDscOverLay').css('display','none');\n"
                    + "        $('#CreateServiceEntryLeftMF').css('display','block');"
                    + "        $('#CreateServiceEntryLeftMH').css('display','block');"
                    + "        $('#CreateServiceEntryLeftMC div.jqte').css('height','396px');"
                    + "        $('#CreateServiceEntryLeftMC .jqte_editor').css('height','347px');"
                    + "        $('#CreateServiceEntryLeftMC .jqte_source').css('height','347px');"
                    + "        $('#CSEDsc2').css('color','green');\n"
                    + "    } else {\n"
                    + "        $('#CSEDscOverLay').css('display','block');\n"
                    + "        $('#CreateServiceEntryLeftMF').css('display','none');"
                    + "        $('#CreateServiceEntryLeftMH').css('display','none');"
                    + "        $('#CreateServiceEntryLeftMC div.jqte').css('height','640px');"
                    + "        $('#CreateServiceEntryLeftMC .jqte_editor').css('height','591px');"
                    + "        $('#CreateServiceEntryLeftMC .jqte_source').css('height','591px');"
                    + "        $('#CSEDsc2').css('color','#000');\n"
                    + "    }"
                    + "}\n"
                    + "");
            
            out.println(""
                    + "function GetSingleCustomerInfo(id,hstid) {\n"
                    + "    $.ajax({\n"
                    + "        url: '/gateway/monitoring/GetCustomerContractNumbers?cuid=' + $.base64.encode( $('#' + id).val() ) + '&hstid=' + hstid,\n"
                    + "        crossDomain: true,\n"
                    + "        success: function(json) {\n"
                    + "            $('#CreateServiceEntryAselect').html('<option value=\"0000\" selected>Bitte w&auml;hlen</option>');\n"
                    + "            $.each(json.CONTRACT, function() {\n"
                    + "                $('#CreateServiceEntryAselect').append('<option value=\"' + $.base64.decode( this.CCID ) + '\">' + $.base64.decode( this.COTRLN ) + ' (' + $.base64.decode( this.CCNR ) + ')</option>');\n"
                    + "            });\n"
                    + "        },\n"
                    + "        dataType: 'json',\n"
                    + "        cache: false\n"
                    + "    });\n"
                    + "    $.ajax({\n"
                    + "        url: '/gateway/exec/GetCustomerMailing?cuid=' + $.base64.encode( $('#' + id).val() ) + '&uuid=' + $.base64.encode( UUID ) + '&hstid=' + hstid,\n"
                    + "        crossDomain: true,\n"
                    + "        success: function(json) {\n"
                    + "            $('#InAN').val($.base64.decode( json.TO ));\n"
                    + "            $('#InEsk1').val($.base64.decode( json.ESK1 ));\n"
                    + "            $('#InEsk2').val($.base64.decode( json.ESK2 ));\n"
                    + "            $('#InEsk3').val($.base64.decode( json.ESK3 ));\n"
                    + "        },\n"
                    + "        dataType: 'json',\n"
                    + "        cache: false\n"
                    + "    });\n"
                    + "}\n"
                    + "");
            
            out.println(""
                    + "function ShowEscalationSubject(dsc,host,service) {\n"
                    + "    $('#InSub').val('!!! Wichtig !!! - Eskalation ' + dsc + ': Information aus dem Monitoring. (Host: ' + $.base64.decode( host ) + ' Service: ' + $.base64.decode( service ) + ')');\n"
                    + "}\n"
                    + "");
            
            }
            
            String line = "<div id=\"ServiceDetail\" title=\"Service Men&uuml;: ' + $.base64.decode( srvna ) + ' auf ' + $.base64.decode( hstna ) + '\">";
                   line+= "<div id=\"ServiceTabs\">";
                   line+= "<ul>";
                   line+= "<li><a href=\"#ServiceTabs1\" onclick=\"ActivateButton(\\\'0\\\',\\\'EINTRAGEN\\\');\">Service Information</a></li>";
            
           if(csw == 1) {
               
                   line+= "<li class=\"ServiceTabs2LI\"><a href=\"#ServiceTabs2\" onclick=\"ActivateButton(\\\'1\\\',\\\'EINTRAGEN\\\');\">Problem Bearbeitung</a></li>";
           
           }
           
                   line+= "</ul>";
                   line+= "<div id=\"ServiceTabs1\">";
                   line+= "    <div id=\"ServiceInfo\"></div>";
                   line+= "    <div id=\"ServiceReCheck\"><div id=\"ServiceReCheckH\"><span style=\"float: left\">Service erneut pr&uuml;fen</span><span style=\"float: left; margin-top: 0px;\" class=\"ui-icon ui-icon-triangle-1-s\"></span></div><div id=\"ServiceReCheckP\"><span>Zeitstempel: </span><input id=\"ServiceReCheckI\" class=\"ui-input-hofo ui-input-corner-all\" type=text value=\"' + now.format(\"yyyy-mm-dd HH:MM:ss\") + '\"/><button id=\"prfn\" onclick=\"ServiceReCheck(\\\'' + hstid + '\\\',\\\'' + srvid + '\\\',\\\'' + instid + '\\\',\\\'ServiceReCheckI\\\');\">Ausf&uuml;hren</button></div></div>";
                   line+= "    <div id=\"ServiceDowntime\"><div id=\"ServiceDowntimeH\"><span style=\"float: left\">Downtime</span><span style=\"float: left; margin-top: 0px;\" class=\"ui-icon ui-icon-triangle-1-s\"></span></div><textarea id=\"ServiceDowntimeT\" class=\"ui-input-hofo ui-input-corner-all\">Hier bitte einen Kommentar eintragen. z.B. Warum die Downtime angegeben wurde.</textarea><div id=\"ServiceDowntimeS\"><span>Start: </span><input id=\"ServiceDowntimeSi\" class=\"ui-input-hofo ui-input-corner-all\" type=text value=\"' + now.format(\"yyyy-mm-dd HH:MM:ss\") + '\"/></div><div id=\"ServiceDowntimeE\"><span>Ende: </span><input id=\"ServiceDowntimeEi\" class=\"ui-input-hofo ui-input-corner-all\" type=text value=\"' + now.format(\"yyyy-mm-dd HH:MM:ss\") + '\"/><button id=\"fstlgn\">Festlegen</button></div></div>";
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
                    + "function ServiceMenu(hstna,hstid,srvna,srvid,instid,ack,ackid) {\n"
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
                    + "        height: 850,\n"
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
                    + "                        $('#ServiceHistoryTable').append('<tr><td class=\"' + csscl + '\"></td><td>' + $.base64.decode( this.OUTPUT ) + '</td><td>' + this.CREATED_ISO + '</td></tr>');\n"
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
                    + "                    $('#ServiceInfo').append('<img class=\"img-smn\" src=\"public/images/' + $.base64.decode( json.HOST_ICON ) + '-128.png\"  title=\"' + $.base64.decode( json.HOST_TYPE ) + '\" />"
                    + "<div id=\"ServiceInfoHn\"><span style=\"float: left;\">Host Name</span><span style=\"float: left; margin-top: 0px;\" class=\"ui-icon ui-icon-triangle-1-s\"></span><br><span>' + $.base64.decode( json.HOST_NAME ) + '</span></div>"
                    + "<div id=\"ServiceInfoAd\"><span style=\"float: left;\">Adresse</span><span style=\"float: left; margin-top: 0px;\" class=\"ui-icon ui-icon-triangle-1-s\"></span><br><span>' + $.base64.decode( json.IP ) + '</span></div>"
                    + "<div id=\"ServiceInfoHt\"><span style=\"float: left;\">Host Typ</span><span style=\"float: left; margin-top: 0px;\" class=\"ui-icon ui-icon-triangle-1-s\"></span><br><span>' + $.base64.decode( json.HOST_TYPE ) + '</span></div>"
                    //+ "<span>' + json.STATE + '</span>"
                    + "<div id=\"ServiceInfoSn\"><span style=\"float: left;\">Service Name</span><span style=\"float: left; margin-top: 0px;\" class=\"ui-icon ui-icon-triangle-1-s\"></span><br><span>' + $.base64.decode( srvna ) + '</span></div>"
                    + "<div id=\"ServiceInfoCo\"><span style=\"float: left;\">Zugeordnete Kunden (Vertr&auml;ge)</span><span style=\"float: left; margin-top: 0px;\" class=\"ui-icon ui-icon-triangle-1-s\"></span><br><span id=\"ServiceInfoCoList\"></span></div>');"
                    + "                    $.each(json.CONTRACTS, function() {\n"
                    + "                        $('#ServiceInfoCoList').append('' + $.base64.decode( this.CUNM ) + ' (' + $.base64.decode( this.CCNM ) + ')<br>');\n"
                    + "                    });\n"
                    + "                },\n"
                    + "                dataType: 'json',\n"
                    + "                cache: false,\n"
                    + "                async: false"
                    + "            });\n"
                    + "");
            
            if(csw == 1) {
            
            out.println(""
                    + "            ActivateButton('0','EINTRAGEN');\n"
                    + "            if (ack == 't') {\n"
                    + "                $('.ServiceTabs2LI').hide();\n"
                    + "                $.ajax({\n"
                    + "                    url: '/gateway/monitoring/GetAcknowledgement?ackid=' + ackid,\n"
                    + "                    crossDomain: true,\n"
                    + "                    success: function(json) {\n"
                    + "                        $('#ServiceTabs1').append('<div id=\"AckMessage\" class=\"ui-state-error ui-corner-all\">Dieses Service Problem wurde bereits von ' + $.base64.decode( json.USER ) + ' (' + json.CREATED_ISO + ') bearbeitet.</div>');\n"
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
                    + "                        $('#CreateServiceEntryCselect').append('<option value=\"' + $.base64.decode( this.CUID ) + '\">' + $.base64.decode( this.CUNM ) + '</option>');\n"
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
                    + "                        $('#CreateServiceEntryTselect').append('<option value=\"' + $.base64.decode( this.ID ) + '\">' + $.base64.decode( this.LN ) + '</option>');\n"
                    + "                    });\n"
                    + "                },\n"
                    + "                dataType: 'json',\n"
                    + "                cache: false\n"
                    + "            });\n"
                    + "            $('#CreateServiceEntryLeftMC div.jqte_editor').html('<hr><b>Betroffene Dienste:</b><br><b>Host:</b> ' + $.base64.decode( hstna ) + '<br><b>Service:</b> ' + $.base64.decode( srvna ) + '<br><!--#ts:' + now.format(\"yyyy-mm-dd HH:MM:ss\") + '--><hr><br><br>');\n"
                    + "");
            
            }
            
            out.println(""
                    + "        },"
                    + "        buttons: { \n"
                    + "");
            
            if(csw == 1) {
            
            out.println(""
                    + "            EINTRAGEN: function() {\n"
                    + "                var esk=0;\n"
                    + "                var rep=0;\n"
                    + "                var err=0;\n"
                    + "                var content = $.base64.encode( $('#CreateServiceEntryLeftMC div.jqte_editor').html() ).replace(/\\+/g,'78');\n"
                    + "                var text = $.base64.encode( $('#CreateServiceEntryLeftMH div.jqte_editor').html() + '<br>' + $('#CreateServiceEntryLeftMC div.jqte_editor').html() + '<br>' + $('#CreateServiceEntryLeftMF div.jqte_editor').html() ).replace(/\\+/g,'78');\n"
                    + "                if( $('#CSEDscCB').is(':checked') ) {\n"
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
                    + "                        url: '/gateway/exec/SendHtmlMail?to=' + $.base64.encode( an ) + '&cc=' + $.base64.encode( cc ) + '&from=' + $.base64.encode( $('#InFrom').val() ) + '&subject=' + $.base64.encode( $('#InSub').val() ) + '&text=' + text,\n"
                    + "                        crossDomain: true,\n"
                    + "                        success: function(json) {\n"
                    + "                            if (json.SEND == '1') {\n"
                    + "                                DialogMailComplete(\"#DialogSuccess\",\"E-Mail erfolgreich versendet.\",\"Die E-Mail an \" + an + \" wurde erfolgreich versendet.\");\n"
                    + "                            } else {\n"
                    + "                                DialogMailComplete(\"#DialogSuccess\",\"+++ E-Mail konnte nicht versendet werden. +++\",\"<font color='#ff7777'>Die E-Mail an \" + an + \" konnte nicht versendet werden.</font>\");\n"
                    + "                            }\n"
                    + "                        },\n"
                    + "                        dataType: 'json',\n"
                    + "                        cache: false\n"
                    + "                    });\n"
                    + "                }\n"
                    + "                $.ajax({\n"
                    + "                    url: '/gateway/exec/CreateServiceEntry?uuid=' + $.base64.encode( UUID ) + '&cuid=' + $.base64.encode( $('#CreateServiceEntryCselect').val() ) + '&ccid=' + $.base64.encode( $('#CreateServiceEntryAselect').val() ) + '&comtid=' + $.base64.encode( $('#CreateServiceEntryTselect').val() ) + '&tm=' + $.base64.encode( $('#CreateServiceEntryTime').val() ) + '&dl=' + $.base64.encode('0') + '&co=' + content + '&esk=' + $.base64.encode( esk ) + '',\n"
                    + "                    crossDomain: true,\n"
                    + "                    success: function(json) {\n"
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
                    + "                    cache: false\n"
                    + "                });\n"
                    + "                $.ajax({\n"
                    + "                    url: '/gateway/monitoring/AcknowledgeService?hstid=' + $.base64.encode( hstid ) + '&srvid=' + $.base64.encode( srvid ) + '&instid=' + $.base64.encode( instid ) + '&cuid=' + $.base64.encode( $('#CreateServiceEntryCselect').val() ) + '&ccid=' + $.base64.encode( $('#CreateServiceEntryAselect').val() ) + '&tm=' + $.base64.encode( $('#CreateServiceEntryTime').val() ) + '&co=' + content,\n"
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
                    + "                    cache: false\n"
                    + "                });\n"
                    + "                $(this).dialog('close');\n"
                    + "                $('#ServiceDetail').remove();\n"
                    + "                $.Shortcuts.start();\n"
                    + "                location.reload();\n"
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
    
    @Override
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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //
    }
}
