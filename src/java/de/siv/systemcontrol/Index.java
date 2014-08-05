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

/**
 *
 * @author sbaresel
 */
public class Index extends HttpServlet {
    Properties props = null;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response, String Uid)
            throws ServletException, IOException {
        try {
            if (Executions.UserExist(Uid)) {
                Executions.UpdateLastLogin(Uid);
                Executions.UpdateUserIsLoggedIn(Uid);
                PrintWriter out = response.getWriter();
                response.setCharacterEncoding("UTF-8");
                response.addHeader("Access-Control-Allow-Origin", "*");
                response.addHeader("Access-Control-Allow-Methods", "*");
                response.setContentType("text/html; charset=utf-8");
                out.println(Html.openHtmlAndHead(null));
                out.println(Html.includeMeta(null));
                out.println("    <title>Startseite - kVASy&reg; System Control</title>");
                out.println(Html.includeJs("Index"));
                out.println(Html.includeCss("Index"));
                out.println(""
                        + "<script>\n"
                        + "$(function() {\n"
                        + "    $('#dashboard').addClass('ui-nav-focus');\n"
                        + "    jQuery.support.cors = true;\n"
                        + "    GetUserConfig();\n"
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
                
                /* Navigation */
                
                out.println(Html.printSectionMenu(Uid,"NavigationOpen"));
                
                out.println(Html.printSectionMenu(Uid,"NavigationClose"));
                
                /* Navigation */
                
                out.println(Html.printSectionMenu(Uid,"Index"));
        
        /*        out.println("<div id='DashboardLinks'></div>");
                        
                if(Executions.UserIsPermitted(Uid,"addlink")) {
                
                    out.println(""
                            + "    <span id='ui-tile-woc' class='ui-tile-130px' onclick='AddLink();'>\n"
                            + "        <img src='public/images/AddNext.png' alt='AddNext' title='F&uuml;ge weiteren Men&uuml;punkt hinzu!'>\n"
                            + "    </span>\n");
                
                }*/
                
                out.println("            <div id='service-state'>\n"
                    + "    <div id='HeaderServicePie'><span style=\"float: left;\">Service Status &Uuml;bersicht</span><span style=\"float: left; margin-top: -1px;\" class=\"ui-icon ui-icon-triangle-1-s\"></span></div>\n"
                    + "                <table id='TPie' cellpadding='0' cellspacing='0' border='0'>\n"
                    + "                    <tr>\n"
                    + "                        <td>\n"
                    + "                            <div id='ServicePie'></div>\n"
                    + "                        </td>\n"
                    + "                        <td>\n"
                    + "                            <div id='ServicePer'></div>\n"
                    + "                        </td>\n"
                    + "                    </tr>\n"
                    + "                </table>\n"
                    + "                <br>\n"
                    + "            </div>\n");
                
                out.println("            <div id='current-problems'>\n"
                    + "    <div id='current-problems-header'><span style=\"float: left;\">Offene Probleme</span><span style=\"float: left; margin-top: -1px;\" class=\"ui-icon ui-icon-triangle-1-s\"></span></div>\n"
                    + "    <div id='current-problems-content'></div>\n"
                    + "            </div>\n");
                
                out.println("            <div id='last-comments'>\n"
                    + "    <div id='last-comments-header'><span style=\"float: left;\">Letzte Kommentare</span><span style=\"float: left; margin-top: -1px;\" class=\"ui-icon ui-icon-triangle-1-s\"></span></div>\n"
                    + "    <div id='last-comments-content'></div>\n"
                    + "            </div>\n");
                
                out.println(Html.printSectionBottom(null));
                out.println(Html.printSidebar(null));
                out.println(Html.printBottombar(null));
                out.println(Html.closeBodyCloseHtml(null));
            } else {
                Logger.getLogger(Index.class.getName()).log(Level.SEVERE, null, "");
                response.sendRedirect("loginError?e=2");
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Index.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(Index.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Index.class.getName()).log(Level.SEVERE, null, ex);
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

