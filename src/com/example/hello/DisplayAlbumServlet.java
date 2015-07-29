package com.example.hello;

import com.example.hello.chapter02.model.PhotoAlbum;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;

/**
 * Created by user on 29.07.2015.
 */
@WebServlet(name = "DisplayAlbumServlet", urlPatterns = "/DisplayAlbumServlet")
@MultipartConfig()
public class DisplayAlbumServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        handleRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        handleRequest(req, resp);
    }

    protected void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        ServletContext servletContext = request.getServletContext();
        PhotoAlbum photoAlbum = PhotoAlbum.getPhotoAlbum(servletContext);

        if (request.getContentType() != null && request.getContentType().startsWith("multipart/form-data")) {
            this.uploadPhoto(request, photoAlbum);
        }

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = response.getWriter();

        try {
            writer.write("<html>");
            writer.write("<head>");
            writer.write("<title>Photo Viewer</title>");
            writer.write("</head>");
            writer.write("<body>");
            writer.write("<h3 align='center'>Photos</h3>");

            this.displayAlbum(photoAlbum, "", writer);

            writer.write("</body>");
            writer.write("</html>");
        } finally {
            writer.close();
        }
    }

    private void uploadPhoto(HttpServletRequest request, PhotoAlbum photoAlbum) throws ServletException, IOException
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        String filename = null;

        for (Part part: request.getParts()) {
            this.copyBytes(part.getInputStream(), baos);
            filename = part.getSubmittedFileName();
        }

        if (!"".equals(filename)) {
            String photoName = filename.substring(0, filename.lastIndexOf("."));
            photoAlbum.addPhoto(photoName, baos.toByteArray());
        }
    }

    private void displayAlbum(PhotoAlbum photoAlbum, String label, PrintWriter writer)
    {
        writer.write("<h3 align='center'>" + label + "</h3>");
        writer.write("<table align='center'>");
        writer.write("<tr>");

        for (int j = 0; j < photoAlbum.getPhotoCount(); j++) {
            writer.write("<td>");
            writer.write("<a href='./DisplayPhotoServlet?photo=" + j + "'>");
            writer.write("<img src='./DisplayPhotoServlet?photo=" + j + "' alt='photo' height='120' width='150'> ");
            writer.write("</a>");
            writer.write("</td>");
        }

        writer.write("<td bgcolor='#cccccc' width='120' height='120'>");
        writer.write("<form align='left' action='DisplayAlbumServlet' method='post' enctype='multipart/form-data'>");
        writer.write("<input value='Choose' name='myFile' type='file' accept='image/jpeg'><br>");
        writer.write("<input value='Upload' type='submit\'><br>");
        writer.write("</form>");
        writer.write("</td>");
        writer.write("</tr>");

        writer.write("<tr>");
        for (int j = 0; j < photoAlbum.getPhotoCount(); j++) {
            writer.write("<td align='center'>");
            writer.write(photoAlbum.getPhotoName(j));
            writer.write("</td>");
        }
        writer.write("</tr>");

        writer.write("<tr>");
        for (int j = 0; j < photoAlbum.getPhotoCount(); j++) {
            writer.write("<td align='center'>");
            writer.write("<a href='RemovePhotoServlet?photo=" + j + "'>remove</a>");
            writer.write("</td>");
        }
        writer.write("</tr>");
        writer.write("</table>");
    }

    private void copyBytes(InputStream inputStream, OutputStream outputStream) throws IOException
    {
        int i;
        while ((i = inputStream.read()) != -1) {
            outputStream.write(i);
        }

        inputStream.close();
        outputStream.close();
    }
}
