package com.example.reverso.dao;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

import java.sql.Connection;
import java.sql.SQLException;

public class InitDaoFactory implements ServletContextListener {

    static final String ATT_DAO_CONNECT = " daoFactoryConnect";

    private Connection daoFactory;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContextListener.super.contextInitialized(sce);
        ServletContext servletContext = sce.getServletContext();

        this.daoFactory = DaoFactory.getInstance();

        servletContext.setAttribute( ATT_DAO_CONNECT, this.daoFactory);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContextListener.super.contextDestroyed(sce);

        try {
            this.daoFactory.close();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
}
