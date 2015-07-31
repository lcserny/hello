package com.example.hello;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import java.io.*;
import com.example.hello.chapter02.model.PhotoAlbum;

/**
 * Created by user on 29.07.2015.
 */
@WebServlet(name = "DisplayPhotoServlet", urlPatterns = "/DisplayPhotoServlet")
public class DisplayPhotoServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String indexString = req.getParameter("photo");
        int index = (new Integer(indexString.trim())).intValue();

        resp.setContentType("image/jpeg");
        OutputStream outputStream = resp.getOutputStream();

        try {
            HttpSession session = req.getSession();
            PhotoAlbum photoAlbum = PhotoAlbum.getPhotoAlbum(session);
            byte[] bytes = photoAlbum.getPhotoData(index);

            for (int i = 0; i < bytes.length; i++) {
                outputStream.write(bytes[i]);
            }
        } finally {
            outputStream.close();
        }
    }
}
