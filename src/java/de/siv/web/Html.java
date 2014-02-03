/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.siv.web;

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
        String replace ="    </head>\n    <body>\n";
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
        return replace;
    }
    
    static public String includeJs(String mod) {
        String replace = "    <script type='text/javascript' src='public/script/jquery-1.10.2.js'></script>\n";
        replace+="    <script type='text/javascript' src='public/script/jquery-ui-1.10.4.custom.min.js'></script>\n";
        
        return replace;
    }
    
    static public String includeCss(String mod) {
        String replace = "    <link rel='stylesheet' href='public/style/jquery-ui-1.10.4.custom.css' />\n";
        replace+="    <link rel='stylesheet' href='public/style/systemcontrol.css' />\n";
              
        return replace;
    }
    
    static public String printSectionMenu(String uid,String mod) {
        String replace = ""
                + "<section id='menu'><div id='bg-menu' class='ui-opacity'></div><div id='bg-user-menu' class='ui-opacity-med'></div>"
                + "    <div id='logo-title'>"
                + "        <font class='logo-red'>kVASy&reg;</font><font class='logo-blue'>System Control</font>"
                + "    </div>"
                + "    <div id='logo-img'>"
                + "        <img src='public/images/SIV_AG_Logo_RGB_Web.png' title='SIV.AG'/>"
                + "    </div>"
                + "    <div id='logo-subtitle'>"
                + "        Version 3 Build 2014.02"
                + "    </div>"
                + "    <div id='menu-locator'>"
                + "    </div>"
                + "</section>";
        
            if(mod.equals("Index")) {
                replace += "<section id='user-menu'></section>";
                replace += "<section id='content-index'>";
            } else {
                replace += "<section id='content'>";
            }
            
                replace += "";
                
        return replace;
    }
    
    static public String printSectionBottom(String uid) {
        String replace = ""
                + "</section>"
                + "<section id='bottom'>"
                + "</section>"
                + "";
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
    
    static public String printSidebar(String mod) {
        String replace = "        <div id='SidebarSmall'>\n";
        replace+="            <div id='LivetickerSidebar'></div>\n";
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
    
    static public String printBottombar(String mod) {
        String replace = "        <div id='SidebarBottomSmall'>\n";
        replace+="            <div id='SlimTaov'></div>\n";
        replace+="        </div>\n";
        replace+="        <div id='SidebarBottom'>\n";
        replace+="            <div id='SidebarBottomContent'>\n";
        replace+="                <table id='TPie' cellpadding='0' cellspacing='0' border='0'>\n";
        replace+="                    <tr>\n";
        replace+="                        <td>\n";
        replace+="                            <div id='HeaderHostPie'>Host Status &Uuml;bersicht</div>\n";
        replace+="                            <div id='HostPie'></div>\n";
        replace+="                        </td>\n";
        replace+="                        <td>\n";
        replace+="                            <div id='HostPer'></div>\n";
        replace+="                        </td>\n";
        replace+="                        <td>\n";
        replace+="                            <div id='HeadComments'>Letzte Kommentare</div>\n";
        replace+="                            <div id='Comments'></div>\n";
        replace+="                            <div id='FooterComments'></div>\n";
        replace+="                        </td>\n";
        replace+="                        <td>\n";
        replace+="                            <div id='HeaderServicePie'>Service Status &Uuml;bersicht (ONLINE Hosts)</div>\n";
        replace+="                            <div id='ServicePie'></div>\n";
        replace+="                        </td>\n";
        replace+="                        <td>\n";
        replace+="                            <div id='ServicePer'></div>\n";
        replace+="                        </td>\n";
        replace+="                    </tr>\n";
        replace+="                </table>\n";
        replace+="                <br>\n";
        replace+="                <div id='HeadDivShowCritical'>Aktuelle Probleme</div>\n";
        replace+="                <div id='DivShowCritical'></div>\n";
        replace+="                <div id='FooterDivShowCritical'></div>\n";
        replace+="            </div>\n";
        replace+="        </div>\n";
        return replace;
    }
}
