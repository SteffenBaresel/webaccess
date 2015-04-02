/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.siv.script;

import de.siv.modules.*;
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
public class UserBasics extends HttpServlet {
    Properties props = null;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response, String Uid)
            throws ServletException, IOException, FileNotFoundException, NamingException, SQLException {
        PrintWriter out = response.getWriter(); 
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "*");
        response.setContentType("application/javascript; charset=utf-8");
        
        out.println("\n" +
"function ClearValue(id) {\n" +
"    document.getElementById(id).value = \"\";\n" +
"}\n");

        Integer MO=0; if(Executions.UserIsPermitted(Uid,"monitoring")) { MO=1; }
        Integer CF=0; if(Executions.UserIsPermitted(Uid,"config")) { CF=1; }
        Integer CW=0; if(Executions.UserIsPermitted(Uid,"config_web")) { CW=1; }
        Integer CFGM=0; if(Executions.UserIsPermitted(Uid,"config_mail")) { CFGM=1; }
        Integer CFGUM=0; if(Executions.UserIsPermitted(Uid,"config_usermanagement")) { CFGUM=1; }
        Integer CFGMM=0; if(Executions.UserIsPermitted(Uid,"config_mailing")) { CFGMM=1; }
        Integer MSVAB=0; if(Executions.UserIsPermitted(Uid,"managed_services_vab")) { MSVAB=1; }
        Integer MSREP=0; if(Executions.UserIsPermitted(Uid,"reporting")) { MSREP=1; }
        
        if(Executions.UserIsPermitted(Uid,"addlink")) {
            out.println("\n" +
"function AddLink() {\n" +
"    $('#Dialog').html('<div id=\"AddLinkDialog\" title=\"F&uuml;ge einen weiteren Link zum Dashboard hinzu!\">" +
"    <table id=\"AddLinkTable\">" +
"        <tr>" +
"            <td>Titel:</td>" +
"            <td><input type=text id=\"DaBTitle\"/></td>" +
"        </tr>" +
"        <tr>" +
"            <td>Beschreibung:</td>" +
"            <td><input type=text id=\"DaBDesc\"/></td>" +
"        </tr>" +
"        <tr>" +
"            <td>URL Ziel:</td>" +
"            <td><input type=text id=\"DaBTarget\"/></td>" +
"        </tr>" +
"    </table>" +
"</div>');\n" +
"    $('input[type=text]').addClass('ui-input-hofo');\n" +
"    $('#AddLinkDialog').dialog({\n" +
"	autoOpen: true,\n" +
"	height: 225,\n" +
"	width: 600,\n" +
"	draggable: false,\n" +
"	resizable: false,\n" +
"	modal: true,\n" +
"	buttons: { \n" +
"            EINTRAGEN: function() { \n" +
"                $.ajax({\n" +
"                    url: '/gateway/exec/AddDashboardLink?uuid=' + base64_encode( UUID ) + '&title=' + base64_encode( $('#DaBTitle').val() ) + '&desc=' + base64_encode( $('#DaBDesc').val() ) + '&target=' + base64_encode( $('#DaBTarget').val() ),\n" +
"                    crossDomain: true,\n" +
"                    success: function(json) {\n" +
"                        if (json.ADD == '1') {\n" +
"                            DialogMailComplete('#DialogSuccess','Dashboard Link hinzugef&uuml;gt','Dashboard Link wurde erfolgreich hinzugef&uuml;gt.');\n" +
"                        } else {\n" +
"                            DialogMailComplete('#DialogSuccess','+++ Fehler beim hinzuf&uuml;gen des Dashboard Links +++','<font color=red>Der Dashboard Link konnte NICHT hinzugef&uuml;gt werden.</font>');\n" +
"                        }\n" +
"                    },\n" +
"                    error: function (xhr, thrownError) {\n" +
"                        alert(xhr.status + '::' + thrownError);\n" +
"                    },\n" +
"                    dataType: 'json',\n" +
"                    cache: false\n" +
"               });\n" +
"		$(this).dialog('close');\n" +
"		$('#AddLinkDialog').remove();\n" +
"                //location.reload();\n" +
"            },\n" +
"            ABBRECHEN: function() {\n" +
"                $(this).dialog('close');\n" +
"		$('#AddLinkDialog').remove();\n" +
"            }\n" +
"	}\n" +
"    }).parent().find('.ui-dialog-titlebar-close').hide();\n" +
"}\n" +
                    "");
        }
        
            // Configuration
        
        if(CF == 1) {
        
            out.println("" +
"function Configuration(uid) {\n" +
"    var b64uid = uid;\n" +
"    /* Dialog format start */\n" +
"    $('section#section-configuration').append('" +
"        <div id=\"ConfigurationTabs\">" +
"            <ul>\\n\\");
            
            if(CW == 1) {
                out.println("                <li><a href=\"#ConfigurationTabs1\">Web-Konfiguration</a></li>\\n\\");
            }
            
            if(CFGM == 1) {
                out.println("                <li><a href=\"#ConfigurationTabs6\">Mail-Format</a></li>\\n\\");
            }
            
            //if(CFGMM == 1) {
            //    out.println("                <li><a href=\"#ConfigurationTabs9\">Mailing Monitoring</a></li>\\n\\");
            //}
            
            if(MSREP == 1 && MSVAB == 1) {
                out.println("                <li><a href=\"#ConfigurationTabs10\">Reports</a></li>\\n\\");
            }
            
            if(CFGUM == 1) {
                out.println("                <li><a href=\"#ConfigurationTabs2\">Nutzerverwaltung</a></li>\\n\\");
                out.println("                <li><a href=\"#ConfigurationTabs3\">Rechteverwaltung</a></li>\\n\\");
                out.println("                <li><a href=\"#ConfigurationTabs4\">Berechtigungen</a></li>\\n\\");
                out.println("                <li><a href=\"#ConfigurationTabs5\">Role-Mapping</a></li>\\n\\");
            }
            
            if(MO == 1) {
                //out.println("                <li><a href=\"#ConfigurationTabs7\">Monitoring</a></li>\\n\\");
                out.println("                <li><a href=\"#ConfigurationTabs8\">Monitoring2Profile</a></li>\\n\\");
            }
        
            out.println("            </ul>\\n\\");
            
            if(CW == 1) {
            
            out.println("            <div id=\"ConfigurationTabs1\">" +
/*"                <div id=\"ConfigurationSection\">" +
"                    <div id=\"ConfigurationSectionTitle\">Reset</div>" +
"                    <button id=\"2\" class=\"ConfigurationSectionPoint\" onclick=\"ResetDashboard();\">Dashboard Zur&uuml;cksetzen</button>" +
"                    <!--button id=\"3\" class=\"ConfigurationSectionPoint\" onclick=\"DeleteBasicConfig();\">Alle Einstellungen zur&uuml;cksetzen</button-->" +
"                </div>" +
"                <div id=\"ConfigurationSection\">" +
"                    <div id=\"ConfigurationSectionTitle\">Anzeige Einstellungen</div>" +
"                    <div class=\"Config\"></div>" +
"                </div>" +*/
"                <div id=\"ConfigurationSection\">" +
"                    <div id=\"ConfigurationSectionTitle\">Mailing, Standard CC Empf&auml;nger</div>" +
"                    <div id=\"MailCC\"><span>Mailadressen CC: (Mail1,Mail2,Mailn)</span><input type=text id=\"InMailCC\"/ placeholder=\"group@domain.local\"><button onclick=\"AddMailingConfig(\\'InMailCC\\',\\'CC\\');\">Festlegen</button></div>" +
"                </div>" +
"                <div id=\"ConfigurationSection\">" +
"                    <div id=\"ConfigurationSectionTitle\">Mailing, Eskalationsmanagement</div>" +
"                    <div id=\"MailEsk1\"><span>Mailadressen Eskalationsstufe 1: (Mail1,Mail2,Mailn)</span><input type=text id=\"InMailEsk1\" placeholder=\"esk1@domain.local\"/><button onclick=\"AddMailingConfig(\\'InMailEsk1\\',\\'ESK1\\');\">Festlegen</button></div>" +
"                    <div id=\"MailEsk2\"><span>Mailadressen Eskalationsstufe 2: (Mail1,Mail2,Mailn)</span><input type=text id=\"InMailEsk2\" placeholder=\"esk1@domain.local,esk2@domain.local\"/><button onclick=\"AddMailingConfig(\\'InMailEsk2\\',\\'ESK2\\');\">Festlegen</button></div>" +
"                    <div id=\"MailEsk3\"><span>Mailadressen Eskalationsstufe 3: (Mail1,Mail2,Mailn)</span><input type=text id=\"InMailEsk3\" placeholder=\"esk1@domain.local,esk2@domain.local,esk3@domain.local\"/><button onclick=\"AddMailingConfig(\\'InMailEsk3\\',\\'ESK3\\');\">Festlegen</button></div>" +
"                </div>" +
"                <div id=\"ConfigurationSection\">" +
"                    <div id=\"ConfigurationSectionTitle\">Mailing, Signatur</div>" +
"                    <div id=\"MailSG\"><div id=\"MailSGContent\"></div><button onclick=\"AddMailingSignature(\\'MailSG\\',\\'SIGN\\');\">Festlegen</button></div>" +
"                </div>" +
"            </div>\\n\\");
            
            }

            if(MSREP == 1 && MSVAB == 1) {
                
                out.println("            <div id=\"ConfigurationTabs10\">" +
"                <div id=\"ConfigurationSection\">" +
"                    <div id=\"ConfigurationSectionTitle\">Bemerkungen</div>" +
"                    <div id=\"LastPage\"><textarea id=\"LastPageContent\"></textarea><br><button onclick=\"UpdateConfigReporting(\\'LastPageContent\\',\\'LastPageComment\\');\">Festlegen</button></div>" +
"                </div>" +
"                <div id=\"ConfigurationSection\">" +
"                    <div id=\"ConfigurationSectionTitle\">Ansprechpartner</div>" +
"                    <div id=\"LastPageContacts\"><textarea id=\"LastPageContactsContent\"></textarea><br><button onclick=\"UpdateConfigReporting(\\'LastPageContactsContent\\',\\'LastPageContactsComment\\');\">Festlegen</button></div>" +
"                </div>" +
"            </div>\\n\\");
                
            }
            
            if(CFGUM == 1) {
            
            out.println("            <div id=\"ConfigurationTabs2\">" +
"                <div id=\"ConfigurationSection\">" +
"                    <div id=\"ConfigurationSectionTitle\">Nutzer</div>" +
"                    <div id=\"UserMgmntUserMenu\"><table><tr><td>UsID:</td></tr><tr><td><input type=\"text\" placeholder=\"mmustermann\" id=\"UMUMuid\" /></td></tr><tr><td>Name:</td></tr><tr><td><input type=\"text\" placeholder=\"Max Mustermann\" id=\"UMUMusnm\" /></td></tr></table><button id=\"UserMgmntUserAdd\" onclick=\"AddEntry(\\'USER\\',\\'UMUMuid\\',\\'UMUMusnm\\');\">Nutzer Anlegen</button></div>" +
"                    <div id=\"UserMgmntUserList\"><table></table></div>" +
"                </div>" +
"                <div id=\"ConfigurationSection\">" +
"                    <div id=\"ConfigurationSectionTitle\">Gruppen</div>" +
"                    <div id=\"UserMgmntGroupMenu\"><table><tr><td>GrID:</td></tr><tr><td><input type=\"text\" placeholder=\"admin\" id=\"UMGMgrid\" /></td></tr><tr><td>Name:</td></tr><tr><td><input type=\"text\" placeholder=\"Administrator\" id=\"UMGMgrnm\" /></td></tr></table><button id=\"UserMgmntGroupAdd\" onclick=\"AddEntry(\\'GROUP\\',\\'UMGMgrid\\',\\'UMGMgrnm\\');\">Gruppe Anlegen</button></div>" +
"                    <div id=\"UserMgmntGroupList\"><table></table></div>" +
"                </div>" +
"            </div>\\n\\");
                 
            out.println("            <div id=\"ConfigurationTabs3\">" +
"                <div id=\"ConfigurationSection\">" +
"                    <div id=\"ConfigurationSectionTitle\">Rollen</div>" +
"                    <div id=\"UserMgmntRoleMenu\"><table><tr><td>RoID:</td></tr><tr><td><input type=\"text\" placeholder=\"full\" id=\"UMRMrlid\" /></td></tr><tr><td>Beschreibung:</td></tr><tr><td><input type=\"text\" placeholder=\"Vollzugriff\" id=\"UMRMrlnm\" /></td></tr></table><button id=\"UserMgmntRoleAdd\" onclick=\"AddEntry(\\'ROLE\\',\\'UMRMrlid\\',\\'UMRMrlnm\\');\">Rolle Anlegen</button></div>" +
"                    <div id=\"UserMgmntRoleList\"><table></table></div>" +
"                </div>" +
"                <div id=\"ConfigurationSection\">" +
"                    <div id=\"ConfigurationSectionTitle\">Privilegien</div>" +
"                    <div id=\"UserMgmntPrivMenu\"><table><tr><td>PrID:</td></tr><tr><td><input type=\"text\" placeholder=\"list_hosts\" id=\"UMPMprid\" /></td></tr><tr><td>Beschreibung:</td></tr><tr><td><input type=\"text\" placeholder=\"Liste alle Hosts\" id=\"UMPMprnm\" /></td></tr></table><button id=\"UserMgmntPrivAdd\" onclick=\"AddEntry(\\'PRIV\\',\\'UMPMprid\\',\\'UMPMprnm\\');\">Privileg Anlegen</button></div>" +
"                    <div id=\"UserMgmntPrivList\"><table></table></div>" +
"                </div>" +
"            </div>\\n\\");

            out.println("            <div id=\"ConfigurationTabs4\">" +
"                <div id=\"ConfigurationSection\">" +
"                    <div id=\"ConfigurationSectionTitle\">Nutzer zu Gruppe</div>" +
"                    <div id=\"UserMgmntUsGrList\"><span id=\"UserMgmntUsGrList1\"><table></table></span><span id=\"UserMgmntUsGrList2\"><table></table></span></div>" +
"                </div>" +
"                <div id=\"ConfigurationSection\">" +
"                    <div id=\"ConfigurationSectionTitle\">Rolle zu Privileg</div>" +
"                    <div id=\"UserMgmntRoPrList\"><span id=\"UserMgmntRoPrList1\"><table></table></span><span id=\"UserMgmntRoPrList2\"><table></table></span></div>" +
"                </div>" +
"            </div>\\n\\");

            out.println("            <div id=\"ConfigurationTabs5\">" +
"                <div id=\"ConfigurationSection\">" +
"                    <div id=\"ConfigurationSectionTitle\">Gruppe zu Rolle</div>" +
"                    <div id=\"UserMgmntGrRoList\"><span id=\"UserMgmntGrRoList1\"><table></table></span><span id=\"UserMgmntGrRoList2\"><table></table></span></div>" +
"                </div>" +
"                <div id=\"ConfigurationSection\">" +
"                    <div id=\"ConfigurationSectionTitle\">Kunde zu Rolle</div>" +
"                    <div id=\"UserMgmntCuRoList\"><span id=\"UserMgmntCuRoList1\"><table></table></span><span id=\"UserMgmntCuRoList2\"><table></table></span></div>" +
"                </div>" +
"                <div id=\"ConfigurationSection\">" +
"                    <div id=\"ConfigurationSectionTitle\">Vertrag zu Rolle</div>" +
"                    <div id=\"UserMgmntCoRoList\"><span id=\"UserMgmntCoRoList1\"><table></table></span><span id=\"UserMgmntCoRoList2\"><table></table></span></div>" +
"                </div>" +
"            </div>\\n\\");
                        
            }
            
            /*if(CFGMM == 1) {
            
            out.println("            <div id=\"ConfigurationTabs9\">" +
"                <div id=\"ConfigurationSection\">" +
"                    <div id=\"ConfigurationSectionTitle\">Mail Gruppen</div>" +
"                    <div id=\"MailingMonitoringGroupMenu\"><table><tr><td>MgID:</td></tr><tr><td><input type=\"text\" placeholder=\"allservices\" id=\"MMGMid\" /></td></tr><tr><td>Beschreibung:</td></tr><tr><td><input type=\"text\" placeholder=\"Alle Services\" id=\"MMGMnm\" /></td></tr></table><button id=\"MailingMonitoringGroupAdd\" onclick=\"AddEntry(\\'USER\\',\\'MMGMid\\',\\'MMGMnm\\');\">Mailgruppe Anlegen</button></div>" +
"                    <div id=\"MailingMonitoringGroupList\"><table></table></div>" +
"                </div>" +
"                <div id=\"ConfigurationSection\">" +
"                    <div id=\"ConfigurationSectionTitle\">Zeitplan</div>" +
"                    <div id=\"MailingMonitoringTimeMenu\"><table><tr><td>TmID:</td></tr><tr><td><input type=\"text\" placeholder=\"twentyfourseven\" id=\"MMTMid\" /></td></tr><tr><td>Beschreibung:</td></tr><tr><td><input type=\"text\" placeholder=\"24x7\" id=\"MMTMnm\" /></td></tr></table><button id=\"MailingMonitoringTimeAdd\" onclick=\"AddEntry(\\'GROUP\\',\\'MMTMid\\',\\'MMTMnm\\');\">Zeitplan Anlegen</button></div>" +
"                    <div id=\"MailingMonitoringTimeList\"><table></table></div>" +
"                </div>" +
"            </div>\\n\\");
            
            }*/
            
            if(CFGM == 1) {

            out.println("            <div id=\"ConfigurationTabs6\">" +
"                <div id=\"ConfigurationSection\">" +
"                    <div id=\"ConfigurationSectionTitle\">Mail-Header</div>" +
"                    <div id=\"MailH\"><div id=\"MailHContent\"></div><button onclick=\"AddMailConfig(\\'MailH\\',\\'HEADER\\');\">Festlegen</button></div>" +
"                </div>" +
"                <div id=\"ConfigurationSection\">" +
"                    <div id=\"ConfigurationSectionTitle\">Mail-Footer</div>" +
"                    <div id=\"MailF\"><div id=\"MailFContent\"></div><button onclick=\"AddMailConfig(\\'MailF\\',\\'FOOTER\\');\">Festlegen</button></div>" +
"                </div>" +
"            </div>\\n\\");
                
            }
            
            if(MO == 1) {
            
            out.println("            <div id=\"ConfigurationTabs8\">" +
"                <div id=\"ConfigurationSection\">" +
"                    <div id=\"ConfigurationSectionTitle\">Host zu Rolle</div>" +
"                    <div id=\"UserMgmntHoRoList\"><span id=\"UserMgmntHoRoList1\"><table></table></span><span id=\"UserMgmntHoRoList2\"><table></table></span></div>" +
"                </div>" +
"                <div id=\"ConfigurationSection\">" +
"                    <div id=\"ConfigurationSectionTitle\">Host zu Kunde</div>" +
"                    <div id=\"UserMgmntHoCuList\"><span id=\"UserMgmntHoCuList1\"><table></table></span><span id=\"UserMgmntHoCuList2\"><table></table></span></div>" +
"                </div>" +
"                <div id=\"ConfigurationSection\">" +
"                    <div id=\"ConfigurationSectionTitle\">Host zu Vertrag</div>" +
"                    <div id=\"UserMgmntHoCoList\"><span id=\"UserMgmntHoCoList1\"><table></table></span><span id=\"UserMgmntHoCoList2\"><table></table></span></div>" +
"                </div>" +
"            </div>\\n\\");
            
            }
            
            out.println("       </div>" +
"   ');\n" +
"    \n" +
"    $('#ConfigurationTabs').tabs();\n" +
"    $('#2').button();\n" +
"    $('#3').button();\n" +
"    $('#UserMgmntUserAdd').button();\n" +
"    $('#UserMgmntGroupAdd').button();\n" +
"    $('#UserMgmntRoleAdd').button();\n" +
"    $('#UserMgmntPrivAdd').button();\n" +
"    $('#MailCC button').button();\n" +
"    $('#MailEsk1 button').button();\n" +
"    $('#MailEsk2 button').button();\n" +
"    $('#MailEsk3 button').button();\n" +
"    $('#MailSG button').button();\n" +
"    $('#MailH button').button();\n" +
"    $('#MailF button').button();\n" +
"    $('#MailSGContent').jqte();\n" +
"    $('#MailHContent').jqte();\n" +
"    $('#MailFContent').jqte();\n" +
"    $('#MailingMonitoringGroupAdd').button();\n" +
"    $('#MailingMonitoringTimeAdd').button();\n" +
"    $('#LastPage button').button();\n" +
"    $('#LastPageContacts button').button();\n" +
"    $('input[type=text]').addClass('ui-input-hofo');\n" +
"\n" +
"\n");
                    
            if(CFGUM == 1) {

                out.println("" +
"            FillUserManagement();\n" +
"            FillUserManagementUsGr();\n" +
"            FillUserManagementRoPri();\n" +
"            FillUserManagementGrRo();\n" +
"            FillUserManagementCuRo();\n" +
"            FillUserManagementCoRo();\n");
        
            }
            
            if(MSREP == 1 && MSVAB == 1) {
                
                out.println("" +
"            GetLastPageComment();\n" +
"            GetLastPageContactsComment();\n");
                
            }
            
            /*if(CFGMM == 1) {

                out.println("" +
"            FillMonitoringMailing();\n");
        
            }*/

            if(MO == 1) {

                out.println("" +
"            FillUserManagementHoCu();\n" +
"            FillUserManagementHoCo();\n" +
"            FillUserManagementHoRo();\n" +
                "");
        
            }

            
            if(CW == 1) {
                
                out.println("" +
"             for (var key in Mailing) {\n" +
"                 var obj = Mailing[key];\n" +
"                 for (var prop in obj) {" +
"                     if(obj.hasOwnProperty(prop)) {\n" +
"                         if (prop == 'CC') {\n" +
"                             $('#InMailCC').val(base64_decode( obj[prop] ));\n" +
"                         } else if (prop == 'ESK1') {\n" +
"                             $('#InMailEsk1').val(base64_decode( obj[prop] ));\n" +
"                         } else if (prop == 'ESK2') {\n" +
"                             $('#InMailEsk2').val(base64_decode( obj[prop] ));\n" +
"                         } else if (prop == 'ESK3') {\n" +
"                             $('#InMailEsk3').val(base64_decode( obj[prop] ));\n" +
"                         } else if (prop == 'SIGN') {\n" +
"                             //$('#MailSGContent').val(base64_decode( obj[prop] ));\n" +
"                             $('#MailSG div.jqte_editor').html(base64_decode( obj[prop] ));\n" +
"                         }\n" +
"                     }\n" +
"                 }" +
"             }\n");
            
            }

            if(CFGM == 1) {

                out.println("" +
"            GetMailConfig();\n");
        
            }
            
            out.println("            PrepareHtmlPasteJqte('MailSG');" +
"            PrepareHtmlPasteJqte('MailH');" +
"            PrepareHtmlPasteJqte('MailF');" +         
"}");
            
            
            if(CW == 1) {
                
                out.println("\n" +
"function ResetDashboard() {\n" +
"    $.ajax({\n" +
"        url: '/gateway/exec/ResetDashboard',\n" +
"        crossDomain: true,\n" +
"        success: function(json) {\n" +
"            if (json.RESET == \"1\") {\n" +
"                DialogMailComplete(\"#DialogSuccess\",\"Konfiguration zur&uuml;ckgesetzt\",\"Die Konfiguration wurde erfolgreich zur&uuml;ckgesetzt.\");\n" +
"            } else {\n" +
"                DialogMailComplete(\"#DialogSuccess\",\"+++ Fehler beim zur&uuml;cksetzen der Konfiguration +++\",\"<font color=red>Die Konfiguration konnte nicht zur&uuml;ckgesetzt werden.</font>\");\n" +
"            }\n" +
"        },\n" +
"        dataType: 'json',\n" +
"        cache: false\n" +
"    });\n" +
"}\n" +
                "\n");
                
                out.println("\n" +
"function AddMailingConfig(id,key) {\n" +
"    $.ajax({\n" +
"        url: '/gateway/exec/AddMailingConfig?uuid=' + base64_encode( UUID ) + '&key=' + base64_encode( key ) + '&val=' + base64_encode( $('#' + id).val() ),\n" +
"        crossDomain: true,\n" +
"        success: function(json) {\n" +
"            if (json.ADD == \"1\") {\n" +
"                DialogMailComplete(\"#DialogSuccess\",\"Mailing Konfiguration hinzugef&uuml;gt\",\"Mailing Konfiguration wurde erfolgreich hinzugef&uuml;gt.\");\n" +
"            } else {\n" +
"                DialogMailComplete(\"#DialogSuccess\",\"+++ Fehler beim hinzuf&uuml;gen der Mailing Konfiguration +++\",\"<font color=red>Die Mailing Konfiguration konnte NICHT hinzugef&uuml;gt werden.</font>\");\n" +
"            }\n" +
"        },\n" +
"        dataType: 'json',\n" +
"        cache: false\n" +
"    });\n" +
"}\n" +
                "\n");

                out.println("\n" +
"function AddMailingSignature(id,key) {\n" +
"    var val = base64_encode( $('#' + id + ' div.jqte_editor').html() ).replace(/\\+/g,'78');\n" +
"    $.ajax({\n" +
"        url: '/gateway/exec/AddMailingConfig?uuid=' + base64_encode( UUID ) + '&key=' + base64_encode( key ) + '&val=' + val,\n" +
"        crossDomain: true,\n" +
"        success: function(json) {\n" +
"            if (json.ADD == \"1\") {\n" +
"                DialogMailComplete(\"#DialogSuccess\",\"Mailing Konfiguration hinzugef&uuml;gt\",\"Mailing Konfiguration wurde erfolgreich hinzugef&uuml;gt.\");\n" +
"            } else {\n" +
"                DialogMailComplete(\"#DialogSuccess\",\"+++ Fehler beim hinzuf&uuml;gen der Mailing Konfiguration +++\",\"<font color=red>Die Mailing Konfiguration konnte NICHT hinzugef&uuml;gt werden.</font>\");\n" +
"            }\n" +
"        },\n" +
"        dataType: 'json',\n" +
"        cache: false\n" +
"    });\n" +
"}\n" +
                "\n");                
                
            }
            
            if(CFGUM == 1) {
            
            out.println("\n" +
"function FillUserManagement() {\n" +
"    $.ajax({\n" +
"        url: '/gateway/exec/UserManagementOverview',\n" +
"        crossDomain: true,\n" +
"        success: function(json) {\n" +
"            $('#UserMgmntUserList table').html('<tr><th>Nutzername:</th><th>Erstellt:</th><th>Zuletzt angemeldet:</th><th>Aktiv:</th><th></th></tr>');\n" +
"            $.each(json.USERS, function() {\n" +
"                var active;\n" +
"                if (this.UACT == \"1\") { active = \"<img onclick=\\\"ActivateUser(\\'0\\',\\'\" + this.UUID + \"\\');\\\" src=\\\"public/images/accept.png\\\" title=\\\"Nutzer deaktivieren\\\"/>\"; } else { active = \"<img onclick=\\\"ActivateUser(\\'1\\',\\'\" + this.UUID + \"\\')\\\" src=\\\"public/images/cross-circle.png\\\" title=\\\"Nutzer aktivieren\\\"/>\"; }" +
"                $('#UserMgmntUserList table').append('<tr><td>' + base64_decode( this.USDC ) + '</td><td>' + this.UCRT + '</td><td>' + this.ULAL + '</td><td>' + active + '</td><td><img onclick=\"DeleteEntry(\\'USER\\',\\'' + this.UUID + '\\')\" src=\"public/images/minus-circle.png\" title=\"Nutzer l&ouml;schen\"/></td></tr>');\n" +
"            });\n" +
"            $('#UserMgmntGroupList table').html('<tr><th>Gruppenname:</th><th>Gruppenbeschreibung:</th><th></th></tr>');\n" +
"            $.each(json.GROUPS, function() {\n" +
"                $('#UserMgmntGroupList table').append('<tr><td>' + base64_decode( this.GRNM ) + '</td><td>' + base64_decode( this.GRDC ) + '</td><td><img onclick=\"DeleteEntry(\\'GROUP\\',\\'' + this.GRID + '\\')\" src=\"public/images/minus-circle.png\" title=\"Gruppe l&ouml;schen\"/></td></tr>');\n" +
"            });\n" +
"            $('#UserMgmntRoleList table').html('<tr><th>Rollenname:</th><th>Rollenbeschreibung:</th><th></th></tr>');\n" +
"            $.each(json.ROLES, function() {\n" +
"                $('#UserMgmntRoleList table').append('<tr><td>' + base64_decode( this.RLNM ) + '</td><td>' + base64_decode( this.RLDC ) + '</td><td><img onclick=\"DeleteEntry(\\'ROLE\\',\\'' + this.RLID + '\\')\" src=\"public/images/minus-circle.png\" title=\"Rolle l&ouml;schen\"/></td></tr>');\n" +
"            });\n" +
"            $('#UserMgmntPrivList table').html('<tr><th>Privilegname:</th><th>Privilegbeschreibung:</th><th></th></tr>');\n" +
"            $.each(json.PRIVS, function() {\n" +
"                $('#UserMgmntPrivList table').append('<tr><td>' + base64_decode( this.PRNM ) + '</td><td>' + base64_decode( this.PRDC ) + '</td><td><img onclick=\"DeleteEntry(\\'PRIV\\',\\'' + this.PRID + '\\')\" src=\"public/images/minus-circle.png\" title=\"Privileg l&ouml;schen\"/></td></tr>');\n" +
"            });\n" +
"        },\n" +
"        dataType: 'json',\n" +
"        cache: false\n" +
"    });\n" +
"}\n");                

            out.println("\n" +
"function FillUserManagementRoPri() {\n" +
"    $.ajax({\n" +
"        url: '/gateway/exec/PermissionRolePrivilege',\n" +
"        crossDomain: true,\n" +
"        success: function(json) {\n" +
"            var c=0;" +
"            $('#UserMgmntRoPrList1 table').html('<tr id=\"thd\"><th>Privileg:</th></tr>');\n" +
"            $('#UserMgmntRoPrList2 table').html('<tr id=\"thd\"></tr>');\n" +
"            $.each(json.PRIVILEGE, function() {\n" +
"                var PRID = this.PRID;\n" +
"                $('#UserMgmntRoPrList1 table').append('<tr id=\"ptr' + PRID + '\"><td>' + base64_decode( this.PRDC ) + '<br>(' + base64_decode( this.PRNM ) + ')</td></tr>');\n" +
"                $('#UserMgmntRoPrList2 table').append('<tr id=\"rtr' + PRID + '\"></tr>');\n" +
"                $('#ptr' + PRID).hover( function() { $( this ).css('background-color','#dedede'); $('#rtr' + PRID).css('background-color','#dedede'); }, function() { $( this ).css('background-color','#fff'); $('#rtr' + PRID).css('background-color','#fff'); } );\n" +
"                $('#rtr' + PRID).hover( function() { $( this ).css('background-color','#dedede'); $('#ptr' + PRID).css('background-color','#dedede'); }, function() { $( this ).css('background-color','#fff'); $('#ptr' + PRID).css('background-color','#fff'); } );\n" +
"                $.each(json.ROLE, function() {\n" +
"                    if(c == 0) {\n" +
"                        $('#UserMgmntRoPrList2 table tr#thd').append('<th>' + base64_decode( this.ROLE_DC ) + '</th>');\n" +
"                    }" +
"                    $('#UserMgmntRoPrList2 table tr#rtr' + PRID ).append('<td><img id=\"PrID' + PRID + '_RlID' + this.ROLE_ID + '\" onclick=\"UpdateRolePriv(\\'' + this.ROLE_ID + '\\',\\'' + PRID + '\\');\" src=\"public/images/cross-circle.png\" title=\"' + base64_decode( this.ROLE_DC ) + ' dieses Privileg erteilen\"/></td>');\n" +
"                });\n" +
"                c++;" +
"            });\n" +
"            $.each(json.ROLE, function() {\n" +
"                var ROLEID = this.ROLE_ID;\n" +                    
"                for(var i=0;i<this.ROLE_PRIV.length;i++) {\n" +
"                    $('#PrID' + this.ROLE_PRIV[i] + '_RlID' + ROLEID).attr('src','public/images/accept.png');\n" +                    
"                    $('#PrID' + this.ROLE_PRIV[i] + '_RlID' + ROLEID).attr('title',base64_decode( this.ROLE_DC ) + ' dieses Privileg entziehen');\n" + 
"                };\n" +
"            });\n" +
"        },\n" +
"        dataType: 'json',\n" +
"        cache: false\n" +
"    });\n" +
"}\n");            

                out.println("\n" +
"function FillUserManagementGrRo() {\n" +
"    $.ajax({\n" +
"        url: '/gateway/exec/PermissionGroupRole',\n" +
"        crossDomain: true,\n" +
"        success: function(json) {\n" +
"            var c=0;" +
"            $('#UserMgmntGrRoList1 table').html('<tr id=\"tghd\"><th>Rolle:</th></tr>');\n" +
"            $('#UserMgmntGrRoList2 table').html('<tr id=\"tghd\"></tr>');\n" +
"            $.each(json.ROLE, function() {\n" +
"                var ROID = this.ROID;\n" +
"                $('#UserMgmntGrRoList1 table').append('<tr id=\"pgtr' + ROID + '\"><td>' + base64_decode( this.RODC ) + '<br>(' + base64_decode( this.RONM ) + ')</td></tr>');\n" +
"                $('#UserMgmntGrRoList2 table').append('<tr id=\"rgtr' + ROID + '\"></tr>');\n" +
"                $('#pgtr' + ROID).hover( function() { $( this ).css('background-color','#dedede'); $('#rgtr' + ROID).css('background-color','#dedede'); }, function() { $( this ).css('background-color','#fff'); $('#rgtr' + ROID).css('background-color','#fff'); } );\n" +
"                $('#rgtr' + ROID).hover( function() { $( this ).css('background-color','#dedede'); $('#pgtr' + ROID).css('background-color','#dedede'); }, function() { $( this ).css('background-color','#fff'); $('#pgtr' + ROID).css('background-color','#fff'); } );\n" +
"                $.each(json.GROUP, function() {\n" +
"                    if(c == 0) {\n" +
"                        $('#UserMgmntGrRoList2 table tr#tghd').append('<th>' + base64_decode( this.GROUP_DC ) + '</th>');\n" +
"                    }" +
"                    $('#UserMgmntGrRoList2 table tr#rgtr' + ROID ).append('<td><img id=\"RoID' + ROID + '_GrID' + this.GROUP_ID + '\" onclick=\"UpdateGroupRole(\\'' + this.GROUP_ID + '\\',\\'' + ROID + '\\');\" src=\"public/images/cross-circle.png\" title=\"' + base64_decode( this.GROUP_DC ) + ' dieser Rolle zuweisen\"/></td>');\n" +
"                });\n" +
"                c++;" +
"            });\n" +
"            $.each(json.GROUP, function() {\n" +
"                var GROUPID = this.GROUP_ID;\n" +                    
"                for(var i=0;i<this.GROUP_ROLE.length;i++) {\n" +
"                    $('#RoID' + this.GROUP_ROLE[i] + '_GrID' + GROUPID).attr('src','public/images/accept.png');\n" +                    
"                    $('#RoID' + this.GROUP_ROLE[i] + '_GrID' + GROUPID).attr('title',base64_decode( this.GROUP_DC ) + ' dieser Rolle entziehen');\n" + 
"                };\n" +
"            });\n" +
"        },\n" +
"        dataType: 'json',\n" +
"        cache: false\n" +
"    });\n" +
"}\n"); 

                out.println("\n" +
"function FillUserManagementCuRo() {\n" +
"    $.ajax({\n" +
"        url: '/gateway/exec/GetCustomerRole',\n" +
"        crossDomain: true,\n" +
"        success: function(json) {\n" +
"            var c=0;" +
"            $('#UserMgmntCuRoList1 table').html('<tr id=\"tgcuro\"><th>Rolle:</th></tr>');\n" +
"            $('#UserMgmntCuRoList2 table').html('<tr id=\"tgcuro\"></tr>');\n" +
"            $.each(json.ROLE, function() {\n" +
"                var ROID = this.ROID;\n" +
"                $('#UserMgmntCuRoList1 table').append('<tr id=\"pgcuro' + ROID + '\"><td>' + base64_decode( this.RODC ) + '<br>(' + base64_decode( this.RONM ) + ')</td></tr>');\n" +
"                $('#UserMgmntCuRoList2 table').append('<tr id=\"rgcuro' + ROID + '\"></tr>');\n" +
"                $('#pgcuro' + ROID).hover( function() { $( this ).css('background-color','#dedede'); $('#rgcuro' + ROID).css('background-color','#dedede'); }, function() { $( this ).css('background-color','#fff'); $('#rgcuro' + ROID).css('background-color','#fff'); } );\n" +
"                $('#rgcuro' + ROID).hover( function() { $( this ).css('background-color','#dedede'); $('#pgcuro' + ROID).css('background-color','#dedede'); }, function() { $( this ).css('background-color','#fff'); $('#pgcuro' + ROID).css('background-color','#fff'); } );\n" +
"                $.each(json.CUSTOMER, function() {\n" +
"                    if(c == 0) {\n" +
"                        $('#UserMgmntCuRoList2 table tr#tgcuro').append('<th title=\"' + base64_decode( this.CUNM ) + '\">' + base64_decode( this.CUNR ) + '</th>');\n" +
"                    }" +
"                    $('#UserMgmntCuRoList2 table tr#rgcuro' + ROID ).append('<td><img id=\"RoID' + ROID + '_CuID' + this.CUID + '\" onclick=\"UpdateCustomerRole(\\'' + this.CUID + '\\',\\'' + ROID + '\\');\" src=\"public/images/cross-circle.png\" title=\"' + base64_decode( this.CUNM ) + ' dieser Rolle zuweisen\"/></td>');\n" +
"                });\n" +
"                c++;" +
"            });\n" +
"            $.each(json.CUSTOMER, function() {\n" +
"                var CUID = this.CUID;\n" +                    
"                for(var i=0;i<this.ROLES.length;i++) {\n" +
"                    $('#RoID' + this.ROLES[i] + '_CuID' + CUID).attr('src','public/images/accept.png');\n" +                    
"                    $('#RoID' + this.ROLES[i] + '_CuID' + CUID).attr('title',base64_decode( this.CUNM ) + ' dieser Rolle entziehen');\n" + 
"                };\n" +
"            });\n" +
"        },\n" +
"        dataType: 'json',\n" +
"        cache: false\n" +
"    });\n" +
"}\n"); 

                out.println("\n" +
"function FillUserManagementCoRo() {\n" +
"    $.ajax({\n" +
"        url: '/gateway/exec/GetContractRole',\n" +
"        crossDomain: true,\n" +
"        success: function(json) {\n" +
"            var c=0;" +
"            $('#UserMgmntCoRoList1 table').html('<tr id=\"tgcoro\"><th>Rolle:</th></tr>');\n" +
"            $('#UserMgmntCoRoList2 table').html('<tr id=\"tgcoro\"></tr>');\n" +
"            $.each(json.ROLE, function() {\n" +
"                var ROID = this.ROID;\n" +
"                $('#UserMgmntCoRoList1 table').append('<tr id=\"pgcoro' + ROID + '\"><td>' + base64_decode( this.RODC ) + '<br>(' + base64_decode( this.RONM ) + ')</td></tr>');\n" +
"                $('#UserMgmntCoRoList2 table').append('<tr id=\"rgcoro' + ROID + '\"></tr>');\n" +
"                $('#pgcoro' + ROID).hover( function() { $( this ).css('background-color','#dedede'); $('#rgcoro' + ROID).css('background-color','#dedede'); }, function() { $( this ).css('background-color','#fff'); $('#rgcoro' + ROID).css('background-color','#fff'); } );\n" +
"                $('#rgcoro' + ROID).hover( function() { $( this ).css('background-color','#dedede'); $('#pgcoro' + ROID).css('background-color','#dedede'); }, function() { $( this ).css('background-color','#fff'); $('#pgcoro' + ROID).css('background-color','#fff'); } );\n" +
"                $.each(json.CONTRACT, function() {\n" +
"                    if(c == 0) {\n" +
"                        $('#UserMgmntCoRoList2 table tr#tgcoro').append('<th title=\"' + base64_decode( this.CCNM ) + ' von ' + base64_decode( this.CUNM ) + '\">' + base64_decode( this.CCNR ) + '</th>');\n" +
"                    }" +
"                    $('#UserMgmntCoRoList2 table tr#rgcoro' + ROID ).append('<td><img id=\"RoID' + ROID + '_CcID' + this.CCID + '\" onclick=\"UpdateContractRole(\\'' + this.CCID + '\\',\\'' + ROID + '\\');\" src=\"public/images/cross-circle.png\" title=\"' + base64_decode( this.CCNM ) + ' von ' + base64_decode( this.CUNM ) + ' dieser Rolle zuweisen\"/></td>');\n" +
"                });\n" +
"                c++;" +
"            });\n" +
"            $.each(json.CONTRACT, function() {\n" +
"                var CCID = this.CCID;\n" +                    
"                for(var i=0;i<this.ROLES.length;i++) {\n" +
"                    $('#RoID' + this.ROLES[i] + '_CcID' + CCID).attr('src','public/images/accept.png');\n" +                    
"                    $('#RoID' + this.ROLES[i] + '_CcID' + CCID).attr('title',base64_decode( this.CCNM ) + ' von ' + base64_decode( this.CUNM ) + ' dieser Rolle entziehen');\n" + 
"                };\n" +
"            });\n" +
"        },\n" +
"        dataType: 'json',\n" +
"        cache: false\n" +
"    });\n" +
"}\n"); 

                out.println("\n" +
"function FillUserManagementUsGr() {\n" +
"    $.ajax({\n" +
"        url: '/gateway/exec/PermissionUserGroup',\n" +
"        crossDomain: true,\n" +
"        success: function(json) {\n" +
"            var c=0;" +
"            $('#UserMgmntUsGrList1 table').html('<tr id=\"tuhd\"><th>Gruppe:</th></tr>');\n" +
"            $('#UserMgmntUsGrList2 table').html('<tr id=\"tuhd\"></tr>');\n" +
"            $.each(json.GROUP, function() {\n" +
"                var GRID = this.GRID;\n" +
"                $('#UserMgmntUsGrList1 table').append('<tr id=\"putr' + GRID + '\"><td>' + base64_decode( this.GRDC ) + '<br>(' + base64_decode( this.GRNM ) + ')</td></tr>');\n" +
"                $('#UserMgmntUsGrList2 table').append('<tr id=\"rutr' + GRID + '\"></tr>');\n" +
"                $('#putr' + GRID).hover( function() { $( this ).css('background-color','#dedede'); $('#rutr' + GRID).css('background-color','#dedede'); }, function() { $( this ).css('background-color','#fff'); $('#rutr' + GRID).css('background-color','#fff'); } );\n" +
"                $('#rutr' + GRID).hover( function() { $( this ).css('background-color','#dedede'); $('#putr' + GRID).css('background-color','#dedede'); }, function() { $( this ).css('background-color','#fff'); $('#putr' + GRID).css('background-color','#fff'); } );\n" +
"                $.each(json.USER, function() {\n" +
"                    if(c == 0) {\n" +
"                        $('#UserMgmntUsGrList2 table tr#tuhd').append('<th>' + base64_decode( this.USER_DC ) + '</th>');\n" +
"                    }" +
"                    $('#UserMgmntUsGrList2 table tr#rutr' + GRID ).append('<td><img id=\"GrID' + GRID + '_UsID' + this.USER_ID + '\" onclick=\"UpdateUserGroup(\\'' + this.USER_ID + '\\',\\'' + GRID + '\\');\" src=\"public/images/cross-circle.png\" title=\"' + base64_decode( this.USER_DC ) + ' dieser Gruppe zuweisen\"/></td>');\n" +
"                });\n" +
"                c++;" +
"            });\n" +
"            $.each(json.USER, function() {\n" +
"                var USERID = this.USER_ID;\n" +                    
"                for(var i=0;i<this.USER_GROUP.length;i++) {\n" +
"                    $('#GrID' + this.USER_GROUP[i] + '_UsID' + USERID).attr('src','public/images/accept.png');\n" +                    
"                    $('#GrID' + this.USER_GROUP[i] + '_UsID' + USERID).attr('title',base64_decode( this.USER_DC ) + ' diser Gruppe entziehen');\n" + 
"                };\n" +
"            });\n" +
"        },\n" +
"        dataType: 'json',\n" +
"        cache: false\n" +
"    });\n" +
"}\n"); 

            out.println("\n" +
"function AddEntry(mod,id,nm) {\n" +
"    if ( document.getElementById(id).value == '' ) {\n" +
"        $('#' + id).css('border','1px solid #FF6969');\n" +
"        alert('Der Wert ist nicht korrekt eingetragen.');\n" +
"    } else if ( document.getElementById(nm).value == '' ) {\n" +
"        $('#' + nm).css('border','1px solid #FF6969');\n" +
"        alert('Der Wert ist nicht korrekt eingetragen.');\n" +
"    } else {\n" +
"        $.ajax({\n" +
"            url: '/gateway/exec/AddEntry?mod=' + base64_encode( mod ) + '&id=' + base64_encode( $('#' + id).val() ) + '&desc=' + base64_encode( $('#' + nm).val() ) ,\n" +
"            crossDomain: true,\n" +
"            success: function() {\n" +
"                DialogSuccess('#Dialog','Der Eintrag wurde erfolgreich gesetzt.');\n" +
"                FillUserManagement();\n" +
"                FillUserManagementUsGr();\n" +
"                FillUserManagementRoPri();\n" +
"                FillUserManagementGrRo();\n" +
"                $('#' + id).css('border','1px solid #82abcc');\n" +
"                $('#' + nm).css('border','1px solid #82abcc');\n" +
"            },\n" +
"            dataType: 'json',\n" +
"            cache: false\n" +
"        });\n" +
"    }\n" +
"}\n");
            
            out.println("\n" +
"function DeleteEntry(mod,id) {\n" +
"    $.ajax({\n" +
"        url: '/gateway/exec/DeleteEntry?mod=' + base64_encode( mod ) + '&id=' + base64_encode( id ),\n" +
"        crossDomain: true,\n" +
"        success: function() {\n" +
"            DialogSuccess('#Dialog','Der Eintrag wurde erfolgreich entfernt.');\n" +
"            FillUserManagement();\n" +
"            FillUserManagementUsGr();\n" +
"            FillUserManagementRoPri();\n" +
"            FillUserManagementGrRo();\n" +
"        },\n" +
"        dataType: 'json',\n" +
"        cache: false\n" +
"    });\n" +
"}\n");
            
            out.println("\n" +
"function ActivateUser(mod,id) {\n" +
"    $.ajax({\n" +
"        url: '/gateway/exec/ActivateUser?mod=' + base64_encode( mod ) + '&id=' + base64_encode( id ),\n" +
"        crossDomain: true,\n" +
"        success: function() {\n" +
"            DialogSuccess('#Dialog','Der User wurde erfolgreich angepasst.');\n" +
"            FillUserManagement();\n" +
"        },\n" +
"        dataType: 'json',\n" +
"        cache: false\n" +
"    });\n" +
"}\n");

            out.println("\n" +
"function UpdateRolePriv(lrid,prid) {\n" +
"    $.ajax({\n" +
"        url: '/gateway/exec/UpdateRolePriv?rlid=' + lrid + '&prid=' + prid,\n" +
"        crossDomain: true,\n" +
"        success: function() {\n" +
"            DialogSuccess('#Dialog','Die Rolle wurde erfolgreich angepasst.');\n" +
"            FillUserManagementRoPri();\n" +
"        },\n" +
"        dataType: 'json',\n" +
"        cache: false\n" +
"    });\n" +
"}\n");

            out.println("\n" +
"function UpdateGroupRole(grid,rlid) {\n" +
"    $.ajax({\n" +
"        url: '/gateway/exec/UpdateGroupRole?grid=' + grid + '&rlid=' + rlid,\n" +
"        crossDomain: true,\n" +
"        success: function() {\n" +
"            DialogSuccess('#Dialog','Die Gruppe wurde erfolgreich angepasst.');\n" +
"            FillUserManagementGrRo();\n" +
"        },\n" +
"        dataType: 'json',\n" +
"        cache: false\n" +
"    });\n" +
"}\n");
            
            out.println("\n" +
"function UpdateCustomerRole(cuid,rlid) {\n" +
"    $.ajax({\n" +
"        url: '/gateway/exec/UpdateCustomerRole?cuid=' + cuid + '&rlid=' + rlid,\n" +
"        crossDomain: true,\n" +
"        success: function() {\n" +
"            DialogSuccess('#Dialog','Der Kunde wurde erfolgreich angepasst.');\n" +
"            FillUserManagementCuRo();\n" +
"        },\n" +
"        dataType: 'json',\n" +
"        cache: false\n" +
"    });\n" +
"}\n");
            
            out.println("\n" +
"function UpdateContractRole(ccid,rlid) {\n" +
"    $.ajax({\n" +
"        url: '/gateway/exec/UpdateContractRole?ccid=' + ccid + '&rlid=' + rlid,\n" +
"        crossDomain: true,\n" +
"        success: function() {\n" +
"            DialogSuccess('#Dialog','Der Vertrag wurde erfolgreich angepasst.');\n" +
"            FillUserManagementCoRo();\n" +
"        },\n" +
"        dataType: 'json',\n" +
"        cache: false\n" +
"    });\n" +
"}\n");
            
            out.println("\n" +
"function UpdateUserGroup(uuid,grid) {\n" +
"    $.ajax({\n" +
"        url: '/gateway/exec/UpdateUserGroup?uuid=' + uuid + '&grid=' + grid,\n" +
"        crossDomain: true,\n" +
"        success: function() {\n" +
"            DialogSuccess('#Dialog','Der Nutzer wurde erfolgreich angepasst.');\n" +
"            FillUserManagementUsGr();\n" +
"        },\n" +
"        dataType: 'json',\n" +
"        cache: false\n" +
"    });\n" +
"}\n");
            
            }
                        
            
            if(CFGM == 1) {

            out.println("\n" +
"function GetMailConfig() {\n" +
"    $.ajax({\n" +
"        url: '/gateway/exec/GetMailConfig',\n" +
"        crossDomain: true,\n" +
"        success: function(json) {\n" +
"            $('#MailH div.jqte_editor').html(base64_decode( json.HEADER )); \n" +
"            $('#MailF div.jqte_editor').html(base64_decode( json.FOOTER )); \n" +
"        },\n" +
"        dataType: 'json',\n" +
"        cache: false\n" +
"    });\n" +
"}\n" +
            "\n");                
                
            out.println("\n" +
"function AddMailConfig(id,key) {\n" +
"    var val = base64_encode( $('#' + id + ' div.jqte_editor').html() ).replace(/\\+/g,'78');\n" +
"    $.ajax({\n" +
"        url: '/gateway/exec/AddMailConfig?key=' + base64_encode( key ) + '&val=' + val,\n" +
"        crossDomain: true,\n" +
"        success: function(json) {\n" +
"            if (json.ADD == \"1\") {\n" +
"                DialogMailComplete(\"#Dialog\",\"Mail Konfiguration hinzugef&uuml;gt\",\"Mail Konfiguration wurde erfolgreich hinzugef&uuml;gt.\");\n" +
"            } else {\n" +
"                DialogMailComplete(\"#Dialog\",\"+++ Fehler beim hinzuf&uuml;gen der Mail Konfiguration +++\",\"<font color=red>Die Mail Konfiguration konnte NICHT hinzugef&uuml;gt werden.</font>\");\n" +
"            }\n" +
"        },\n" +
"        dataType: 'json',\n" +
"        cache: false\n" +
"    });\n" +
"}\n" +
            "\n");
            
            }

            if(MSREP == 1 && MSVAB == 1) {
            
            out.println("\n" +
"function GetLastPageComment() {\n" +
"    $.ajax({\n" +
"        url: '/gateway/reporting/GetLastPageComment',\n" +
"        crossDomain: true,\n" +
"        success: function(json) {\n" +
"            $('#LastPageContent').val(base64_decode(json.COMMENT));\n" +
"        },\n" +
"        dataType: 'json',\n" +
"        cache: false\n" +
"    });\n" +
"}\n");    
            
            out.println("\n" +
"function UpdateConfigReporting(id,key) {\n" +
"    var val = base64_encode( $('#' + id + '').val() ).replace(/\\+/g,'78');\n" +
"    $.ajax({\n" +
"        url: '/gateway/reporting/UpdateConfigReporting?key=' + base64_encode( key ) + '&val=' + val,\n" +
"        crossDomain: true,\n" +
"        success: function(json) {\n" +
"            if (json.ADD == \"1\") {\n" +
"                DialogMailComplete(\"#Dialog\",\"Reporting Konfiguration hinzugef&uuml;gt\",\"Reporting Konfiguration wurde erfolgreich aktualisiert.\");\n" +
"            } else {\n" +
"                DialogMailComplete(\"#Dialog\",\"+++ Fehler beim hinzuf&uuml;gen der Reporting Konfiguration +++\",\"<font color=red>Die Reporting Konfiguration konnte NICHT aktualisiert werden.</font>\");\n" +
"            }\n" +
"        },\n" +
"        dataType: 'json',\n" +
"        cache: false\n" +
"    });\n" +
"}\n" +
                "\n");    
            
            out.println("\n" +
"function GetLastPageContactsComment() {\n" +
"    $.ajax({\n" +
"        url: '/gateway/reporting/GetLastPageContactsComment',\n" +
"        crossDomain: true,\n" +
"        success: function(json) {\n" +
"            $('#LastPageContactsContent').val(base64_decode(json.COMMENT));\n" +
"        },\n" +
"        dataType: 'json',\n" +
"        cache: false\n" +
"    });\n" +
"}\n");  
            
            }
            
            if(MO == 1) {
            
            out.println("\n" +
"function FillUserManagementHoCu() {\n" +
"    $.ajax({\n" +
"        url: '/gateway/monitoring/GetHostCustomer',\n" +
"        crossDomain: true,\n" +
"        success: function(json) {\n" +
"            var c=0;" +
"            $('#UserMgmntHoCuList1 table').html('<tr id=\"tuhocu\"><th>Kunde:</th></tr>');\n" +
"            $('#UserMgmntHoCuList2 table').html('<tr id=\"tuhocu\"></tr>');\n" +
"            $.each(json.CUSTOMER, function() {\n" +
"                var CUID = this.CUID;\n" +
"                $('#UserMgmntHoCuList1 table').append('<tr id=\"puhocu' + CUID + '\" title=\"' + base64_decode( this.CUNM ) + '\"><td>' + base64_decode( this.CUNR ) + '</td></tr>');\n" +
"                $('#UserMgmntHoCuList2 table').append('<tr id=\"ruhocu' + CUID + '\"></tr>');\n" +
"                $('#puhocu' + CUID).hover( function() { $( this ).css('background-color','#dedede'); $('#ruhocu' + CUID).css('background-color','#dedede'); }, function() { $( this ).css('background-color','#fff'); $('#ruhocu' + CUID).css('background-color','#fff'); } );\n" +
"                $('#ruhocu' + CUID).hover( function() { $( this ).css('background-color','#dedede'); $('#puhocu' + CUID).css('background-color','#dedede'); }, function() { $( this ).css('background-color','#fff'); $('#puhocu' + CUID).css('background-color','#fff'); } );\n" +
"                $.each(json.HOST, function() {\n" +
"                    if(c == 0) {\n" +
"                        $('#UserMgmntHoCuList2 table tr#tuhocu').append('<th title=\"' + base64_decode( this.HOST_NM ) + '\">' + this.HOST_ID + '</th>');\n" +
"                    }" +
"                    $('#UserMgmntHoCuList2 table tr#ruhocu' + CUID ).append('<td><img id=\"CuID' + CUID + '_HtID' + this.HOST_ID + '\" onclick=\"UpdateHostCustomer(\\'' + this.HOST_ID + '\\',\\'' + CUID + '\\');\" src=\"public/images/cross-circle.png\" title=\"' + base64_decode( this.HOST_NM ) + ' diesem Kunden zuweisen\"/></td>');\n" +
"                });\n" +
"                c++;" +
"            });\n" +
"            $.each(json.HOST, function() {\n" +
"                var HOSTID = this.HOST_ID;\n" +                    
"                for(var i=0;i<this.HOST_GROUP.length;i++) {\n" +
"                    $('#CuID' + this.HOST_GROUP[i] + '_HtID' + HOSTID).attr('src','public/images/accept.png');\n" +                    
"                    $('#CuID' + this.HOST_GROUP[i] + '_HtID' + HOSTID).attr('title',base64_decode( this.HOST_NM ) + ' diesem Kunden entziehen');\n" + 
"                };\n" +
"            });\n" +
"        },\n" +
"        dataType: 'json',\n" +
"        cache: false\n" +
"    });\n" +
"}\n"); 

            out.println("\n" +
"function FillUserManagementHoCo() {\n" +
"    $.ajax({\n" +
"        url: '/gateway/monitoring/GetHostContract',\n" +
"        crossDomain: true,\n" +
"        success: function(json) {\n" +
"            var c=0;" +
"            $('#UserMgmntHoCoList1 table').html('<tr id=\"tuhoco\"><th>Vertrag:</th></tr>');\n" +
"            $('#UserMgmntHoCoList2 table').html('<tr id=\"tuhoco\"></tr>');\n" +
"            $.each(json.CONTRACT, function() {\n" +
"                var CCID = this.CCID;\n" +
"                $('#UserMgmntHoCoList1 table').append('<tr id=\"puhoco' + CCID + '\"><td title=\"' + base64_decode( this.CCNM ) + ' von ' + base64_decode( this.CUNM ) + '\">' + base64_decode( this.CCNR ) + '</td></tr>');\n" +
"                $('#UserMgmntHoCoList2 table').append('<tr id=\"ruhoco' + CCID + '\"></tr>');\n" +
"                $('#puhoco' + CCID).hover( function() { $( this ).css('background-color','#dedede'); $('#ruhoco' + CCID).css('background-color','#dedede'); }, function() { $( this ).css('background-color','#fff'); $('#ruhoco' + CCID).css('background-color','#fff'); } );\n" +
"                $('#ruhoco' + CCID).hover( function() { $( this ).css('background-color','#dedede'); $('#puhoco' + CCID).css('background-color','#dedede'); }, function() { $( this ).css('background-color','#fff'); $('#puhoco' + CCID).css('background-color','#fff'); } );\n" +
"                $.each(json.HOST, function() {\n" +
"                    if(c == 0) {\n" +
"                        $('#UserMgmntHoCoList2 table tr#tuhoco').append('<th title=\"' + base64_decode( this.HOST_NM ) + '\">' + this.HOST_ID + '</th>');\n" +
"                    }" +
"                    $('#UserMgmntHoCoList2 table tr#ruhoco' + CCID ).append('<td><img id=\"CcID' + CCID + '_HtID' + this.HOST_ID + '\" onclick=\"UpdateHostContract(\\'' + this.HOST_ID + '\\',\\'' + CCID + '\\');\" src=\"public/images/cross-circle.png\" title=\"' + base64_decode( this.HOST_NM ) + ' diesem Vertrag zuweisen\"/></td>');\n" +
"                });\n" +
"                c++;" +
"            });\n" +
"            $.each(json.HOST, function() {\n" +
"                var HOSTID = this.HOST_ID;\n" +                    
"                for(var i=0;i<this.CONTRACTS.length;i++) {\n" +
"                    $('#CcID' + this.CONTRACTS[i] + '_HtID' + HOSTID).attr('src','public/images/accept.png');\n" +                    
"                    $('#CcID' + this.CONTRACTS[i] + '_HtID' + HOSTID).attr('title',base64_decode( this.HOST_NM ) + ' diesem Vertrag entziehen');\n" + 
"                };\n" +
"            });\n" +
"        },\n" +
"        dataType: 'json',\n" +
"        cache: false\n" +
"    });\n" +
"}\n"); 

            out.println("\n" +
"function FillUserManagementHoRo() {\n" +
"    $.ajax({\n" +
"        url: '/gateway/monitoring/GetHostRole',\n" +
"        crossDomain: true,\n" +
"        success: function(json) {\n" +
"            var c=0;" +
"            $('#UserMgmntHoRoList1 table').html('<tr id=\"tuhoro\"><th>Rolle:</th></tr>');\n" +
"            $('#UserMgmntHoRoList2 table').html('<tr id=\"tuhoro\"></tr>');\n" +
"            $.each(json.ROLE, function() {\n" +
"                var ROID = this.ROID;\n" +
"                $('#UserMgmntHoRoList1 table').append('<tr id=\"puhoro' + ROID + '\"><td>' + base64_decode( this.RODC ) + '<br>(' + base64_decode( this.RONM ) + ')</td></tr>');\n" +
"                $('#UserMgmntHoRoList2 table').append('<tr id=\"ruhoro' + ROID + '\"></tr>');\n" +
"                $('#puhoro' + ROID).hover( function() { $( this ).css('background-color','#dedede'); $('#ruhoro' + ROID).css('background-color','#dedede'); }, function() { $( this ).css('background-color','#fff'); $('#ruhoro' + ROID).css('background-color','#fff'); } );\n" +
"                $('#ruhoro' + ROID).hover( function() { $( this ).css('background-color','#dedede'); $('#puhoro' + ROID).css('background-color','#dedede'); }, function() { $( this ).css('background-color','#fff'); $('#puhoro' + ROID).css('background-color','#fff'); } );\n" +
"                $.each(json.HOST, function() {\n" +
"                    if(c == 0) {\n" +
"                        $('#UserMgmntHoRoList2 table tr#tuhoro').append('<th title=\"' + base64_decode( this.HOST_NM ) + '\">' + this.HOST_ID + '</th>');\n" +
"                    }" +
"                    $('#UserMgmntHoRoList2 table tr#ruhoro' + ROID ).append('<td><img id=\"RoID' + ROID + '_HtID' + this.HOST_ID + '\" onclick=\"UpdateHostRole(\\'' + this.HOST_ID + '\\',\\'' + ROID + '\\');\" src=\"public/images/cross-circle.png\" title=\"' + base64_decode( this.HOST_NM ) + ' dieser Rolle zuweisen\"/></td>');\n" +
"                });\n" +
"                c++;" +
"            });\n" +
"            $.each(json.HOST, function() {\n" +
"                var HOSTID = this.HOST_ID;\n" +                    
"                for(var i=0;i<this.ROLE.length;i++) {\n" +
"                    $('#RoID' + this.ROLE[i] + '_HtID' + HOSTID).attr('src','public/images/accept.png');\n" +                    
"                    $('#RoID' + this.ROLE[i] + '_HtID' + HOSTID).attr('title','' + base64_decode( this.HOST_NM ) + ' dieser Rolle entziehen');\n" + 
"                };\n" +
"            });\n" +
"        },\n" +
"        dataType: 'json',\n" +
"        cache: false\n" +
"    });\n" +
"}\n");
            
            out.println("\n" +
"function UpdateHostRole(hstid,rlid) {\n" +
"    $.ajax({\n" +
"        url: '/gateway/monitoring/UpdateHostRole?hstid=' + hstid + '&rlid=' + rlid,\n" +
"        crossDomain: true,\n" +
"        success: function() {\n" +
"            DialogSuccess('#Dialog','Der Host wurde erfolgreich angepasst.');\n" +
"            FillUserManagementHoRo();\n" +
"        },\n" +
"        dataType: 'json',\n" +
"        cache: false\n" +
"    });\n" +
"}\n");
            
            out.println("\n" +
"function UpdateHostCustomer(hstid,cuid) {\n" +
"    $.ajax({\n" +
"        url: '/gateway/monitoring/UpdateHostCustomer?hstid=' + hstid + '&cuid=' + cuid,\n" +
"        crossDomain: true,\n" +
"        success: function() {\n" +
"            DialogSuccess('#Dialog','Der Host wurde erfolgreich angepasst.');\n" +
"            FillUserManagementHoCu();\n" +
"        },\n" +
"        dataType: 'json',\n" +
"        cache: false\n" +
"    });\n" +
"}\n");
            
            out.println("\n" +
"function UpdateHostContract(hstid,ccid) {\n" +
"    $.ajax({\n" +
"        url: '/gateway/monitoring/UpdateHostContract?hstid=' + hstid + '&ccid=' + ccid,\n" +
"        crossDomain: true,\n" +
"        success: function() {\n" +
"            DialogSuccess('#Dialog','Der Host wurde erfolgreich angepasst.');\n" +
"            FillUserManagementHoCo();\n" +
"        },\n" +
"        dataType: 'json',\n" +
"        cache: false\n" +
"    });\n" +
"}\n");

            }
            
        }
        
        
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, FileNotFoundException {
        try {
            processRequest(request, response, request.getRemoteUser());
        } catch (NamingException ex) {
            Logger.getLogger(UserBasics.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserBasics.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //
    }
}
