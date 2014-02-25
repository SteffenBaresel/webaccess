/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.siv.script;

import de.siv.modules.Executions;
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
public class UserProfile extends HttpServlet {
    Properties props = null;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response, String Uid)
            throws ServletException, IOException, FileNotFoundException, NamingException, SQLException {
        PrintWriter out = response.getWriter(); 
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "*");
        response.setContentType("application/javascript; charset=utf-8");
        
            // User Profile Dialog
        
            if(Executions.UserIsPermitted(Uid,"profile")) {
        
            out.println("\n" +
"function UserProfile(uid) {\n" +
"    $('#Dialog').html('<div id=\"UserProfileDialog\" title=\"Profileinstellungen bearbeiten\">" +
"    <div id=\"UserProfileImg\"><img id=UserProfileConfig2 src=\"public/images/DefaultProfile.png\" /></div><div onclick=\"UploadPicture();\" id=\"UserProfileImgHover\">Bild hochladen ...</div>" +
"    <div id=\"UserProfileUid\"><span>UID:</span><input type=text id=\"UsPuid\" value=\"' + UserID + '\" disabled/></div>" +
"    <div id=\"UserProfileName\"><span>Name:</span><input type=text id=\"UsPname\" value=\"' + FullName + '\" disabled/></div>" +
"    <div id=\"UserProfileMail\"><span>E-Mail:</span><input type=text id=\"UsPmail\" value=\"' + UserMail + '\" onclick=\"ClearValue(\\'UsPmail\\');\"/></div>" +
"    <div id=\"UserProfileGroup\"><span>Gruppen Mitglied:</span><div id=\"UserProfileGroupCnt\"></div></div>" +
"    <div id=\"UserProfilePerm\"><span>Erteilte Berechtigungen:</span><div id=\"UserProfilePermCnt\"></div></div>" +
"    </div>');\n" +
"\n" +
"     UserGroups.forEach (function(e) {\n" +
"         $('#UserProfileGroupCnt').append(' - ' + $.base64.decode( e ) + '<br>');\n" +
"     });\n" +
"" +
"     UserPerm.forEach (function(e) {\n" +
"         $('#UserProfilePermCnt').append(' - ' + $.base64.decode( e ) + '<br>');\n" +
"     });\n" +
"\n" +
"" +
"     $('#UserProfileImg').hover( function() {" +
"         $('#UserProfileImgHover').show();" +
"     },function() {" +
"         $('#UserProfileImgHover').hide();" +
"     });" +
"     $('#UserProfileImgHover').hover( function() {" +
"         $('#UserProfileImgHover').show();" +
"     },function() {" +
"         $('#UserProfileImgHover').hide();" +
"     });" +
"\n" +                
"     if ( UsrPctrLength != '0' ) {" +
"         $('#UserProfileConfig2').attr('src',UsrPctrPath);" +
"     }" +
"    $('input').addClass('ui-input-hofo');\n" +
"    $('#UserProfileDialog').dialog({\n" +
"        autoOpen: true,\n" +
"        height: 600,\n" +
"        width: 600,\n" +
"        draggable: false,\n" +
"        resizable: false,\n" +
"        modal: true,\n" +
"        buttons: { \n" +
"            BEARBEITEN: function() { \n" +
"                $.ajax({\n" +
"                    url: '/gateway/exec/UpdateUserMail?mail=' + $.base64.encode( $('#UsPmail').val() ),\n" +
"                    crossDomain: true,\n" +
"                    success: function(json) {\n" +
"                        if (json.UPDATED == \"1\") {\n" +
"                            DialogMailComplete(\"#Dialog\",\"Mailadresse erfolgreich geupdated\",\"Die Mailadresse wurde erfolgreich geupdated.\");\n" +
"                        } else {\n" +
"                            DialogMailComplete(\"#Dialog\",\"+++ Fehler beim updated der Mailadresse +++\",\"<font color=red>Die Mailadresse konnte NICHT geupdated werden.</font>\");\n" +
"                        }\n" +
"                    },\n" +
"                    error: function (xhr, thrownError) {\n" +
"                        alert(xhr.status + \"::\" + thrownError);\n" +
"                    },\n" +
"                    dataType: 'json',\n" +
"                    cache: false\n" +
"                });\n" +
"                $(this).dialog('close');\n" +
"                $('#UserProfileDialog').remove();\n" +
"                //location.reload();\n");

            if (Executions.UserMailEmpty(Uid)) {

            out.println("            }\n");

            } else {

            out.println("" +
"            },\n" +
"            BEENDEN: function() {\n" +
"                $(this).dialog('close');\n" +
"		 $('#UserProfileDialog').remove();\n" +
"                location.reload();\n" +
"            }\n");
            
            }
        
            out.println("	}\n"
                    + "    }).parent().find('.ui-dialog-titlebar-close').hide();\n"
                    + "}\n"
                    + "");
            
            out.println("\n"
                    + "function UploadPicture() {\n"
                    + "    window.open('/gateway/exec/FormPicture','UploadProfilBild','menubar=0,location=0,top=0,left=0,toolbar=0,personalbar=0,width=843,height=393,resizable=yes');\n"
                    + "    $('#UserProfileDialog').dialog('close');\n"
                    + "    $('#UserProfileDialog').remove();\n"
                    + "}\n");
            
        }
        
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, FileNotFoundException {
        try {
            processRequest(request, response, request.getRemoteUser());
        } catch (NamingException ex) {
            Logger.getLogger(TacticalOverview.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(TacticalOverview.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //
    }
}
