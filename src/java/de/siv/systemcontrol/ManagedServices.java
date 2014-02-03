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
            if (Functions.UserExist(Uid)) {
                Functions.UpdateLastLogin(Uid);
                Functions.UpdateUserIsLoggedIn(Uid);
                String b64uid = Base64Coder.encodeString(Uid);
                HttpSession session = request.getSession();
                PrintWriter out = response.getWriter();
                response.setCharacterEncoding("UTF-8");
                response.addHeader("Access-Control-Allow-Origin", "*");
                response.addHeader("Access-Control-Allow-Methods", "*");
                response.setContentType("text/html; charset=utf-8");
                out.println(Html.openHtmlAndHead(null));
                out.println(Html.includeMeta(null));
                out.println("    <title>Managed Services - kVASy&reg; System Control</title>");
                out.println(Html.includeJs("ManagedService"));
                out.println(Html.includeCss("ManagedService"));
                out.println("             <script>\n" +
        "                $(function() {\n" +
        "                    jQuery.support.cors = true;\n" +
        "                    Top('" + Uid + "');" +
        "                    GetUserConfig();\n" +
        "                    StyleManagedServices();\n" +
        "                    MenuSidebar();\n" +
        "                    GetServiceEntry('0','75','1');\n" +
        "                    KlickFunctionMenuSidebar();\n" +
        "                    SummaryView('" + Uid + "');\n" +
        "                    Liveticker('" + Uid + "');\n" +
        "                    SlimTaov('" + Uid + "');\n" +
        "                    ShowAllComments('" + Uid + "');\n" +
        "                    ModShowCritical('" + Uid + "');\n" +
        "                });\n" +
        "            </script>\n");
                out.println(Html.printTopMenu("mod",Uid));
                
                out.println("" +
                        "                    <div id=\"StatusSummary\">\n" +
                        "                        <div id=\"HostStatusSummaryHead\"><span style=\"float: left;\">Hosts</span><span style=\"float: left; margin-top: -1px;\" class=\"ui-icon ui-icon-triangle-1-s\"></span></div>\n" +
                        "                        <div id=\"HostStatusSummary\"><div id=\"HostStatusSummaryContent\"></div></div>\n" +
                        "                        <div id=\"ServiceStatusSummaryHead\"><span style=\"float: left;\">Services</span><span style=\"float: left; margin-top: -1px;\" class=\"ui-icon ui-icon-triangle-1-s\"></span></div>\n" +
                        "                        <div id=\"ServiceStatusSummary\"><div id=\"ServiceStatusSummaryContent\"></div></div>\n" +
                        "                    </div>" +
                        "");
                
                if(Functions.UserIsPermitted(Uid,"managed_services")) {
                    
                    out.println("<div id='MenuSidebarSmall'></div><div id='MenuSidebar'><div id='MenuSidebarContent'>\n");
                    
                
                    out.println("        <div id='MenuSidebarManagedServiceMenus'><div id='MS_CustActions'>\n");
                
                    out.println("<div class='UserDesc'>Servicearbeiten</div>\n");
                
                    out.println("<div id='MS_srv'>");
                
                    if(Functions.UserIsPermitted(Uid,"managed_services_csw")) {
                        out.println("<a href='#' class='icon' onclick=\"CreateServiceEntry()\" title='Servicearbeiten eintragen'><img src='layout/images/white/add.png' alt='Konfiguraion' width='50' height='50'></a>");
                    }
                
                    out.println("</div>");
                    
                    out.println("<div class='UserDesc'>Kundeninfo</div>");
                
                    out.println("<div id='MS_cc'>");
                
                    if(Functions.UserIsPermitted(Uid,"managed_services_nka")) {
                        out.println("<a href='#' class='icon' onclick=\"CreateCustomer();\" title='Neuen Kunden anlegen'><img src='layout/images/white/add.png' alt='Konfiguraion' width='50' height='50'></a>");
                    }
                    
                    if(Functions.UserIsPermitted(Uid,"managed_services_kb")) {
                        out.println("<a href='#' class='icon' onclick=\"EditCustomer();\" title='Kunden bearbeiten'><img src='layout/images/white/edit.png' alt='Konfiguraion' width='50' height='50'></a>");
                    } 
                    
                    if(Functions.UserIsPermitted(Uid,"managed_services_kl")) {
                        out.println("<a href='#' class='icon' onclick=\"DeleteCustomer();\" title='Kunden l&ouml;schen'><img src='layout/images/white/delete.png' alt='Konfiguraion' width='50' height='50'></a>");
                    } 
                    
                    out.println("</div>");
                    
                    out.println("<div class='UserDesc'>Vertragstypen</div>");
                
                    out.println("<div id='MS_vt'>");
                
                    if(Functions.UserIsPermitted(Uid,"managed_services_vae")) {
                        out.println("<a href='#' class='icon' onclick=\"CreateContractType();\" title='Neuer Vertragstyp'><img src='layout/images/white/add.png' alt='Konfiguraion' width='50' height='50'></a>");
                    }
                    
                    if(Functions.UserIsPermitted(Uid,"managed_services_vab")) {
                        out.println("<a href='#' class='icon' onclick=\"EditContractType();\" title='Vertragstyp bearbeiten'><img src='layout/images/white/edit.png' alt='Konfiguraion' width='50' height='50'></a>");
                    } 
                    
                    if(Functions.UserIsPermitted(Uid,"managed_services_val")) {
                        out.println("<a href='#' class='icon' onclick=\"DeleteContractType();\" title='Vertragstyp l&ouml;schen'><img src='layout/images/white/delete.png' alt='Konfiguraion' width='50' height='50'></a>");
                    } 
                
                    out.println("</div>");
                    
                    out.println("        </div><div id='MSDialog'></div><div id='MSDialogSuccess'></div>");
                    
                    out.println("</div></div></div>");
                    
                    out.println("        <div id='ManagedServiceMenus'>");
                    
                    if(Functions.UserIsPermitted(Uid,"managed_services_wili")) {
                        out.println("<script type='text/javascript'>$(function() { WhoIsLoggedIn(); });</script>");
                        out.println("<div id='WhosLoggedInH'><span id='WLI_Head'>Wer ist noch angemeldet?</span></div>");
                        out.println("<div id='WhosLoggedIn'><span id='WLI_Cont'></span></div>");
                    }
                    
                    out.println("        </div>");
                    
                    if(Functions.UserIsPermitted(Uid,"managed_services_csr")) {
                        out.println("<div id='ManagedServiceActionsPage'></div><div id='ManagedServiceActions'></div>");
                    }
                    
                    if(Functions.UserIsPermitted(Uid,"managed_services_ki")) {
                        out.println("<script type='text/javascript'>$(function() { CustomerInfo(); });</script>");
                        out.println("<div id='ManagedServiceInfosH'><span id='MSI_Head'>Kundeninformationen</span></div>");
                        out.println("<div id='ManagedServiceInfos'><div id='MSI_Cont'></div></div><div id='MSIDialog'></div>");
                    }
                 
                }
            
                if(Functions.UserIsPermitted(Uid,"sidebar")) {
                    out.println(Html.printSidebar(null));
                }
                    
                if(Functions.UserIsPermitted(Uid,"bottombar")) {
                    out.println(Html.printBottombar(null));
                }
            
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
