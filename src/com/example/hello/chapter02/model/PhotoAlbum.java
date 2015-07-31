package com.example.hello.chapter02.model;

import java.util.*;
import javax.servlet.ServletContext;

/**
 * Created by user on 29.07.2015.
 */
public class PhotoAlbum
{
    public static String ATTRIBUTE_NAME = "Photo_Album";
    private List<byte[]> photoDataList = new ArrayList<byte[]>();
    private List<String> names = new ArrayList<String>();

    public static PhotoAlbum getPhotoAlbum(ServletContext servletContext)
    {
        if (servletContext.getAttribute(ATTRIBUTE_NAME) == null) {
            PhotoAlbum photoAlbum = new PhotoAlbum();
            servletContext.setAttribute(ATTRIBUTE_NAME, photoAlbum);
        }

        return (PhotoAlbum) servletContext.getAttribute(ATTRIBUTE_NAME);
    }

    public synchronized void addPhoto(String name, byte[] bytes)
    {
        this.photoDataList.add(bytes);
        this.names.add(name);
    }

    public synchronized byte[] getPhotoData(int i)
    {
        return photoDataList.get(i);
    }

    public synchronized String getPhotoName(int i)
    {
        return names.get(i);
    }

    public synchronized int getPhotoCount()
    {
        return photoDataList.size();
    }

    public synchronized void removePhoto(int i)
    {
        photoDataList.remove(i);
        names.remove(i);
    }
}
