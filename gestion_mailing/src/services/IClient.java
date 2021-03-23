package services;

import entites.Client;

import java.util.ArrayList;

public interface IClient {
    public void ajoutClient();

    public void listeClient();

    public void modifierClient();

    public void supprimerClient();

    public Client searchClient();

    public int menu();
}
