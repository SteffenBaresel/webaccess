/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.siv.systemcontrol;

import de.siv.modules.Executions;
import de.siv.web.Base64Coder;
import de.siv.web.Html;
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
import javax.servlet.http.HttpSession;

/**
 *
 * @author sbaresel
 */
public class Reporting extends HttpServlet {
    Properties props = null;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response, String Uid)
            throws ServletException, IOException {
        try {
            if (Executions.UserExist(Uid)) {
                Executions.UpdateLastLogin(Uid);
                Executions.UpdateUserIsLoggedIn(Uid);
                String b64uid = Base64Coder.encodeString(Uid);
                HttpSession session = request.getSession();
                PrintWriter out = response.getWriter();
                response.setCharacterEncoding("UTF-8");
                response.addHeader("Access-Control-Allow-Origin", "*");
                response.addHeader("Access-Control-Allow-Methods", "*");
                response.setContentType("text/html; charset=utf-8");
                out.println(Html.openHtmlAndHead(null));
                out.println(Html.includeMeta(null));
                out.println("    <title>Reporting - SuS-Reports</title>");
                out.println(Html.includeJs("Reporting"));
                out.println(Html.includeCss("Reporting"));
                out.println(""
                        + "<script>\n"
                        + "$(function() {\n"
                        + "    $('#reporting').addClass('ui-nav-focus');\n"
                        + "    jQuery.support.cors = true;\n"
                        + "    GetUserConfig();\n"
                        + "    $('input').addClass('ui-input-hofo');\n"
                        + "    $('span#ui-tile').addClass('ui-input-hofo');\n");
                
                        if(Executions.UserIsPermitted(Uid,"monitoring")) {
                        
                out.println("    SummaryView();\n");
                                
                        }
                        
                if (Executions.UserMailEmpty(Uid)) {
                            out.println("    alert('Eine Mailadresse für Nutzer \"" + Uid + "\" ist noch nicht hinterlegt. Sie werden automatisch weitergeleitet.');\n"
                                    + "    UserProfile('" + Base64Coder.encodeString(Uid) + "');");
                }
                
                out.println("});\n"
                        + "</script>\n"
                        + "");
                out.println(Html.closeHeadOpenBody(null));
                
                /* Navigation */
                
                out.println(Html.printSectionMenu(Uid,"NavigationOpen"));
                
                out.println(Html.printSectionMenu(Uid,"NavigationClose"));
                
                /* Navigation */
                
                out.println(Html.printSectionMenu(Uid,"Reporting"));
        
                /* Content */
        
                if(Executions.UserIsPermitted(Uid,"reporting")) {
                    out.println("<script type='text/javascript'>$(function() { GetReportingCustomer(); });</script>");
                    out.println("<div id='ReportingDiv'>");
                    out.println("    <div id='ReportingText'><span style='float: left; margin-top: 0px;'>Servicereport erstellen</span><span class='ui-icon ui-icon-triangle-1-s' style='float: left; margin-top: 0px;'></span></div><br>");
                    out.println("    <select id='ReportingSelect' class='ui-input-hofo'><option value='0000' selected>Bitte Kunden w&auml;hlen!</option></select><br>");
                    out.println("    <input id='ReportingInput1' class='ui-input-hofo' type='text' placeholder='Von ...' /><br>");
                    out.println("    <input id='ReportingInput2' class='ui-input-hofo' type='text' placeholder='Bis ...' /><br>");
                    out.println("    <button id='ReportingButton1' class='ui-input-hofo' onclick='OpenReport();'>Bericht erstellen ...</button>");
                    out.println("    <button id='ReportingButton2' class='ui-input-hofo' onclick='ScheduleReport();'>Bericht einplanen ...</button>");
                    out.println("</div>");
                    
                    out.println("<script type='text/javascript'>$(function() { GetCron(); });</script>");
                    out.println("<div id='CronDiv'>");
                    out.println("    <div id='ReportingText'><span style='float: left; margin-top: 0px;'>Job Scheduler</span><span class='ui-icon ui-icon-triangle-1-s' style='float: left; margin-top: 0px;'></span></div><br>");
                    out.println("    <textarea id='CronArea' class='ui-input-hofo'></textarea><br>");
                    out.println("    <button id='CronButton1' class='ui-input-hofo' onclick='UpdateCron();'>Konfiguration speichern ...</button>");
                    out.println("</div>");
                    out.println("    <div id='CronList'></div>");
                }
                
                /* Footer */
                
                out.println(Html.printSectionBottom(Uid));
                out.println(Html.printSidebar(null,Uid));
                out.println(Html.printBottombar(null,Uid));
                out.println(Html.closeBodyCloseHtml(null));
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ManagedServices.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(ManagedServices.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ManagedServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response, request.getRemoteUser());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response, request.getRemoteUser());
    }
}
