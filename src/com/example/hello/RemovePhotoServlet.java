package com.example.hello;

import com.example.hello.chapter02.model.PhotoAlbum;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by user on 31.07.2015.
 */
@WebServlet(name = "RemovePhotoServlet", urlPatterns = "/RemovePhotoServlet")
public class RemovePhotoServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String indexString = req.getParameter("photo");
        int index = (new Integer(indexString.trim())).intValue();
        PhotoAlbum photoAlbum = PhotoAlbum.getPhotoAlbum(req.getSession());

        photoAlbum.removePhoto(index);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("DisplayAlbumServlet");
        requestDispatcher.forward(req, resp);
    }
}
