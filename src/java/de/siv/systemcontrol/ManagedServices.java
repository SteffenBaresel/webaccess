/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.siv.systemcontrol;

import de.siv.modules.Executions;
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
import javax.servlet.http.HttpSession;

/**
 *
 * @author sbaresel
 */
public class ManagedServices extends HttpServlet {
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
                out.println("    <title>Managed Services - SuS-Reports</title>");
                out.println(Html.includeJs("ManagedServices"));
                out.println(Html.includeCss("ManagedServices"));
                out.println(""
                        + "<script>\n"
                        + "$(function() {\n"
                        + "    $('#managedservices').addClass('ui-nav-focus');\n"
                        + "    jQuery.support.cors = true;\n"
                        + "    GetUserConfig();\n"
                        + "    $('input').addClass('ui-input-hofo');\n"
                        + "    $('span#ui-tile').addClass('ui-input-hofo');\n");
                        
                        if(Executions.UserIsPermitted(Uid,"monitoring")) {
                        
                out.println("    SummaryView();\n");
                                
                        }
                        
                out.println("    StyleManagedServices();\n"
                        + "    SelectCustomer();\n"
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
                
                out.println(Html.printSectionMenu(Uid,"ManagedService"));
        
                if(Executions.UserIsPermitted(Uid,"managed_services")) {
                
                    String val = request.getParameter("v");
                    
                    //out.println("        <div id='ManagedServiceMenus'>");
                    
                    /*if(Executions.UserIsPermitted(Uid,"managed_services_wili")) {
                        out.println("<script type='text/javascript'>$(function() { WhoIsLoggedIn(); });</script>");
                        //out.println("<div id='WhosLoggedInH'><span style=\"float: left;\" id='WLI_Head'>Wer ist noch angemeldet</span><span style=\"float: left; margin-top: 0px;\" class=\"ui-icon ui-icon-triangle-1-s\"></span></div>");
                        out.println("<div id='WhosLoggedIn'><span id='WLI_Cont'></span></div>");
                    }*/
                    
                    //out.println("        </div>");
                    
                    if(Executions.UserIsPermitted(Uid,"managed_services_csr")) {
                    
                        if(val != null) {
                    
                            if( val.equals(Base64Coder.encodeString("SearchCustomer")) ) {
                                out.println("<script type='text/javascript'>$(function() { GetSearchServiceEntry('0','75','1'); });</script>");
                            }
                                
                        } else {
                        
                            out.println("<script type='text/javascript'>$(function() { GetServiceEntry('0','75','1'); });</script>");
                            //out.println("<div id='ManagedServiceActionsSelectDiv'><select id='ManagedServiceActionsSelect' onchange=\"GetCustomerServiceEntries('ManagedServiceActionsSelect','0','75','1');CustomerInfoServiceEntries('ManagedServiceActionsSelect');\"><option value='0000' selected>Kunden w&auml;hlen</option></select></div>");
                        
                        }
                        
                        if(Executions.UserIsPermitted(Uid,"monitoring")) {
                        
                            out.println("<div id='ManagedServiceActionsPage'></div><div id='ManagedServiceActions'></div>");
                        
                        } else {
                            
                            out.println("<div style='positon: absolute; top: 0' id='ManagedServiceActionsPage'></div><div style='positon: absolute; top: 50px; bottom: -50px;' id='ManagedServiceActions'></div>");
                            
                        }
                        
                    }
                    
                    if(Executions.UserIsPermitted(Uid,"monitoring")) {
                    
                        out.println("        <div id='ManagedServiceInfo'>");
                    
                    } else {
                        
                        out.println("        <div style='positon: absolute; top: 15px; bottom: -50px;' id='ManagedServiceInfo'>");
                        
                    }
                    
                    if(Executions.UserIsPermitted(Uid,"managed_services_ki")) {
                        
                        if(val != null) {
                    
                            if( val.equals(Base64Coder.encodeString("SearchCustomer")) ) {
                                out.println("<script type='text/javascript'>$(function() { SearchCustomerInfo(); });</script>");
                            }
                                
                        } else {
                        
                            out.println("<script type='text/javascript'>$(function() { CustomerInfo(); });</script>");
                            //out.println("<div id='ManagedServiceInfosH'><span style=\"float: left;\" id='MSI_Head'>Kundeninformationen</span><span style=\"float: left; margin-top: 0px;\" class=\"ui-icon ui-icon-triangle-1-s\"></span></div>");
                        
                        }
                        
                        out.println("<div id='ManagedServiceInfos'><div id='MSI_Cont'></div></div><div id='MSIDialog'></div>");
                        
                    }
                    
                    out.println("        </div>");
                 
                }
                
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
