package com.example.hello;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;
import com.example.hello.chapter01.model.MessageException;
import com.example.hello.chapter01.model.ModelEJB;
import javax.ejb.EJB;

/**
 * Created by user on 29.07.2015.
 */
@WebServlet(name = "DisplayServlet", urlPatterns = "/DisplayServlet")
public class DisplayServlet extends HttpServlet
{
    @EJB
    private ModelEJB modelEJB;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        resp.setContentType("text/html;charset=UTF-8");

        PrintWriter out = resp.getWriter();
        try {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Hello Java EE</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<br>");
            out.println("<div align='center'>");
            out.println("<h2>Hello Java EE</h2>");
            out.println("Enter a message for Java EE which will pass through the web tier, the EJB tier to the database and back again!");
            out.println("<br><br><br>");
            out.println("<form action='./WriteServlet' method='POST'>");
            out.println("<input type='submit' value='Enter'>");
            out.println("<input type='text' name='put_message'>");
            out.println("</form>");
            out.println("<br>");

            String displayMessage;
            try {
                String storedMessage = modelEJB.getStoredMessage();
                displayMessage = "Hello from (" + storedMessage + "), inside a web component.";
            } catch (MessageException me) {
                displayMessage = "you should enter a value...";
            }
            out.println("The current message from Java EE: <br><b>" + displayMessage + "</b>");

            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }
}
