package services;

import entites.Client;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ClientImpl implements IClient{
    Scanner sc = new Scanner(System.in);
    DatabaseHelper db = DatabaseHelper.getInstance();

    @Override
    public void ajoutClient() {
        entete("AJOUT CLIENT");
        Client cl = new Client();
        cl.saisie();

        String sql = "INSERT INTO client VALUES("+db.lastIdFor("client")+", ?, ?, ?, ?) ";
        try
        {
            db.prepareStatement(sql);
            Object[] tab = {cl.getNom(), cl.getPrenom(), cl.getEmail(), cl.getTel()};
            db.bindParams(tab);
            db.executeUpdate();
            System.out.println("CLIENT AJOUTE AVEC SUCCES !!");
        }
        catch (Exception e)
        {
            System.out.println("ECHEC !!");
            System.out.println(e);
        }
    }

    @Override
    public void listeClient() {
        entete("LISTE DES CLIENTS");

        String sql = "SELECT * FROM client";

        List<Client> clients = new ArrayList<>();
        try {
            db.prepareStatement(sql);
            ResultSet rs = db.executeQuery();

            while (rs.next())
            {
                Client cl = new Client(rs.getInt(1),rs.getString(2),
                        rs.getString(3),rs.getString(4),rs.getString(5));

                clients.add(cl);
            }
            rs.close();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        if(clients.isEmpty())
        {
            System.out.println("LISTE VIDE");
        }
        else {
            for (Client cl: clients) {
                cl.affiche();
            }
        }
    }

    @Override
    public void modifierClient() {
        Client cl = searchClient();
        if(cl == null)
        {
            System.out.println("CLIENT INEXISTANT !!!");
        }
        else {
            cl.affiche();
            cl.saisie();
            String sql = "UPDATE client SET nom = ?,prenom = ?,email = ?,tel = ? WHERE id = ?";
            try
            {
                db.prepareStatement(sql);
                Object[] tab = {cl.getNom(),cl.getPrenom(),cl.getEmail(),cl.getTel(),cl.getId()};
                db.bindParams(tab);
                db.executeUpdate();
                System.out.println("CLIENT MODIFIE AVEC SUCCES !!! ");
            }
            catch (Exception e)
            {
                System.out.println(e);
            }
        }
    }

    @Override
    public void supprimerClient() {
        Client cl = searchClient();
        if(cl == null)
        {
            System.out.println("CLIENT INEXISTANT !!!");
        }
        else {
            String sql = "DELETE FROM client WHERE id = ?";
            try
            {
                db.prepareStatement(sql);
                Object[] tab = {cl.getId()};
                db.bindParams(tab);
                db.executeUpdate();
                System.out.println("CLIENT SUPRIME AVEC SUCCES");
            }
            catch (Exception e)
            {
                System.out.println(e);
            }
        }
    }

    @Override
    public Client searchClient() {
        Client cl = null;
        String mail;
        entete("RECHERCHE CLIENT");
        sc.nextLine();
        System.out.print("\tEMAIL : ");
        mail = sc.nextLine();

        String sql = "SELECT * FROM client WHERE email = ?";

        try
        {
            db.prepareStatement(sql);
            Object[] tab = {mail};
            db.bindParams(tab);
            ResultSet rs = db.executeQuery();
            while (rs.next())
            {
                cl = new Client(rs.getInt(1),rs.getString(2),
                        rs.getString(3),rs.getString(4),rs.getString(5));
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return cl;
    }

    @Override
    public int menu() {
        int choix;
        entete("MENU");
        System.out.println("\t1-                   CREATION D'UN CLIENT");
        System.out.println("\t2-              VISUALISATION DES CLIENTS");
        System.out.println("\t3-                    EDITION D'UN CLIENT");
        System.out.println("\t4-                 SUPRESSION D'UN CLIENT");
        System.out.println("\t5-                  RECHERCHE D'UN CLIENT\n");
        System.out.println("\t6-                            DECONNEXION\n");
        do {
            System.out.print("\t\tVOTRE CHOIX : ");
            choix = sc.nextInt();
        }while (choix < 1 || choix > 6);
        return choix;
    }

    public void entete(String chaine)
    {
        System.out.println("\n");
        System.out.println("\n\t=========================================");
        System.out.println("\t              "+chaine);
        System.out.println("\t=========================================\n");
    }
}
