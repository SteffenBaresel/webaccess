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
