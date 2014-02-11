/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.siv.script;

import de.siv.modules.*;
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
"                    url: '/gateway/exec/AddDashboardLink?uuid=' + $.base64.encode( UUID ) + '&title=' + $.base64.encode( $('#DaBTitle').val() ) + '&desc=' + $.base64.encode( $('#DaBDesc').val() ) + '&target=' + $.base64.encode( $('#DaBTarget').val() ),\n" +
"                    crossDomain: true,\n" +
"                    success: function(json) {\n" +
"                        if (json.ADD == '1') {\n" +
"                            DialogMailComplete('#UserProfileSuccess','Dashboard Link hinzugef&uuml;gt','Dashboard Link wurde erfolgreich hinzugef&uuml;gt.');\n" +
"                        } else {\n" +
"                            DialogMailComplete('#UserProfileSuccess','+++ Fehler beim hinzuf&uuml;gen des Dashboard Links +++','<font color=red>Der Dashboard Link konnte NICHT hinzugef&uuml;gt werden.</font>');\n" +
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
        
        if(Executions.UserIsPermitted(Uid,"config")) {
        
            out.println("" +
"function Configuration(uid) {\n" +
"    var b64uid = $.base64.encode( uid );\n" +
"    /* Dialog format start */\n" +
"    $('#Dialog').append('<div id=\"ConfigurationDialog\" title=\"Einstellungen\">" +
"        <div id=\"ConfigurationTabs\">" +
"            <ul>\\n\\");
            
            if(Executions.UserIsPermitted(Uid,"config_web")) {
                out.println("                <li><a href=\"#ConfigurationTabs1\">Web-Konfiguration</a></li>\\n\\");
            }
            
            if(Executions.UserIsPermitted(Uid,"config_usermanagement")) {
                out.println("                <li><a href=\"#ConfigurationTabs2\">Nutzerverwaltung</a></li>\\n\\");
                out.println("                <li><a href=\"#ConfigurationTabs3\">Rechteverwaltung</a></li>\\n\\");
                out.println("                <li><a href=\"#ConfigurationTabs4\">Berechtigungen</a></li>\\n\\");
            }
            
            if(Executions.UserIsPermitted(Uid,"config_mail")) {
                out.println("                <li><a href=\"#ConfigurationTabs5\">Mail-Format</a></li>\\n\\");
            }
        
            out.println("            </ul>\\n\\");
            
            if(Executions.UserIsPermitted(Uid,"config_web")) {
            
            out.println("            <div id=\"ConfigurationTabs1\">" +
"                <div id=\"ConfigurationSection\">" +
"                    <div id=\"ConfigurationSectionTitle\">Reset</div>" +
"                    <button id=\"2\" class=\"ConfigurationSectionPoint\" onclick=\"DeleteBasic(\\'' + b64uid + 'Ljd84K\\');\">Dashboard Zur&uuml;cksetzen</button>" +
"                    <button id=\"3\" class=\"ConfigurationSectionPoint\" onclick=\"DeleteBasicConfig(\\'' + b64uid + 'Ljd84K\\');\">Alle Einstellungen zur&uuml;cksetzen</button>" +
"                </div>" +
"                <div id=\"ConfigurationSection\">" +
"                    <div id=\"ConfigurationSectionTitle\">Anzeige Einstellungen</div>" +
"                    <div class=\"Config\"></div>" +
"                </div>" +
"                <div id=\"ConfigurationSection\">" +
"                    <div id=\"ConfigurationSectionTitle\">Mailing, Standard CC Empf&auml;nger</div>" +
"                    <div id=\"MailCC\"><span>Mailadressen CC: (Mail1,Mail2,Mailn)</span><input type=text id=\"InMailCC\"/><button onclick=\"AddMailingConfig(\\'InMailCC\\',\\'CC\\');\">Festlegen</button></div>" +
"                </div>" +
"                <div id=\"ConfigurationSection\">" +
"                    <div id=\"ConfigurationSectionTitle\">Mailing, Eskalationsmanagement</div>" +
"                    <div id=\"MailEsk1\"><span>Mailadressen Eskalationsstufe 1: (Mail1,Mail2,Mailn)</span><input type=text id=\"InMailEsk1\"/><button onclick=\"AddMailingConfig(\\'InMailEsk1\\',\\'ESK1\\');\">Festlegen</button></div>" +
"                    <div id=\"MailEsk2\"><span>Mailadressen Eskalationsstufe 2: (Mail1,Mail2,Mailn)</span><input type=text id=\"InMailEsk2\"/><button onclick=\"AddMailingConfig(\\'InMailEsk2\\',\\'ESK2\\');\">Festlegen</button></div>" +
"                    <div id=\"MailEsk3\"><span>Mailadressen Eskalationsstufe 3: (Mail1,Mail2,Mailn)</span><input type=text id=\"InMailEsk3\"/><button onclick=\"AddMailingConfig(\\'InMailEsk3\\',\\'ESK3\\');\">Festlegen</button></div>" +
"                </div>" +
"                <div id=\"ConfigurationSection\">" +
"                    <div id=\"ConfigurationSectionTitle\">Mailing, Signatur</div>" +
"                    <div id=\"MailSG\"><div id=\"MailSGContent\"></div><button onclick=\"AddMailingSignature(\\'MailSG\\',\\'SIGN\\');\">Festlegen</button></div>" +
"                </div>" +
"            </div>\\n\\");
            
            }
            
            if(Executions.UserIsPermitted(Uid,"config_usermanagement")) {
            
            out.println("            <div id=\"ConfigurationTabs2\">" +
"                <div id=\"ConfigurationSection\">" +
"                    <div id=\"ConfigurationSectionTitle\">Nutzer</div>" +
"                    <div id=\"UserMgmntUserMenu\"><table><tr><td>UsID:</td></tr><tr><td><input type=\"text\" value=\"mmustermann\" id=\"UMUMuid\" onclick=\"ClearValue(\\'UMUMuid\\');\"/></td></tr><tr><td>Name:</td></tr><tr><td><input type=\"text\" value=\"Max Mustermann\" id=\"UMUMusnm\" onclick=\"ClearValue(\\'UMUMusnm\\');\"/></td></tr></table><button id=\"UserMgmntUserAdd\" onclick=\"AddEntry(\\'USER\\',\\'UMUMuid\\',\\'UMUMusnm\\');\">Nutzer Anlegen</button></div>" +
"                    <div id=\"UserMgmntUserList\"><table></table></div>" +
"                </div>" +
"                <div id=\"ConfigurationSection\">" +
"                    <div id=\"ConfigurationSectionTitle\">Gruppen</div>" +
"                    <div id=\"UserMgmntGroupMenu\"><table><tr><td>GrID:</td></tr><tr><td><input type=\"text\" value=\"admin\" id=\"UMGMgrid\" onclick=\"ClearValue(\\'UMGMgrid\\');\"/></td></tr><tr><td>Name:</td></tr><tr><td><input type=\"text\" value=\"Administrator\" id=\"UMGMgrnm\" onclick=\"ClearValue(\\'UMGMgrnm\\');\"/></td></tr></table><button id=\"UserMgmntGroupAdd\" onclick=\"AddEntry(\\'GROUP\\',\\'UMGMgrid\\',\\'UMGMgrnm\\');\">Gruppe Anlegen</button></div>" +
"                    <div id=\"UserMgmntGroupList\"><table></table></div>" +
"                </div>" +
"            </div>\\n\\");
                 
            out.println("            <div id=\"ConfigurationTabs3\">" +
"                <div id=\"ConfigurationSection\">" +
"                    <div id=\"ConfigurationSectionTitle\">Rollen</div>" +
"                    <div id=\"UserMgmntRoleMenu\"><table><tr><td>RoID:</td></tr><tr><td><input type=\"text\" value=\"full\" id=\"UMRMrlid\" onclick=\"ClearValue(\\'UMRMrlid\\');\"/></td></tr><tr><td>Beschreibung:</td></tr><tr><td><input type=\"text\" value=\"Vollzugriff\" id=\"UMRMrlnm\" onclick=\"ClearValue(\\'UMRMrlnm\\');\"/></td></tr></table><button id=\"UserMgmntRoleAdd\" onclick=\"AddEntry(\\'ROLE\\',\\'UMRMrlid\\',\\'UMRMrlnm\\');\">Rolle Anlegen</button></div>" +
"                    <div id=\"UserMgmntRoleList\"><table></table></div>" +
"                </div>" +
"                <div id=\"ConfigurationSection\">" +
"                    <div id=\"ConfigurationSectionTitle\">Privilegien</div>" +
"                    <div id=\"UserMgmntPrivMenu\"><table><tr><td>PrID:</td></tr><tr><td><input type=\"text\" value=\"list_hosts\" id=\"UMPMprid\" onclick=\"ClearValue(\\'UMPMprid\\');\"/></td></tr><tr><td>Beschreibung:</td></tr><tr><td><input type=\"text\" value=\"Liste alle Hosts\" id=\"UMPMprnm\" onclick=\"ClearValue(\\'UMPMprnm\\');\"/></td></tr></table><button id=\"UserMgmntPrivAdd\" onclick=\"AddEntry(\\'PRIV\\',\\'UMPMprid\\',\\'UMPMprnm\\');\">Privileg Anlegen</button></div>" +
"                    <div id=\"UserMgmntPrivList\"><table></table></div>" +
"                </div>" +
"            </div>\\n\\");

            out.println("            <div id=\"ConfigurationTabs4\">" +
"                <div id=\"ConfigurationSection\">" +
"                    <div id=\"ConfigurationSectionTitle\">Nutzer zu Gruppe</div>" +
"                    <div id=\"UserMgmntUsGrList\"><span id=\"UserMgmntUsGrList1\"><table></table></span><span id=\"UserMgmntUsGrList2\"><table></table></span></div>" +
"                </div>" +
"                <div id=\"ConfigurationSection\">" +
"                    <div id=\"ConfigurationSectionTitle\">Gruppe zu Rolle</div>" +
"                    <div id=\"UserMgmntGrRoList\"><span id=\"UserMgmntGrRoList1\"><table></table></span><span id=\"UserMgmntGrRoList2\"><table></table></span></div>" +
"                </div>" +
"                <div id=\"ConfigurationSection\">" +
"                    <div id=\"ConfigurationSectionTitle\">Rolle zu Privileg</div>" +
"                    <div id=\"UserMgmntRoPrList\"><span id=\"UserMgmntRoPrList1\"><table></table></span><span id=\"UserMgmntRoPrList2\"><table></table></span></div>" +
"                </div>" +
"            </div>\\n\\");
            
            }
            
            if(Executions.UserIsPermitted(Uid,"config_mail")) {

            out.println("            <div id=\"ConfigurationTabs5\">" +
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
            
            out.println("       </div>" +
"   </div>');\n" +
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
"    $('input[type=text]').addClass('ui-input-hofo');\n" +
"\n" +
"    /* Dialog open */\n" +
"    $('#ConfigurationDialog').dialog({\n" +
"	autoOpen: true,\n" +
"	height: 900,\n" +
"	width: 1200,\n" +
"	draggable: false,\n" +
"	resizable: false,\n" +
"	modal: true,\n" +
"        open: function() {\n" +
/*"            $.ajax({\n" +
"                url: 'http://' + Backend + '/repo/json/?e=1&m=U2VsZWN0Q29uZmlnJk8Uhg&u=' + b64uid + 'KjHu8s&m2=Q29uZmlnJq0OpP',\n" +
"                crossDomain: true,\n" +
"                success: function(json) {\n" +
"                    $('div.Config').append('<table id=\"TableConfig\" cellpadding=0 cellspacing=5 border=0></table>');\n" +
"                    $.each(json, function(key,value) {\n" +
"                        if ( value.ACTION == 0 ) {\n" +
"                            $('table#TableConfig').append('<tr><td>' + value.DESC + '</td><td></td><td><div id=\"radio' + value.KEY + '\" class=\"RadioBorder\"><input type=\"radio\" id=\"radio1' + value.KEY + '\" name=\"radio' + value.KEY + '\" onclick=\"AddConfig(\\'' + b64uid + 'Ljd84K\\',\\'Config\\',\\'' + value.KEY + '\\',\\'1\\',\\'' + value.DESC + '\\',\\'\\');\" /><label for=\"radio1' + value.KEY + '\">ON</label><input type=\"radio\" id=\"radio2' + value.KEY + '\" name=\"radio' + value.KEY + '\" checked=\"checked\" onclick=\"AddConfig(\\'' + b64uid + 'Ljd84K\\',\\'Config\\',\\'' + value.KEY + '\\',\\'0\\',\\'' + value.DESC + '\\',\\'\\');\" /><label for=\"radio2' + value.KEY + '\">OFF</label></div></td></tr>');\n" +
"                        } else {\n" +
"                            $('table#TableConfig').append('<tr><td>' + value.DESC + '</td><td></td><td><div id=\"radio' + value.KEY + '\" class=\"RadioBorder\"><input type=\"radio\" id=\"radio1' + value.KEY + '\" name=\"radio' + value.KEY + '\" onclick=\"AddConfig(\\'' + b64uid + 'Ljd84K\\',\\'Config\\',\\'' + value.KEY + '\\',\\'1\\',\\'' + value.DESC + '\\',\\'\\');\" checked=\"checked\" /><label for=\"radio1' + value.KEY + '\">ON</label><input type=\"radio\" id=\"radio2' + value.KEY + '\" name=\"radio' + value.KEY + '\" onclick=\"AddConfig(\\'' + b64uid + 'Ljd84K\\',\\'Config\\',\\'' + value.KEY + '\\',\\'0\\',\\'' + value.DESC + '\\',\\'\\');\" /><label for=\"radio2' + value.KEY + '\">OFF</label></div></td></tr>');\n" +
"                        }\n" +
"                        $('#radio' + value.KEY ).buttonset();\n" +
"                    });\n" +
"                },\n" +
"                dataType: 'json',\n" +
"                cache: false\n" +
"            });\n");*/
"\n");
                    
            if(Executions.UserIsPermitted(Uid,"config_usermanagement")) {

                out.println("" +
"            FillUserManagement();\n" +
"            FillUserManagementUsGr();\n" +
"            FillUserManagementRoPri();\n" +
"            FillUserManagementGrRo();\n");
        
            }
            
            if(Executions.UserIsPermitted(Uid,"config_web")) {
                
                out.println("" +
"             for (var key in Mailing) {\n" +
"                 var obj = Mailing[key];\n" +
"                 for (var prop in obj) {" +
"                     if(obj.hasOwnProperty(prop)) {\n" +
"                         if (prop == 'CC') {\n" +
"                             $('#InMailCC').val($.base64.decode( obj[prop] ));\n" +
"                         } else if (prop == 'ESK1') {\n" +
"                             $('#InMailEsk1').val($.base64.decode( obj[prop] ));\n" +
"                         } else if (prop == 'ESK2') {\n" +
"                             $('#InMailEsk2').val($.base64.decode( obj[prop] ));\n" +
"                         } else if (prop == 'ESK3') {\n" +
"                             $('#InMailEsk3').val($.base64.decode( obj[prop] ));\n" +
"                         } else if (prop == 'SIGN') {\n" +
"                             //$('#MailSGContent').val($.base64.decode( obj[prop] ));\n" +
"                             $('#MailSG div.jqte_editor').html($.base64.decode( obj[prop] ));\n" +
"                         }\n" +
"                     }\n" +
"                 }" +
"             }\n");
            
            }

            if(Executions.UserIsPermitted(Uid,"config_mail")) {

                out.println("" +
"            GetMailConfig();\n");
        
            }
            
            out.println("        },\n" +
"        buttons: { \n" +
"            BEENDEN: function() {\n" +
"                $(this).dialog('close');\n" +
"		 $('#ConfigurationDialog').remove();\n" +
"                location.reload();\n" +
"            }\n" +
"	}\n" +
"    }).parent().find('.ui-dialog-titlebar-close').hide();\n" +
"}");
            
            
            if(Executions.UserIsPermitted(Uid,"config_web")) {
                
                out.println("\n" +
"function AddMailingConfig(id,key) {\n" +
"    $.ajax({\n" +
"        url: '/gateway/exec/AddMailingConfig?uuid=' + $.base64.encode( UUID ) + '&key=' + $.base64.encode( key ) + '&val=' + $.base64.encode( $('#' + id).val() ),\n" +
"        crossDomain: true,\n" +
"        success: function(json) {\n" +
"            if (json.ADD == \"1\") {\n" +
"                DialogMailComplete(\"#UserProfileSuccess\",\"Mailing Konfiguration hinzugef&uuml;gt\",\"Mailing Konfiguration wurde erfolgreich hinzugef&uuml;gt.\");\n" +
"            } else {\n" +
"                DialogMailComplete(\"#UserProfileSuccess\",\"+++ Fehler beim hinzuf&uuml;gen der Mailing Konfiguration +++\",\"<font color=red>Die Mailing Konfiguration konnte NICHT hinzugef&uuml;gt werden.</font>\");\n" +
"            }\n" +
"        },\n" +
"        dataType: 'json',\n" +
"        cache: false\n" +
"    });\n" +
"}\n" +
                "\n");

                out.println("\n" +
"function AddMailingSignature(id,key) {\n" +
"    var val = $.base64.encode( $('#' + id + ' div.jqte_editor').html() ).replace(/\\+/g,'78');\n" +
"    $.ajax({\n" +
"        url: '/gateway/exec/AddMailingConfig?uuid=' + $.base64.encode( UUID ) + '&key=' + $.base64.encode( key ) + '&val=' + val,\n" +
"        crossDomain: true,\n" +
"        success: function(json) {\n" +
"            if (json.ADD == \"1\") {\n" +
"                DialogMailComplete(\"#UserProfileSuccess\",\"Mailing Konfiguration hinzugef&uuml;gt\",\"Mailing Konfiguration wurde erfolgreich hinzugef&uuml;gt.\");\n" +
"            } else {\n" +
"                DialogMailComplete(\"#UserProfileSuccess\",\"+++ Fehler beim hinzuf&uuml;gen der Mailing Konfiguration +++\",\"<font color=red>Die Mailing Konfiguration konnte NICHT hinzugef&uuml;gt werden.</font>\");\n" +
"            }\n" +
"        },\n" +
"        dataType: 'json',\n" +
"        cache: false\n" +
"    });\n" +
"}\n" +
                "\n");                
                
            }
            
            if(Executions.UserIsPermitted(Uid,"config_usermanagement")) {
            
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
"                $('#UserMgmntUserList table').append('<tr><td>' + $.base64.decode( this.USDC ) + '</td><td>' + this.UCRT + '</td><td>' + this.ULAL + '</td><td>' + active + '</td><td><img onclick=\"DeleteEntry(\\'USER\\',\\'' + this.UUID + '\\')\" src=\"public/images/minus-circle.png\" title=\"Nutzer l&ouml;schen\"/></td></tr>');\n" +
"            });\n" +
"            $('#UserMgmntGroupList table').html('<tr><th>Gruppenname:</th><th>Gruppenbeschreibung:</th><th></th></tr>');\n" +
"            $.each(json.GROUPS, function() {\n" +
"                $('#UserMgmntGroupList table').append('<tr><td>' + $.base64.decode( this.GRNM ) + '</td><td>' + $.base64.decode( this.GRDC ) + '</td><td><img onclick=\"DeleteEntry(\\'GROUP\\',\\'' + this.GRID + '\\')\" src=\"public/images/minus-circle.png\" title=\"Gruppe l&ouml;schen\"/></td></tr>');\n" +
"            });\n" +
"            $('#UserMgmntRoleList table').html('<tr><th>Rollenname:</th><th>Rollenbeschreibung:</th><th></th></tr>');\n" +
"            $.each(json.ROLES, function() {\n" +
"                $('#UserMgmntRoleList table').append('<tr><td>' + $.base64.decode( this.RLNM ) + '</td><td>' + $.base64.decode( this.RLDC ) + '</td><td><img onclick=\"DeleteEntry(\\'ROLE\\',\\'' + this.RLID + '\\')\" src=\"public/images/minus-circle.png\" title=\"Rolle l&ouml;schen\"/></td></tr>');\n" +
"            });\n" +
"            $('#UserMgmntPrivList table').html('<tr><th>Privilegname:</th><th>Privilegbeschreibung:</th><th></th></tr>');\n" +
"            $.each(json.PRIVS, function() {\n" +
"                $('#UserMgmntPrivList table').append('<tr><td>' + $.base64.decode( this.PRNM ) + '</td><td>' + $.base64.decode( this.PRDC ) + '</td><td><img onclick=\"DeleteEntry(\\'PRIV\\',\\'' + this.PRID + '\\')\" src=\"public/images/minus-circle.png\" title=\"Privileg l&ouml;schen\"/></td></tr>');\n" +
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
"                $('#UserMgmntRoPrList1 table').append('<tr id=\"ptr' + PRID + '\"><td>' + $.base64.decode( this.PRDC ) + '<br>(' + $.base64.decode( this.PRNM ) + ')</td></tr>');\n" +
"                $('#UserMgmntRoPrList2 table').append('<tr id=\"rtr' + PRID + '\"></tr>');\n" +
"                $('#ptr' + PRID).hover( function() { $( this ).css('background-color','#dedede'); $('#rtr' + PRID).css('background-color','#dedede'); }, function() { $( this ).css('background-color','#fff'); $('#rtr' + PRID).css('background-color','#fff'); } );\n" +
"                $('#rtr' + PRID).hover( function() { $( this ).css('background-color','#dedede'); $('#ptr' + PRID).css('background-color','#dedede'); }, function() { $( this ).css('background-color','#fff'); $('#ptr' + PRID).css('background-color','#fff'); } );\n" +
"                $.each(json.ROLE, function() {\n" +
"                    if(c == 0) {\n" +
"                        $('#UserMgmntRoPrList2 table tr#thd').append('<th>' + $.base64.decode( this.ROLE_DC ) + '</th>');\n" +
"                    }" +
"                    $('#UserMgmntRoPrList2 table tr#rtr' + PRID ).append('<td><img id=\"PrID' + PRID + '_RlID' + this.ROLE_ID + '\" onclick=\"UpdateRolePriv(\\'' + this.ROLE_ID + '\\',\\'' + PRID + '\\');\" src=\"public/images/cross-circle.png\" title=\"Privileg erteilen\"/></td>');\n" +
"                });\n" +
"                c++;" +
"            });\n" +
"            $.each(json.ROLE, function() {\n" +
"                var ROLEID = this.ROLE_ID;\n" +                    
"                for(var i=0;i<this.ROLE_PRIV.length;i++) {\n" +
"                    $('#PrID' + this.ROLE_PRIV[i] + '_RlID' + ROLEID).attr('src','public/images/accept.png');\n" +                    
"                    $('#PrID' + this.ROLE_PRIV[i] + '_RlID' + ROLEID).attr('title','Privileg entziehen');\n" + 
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
"                $('#UserMgmntGrRoList1 table').append('<tr id=\"pgtr' + ROID + '\"><td>' + $.base64.decode( this.RODC ) + '<br>(' + $.base64.decode( this.RONM ) + ')</td></tr>');\n" +
"                $('#UserMgmntGrRoList2 table').append('<tr id=\"rgtr' + ROID + '\"></tr>');\n" +
"                $('#pgtr' + ROID).hover( function() { $( this ).css('background-color','#dedede'); $('#rgtr' + ROID).css('background-color','#dedede'); }, function() { $( this ).css('background-color','#fff'); $('#rgtr' + ROID).css('background-color','#fff'); } );\n" +
"                $('#rgtr' + ROID).hover( function() { $( this ).css('background-color','#dedede'); $('#pgtr' + ROID).css('background-color','#dedede'); }, function() { $( this ).css('background-color','#fff'); $('#pgtr' + ROID).css('background-color','#fff'); } );\n" +
"                $.each(json.GROUP, function() {\n" +
"                    if(c == 0) {\n" +
"                        $('#UserMgmntGrRoList2 table tr#tghd').append('<th>' + $.base64.decode( this.GROUP_DC ) + '</th>');\n" +
"                    }" +
"                    $('#UserMgmntGrRoList2 table tr#rgtr' + ROID ).append('<td><img id=\"RoID' + ROID + '_GrID' + this.GROUP_ID + '\" onclick=\"UpdateGroupRole(\\'' + this.GROUP_ID + '\\',\\'' + ROID + '\\');\" src=\"public/images/cross-circle.png\" title=\"Rolle zuweisen\"/></td>');\n" +
"                });\n" +
"                c++;" +
"            });\n" +
"            $.each(json.GROUP, function() {\n" +
"                var GROUPID = this.GROUP_ID;\n" +                    
"                for(var i=0;i<this.GROUP_ROLE.length;i++) {\n" +
"                    $('#RoID' + this.GROUP_ROLE[i] + '_GrID' + GROUPID).attr('src','public/images/accept.png');\n" +                    
"                    $('#RoID' + this.GROUP_ROLE[i] + '_GrID' + GROUPID).attr('title','Rolle entziehen');\n" + 
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
"                $('#UserMgmntUsGrList1 table').append('<tr id=\"putr' + GRID + '\"><td>' + $.base64.decode( this.GRDC ) + '<br>(' + $.base64.decode( this.GRNM ) + ')</td></tr>');\n" +
"                $('#UserMgmntUsGrList2 table').append('<tr id=\"rutr' + GRID + '\"></tr>');\n" +
"                $('#putr' + GRID).hover( function() { $( this ).css('background-color','#dedede'); $('#rutr' + GRID).css('background-color','#dedede'); }, function() { $( this ).css('background-color','#fff'); $('#rutr' + GRID).css('background-color','#fff'); } );\n" +
"                $('#rutr' + GRID).hover( function() { $( this ).css('background-color','#dedede'); $('#putr' + GRID).css('background-color','#dedede'); }, function() { $( this ).css('background-color','#fff'); $('#putr' + GRID).css('background-color','#fff'); } );\n" +
"                $.each(json.USER, function() {\n" +
"                    if(c == 0) {\n" +
"                        $('#UserMgmntUsGrList2 table tr#tuhd').append('<th>' + $.base64.decode( this.USER_DC ) + '</th>');\n" +
"                    }" +
"                    $('#UserMgmntUsGrList2 table tr#rutr' + GRID ).append('<td><img id=\"GrID' + GRID + '_UsID' + this.USER_ID + '\" onclick=\"UpdateUserGroup(\\'' + this.USER_ID + '\\',\\'' + GRID + '\\');\" src=\"public/images/cross-circle.png\" title=\"Gruppe zuweisen\"/></td>');\n" +
"                });\n" +
"                c++;" +
"            });\n" +
"            $.each(json.USER, function() {\n" +
"                var USERID = this.USER_ID;\n" +                    
"                for(var i=0;i<this.USER_GROUP.length;i++) {\n" +
"                    $('#GrID' + this.USER_GROUP[i] + '_UsID' + USERID).attr('src','public/images/accept.png');\n" +                    
"                    $('#GrID' + this.USER_GROUP[i] + '_UsID' + USERID).attr('title','Gruppe entziehen');\n" + 
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
"            url: '/gateway/exec/AddEntry?mod=' + $.base64.encode( mod ) + '&id=' + $.base64.encode( $('#' + id).val() ) + '&desc=' + $.base64.encode( $('#' + nm).val() ) ,\n" +
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
"        url: '/gateway/exec/DeleteEntry?mod=' + $.base64.encode( mod ) + '&id=' + $.base64.encode( id ),\n" +
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
"        url: '/gateway/exec/ActivateUser?mod=' + $.base64.encode( mod ) + '&id=' + $.base64.encode( id ),\n" +
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
            
            
            if(Executions.UserIsPermitted(Uid,"config_mail")) {

            out.println("\n" +
"function GetMailConfig() {\n" +
"    $.ajax({\n" +
"        url: '/gateway/exec/GetMailConfig',\n" +
"        crossDomain: true,\n" +
"        success: function(json) {\n" +
"            $('#MailH div.jqte_editor').html($.base64.decode( json.HEADER )); \n" +
"            $('#MailF div.jqte_editor').html($.base64.decode( json.FOOTER )); \n" +
"        },\n" +
"        dataType: 'json',\n" +
"        cache: false\n" +
"    });\n" +
"}\n" +
            "\n");                
                
            out.println("\n" +
"function AddMailConfig(id,key) {\n" +
"    var val = $.base64.encode( $('#' + id + ' div.jqte_editor').html() ).replace(/\\+/g,'78');\n" +
"    $.ajax({\n" +
"        url: '/gateway/exec/AddMailConfig?key=' + $.base64.encode( key ) + '&val=' + val,\n" +
"        crossDomain: true,\n" +
"        success: function(json) {\n" +
"            if (json.ADD == \"1\") {\n" +
"                DialogMailComplete(\"#UserProfileSuccess\",\"Mail Konfiguration hinzugef&uuml;gt\",\"Mail Konfiguration wurde erfolgreich hinzugef&uuml;gt.\");\n" +
"            } else {\n" +
"                DialogMailComplete(\"#UserProfileSuccess\",\"+++ Fehler beim hinzuf&uuml;gen der Mail Konfiguration +++\",\"<font color=red>Die Mail Konfiguration konnte NICHT hinzugef&uuml;gt werden.</font>\");\n" +
"            }\n" +
"        },\n" +
"        dataType: 'json',\n" +
"        cache: false\n" +
"    });\n" +
"}\n" +
            "\n");
            
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
