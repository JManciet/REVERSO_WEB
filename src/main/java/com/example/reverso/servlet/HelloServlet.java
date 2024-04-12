package com.example.reverso.servlet;

import java.io.*;

import com.example.reverso.bdd.Noms;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        Noms tableNoms = new Noms();
        try {
            request.setAttribute("adresses", tableNoms.recupererAdresses());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        this.getServletContext().getRequestDispatcher("/WEB-INF/bonjour.jsp").forward(request, response);
    }

    public void destroy() {
    }
}