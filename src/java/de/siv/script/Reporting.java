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
public class Reporting extends HttpServlet {
    Properties props = null;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response, String Uid)
            throws ServletException, IOException, FileNotFoundException, NamingException, SQLException {
        PrintWriter out = response.getWriter(); 
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "*");
        response.setContentType("application/javascript; charset=utf-8");
        
        Integer RPG=0; if(Executions.UserIsPermitted(Uid,"reporting")) { RPG=1; }
        
        if(RPG == 1) {
            
            out.println("" +
                "function GetReportingCustomer() {\n" +
                "    $.ajax({\n" +
                "        url: '/gateway/exec/GetCustomer',\n" +
                "        crossDomain: true,\n" +
                "        success: function(json) {\n" +
                "            $.each(json.CUSTOMER, function() {\n" +
                "                $('#ReportingSelect').append('<option value=\"' + base64_decode( this.CUID ) + '\">' + base64_decode( this.CUNM ) + '</option>');\n" +
                "            });\n" +
                "        },\n" +
                "        dataType: 'json',\n" +
                "        cache: false\n" +
                "    });\n" +
                "    $('#ReportingInput1').datepicker({ constrainInput: false });\n" +
                "    $('#ReportingInput2').datepicker({ constrainInput: false });\n" +
                "}\n" +
                "");
            
            out.println("" +
                "function OpenReport() {\n" +
                "    if ($('#ReportingSelect').val() == '0000') {\n" +
                "        $('#ReportingSelect').removeClass('ui-input-hofo');\n" +
                "        $('#ReportingSelect').addClass('ui-input-corner-all-error');\n" +
                "        alert('Es wurde kein Kunde ausgesucht!');\n" +
                "    } else if ($('#ReportingInput1').val().length == 0) {\n" +
                "        $('#ReportingInput1').removeClass('ui-input-hofo');\n" +
                "        $('#ReportingInput1').addClass('ui-input-corner-all-error');\n" +
                "        alert('Es wurde kein Startdatum angegeben!');\n" +
                "    } else if ($('#ReportingInput2').val().length == 0) {\n" +
                "        $('#ReportingInput2').removeClass('ui-input-hofo');\n" +
                "        $('#ReportingInput2').addClass('ui-input-corner-all-error');\n" +
                "        alert('Es wurde kein Enddatum angegeben!');\n" +
                "    } else {" +
                "        window.open('/gateway/reporting/ServiceReport?cuid=' + $('#ReportingSelect').val() + '&from=' + base64_encode( $('#ReportingInput1').val() ) + '&to=' + base64_encode( $('#ReportingInput2').val() ) + '','ServiceReport','menubar=0,location=0,top=0,left=0,toolbar=0,personalbar=0,width=843,height=393,resizable=yes');\n" +
                "    }\n" +
                "}\n" +
                "");
            
            out.println("\n" +
                "function GetCron() {\n" +
                "    $.ajax({\n" +
                "        url: '/gateway/reporting/GetCron',\n" +
                "        crossDomain: true,\n" +
                "        success: function(json) {\n" +
                "            $('#CronArea').val(base64_decode(json.COMMENT));\n" +
                "            $('#CronList').html('');\n" +
                "            $.each(json.CLEANED, function() {\n" +
                "                $('#CronList').append('<span>Job <font color=green>\"' + base64_decode(this.FUNCTION) + '\"</font> wird am <font color=green>' + base64_decode(this.INTERVALL) + '</font> das n&auml;chste Mal ausgef&uuml;hrt.</span><br>');\n" +
                "            });\n" +
                "        },\n" +
                "        dataType: 'json',\n" +
                "        cache: false\n" +
                "    });\n" +
                "}\n");    
            
            out.println("\n" +
                "function UpdateCron() {\n" +
                "    var val = base64_encode( $('#CronArea').val() ).replace(/\\+/g,'78');\n" +
                "    $.ajax({\n" +
                "        url: '/gateway/reporting/UpdateConfigReporting?key=' + base64_encode(\"Cron\") + '&val=' + val,\n" +
                "        crossDomain: true,\n" +
                "        success: function(json) {\n" +
                "            if (json == null) {\n" +
                "                DialogMailComplete(\"#Dialog\",\"+++ Fehler beim Speichern der Konfiguration +++\",\"<font color=red>Die Konfiguration konnte NICHT gespeichert werden. Bitte beachten Sie: day-of-month und day-of-week d&uuml;rfen nicht zur gleichen Zeit mit * deklariert werden. Eines muss mind. Eins mit ? angegeben werden.</font>\");\n" +
                "            }\n" +
                "            if (json.ADD == \"1\") {\n" +
                "                DialogMailComplete(\"#Dialog\",\"Konfiguration gespeichert\",\"Die Konfiguration wurde erfolgreich gespeichert.\");\n" +
                "                GetCron();\n" +
                "            } else {\n" +
                "                DialogMailComplete(\"#Dialog\",\"+++ Fehler beim Speichern der Konfiguration +++\",\"<font color=red>Die Konfiguration konnte NICHT gespeichert werden.</font>\");\n" +
                "            }\n" +
                "        },\n" +
                "        error: function(jqXHR, textStatus, errorThrown) {\n" +
                "            DialogMailComplete(\"#Dialog\",\"+++ Fehler beim Speichern der Konfiguration +++\",\"<font color=red>Die Konfiguration konnte NICHT gespeichert werden.</font>\");\n" +
                "        }," +
                "        dataType: 'json',\n" +
                "        cache: false\n" +
                "    });\n" +
                "}\n" +
                "\n");   
            
            out.println("\n" +
                "function ScheduleReport() {\n" +
                "    var cu = base64_encode($('#ReportingSelect').val());\n" +
                "    var fr = base64_encode($('#ReportingInput1').val());\n" +
                "    var to = base64_encode($('#ReportingInput2').val());\n" +
                "    if ($('#ReportingSelect').val() == '0000') {\n" +
                "        $('#ReportingSelect').removeClass('ui-input-hofo');\n" +
                "        $('#ReportingSelect').addClass('ui-input-corner-all-error');\n" +
                "        alert('Es wurde kein Kunde ausgesucht!');\n" +
                "    } else if ($('#ReportingInput1').val().length == 0) {\n" +
                "        $('#ReportingInput1').removeClass('ui-input-hofo');\n" +
                "        $('#ReportingInput1').addClass('ui-input-corner-all-error');\n" +
                "        alert('Es wurde kein Startdatum angegeben!');\n" +
                "    } else if ($('#ReportingInput2').val().length == 0) {\n" +
                "        $('#ReportingInput2').removeClass('ui-input-hofo');\n" +
                "        $('#ReportingInput2').addClass('ui-input-corner-all-error');\n" +
                "        alert('Es wurde kein Enddatum angegeben!');\n" +
                "    } else {" +
                "        $.ajax({\n" +
                "            url: '/gateway/reporting/ScheduleReport?cu=' + cu + '&fr=' + fr + '&to=' + to,\n" +
                "            crossDomain: true,\n" +
                "            success: function(json) {\n" +
                "                if (json.ADD == \"1\") {\n" +
                "                    DialogMailComplete(\"#Dialog\",\"Job Scheduler aktualisiert\",\"Job Scheduler wurde erfolgreich aktualisiert.<br><font color=green>Falls notwendig, passen Sie die Ausf&uuml;hrungszeit noch an.</font>\");\n" +
                "                    GetCron();\n" +
                "                } else {\n" +
                "                    DialogMailComplete(\"#Dialog\",\"+++ Fehler beim aktualisieren des Job Scheduler +++\",\"<font color=red>Der Job Scheduler konnte NICHT aktualisiert werden.</font>\");\n" +
                "                }\n" +
                "            },\n" +
                "            error: function(jqXHR, textStatus, errorThrown) {\n" +
                "                DialogMailComplete(\"#Dialog\",\"+++ Fehler beim aktualisieren des Job Scheduler +++\",\"<font color=red>Der Job Scheduler konnte NICHT aktualisiert werden.</font>\");\n" +
                "            }," +
                "            dataType: 'json',\n" +
                "            cache: false\n" +
                "        });\n" +
                "    }\n" +
                "}\n" +
                "\n");
            
        }
        
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, FileNotFoundException {
        try {
            processRequest(request, response, request.getRemoteUser());
        } catch (NamingException ex) {
            Logger.getLogger(UserBasics.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserBasics.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //
    }
}
