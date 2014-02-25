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
                "function Top(uid) {\n" +
                "    var b64uid = $.base64.encode( uid );\n" +
                "    $('#TopMenu').append('<div><span style=\"float: left;\"><table cellpadding=0 cellspacing=0 border=0 id=\"TopMenuTable\"><tr><td><a href=\"./\">Home</a></td><td><span id=\"TopMenuIcon\" class=\"ui-icon ui-icon-triangle-1-e\"></span></td><td>Managed Services</td></tr></table></div>');\n" +
                "    $('#TopMenuIconGear').click( function() {\n" +
                "            if ($(\"#ExtSysInfo\").is(\":hidden\")) {\n" +
                "                $('#ExtSysInfo').fadeIn(100).css('zIndex',30);\n" +
                "            } else {\n" +
                "                $('#ExtSysInfo').fadeOut(100).css('zIndex',0);\n" +
                "            }\n" +
                "        }\n" +
                "    );\n" +
                "    $('#back-div').append('<a class=\"back-a\" href=\"./\"><img class=\"back-img\" src=\"public/images/back.png\" title=\"Zur&uuml;ck\"/></a>\');\n" +
                "        \n");
                
        if(Executions.UserIsPermitted(Uid,"sidebar")) {
        
        out.println("" +
                "    $.Shortcuts.add({\n" +
                "        type: 'down',\n" +
                "        mask: 's',\n" +
                "        handler: function() {\n" +
                "            if ($(\"#Sidebar\").is(\":hidden\")) {\n" +
                "                $('#SidebarSmall').animate({marginRight: \"400px\"},350).css('zIndex',30);\n" +
                "                $('#Sidebar').animate({width:'toggle'},350, function() {\n" +
                "                    $('#SidebarContent').fadeIn(100);\n" +
                "                }).css('zIndex',30);\n" +
                "                SearchCustomer();\n" +
                "            } else {\n" +
                "                $('#SidebarContent').fadeOut(100);\n" +
                "                $('#Sidebar').animate({width:'toggle'},350).css('zIndex',30);\n" +
                "                $('#SidebarSmall').animate({marginRight: \"0px\"},350).css('zIndex',30);\n" +
                "            }\n" +
                "        }\n" +
                "    }).start();\n"+
                "    $('#SidebarSmall').click(function() {\n" +
                "        if ($('#Sidebar').is(':hidden')) {\n" +
                "            $('#SidebarSmall').animate({marginRight: '400px'},350).css('zIndex',30);\n" +
                "            $('#Sidebar').animate({width:'toggle'},350, function() {\n" +
                "                $('#SidebarContent').fadeIn(100);\n" +
                "            }).css('zIndex',30);\n" +
                "            SearchCustomer();\n" +
                "        } else {\n" +
                "            $('#SidebarContent').fadeOut(100);\n" +
                "            $('#Sidebar').animate({width:'toggle'},350).css('zIndex',30);\n" +
                "            $('#SidebarSmall').animate({marginRight: '0px'},350).css('zIndex',30);\n" +
                "        }\n" +
                "    });\n" +
                "    $('#SidebarSearch').append('<div id=\"Title\">Suchen</div><div id=\"SubTitle\">Nichts ausgew&auml;hlt!</div><form id=\"SearchForm\" method=\"GET\"><input id=\"SearchInput\" name=\"searchstring\" type=\"text\" onclick=\"DeleteVal();\"><img onclick=\"FormSubmit();\" id=\"SearchImgInput\" src=\"public/images/search.png\" /></form>');\n" +
                "    $('#SidebarSearchFilter').append('<div class=\"DivSearchFilter\" id=\"SFCustomer\" onclick=\"SearchCustomer();\"><img id=\"SearchImg\" src=\"public/images/portal.png\"><span>Kunden</span></div>');\n" +
                "    $('#SidebarSearchFilter').append('<div class=\"DivSearchFilter\" id=\"SFHost\" onclick=\"SearchHosts(\\'' + b64uid + 'Ljd84K\\');\"><img id=\"SearchImg\" src=\"public/images/server.png\"><span>Hosts</span></div>');\n" +
                "    $('#SidebarSearchFilter').append('<div class=\"DivSearchFilter\" id=\"SFService\" onclick=\"SearchServices(\\'' + b64uid + 'Ljd84K\\');\"><img id=\"SearchImg\" src=\"public/images/services.png\"><span>Services</span></div>');\n" +
                "    $('#SidebarSearchFilter').append('<div class=\"DivSearchFilter\" id=\"SFDatabase\" onclick=\"SearchDatabases(\\'' + b64uid + 'Ljd84K\\');\"><img id=\"SearchImg\" src=\"public/images/database.png\"><span>Datenbanken</span></div>');\n" +
                "    $('#SidebarSearchFilter').append('<div class=\"DivSearchFilter\" id=\"SFMiddleware\" onclick=\"SearchMiddleware(\\'' + b64uid + 'Ljd84K\\');\"><img id=\"SearchImg\" src=\"public/images/layers.png\"><span>Middleware</span></div>');\n" +
                "\n" +
        "");
               
        }
        
        if(Executions.UserIsPermitted(Uid,"bottombar")) {
        
        out.println("" +
                "    $.Shortcuts.add({\n" +
                "        type: 'down',\n" +
                "        mask: 'b',\n" +
                "        handler: function() {\n" +
                "            if ($('#SidebarBottom').is(':hidden')) {\n" +
                "                $('#SidebarBottomSmall').animate({marginBottom: '870px'},350).css('zIndex',25);\n" +
                "                $('#SidebarBottom').animate({height:'toggle'},350, function() {\n" +
                "                    $('#SidebarBottomContent').fadeIn(100);\n" +
                "                }).css('zIndex',25);\n" +
                "            } else {\n" +
                "                $('#SidebarBottomContent').fadeOut(100);\n" +
                "                $('#SidebarBottom').animate({height:'toggle'},350).css('zIndex',10);\n" +
                "                $('#SidebarBottomSmall').animate({marginBottom: '0px'},350).css('zIndex',10);\n" +
                "            }\n" +
                "        }\n" +
                "    }).start();\n"+
                "    $('#SidebarBottomSmall').dblclick(function() {\n" +
                "        if ($('#SidebarBottom').is(':hidden')) {\n" +
                "            $('#SidebarBottomSmall').animate({marginBottom: '870px'},350).css('zIndex',25);\n" +
                "            $('#SidebarBottom').animate({height:'toggle'},350, function() {\n" +
                "                $('#SidebarBottomContent').fadeIn(100);\n" +
                "            }).css('zIndex',25);\n" +
                "        } else {\n" +
                "            $('#SidebarBottomContent').fadeOut(100);\n" +
                "            $('#SidebarBottom').animate({height:'toggle'},350).css('zIndex',10);\n" +
                "            $('#SidebarBottomSmall').animate({marginBottom: '0px'},350).css('zIndex',10);\n" +
                "        }\n" +
                "    });\n" +
        "");
               
        }
        
               
                out.println("}" +
                "");
                
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
        
        out.println("" +
                "function SearchCustomer() {\n" +
                "    $('form#SearchForm').attr('action', '');\n" +
                "    $('#SubTitle').html('.. nach Kundennamen');\n" +
                "    $('input#SearchInput').val('Kundenname');\n" +
                "    $('#SFService').removeClass('BgBlue');\n" +
                "    $('#SFHost').removeClass('BgBlue');\n" +
                "    $('#SFCustomer').addClass('BgBlue');\n" +
                "    $('#SFDatabase').removeClass('BgBlue');\n" +
                "    $('#SFMiddleware').removeClass('BgBlue');\n" +
                "    $('#SearchInput').autocomplete({\n" +
                "        source: function( request, response ) {\n" +
                "            $.ajax({\n" +
                "                url: '/gateway/exec/AutoCompleteCustomer',\n" +
                "                dataType: 'json',\n" +
                "                cache: false,\n" +
                "                data: {                    \n" +
                "                    cunm: $.base64.encode( request.term )\n" +
                "                },\n" +
                "                success: function( data ) {\n" +
                "                    response( $.map( data.CUSTOMER, function( item ) {\n" +
                "                        return {\n" +
                "                            label: $.base64.decode( item.CUNM ) + ' (' + $.base64.decode( item.CUNR ) +')',\n" +
                "                            value: $.base64.decode( item.CUID )\n" +
                "                        }\n" +
                "                    }));\n" +
                "                }\n" +
                "            });\n" +
                "        },\n" +
                "        minLength: 1\n" +
                "    });\n" +
                "}\n" +
        "");
        
        out.println("" +
                "function MenuSidebar() {\n" +
                "    $.Shortcuts.add({\n" +
                "        type: 'down',\n" +
                "        mask: 'm',\n" +
                "        enableInInput: true,\n" +
                "        handler: function() {\n" +
                "            if ($('#MenuSidebar').is(':hidden')) {\n" +
                "                $('#MenuSidebarSmall').animate({marginLeft: \"125px\"},350).css('zIndex',30);\n" +
                "                $('#MenuSidebar').animate({width:'toggle'},130, function() {\n" +
                "                    $('#MenuSidebarContent').fadeIn(100);\n" +
                "                }).css('zIndex',30);\n" +
                "            } else {\n" +
                "                $('#MenuSidebarContent').fadeOut(100);\n" +
                "                $('#MenuSidebar').animate({width:'toggle'},130).css('zIndex',30);\n" +
                "                $('#MenuSidebarSmall').animate({marginLeft: \"0px\"},350).css('zIndex',30);\n" +
                "            }\n" +
                "        }\n" +
                "    }).start();\n" +
                "}\n" +
        "");
        
        out.println("" +
            "function KlickFunctionMenuSidebar() {\n" +
            "    $('#MenuSidebarSmall').click(function() {\n" +
            "        if ($('#MenuSidebar').is(':hidden')) {\n" +
            "            $('#MenuSidebarSmall').animate({marginLeft: \"125px\"},350).css('zIndex',30);\n" +
            "            $('#MenuSidebar').animate({width:'toggle'},130, function() {\n" +
            "                $('#MenuSidebarContent').fadeIn(100);\n" +
            "            }).css('zIndex',30);\n" +
            "        } else {\n" +
            "            $('#MenuSidebarContent').fadeOut(100);\n" +
            "            $('#MenuSidebar').animate({width:'toggle'},130).css('zIndex',30);\n" +
            "            $('#MenuSidebarSmall').animate({marginLeft: \"0px\"},350).css('zIndex',30);\n" +
            "        }\n" +
            "    });\n" +
            "}" +
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
                "<tr><td><span style=\"float: left\">Kundenname</span><span style=\"float: left; margin-top: 0px;\" class=\"ui-icon ui-icon-triangle-1-s\"></span></td></tr><tr><td><input class=\"ui-input-hofo\" type=\"text\" value=\"Musterstadtwerk\" id=\"cname\" onclick=\"ClearValue(\\'cname\\');\"/></td></tr><tr><td><span style=\"float: left\">Kundennummer</span><span style=\"float: left; margin-top: 0px;\" class=\"ui-icon ui-icon-triangle-1-s\"></span></td></tr><tr><td><input class=\"ui-input-hofo\" type=\"text\" value=\"123456\" id=\"cnumber\" onclick=\"ClearValue(\\'cnumber\\');\"/></td></tr>" +
                "<tr><td><span style=\"float: left\">Kontakt E-Mail Adressen (mail1,mai2,mailn)</span><span style=\"float: left; margin-top: 0px;\" class=\"ui-icon ui-icon-triangle-1-s\"></span></td></tr><tr><td><input class=\"ui-input-hofo\" type=\"text\" value=\"admin@musterstadtwerk.de\" id=\"cmail\" onclick=\"ClearValue(\\'cmail\\');\"/></td></tr><tr><td><span style=\"float: left\">Eskalationsmailadressen (mail1,mail2,mailn)</span><span style=\"float: left; margin-top: 0px;\" class=\"ui-icon ui-icon-triangle-1-s\"></span></td></tr><tr><td><input class=\"ui-input-hofo\" type=\"text\" value=\"escalation-manager@musterstadtwerk.de\" id=\"cesmail\" onclick=\"ClearValue(\\'cesmail\\');\"/></td></tr>" +
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
                "<tr><td><div id=\"ContractTypeDiv1\"><select class=\"ui-input-hofo\" id=\"ContractType1\"><option selected value=\"0000\">Vertrag 1: Bitte Ausw&auml;hlen</option></select></div><input class=\"ui-input-hofo\" id=\"ContractType1AN\" type=text value=\"Auftragsnummer\" onclick=\"ClearValue(\\\'ContractType1AN\\\');\"/><input class=\"ui-input-hofo\" id=\"ContractType1PV\" type=text value=\"Produkt Version\" onclick=\"ClearValue(\\\'ContractType1PV\\\');\"/><input class=\"ui-input-hofo\" id=\"ContractType1PI\" type=text value=\"Produkt Information\" onclick=\"ClearValue(\\\'ContractType1PI\\\');\"/></td></tr>" +
                "<tr><td><div id=\"ContractTypeDiv2\"><select class=\"ui-input-hofo\" id=\"ContractType2\"><option selected value=\"0000\">Vertrag 2: Bitte Ausw&auml;hlen</option></select></div><input class=\"ui-input-hofo\" id=\"ContractType2AN\" type=text value=\"Auftragsnummer\" onclick=\"ClearValue(\\\'ContractType2AN\\\');\"/><input class=\"ui-input-hofo\" id=\"ContractType2PV\" type=text value=\"Produkt Version\" onclick=\"ClearValue(\\\'ContractType2PV\\\');\"/><input class=\"ui-input-hofo\" id=\"ContractType2PI\" type=text value=\"Produkt Information\" onclick=\"ClearValue(\\\'ContractType2PI\\\');\"/></td></tr>" +
                "<tr><td><div id=\"ContractTypeDiv3\"><select class=\"ui-input-hofo\" id=\"ContractType3\"><option selected value=\"0000\">Vertrag 3: Bitte Ausw&auml;hlen</option></select></div><input class=\"ui-input-hofo\" id=\"ContractType3AN\" type=text value=\"Auftragsnummer\" onclick=\"ClearValue(\\\'ContractType3AN\\\');\"/><input class=\"ui-input-hofo\" id=\"ContractType3PV\" type=text value=\"Produkt Version\" onclick=\"ClearValue(\\\'ContractType3PV\\\');\"/><input class=\"ui-input-hofo\" id=\"ContractType3PI\" type=text value=\"Produkt Information\" onclick=\"ClearValue(\\\'ContractType3PI\\\');\"/></td></tr>" +
                "<tr><td><div id=\"ContractTypeDiv4\"><select class=\"ui-input-hofo\" id=\"ContractType4\"><option selected value=\"0000\">Vertrag 4: Bitte Ausw&auml;hlen</option></select></div><input class=\"ui-input-hofo\" id=\"ContractType4AN\" type=text value=\"Auftragsnummer\" onclick=\"ClearValue(\\\'ContractType4AN\\\');\"/><input class=\"ui-input-hofo\" id=\"ContractType4PV\" type=text value=\"Produkt Version\" onclick=\"ClearValue(\\\'ContractType4PV\\\');\"/><input class=\"ui-input-hofo\" id=\"ContractType4PI\" type=text value=\"Produkt Information\" onclick=\"ClearValue(\\\'ContractType4PI\\\');\"/></td></tr>" +
                "<tr><td><div id=\"ContractTypeDiv5\"><select class=\"ui-input-hofo\" id=\"ContractType5\"><option selected value=\"0000\">Vertrag 5: Bitte Ausw&auml;hlen</option></select></div><input class=\"ui-input-hofo\" id=\"ContractType5AN\" type=text value=\"Auftragsnummer\" onclick=\"ClearValue(\\\'ContractType5AN\\\');\"/><input class=\"ui-input-hofo\" id=\"ContractType5PV\" type=text value=\"Produkt Version\" onclick=\"ClearValue(\\\'ContractType5PV\\\');\"/><input class=\"ui-input-hofo\" id=\"ContractType5PI\" type=text value=\"Produkt Information\" onclick=\"ClearValue(\\\'ContractType5PI\\\');\"/></td></tr>" +
                "<tr><td><div id=\"ContractTypeDiv6\"><select class=\"ui-input-hofo\" id=\"ContractType6\"><option selected value=\"0000\">Vertrag 6: Bitte Ausw&auml;hlen</option></select></div><input class=\"ui-input-hofo\" id=\"ContractType6AN\" type=text value=\"Auftragsnummer\" onclick=\"ClearValue(\\\'ContractType6AN\\\');\"/><input class=\"ui-input-hofo\" id=\"ContractType6PV\" type=text value=\"Produkt Version\" onclick=\"ClearValue(\\\'ContractType6PV\\\');\"/><input class=\"ui-input-hofo\" id=\"ContractType6PI\" type=text value=\"Produkt Information\" onclick=\"ClearValue(\\\'ContractType6PI\\\');\"/></td></tr>" +
                "</table>" +
                "</div>" +
                "</div>');\n" +
                "" +
                "    $('#ccommc').jqte();\n" +
                "    $('#caddress').bind('copy paste', function (e) { e.preventDefault(); });\n" +
                "" +
                "    $('#CreateCustomer').dialog({\n" +
                "        autoOpen: true,\n" +
                "        height: 640,\n" +
                "        width: 1205,\n" +
                "        draggable: false,\n" +
                "        resizable: false,\n" +
                "        modal: true,\n" +
                "        open: function() {\n" +
                "            $.ajax({\n" +
                "                url: '/gateway/exec/GetContractTypes',\n" +
                "                crossDomain: true,\n" +
                "                success: function(json) {\n" +
                "                    $.each(json.CONTRACT_TYPES, function() {\n" +
                "                        $('#ContractType1').append('<option value=\"' + $.base64.decode( this.ID ) + '\">' + $.base64.decode( this.LN ) + ' (' + $.base64.decode( this.SN ) + ')</option>');\n" +
                "                        $('#ContractType2').append('<option value=\"' + $.base64.decode( this.ID ) + '\">' + $.base64.decode( this.LN ) + ' (' + $.base64.decode( this.SN ) + ')</option>');\n" +
                "                        $('#ContractType3').append('<option value=\"' + $.base64.decode( this.ID ) + '\">' + $.base64.decode( this.LN ) + ' (' + $.base64.decode( this.SN ) + ')</option>');\n" +
                "                        $('#ContractType4').append('<option value=\"' + $.base64.decode( this.ID ) + '\">' + $.base64.decode( this.LN ) + ' (' + $.base64.decode( this.SN ) + ')</option>');\n" +
                "                        $('#ContractType5').append('<option value=\"' + $.base64.decode( this.ID ) + '\">' + $.base64.decode( this.LN ) + ' (' + $.base64.decode( this.SN ) + ')</option>');\n" +
                "                        $('#ContractType6').append('<option value=\"' + $.base64.decode( this.ID ) + '\">' + $.base64.decode( this.LN ) + ' (' + $.base64.decode( this.SN ) + ')</option>');\n" +
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
                "        }," +
                "        buttons: { \n" +
                "            EINTRAGEN: function() {\n" +
                "                var ccomm = $.base64.encode( $('#ccomm div.jqte_editor').html() ).replace(/\\+/g,'78');\n" +
                "                var caddress = $.base64.encode( $('#caddress').html() ).replace(/\\+/g,'78');\n" +
                "                $.ajax({\n" +
                "                    url: '/gateway/exec/CreateCustomer?cname=' + $.base64.encode( $('#cname').val() ) + '&cnumber=' + $.base64.encode( $('#cnumber').val() ) + '&cmail=' + $.base64.encode( $('#cmail').val() ) + '&cesmail=' + $.base64.encode( $('#cesmail').val() ) + '&caddress=' + caddress + '&ccomm=' + ccomm + '&ct1=' + $.base64.encode( $('#ContractType1').val() ) + '&ct1an=' + $.base64.encode( $('#ContractType1AN').val() ) + '&ct1pv=' + $.base64.encode( $('#ContractType1PV').val() ) + '&ct1pi=' + $.base64.encode( $('#ContractType1PI').val() ) + '&ct2=' + $.base64.encode( $('#ContractType2').val() ) + '&ct2an=' + $.base64.encode( $('#ContractType2AN').val() ) + '&ct2pv=' + $.base64.encode( $('#ContractType2PV').val() ) + '&ct2pi=' + $.base64.encode( $('#ContractType2PI').val() ) + '&ct3=' + $.base64.encode( $('#ContractType3').val() ) + '&ct3an=' + $.base64.encode( $('#ContractType3AN').val() ) + '&ct3pv=' + $.base64.encode( $('#ContractType3PV').val() ) + '&ct3pi=' + $.base64.encode( $('#ContractType3PI').val() ) + '&ct4=' + $.base64.encode( $('#ContractType4').val() ) + '&ct4an=' + $.base64.encode( $('#ContractType4AN').val() ) + '&ct4pv=' + $.base64.encode( $('#ContractType4PV').val() ) + '&ct4pi=' + $.base64.encode( $('#ContractType4PI').val() ) + '&ct5=' + $.base64.encode( $('#ContractType5').val() ) + '&ct5an=' + $.base64.encode( $('#ContractType5AN').val() ) + '&ct5pv=' + $.base64.encode( $('#ContractType5PV').val() ) + '&ct5pi=' + $.base64.encode( $('#ContractType5PI').val() ) + '&ct6=' + $.base64.encode( $('#ContractType6').val() ) + '&ct6an=' + $.base64.encode( $('#ContractType6AN').val() ) + '&ct6pv=' + $.base64.encode( $('#ContractType6PV').val() ) + '&ct6pi=' + $.base64.encode( $('#ContractType6PI').val() ),\n" +
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
                "        url: '/gateway/exec/GetSingleCustomer?cuid=' + $.base64.encode( $('#cnameEselect').val() ),\n" +
                "        crossDomain: true,\n" +
                "        success: function(json) {\n" +
                "            $.each(json.CUSTOMER, function() {\n" +
                "                $('#cnumberE').val($.base64.decode( this.CUNR ));\n" +
                "                $('#cmailE').val($.base64.decode( this.CUMAIL ));\n" +
                "                $('#cesmailE').val($.base64.decode( this.CUESKMAIL ));\n" +
                "                $('#caddressE').html($.base64.decode( this.CUADDR ));\n" +
                "                $('#ccommE div.jqte_editor').html($.base64.decode( this.CUCOMM ));\n" +
                "            });\n" +
                "            $('#ContractsDivInner').html('');\n" +
                "            $.each(json.CONTRACTS, function() {\n" +
                "                var img = '';\n" +
                "                if(json.CONTRACTS.length > 1) { img = '<img src=\"public/images/minus-circle.png\" title=\"Vertrag l&ouml;schen\" onclick=\"DeleteContract(\\\'' + $.base64.encode( $('#cnameEselect').val() ) + '\\\',\\\'' + this.CCID + '\\\');\"/>'; }\n" +
                "                $('#ContractsDivInner').append('<div id=\"CT_Outer\"><div id=\"ContractsDelete\">' + img + '</div><div id=\"CT_Head\"><span>Vertrag: </span><span>' + $.base64.decode( this.COTRLN ) + '</span></div><div id=\"CT_AN\"><span>Auftragsnummer: </span><span>' + $.base64.decode( this.CCNR ) + '</span></div><div id=\"CT_PR\"><span>Umgebung: </span><span>' + $.base64.decode( this.CCPRDC ) + ' (' + $.base64.decode( this.CCPRVE ) + ')</span></div></div>');\n" +
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
                "<tr><td><span style=\"float: left\">Kundenname</span><span style=\"float: left; margin-top: 0px;\" class=\"ui-icon ui-icon-triangle-1-s\"></span></td></tr><tr><td><div id=\"cnameE\"><select class=\"ui-input-hofo\" id=\"cnameEselect\" onchange=\"GetSingleCustomer(\\\'cnameEselect\\\');\"><option selected value=\"0000\">Kunde w&auml;hlen</option></select></div></td></tr><tr><td><span style=\"float: left\">Kundennummer</span><span style=\"float: left; margin-top: 0px;\" class=\"ui-icon ui-icon-triangle-1-s\"></span></td></tr><tr><td><input class=\"ui-input-hofo\" type=\"text\" id=\"cnumberE\" /></td></tr>" +
                "<tr><td><span style=\"float: left\">Kontakt E-Mail Adressen (mail1,mai2,mailn)</span><span style=\"float: left; margin-top: 0px;\" class=\"ui-icon ui-icon-triangle-1-s\"></span></td></tr><tr><td><input class=\"ui-input-hofo\" type=\"text\" id=\"cmailE\" /></td></tr><tr><td><span style=\"float: left\">Eskalationsmailadressen (mail1,mail2,mailn)</span><span style=\"float: left; margin-top: 0px;\" class=\"ui-icon ui-icon-triangle-1-s\"></span></td></tr><tr><td><input class=\"ui-input-hofo\" type=\"text\" id=\"cesmailE\" /></td></tr>" +
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
                "<tr><td><div id=\"ContractTypeDiv1\"><select class=\"ui-input-hofo\" id=\"ContractType1\"><option selected value=\"0000\">Vertrag 1: Bitte Ausw&auml;hlen</option></select></div><input class=\"ui-input-hofo\" id=\"ContractType1AN\" type=text value=\"Auftragsnummer\" onclick=\"ClearValue(\\\'ContractType1AN\\\');\"/><input class=\"ui-input-hofo\" id=\"ContractType1PV\" type=text value=\"Produkt Version\" onclick=\"ClearValue(\\\'ContractType1PV\\\');\"/><input class=\"ui-input-hofo\" id=\"ContractType1PI\" type=text value=\"Produkt Information\" onclick=\"ClearValue(\\\'ContractType1PI\\\');\"/></td></tr>" +
                "</table>" +
                "</div>" +
                "</div>');\n" +
                "" +
                "    $('#ccommEc').jqte();\n" +
                "    $('#caddressE').bind('copy paste', function (e) { e.preventDefault(); });\n" +
                "" +
                "    $('#CreateCustomer').dialog({\n" +
                "        autoOpen: true,\n" +
                "        height: 640,\n" +
                "        width: 1205,\n" +
                "        draggable: false,\n" +
                "        resizable: false,\n" +
                "        modal: true,\n" +
                "        open: function() {\n" +
                "            $.ajax({\n" +
                "                url: '/gateway/exec/GetContractTypes',\n" +
                "                crossDomain: true,\n" +
                "                success: function(json) {\n" +
                "                    $.each(json.CONTRACT_TYPES, function() {\n" +
                "                        $('#ContractType1').append('<option value=\"' + $.base64.decode( this.ID ) + '\">' + $.base64.decode( this.LN ) + ' (' + $.base64.decode( this.SN ) + ')</option>');\n" +
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
                "                        $('#cnameEselect').append('<option value=\"' + $.base64.decode( this.CUID ) + '\">' + $.base64.decode( this.CUNM ) + '</option>');\n" +
                "                    });\n" +
                /*"                    $('#cnameEselect').selectmenu({ width:385,menuWidth:385,style:'dropdown',maxHeight:200 });\n" +*/
                "                },\n" +
                "                dataType: 'json',\n" +
                "                cache: false\n" +
                "            });\n" +
                "        }," +
                "        buttons: { \n" +
                "            BEARBEITEN: function() {\n" +
                "                var ccommE = $.base64.encode( $('#ccommE div.jqte_editor').html() ).replace(/\\+/g,'78');\n" +
                "                var caddressE = $.base64.encode( $('#caddressE').html() ).replace(/\\+/g,'78');\n" +
                "                $.ajax({\n" +
                "                    url: '/gateway/exec/UpdateCustomer?cuid=' + $.base64.encode( $('#cnameEselect').val() ) + '&cname=' + $.base64.encode( $('#cnameEselect option:selected').text() ) + '&cnumber=' + $.base64.encode( $('#cnumberE').val() ) + '&cmail=' + $.base64.encode( $('#cmailE').val() ) + '&cesmail=' + $.base64.encode( $('#cesmailE').val() ) + '&caddress=' + caddressE + '&ccomm=' + ccommE + '&ct1=' + $.base64.encode( $('#ContractType1').val() ) + '&ct1an=' + $.base64.encode( $('#ContractType1AN').val() ) + '&ct1pv=' + $.base64.encode( $('#ContractType1PV').val() ) + '&ct1pi=' + $.base64.encode( $('#ContractType1PI').val() ),\n" +
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
                "        height: 210,\n" +
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
                "                        $('#cnameEselect').append('<option value=\"' + $.base64.decode( this.CUID ) + '\">' + $.base64.decode( this.CUNM ) + '</option>');\n" +
                "                    });\n" +
                /*"                    $('#cnameEselect').selectmenu({ width:385,menuWidth:385,style:'dropdown',maxHeight:200 });\n" +*/
                "                },\n" +
                "                dataType: 'json',\n" +
                "                cache: false\n" +
                "            });\n" +
                "        }," +
                "        buttons: { \n" +
                "            ENTFERNEN: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/gateway/exec/DeleteCustomer?cuid=' + $.base64.encode( $('#cnameEselect').val() ),\n" +
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
                "<tr><td><input class=\"ui-input-hofo\" type=\"text\" value=\"IH\" id=\"cotrsn\" onclick=\"ClearValue(\\'cotrsn\\');\"/></td><td><input class=\"ui-input-hofo\" type=\"text\" value=\"Inhouse\" id=\"cotrln\" onclick=\"ClearValue(\\'cotrln\\');\"/></td></tr>" +
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
                "        height: 615,\n" +
                "        width: 583,\n" +
                "        draggable: false,\n" +
                "        resizable: false,\n" +
                "        modal: true,\n" +
                "        open: function() {\n" +
                "        }," +
                "        buttons: { \n" +
                "            ANLEGEN: function() {\n" +
                "                var mactions = $.base64.encode( $('#mactions div.jqte_editor').html() ).replace(/\\+/g,'78');\n" +
                "                $.ajax({\n" +
                "                    url: '/gateway/exec/CreateContractType?cotrsn=' + $.base64.encode( $('#cotrsn').val() ) + '&cotrln=' + $.base64.encode( $('#cotrln').val() ) + '&mactions=' + mactions,\n" +
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
                "        url: '/gateway/exec/GetSingleContractType?cttyid=' + $.base64.encode( $('#' + id).val() ),\n" +
                "        crossDomain: true,\n" +
                "        success: function(json) {\n" +
                "            $.each(json.CONTRACT, function() {\n" +
                "                $('#cotrlnE').html($.base64.decode( this.COTRLN ));\n" +
                "                $('#mactionsE div.jqte_editor').html($.base64.decode( this.MACTIONS ));\n" +
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
                "        height: 615,\n" +
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
                "                        $('#cotrsnEselect').append('<option value=\"' + $.base64.decode( this.ID ) + '\">' + $.base64.decode( this.LN ) + ' (' + $.base64.decode( this.SN ) + ')</option>');\n" +
                "                    });\n" +
                /*"                    $('#cotrsnEselect').selectmenu({ width:255,menuWidth:385,style:'dropdown',maxHeight:200 });\n" +*/
                "                },\n" +
                "                dataType: 'json',\n" +
                "                cache: false\n" +
                "            });\n" +
                "        },\n" +
                "        buttons: { \n" +
                "            BEARBEITEN: function() {\n" +
                "                var mactions = $.base64.encode( $('#mactionsE div.jqte_editor').html() ).replace(/\\+/g,'78');\n" +
                "                var cotrln = $.base64.encode( $('#cotrlnE').html() ).replace(/\\+/g,'78');\n" +                
                "                $.ajax({\n" +
                "                    url: '/gateway/exec/UpdateContractType?cttyid=' + $.base64.encode( $('#cotrsnEselect').val() ) + '&cotrsn=' + $.base64.encode( $('#cotrsnEselect').text() ) + '&cotrln=' + cotrln + '&mactions=' + mactions,\n" +
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
                "                        $('#cotrsnEselect').append('<option value=\"' + $.base64.decode( this.ID ) + '\">' + $.base64.decode( this.LN ) + ' (' + $.base64.decode( this.SN ) + ')</option>');\n" +
                "                    });\n" +
                /*"                    $('#cotrsnEselect').selectmenu({ width:385,menuWidth:385,style:'dropdown',maxHeight:200 });\n" +*/
                "                },\n" +
                "                dataType: 'json',\n" +
                "                cache: false\n" +
                "            });\n" +
                "        },\n" +
                "        buttons: { \n" +
                "            ENTFERNEN: function() {\n" +
                "                $.ajax({\n" +
                "                    url: '/gateway/exec/DeleteContractType?cttyid=' + $.base64.encode( $('#cotrsnEselect').val() ),\n" +
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
                "                $('#WLI_Cont').append('<div id=\"LoggedInUser\" class=\"ui-input-hofo\"><img style=\"float: left; width: 50px; height: 50px; \" id=\"imgLU' + i + '\" src=\"public/images/DefaultProfile.png\" /><span id=\"LIU_Head\">' + $.base64.decode( this.USDC ) + '</span><span class=\"ui-icon ui-icon-mail-closed\" onclick=\"SendMailTo(\\\'' + $.base64.decode( this.UMAI ) + '\\\');\" title=\"Mail senden\"></span><span style=\"float: left;\">' + $.base64.decode( this.UMAI ) + '</span></div>');\n" +
                "                if(this.PCTRL != '0') { $('img#imgLU' + i).attr('src','/gateway/exec/UserPicture?user=' + $.base64.decode( this.USNM ) + ''); }\n" +
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
                "function GetSingleCustomerInfo(id) {\n" +
                "    $.ajax({\n" +
                "        url: '/gateway/exec/GetCustomerContractNumbers?cuid=' + $.base64.encode( $('#' + id).val() ),\n" +
                "        crossDomain: true,\n" +
                "        success: function(json) {\n" +
                "            $('#CreateServiceEntryAselect').html('<option value=\"0000\" selected>Bitte w&auml;hlen</option>');\n" +
                "            $.each(json.CONTRACT, function() {\n" +
                "                $('#CreateServiceEntryAselect').append('<option value=\"' + $.base64.decode( this.CCID ) + '\">' + $.base64.decode( this.COTRLN ) + ' (' + $.base64.decode( this.CCNR ) + ')</option>');\n" +
                "            });\n" +
                //"            $('#CreateServiceEntryAselect').selectmenu({ width:385,menuWidth:385,style:'dropdown',maxHeight:200 });\n" +
                "        },\n" +
                "        dataType: 'json',\n" +
                "        cache: false\n" +
                "    });\n" +
                "    $.ajax({\n" +
                "        url: '/gateway/exec/GetCustomerMailing?cuid=' + $.base64.encode( $('#' + id).val() ) + '&uuid=' + $.base64.encode( UUID ),\n" +
                "        crossDomain: true,\n" +
                "        success: function(json) {\n" +
                "            $('#InAN').val($.base64.decode( json.TO ));\n" +
                "            $('#InEsk1').val($.base64.decode( json.ESK1 ));\n" +
                "            $('#InEsk2').val($.base64.decode( json.ESK2 ));\n" +
                "            $('#InEsk3').val($.base64.decode( json.ESK3 ));\n" +
                "        },\n" +
                "        dataType: 'json',\n" +
                "        cache: false\n" +
                "    });\n" +
                "}\n" +
            "");
            
            out.println("" +
                "function ActivateMailing(id) {\n" +
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
                "        $('#CreateServiceEntryLeftMC div.jqte').css('height','670px');" +
                "        $('#CreateServiceEntryLeftMC .jqte_editor').css('height','621px');" +
                "        $('#CreateServiceEntryLeftMC .jqte_source').css('height','621px');" +
                "        $('#CSEDsc2').css('color','#000');\n" +
                "    }" +
                "}\n" +
            "");
            
            out.println("" +
                "function ShowEscalationSubject(dsc) {\n" +
                "    $('#InSub').val('!!! Wichtig !!! - Eskalation ' + dsc + ': Information zur laufenden Wartung Ihrer Systeme');\n" +
                "}\n" +
            "");
            
            out.println("" +
                "function ChangeSubject(id1,id2) {\n" +
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
                "<div id=\"CreateServiceEntryC\"><div id=\"CSEDsc\"><span style=\"float: left\">Arbeiten wurden ausgef&uuml;hrt f&uuml;r</span><span style=\"float: left; margin-top: 0px;\" class=\"ui-icon ui-icon-triangle-1-s\"></span></div><div id=\"CreateServiceEntryCs\"><select class=\"ui-input-hofo\" id=\"CreateServiceEntryCselect\" onchange=\"GetSingleCustomerInfo(\\\'CreateServiceEntryCselect\\\');\"><option selected value=\"0000\">Kunde w&auml;hlen</option></select></div></div>" +
                "<div id=\"CreateServiceEntryA\"><div id=\"CSEDsc\"><span style=\"float: left\">Auftragsnummer</span><span style=\"float: left; margin-top: 0px;\" class=\"ui-icon ui-icon-triangle-1-s\"></span></div><select class=\"ui-input-hofo\" id=\"CreateServiceEntryAselect\" onchange=\"ChangeSubject(\\\'CreateServiceEntryCselect\\\',\\\'CreateServiceEntryAselect\\\');\"><option selected value=\"0000\">-</option></select></div>" +
                "<div id=\"CreateServiceEntryT\"><div id=\"CSEDsc\"><span style=\"float: left\">Typ des Eintrags</span><span style=\"float: left; margin-top: 0px;\" class=\"ui-icon ui-icon-triangle-1-s\"></span></div><div id=\"CreateServiceEntryTs\"><select class=\"ui-input-hofo\" id=\"CreateServiceEntryTselect\"><option selected value=\"0000\">Bitte w&auml;hlen</option></select></div></div>" +
                "<div id=\"CreateServiceEntryS\"><div id=\"CSEDsc\"><span style=\"float: left\">Zeitstempel</span><span style=\"float: left; margin-top: 0px;\" class=\"ui-icon ui-icon-triangle-1-s\"></span></div><input class=\"ui-input-hofo\" id=\"CreateServiceEntryTime\" type=text value=\"' + now.format(\"yyyy-mm-dd HH:MM:ss\") + '\" /></div>" +
                "<div id=\"CreateServiceEntryD\"><div id=\"CSEDsc\"><span style=\"float: left\">Dauer (min)</span><span style=\"float: left; margin-top: 0px;\" class=\"ui-icon ui-icon-triangle-1-s\"></span></div><input class=\"ui-input-hofo\" id=\"CreateServiceEntryDelay\" type=text value=\"15\" /></div>" +
                "<div id=\"CreateServiceEntryM\">" +
                "<div id=\"CSEDsc2\">Soll der Eintrag per Mail an den Kunden verschickt werden?" +
                "<input id=\"CSEDscCB\" type=\"checkbox\" value=\"1\" name=\"mailingto\" onclick=\"ActivateMailing(\\\'CSEDscCB\\\');\"></div>" +
                "<div id=\"CSEDscOverLay\"></div>" +
                "<div id=\"InANt\">An:</div><input class=\"ui-input-hofo\" id=\"InAN\" type=\"text\" />" +
                "<div id=\"InCCt\">Cc:</div><input class=\"ui-input-hofo\" id=\"InCC\" type=\"text\" />" +
                "<div id=\"InSubt\">Btr.</div><input class=\"ui-input-hofo\" id=\"InSub\" type=\"text\" value=\"\" />" +
                "<div id=\"CSEDsc2\">Soll der Eintrag eskaliert werden?</div>" +
                "<div id=\"CSEDsc3\" class=\"CSEDsc3a\"><span>Stufe 1 - erweitert den Betreff, CC bis in die FGL Ebene</span><input onclick=\"ShowEscalationSubject(\\\'Stufe 1\\\');\" type=\"radio\" value=\"1\" name=\"escalate\" /></div>" +
                "<input class=\"ui-input-hofo\" id=\"InEsk1\" type=\"text\" value=\"\" />" +
                "<div id=\"CSEDsc3\" class=\"CSEDsc3b\">Stufe 2 - erweitert den Betreff, CC bis in die AL Ebene<input onclick=\"ShowEscalationSubject(\\\'Stufe 2\\\');\" type=\"radio\" value=\"2\" name=\"escalate\" /></div>" +
                "<input class=\"ui-input-hofo\" id=\"InEsk2\" type=\"text\" value=\"\" />" +
                "<div id=\"CSEDsc3\" class=\"CSEDsc3c\">Stufe 3 - erweitert den Betreff, CC bis in die GF Ebene<input onclick=\"ShowEscalationSubject(\\\'Stufe 3\\\');\" type=\"radio\" value=\"3\" name=\"escalate\" /></div>" +
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
                "        height: 805,\n" +
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
                "                        $('#CreateServiceEntryCselect').append('<option value=\"' + $.base64.decode( this.CUID ) + '\">' + $.base64.decode( this.CUNM ) + '</option>');\n" +
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
                "                        $('#CreateServiceEntryTselect').append('<option value=\"' + $.base64.decode( this.ID ) + '\">' + $.base64.decode( this.LN ) + '</option>');\n" +
                "                    });\n" +
                //"                    $('#CreateServiceEntryTselect').selectmenu({ width:385,menuWidth:385,style:'dropdown',maxHeight:200 });\n" +
                "                },\n" +
                "                dataType: 'json',\n" +
                "                cache: false\n" +
                "            });\n" +
                "            $.ajax({\n" +
                "                url: '/gateway/exec/GetUserMailFormat?uuid=' + $.base64.encode( UUID ),\n" +
                "                crossDomain: true,\n" +
                "                success: function(json) {\n" +
                "                    $('#CreateServiceEntryLeftMF div.jqte_editor').html($.base64.decode( json.FOOTER ));\n" +
                "                    $('#CreateServiceEntryLeftMH div.jqte_editor').html($.base64.decode( json.HEADER ));\n" +
                "                    $('#InCC').val($.base64.decode( json.CC ));\n" +
                "                },\n" +
                "                dataType: 'json',\n" +
                "                cache: false\n" +
                "            });\n" +
                "        }," +
                "        buttons: { \n" +
                "            EINTRAGEN: function() {\n" +
                "                var esk=0;" +
                "                var rep=0;" +
                "                var err=0;" +
                "                var content = $.base64.encode( $('#CreateServiceEntryLeftMC div.jqte_editor').html() ).replace(/\\+/g,'78');\n" +
                "                var text = $.base64.encode( $('#CreateServiceEntryLeftMH div.jqte_editor').html() + '<br>' + $('#CreateServiceEntryLeftMC div.jqte_editor').html() + '<br>' + $('#CreateServiceEntryLeftMF div.jqte_editor').html() ).replace(/\\+/g,'78');\n" +
                "                if( $('#CSEDscCB').is(':checked') ) {\n" +
                "                    var cc = $('#InCC').val();\n" +
                "                    $('input:radio[name=\"escalate\"]').each( function() {\n" +
                "                        if ($(this).is(':checked')) {\n" +
                "                            var c = $(this).val();\n" +
                "                            cc += ',' + $('#InEsk' + c).val();\n" +
                "                            esk=c;\n" +
                "                        }\n" +
                "                    });" +
                "                    var an = $('#InAN').val();\n" +
                "                    $.ajax({\n" +
                "                        url: '/gateway/exec/SendHtmlMail?to=' + $.base64.encode( an ) + '&cc=' + $.base64.encode( cc ) + '&from=' + $.base64.encode( $('#InFrom').val() ) + '&subject=' + $.base64.encode( $('#InSub').val() ) + '&text=' + text,\n" +
                "                        crossDomain: true,\n" +
                "                        success: function(json) {\n" +
                "                            if (json.SEND == '1') {\n" +
                "                                DialogMailComplete(\"#MSDialogSuccess\",\"E-Mail erfolgreich versendet.\",\"Die E-Mail an \" + an + \" wurde erfolgreich versendet.\");\n" +
                "                            } else {\n" +
                "                                DialogMailComplete(\"#MSDialogSuccess\",\"+++ E-Mail konnte nicht versendet werden. +++\",\"<font color='#ff7777'>Die E-Mail an \" + an + \" konnte nicht versendet werden.</font>\");\n" +
                "                            }\n" +
                "                        },\n" +
                "                        dataType: 'json',\n" +
                "                        cache: false\n" +
                "                    });\n" +
                "                }\n" +
                "                $.ajax({\n" +
                "                    url: '/gateway/exec/CreateServiceEntry?uuid=' + $.base64.encode( UUID ) + '&cuid=' + $.base64.encode( $('#CreateServiceEntryCselect').val() ) + '&ccid=' + $.base64.encode( $('#CreateServiceEntryAselect').val() ) + '&comtid=' + $.base64.encode( $('#CreateServiceEntryTselect').val() ) + '&tm=' + $.base64.encode( $('#CreateServiceEntryTime').val() ) + '&dl=' + $.base64.encode( $('#CreateServiceEntryDelay').val() ) + '&co=' + content + '&esk=' + $.base64.encode( esk ) + '',\n" +
                "                    crossDomain: true,\n" +
                "                    success: function(json) {\n" +
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
                "                    cache: false\n" +
                "                });\n" +
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
                "        url: '/gateway/exec/GetServiceEntry?uuid=' + $.base64.encode( UUID ) + '&offset=' + $.base64.encode( offset ) + '&limit=' + $.base64.encode( limit ),\n" +
                "        crossDomain: true,\n" +
                "        success: function(json) {\n" +
                "            var i = 0;\n" +
                "            $('#ManagedServiceActionsPage').html('<div id=\"ManagedServiceActionsPageSelected\"></div>')\n" +
                "            var count = $.base64.decode( json.COUNT );\n" +
                "            PagingServiceEntry(count,limit,pnumber);\n" +
                "            $('#ManagedServiceActions').html('<div id=\"ManagedServiceActionsC\"></div>');\n" +
                "            if(json.ROWS.length == 0) { $('#ManagedServiceActionsC').html('<span class=\"NoEntry\">Keine Eintr&auml;ge vorhanden.</span>'); }\n" +
                "            $.each(json.ROWS, function() {\n" +
                "                var now = new Date($.base64.decode( this.TS ) * 1000);\n" +
                "                var esk = $.base64.decode( this.ESK );\n" +
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
                "                $('#ManagedServiceActionsC').append('<div id=\"ManagedServiceActionsE\"><table><tr><td><img id=\"img' + i + '\" src=\"public/images/DefaultProfile.png\" /></td><td><div id=\"ManagedServiceActionsH\">' + $.base64.decode( this.NAME ) + ' f&uuml;r ' + $.base64.decode( this.CUNM ) + '</div>" +
                "<div id=\"ManagedServiceActionsD\"><span id=\"EskDscD\">am ' + now.format(\"yyyy-mm-dd HH:MM:ss\") + ' | </span>' + eskt + '</div>" +
                "<div id=\"ManagedServiceActionsT\">' + $.base64.decode( this.TEXT ) + '</div>" +
                "<div id=\"ManagedServiceActionsF\"><span id=\"EskDscD\">Auftragsnummer: ' + $.base64.decode( this.AN ) + ' | Vertragstyp: ' + $.base64.decode( this.CONM ) + '</span></div>" +
                "</td></tr></table></div>');\n" +
                "                if(this.PCTRL != '0') { $('img#img' + i).attr('src','/gateway/exec/UserPicture?user=' + $.base64.decode( this.UID ) + ''); }\n" +
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
                    "        height: 650,\n" +
                    "        width: 1205,\n" +
                    "        draggable: false,\n" +
                    "        resizable: false,\n" +
                    "        modal: true,\n" +
                    "        open: function () {\n" +
                    "            $.ajax({\n" +
                    "                url: '/gateway/exec/GetSingleCustomer?cuid=' + $.base64.encode( cuid ),\n" +
                    "                crossDomain: true,\n" +
                    "                success: function(json) {\n" +
                    "                    $.each(json.CUSTOMER, function() {\n" +
                    "                        $('#cnameE').val($.base64.decode( this.CUNM ));\n" +
                    "                        $('#cnumberE').val($.base64.decode( this.CUNR ));\n" +
                    "                        $('#cmailE').val($.base64.decode( this.CUMAIL ));\n" +
                    "                        $('#cesmailE').val($.base64.decode( this.CUESKMAIL ));\n" +
                    "                        $('#caddressE').html($.base64.decode( this.CUADDR ));\n" +
                    "                        $('#ccommE').html($.base64.decode( this.CUCOMM ));\n" +
                    "                    });\n" +
                    "                    $('#ContractsDivInner').html('');\n" +
                    "                    $.each(json.CONTRACTS, function() {\n" +
                    "                        $('#ContractsDivInner').append('<div class=\"ui-input-hofo\" id=\"CT_Outer\"><div id=\"CT_Head\"><span>Vertrag: </span><span>' + $.base64.decode( this.COTRLN ) + '</span></div><div id=\"CT_AN\"><span>Auftragsnummer: </span><span>' + $.base64.decode( this.CCNR ) + '</span></div><div id=\"CT_PR\"><span>Umgebung: </span><span>' + $.base64.decode( this.CCPRDC ) + ' (' + $.base64.decode( this.CCPRVE ) + ')</span></div></div>');\n" +
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
                    "                $('#MSI_Cont').append('<div class=\"ui-input-hofo\" id=\"CustomerInfoEntry\" onclick=\"ShowCustomer(\\\'' + $.base64.decode( this.CUID ) + '\\\');\"><span id=\"CustomerInfoEntryHeader\">' + $.base64.decode( this.CUNM ) + '</span><br><span id=\"CustomerInfoEntryContent\">Kundennummer: ' + $.base64.decode( this.CUNR ) + '</span></div>');\n" +
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

