package com.example.reverso.servlet;

import java.io.*;
import java.time.LocalDate;

import com.example.reverso.beans.Adresse;
import com.example.reverso.beans.Client;
import com.example.reverso.beans.Prospect;
import com.example.reverso.beans.Societe;
import com.example.reverso.dao.ClientDao;
import com.example.reverso.dao.ProspectDao;
import com.example.reverso.utilitaires.Interessement;
import com.example.reverso.utilitaires.TypeAction;
import com.example.reverso.utilitaires.TypeSociete;
import com.example.reverso.utilitaires.Utilitaires;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class Formulaire extends HttpServlet {
    String choix;
    private String action;

    private Societe societe;
//    public void init() {
//        message = "Hello World!";
//    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {


        choix = request.getParameter("choix");
        action = request.getParameter("action");
        String nomSociete = request.getParameter("societe");

        System.out.println(nomSociete);
        if (action == null){
            request.setAttribute("typeAction", TypeAction.CREATION);
        } else if (action.equals("modifier")){
            request.setAttribute("typeAction", TypeAction.MODIFICATION);
        } else if (action.equals("supprimer")){
            request.setAttribute("typeAction", TypeAction.SUPPRESSION);
        }

        if (choix.equals("client")){
            request.setAttribute("typeSociete", TypeSociete.CLIENT);

                try {
                    request.setAttribute("societes", new ClientDao().findByName(nomSociete));
                    societe = new ClientDao().findByName(nomSociete);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }


        }else if (choix.equals("prospect")){
            request.setAttribute("typeSociete", TypeSociete.PROSPECT);

                try {
                    request.setAttribute("societes", new ProspectDao().findByName(nomSociete));
                    societe = new ProspectDao().findByName(nomSociete);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

        }
        System.out.println(societe);
        this.getServletContext().getRequestDispatcher("/WEB-INF/formulaire.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doPost(req, resp);


        try {
           societe = instantiateSociete(req);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (action == null){

            if(societe instanceof Client) {
                try {
                    new ClientDao().create((Client)societe);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }else if (societe instanceof Prospect){
                try {
                    new ProspectDao().create((Prospect)societe);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

        } else if (action.equals("modifier")){

            if(societe instanceof Client) {
                try {
                    new ClientDao().update((Client)societe);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }else if (societe instanceof Prospect){
                try {
                    new ProspectDao().update((Prospect)societe);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

        } else if (action.equals("supprimer")){

            if(societe instanceof Client) {
                try {
                    new ClientDao().delete((Client)societe);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }else if (societe instanceof Prospect){
                try {
                    new ProspectDao().delete((Prospect)societe);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

        }


        try {
            req.setAttribute("clients", new ClientDao().findAll());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            req.setAttribute("prospects", new ProspectDao().findAll());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        this.getServletContext().getRequestDispatcher("/WEB-INF/acceuil.jsp").forward(req, resp);
    }

    public void destroy() {
    }

    private Societe instantiateSociete(HttpServletRequest req) throws Exception {

        Adresse adresse = new Adresse(
                this.societe == null ? null : this.societe.getAdresse().getIdentifiant(),
                req.getParameter("num"),
                req.getParameter("rue"),
                req.getParameter("cp"),
                req.getParameter("ville")
        );
        Societe societe = null;
        if (choix.equals("client")) {
            societe = new Client(
                    this.societe == null ? null : this.societe.getIdentifiant(),
                    req.getParameter("rs"),
                    adresse,
                    req.getParameter("tel"),
                    req.getParameter("email"),
                    req.getParameter("commentaires"),
                    Utilitaires.formatMoneyForSave(req.getParameter("ca").replace(",",".")),
                    Integer.parseInt(req.getParameter("ca"))
            );
        } else if (choix.equals("prospect")) {
            Interessement interesse = null;
            if (true) {
                interesse = Interessement.OUI;
            } else if (true) {
                interesse = Interessement.NON;
            }
            societe = new Prospect(
                    this.societe == null ? null : this.societe.getIdentifiant(),
                    req.getParameter("rs"),
                    adresse,
                    req.getParameter("tel"),
                    req.getParameter("email"),
                    req.getParameter("commentaires"),
                    LocalDate.of(
                            2022,
                            10,
                            22
                    ),
                    interesse
            );
        }

        return societe;

    }
}