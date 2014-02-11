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
                        + "    jQuery.support.cors = true;\n"
                        + "    GetUserConfig();\n"
                        + "    $('div#menu-locator').html('Startseite');\n"
                        + "    $('input').addClass('ui-input-hofo');\n"
                        + "    $('span#ui-tile').addClass('ui-input-hofo');\n"
                        + "    SummaryView();\n"
                        + "    SidebarBottom();\n"
                        + "");
                        
                if (Executions.UserMailEmpty(Uid)) {
                            out.println("    alert('Eine Mailadresse für Nutzer \"" + Uid + "\" ist noch nicht hinterlegt. Sie werden automatisch weitergeleitet.');\n"
                                    + "    UserProfile('" + Base64Coder.encodeString(Uid) + "');");
                }
                
                out.println("});\n"
                        + "</script>\n"
                        + "");
                out.println(Html.closeHeadOpenBody(null));
                out.println(Html.printSectionMenu(Uid,"Index"));
        
                if(Executions.UserIsPermitted(Uid,"config")) {
                
                    out.println(""
                            + "    <span id='ui-tile' class='ui-tile-130px' onclick=\"Configuration('" + Base64Coder.encodeString(Uid) + "')\" title='Konfiguration'>\n"
                            + "        <img class='ui-tile-cimg' src='public/images/configure-128.png' alt='Konfiguraion' width='96' height='96'>\n"
                            + "    </span>\n");
                
                }
                
                if(Executions.UserIsPermitted(Uid,"host")) {
                
                    out.println(""
                            + "    <span id='ui-tile' class='ui-tile-300px' title='Hosts' onclick=\"\">\n"
                            + "        <img class='ui-tile-cimg' src='public/images/server-128.png' alt='Hosts' width='96' height='96'>\n"
                            + "        <span class='ui-tile-header'>Hosts</span><br></br>\n"
                            + "        <span class='ui-tile-content'>Eine &Uuml;bersicht &uuml;ber alle eingerichteten Server.</span>\n"
                            + "    </span>\n");
                
                }                        
                       
                if(Executions.UserIsPermitted(Uid,"service")) {
                
                    out.println(""
                            + "    <span id='ui-tile' class='ui-tile-300px' title='Services' onclick=\"\">\n"
                            + "        <img class='ui-tile-cimg' src='public/images/list-128.png' alt='Services' width='96' height='96'>\n"
                            + "        <span class='ui-tile-header'>Services</span><br></br>\n"
                            + "        <span class='ui-tile-content'>Eine &Uuml;bersicht &uuml;ber alle eingerichteten Services.</span>\n"
                            + "    </span>\n");
                
                }
                        
                if(Executions.UserIsPermitted(Uid,"managed_services")) {
                
                    out.println(""
                            + "    <span id='ui-tile' class='ui-tile-300px' title='Managed Services' onclick=\"OpenWindow('ManagedServices','_self');\">\n"
                            + "        <img class='ui-tile-cimg' src='public/images/group-128.png' alt='Managed Services' width='96' height='96'>\n"
                            + "        <span class='ui-tile-header'>Managed Services</span><br></br>\n"
                            + "        <span class='ui-tile-content'>Servicearbeiten, Kundeninformationen und Vertragstypen.</span>\n"
                            + "    </span>\n");
                
                }
                
                out.println("<div id='DashboardLinks'></div>");
                        
                if(Executions.UserIsPermitted(Uid,"addlink")) {
                
                    out.println(""
                            + "    <span id='ui-tile-woc' class='ui-tile-130px' onclick='AddLink();'>\n"
                            + "        <img src='public/images/AddNext.png' alt='AddNext' title='F&uuml;ge weiteren Men&uuml;punkt hinzu!'>\n"
                            + "    </span>\n");
                
                }
                
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

