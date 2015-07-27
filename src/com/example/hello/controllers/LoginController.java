package com.example.hello.controllers;

import com.example.hello.models.Authenticator;
import com.example.hello.models.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by user on 27.07.2015.
 */
public class LoginController extends HttpServlet
{

    private static final long serialVersionUID = 1L;

    public LoginController()
    {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        RequestDispatcher rd = null;

        Authenticator authenticator = new Authenticator();
        String result = authenticator.authenticate(username, password);

        if (result.equals("success")) {
            rd = req.getRequestDispatcher("/success.jsp");
            User user = new User(username, password);
            req.setAttribute("user", user);
        } else {
            rd = req.getRequestDispatcher("/error.jsp");
        }

        rd.forward(req, resp);
    }

}
