/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.siv.systemcontrol;

import de.siv.web.Html;
import de.siv.webinstaller.Functions;
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
public class Login extends HttpServlet {
    Properties props = null;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String line="0";
        try {
            line = Functions.IsAlreadyInstalled();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        
            if (line.equals("1")) {
                PrintWriter out = response.getWriter();
                response.setCharacterEncoding("UTF-8");
                response.addHeader("Access-Control-Allow-Origin", "*");
                response.addHeader("Access-Control-Allow-Methods", "*");
                response.setContentType("text/html; charset=utf-8");
                out.println(Html.openHtmlAndHead(null));
                out.println(Html.includeMeta(null));
                out.println("    <title>Login - kVASy&reg; System Control</title>");
                out.println(Html.includeJs("Login"));
                out.println(Html.includeCss("Login"));
                out.println(""
                    + "<script>\n"
                    + "$(function() {\n"
                    + "    //$('.login-button').button().css('border','1px solid #82abcc');\n"
                    + "    $('div#menu-locator').html('Login');\n"
                    + "    $('input.login-input').addClass('ui-input-corner-all');\n"
                    + "    $('input.login-button').button();\n"
                    + "    $('input').addClass('ui-input-hofo');\n"
                    + "});\n"
                    + "</script>\n"
                    + "");
                out.println(Html.closeHeadOpenBody(null));
                out.println(Html.printSectionMenu(null,"Login"));
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
                    + "    <span class='login-left'><h3>Gute Basis, Flexible und modulare Architektur</h3><p>Das kVASy&reg; System Control baut auf der beliebten Monitoring API Icinga und etablierten Erweiterungen auf und nutzt alle einhergehenden Add-Ons, Features und Funktionen. Statistische Informationen spezieller Performance Sensoren werden f&uuml;r das Trendreporting in einer PostgreSQL Datenbank ausgelagert.</p><br /><h3>System Monitoring: alles auf einen Blick!</h3><p>Die Web-Oberfl&auml;che der kVASy&reg; System Control zeigt &uuml;bersichtlich den aktuellen Zustand aller eingerichteten Systeme und Dienste der kVASy&reg;, kVASy&reg; BI und dazugeh&ouml;rigen IT-Infrastruktur. St&ouml;rungen oder Ausf&auml;lle von Diensten, Services, Servern oder Netzwerkkomponenten werden mit einem Blick erfasst. </p></span>\n"
                    + "    <span class='login-center'><h3>Flexible Ereignissteuerung</h3><p>Je nach Ergebnis der Checks ordnet das System den richtigen Status zu (z. B. OK, WARNING, CRITICAL oder UNKNOWN). Individuelle Regeln entscheiden wann alarmiert und, sofern notwendig, eskaliert wird. Die Alarmierung erfolgt er-eignisgesteuert. Individuelle Konfigurationen definieren welche Personen in welchem Zeitfenster &uuml;ber welche Medien (E-Mail, SMS) benachrichtigt werden. Durch definierte Event Handler k&ouml;nnen erste Reaktionen auf das Problem initiiert werden.</p><br /><h3>Erweiterbarkeit</h3><p>Von einem bis tausenden von Hosts und Services – die Icinga Basis mit seinem modularen Aufbau bietet grenzenlose Erweiterbarkeit der Monitoring API, die dann Templatebasiert oder selbstkonfiguriert in der Web Applikation kVASy&reg; System Control dargestellt werden.</p></span>\n"
                    + "    <span class='login-right'><h3>Nutzerverwaltung mit LDAP-Authentifizierung</h3><p>Die eingebaute Nutzerverwaltung erm&ouml;glicht die Anpassung der Benutzer Zugriffe und Benachrichtigungen auf Server-Gruppen, Servern und Diensten. Die Authen-tifizierung wird dabei vom AD- oder LDAP-Server &uuml;bernommen.</p><br /><h3>Trendreporting und historische Performance Daten</h3><p>Durch die Sammlung verschiedenster Performancedaten von speziell definierten Sensoren k&ouml;nnen wir langfristige Trends fr&uuml;hzeitig erkennen und zuverl&auml;ssige Prognosen f&uuml;r die Zukunft erstellen wie z.B. Festplatten Platz oder Load des Systems.</p></span>\n"
                    + "</div>"
                    + "");
                out.println(Html.printSectionBottom(null));
                out.println(Html.closeBodyCloseHtml(null));
            } else {
                response.sendRedirect("admin/");
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
        //
    }
}
