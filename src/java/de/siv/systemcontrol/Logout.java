/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.siv.systemcontrol;

import de.siv.modules.Executions;
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
public class Logout extends HttpServlet {
    Properties props = null;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String Uid = request.getRemoteUser();
            HttpSession session = request.getSession();
            PrintWriter out = response.getWriter(); 
            response.setCharacterEncoding("UTF-8");
            response.addHeader("Access-Control-Allow-Origin", "*");
            response.addHeader("Access-Control-Allow-Methods", "*");
            response.setContentType("text/html; charset=utf-8");
            out.println(Html.openHtmlAndHead(null));
            out.println(Html.includeMeta(null));
            out.println("    <meta http-equiv='Refresh' content='3; URL=./' />");
            out.println("    <title>Logout - kVASy&reg; System Control</title>");
            out.println(Html.includeJs("Login"));
            out.println(Html.includeCss("Login"));
            out.println(""
                + "<script>\n"
                + "$(function() {\n"
                + "    $('div#menu-locator').html('Login');\n"
                + "    $('input.login-button').button();\n"
                + "    $('input.login-input').addClass('ui-input-corner-all');\n"
                + "    $('input').addClass('ui-input-hofo');\n"
                + "    $.cookie('liveticker-length',0);\n"
                + "    $.cookie('liveticker-count',0);\n"
                + "});\n"
                + "</script>\n"
                + "");
            out.println(Html.closeHeadOpenBody(null));
            out.println(Html.printSectionMenu("000","Login"));
            
            out.println(Html.printInfoBox("Der Nutzer '" + Uid + "' wurde abgemeldet."));
            
            out.println(""
                + "<div id='login-div'>\n"
                + "<section>\n"
                + "    <form method='post' action='j_security_check'>\n"
                + "            <table cellpadding='0' cellspacing='5' border='0' id='login-table'>\n"
                + "                <tr>\n"
                + "                    <td width='115' align='right' class='login-small'>Benutzername:</td>\n"
                + "                    <td><input type='text' name='j_username' class='login-input'/></td>\n"
                + "                </tr>\n"
                + "                <tr>\n"
                + "                    <td width='115' align='right' class='login-small'>Kennwort:</td>\n"
                + "                    <td><input type='password' name='j_password' class='login-input'/></td>\n"
                + "                </tr>\n"
                + "            </table>\n"
                + "        <div id='login-footer'><input type='submit' value='Anmeldung' class='login-button'/></div>\n"
                + "    </form>\n"
                + "</section>\n"
                + "</div>\n"
                + "<div id='login-desc'>\n"
                + "    <span class='login-left'><h3>Kunden, Vertr&auml;ge und Berichte</h3><p>AMS Contracts & Reports erm&ouml;glicht es dem Anwender anhand von Kunden und deren zugeordneten Vertr&auml;gen Servicearbeiten zu pflegen und reporten.</p><br /><!--h3>System Monitoring: alles auf einen Blick!</h3><p>Die Web-Oberfl&auml;che der kVASy&reg; System Control zeigt &uuml;bersichtlich den aktuellen Zustand aller eingerichteten Systeme und Dienste der kVASy&reg;, kVASy&reg; BI und dazugeh&ouml;rigen IT-Infrastruktur. St&ouml;rungen oder Ausf&auml;lle von Diensten, Services, Servern oder Netzwerkkomponenten werden mit einem Blick erfasst. </p--></span>\n"
                //+ "    <span class='login-center'><h3>Flexible Ereignissteuerung</h3><p>Je nach Ergebnis der Checks ordnet das System den richtigen Status zu (z. B. OK, WARNING, CRITICAL oder UNKNOWN). Individuelle Regeln entscheiden wann alarmiert und, sofern notwendig, eskaliert wird. Die Alarmierung erfolgt er-eignisgesteuert. Individuelle Konfigurationen definieren welche Personen in welchem Zeitfenster &uuml;ber welche Medien (E-Mail, SMS) benachrichtigt werden. Durch definierte Event Handler k&ouml;nnen erste Reaktionen auf das Problem initiiert werden.</p><br /><h3>Erweiterbarkeit</h3><p>Von einem bis tausenden von Hosts und Services – die Icinga Basis mit seinem modularen Aufbau bietet grenzenlose Erweiterbarkeit der Monitoring API, die dann Templatebasiert oder selbstkonfiguriert in der Web Applikation kVASy&reg; System Control dargestellt werden.</p></span>\n"
                + "    <span class='login-right'><h3>Nutzerverwaltung mit LDAP-Authentifizierung</h3><p>Die eingebaute Nutzerverwaltung erm&ouml;glicht die Anpassung der Benutzer Zugriffe auf spezielle Dienste. Die Authentifizierung wird dabei vom AD- oder LDAP-Server &uuml;bernommen.</p><br /><!--h3>Trendreporting und historische Performance Daten</h3><p>Durch die Sammlung verschiedenster Performancedaten von speziell definierten Sensoren k&ouml;nnen wir langfristige Trends fr&uuml;hzeitig erkennen und zuverl&auml;ssige Prognosen f&uuml;r die Zukunft erstellen wie z.B. Festplatten Platz oder Load des Systems.</p--></span>\n"
                + "</div>"
                + "");
            //out.println(Html.printSectionBottom("000"));
            out.println(Html.closeBodyCloseHtml(null));
            Executions.UpdateUserIsLoggedOut(Uid);
            session.invalidate();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Logout.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(Logout.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Logout.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
