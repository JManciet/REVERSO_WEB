package com.example.reverso.bdd;

import com.example.reverso.beans.Adresse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class Noms {
    private Connection connexion;

    public List<Adresse> recupererAdresses() throws Exception {
        List<Adresse> adresses = new ArrayList<Adresse>();
        Statement statement = null;
        ResultSet resultat = null;

        loadDatabase();

        try {
            statement = connexion.createStatement();

            // Exécution de la requête
            resultat = statement.executeQuery("SELECT * FROM ADRESSE");

            // Récupération des données
            while (resultat.next()) {

                Adresse utilisateur = new Adresse(resultat.getInt("IDADRESSE"),
                        resultat.getString( "NUMERORUE"),
                        resultat.getString("NOMRUE"),
                        resultat.getString("CODEPOSTAL"),
                        resultat.getString("VILLE"));

                adresses.add(utilisateur);
            }
        } catch (SQLException e) {
        } finally {
            // Fermeture de la connexion
            try {
                if (resultat != null)
                    resultat.close();
                if (statement != null)
                    statement.close();
                if (connexion != null)
                    connexion.close();
            } catch (SQLException ignore) {
            }
        }

        return adresses;
    }

    private void loadDatabase() {
        // Chargement du driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
        }

        try {
            connexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/reverso", "root", "123456");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    public void ajouterUtilisateur(Utilisateur utilisateur) {
//        loadDatabase();
//
//        try {
//            PreparedStatement preparedStatement = connexion.prepareStatement("INSERT INTO noms(nom, prenom) VALUES(?, ?);");
//            preparedStatement.setString(1, utilisateur.getNom());
//            preparedStatement.setString(2, utilisateur.getPrenom());
//
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
}
