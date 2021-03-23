package main;

import entites.Client;
import services.ClientImpl;
import services.IClient;
import services.IMarketeur;
import services.MarketeurImpl;

public class Main {
    public static void main(String[] args) {
//        DECLARATION DES VARIABLES
        IMarketeur im = new MarketeurImpl();
        IClient ic = new ClientImpl();
        boolean login = false;
        int choix;

        do {
            login = im.loginMarketeur();
            do {
                choix = ic.menu();
                switch (choix){
                    case 1:
                        ic.ajoutClient();
                        break;
                    case 2:
                        ic.listeClient();
                        break;
                    case 3:
                        ic.modifierClient();
                        break;
                    case 4:
                        ic.supprimerClient();
                        break;
                    case 5:
                        Client cl = ic.searchClient();
                        if(cl == null) System.out.println("AMOUL CLIENT BI !!");
                        else cl.affiche();
                        break;
                    case 6:
                        login = false;
                        break;
                }
            }
            while (login == true);
        }while (1==1);
    }
}
