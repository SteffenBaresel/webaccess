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
public class Monitoring extends HttpServlet {
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
                out.println("    <title>Monitoring - kVASy&reg; System Control</title>");
                out.println(Html.includeJs("Monitoring"));
                out.println(Html.includeCss("Monitoring"));
                out.println(""
                        + "<script>\n"
                        + "$(function() {\n"
                        + "    jQuery.support.cors = true;\n"
                        + "    $('#big-taov').hide();\n"
                        + "    GetUserConfig();\n"
                        + "    $('div#menu-locator').html('<div class=\"ui-user-menu\"><span style=\"float: right\" >Monitoring</span><span style=\"float: right; margin-top: 2px;\" class=\"ui-icon ui-icon-triangle-1-e\"></span><span style=\"float: right; cursor: pointer;\" onclick=\"OpenWindow(\\'/webaccess/\\',\\'_self\\');\">Home</span></div>');\n"
                        + "    $('input').addClass('ui-input-hofo');\n"
                        + "    $('span#ui-tile').addClass('ui-input-hofo');\n"
                        + "    SummaryView();\n"
                        + "    //SidebarBottom();\n"
                        + "");
                        
                if (Executions.UserMailEmpty(Uid)) {
                            out.println("    alert('Eine Mailadresse für Nutzer \"" + Uid + "\" ist noch nicht hinterlegt. Sie werden automatisch weitergeleitet.');\n"
                                    + "    UserProfile('" + Base64Coder.encodeString(Uid) + "');");
                }
                
                out.println("});\n"
                        + "</script>\n"
                        + "");
                out.println(Html.closeHeadOpenBody(null));
                out.println(Html.printSectionMenu(Uid,"Monitoring"));
        
                if(Executions.UserIsPermitted(Uid,"monitoring")) {
                
                    out.println("<script type='text/javascript'>$(function() { ShowMonitoringFull(); });</script>");
                    out.println("<div id='div-monitoring'></div>");
                 
                }
                
                out.println(Html.printSectionBottom(null));
                out.println(Html.printSidebar(null));
                out.println(Html.printBottombar(null));
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
