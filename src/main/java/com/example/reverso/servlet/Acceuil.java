package com.example.reverso.servlet;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "acceuil", value = "/Acceuil")
public class Acceuil extends HttpServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//        response.setContentType("text/html");
//
//        // Hello
//        PrintWriter out = response.getWriter();
//        out.println("<html><body>");
//        out.println("<h1>" + message + "</h1>");
//        out.println("</body></html>");
        this.getServletContext().getRequestDispatcher("/WEB-INF/acceuil.jsp").forward(request, response);
    }

    public void destroy() {
    }
}