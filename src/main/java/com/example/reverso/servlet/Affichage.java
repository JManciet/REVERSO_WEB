package com.example.reverso.servlet;

import java.io.*;

import com.example.reverso.dao.ClientDao;
import com.example.reverso.dao.ProspectDao;
import com.example.reverso.utilitaires.TypeSociete;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class Affichage extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {


        String choix = request.getParameter("choix");

        if (choix.equals("client")){
            request.setAttribute("type", TypeSociete.CLIENT);
            try {
                request.setAttribute("societes", new ClientDao().findAll());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }else if (choix.equals("prospect")){
            request.setAttribute("type", TypeSociete.PROSPECT);
            try {
                request.setAttribute("societes", new ProspectDao().findAll());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        this.getServletContext().getRequestDispatcher("/WEB-INF/affichage.jsp").forward(request, response);
    }

    public void destroy() {
    }
}