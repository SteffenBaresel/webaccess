/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.siv.web;

import de.siv.modules.Executions;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import javax.naming.NamingException;

/**
 *
 * @author sbaresel
 */
public class Html {
    
    static public String openHtmlAndHead(String mod) {
        String replace ="<!DOCTYPE html>\n<html>\n    <head>\n";
        return replace;
    }
    
    static public String closeHeadOpenBody(String mod) {
        String replace ="    </head>\n    <body><div id=\"ScreenAlertBG\"></div><div id=\"ScreenAlertText\">Die Bildschirm Aufl&ouml;sung ist zu klein. Bitte sorgen Sie daf&uuml;r, dass mind. 1280px*1024px eingestellt sind.</div>\n";
        return replace;
    }
    
    static public String closeBodyCloseHtml(String mod) {
        String replace ="    </body>\n</html>";
        return replace;
    }
    
    static public String includeMeta(String mod) {
        String replace ="    <link rel='shortcut icon' href='public/images/favicon.ico' type='image/vnd.microsoft.icon' />\n";
        replace+="    <meta name='author' content='Steffen Baresel'>\n";
	replace+="    <meta name='description' content='kVASy(R) System Control'>\n";
	replace+="    <meta name='keywords' content='kVASy, System Control, kVASy System Control'>\n";
	replace+="    <meta name='language' content='it'>\n";
	replace+="    <meta name='charset' content='utf-8'>\n";
        replace+="    <meta name='viewport' content='width=device-width,initial-scale=1.0'>\n";
        return replace;
    }
    
    static public String includeJs(String mod) {
        String replace = "    <script type='text/javascript' src='public/script/jquery-1.8.2.min.js'></script>\n";
        replace+="    <script type='text/javascript' src='public/script/jquery-ui-1.10.4.custom.min.js'></script>\n";
        replace+="    <script type='text/javascript' src='public/script/jquery.shortcuts.min.js'></script>\n";
        replace+="    <script type='text/javascript' src='public/script/jquery.cookie.js'></script>\n";
        replace+="    <script type='text/javascript' src='public/script/timepicker.js'></script>\n";
        replace+="    <script type='text/javascript' src='public/script/basics.js'></script>\n";
        
        if(!mod.equals("Login")) {
        
            replace+="    <script type='text/javascript' src='script/UserBasics.js'></script>\n";
            replace+="    <script type='text/javascript' src='script/ManagedServiceBasics.js'></script>\n";
            replace+="    <script type='text/javascript' src='script/MonitoringBasics.js'></script>\n";
            replace+="    <script type='text/javascript' src='script/UserProfile.js'></script>\n";
            replace+="    <script type='text/javascript' src='script/Reporting.js'></script>\n";
            replace+="    <script type='text/javascript' src='public/script/jquery-te-1.4.0.min.js'></script>\n";
            replace+="    <script type='text/javascript' src='script/TacticalOverview.js'></script>\n";
            replace+="    <script type='text/javascript' src='public/script/charting.js'></script>\n";
            replace+="    <script type='text/javascript' src='public/script/charts.js'></script>\n";
            replace+="    <script type='text/javascript' src='public/script/jquery.simplePagination.js'></script>\n";
            replace+="    <script type='text/javascript' src='public/script/caret.js'></script>\n";
        
        }
        
        return replace;
    }
    
    static public String includeCss(String mod) {
        String replace = "    <link rel='stylesheet' href='public/style/jquery-ui-1.10.4.custom.css' />\n";
        replace+="    <link rel='stylesheet' href='public/style/systemcontrol.css' />\n";
        replace+="    <link rel='stylesheet' href='public/style/jquery-te-1.4.0.css' />\n";
              
        return replace;
    }
    
