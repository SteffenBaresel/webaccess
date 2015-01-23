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
                "    $('#ReportingInput1').datetimepicker();\n" +
                "    $('#ReportingInput2').datetimepicker();\n" +
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
