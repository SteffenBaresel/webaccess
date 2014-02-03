/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.siv.systemcontrol;

import de.siv.modules.Functions;
import de.siv.web.*;
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
public class Index_old extends HttpServlet {
    Properties props = null;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response, String Uid)
            throws ServletException, IOException {
        try {
            if (Functions.UserExist(Uid)) {
                Functions.UpdateLastLogin(Uid);
                Functions.UpdateUserIsLoggedIn(Uid);
                PrintWriter out = response.getWriter();
                response.setCharacterEncoding("UTF-8");
                response.addHeader("Access-Control-Allow-Origin", "*");
                response.addHeader("Access-Control-Allow-Methods", "*");
                response.setContentType("text/html; charset=utf-8");
                out.println(Html.openHtmlAndHead(null));
                out.println(Html.includeMeta(null));
                out.println(" <title>Startseite - kVASy&reg; System Control</title>");
                out.println(Html.includeJs("Index"));
                out.println(Html.includeCss("Index"));
                out.println("             <script>\n" +
    "                $(function() {\n" +
    "                    jQuery.support.cors = true;\n" +
    "                    GetUserConfig();\n");
                        
                        if (Functions.UserMailEmpty(Uid)) {
                            out.println("\n" +
    "                    alert('Eine Mailadresse für Nutzer \"" + Uid + "\" ist noch nicht hinterlegt. Sie werden automatisch weitergeleitet.');\n" + 
    "                    UserProfile('" + Base64Coder.encodeString(Uid) + "');");
                        }
                        
                out.println("\n" +
    "                });\n" +
    "            </script>\n");
                out.println(Html.closeHeadOpenBody(null));
                out.println(Html.printTopMenu(null,null));
                out.println("        <div id='center'>\n" +
    "            <section>\n");
                
                        if(Functions.UserIsPermitted(Uid,"config")) {
                
                out.println("" +
    "                <a href='#' class='icon' onclick=\"Configuration('" + Base64Coder.encodeString(Uid) + "')\" title='Konfiguration'>\n" +
    "                    <img src='layout/images/gear_icon.png' alt='Konfiguraion' width='148' height='148'>\n" +
    "                </a>\n");
                
                        }

                        if(Functions.UserIsPermitted(Uid,"profile")) {
                        
                out.println("" +
    "                <a href='#' class='icon' onclick=\"UserProfile('" + Base64Coder.encodeString(Uid) + "')\" title='Profileinstellungen bearbeiten'>\n" +
    "                    <img id='UserProfileConfig' src='layout/images/DefaultProfile.png' alt='Profil Bearbeiten' width='148' height='148'>\n" +
    "                </a>\n");
                        
                        }
                
                        if(Functions.UserIsPermitted(Uid,"host")) {
                
                out.println("" +
    "                <a href='hosts/' class='twitter'>\n" +
    "                    <span>Hosts</span><br></br>\n" +
    "                    <span class='sub-grid'>Eine &Uuml;bersicht &uuml;ber alle eingerichteten Server.</span>\n" +
    "                </a>\n");
                
                        }                        
                        
                        if(Functions.UserIsPermitted(Uid,"service")) {
                
                out.println("" +
    "                <a href='services/' class='twitter'>\n" +
    "                    <span>Services</span><br></br>\n" +
    "                    <span class='sub-grid'>Eine &Uuml;bersicht &uuml;ber alle eingerichteten Services.</span>\n" +
    "                </a>\n");
                
                        }
                        
                        if(Functions.UserIsPermitted(Uid,"managed_services")) {
                
                out.println("" +
    "                <a href='ManagedServices' class='twitter'>\n" +
    "                    <span>Managed Services</span><br></br>\n" +
    "                    <span class='sub-grid'>Servicearbeiten, Kundeninformationen und Vertragstypen.</span>\n" +
    "                </a>\n");
                
                        }
                
                out.println("<div id='DashboardLinks'></div>");
                        
                        if(Functions.UserIsPermitted(Uid,"addlink")) {
                
                out.println("" +
    "                <a href='#' class='AddNext' onclick='AddLink();'>\n" +
    "                    <img src='layout/images/white/add.png' alt='AddNext' title='F&uuml;ge weiteren Men&uuml;punkt hinzu!' width='50' height='50'>\n" +
    "                </a>\n");
                
                        }
                        
                out.println("" +
    "			</section>\n" +
    "		 </div>\n" +
    "                <div id='Configuration'></div>\n" +
    "                <div id='UserProfile'></div><div id='UserProfileSuccess'></div>\n" +
    "                <div id='AddLink'></div>\n");
                //out.println("<div onclick='MailTest();'>Mail Test</div><div id='MailForm'></div><div id='MailSendSuccess'></div>");
                
                if(Functions.UserIsPermitted(Uid,"sidebar")) {
                    out.println(Html.printSidebar(null));
                }
                
                if(Functions.UserIsPermitted(Uid,"bottombar")) {
                    out.println(Html.printBottombar(null));
                }
                
                out.println(Html.closeBodyCloseHtml(null));
            } else {
                Logger.getLogger(Index_old.class.getName()).log(Level.SEVERE, null, "");
                response.sendRedirect("loginError?e=2");
            }    
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Index_old.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(Index_old.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Index_old.class.getName()).log(Level.SEVERE, null, ex);
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
        //
    }
}