    static public String printSectionMenu(String uid,String mod) throws FileNotFoundException, IOException, NamingException, SQLException {
        String replace = "<div id=\"search-tool-tip\"><div id=\"search-tool-tip-header\"><span>Wonach wollen Sie suchen?</span><span title='Tooltip Schlie&szlig;en' onclick=\"closeTooltip('search-tool-tip');\" style='cursor: pointer; margin-top: 1px;' class='ui-icon ui-icon-close'></span></div><div id=\"search-tool-tip-content\"><table>";
        if(Executions.UserIsPermitted(uid,"monitoring")) {
            replace+= "<tr><td><input type=\"radio\" name=\"search\" value=\"Hosts\" onclick=\"setValue('search-field','Host: ');\">Hosts</td><td><input type=\"radio\" name=\"search\" value=\"Services\" onclick=\"setValue('search-field','Service: ');\">Services</td></tr>";
        }
        if(Executions.UserIsPermitted(uid,"managed_services")) {
            replace+= "<tr><td><input type=\"radio\" name=\"search\" value=\"Kunden\" onclick=\"setValue('search-field','Kunde: ');\">Kunden</td><td><!--input type=\"radio\" name=\"search\" value=\"Vertrag\" onclick=\"setValue('search-field','Vertrag: ');\">Vertrag--></td></tr><tr><!--td><input type=\"radio\" name=\"search\" value=\"LDAP\" onclick=\"setValue('search-field','LDAP: ');\">LDAP</td><td><input type=\"radio\" name=\"search\" value=\"Vertrag\" onclick=\"setValue('search-field','Vertrag: ');\">Vertrag--></td></tr>";
        }
        
        replace+= "</table></div></div>";
        
            if(mod.equals("NavigationOpen")) {
                replace += "<section id='left-pane'><section id='navigation-top'><br>";
                
                if(Executions.UserIsPermitted(uid,"sidebarsearch")) {
                    //replace += "<form method='GET' id='search-form'><input type='hidden' name='v'/><input type='text' name='search-field' id='search-field' onkeypress='submitOnEnter(this, event);' onclick='showTooltip();' autofocus='autofocus' autocomplete='off' placeholder='Suchen' /></form>";
                    replace += "<form method='GET' id='search-form'><input type='hidden' name='v'/><input type='text' name='search-field' id='search-field' onkeypress='submitOnEnter(this, event);' autofocus='autofocus' autocomplete='off' placeholder='Suchen' /></form>";
                }
                    
                if(Executions.UserIsPermitted(uid,"monitoring")) {
                    replace += "    <span id='dashboard' class='ui-nav-tile ui-nav-normal ui-nav-hover' onclick=\"OpenWindow('/webaccess/','_self');\"><div><img src='public/images/discoverer-128.png' />Dashboard</div></span>\n";
                    replace += "    <span id='monitoring' class='ui-nav-tile ui-nav-normal ui-nav-hover' onclick=\"OpenWindow('Monitoring?v=RnVsbFZpZXc=','_self');\"><div><img src='public/images/server-128.png' />Monitoring</div></span>\n";
                }                        
                
                if(Executions.UserIsPermitted(uid,"managed_services")) {
                    replace += "    <span id='managedservices' class='ui-nav-tile ui-nav-normal ui-nav-hover' onclick=\"OpenWindow('ManagedServices','_self');\"><div><img src='public/images/group-128.png' />Managed Services</div></span>\n";
                } 
                
                if(Executions.UserIsPermitted(uid,"reporting")) {
                    replace += "    <span id='reporting' class='ui-nav-tile ui-nav-normal ui-nav-hover' onclick=\"OpenWindow('Reporting','_self');\"><div><img src='public/images/output-128.png' />Reporting</div></span>\n";
                }
                
                replace += "<br>";
                
                if(Executions.UserIsPermitted(uid,"managed_services")) {
                    
                    if(Executions.UserIsPermitted(uid,"managed_services_csw")) {
                        replace += "    <span id='managedservicescsw' class='ui-nav-tile ui-nav-normal ui-nav-hover' onclick=\"CreateServiceEntry();\"><div><img src='public/images/add.png' />Servicearbeit eintragen</div></span>\n";
                    }
                
                    replace += "<br>";
                    
                    if(Executions.UserIsPermitted(uid,"managed_services_nka")) {
                        replace += "    <span id='managedservicesnka' class='ui-nav-tile ui-nav-normal ui-nav-hover' onclick=\"CreateCustomer();\"><div><img src='public/images/add.png' />Kunde hinzuf&uuml;gen</div></span>\n";
                        
                        if(Executions.UserIsPermitted(uid,"managed_services_kb")) {
                            replace += "    <span id='managedserviceskb' class='ui-nav-tile ui-nav-normal ui-nav-hover' onclick=\"EditCustomer();\"><div><img src='public/images/edit.png' />Kunde bearbeiten</div></span>\n";
                        } 
                    
                        if(Executions.UserIsPermitted(uid,"managed_services_kl")) {
                            replace += "    <span id='managedserviceskl' class='ui-nav-tile ui-nav-normal ui-nav-hover' onclick=\"DeleteCustomer();\"><div><img src='public/images/delete.png' />Kunde l&ouml;schen</div></span>\n";
                        } 
                   
                    }
                    
                    replace += "<br>";
                    
                    if(Executions.UserIsPermitted(uid,"managed_services_vae")) {
                        
                        replace += "    <span id='managedservicesvae' class='ui-nav-tile ui-nav-normal ui-nav-hover' onclick=\"CreateContractType();\"><div><img src='public/images/add.png' />Vertragstyp hinzuf&uuml;gen</div></span>\n";
                        
                        if(Executions.UserIsPermitted(uid,"managed_services_vab")) {
                            replace += "    <span id='managedservicesvab' class='ui-nav-tile ui-nav-normal ui-nav-hover' onclick=\"EditContractType();\"><div><img src='public/images/edit.png' />Vertragstyp bearbeiten</div></span>\n";
                        } 
                    
                        if(Executions.UserIsPermitted(uid,"managed_services_val")) {
                            replace += "    <span id='managedservicesval' class='ui-nav-tile ui-nav-normal ui-nav-hover' onclick=\"DeleteContractType();\"><div><img src='public/images/delete.png' />Vertragstyp l&ouml;schen</div></span>\n";
                        }
                            
                    }
                            
                    replace += "<div id='MSDialog'></div><div id='MSDialogSuccess'></div>";
                }
                
                replace += "</section><section id='navigation-bottom'>";
                
                if(Executions.UserIsPermitted(uid,"profile")) {
                    replace += "    <span id='profile' class='ui-nav-tile ui-nav-normal ui-nav-hover' onclick=\"UserProfile('" + Base64Coder.encodeString(uid) + "');\"><div><img src='public/images/magazine-128.png' />Profil</div></span></span>";
                }
                
                if(Executions.UserIsPermitted(uid,"config")) {                
                    replace += "    <span id='config' class='ui-nav-tile ui-nav-normal ui-nav-hover' onclick=\"OpenWindow('Configuration','_self');\"><div><img src='public/images/configure-128.png' />Einstellungen</div></span>\n";
                }
                
                replace += "<br>\n";
                
            } else if(mod.equals("NavigationClose")) {
                replace += "</section></section>";
            } else if(mod.equals("Index") || mod.equals("ManagedService") || mod.equals("Monitoring") || mod.equals("Reporting")) {
                
                replace += "<section id='menu'><div id='bg-menu' class='ui-opacity'></div><div id='bg-user-menu' class='ui-opacity-med'></div>";
                replace += "    <div id='logo-title'>";
                replace += "        <font class='logo-red'>SuS</font><font class='logo-blue'>- Reports</font>";
                replace += "    </div>";
                replace += "    <div id='logo-img'>";
                replace += "        <img src='public/images/SIV_AG_Logo_RGB_Web.png' title='SIV.AG'/>";
                replace += "    </div>";
                replace += "    <div id='logo-subtitle'>";
                replace += "        Version 1 Build 2015.03";
                replace += "    </div>";
                replace += "    <div id='menu-locator'>";
                replace += "    </div>";
                replace += "</section>";
                
                if(Executions.UserIsPermitted(uid,"monitoring")) {
                    replace += "<section id='monitoring-menu'><div id='monitoring-view'></div></section>";
                }
                
                replace += "<section id='user-menu'><div id='UserMenu' class='ui-user-menu'><span class='login_username' style='float: left'></span>";
                
                replace += "<a href='logout'><span title='Logout' style='float: left; margin-top: 1px;' class='ui-icon ui-icon-power'></span></a></div></section><section id='user-picture'><img id='UserProfileConfig' src='public/images/DefaultProfile.png' /></section>";
                
                replace += "<section id='content-index'>";

                if(Executions.UserIsPermitted(uid,"monitoring")) {
                    if(Executions.UserIsPermitted(uid,"liveticker")) {
                        replace += "<section id='big-taov'></section>";
                        replace += "<div id='DialogSuccess'></div>";
                    }
                }
                    
            } else if (mod.equals("Login")) { 
                replace += "<section id='login-top'></section><section id='login-bottom'></section>";
                replace += "<section id='menu'><div id='bg-menu' class='ui-opacity'></div><div id='bg-user-menu' class='ui-opacity-med'></div>";
                replace += "    <div id='logo-title'>";
                replace += "        <font class='logo-red'>SuS</font><font class='logo-blue'>- Reports</font>";
                replace += "    </div>";
                replace += "    <div id='logo-img'>";
                replace += "        <img src='public/images/SIV_AG_Logo_RGB_Web.png' title='SIV.AG'/>";
                replace += "    </div>";
                replace += "    <div id='logo-subtitle'>";
                replace += "        Version 1 Build 2015.03";
                replace += "    </div>";
                replace += "    <div id='menu-locator'>";
                replace += "    </div>";
                replace += "</section>";
                replace += "<section id='content'>";
            } else {
                replace += "<section id='content'>";
            }

                replace += "";
                
        return replace;
    }
    
