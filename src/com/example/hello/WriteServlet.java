package com.example.hello;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.ejb.EJB;
import com.example.hello.chapter01.model.ModelEJB;
import com.example.hello.chapter01.model.MessageException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by user on 27.07.2015.
 */
@WebServlet(name = "WriteServlet", urlPatterns = "/WriteServlet")
public class WriteServlet extends HttpServlet
{
    @EJB
    private ModelEJB ModelEJB;
    private static String PUT_MESSAGE = "put_message";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String message = req.getParameter(PUT_MESSAGE);

        if ("".equals(message)) {
            ModelEJB.deleteMessage();
        } else {
            try {
                ModelEJB.putUserMessage(message);
            } catch (MessageException messageException) {
                throw new ServletException(messageException);
            }
        }

        resp.sendRedirect("./DisplayServlet");
    }

}
