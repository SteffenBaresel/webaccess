/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.siv.systemcontrol;

import de.siv.web.Html;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sbaresel
 */
public class Index extends HttpServlet {
    Properties props = null;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setCharacterEncoding("UTF-8");
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "*");
        response.setContentType("text/html; charset=utf-8");
        out.println(Html.openHtmlAndHead(null));
        out.println(Html.includeMeta(null));
        out.println("    <title>Startseite - kVASy&reg; System Control</title>");
        out.println(Html.includeJs("Index"));
        out.println(Html.includeCss("Index"));
        out.println(""
                + "<script>\n"
                + "$(function() {\n"
                + "    $('div#menu-locator').html('Startseite');\n"
                + "    $('input').addClass('ui-input-hofo');\n"
                + "    $('span#ui-tile').addClass('ui-input-hofo');\n"
                + "});\n"
                + "</script>\n"
                + "");
        out.println(Html.closeHeadOpenBody(null));
        out.println(Html.printSectionMenu(null,"Index"));
        
        out.println(""
                + "<section id='big-taov'></section>"
                + "<span id='ui-tile' class='ui-tile-150px'></span>"
                + "<span id='ui-tile' class='ui-tile-300px'></span>"
                + "");
        out.println(Html.printSectionBottom(null));
        out.println(Html.closeBodyCloseHtml(null));
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

