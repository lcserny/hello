package com.example.hello;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import java.util.*;
import java.io.*;

/**
 * Created by user on 29.07.2015.
 */
@WebServlet(name = "TemplateServlet", urlPatterns = "/TemplateServletURI")
public class TemplateServlet extends HttpServlet
{
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        for (Enumeration e = req.getHeaderNames(); e.hasMoreElements();) {
            String nextRequestHeaderName = (String) e.nextElement();
            String nextRequestHeaderValue = req.getHeader(nextRequestHeaderName);

            // do something with the request headers
        }

        InputStream inputStream = req.getInputStream();
        // or
        Reader reader = req.getReader();

        // gather data to send back

        // set response headers
        resp.setHeader("headerName", "headerValue");

        // write response body
        PrintWriter respWriter = resp.getWriter();
        // or
        OutputStream outputStream = resp.getOutputStream();
    }
}