    static public String printSectionBottom(String uid) throws FileNotFoundException, IOException, NamingException, SQLException {
        String replace = "";
        replace+="</section>";
        replace+="<div id='Dialog'></div><div id='LiveDialog'></div>";
        
        if(Executions.UserIsPermitted(uid,"monitoring")) {
            replace+="<section id='bottom'>";
            replace+="</section>";
        }
        
        replace+="";
        return replace;
    }
    
    static public String printErrorBox(String message) {
        String replace = "<div id='login-error' class='ui-widget'><div class='ui-state-error ui-corner-all' style='padding: 0 .7em;'><p><span class='ui-icon ui-icon-alert' style='float: left; margin-right: .3em;'></span><strong>Fehler:</strong> " + message + "</p></div></div>";
        return replace;
    }
    
    static public String printInfoBox(String message) {
        String replace = "<div id='login-success' class='ui-widget'><div class='ui-state-highlight ui-corner-all' style='padding: 0 .7em;'><p><span class='ui-icon ui-icon-info' style='float: left; margin-right: .3em;'></span><strong>Information:</strong> " + message + "</p></div></div>";
        return replace;
    }
    
    static public String printTopMenu(String mod, String uid) {
        String replace = "        <div id='TopMenu'></div>\n";
        replace+="        <span id='top'>\n";
        replace+="            <p class='title'><font class='kvasy'>kVASy&reg;</font> System Control</p><div id='logo-div'><img class='logo' src='layout/images/logo_backgroundblue_whitetext.png' title='SIV.AG'/></div>\n";
        replace+="            <p class='subtitle'>Monitoring quite simple!</p>";
        replace+="        </span>\n";
        replace+="        <div id='UserMenu'><table cellpadding=0 cellspacing=0 border=0><tr><td><span class='UserDesc' style='float: left;'>User</span><span style='float: left; margin-top: -1px;' class='ui-icon ui-icon-triangle-1-s'></span></td><td width='10'></td><td colspan=3><span class='UserDesc' style='float: left;'>Session</span><span style='float: left; margin-top: -1px;' class='ui-icon ui-icon-triangle-1-s'></span></td></tr><tr valign=middle><td><p class='login_username'></p></td><td width='10'></td><td><p class='login_shortname'><a href='logout'>Abmelden</a><p></td></tr></table><div id='Liveticker'></div></div>\n";
        if ("mod".equals(mod)) {
        replace+="        <div id='UserView'><table cellpadding=0 cellspacing=0 border=0><tr><td><span class='UserDesc' style='float: left;'>View</span><span style='float: left; margin-top: -1px;' class='ui-icon ui-icon-triangle-1-s'></span></td></tr><tr><td><span class='UserReload' onclick=\"Reload('" + uid + "');\">Reload</span></td></tr></table></div>";
        replace+="        <div id='back-div'></div>\n";
        }
        return replace;
    }
    
