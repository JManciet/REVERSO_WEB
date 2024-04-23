package com.example.reverso.servlet;

import java.io.*;

import com.example.reverso.dao.ClientDao;
import com.example.reverso.dao.ProspectDao;
import com.example.reverso.utilitaires.TypeAction;
import com.example.reverso.utilitaires.TypeSociete;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class Formulaire extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {


        String choix = request.getParameter("choix");
        String action = request.getParameter("action");
        String societe = request.getParameter("societe");

        if(action.equals("modifier")){
            request.setAttribute("typeAction", TypeAction.MODIFICATION);
        } else if (action.equals("supprimer")){
            request.setAttribute("typeAction", TypeAction.SUPPRESSION);
        } else {
            request.setAttribute("typeAction", TypeAction.CREATION);
        }

        if (choix.equals("client")){
            request.setAttribute("typeSociete", TypeSociete.CLIENT);

                try {
                    request.setAttribute("societes", new ClientDao().findByName(societe));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }


        }else if (choix.equals("prospect")){
            request.setAttribute("typeSociete", TypeSociete.PROSPECT);

                try {
                    request.setAttribute("societes", new ProspectDao().findByName(societe));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

        }

        this.getServletContext().getRequestDispatcher("/WEB-INF/formulaire.jsp").forward(request, response);
    }

    public void destroy() {
    }
}