package com.example.reverso.servlet;

import java.io.*;

import com.example.reverso.dao.ClientDao;
import com.example.reverso.dao.ProspectDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "acceuil", value = "/Acceuil")
public class Acceuil extends HttpServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            request.setAttribute("clients", new ClientDao().findAll());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            request.setAttribute("prospects", new ProspectDao().findAll());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        this.getServletContext().getRequestDispatcher("/WEB-INF/acceuil.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);

        System.out.println("zzzzzzzzzzzzzzzzzzzzzzzzzzzz");
        System.out.println("URL: " + req.getRequestURI());
        this.getServletContext().getRequestDispatcher("/WEB-INF/acceuil.jsp").forward(req, resp);
    }

    public void destroy() {
    }
}