    static public String printSidebar(String mod, String uid) throws FileNotFoundException, IOException, NamingException, SQLException  {
        String replace = "        <div id='SidebarSmall'>\n";
        
        if(Executions.UserIsPermitted(uid,"monitoring")) {
            if(Executions.UserIsPermitted(uid,"liveticker")) {
                replace+="            <div id='LivetickerSidebar'></div>\n";
            }
        }
        
        replace+="        </div>\n";
        replace+="        <div id='Sidebar'>\n";
        replace+="            <div id='SidebarContent'>\n";
        replace+="                <section id='SidebarSearch'></section>\n";
        replace+="                <section id='SidebarSearchFilter'></section>\n";
        replace+="                <section id='SidebarLiveticker'></section>\n";
        replace+="                <section id='SidebarSubmenu'></section>\n";
        replace+="            </div>\n";
        replace+="        </div>\n";
        return replace;
    }
    
    static public String printBottombar(String mod, String uid) throws FileNotFoundException, IOException, NamingException, SQLException {
        String replace = "";
        
        if(Executions.UserIsPermitted(uid,"monitoring")) {
            replace+="        <div id='SidebarBottomSmall'>\n";
            replace+="            <div id='SlimTaov'></div>\n";
            replace+="        </div>\n";
            replace+="        <div id='SidebarBottom'>\n";
        }
                
        replace+="        </div>\n";
        return replace;
    }
    
    static public String printAccessDenied(String lang) {
        String replace = "<span class='access-denied'>Du bis nicht berechtigt dieses Modul aufzurufen.</span><script>alert('Du bis nicht berechtigt dieses Modul aufzurufen.');</script>";
        return replace;
    }
}
