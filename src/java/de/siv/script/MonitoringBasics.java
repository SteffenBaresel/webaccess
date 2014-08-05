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
public class MonitoringBasics extends HttpServlet {
    Properties props = null;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response, String Uid)
            throws ServletException, IOException, FileNotFoundException, NamingException, SQLException {
        PrintWriter out = response.getWriter(); 
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "*");
        response.setContentType("application/javascript; charset=utf-8");
        
        if(Executions.UserIsPermitted(Uid,"monitoring")) {
        
            out.println(""
                    + "function ShowMonitoringFull(uid) {\n"
                    + "    $('#monitoring-view').html('<img id=\"monitoring-tab\" onclick=\"ShowMonitoringTab();\" src=\"public/images/grid-128.png\" title=\"Tab Ansicht\"/><img id=\"monitoring-list\" onclick=\"ShowMonitoringList();\" src=\"public/images/list-128.png\" title=\"Listen Ansicht\"/>');\n"
                    + "    if ($.cookie('view') == 'list') {\n"
                    + "        ShowMonitoringList();\n"
                    + "    } else {\n"
                    + "        ShowMonitoringTab();\n"
                    + "    }\n"
                    + "    setTimeout('ShowMonitoringFull()', 60000);\n"
                    + "}\n"
                    + "");
            
            out.println(""
                    + "function ShowMonitoringTab(uid) {\n"
                    + "    $('#monitoring-list').addClass('ui-opacity-med');\n"
                    + "    $('#monitoring-tab').removeClass('ui-opacity-med');\n"
                    + "    $.ajax({\n"
                    + "        url: '/gateway/monitoring/MonitoringFull',\n"
                    + "        crossDomain: true,\n"
                    + "        success: function(json) {\n"
                    + "            $('#div-monitoring').html('<div id=\"div-monitoring-content\"></div>');\n"
                    + "            $.each(json, function() {\n"
                    + "                var hstid = this.HOST_ID;\n"
                    + "                var hstna = this.HOST_NAME;\n"
                    + "                $('#div-monitoring-content').append('<div id=\"div-monitoring-entry\" class=\"ui-input-hofo-grey\"><img class=\"img-h\" src=\"public/images/' + base64_decode( this.HOST_TYPE ) + '-128.png\"  title=\"' + base64_decode( this.HOST_TYLN ) + '\" /><div class=\"ui-input-hofo-grey-header\"><span style=\"float: left;\">' + base64_decode( this.HOST_NAME ) + '</span><br><span id=\"c' + hstid + '\" class=\"content-h\"></span></div>');\n"
                    + "                $.each(this.SERVICES, function() {\n"
                    + "                    var csscl; var dtmcl;\n"
                    + "                    if (this.ACK == \"t\") { csscl = \"ack\"; } else { if (this.STATE == \"1\") { csscl = \"warning\"; } else if (this.STATE == \"2\") { csscl = \"critical\"; } else if (this.STATE == \"3\") { csscl = \"unknown\"; } else { csscl = \"ok\"; } }\n"
                    + "                    if (this.DTM == \"t\") { dtmcl='<img src=\"public/images/clock-small.png\" />'; } else { dtmcl=''; }\n"
                    + "                    $('#c' + hstid).append('<span style=\"cursor: pointer;\" class=\"ui-taov-tile ' + csscl + '\" title=\"' + base64_decode( this.SERVICE_NAME ) + '\" onclick=\"ServiceMenu(\\\'' + hstna + '\\\',\\\'' + hstid + '\\\',\\\'' + this.SERVICE_NAME + '\\\',\\\'' + this.SERVICE_ID + '\\\',\\\'' + this.INSTID + '\\\',\\\'' + this.ACK + '\\\',\\\'' + this.ACKID + '\\\',\\\'' + this.DTM + '\\\',\\\'' + this.DTMID + '\\\');\">' + dtmcl + '</span>');\n"
                    + "                });\n"
                    + "            });\n"
                    + "        },\n"
                    + "        dataType: 'json',\n"
                    + "        cache: false\n"
                    + "    });\n"
                    + "    $.cookie('view','tabs');\n"
                    + "}\n"
                    + "");
            
            out.println(""
                    + "function ShowMonitoringList(uid) {\n"
                    + "    $('#monitoring-tab').addClass('ui-opacity-med');\n"
                    + "    $('#monitoring-list').removeClass('ui-opacity-med');\n"
                    + "    $.ajax({\n"
                    + "        url: '/gateway/monitoring/MonitoringFull',\n"
                    + "        crossDomain: true,\n"
                    + "        success: function(json) {\n"
                    + "            $('#div-monitoring').html('<div id=\"div-monitoring-content\"></div>');\n"
                    + "            $.each(json, function() {\n"
                    + "                var hstid = this.HOST_ID;\n"
                    + "                var hstna = this.HOST_NAME;\n"
                    + "                $('#div-monitoring-content').append('<div id=\"content-list\" class=\"ui-input-hofo-g\"><img class=\"img-h\" src=\"public/images/' + base64_decode( this.HOST_TYPE ) + '-128.png\"  title=\"' + base64_decode( this.HOST_TYLN ) + '\" /><div class=\"ui-input-hofo-grey-header\"><span style=\"float: left;\">' + base64_decode( this.HOST_NAME ) + '</span><span style=\"float: left; margin-top: -1px;\" class=\"ui-icon ui-icon-triangle-1-s\"></span></div><div><span id=\"list-' + hstid + '\"></span></div>');\n"
                    + "                $.each(this.SERVICES, function() {\n"
                    + "                    var csscl; var dtmcl;\n"
                    + "                    if (this.ACK == \"t\") { csscl = \"ack\"; } else { if (this.STATE == \"1\") { csscl = \"warning\"; } else if (this.STATE == \"2\") { csscl = \"critical\"; } else if (this.STATE == \"3\") { csscl = \"unknown\"; } else { csscl = \"ok\"; } }\n"
                    + "                    if (this.DTM == \"t\") { dtmcl='<img src=\"public/images/clock-small.png\" />'; } else { dtmcl=''; }\n"
                    + "                    $('#list-' + hstid).append('<div class=\"content-list-entry\" title=\"Klicken um Bearbeitung zu &ouml;ffnen.\" onclick=\"ServiceMenu(\\\'' + hstna + '\\\',\\\'' + hstid + '\\\',\\\'' + this.SERVICE_NAME + '\\\',\\\'' + this.SERVICE_ID + '\\\',\\\'' + this.INSTID + '\\\',\\\'' + this.ACK + '\\\',\\\'' + this.ACKID + '\\\',\\\'' + this.DTM + '\\\',\\\'' + this.DTMID + '\\\');\"><span class=\"ui-taov-tile ' + csscl + '\">' + dtmcl + '</span><span>Service: <b>' + base64_decode( this.SERVICE_NAME )+ '</b></span><span>Datum: <b>' + this.CREATED_ISO + '</b></span><br><span>' + base64_decode( this.OUTPUT ) + '</span></div>');\n"
                    + "                });\n"
                    + "            });\n"
                    + "        },\n"
                    + "        dataType: 'json',\n"
                    + "        cache: false\n"
                    + "    }); \n"
                    + "    $.cookie('view','list');\n"
                    + "}\n"
                    + "");
            
            if(Executions.UserIsPermitted(Uid,"sidebarsearch")) {
        
                out.println("\n"
                    + "var ivalS = UrlDescape( urlPara('search-field') );\n"
                    + "var typeS = ivalS.substring(0, ivalS.indexOf(':'));\n"
                    + "var sstrS = $.trim(ivalS.substring(ivalS.indexOf(':')+1));\n"
                    + "var statS = UrlDescape( urlPara('s') );\n"
                    + "\n");
                
                out.println(""
                    + "function SearchHosts(uid) {\n"
                    + "    $('form#search-form input[name=search-field]').attr('value',ivalS);\n"
                    + "    $('#monitoring-view').html('<img id=\"monitoring-tab\" onclick=\"SearchHostsTab();\" src=\"public/images/grid-128.png\" title=\"Tab Ansicht\"/><img id=\"monitoring-list\" onclick=\"SearchHostsList();\" src=\"public/images/list-128.png\" title=\"Listen Ansicht\"/>');\n"
                    + "    if ($.cookie('view') == 'list') {\n"
                    + "        SearchHostsList();\n"
                    + "    } else {\n"
                    + "        SearchHostsTab();\n"
                    + "    }\n"
                    + "    setTimeout('SearchHosts()', 60000);\n"
                    + "}\n"
                    + "");
            
                out.println(""
                    + "function SearchHostsTab(uid) {\n"
                    + "    $('#monitoring-list').addClass('ui-opacity-med');\n"
                    + "    $('#monitoring-tab').removeClass('ui-opacity-med');\n"
                    + "    $.ajax({\n"
                    + "        url: '/gateway/search/SearchHosts',\n"
                    + "        data: {\n"
                    + "            v: base64_encode(sstrS)\n"
                    + "        },\n"
                    + "        success: function(json) {\n"
                    + "            $('#div-monitoring').html('<div id=\"div-monitoring-content\"></div>');\n"
                    + "            $.each(json, function() {\n"
                    + "                var hstid = this.HOST_ID;\n"
                    + "                var hstna = this.HOST_NAME;\n"
                    + "                $('#div-monitoring-content').append('<div id=\"div-monitoring-entry\" class=\"ui-input-hofo-grey\"><img class=\"img-h\" src=\"public/images/' + base64_decode( this.HOST_TYPE ) + '-128.png\"  title=\"' + base64_decode( this.HOST_TYLN ) + '\" /><div class=\"ui-input-hofo-grey-header\"><span style=\"float: left;\">' + base64_decode( this.HOST_NAME ) + '</span><span style=\"float: left; margin-top: -1px;\" class=\"ui-icon ui-icon-triangle-1-s\"></span><br><span id=\"c' + hstid + '\" class=\"content-h\"></span></div>');\n"
                    + "                $.each(this.SERVICES, function() {\n"
                    + "                    var csscl; var dtmcl;\n"
                    + "                    if (this.ACK == \"t\") { csscl = \"ack\"; } else { if (this.STATE == \"1\") { csscl = \"warning\"; } else if (this.STATE == \"2\") { csscl = \"critical\"; } else if (this.STATE == \"3\") { csscl = \"unknown\"; } else { csscl = \"ok\"; } }\n"
                    + "                    if (this.DTM == \"t\") { dtmcl='<img src=\"public/images/clock-small.png\" />'; } else { dtmcl=''; }\n"
                    + "                    $('#c' + hstid).append('<span style=\"cursor: pointer;\" class=\"ui-taov-tile ' + csscl + '\" title=\"' + base64_decode( this.SERVICE_NAME ) + '\" onclick=\"ServiceMenu(\\\'' + hstna + '\\\',\\\'' + hstid + '\\\',\\\'' + this.SERVICE_NAME + '\\\',\\\'' + this.SERVICE_ID + '\\\',\\\'' + this.INSTID + '\\\',\\\'' + this.ACK + '\\\',\\\'' + this.ACKID + '\\\',\\\'' + this.DTM + '\\\',\\\'' + this.DTMID + '\\\');\">' + dtmcl + '</span>');\n"
                    + "                });\n"
                    + "            });\n"
                    + "        },\n"
                    + "        dataType: 'json',\n"
                    + "        cache: false\n"
                    + "    });\n"
                    + "    $.cookie('view','tabs');\n"
                    + "}\n"
                    + "");
            
                out.println(""
                    + "function SearchHostsList(uid) {\n"
                    + "    $('#monitoring-tab').addClass('ui-opacity-med');\n"
                    + "    $('#monitoring-list').removeClass('ui-opacity-med');\n"
                    + "    $.ajax({\n"
                    + "        url: '/gateway/search/SearchHosts',\n"
                    + "        data: {\n"
                    + "            v: base64_encode(sstrS)\n"
                    + "        },\n"
                    + "        success: function(json) {\n"
                    + "            $('#div-monitoring').html('<div id=\"div-monitoring-content\"></div>');\n"
                    + "            $.each(json, function() {\n"
                    + "                var hstid = this.HOST_ID;\n"
                    + "                var hstna = this.HOST_NAME;\n"
                    + "                $('#div-monitoring-content').append('<div id=\"content-list\" class=\"ui-input-hofo-g\"><img class=\"img-h\" src=\"public/images/' + base64_decode( this.HOST_TYPE ) + '-128.png\"  title=\"' + base64_decode( this.HOST_TYLN ) + '\" /><div class=\"ui-input-hofo-grey-header\"><span style=\"float: left;\">' + base64_decode( this.HOST_NAME ) + '</span><span style=\"float: left; margin-top: -1px;\" class=\"ui-icon ui-icon-triangle-1-s\"></span></div><div><span id=\"list-' + hstid + '\"></span></div>');\n"
                    + "                $.each(this.SERVICES, function() {\n"
                    + "                    var csscl; var dtmcl;\n"
                    + "                    if (this.ACK == \"t\") { csscl = \"ack\"; } else { if (this.STATE == \"1\") { csscl = \"warning\"; } else if (this.STATE == \"2\") { csscl = \"critical\"; } else if (this.STATE == \"3\") { csscl = \"unknown\"; } else { csscl = \"ok\"; } }\n"
                    + "                    if (this.DTM == \"t\") { dtmcl='<img src=\"public/images/clock-small.png\" />'; } else { dtmcl=''; }\n"
                    + "                    $('#list-' + hstid).append('<div class=\"content-list-entry\" title=\"Klicken um Bearbeitung zu &ouml;ffnen.\" onclick=\"ServiceMenu(\\\'' + hstna + '\\\',\\\'' + hstid + '\\\',\\\'' + this.SERVICE_NAME + '\\\',\\\'' + this.SERVICE_ID + '\\\',\\\'' + this.INSTID + '\\\',\\\'' + this.ACK + '\\\',\\\'' + this.ACKID + '\\\',\\\'' + this.DTM + '\\\',\\\'' + this.DTMID + '\\\');\"><span class=\"ui-taov-tile ' + csscl + '\">' + dtmcl + '</span><span>Service: <b>' + base64_decode( this.SERVICE_NAME )+ '</b></span><span>Datum: <b>' + this.CREATED_ISO + '</b></span><br><span>' + base64_decode( this.OUTPUT ) + '</span></div>');\n"
                    + "                });\n"
                    + "            });\n"
                    + "        },\n"
                    + "        dataType: 'json',\n"
                    + "        cache: false\n"
                    + "    }); \n"
                    + "    $.cookie('view','list');\n"
                    + "}\n"
                    + "");
                
                out.println(""
                    + "function SearchServices(uid) {\n"
                    + "    $('form#search-form input[name=search-field]').attr('value',ivalS);\n"
                    + "    $('#monitoring-view').html('<img id=\"monitoring-tab\" onclick=\"SearchServicesTab();\" src=\"public/images/grid-128.png\" title=\"Tab Ansicht\"/><img id=\"monitoring-list\" onclick=\"SearchServicesList();\" src=\"public/images/list-128.png\" title=\"Listen Ansicht\"/>');\n"
                    + "    if ($.cookie('view') == 'list') {\n"
                    + "        SearchServicesList();\n"
                    + "    } else {\n"
                    + "        SearchServicesTab();\n"
                    + "    }\n"
                    + "    setTimeout('SearchServices()', 60000);\n"
                    + "}\n"
                    + "");
            
                out.println(""
                    + "function SearchServicesTab(uid) {\n"
                    + "    $('#monitoring-list').addClass('ui-opacity-med');\n"
                    + "    $('#monitoring-tab').removeClass('ui-opacity-med');\n"
                    + "    $.ajax({\n"
                    + "        url: '/gateway/search/SearchServices',\n"
                    + "        data: {\n"
                    + "            v: base64_encode(sstrS)\n"
                    + "        },\n"
                    + "        success: function(json) {\n"
                    + "            $('#div-monitoring').html('<div id=\"div-monitoring-content\"></div>');\n"
                    + "            $.each(json, function() {\n"
                    + "                var hstid = this.HOST_ID;\n"
                    + "                var hstna = this.HOST_NAME;\n"
                    //+ "                $('#div-monitoring-content').append('<div id=\"div-monitoring-entry\" class=\"ui-input-hofo-grey\"><img class=\"img-h\" src=\"public/images/' + base64_decode( this.HOST_TYPE ) + '-128.png\"  title=\"' + base64_decode( this.HOST_TYLN ) + '\" /><div class=\"ui-input-hofo-grey-header\"><span style=\"float: left;\">' + base64_decode( this.HOST_NAME ) + '</span><span style=\"float: left; margin-top: -1px;\" class=\"ui-icon ui-icon-triangle-1-s\"></span><br><span id=\"c' + hstid + '\" class=\"content-h\"></span></div>');\n"
                    + "                $.each(this.SERVICES, function() {\n"
                    + "                    var csscl; var dtmcl;\n"
                    + "                    if (this.ACK == \"t\") { csscl = \"ack2\"; } else { if (this.STATE == \"1\") { csscl = \"warning\"; } else if (this.STATE == \"2\") { csscl = \"critical\"; } else if (this.STATE == \"3\") { csscl = \"unknown\"; } else { csscl = \"ok\"; } }\n"
                    + "                    if (this.DTM == \"t\") { dtmcl='<img src=\"public/images/clock-small.png\" />'; } else { dtmcl=''; }\n"
                    + "                    $('#div-monitoring-content').append('<span id=\"service-tab\" class=\"' + csscl + '\" title=\"' + base64_decode( this.OUTPUT ) + '\" onclick=\"ServiceMenu(\\\'' + hstna + '\\\',\\\'' + hstid + '\\\',\\\'' + this.SERVICE_NAME + '\\\',\\\'' + this.SERVICE_ID + '\\\',\\\'' + this.INSTID + '\\\',\\\'' + this.ACK + '\\\',\\\'' + this.ACKID + '\\\',\\\'' + this.DTM + '\\\',\\\'' + this.DTMID + '\\\');\">' + dtmcl + 'Service: <b>' + base64_decode( this.SERVICE_NAME ) + '</b><br>Host: <b>' + base64_decode( hstna ) + '</b></span>');\n"
                    + "                });\n"
                    + "            });\n"
                    + "        },\n"
                    + "        dataType: 'json',\n"
                    + "        cache: false\n"
                    + "    });\n"
                    + "    $.cookie('view','tabs');\n"
                    + "}\n"
                    + "");
            
                out.println(""
                    + "function SearchServicesList(uid) {\n"
                    + "    $('#monitoring-tab').addClass('ui-opacity-med');\n"
                    + "    $('#monitoring-list').removeClass('ui-opacity-med');\n"
                    + "    $.ajax({\n"
                    + "        url: '/gateway/search/SearchServices',\n"
                    + "        data: {\n"
                    + "            v: base64_encode(sstrS)\n"
                    + "        },\n"
                    + "        success: function(json) {\n"
                    + "            $('#div-monitoring').html('<div id=\"div-monitoring-content\"></div>');\n"
                    + "            $.each(json, function() {\n"
                    + "                var hstid = this.HOST_ID;\n"
                    + "                var hstna = this.HOST_NAME;\n"
                    + "                $('#div-monitoring-content').append('<div id=\"content-list\" class=\"ui-input-hofo-g\"><img class=\"img-h\" src=\"public/images/' + base64_decode( this.HOST_TYPE ) + '-128.png\"  title=\"' + base64_decode( this.HOST_TYLN ) + '\" /><div class=\"ui-input-hofo-grey-header\"><span style=\"float: left;\">' + base64_decode( this.HOST_NAME ) + '</span><span style=\"float: left; margin-top: -1px;\" class=\"ui-icon ui-icon-triangle-1-s\"></span></div><div><span id=\"list-' + hstid + '\"></span></div>');\n"
                    + "                $.each(this.SERVICES, function() {\n"
                    + "                    var csscl; var dtmcl;\n"
                    + "                    if (this.ACK == \"t\") { csscl = \"ack\"; } else { if (this.STATE == \"1\") { csscl = \"warning\"; } else if (this.STATE == \"2\") { csscl = \"critical\"; } else if (this.STATE == \"3\") { csscl = \"unknown\"; } else { csscl = \"ok\"; } }\n"
                    + "                    if (this.DTM == \"t\") { dtmcl='<img src=\"public/images/clock-small.png\" />'; } else { dtmcl=''; }\n"
                    + "                    $('#list-' + hstid).append('<div class=\"content-list-entry\" title=\"Klicken um Bearbeitung zu &ouml;ffnen.\" onclick=\"ServiceMenu(\\\'' + hstna + '\\\',\\\'' + hstid + '\\\',\\\'' + this.SERVICE_NAME + '\\\',\\\'' + this.SERVICE_ID + '\\\',\\\'' + this.INSTID + '\\\',\\\'' + this.ACK + '\\\',\\\'' + this.ACKID + '\\\',\\\'' + this.DTM + '\\\',\\\'' + this.DTMID + '\\\');\"><span class=\"ui-taov-tile ' + csscl + '\">' + dtmcl + '</span><span>Service: <b>' + base64_decode( this.SERVICE_NAME )+ '</b></span><span>Datum: <b>' + this.CREATED_ISO + '</b></span><br><span>' + base64_decode( this.OUTPUT ) + '</span></div>');\n"
                    + "                });\n"
                    + "            });\n"
                    + "        },\n"
                    + "        dataType: 'json',\n"
                    + "        cache: false\n"
                    + "    }); \n"
                    + "    $.cookie('view','list');\n"
                    + "}\n"
                    + "");
                
                out.println(""
                    + "function ServiceStatus(uid) {\n"
                    + "    $('#monitoring-view').html('<img id=\"monitoring-tab\" onclick=\"ServiceStatusTab();\" src=\"public/images/grid-128.png\" title=\"Tab Ansicht\"/><img id=\"monitoring-list\" onclick=\"ServiceStatusList();\" src=\"public/images/list-128.png\" title=\"Listen Ansicht\"/>');\n"
                    + "    if ($.cookie('view') == 'list') {\n"
                    + "        ServiceStatusList();\n"
                    + "    } else {\n"
                    + "        ServiceStatusTab();\n"
                    + "    }\n"
                    + "    setTimeout('ShowServiceStatus()', 60000);\n"
                    + "}\n"
                    + "");
            
                out.println(""
                    + "function ServiceStatusTab(uid) {\n"
                    + "    $('#monitoring-list').addClass('ui-opacity-med');\n"
                    + "    $('#monitoring-tab').removeClass('ui-opacity-med');\n"
                    + "    $.ajax({\n"
                    + "        url: '/gateway/monitoring/ServiceStatus',\n"
                    + "        data: {\n"
                    + "            s: statS\n"
                    + "        },\n"
                    + "        success: function(json) {\n"
                    + "            $('#div-monitoring').html('<div id=\"div-monitoring-content\"></div>');\n"
                    + "            $.each(json, function() {\n"
                    + "                var hstid = this.HOST_ID;\n"
                    + "                var hstna = this.HOST_NAME;\n"
                    //+ "                $('#div-monitoring-content').append('<div id=\"div-monitoring-entry\" class=\"ui-input-hofo-grey\"><img class=\"img-h\" src=\"public/images/' + base64_decode( this.HOST_TYPE ) + '-128.png\"  title=\"' + base64_decode( this.HOST_TYLN ) + '\" /><div class=\"ui-input-hofo-grey-header\"><span style=\"float: left;\">' + base64_decode( this.HOST_NAME ) + '</span><span style=\"float: left; margin-top: -1px;\" class=\"ui-icon ui-icon-triangle-1-s\"></span><br><span id=\"c' + hstid + '\" class=\"content-h\"></span></div>');\n"
                    + "                $.each(this.SERVICES, function() {\n"
                    + "                    var csscl; var dtmcl;\n"
                    + "                    if (this.ACK == \"t\") { csscl = \"ack2\"; } else { if (this.STATE == \"1\") { csscl = \"warning\"; } else if (this.STATE == \"2\") { csscl = \"critical\"; } else if (this.STATE == \"3\") { csscl = \"unknown\"; } else { csscl = \"ok\"; } }\n"
                    + "                    if (this.DTM == \"t\") { dtmcl='<img src=\"public/images/clock-small.png\" />'; } else { dtmcl=''; }\n"
                    + "                    $('#div-monitoring-content').append('<span id=\"service-tab\" class=\"' + csscl + '\" title=\"' + base64_decode( this.OUTPUT ) + '\" onclick=\"ServiceMenu(\\\'' + hstna + '\\\',\\\'' + hstid + '\\\',\\\'' + this.SERVICE_NAME + '\\\',\\\'' + this.SERVICE_ID + '\\\',\\\'' + this.INSTID + '\\\',\\\'' + this.ACK + '\\\',\\\'' + this.ACKID + '\\\',\\\'' + this.DTM + '\\\',\\\'' + this.DTMID + '\\\');\">' + dtmcl + 'Service: <b>' + base64_decode( this.SERVICE_NAME ) + '</b><br>Host: <b>' + base64_decode( hstna ) + '</b></span>');\n"
                    + "                });\n"
                    + "            });\n"
                    + "        },\n"
                    + "        dataType: 'json',\n"
                    + "        cache: false\n"
                    + "    });\n"
                    + "    $.cookie('view','tabs');\n"
                    + "}\n"
                    + "");
            
                out.println(""
                    + "function ServiceStatusList(uid) {\n"
                    + "    $('#monitoring-tab').addClass('ui-opacity-med');\n"
                    + "    $('#monitoring-list').removeClass('ui-opacity-med');\n"
                    + "    $.ajax({\n"
                    + "        url: '/gateway/monitoring/ServiceStatus',\n"
                    + "        data: {\n"
                    + "            s: statS\n"
                    + "        },\n"
                    + "        success: function(json) {\n"
                    + "            $('#div-monitoring').html('<div id=\"div-monitoring-content\"></div>');\n"
                    + "            $.each(json, function() {\n"
                    + "                var hstid = this.HOST_ID;\n"
                    + "                var hstna = this.HOST_NAME;\n"
                    + "                $('#div-monitoring-content').append('<div id=\"content-list\" class=\"ui-input-hofo-g\"><img class=\"img-h\" src=\"public/images/' + base64_decode( this.HOST_TYPE ) + '-128.png\"  title=\"' + base64_decode( this.HOST_TYLN ) + '\" /><div class=\"ui-input-hofo-grey-header\"><span style=\"float: left;\">' + base64_decode( this.HOST_NAME ) + '</span><span style=\"float: left; margin-top: -1px;\" class=\"ui-icon ui-icon-triangle-1-s\"></span></div><div><span id=\"list-' + hstid + '\"></span></div>');\n"
                    + "                $.each(this.SERVICES, function() {\n"
                    + "                    var csscl; var dtmcl;\n"
                    + "                    if (this.ACK == \"t\") { csscl = \"ack\"; } else { if (this.STATE == \"1\") { csscl = \"warning\"; } else if (this.STATE == \"2\") { csscl = \"critical\"; } else if (this.STATE == \"3\") { csscl = \"unknown\"; } else { csscl = \"ok\"; } }\n"
                    + "                    if (this.DTM == \"t\") { dtmcl='<img src=\"public/images/clock-small.png\" />'; } else { dtmcl=''; }\n"
                    + "                    $('#list-' + hstid).append('<div class=\"content-list-entry\" title=\"Klicken um Bearbeitung zu &ouml;ffnen.\" onclick=\"ServiceMenu(\\\'' + hstna + '\\\',\\\'' + hstid + '\\\',\\\'' + this.SERVICE_NAME + '\\\',\\\'' + this.SERVICE_ID + '\\\',\\\'' + this.INSTID + '\\\',\\\'' + this.ACK + '\\\',\\\'' + this.ACKID + '\\\',\\\'' + this.DTM + '\\\',\\\'' + this.DTMID + '\\\');\"><span class=\"ui-taov-tile ' + csscl + '\">' + dtmcl + '</span><span>Service: <b>' + base64_decode( this.SERVICE_NAME )+ '</b></span><span>Datum: <b>' + this.CREATED_ISO + '</b></span><br><span>' + base64_decode( this.OUTPUT ) + '</span></div>');\n"
                    + "                });\n"
                    + "            });\n"
                    + "        },\n"
                    + "        dataType: 'json',\n"
                    + "        cache: false\n"
                    + "    }); \n"
                    + "    $.cookie('view','list');\n"
                    + "}\n"
                    + "");
                
                out.println(""
                    + "function CurProbs(uid) {\n"
                    + "    $('#monitoring-view').html('<img id=\"monitoring-tab\" onclick=\"CurProbsTab();\" src=\"public/images/grid-128.png\" title=\"Tab Ansicht\"/><img id=\"monitoring-list\" onclick=\"CurProbsList();\" src=\"public/images/list-128.png\" title=\"Listen Ansicht\"/>');\n"
                    + "    if ($.cookie('view') == 'list') {\n"
                    + "        CurProbsList();\n"
                    + "    } else {\n"
                    + "        CurProbsTab();\n"
                    + "    }\n"
                    + "    setTimeout('CurProbs()', 60000);\n"
                    + "}\n"
                    + "");
            
                out.println(""
                    + "function CurProbsTab(uid) {\n"
                    + "    $('#monitoring-list').addClass('ui-opacity-med');\n"
                    + "    $('#monitoring-tab').removeClass('ui-opacity-med');\n"
                    + "    $.ajax({\n"
                    + "        url: '/gateway/monitoring/CurProbs',\n"
                    + "        success: function(json) {\n"
                    + "            $('#div-monitoring').html('<div id=\"div-monitoring-content\"></div>');\n"
                    + "            $.each(json, function() {\n"
                    + "                var hstid = this.HOST_ID;\n"
                    + "                var hstna = this.HOST_NAME;\n"
                    //+ "                $('#div-monitoring-content').append('<div id=\"div-monitoring-entry\" class=\"ui-input-hofo-grey\"><img class=\"img-h\" src=\"public/images/' + base64_decode( this.HOST_TYPE ) + '-128.png\"  title=\"' + base64_decode( this.HOST_TYLN ) + '\" /><div class=\"ui-input-hofo-grey-header\"><span style=\"float: left;\">' + base64_decode( this.HOST_NAME ) + '</span><span style=\"float: left; margin-top: -1px;\" class=\"ui-icon ui-icon-triangle-1-s\"></span><br><span id=\"c' + hstid + '\" class=\"content-h\"></span></div>');\n"
                    + "                $.each(this.SERVICES, function() {\n"
                    + "                    var csscl; var dtmcl;\n"
                    + "                    if (this.ACK == \"t\") { csscl = \"ack2\"; } else { if (this.STATE == \"1\") { csscl = \"warning\"; } else if (this.STATE == \"2\") { csscl = \"critical\"; } else if (this.STATE == \"3\") { csscl = \"unknown\"; } else { csscl = \"ok\"; } }\n"
                    + "                    if (this.DTM == \"t\") { dtmcl='<img src=\"public/images/clock-small.png\" />'; } else { dtmcl=''; }\n"
                    + "                    $('#div-monitoring-content').append('<span id=\"service-tab\" class=\"' + csscl + '\" title=\"' + base64_decode( this.OUTPUT ) + '\" onclick=\"ServiceMenu(\\\'' + hstna + '\\\',\\\'' + hstid + '\\\',\\\'' + this.SERVICE_NAME + '\\\',\\\'' + this.SERVICE_ID + '\\\',\\\'' + this.INSTID + '\\\',\\\'' + this.ACK + '\\\',\\\'' + this.ACKID + '\\\',\\\'' + this.DTM + '\\\',\\\'' + this.DTMID + '\\\');\">' + dtmcl + 'Service: <b>' + base64_decode( this.SERVICE_NAME ) + '</b><br>Host: <b>' + base64_decode( hstna ) + '</b></span>');\n"
                    + "                });\n"
                    + "            });\n"
                    + "        },\n"
                    + "        dataType: 'json',\n"
                    + "        cache: false\n"
                    + "    });\n"
                    + "    $.cookie('view','tabs');\n"
                    + "}\n"
                    + "");
            
                out.println(""
                    + "function CurProbsList(uid) {\n"
                    + "    $('#monitoring-tab').addClass('ui-opacity-med');\n"
                    + "    $('#monitoring-list').removeClass('ui-opacity-med');\n"
                    + "    $.ajax({\n"
                    + "        url: '/gateway/monitoring/CurProbs',\n"
                    + "        success: function(json) {\n"
                    + "            $('#div-monitoring').html('<div id=\"div-monitoring-content\"></div>');\n"
                    + "            $.each(json, function() {\n"
                    + "                var hstid = this.HOST_ID;\n"
                    + "                var hstna = this.HOST_NAME;\n"
                    + "                $('#div-monitoring-content').append('<div id=\"content-list\" class=\"ui-input-hofo-g\"><img class=\"img-h\" src=\"public/images/' + base64_decode( this.HOST_TYPE ) + '-128.png\"  title=\"' + base64_decode( this.HOST_TYLN ) + '\" /><div class=\"ui-input-hofo-grey-header\"><span style=\"float: left;\">' + base64_decode( this.HOST_NAME ) + '</span><span style=\"float: left; margin-top: -1px;\" class=\"ui-icon ui-icon-triangle-1-s\"></span></div><div><span id=\"list-' + hstid + '\"></span></div>');\n"
                    + "                $.each(this.SERVICES, function() {\n"
                    + "                    var csscl; var dtmcl;\n"
                    + "                    if (this.ACK == \"t\") { csscl = \"ack\"; } else { if (this.STATE == \"1\") { csscl = \"warning\"; } else if (this.STATE == \"2\") { csscl = \"critical\"; } else if (this.STATE == \"3\") { csscl = \"unknown\"; } else { csscl = \"ok\"; } }\n"
                    + "                    if (this.DTM == \"t\") { dtmcl='<img src=\"public/images/clock-small.png\" />'; } else { dtmcl=''; }\n"
                    + "                    $('#list-' + hstid).append('<div class=\"content-list-entry\" title=\"Klicken um Bearbeitung zu &ouml;ffnen.\" onclick=\"ServiceMenu(\\\'' + hstna + '\\\',\\\'' + hstid + '\\\',\\\'' + this.SERVICE_NAME + '\\\',\\\'' + this.SERVICE_ID + '\\\',\\\'' + this.INSTID + '\\\',\\\'' + this.ACK + '\\\',\\\'' + this.ACKID + '\\\',\\\'' + this.DTM + '\\\',\\\'' + this.DTMID + '\\\');\"><span class=\"ui-taov-tile ' + csscl + '\">' + dtmcl + '</span><span>Service: <b>' + base64_decode( this.SERVICE_NAME )+ '</b></span><span>Datum: <b>' + this.CREATED_ISO + '</b></span><br><span>' + base64_decode( this.OUTPUT ) + '</span></div>');\n"
                    + "                });\n"
                    + "            });\n"
                    + "        },\n"
                    + "        dataType: 'json',\n"
                    + "        cache: false\n"
                    + "    }); \n"
                    + "    $.cookie('view','list');\n"
                    + "}\n"
                    + "");
                
                out.println(""
                    + "function DbStatus(uid) {\n"
                    + "    $('#monitoring-view').html('<img id=\"monitoring-tab\" onclick=\"DbStatusTab();\" src=\"public/images/grid-128.png\" title=\"Tab Ansicht\"/><img id=\"monitoring-list\" onclick=\"DbStatusList();\" src=\"public/images/list-128.png\" title=\"Listen Ansicht\"/>');\n"
                    + "    if ($.cookie('view') == 'list') {\n"
                    + "        DbStatusList();\n"
                    + "    } else {\n"
                    + "        DbStatusTab();\n"
                    + "    }\n"
                    + "    setTimeout('DbStatus()', 60000);\n"
                    + "}\n"
                    + "");
            
                out.println(""
                    + "function DbStatusTab(uid) {\n"
                    + "    $('#monitoring-list').addClass('ui-opacity-med');\n"
                    + "    $('#monitoring-tab').removeClass('ui-opacity-med');\n"
                    + "    $.ajax({\n"
                    + "        url: '/gateway/monitoring/DbStatus',\n"
                    + "        data: {\n"
                    + "            s: statS\n"
                    + "        },\n"
                    + "        success: function(json) {\n"
                    + "            $('#div-monitoring').html('<div id=\"div-monitoring-content\"></div>');\n"
                    + "            $.each(json, function() {\n"
                    + "                var hstid = this.HOST_ID;\n"
                    + "                var hstna = this.HOST_NAME;\n"
                    //+ "                $('#div-monitoring-content').append('<div id=\"div-monitoring-entry\" class=\"ui-input-hofo-grey\"><img class=\"img-h\" src=\"public/images/' + base64_decode( this.HOST_TYPE ) + '-128.png\"  title=\"' + base64_decode( this.HOST_TYLN ) + '\" /><div class=\"ui-input-hofo-grey-header\"><span style=\"float: left;\">' + base64_decode( this.HOST_NAME ) + '</span><span style=\"float: left; margin-top: -1px;\" class=\"ui-icon ui-icon-triangle-1-s\"></span><br><span id=\"c' + hstid + '\" class=\"content-h\"></span></div>');\n"
                    + "                $.each(this.SERVICES, function() {\n"
                    + "                    var csscl; var dtmcl;\n"
                    + "                    if (this.ACK == \"t\") { csscl = \"ack2\"; } else { if (this.STATE == \"1\") { csscl = \"warning\"; } else if (this.STATE == \"2\") { csscl = \"critical\"; } else if (this.STATE == \"3\") { csscl = \"unknown\"; } else { csscl = \"ok\"; } }\n"
                    + "                    if (this.DTM == \"t\") { dtmcl='<img src=\"public/images/clock-small.png\" />'; } else { dtmcl=''; }\n"
                    + "                    $('#div-monitoring-content').append('<span id=\"service-tab\" class=\"' + csscl + '\" title=\"' + base64_decode( this.OUTPUT ) + '\" onclick=\"ServiceMenu(\\\'' + hstna + '\\\',\\\'' + hstid + '\\\',\\\'' + this.SERVICE_NAME + '\\\',\\\'' + this.SERVICE_ID + '\\\',\\\'' + this.INSTID + '\\\',\\\'' + this.ACK + '\\\',\\\'' + this.ACKID + '\\\',\\\'' + this.DTM + '\\\',\\\'' + this.DTMID + '\\\');\">' + dtmcl + 'Service: <b>' + base64_decode( this.SERVICE_NAME ) + '</b><br>Host: <b>' + base64_decode( hstna ) + '</b></span>');\n"
                    + "                });\n"
                    + "            });\n"
                    + "        },\n"
                    + "        dataType: 'json',\n"
                    + "        cache: false\n"
                    + "    });\n"
                    + "    $.cookie('view','tabs');\n"
                    + "}\n"
                    + "");
            
                out.println(""
                    + "function DbStatusList(uid) {\n"
                    + "    $('#monitoring-tab').addClass('ui-opacity-med');\n"
                    + "    $('#monitoring-list').removeClass('ui-opacity-med');\n"
                    + "    $.ajax({\n"
                    + "        url: '/gateway/monitoring/DbStatus',\n"
                    + "        data: {\n"
                    + "            s: statS\n"
                    + "        },\n"
                    + "        success: function(json) {\n"
                    + "            $('#div-monitoring').html('<div id=\"div-monitoring-content\"></div>');\n"
                    + "            $.each(json, function() {\n"
                    + "                var hstid = this.HOST_ID;\n"
                    + "                var hstna = this.HOST_NAME;\n"
                    + "                $('#div-monitoring-content').append('<div id=\"content-list\" class=\"ui-input-hofo-g\"><img class=\"img-h\" src=\"public/images/' + base64_decode( this.HOST_TYPE ) + '-128.png\"  title=\"' + base64_decode( this.HOST_TYLN ) + '\" /><div class=\"ui-input-hofo-grey-header\"><span style=\"float: left;\">' + base64_decode( this.HOST_NAME ) + '</span><span style=\"float: left; margin-top: -1px;\" class=\"ui-icon ui-icon-triangle-1-s\"></span></div><div><span id=\"list-' + hstid + '\"></span></div>');\n"
                    + "                $.each(this.SERVICES, function() {\n"
                    + "                    var csscl; var dtmcl;\n"
                    + "                    if (this.ACK == \"t\") { csscl = \"ack\"; } else { if (this.STATE == \"1\") { csscl = \"warning\"; } else if (this.STATE == \"2\") { csscl = \"critical\"; } else if (this.STATE == \"3\") { csscl = \"unknown\"; } else { csscl = \"ok\"; } }\n"
                    + "                    if (this.DTM == \"t\") { dtmcl='<img src=\"public/images/clock-small.png\" />'; } else { dtmcl=''; }\n"
                    + "                    $('#list-' + hstid).append('<div class=\"content-list-entry\" title=\"Klicken um Bearbeitung zu &ouml;ffnen.\" onclick=\"ServiceMenu(\\\'' + hstna + '\\\',\\\'' + hstid + '\\\',\\\'' + this.SERVICE_NAME + '\\\',\\\'' + this.SERVICE_ID + '\\\',\\\'' + this.INSTID + '\\\',\\\'' + this.ACK + '\\\',\\\'' + this.ACKID + '\\\',\\\'' + this.DTM + '\\\',\\\'' + this.DTMID + '\\\');\"><span class=\"ui-taov-tile ' + csscl + '\">' + dtmcl + '</span><span>Service: <b>' + base64_decode( this.SERVICE_NAME )+ '</b></span><span>Datum: <b>' + this.CREATED_ISO + '</b></span><br><span>' + base64_decode( this.OUTPUT ) + '</span></div>');\n"
                    + "                });\n"
                    + "            });\n"
                    + "        },\n"
                    + "        dataType: 'json',\n"
                    + "        cache: false\n"
                    + "    }); \n"
                    + "    $.cookie('view','list');\n"
                    + "}\n"
                    + "");
        
            }
            
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
        try {
            processRequest(request, response, request.getRemoteUser());
        } catch (NamingException ex) {
            Logger.getLogger(TacticalOverview.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(TacticalOverview.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
