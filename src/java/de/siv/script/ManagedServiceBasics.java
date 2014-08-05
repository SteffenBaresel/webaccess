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
public class ManagedServiceBasics extends HttpServlet {
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

    if(Executions.UserIsPermitted(Uid,"managed_services")) {
        
        
                
        out.println("" +
                "function Reload(uid) {\n" +
                "    //$('#TopMenu').append('<img id=\"AjaxLoader\" src=\"public/images/ajax-loader.gif\">');\n" +
                "    GetServiceEntry('0','75','1');\n" +
                "}\n" +
                "");        
        
        out.println("" +
                "function StyleManagedServices() {\n" +
                "    $('#MS_CustActions button').button();\n" +
                "}\n" +
        "");
        
        
        if(Executions.UserIsPermitted(Uid,"managed_services_nka")) {
        
        out.println("" +
                "function CreateCustomer() {\n" +
                "    $.Shortcuts.stop();\n" +
                "    $('#MenuSidebarContent').fadeOut(100);\n" +
                "    $('#MenuSidebar').animate({width:'toggle'},350).css('zIndex',30);\n" +
                "    $('#MenuSidebarSmall').animate({marginLeft: \"0px\"},350).css('zIndex',30);\n" +
                "    $('#MSDialog').html('<div id=\"CreateCustomer\" title=\"Einen neuen Kunden anlegen.\">" +
                "<div id=\"CreateCustomerLeft\">" +
                "<table>" +
                "<tr><td><span style=\"float: left\">Kundenname</span><span style=\"float: left; margin-top: 0px;\" class=\"ui-icon ui-icon-triangle-1-s\"></span></td></tr><tr><td><input class=\"ui-input-hofo\" type=\"text\" placeholder=\"Musterstadtwerk\" id=\"cname\"/></td></tr><tr><td><span style=\"float: left\">Kundennummer</span><span style=\"float: left; margin-top: 0px;\" class=\"ui-icon ui-icon-triangle-1-s\"></span></td></tr><tr><td><input class=\"ui-input-hofo\" type=\"text\" placeholder=\"123456\" id=\"cnumber\" /></td></tr>" +
                "<tr><td><span style=\"float: left\">Kontakt E-Mail Adressen (mail1,mai2,mailn)</span><span style=\"float: left; margin-top: 0px;\" class=\"ui-icon ui-icon-triangle-1-s\"></span></td></tr><tr><td><input class=\"ui-input-hofo\" type=\"text\" placeholder=\"admin@musterstadtwerk.de\" id=\"cmail\" /></td></tr><tr><td><span style=\"float: left\">Eskalationsmailadressen (mail1,mail2,mailn)</span><span style=\"float: left; margin-top: 0px;\" class=\"ui-icon ui-icon-triangle-1-s\"></span></td></tr><tr><td><input class=\"ui-input-hofo\" type=\"text\" placeholder=\"escalation-manager@musterstadtwerk.de\" id=\"cesmail\" /></td></tr>" +
                "<tr><td><span style=\"float: left\">Kontakt Adresse</span><span style=\"float: left; margin-top: 0px;\" class=\"ui-icon ui-icon-triangle-1-s\"></span></td></tr><tr><td><div class=\"ui-input-hofo\" contenteditable=\"true\" id=\"caddress\"></div></td></tr>" +
                "</table>" +
                "</div>" +
                "<div id=\"CreateCustomerMiddle\">" +
                "<table>" +
                "<tr><td><span style=\"float: left\">Weitere Informationen zu diesem Kunden</span><span style=\"float: left; margin-top: 0px;\" class=\"ui-icon ui-icon-triangle-1-s\"></span></td></tr><tr><td><div class=\"ui-input-hofo\" id=\"ccomm\"><div id=\"ccommc\"></div></div></td></tr>" +
                "</table>" +
                "</div>" +
                "<div id=\"CreateCustomerRight\">" +
                "<table>" +
                "<tr><td><span style=\"float: left\">Welche Pflegevertr&auml;ge sind dem Kunden zugeordnet</span><span style=\"float: left; margin-top: 0px;\" class=\"ui-icon ui-icon-triangle-1-s\"></span></td></tr>" +
                "<tr><td><div id=\"ContractTypeDiv1\"><select class=\"ui-input-hofo\" id=\"ContractType1\"><option selected value=\"0000\">Vertrag 1: Bitte Ausw&auml;hlen</option></select></div><input class=\"ui-input-hofo\" id=\"ContractType1AN\" type=text placeholder=\"Auftragsnummer\"/><input class=\"ui-input-hofo\" id=\"ContractType1PV\" type=text placeholder=\"Produkt Version\"/><input class=\"ui-input-hofo\" id=\"ContractType1PI\" type=text placeholder=\"Produkt Information\" /></td></tr>" +
                "<tr><td><div id=\"ContractTypeDiv2\"><select class=\"ui-input-hofo\" id=\"ContractType2\"><option selected value=\"0000\">Vertrag 2: Bitte Ausw&auml;hlen</option></select></div><input class=\"ui-input-hofo\" id=\"ContractType2AN\" type=text placeholder=\"Auftragsnummer\"/><input class=\"ui-input-hofo\" id=\"ContractType2PV\" type=text placeholder=\"Produkt Version\"/><input class=\"ui-input-hofo\" id=\"ContractType2PI\" type=text placeholder=\"Produkt Information\" /></td></tr>" +
                "<tr><td><div id=\"ContractTypeDiv3\"><select class=\"ui-input-hofo\" id=\"ContractType3\"><option selected value=\"0000\">Vertrag 3: Bitte Ausw&auml;hlen</option></select></div><input class=\"ui-input-hofo\" id=\"ContractType3AN\" type=text placeholder=\"Auftragsnummer\"/><input class=\"ui-input-hofo\" id=\"ContractType3PV\" type=text placeholder=\"Produkt Version\"/><input class=\"ui-input-hofo\" id=\"ContractType3PI\" type=text placeholder=\"Produkt Information\" /></td></tr>" +
                "<tr><td><div id=\"ContractTypeDiv4\"><select class=\"ui-input-hofo\" id=\"ContractType4\"><option selected value=\"0000\">Vertrag 4: Bitte Ausw&auml;hlen</option></select></div><input class=\"ui-input-hofo\" id=\"ContractType4AN\" type=text placeholder=\"Auftragsnummer\"/><input class=\"ui-input-hofo\" id=\"ContractType4PV\" type=text placeholder=\"Produkt Version\"/><input class=\"ui-input-hofo\" id=\"ContractType4PI\" type=text placeholder=\"Produkt Information\" /></td></tr>" +
                "<tr><td><div id=\"ContractTypeDiv5\"><select class=\"ui-input-hofo\" id=\"ContractType5\"><option selected value=\"0000\">Vertrag 5: Bitte Ausw&auml;hlen</option></select></div><input class=\"ui-input-hofo\" id=\"ContractType5AN\" type=text placeholder=\"Auftragsnummer\"/><input class=\"ui-input-hofo\" id=\"ContractType5PV\" type=text placeholder=\"Produkt Version\"/><input class=\"ui-input-hofo\" id=\"ContractType5PI\" type=text placeholder=\"Produkt Information\" /></td></tr>" +
                "<tr><td><div id=\"ContractTypeDiv6\"><select class=\"ui-input-hofo\" id=\"ContractType6\"><option selected value=\"0000\">Vertrag 6: Bitte Ausw&auml;hlen</option></select></div><input class=\"ui-input-hofo\" id=\"ContractType6AN\" type=text placeholder=\"Auftragsnummer\"/><input class=\"ui-input-hofo\" id=\"ContractType6PV\" type=text placeholder=\"Produkt Version\"/><input class=\"ui-input-hofo\" id=\"ContractType6PI\" type=text placeholder=\"Produkt Information\" /></td></tr>" +
                "</table>" +
                "</div>" +
                "</div>');\n" +
                "" +
                "    $('#ccommc').jqte();\n" +
                "" +
                "    $('#CreateCustomer').dialog({\n" +
                "        autoOpen: true,\n" +
                "        height: 665,\n" +
                "        width: 1210,\n" +
                "        draggable: false,\n" +
                "        resizable: false,\n" +
                "        modal: true,\n" +
                "        open: function() {\n" +
                "            $.ajax({\n" +
                "                url: '/gateway/exec/GetContractTypes',\n" +
                "                crossDomain: true,\n" +
                "                success: function(json) {\n" +
                "                    $.each(json.CONTRACT_TYPES, function() {\n" +
                "                        $('#ContractType1').append('<option value=\"' + base64_decode( this.ID ) + '\">' + base64_decode( this.LN ) + ' (' + base64_decode( this.SN ) + ')</option>');\n" +
                "                        $('#ContractType2').append('<option value=\"' + base64_decode( this.ID ) + '\">' + base64_decode( this.LN ) + ' (' + base64_decode( this.SN ) + ')</option>');\n" +
                "                        $('#ContractType3').append('<option value=\"' + base64_decode( this.ID ) + '\">' + base64_decode( this.LN ) + ' (' + base64_decode( this.SN ) + ')</option>');\n" +
                "                        $('#ContractType4').append('<option value=\"' + base64_decode( this.ID ) + '\">' + base64_decode( this.LN ) + ' (' + base64_decode( this.SN ) + ')</option>');\n" +
                "                        $('#ContractType5').append('<option value=\"' + base64_decode( this.ID ) + '\">' + base64_decode( this.LN ) + ' (' + base64_decode( this.SN ) + ')</option>');\n" +
                "                        $('#ContractType6').append('<option value=\"' + base64_decode( this.ID ) + '\">' + base64_decode( this.LN ) + ' (' + base64_decode( this.SN ) + ')</option>');\n" +
                "                    });\n" +
                /*"                    $('#ContractType1').selectmenu({ width:385,menuWidth:385,style:'dropdown',maxHeight:200 });\n" +
                "                    $('#ContractType2').selectmenu({ width:385,menuWidth:385,style:'dropdown',maxHeight:200 });\n" +
                "                    $('#ContractType3').selectmenu({ width:385,menuWidth:385,style:'dropdown',maxHeight:200 });\n" +
                "                    $('#ContractType4').selectmenu({ width:385,menuWidth:385,style:'dropdown',maxHeight:200 });\n" +
                "                    $('#ContractType5').selectmenu({ width:385,menuWidth:385,style:'dropdown',maxHeight:200 });\n" +
                "                    $('#ContractType6').selectmenu({ width:385,menuWidth:385,style:'dropdown',maxHeight:200 });\n" +*/
                "                },\n" +
                "                dataType: 'json',\n" +
                "                cache: false\n" +
                "            });\n" +
                "            PrepareHtmlPasteJqte('ccomm');" +
                "            PrepareHtmlPasteDiv('caddress');" +
                "        }," +
                "        buttons: { \n" +
                "            SPEICHERN: function() {\n" +
                "                var ccomm = base64_encode( $('#ccomm div.jqte_editor').html() ).replace(/\\+/g,'78');\n" +
                "                var caddress = base64_encode( $('#caddress').html() ).replace(/\\+/g,'78');\n" +
                "                $.ajax({\n" +
                "                    url: '/gateway/exec/CreateCustomer?cname=' + base64_encode( $('#cname').val() ) + '&cnumber=' + base64_encode( $('#cnumber').val() ) + '&cmail=' + base64_encode( $('#cmail').val() ) + '&cesmail=' + base64_encode( $('#cesmail').val() ) + '&caddress=' + caddress + '&ccomm=' + ccomm + '&ct1=' + base64_encode( $('#ContractType1').val() ) + '&ct1an=' + base64_encode( $('#ContractType1AN').val() ) + '&ct1pv=' + base64_encode( $('#ContractType1PV').val() ) + '&ct1pi=' + base64_encode( $('#ContractType1PI').val() ) + '&ct2=' + base64_encode( $('#ContractType2').val() ) + '&ct2an=' + base64_encode( $('#ContractType2AN').val() ) + '&ct2pv=' + base64_encode( $('#ContractType2PV').val() ) + '&ct2pi=' + base64_encode( $('#ContractType2PI').val() ) + '&ct3=' + base64_encode( $('#ContractType3').val() ) + '&ct3an=' + base64_encode( $('#ContractType3AN').val() ) + '&ct3pv=' + base64_encode( $('#ContractType3PV').val() ) + '&ct3pi=' + base64_encode( $('#ContractType3PI').val() ) + '&ct4=' + base64_encode( $('#ContractType4').val() ) + '&ct4an=' + base64_encode( $('#ContractType4AN').val() ) + '&ct4pv=' + base64_encode( $('#ContractType4PV').val() ) + '&ct4pi=' + base64_encode( $('#ContractType4PI').val() ) + '&ct5=' + base64_encode( $('#ContractType5').val() ) + '&ct5an=' + base64_encode( $('#ContractType5AN').val() ) + '&ct5pv=' + base64_encode( $('#ContractType5PV').val() ) + '&ct5pi=' + base64_encode( $('#ContractType5PI').val() ) + '&ct6=' + base64_encode( $('#ContractType6').val() ) + '&ct6an=' + base64_encode( $('#ContractType6AN').val() ) + '&ct6pv=' + base64_encode( $('#ContractType6PV').val() ) + '&ct6pi=' + base64_encode( $('#ContractType6PI').val() ),\n" +
                "                    crossDomain: true,\n" +
                "                    success: function(json) {\n" +
                "                        if (json.EXEC == '1') {\n" +
                "                            DialogMailComplete(\"#MSDialogSuccess\",\"Kunde erfolgreich erstellt.\",\"Kunde wurde erfolgreich erstellt.\");\n" +
                "                        } else {\n" +
                "                            DialogMailComplete(\"#MSDialogSuccess\",\"+++ Kunde konnte nicht erstellt werden. +++\",\"<font color='#ff7777'>Kunde konnte nicht erstellt werden.</font>\");\n" +
                "                        }\n" +
                "                    },\n" +
                "                    dataType: 'json',\n" +
                "                    cache: false\n" +
                "                });\n" +
                "                $(this).dialog('close');\n" +
                "                $('#CreateCustomer').remove();\n" +
                "                $.Shortcuts.start();\n" +
                "                CustomerInfo();\n" +
                "            },\n" +
                "            ABBRECHEN: function() {\n" +
                "                $(this).dialog('close');\n" +
                "                $('#CreateCustomer').remove();\n" +
                "                $.Shortcuts.start();\n" +
                "            }\n" +
                "        }\n" +
                "    }).parent().find('.ui-dialog-titlebar-close').hide();\n" +
                "}\n" +
        "");
        
        }
        
        if(Executions.UserIsPermitted(Uid,"managed_services_kb")) {
        
        out.println("" +
                "function GetSingleCustomer() {\n" +
                "    $.ajax({\n" +
                "        url: '/gateway/exec/GetSingleCustomer?cuid=' + base64_encode( $('#cnameEselect').val() ),\n" +
                "        crossDomain: true,\n" +
                "        success: function(json) {\n" +
                "            $.each(json.CUSTOMER, function() {\n" +
                "                $('#cnumberE').val(base64_decode( this.CUNR ));\n" +
                "                $('#cmailE').val(base64_decode( this.CUMAIL ));\n" +
                "                $('#cesmailE').val(base64_decode( this.CUESKMAIL ));\n" +
                "                $('#caddressE').html(base64_decode( this.CUADDR ));\n" +
                "                $('#ccommE div.jqte_editor').html(base64_decode( this.CUCOMM ));\n" +
                "            });\n" +
                "            $('#ContractsDivInner').html('');\n" +
                "            $.each(json.CONTRACTS, function() {\n" +
                "                var img = '';\n" +
                "                if(json.CONTRACTS.length > 1) { img = '<img src=\"public/images/minus-circle.png\" title=\"Vertrag l&ouml;schen\" onclick=\"DeleteContract(\\\'' + base64_encode( $('#cnameEselect').val() ) + '\\\',\\\'' + this.CCID + '\\\');\"/>'; }\n" +
                "                $('#ContractsDivInner').append('<div id=\"CT_Outer\"><div id=\"ContractsDelete\">' + img + '</div><div id=\"CT_Head\"><span>Vertrag: </span><span>' + base64_decode( this.COTRLN ) + '</span></div><div id=\"CT_AN\"><span>Auftragsnummer: </span><span>' + base64_decode( this.CCNR ) + '</span></div><div id=\"CT_PR\"><span>Umgebung: </span><span>' + base64_decode( this.CCPRDC ) + ' (' + base64_decode( this.CCPRVE ) + ')</span></div></div>');\n" +
                "            });\n" +
                "        },\n" +
                "        dataType: 'json',\n" +
                "        cache: false\n" +
                "    });\n" +
                "}\n" +
        "");
        
        out.println("" +
                "function DeleteContract(cuid,ccid) {\n" +
                "    $.ajax({\n" +
                "        url: '/gateway/exec/DeleteContract?cuid=' + cuid + '&ccid=' + ccid,\n" +
                "        crossDomain: true,\n" +
                "        success: function(json) {\n" +
                "            GetSingleCustomer();\n" +
                "        },\n" +
                "        dataType: 'json',\n" +
                "        cache: false\n" +
                "    });\n" +
                "}\n" +
        "");
            
        out.println("" +
                "function EditCustomer() {\n" +
                "    $.Shortcuts.stop();\n" +
                "    $('#MenuSidebarContent').fadeOut(100);\n" +
                "    $('#MenuSidebar').animate({width:'toggle'},350).css('zIndex',30);\n" +
                "    $('#MenuSidebarSmall').animate({marginLeft: \"0px\"},350).css('zIndex',30);\n" +
                "    $('#MSDialog').html('<div id=\"CreateCustomer\" title=\"Einen Kunden bearbeiten.\">" +
                "<div id=\"CreateCustomerLeft\">" +
                "<table>" +
                "<tr><td><span style=\"float: left\">Kundenname</span><span style=\"float: left; margin-top: 0px;\" class=\"ui-icon ui-icon-triangle-1-s\"></span></td></tr><tr><td><div id=\"cnameE\"><select class=\"ui-input-hofo\" id=\"cnameEselect\" onchange=\"GetSingleCustomer(\\\'cnameEselect\\\');\"><option selected value=\"0000\">Kunde w&auml;hlen</option></select></div></td></tr><tr><td><span style=\"float: left\">Kundennummer</span><span style=\"float: left; margin-top: 0px;\" class=\"ui-icon ui-icon-triangle-1-s\"></span></td></tr><tr><td><input class=\"ui-input-hofo\" type=\"text\" id=\"cnumberE\" placeholder=\"123456\"/></td></tr>" +
                "<tr><td><span style=\"float: left\">Kontakt E-Mail Adressen (mail1,mai2,mailn)</span><span style=\"float: left; margin-top: 0px;\" class=\"ui-icon ui-icon-triangle-1-s\"></span></td></tr><tr><td><input class=\"ui-input-hofo\" type=\"text\" id=\"cmailE\" placeholder=\"admin@musterstadtwerk.de\"/></td></tr><tr><td><span style=\"float: left\">Eskalationsmailadressen (mail1,mail2,mailn)</span><span style=\"float: left; margin-top: 0px;\" class=\"ui-icon ui-icon-triangle-1-s\"></span></td></tr><tr><td><input class=\"ui-input-hofo\" type=\"text\" id=\"cesmailE\" placeholder=\"escalation-manager@musterstadtwerk.de\"/></td></tr>" +
                "<tr><td><span style=\"float: left\">Kontakt Adresse</span><span style=\"float: left; margin-top: 0px;\" class=\"ui-icon ui-icon-triangle-1-s\"></span></td></tr><tr><td><div class=\"ui-input-hofo\" contenteditable=\"true\" id=\"caddressE\"></div></td></tr>" +
                "</table>" +
                "</div>" +
                "<div id=\"CreateCustomerMiddle\">" +
                "<table>" +
                "<tr><td><span style=\"float: left\">Weitere Informationen zu diesem Kunden</span><span style=\"float: left; margin-top: 0px;\" class=\"ui-icon ui-icon-triangle-1-s\"></span></td></tr><tr><td><div class=\"ui-input-hofo\" id=\"ccommE\"><div id=\"ccommEc\"></div></div></td></tr>" +
                "</table>" +
                "</div>" +
                "<div id=\"CreateCustomerRight\">" +
                "<table>" +
                "<tr><td><span style=\"float: left\">Welche Pflegevertr&auml;ge sind dem Kunden zugeordnet</span><span style=\"float: left; margin-top: 0px;\" class=\"ui-icon ui-icon-triangle-1-s\"></span></td></tr>" +
                "<tr><td><div class=\"ui-input-hofo\" id=\"ContractsDiv1\"><div id=\"ContractsDivInner\"></div></div></td></tr>" +
                "</table>" +
                "<table>" +
                "<tr><td><span style=\"float: left\">Weiteren Pflegevertrag hinzuf&uuml;gen</span><span style=\"float: left; margin-top: 0px;\" class=\"ui-icon ui-icon-triangle-1-s\"></span></td></tr>" +
                "<tr><td><div id=\"ContractTypeDiv1\"><select class=\"ui-input-hofo\" id=\"ContractType1\"><option selected value=\"0000\">Vertrag 1: Bitte Ausw&auml;hlen</option></select></div><input class=\"ui-input-hofo\" id=\"ContractType1AN\" type=text placeholder=\"Auftragsnummer\" /><input class=\"ui-input-hofo\" id=\"ContractType1PV\" type=text placeholder=\"Produkt Version\" /><input class=\"ui-input-hofo\" id=\"ContractType1PI\" type=text placeholder=\"Produkt Information\" /></td></tr>" +
                "</table>" +
                "</div>" +
                "</div>');\n" +
                "" +
                "    $('#ccommEc').jqte();\n" +
                "" +
                "    $('#CreateCustomer').dialog({\n" +
                "        autoOpen: true,\n" +
                "        height: 665,\n" +
                "        width: 1210,\n" +
                "        draggable: false,\n" +
                "        resizable: false,\n" +
                "        modal: true,\n" +
                "        open: function() {\n" +
                "            $.ajax({\n" +
                "                url: '/gateway/exec/GetContractTypes',\n" +
                "                crossDomain: true,\n" +
                "                success: function(json) {\n" +
                "                    $.each(json.CONTRACT_TYPES, function() {\n" +
                "                        $('#ContractType1').append('<option value=\"' + base64_decode( this.ID ) + '\">' + base64_decode( this.LN ) + ' (' + base64_decode( this.SN ) + ')</option>');\n" +
                "                    });\n" +
                /*"                    $('#ContractType1').selectmenu({ width:385,menuWidth:385,style:'dropdown',maxHeight:200 });\n" +*/
                "                },\n" +
                "                dataType: 'json',\n" +
                "                cache: false\n" +
                "            });\n" +
                "            $.ajax({\n" +
                "                url: '/gateway/exec/GetCustomer',\n" +
                "                crossDomain: true,\n" +
                "                success: function(json) {\n" +
                "                    $.each(json.CUSTOMER, function() {\n" +
                "                        $('#cnameEselect').append('<option value=\"' + base64_decode( this.CUID ) + '\">' + base64_decode( this.CUNM ) + '</option>');\n" +
                "                    });\n" +
                /*"                    $('#cnameEselect').selectmenu({ width:385,menuWidth:385,style:'dropdown',maxHeight:200 });\n" +*/
                "                },\n" +
                "                dataType: 'json',\n" +
                "                cache: false\n" +
                "            });\n" +
                "            PrepareHtmlPasteJqte('ccommE');" +
                "            PrepareHtmlPasteDiv('caddressE');" +
                "        }," +
                "        buttons: { \n" +
                "            SPEICHERN: function() {\n" +
                "                var ccommE = base64_encode( $('#ccommE div.jqte_editor').html() ).replace(/\\+/g,'78');\n" +
                "                var caddressE = base64_encode( $('#caddressE').html() ).replace(/\\+/g,'78');\n" +
                "                $.ajax({\n" +
                "                    url: '/gateway/exec/UpdateCustomer?cuid=' + base64_encode( $('#cnameEselect').val() ) + '&cname=' + base64_encode( $('#cnameEselect option:selected').text() ) + '&cnumber=' + base64_encode( $('#cnumberE').val() ) + '&cmail=' + base64_encode( $('#cmailE').val() ) + '&cesmail=' + base64_encode( $('#cesmailE').val() ) + '&caddress=' + caddressE + '&ccomm=' + ccommE + '&ct1=' + base64_encode( $('#ContractType1').val() ) + '&ct1an=' + base64_encode( $('#ContractType1AN').val() ) + '&ct1pv=' + base64_encode( $('#ContractType1PV').val() ) + '&ct1pi=' + base64_encode( $('#ContractType1PI').val() ),\n" +
                "                    crossDomain: true,\n" +
                "                    success: function(json) {\n" +
                "                        if (json.EXEC == '1') {\n" +
                "                            DialogMailComplete(\"#MSDialogSuccess\",\"Kunde erfolgreich bearbeitet.\",\"Kunde wurde erfolgreich bearbeitet.\");\n" +
                "                        } else {\n" +
                "                            DialogMailComplete(\"#MSDialogSuccess\",\"+++ Kunde konnte nicht bearbeitet werden. +++\",\"<font color='#ff7777'>Kunde konnte nicht bearbeitet werden.</font>\");\n" +
                "                        }\n" +
                "                    },\n" +
                "                    dataType: 'json',\n" +
                "                    cache: false\n" +
                "                });\n" +
                "                $(this).dialog('close');\n" +
                "                $('#CreateCustomer').remove();\n" +
                "                $.Shortcuts.start();\n" +
                "                CustomerInfo();\n" +
                "            },\n" +
                "            ABBRECHEN: function() {\n" +
                "                $(this).dialog('close');\n" +
                "                $('#CreateCustomer').remove();\n" +
                "                $.Shortcuts.start();\n" +
                "            }\n" +
                "        }\n" +
                "    }).parent().find('.ui-dialog-titlebar-close').hide();\n" +
                "}\n" +
        "");
        
        }
        
        if(Executions.UserIsPermitted(Uid,"managed_services_kl")) {
        
        out.println("" +
                "function DeleteCustomer() {\n" +
                "    $.Shortcuts.stop();\n" +
                "    $('#MenuSidebarContent').fadeOut(100);\n" +
                "    $('#MenuSidebar').animate({width:'toggle'},350).css('zIndex',30);\n" +
                "    $('#MenuSidebarSmall').animate({marginLeft: \"0px\"},350).css('zIndex',30);\n" +
                "    $('#MSDialog').html('<div id=\"DeleteCustomer\" title=\"Einen Kunden l&ouml;schen.\">" +
                "<div id=\"DeleteCustomerLeft\">" +
                "<table>" +
                "<tr><td><span style=\"float: left\">Kundenname</span><span style=\"float: left; margin-top: 0px;\" class=\"ui-icon ui-icon-triangle-1-s\"></span></td></tr><tr><td><div id=\"cnameE\"><select class=\"ui-input-hofo\" id=\"cnameEselect\" onchange=\"GetSingleCustomer(\\\'cnameEselect\\\');\"><option selected value=\"0000\">Kunde w&auml;hlen</option></select></div></td></tr>" +
                "</table>" +
                "</div></div>');\n" +
                "" +
                "    \n" +
                "" +
                "    $('#DeleteCustomer').dialog({\n" +
                "        autoOpen: true,\n" +
                "        height: 220,\n" +
                "        width: 490,\n" +
                "        draggable: false,\n" +
                "        resizable: false,\n" +
                "        modal: true,\n" +
                "        open: function() {\n" +
                "            $.ajax({\n" +
                "                url: '/gateway/exec/GetCustomer',\n" +
                "                crossDomain: true,\n" +
                "                success: function(json) {\n" +
                "                    $.each(json.CUSTOMER, function() {\n" +
                "                        $('#cnameEselect').append('<option value=\"' + base64_decode( this.CUID ) + '\">' + base64_decode( this.CUNM ) + '</option>');\n" +
                "                    });\n" +
                /*"                    $('#cnameEselect').selectmenu({ width:385,menuWidth:385,style:'dropdown',maxHeight:200 });\n" +*/
                "                },\n" +
                "                dataType: 'json',\n" +
                "                cache: false\n" +
                "            });\n" +
                "        }," +
                "        buttons: { \n" +
                "            SPEICHERN: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/gateway/exec/DeleteCustomer?cuid=' + base64_encode( $('#cnameEselect').val() ),\n" +
                "                    crossDomain: true,\n" +
                "                    success: function(json) {\n" +
                "                        if (json.EXEC == '1') {\n" +
                "                            DialogMailComplete(\"#MSDialogSuccess\",\"Kunde erfolgreich entfernt.\",\"Kunde wurde erfolgreich entfernt.\");\n" +
                "                        } else {\n" +
                "                            DialogMailComplete(\"#MSDialogSuccess\",\"+++ Kunde konnte nicht entfernt werden. +++\",\"<font color='#ff7777'>Kunde konnte nicht entfernt werden.</font>\");\n" +
                "                        }\n" +
                "                    },\n" +
                "                    dataType: 'json',\n" +
                "                    cache: false\n" +
                "                });\n" +
                "                $(this).dialog('close');\n" +
                "                $('#DeleteCustomer').remove();\n" +
                "                $.Shortcuts.start();\n" +
                "                CustomerInfo();\n" +
                "            },\n" +
                "            ABBRECHEN: function() {\n" +
                "                $(this).dialog('close');\n" +
                "                $('#DeleteCustomer').remove();\n" +
                "                $.Shortcuts.start();\n" +
                "            }\n" +
                "        }\n" +
                "    }).parent().find('.ui-dialog-titlebar-close').hide();\n" +
                "}\n" +
        "");
        
        }
        
        if(Executions.UserIsPermitted(Uid,"managed_services_vae")) {
        
            out.println("" +
                "function CreateContractType() {\n" +
                "    $.Shortcuts.stop();\n" +
                "    $('#MenuSidebarContent').fadeOut(100);\n" +
                "    $('#MenuSidebar').animate({width:'toggle'},350).css('zIndex',30);\n" +
                "    $('#MenuSidebarSmall').animate({marginLeft: \"0px\"},350).css('zIndex',30);\n" +
                "    $('#MSDialog').html('<div id=\"CreateContractType\" title=\"Einen neuen Vertragstyp anlegen.\">" +
                "<div id=\"CreateContractTypeLeft\">" +
                "<table>" +
                "<tr><td><span style=\"float: left\">Name</span><span style=\"float: left; margin-top: 0px;\" class=\"ui-icon ui-icon-triangle-1-s\"></span></td><td><span style=\"float: left\">Beschreibung</span><span style=\"float: left; margin-top: 0px;\" class=\"ui-icon ui-icon-triangle-1-s\"></span></td></tr>" +
                "<tr><td><input class=\"ui-input-hofo\" type=\"text\" placeholder=\"IH\" id=\"cotrsn\"/></td><td><input class=\"ui-input-hofo\" type=\"text\" placeholder=\"Inhouse\" id=\"cotrln\"/></td></tr>" +
                "<tr><td colspan=2><span style=\"float: left\">Welche Pflegeaufgaben sind diesem Vertragstyp zugeordnet</span><span style=\"float: left; margin-top: 0px;\" class=\"ui-icon ui-icon-triangle-1-s\"></span></td></tr>" +
                "<tr><td><div class=\"ui-input-hofo\" id=\"mactions\"><div id=\"mactionsc\"></div></div></td></tr>" +
                "</table>" +
                "</div>" +
                "</div>');\n" +
                "" +
                "    $('#mactionsc').jqte();\n" +
                "" +
                "    $('#CreateContractType').dialog({\n" +
                "        autoOpen: true,\n" +
                "        height: 630,\n" +
                "        width: 583,\n" +
                "        draggable: false,\n" +
                "        resizable: false,\n" +
                "        modal: true,\n" +
                "        open: function() {\n" +
                "            PrepareHtmlPasteJqte('mactions');" +
                "        }," +
                "        buttons: { \n" +
                "            SPEICHERN: function() {\n" +
                "                var mactions = base64_encode( $('#mactions div.jqte_editor').html() ).replace(/\\+/g,'78');\n" +
                "                $.ajax({\n" +
                "                    url: '/gateway/exec/CreateContractType?cotrsn=' + base64_encode( $('#cotrsn').val() ) + '&cotrln=' + base64_encode( $('#cotrln').val() ) + '&mactions=' + mactions,\n" +
                "                    crossDomain: true,\n" +
                "                    success: function(json) {\n" +
                "                        if (json.EXEC == '1') {\n" +
                "                            DialogMailComplete(\"#MSDialogSuccess\",\"Vertragstyp erfolgreich erstellt.\",\"Vertragstyp wurde erfolgreich erstellt.\");\n" +
                "                        } else {\n" +
                "                            DialogMailComplete(\"#MSDialogSuccess\",\"+++ Vertragstyp konnte nicht erstellt werden. +++\",\"<font color='#ff7777'>Vertragstyp konnte nicht erstellt werden.</font>\");\n" +
                "                        }\n" +
                "                    },\n" +
                "                    dataType: 'json',\n" +
                "                    cache: false\n" +
                "                });\n" +
                "                $(this).dialog('close');\n" +
                "                $('#CreateContractType').remove();\n" +
                "                $.Shortcuts.start();\n" +
                "            },\n" +
                "            ABBRECHEN: function() {\n" +
                "                $(this).dialog('close');\n" +
                "                $('#CreateContractType').remove();\n" +
                "                $.Shortcuts.start();\n" +
                "            }\n" +
                "        }\n" +
                "    }).parent().find('.ui-dialog-titlebar-close').hide();\n" +
                "}\n" +
            "");
        
        }
        
        if(Executions.UserIsPermitted(Uid,"managed_services_vab")) {
        
            out.println("" +
                "function GetSingleContractType(id) {\n" +
                "    $.ajax({\n" +
                "        url: '/gateway/exec/GetSingleContractType?cttyid=' + base64_encode( $('#' + id).val() ),\n" +
                "        crossDomain: true,\n" +
                "        success: function(json) {\n" +
                "            $.each(json.CONTRACT, function() {\n" +
                "                $('#cotrlnE').html(base64_decode( this.COTRLN ));\n" +
                "                $('#mactionsE div.jqte_editor').html(base64_decode( this.MACTIONS ));\n" +
                "            });\n" +
                "        },\n" +
                "        dataType: 'json',\n" +
                "        cache: false\n" +
                "    });\n" +
                "}\n" +
        "");
            
        out.println("" +
                "function EditContractType() {\n" +
                "    $.Shortcuts.stop();\n" +
                "    $('#MenuSidebarContent').fadeOut(100);\n" +
                "    $('#MenuSidebar').animate({width:'toggle'},350).css('zIndex',30);\n" +
                "    $('#MenuSidebarSmall').animate({marginLeft: \"0px\"},350).css('zIndex',30);\n" +
                "    $('#MSDialog').html('<div id=\"CreateContractType\" title=\"Einen Vertragstyp bearbeiten.\">" +
                "<div id=\"CreateContractTypeLeft\">" +
                "<table>" +
                "<tr><td><span style=\"float: left\">Name</span><span style=\"float: left; margin-top: 0px;\" class=\"ui-icon ui-icon-triangle-1-s\"></span></td><td><span style=\"float: left\">Beschreibung</span><span style=\"float: left; margin-top: 0px;\" class=\"ui-icon ui-icon-triangle-1-s\"></span></td></tr>" +
                "<tr><td><div id=\"cotrsnE\"><select class=\"ui-input-hofo\" id=\"cotrsnEselect\" onchange=\"GetSingleContractType(\\\'cotrsnEselect\\\');\"><option selected value=\"0000\">Vertragstyp w&auml;hlen</option></select></div></td><td><div class=\"ui-input-hofo\" contenteditable=\"true\" id=\"cotrlnE\">&nbsp;</div></td></tr>" +
                "<tr><td colspan=2><br><span style=\"float: left\">Welche Pflegeaufgaben sind diesem Vertragstyp zugeordnet</span><span style=\"float: left; margin-top: 0px;\" class=\"ui-icon ui-icon-triangle-1-s\"></span></td></tr>" +
                "<tr><td><div class=\"ui-input-hofo\" id=\"mactionsE\"><div id=\"mactionsEc\"></div></div></td></tr>" +
                "</table>" +
                "</div>" +
                "</div>');\n" +
                "" +
                "    $('#mactionsEc').jqte();\n" +
                "" +
                "    $('#CreateContractType').dialog({\n" +
                "        autoOpen: true,\n" +
                "        height: 630,\n" +
                "        width: 583,\n" +
                "        draggable: false,\n" +
                "        resizable: false,\n" +
                "        modal: true,\n" +
                "        open: function() {\n" +
                "            $.ajax({\n" +
                "                url: '/gateway/exec/GetContractTypes',\n" +
                "                crossDomain: true,\n" +
                "                success: function(json) {\n" +
                "                    $.each(json.CONTRACT_TYPES, function() {\n" +
                "                        $('#cotrsnEselect').append('<option value=\"' + base64_decode( this.ID ) + '\">' + base64_decode( this.LN ) + ' (' + base64_decode( this.SN ) + ')</option>');\n" +
                "                    });\n" +
                /*"                    $('#cotrsnEselect').selectmenu({ width:255,menuWidth:385,style:'dropdown',maxHeight:200 });\n" +*/
                "                },\n" +
                "                dataType: 'json',\n" +
                "                cache: false\n" +
                "            });\n" +
                "            PrepareHtmlPasteJqte('mactionsE');" +
                "        },\n" +
                "        buttons: { \n" +
                "            SPEICHERN: function() {\n" +
                "                var mactions = base64_encode( $('#mactionsE div.jqte_editor').html() ).replace(/\\+/g,'78');\n" +
                "                var cotrln = base64_encode( $('#cotrlnE').html() ).replace(/\\+/g,'78');\n" +                
                "                $.ajax({\n" +
                "                    url: '/gateway/exec/UpdateContractType?cttyid=' + base64_encode( $('#cotrsnEselect').val() ) + '&cotrsn=' + base64_encode( $('#cotrsnEselect').text() ) + '&cotrln=' + cotrln + '&mactions=' + mactions,\n" +
                "                    crossDomain: true,\n" +
                "                    success: function(json) {\n" +
                "                        if (json.EXEC == '1') {\n" +
                "                            DialogMailComplete(\"#MSDialogSuccess\",\"Vertragstyp erfolgreich bearbeitet.\",\"Vertragstyp wurde erfolgreich bearbeitet.\");\n" +
                "                        } else {\n" +
                "                            DialogMailComplete(\"#MSDialogSuccess\",\"+++ Vertragstyp konnte nicht bearbeitet werden. +++\",\"<font color='#ff7777'>Vertragstyp konnte nicht bearbeitet werden.</font>\");\n" +
                "                        }\n" +
                "                    },\n" +
                "                    dataType: 'json',\n" +
                "                    cache: false\n" +
                "                });\n" +
                "                $(this).dialog('close');\n" +
                "                $('#CreateContractType').remove();\n" +
                "                $.Shortcuts.start();\n" +
                "            },\n" +
                "            ABBRECHEN: function() {\n" +
                "                $(this).dialog('close');\n" +
                "                $('#CreateContractType').remove();\n" +
                "                $.Shortcuts.start();\n" +
                "            }\n" +
                "        }\n" +
                "    }).parent().find('.ui-dialog-titlebar-close').hide();\n" +
                "}\n" +
        "");
        
        }
        
        if(Executions.UserIsPermitted(Uid,"managed_services_val")) {
        
        out.println("" +
                "function DeleteContractType() {\n" +
                "    $.Shortcuts.stop();\n" +
                "    $('#MenuSidebarContent').fadeOut(100);\n" +
                "    $('#MenuSidebar').animate({width:'toggle'},350).css('zIndex',30);\n" +
                "    $('#MenuSidebarSmall').animate({marginLeft: \"0px\"},350).css('zIndex',30);\n" +
                "    $('#MSDialog').html('<div id=\"DeleteContractType\" title=\"Einen Vertragstyp l&ouml;schen.\">" +
                "<div id=\"CreateContractTypeLeftE\">" +
                "<table>" +
                "<tr><td><span style=\"float: left\">Name</span><span style=\"float: left; margin-top: 0px;\" class=\"ui-icon ui-icon-triangle-1-s\"></span></td></tr>" +
                "<tr><td><div id=\"cotrsnD\"><select class=\"ui-input-hofo\" id=\"cotrsnEselect\"><option selected value=\"0000\">Vertragstyp w&auml;hlen</option></select></div></td></tr>" +
                "</table>" +
                "</div>" +
                "</div>');\n" +
                "" +
                "    \n" +
                "" +
                "    $('#DeleteContractType').dialog({\n" +
                "        autoOpen: true,\n" +
                "        height: 210,\n" +
                "        width: 500,\n" +
                "        draggable: false,\n" +
                "        resizable: false,\n" +
                "        modal: true,\n" +
                "        open: function() {\n" +
                "            $.ajax({\n" +
                "                url: '/gateway/exec/GetContractTypes',\n" +
                "                crossDomain: true,\n" +
                "                success: function(json) {\n" +
                "                    $.each(json.CONTRACT_TYPES, function() {\n" +
                "                        $('#cotrsnEselect').append('<option value=\"' + base64_decode( this.ID ) + '\">' + base64_decode( this.LN ) + ' (' + base64_decode( this.SN ) + ')</option>');\n" +
                "                    });\n" +
                /*"                    $('#cotrsnEselect').selectmenu({ width:385,menuWidth:385,style:'dropdown',maxHeight:200 });\n" +*/
                "                },\n" +
                "                dataType: 'json',\n" +
                "                cache: false\n" +
                "            });\n" +
                "        },\n" +
                "        buttons: { \n" +
                "            SPEICHERN: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/gateway/exec/DeleteContractType?cttyid=' + base64_encode( $('#cotrsnEselect').val() ),\n" +
                "                    crossDomain: true,\n" +
                "                    success: function(json) {\n" +
                "                        if (json.EXEC == '1') {\n" +
                "                            DialogMailComplete(\"#MSDialogSuccess\",\"Vertragstyp erfolgreich entfernt.\",\"Vertragstyp wurde erfolgreich entfernt.\");\n" +
                "                        } else {\n" +
                "                            DialogMailComplete(\"#MSDialogSuccess\",\"+++ Vertragstyp konnte nicht entfernt werden. +++\",\"<font color='#ff7777'>Vertragstyp konnte nicht entfernt werden.</font>\");\n" +
                "                        }\n" +
                "                    },\n" +
                "                    dataType: 'json',\n" +
                "                    cache: false\n" +
                "                });\n" +
                "                $(this).dialog('close');\n" +
                "                $('#DeleteContractType').remove();\n" +
                "                $.Shortcuts.start();\n" +
                "            },\n" +
                "            ABBRECHEN: function() {\n" +
                "                $(this).dialog('close');\n" +
                "                $('#DeleteContractType').remove();\n" +
                "                $.Shortcuts.start();\n" +
                "            }\n" +
                "        }\n" +
                "    }).parent().find('.ui-dialog-titlebar-close').hide();\n" +
                "}\n" +
        "");
        
        }
        
        if(Executions.UserIsPermitted(Uid,"managed_services_wili")) {
        
            out.println("" +
                "function WhoIsLoggedIn() {\n" +
                "    $.ajax({\n" +
                "        url: '/gateway/exec/WhoIsLoggedIn',\n" +
                "        crossDomain: true,\n" +
                "        success: function(json) {\n" +
                "            var i = 0;" +
                "            $('#WLI_Cont').html('');\n" +
                "            $.each(json.USER, function() {\n" +
                "                $('#WLI_Cont').append('<div id=\"LoggedInUser\" class=\"ui-input-hofo\"><img style=\"float: left; width: 50px; height: 50px; \" id=\"imgLU' + i + '\" src=\"public/images/DefaultProfile.png\" /><span id=\"LIU_Head\">' + base64_decode( this.USDC ) + '</span><span class=\"ui-icon ui-icon-mail-closed\" onclick=\"SendMailTo(\\\'' + base64_decode( this.UMAI ) + '\\\');\" title=\"Mail senden\"></span><br><span style=\"float: left;\">' + base64_decode( this.UMAI ) + '</span></div>');\n" +
                "                if(this.PCTRL != '0') { $('img#imgLU' + i).attr('src','/gateway/exec/UserPicture?user=' + base64_decode( this.USNM ) + ''); }\n" +
                "                i++;" +
                "            });\n" +
                "        },\n" +
                "        dataType: 'json',\n" +
                "        cache: false\n" +
                "    });\n" +
                "    setTimeout('WhoIsLoggedIn()', 30000);\n" +
                "}\n" +
            "");
            
        }
        
        if(Executions.UserIsPermitted(Uid,"managed_services_csw")) {
            
            out.println("" +
                "function GetSingleCustomerInfoMS(id) {\n" +
                "    $.ajax({\n" +
                "        url: '/gateway/exec/GetCustomerContractNumbers?cuid=' + base64_encode( $('#' + id).val() ),\n" +
                "        crossDomain: true,\n" +
                "        success: function(json) {\n" +
                "            $('#CreateServiceEntryAselect').html('<option value=\"0000\" selected>Bitte w&auml;hlen</option>');\n" +
                "            $.each(json.CONTRACT, function() {\n" +
                "                $('#CreateServiceEntryAselect').append('<option value=\"' + base64_decode( this.CCID ) + '\">' + base64_decode( this.COTRLN ) + ' (' + base64_decode( this.CCNR ) + ')</option>');\n" +
                "            });\n" +
                //"            $('#CreateServiceEntryAselect').selectmenu({ width:385,menuWidth:385,style:'dropdown',maxHeight:200 });\n" +
                "        },\n" +
                "        dataType: 'json',\n" +
                "        cache: false\n" +
                "    });\n" +
                "    $.ajax({\n" +
                "        url: '/gateway/exec/GetCustomerMailing?cuid=' + base64_encode( $('#' + id).val() ) + '&uuid=' + base64_encode( UUID ),\n" +
                "        crossDomain: true,\n" +
                "        success: function(json) {\n" +
                "            $('#InAN').val(base64_decode( json.TO ));\n" +
                "            $('#InEsk1').val(base64_decode( json.ESK1 ));\n" +
                "            $('#InEsk2').val(base64_decode( json.ESK2 ));\n" +
                "            $('#InEsk3').val(base64_decode( json.ESK3 ));\n" +
                "        },\n" +
                "        dataType: 'json',\n" +
                "        cache: false\n" +
                "    });\n" +
                "}\n" +
            "");
            
            out.println("" +
                "function ActivateMailingMS(id) {\n" +
                "    if( $('#' + id).is(':checked') ) {\n"+
                "        $('#CSEDscOverLay').css('display','none');\n" +
                "        $('#CreateServiceEntryLeftMF').css('display','block');" +
                "        $('#CreateServiceEntryLeftMH').css('display','block');" +
                "        $('#CreateServiceEntryLeftMC div.jqte').css('height','396px');" +
                "        $('#CreateServiceEntryLeftMC .jqte_editor').css('height','347px');" +
                "        $('#CreateServiceEntryLeftMC .jqte_source').css('height','347px');" +
                "        $('#CSEDsc2').css('color','green');\n" +
                "    } else {\n" +
                "        $('#CSEDscOverLay').css('display','block');\n" +
                "        $('#CreateServiceEntryLeftMF').css('display','none');" +
                "        $('#CreateServiceEntryLeftMH').css('display','none');" +
                "        $('#CreateServiceEntryLeftMC div.jqte').css('height','665px');" +
                "        $('#CreateServiceEntryLeftMC .jqte_editor').css('height','616px');" +
                "        $('#CreateServiceEntryLeftMC .jqte_source').css('height','616px');" +
                "        $('#CSEDsc2').css('color','#000');\n" +
                "    }" +
                "}\n" +
            "");
            
            out.println("" +
                "function ShowEscalationSubjectMS(dsc) {\n" +
                "    $('#InSub').val('!!! Wichtig !!! - Eskalation ' + dsc + ': Information zur laufenden Wartung Ihrer Systeme');\n" +
                "}\n" +
            "");
            
            out.println("" +
                "function ChangeSubjectMS(id1,id2) {\n" +
                "    var cu = $('#' + id1 + ' option:selected').text();\n" +
                "    var a = $('#' + id2 + ' option:selected').text();\n" +
                "    var an = a.substring(0,a.indexOf('\\('));\n" +
                "    $('#InSub').val('Information zur laufenden Wartung: ' + cu + ' - ' + an);\n" +
                "}\n" +
            "");
            
            out.println("" +
                "function CreateServiceEntry() {\n" +
                "    $.Shortcuts.stop();\n" +
                "    $('#MenuSidebarContent').fadeOut(100);\n" +
                "    $('#MenuSidebar').animate({width:'toggle'},350).css('zIndex',30);\n" +
                "    $('#MenuSidebarSmall').animate({marginLeft: \"0px\"},350).css('zIndex',30);\n" +
                "    var now = new Date();\n" +
                "    $('#MSDialog').html('<div id=\"CreateServiceEntry\" title=\"Servicearbeit eintragen.\">" +
                "<div id=\"CreateServiceEntryRight\">" +
                "<div id=\"CreateServiceEntryU\"><div id=\"CSEDsc\"><span style=\"float: left\">Arbeiten wurden ausgef&uuml;hrt durch</span><span style=\"float: left; margin-top: 0px;\" class=\"ui-icon ui-icon-triangle-1-s\"></span></div><input class=\"ui-input-hofo\" type=text value=\"' + FullName + '\" disabled/><input id=\"InFrom\" type=hidden value=\"' + UserMail + '\" /></div>" +
                "<div id=\"CreateServiceEntryC\"><div id=\"CSEDsc\"><span style=\"float: left\">Arbeiten wurden ausgef&uuml;hrt f&uuml;r</span><span style=\"float: left; margin-top: 0px;\" class=\"ui-icon ui-icon-triangle-1-s\"></span></div><div id=\"CreateServiceEntryCs\"><select class=\"ui-input-hofo\" id=\"CreateServiceEntryCselect\" onchange=\"GetSingleCustomerInfoMS(\\\'CreateServiceEntryCselect\\\');\"><option selected value=\"0000\">Kunde w&auml;hlen</option></select></div></div>" +
                "<div id=\"CreateServiceEntryA\"><div id=\"CSEDsc\"><span style=\"float: left\">Auftragsnummer</span><span style=\"float: left; margin-top: 0px;\" class=\"ui-icon ui-icon-triangle-1-s\"></span></div><select class=\"ui-input-hofo\" id=\"CreateServiceEntryAselect\" onchange=\"ChangeSubjectMS(\\\'CreateServiceEntryCselect\\\',\\\'CreateServiceEntryAselect\\\');\"><option selected value=\"0000\">-</option></select></div>" +
                "<div id=\"CreateServiceEntryT\"><div id=\"CSEDsc\"><span style=\"float: left\">Typ des Eintrags</span><span style=\"float: left; margin-top: 0px;\" class=\"ui-icon ui-icon-triangle-1-s\"></span></div><div id=\"CreateServiceEntryTs\"><select class=\"ui-input-hofo\" id=\"CreateServiceEntryTselect\"><option selected value=\"0000\">Bitte w&auml;hlen</option></select></div></div>" +
                "<div id=\"CreateServiceEntryS\"><div id=\"CSEDsc\"><span style=\"float: left\">Zeitstempel</span><span style=\"float: left; margin-top: 0px;\" class=\"ui-icon ui-icon-triangle-1-s\"></span></div><input class=\"ui-input-hofo\" id=\"CreateServiceEntryTime\" type=text value=\"' + now.format(\"yyyy-mm-dd HH:MM:ss\") + '\" /></div>" +
                "<div id=\"CreateServiceEntryD\"><div id=\"CSEDsc\"><span style=\"float: left\">Dauer (min)</span><span style=\"float: left; margin-top: 0px;\" class=\"ui-icon ui-icon-triangle-1-s\"></span></div><input class=\"ui-input-hofo\" id=\"CreateServiceEntryDelay\" type=text value=\"15\" /></div>" +
                "<div id=\"CreateServiceEntryM\">" +
                "<div id=\"CSEDsc2\">Soll der Eintrag per Mail an den Kunden verschickt werden?" +
                "<input id=\"CSEDscCB\" type=\"checkbox\" value=\"1\" name=\"mailingto\" onclick=\"ActivateMailingMS(\\\'CSEDscCB\\\');\"></div>" +
                "<div id=\"CSEDscOverLay\"></div>" +
                "<div id=\"InANt\">An:</div><input class=\"ui-input-hofo\" id=\"InAN\" type=\"text\" />" +
                "<div id=\"InCCt\">Cc:</div><input class=\"ui-input-hofo\" id=\"InCC\" type=\"text\" />" +
                "<div id=\"InSubt\">Btr.</div><input class=\"ui-input-hofo\" id=\"InSub\" type=\"text\" value=\"\" />" +
                "<div id=\"CSEDsc2\">Soll der Eintrag eskaliert werden?</div>" +
                "<div id=\"CSEDsc3\" class=\"CSEDsc3a\"><span>Stufe 1 - erweitert den Betreff, CC bis in die FGL Ebene</span><input onclick=\"ShowEscalationSubjectMS(\\\'Stufe 1\\\');\" type=\"radio\" value=\"1\" name=\"escalate\" /></div>" +
                "<input class=\"ui-input-hofo\" id=\"InEsk1\" type=\"text\" value=\"\" />" +
                "<div id=\"CSEDsc3\" class=\"CSEDsc3b\">Stufe 2 - erweitert den Betreff, CC bis in die AL Ebene<input onclick=\"ShowEscalationSubjectMS(\\\'Stufe 2\\\');\" type=\"radio\" value=\"2\" name=\"escalate\" /></div>" +
                "<input class=\"ui-input-hofo\" id=\"InEsk2\" type=\"text\" value=\"\" />" +
                "<div id=\"CSEDsc3\" class=\"CSEDsc3c\">Stufe 3 - erweitert den Betreff, CC bis in die GF Ebene<input onclick=\"ShowEscalationSubjectMS(\\\'Stufe 3\\\');\" type=\"radio\" value=\"3\" name=\"escalate\" /></div>" +
                "<input class=\"ui-input-hofo\" id=\"InEsk3\" type=\"text\" value=\"\" />" +
                "</div></div>" +
                "<div id=\"CreateServiceEntryLeft\">" +
                "<div class=\"ui-input-hofo\" id=\"CreateServiceEntryLeftMH\"><div id=\"CreateServiceEntryLeftMHc\"></div></div>" +
                "<div class=\"ui-input-hofo\" id=\"CreateServiceEntryLeftMC\"><div id=\"CreateServiceEntryLeftMCc\"></div></div>" +
                "<div class=\"ui-input-hofo\" id=\"CreateServiceEntryLeftMF\"><div id=\"CreateServiceEntryLeftMFc\"></div></div>" +
                "</div>" +
                "</div>');\n" +
                "" +
                "    $('#CreateServiceEntryLeftMHc').jqte();\n" +
                "    $('#CreateServiceEntryLeftMCc').jqte();\n" +
                "    $('#CreateServiceEntryLeftMFc').jqte();\n" +
                "" +
                "    $('#CreateServiceEntry').dialog({\n" +
                "        autoOpen: true,\n" +
                "        height: 820,\n" +
                "        width: 1200,\n" +
                "        draggable: false,\n" +
                "        resizable: false,\n" +
                "        modal: true,\n" +
                "        open: function() {\n" +
                "            $.ajax({\n" +
                "                url: '/gateway/exec/GetCustomer',\n" +
                "                crossDomain: true,\n" +
                "                success: function(json) {\n" +
                "                    $.each(json.CUSTOMER, function() {\n" +
                "                        $('#CreateServiceEntryCselect').append('<option value=\"' + base64_decode( this.CUID ) + '\">' + base64_decode( this.CUNM ) + '</option>');\n" +
                "                    });\n" +
                //"                    $('#CreateServiceEntryCselect').selectmenu({ width:385,menuWidth:385,style:'dropdown',maxHeight:200 });\n" +
                "                },\n" +
                "                dataType: 'json',\n" +
                "                cache: false\n" +
                "            });\n" +
                "            $.ajax({\n" +
                "                url: '/gateway/exec/GetCommentTypes',\n" +
                "                crossDomain: true,\n" +
                "                success: function(json) {\n" +
                "                    $.each(json.COMMENT_TYPES, function() {\n" +
                "                        $('#CreateServiceEntryTselect').append('<option value=\"' + base64_decode( this.ID ) + '\">' + base64_decode( this.LN ) + '</option>');\n" +
                "                    });\n" +
                //"                    $('#CreateServiceEntryTselect').selectmenu({ width:385,menuWidth:385,style:'dropdown',maxHeight:200 });\n" +
                "                },\n" +
                "                dataType: 'json',\n" +
                "                cache: false\n" +
                "            });\n" +
                "            $.ajax({\n" +
                "                url: '/gateway/exec/GetUserMailFormat?uuid=' + base64_encode( UUID ),\n" +
                "                crossDomain: true,\n" +
                "                success: function(json) {\n" +
                "                    $('#CreateServiceEntryLeftMF div.jqte_editor').html(base64_decode( json.FOOTER ));\n" +
                "                    $('#CreateServiceEntryLeftMH div.jqte_editor').html(base64_decode( json.HEADER ));\n" +
                "                    $('#InCC').val(base64_decode( json.CC ));\n" +
                "                },\n" +
                "                dataType: 'json',\n" +
                "                cache: false\n" +
                "            });\n" +
                "            PrepareHtmlPasteJqte('CreateServiceEntryLeftMC');" +
                "            PrepareHtmlPasteJqte('CreateServiceEntryLeftMH');" +
                "            PrepareHtmlPasteJqte('CreateServiceEntryLeftMF');" +
                "        },\n" +
                "        buttons: { \n" +
                "            SPEICHERN: function() {\n" +
                "                var esk='0';\n" +
                "                var rep=0;\n" +
                "                var err=0;\n" +
                "                var msid=0;\n" +
                "                var exec=0;\n" +
                "                var content = base64_encode( $('#CreateServiceEntryLeftMC div.jqte_editor').html() ).replace(/\\+/g,'78');\n" +
                "                var text = base64_encode( $('#CreateServiceEntryLeftMH div.jqte_editor').html() + '<br>' + $('#CreateServiceEntryLeftMC div.jqte_editor').html() + '<br>' + $('#CreateServiceEntryLeftMF div.jqte_editor').html() ).replace(/\\+/g,'78');\n" +
                "                $('input:radio[name=\"escalate\"]').each( function() {\n" +
                "                    if ($(this).is(':checked')) {\n" +
                "                        var c = $(this).val();\n" +
                "                        esk=c;\n" +
                "                    }\n" +
                "                });\n" +
                "                $.ajax({\n" +
                "                    url: '/gateway/exec/CreateServiceEntry?uuid=' + base64_encode( UUID ) + '&cuid=' + base64_encode( $('#CreateServiceEntryCselect').val() ) + '&ccid=' + base64_encode( $('#CreateServiceEntryAselect').val() ) + '&comtid=' + base64_encode( $('#CreateServiceEntryTselect').val() ) + '&tm=' + base64_encode( $('#CreateServiceEntryTime').val() ) + '&dl=' + base64_encode( $('#CreateServiceEntryDelay').val() ) + '&co=' + content + '&esk=' + base64_encode( esk ) + '',\n" +
                "                    crossDomain: true,\n" +
                "                    success: function(json) {\n" +
                "                        msid = parseInt(json.MSID);\n" +
                "                        exec = parseInt(json.EXEC);\n" +
                "                        if (json.EXEC == '1') {\n" +
                "                            DialogMailComplete(\"#MSDialogSuccess\",\"Serviceeintrag erfolgreich erstellt.\",\"Serviceeintrag wurde erfolgreich erstellt.\");\n" +
                "                        } else {\n" +
                "                            DialogMailComplete(\"#MSDialogSuccess\",\"+++ Serviceeintrag konnte nicht erstellt werden. +++\",\"<font color='#ff7777'>Serviceeintrag konnte nicht erstellt werden.</font>\");\n" +
                "                        }\n" +
                "                    },\n" +
                "                    error: function(jqxhr,textStatus,errorThrown) {\n" +
                "                        DialogMailComplete(\"#MSDialogSuccess\",\"+++ Serviceeintrag konnte nicht erstellt werden. +++\",\"<font color='#ff7777'>Serviceeintrag konnte nicht erstellt werden.</font>\");\n" +
                "                    },\n" +
                "                    dataType: 'json',\n" +
                "                    cache: false,\n" +
                "                    async: false\n" +
                "                });\n" +
                "                if (exec == 1 && msid > 0) {\n" +
                "                  if( $('#CSEDscCB').is(':checked') ) {\n" +
                "                    var cc = $('#InCC').val();\n" +
                "                    $('input:radio[name=\"escalate\"]').each( function() {\n" +
                "                        if ($(this).is(':checked')) {\n" +
                "                            var c = $(this).val();\n" +
                "                            cc += ',' + $('#InEsk' + c).val();\n" +
                "                            esk=c;\n" +
                "                        }\n" +
                "                    });\n" +
                "                    var an = $('#InAN').val();\n" +
                "                    $.ajax({\n" +
                "                        url: '/gateway/exec/CreateMailEntry?msid=' + msid + '&uuid=' + UUID + '&cuid=' + $('#CreateServiceEntryCselect').val() + '&ccid=' + $('#CreateServiceEntryAselect').val() + '&mto=' + base64_encode( an ) + '&mcc=' + base64_encode( cc ) + '&msubject=' + base64_encode( $('#InSub').val() ) + '&mbody=' + text + '&mesc=' + esk,\n" +
                "                        crossDomain: true,\n" +
                "                        success: function(json) {\n" +
                "                            if (json.EXEC == '1') {\n" +
                "                                DialogMailComplete(\"#MSDialogSuccess\",\"E-Mail erfolgreich in Queue eintragen.\",\"Die E-Mail wurde erfolgreich in die Queue eingetragen.\");\n" +
                "                            } else {\n" +
                "                                DialogMailComplete(\"#MSDialogSuccess\",\"+++ E-Mail konnte nicht in Queue eingetragen werden. +++\",\"<font color='#ff7777'>Die E-Mail konnte nicht in die Queue eingetragen werden.</font>\");\n" +
                "                            }\n" +
                "                        },\n" +
                "                        dataType: 'json',\n" +
                "                        cache: false,\n" +
                "                        async: false\n" +
                "                    });\n" +
                "                  }\n" +
                "                }\n" +
                "                $.Shortcuts.start();\n" +
                "                $(this).dialog('close');\n" +
                "                $('#CreateServiceEntry').remove();\n" +
                "                GetServiceEntry('0','75','1');\n" +
                "            },\n" +
                "            ABBRECHEN: function() {\n" +
                "                $.Shortcuts.start();\n" +
                "                $(this).dialog('close');\n" +
                "                $('#CreateServiceEntry').remove();\n" +
                "            }\n" +
                "        }\n" +
                "    }).parent().find('.ui-dialog-titlebar-close').hide();\n" +
                "}\n" +
            "");
            
        }
        
        if(Executions.UserIsPermitted(Uid,"managed_services_csr")) {
            
            out.println("" +
                "function GetServiceEntry(offset,limit,pnumber) {" +
                "" +
                "    $.ajax({\n" +
                "        url: '/gateway/exec/GetServiceEntry?uuid=' + base64_encode( UUID ) + '&offset=' + offset + '&limit=' + limit,\n" +
                "        crossDomain: true,\n" +
                "        success: function(json) {\n" +
                "            var i = 0;\n" +
                "            $('#ManagedServiceActionsPage').html('<div id=\"ManagedServiceActionsPageSelected\"></div>')\n" +
                "            var count = base64_decode( json.COUNT );\n" +
                "            PagingServiceEntry(count,limit,pnumber);\n" +
                "            $('#ManagedServiceActions').html('<div id=\"ManagedServiceActionsC\"></div>');\n" +
                "            if(json.ROWS.length == 0) { $('#ManagedServiceActionsC').html('<span class=\"NoEntry\">Keine Eintr&auml;ge vorhanden.</span>'); }\n" +
                "            $.each(json.ROWS, function() {\n" +
                "                var now = new Date(base64_decode( this.TS ) * 1000);\n" +
                "                var esk = base64_decode( this.ESK );\n" +
                "                var eskt;\n" +
                "                if (esk == '1') {\n" +
                "                    eskt = '<span id=\"EskDscE\">Dieser Eintrag wurde in der Stufe 1 eskaliert.</span>';\n" +
                "                } else if (esk == '2') {\n" +
                "                    eskt = '<span id=\"EskDscE\">Dieser Eintrag wurde in der Stufe 2 eskaliert.</span>';\n" +
                "                } else if (esk == '3') {\n" +
                "                    eskt = '<span id=\"EskDscE\">Dieser Eintrag wurde in der Stufe 3 eskaliert.</span>';\n" +
                "                } else {\n" +
                "                    eskt = '<span id=\"EskDscD\">Dieser Eintrag wurde nicht eskaliert.</span>';\n" +
                "                }\n" +
                "                $('#ManagedServiceActionsC').append('<div id=\"ManagedServiceActionsE\" class=\"ui-input-hofo\"><table><tr><td><img id=\"img' + i + '\" src=\"public/images/DefaultProfile.png\" /></td><td><div id=\"ManagedServiceActionsH\">' + base64_decode( this.NAME ) + ' f&uuml;r ' + base64_decode( this.CUNM ) + '</div>" +
                "<div id=\"ManagedServiceActionsD\"><span id=\"EskDscD\">am ' + now.format(\"yyyy-mm-dd HH:MM:ss\") + ' | </span>' + eskt + '</div>" +
                "<div id=\"ManagedServiceActionsT\">' + base64_decode( this.TEXT ) + '</div>" +
                "<div id=\"ManagedServiceActionsF\"><span id=\"EskDscD\">Auftragsnummer: ' + base64_decode( this.AN ) + ' | Vertragstyp: ' + base64_decode( this.CONM ) + '</span></div>" +
                "</td></tr></table></div>');\n" +
                "                if(this.PCTRL != '0') { $('img#img' + i).attr('src','/gateway/exec/UserPicture?user=' + base64_decode( this.UID ) + ''); }\n" +
                "                i++;\n" +
                "            });\n" +
                "            setTimeout('GetServiceEntry(\\\'0\\\',\\\'75\\\',\\\'1\\\');',300000);\n" +
                "        },\n" +
                "        dataType: 'json',\n" +
                "        cache: false\n" +
                "    });\n" +
                "}" +
            "");
            
            out.println("" +
                "function PagingServiceEntry(count,limit,pnumber) {\n" +
                "    $('#ManagedServiceActionsPageSelected').pagination({\n" +
                "        items: count,\n" +
                "        itemsOnPage: limit,\n" +
                "        cssStyle: 'compact-theme',\n" +
                "        currentPage: pnumber,\n" +
                "        prevText: 'Zur&uuml;ck',\n" +
                "        nextText: 'Weiter',\n" +
                "        onPageClick: function(pageNumber) {\n" +
                "            var offset = (pageNumber-1)*limit;\n" +
                "            GetServiceEntry(offset,limit,pageNumber);\n" +
                "        }" +
                "    });" +
                "}\n" +
                "");
            
            /*
             * Select service entries for one Customer
             */
            
            out.println("" +
                "function SelectCustomer() {\n" +
                "    $.ajax({\n" +
                "        url: '/gateway/exec/GetCustomer',\n" +
                "        crossDomain: true,\n" +
                "        success: function(json) {\n" +
                "            $.each(json.CUSTOMER, function() {\n" +
                "                $('#ManagedServiceActionsSelect').append('<option value=\"' + base64_decode( this.CUID ) + '\">' + base64_decode( this.CUNM ) + '</option>');\n" +
                "            });\n" +
                "        },\n" +
                "        dataType: 'json',\n" +
                "        cache: false\n" +
                "    });\n" +
                "}\n" +
                "");
            
            out.println("" +
                "function GetCustomerServiceEntries(id,offset,limit,pnumber) {\n" +
                "    $.ajax({\n" +
                "        url: '/gateway/exec/GetCustomerServiceEntry?uuid=' + base64_encode( UUID ) + '&cuid=' + base64_encode( $('#' + id).val() ) + '&offset=' + offset + '&limit=' + limit,\n" +
                "        crossDomain: true,\n" +
                "        success: function(json) {\n" +
                "            var i = 0;\n" +
                "            $('#ManagedServiceActionsPage').html('<div id=\"ManagedServiceActionsPageSelected\"></div>')\n" +
                "            var count = base64_decode( json.COUNT );\n" +
                "            PagingServiceEntrySelect(id,count,limit,pnumber);\n" +
                "            $('#ManagedServiceActions').html('<div id=\"ManagedServiceActionsC\"></div>');\n" +
                "            if(json.ROWS.length == 0) { $('#ManagedServiceActionsC').html('<span class=\"NoEntry\">Keine Eintr&auml;ge vorhanden.</span>'); }\n" +
                "            $.each(json.ROWS, function() {\n" +
                "                var now = new Date(base64_decode( this.TS ) * 1000);\n" +
                "                var esk = base64_decode( this.ESK );\n" +
                "                var eskt;\n" +
                "                if (esk == '1') {\n" +
                "                    eskt = '<span id=\"EskDscE\">Dieser Eintrag wurde in der Stufe 1 eskaliert.</span>';\n" +
                "                } else if (esk == '2') {\n" +
                "                    eskt = '<span id=\"EskDscE\">Dieser Eintrag wurde in der Stufe 2 eskaliert.</span>';\n" +
                "                } else if (esk == '3') {\n" +
                "                    eskt = '<span id=\"EskDscE\">Dieser Eintrag wurde in der Stufe 3 eskaliert.</span>';\n" +
                "                } else {\n" +
                "                    eskt = '<span id=\"EskDscD\">Dieser Eintrag wurde nicht eskaliert.</span>';\n" +
                "                }\n" +
                "                $('#ManagedServiceActionsC').append('<div id=\"ManagedServiceActionsE\" class=\"ui-input-hofo\"><table><tr><td><img id=\"img' + i + '\" src=\"public/images/DefaultProfile.png\" /></td><td><div id=\"ManagedServiceActionsH\">' + base64_decode( this.NAME ) + ' f&uuml;r ' + base64_decode( this.CUNM ) + '</div>" +
                "<div id=\"ManagedServiceActionsD\"><span id=\"EskDscD\">am ' + now.format(\"yyyy-mm-dd HH:MM:ss\") + ' | </span>' + eskt + '</div>" +
                "<div id=\"ManagedServiceActionsT\">' + base64_decode( this.TEXT ) + '</div>" +
                "<div id=\"ManagedServiceActionsF\"><span id=\"EskDscD\">Auftragsnummer: ' + base64_decode( this.AN ) + ' | Vertragstyp: ' + base64_decode( this.CONM ) + '</span></div>" +
                "</td></tr></table></div>');\n" +
                "                if(this.PCTRL != '0') { $('img#img' + i).attr('src','/gateway/exec/UserPicture?user=' + base64_decode( this.UID ) + ''); }\n" +
                "                i++;\n" +
                "            });\n" +
                "        },\n" +
                "        dataType: 'json',\n" +
                "        cache: false\n" +
                "    });\n" +
                "}" +
                "");
            
            out.println("" +
                "function PagingServiceEntrySelect(id,count,limit,pnumber) {\n" +
                "    $('#ManagedServiceActionsPageSelected').pagination({\n" +
                "        items: count,\n" +
                "        itemsOnPage: limit,\n" +
                "        cssStyle: 'compact-theme',\n" +
                "        currentPage: pnumber,\n" +
                "        prevText: 'Zur&uuml;ck',\n" +
                "        nextText: 'Weiter',\n" +
                "        onPageClick: function(pageNumber) {\n" +
                "            var offset = (pageNumber-1)*limit;\n" +
                "            GetCustomerServiceEntries(id,offset,limit,pageNumber);\n" +
                "        }" +
                "    });" +
                "}\n" +
                "");
            
        }
        
        if(Executions.UserIsPermitted(Uid,"managed_services_ki")) {
            
            out.println("" +
                    "function ShowCustomer(cuid) {\n" +
                    "    $('#MSIDialog').html('<div id=\"ShowCustomer\" title=\"Kundeninformation.\">" +
                    "<div id=\"CreateCustomerLeft\">" +
                    "<table>" +
                    "<tr><td><span style=\"float: left\">Kundenname</span><span style=\"float: left; margin-top: 0px;\" class=\"ui-icon ui-icon-triangle-1-s\"></span></td></tr><tr><td><input class=\"ui-input-hofo\" type=\"text\" id=\"cnameE\" disabled/></td></tr><tr><td><span style=\"float: left\">Kundennummer</span><span style=\"float: left; margin-top: 0px;\" class=\"ui-icon ui-icon-triangle-1-s\"></span></td></tr><tr><td><input class=\"ui-input-hofo\" type=\"text\" id=\"cnumberE\" disabled/></td></tr>" +
                    "<tr><td><span style=\"float: left\">Kontakt E-Mail Adressen (mail1,mai2,mailn)</span><span style=\"float: left; margin-top: 0px;\" class=\"ui-icon ui-icon-triangle-1-s\"></span></td></tr><tr><td><input class=\"ui-input-hofo\" type=\"text\" id=\"cmailE\" disabled/></td></tr><tr><td><span style=\"float: left\">Eskalationsmailadressen (mail1,mail2,mailn)</span><span style=\"float: left; margin-top: 0px;\" class=\"ui-icon ui-icon-triangle-1-s\"></span></td></tr><tr><td><input class=\"ui-input-hofo\" type=\"text\" id=\"cesmailE\" disabled/></td></tr>" +
                    "<tr><td><span style=\"float: left\">Kontakt Adresse</span><span style=\"float: left; margin-top: 0px;\" class=\"ui-icon ui-icon-triangle-1-s\"></span></td></tr><tr><td><div class=\"ui-input-hofo\" id=\"caddressE\"></div></td></tr>" +
                    "</table>" +
                    "</div>" +
                    "<div id=\"CreateCustomerMiddle\">" +
                    "<table>" +
                    "<tr><td><span style=\"float: left\">Weitere Informationen zu diesem Kunden</span><span style=\"float: left; margin-top: 0px;\" class=\"ui-icon ui-icon-triangle-1-s\"></span></td></tr><tr><td><div class=\"ui-input-hofo\" id=\"ccommE\"></div></td></tr>" +
                    "</table>" +
                    "</div>" +
                    "<div id=\"CreateCustomerRight\">" +
                    "<table>" +
                    "<tr><td><span style=\"float: left\">Welche Pflegevertr&auml;ge sind dem Kunden zugeordnet</span><span style=\"float: left; margin-top: 0px;\" class=\"ui-icon ui-icon-triangle-1-s\"></span></td></tr>" +
                    "<tr><td><div id=\"ContractsDiv1\"><div id=\"ContractsDivInner\"></div></div></td></tr>" +
                    "</table>" +
                    "</div>" +
                    "</div>');\n" +
                    "    $('#ShowCustomer').dialog({\n" +
                    "        autoOpen: true,\n" +
                    "        height: 660,\n" +
                    "        width: 1210,\n" +
                    "        draggable: false,\n" +
                    "        resizable: false,\n" +
                    "        modal: true,\n" +
                    "        open: function () {\n" +
                    "            $.ajax({\n" +
                    "                url: '/gateway/exec/GetSingleCustomer?cuid=' + base64_encode( cuid ),\n" +
                    "                crossDomain: true,\n" +
                    "                success: function(json) {\n" +
                    "                    $.each(json.CUSTOMER, function() {\n" +
                    "                        $('#cnameE').val(base64_decode( this.CUNM ));\n" +
                    "                        $('#cnumberE').val(base64_decode( this.CUNR ));\n" +
                    "                        $('#cmailE').val(base64_decode( this.CUMAIL ));\n" +
                    "                        $('#cesmailE').val(base64_decode( this.CUESKMAIL ));\n" +
                    "                        $('#caddressE').html(base64_decode( this.CUADDR ));\n" +
                    "                        $('#ccommE').html(base64_decode( this.CUCOMM ));\n" +
                    "                    });\n" +
                    "                    $('#ContractsDivInner').html('');\n" +
                    "                    $.each(json.CONTRACTS, function() {\n" +
                    "                        $('#ContractsDivInner').append('<div class=\"ui-input-hofo\" id=\"CT_Outer\"><div id=\"CT_Head\"><span>Vertrag: </span><span>' + base64_decode( this.COTRLN ) + '</span></div><div id=\"CT_AN\"><span>Auftragsnummer: </span><span>' + base64_decode( this.CCNR ) + '</span></div><div id=\"CT_PR\"><span>Umgebung: </span><span>' + base64_decode( this.CCPRDC ) + ' (' + base64_decode( this.CCPRVE ) + ')</span></div></div>');\n" +
                    "                    });\n" +
                    "                },\n" +
                    "                dataType: 'json',\n" +
                    "                cache: false\n" +
                    "            });\n" +
                    "        }," +
                    "        buttons: { \n" +
                    "            OK: function() {\n" +
                    "                $(this).dialog('close');\n" +
                    "                $('#ShowCustomer').remove();\n" +
                    "            },\n" +
                    "        }\n" +
                    "    }).parent().find('.ui-dialog-titlebar-close').hide();\n" +
                    "}\n" +
            "");
            
            out.println("" +
                    "function CustomerInfo() {\n" +
                    "    $.ajax({\n" +
                    "        url: '/gateway/exec/GetCustomer',\n" +
                    "        crossDomain: true,\n" +
                    "        success: function(json) {\n" +
                    "            $('#MSI_Cont').html('');\n" +
                    "            $.each(json.CUSTOMER, function() {\n" +
                    "                $('#MSI_Cont').append('<div class=\"ui-input-hofo\" id=\"CustomerInfoEntry\" onclick=\"ShowCustomer(\\\'' + base64_decode( this.CUID ) + '\\\');\"><span id=\"CustomerInfoEntryHeader\">' + base64_decode( this.CUNM ) + '</span><br><span id=\"CustomerInfoEntryContent\">Kundennummer: ' + base64_decode( this.CUNR ) + '</span></div>');\n" +
                    "            });\n" +
                    "        },\n" +
                    "        dataType: 'json',\n" +
                    "        cache: false\n" +
                    "    });\n" +
                    "}\n" +
                    "");
            
            out.println("" +
                    "function CustomerInfoServiceEntries(id) {\n" +
                    "    $.ajax({\n" +
                    "        url: '/gateway/exec/GetCustomerServiceEntries?cuid=' + base64_encode( $('#' + id).val() ),\n" +
                    "        crossDomain: true,\n" +
                    "        success: function(json) {\n" +
                    "            $('#MSI_Cont').html('');\n" +
                    "            $.each(json.CUSTOMER, function() {\n" +
                    "                $('#MSI_Cont').append('<div class=\"ui-input-hofo\" id=\"CustomerInfoEntry\" onclick=\"ShowCustomer(\\\'' + base64_decode( this.CUID ) + '\\\');\"><span id=\"CustomerInfoEntryHeader\">' + base64_decode( this.CUNM ) + '</span><br><span id=\"CustomerInfoEntryContent\">Kundennummer: ' + base64_decode( this.CUNR ) + '</span></div>');\n" +
                    "            });\n" +
                    "        },\n" +
                    "        dataType: 'json',\n" +
                    "        cache: false\n" +
                    "    });\n" +
                    "}\n" +
                    "");
            
        }
        
    }
        
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, FileNotFoundException {
        try {
            processRequest(request, response, request.getRemoteUser());
        } catch (NamingException ex) {
            Logger.getLogger(ManagedServiceBasics.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ManagedServiceBasics.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //
    }
}

