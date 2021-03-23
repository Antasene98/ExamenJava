package services;

import java.sql.ResultSet;
import java.util.Scanner;

public class MarketeurImpl implements IMarketeur{
    @Override
    public boolean loginMarketeur() {
        Scanner sc = new Scanner(System.in);
        String login, mdp;
        do {
            System.out.println("\n\t=========================================");
            System.out.println("\t              CONNEXION");
            System.out.println("\t=========================================\n");
            System.out.print("\t\tLOGIN : ");
            login = sc.nextLine();
            System.out.print("\t\tPASSWORD : ");
            mdp = sc.nextLine();
            if (search(login, mdp)) {
                System.out.println("\n\tCONNEXION REUSSIE !!");
                return true;
            } else {
                System.out.println("\n\tCONNEXION ECHOUEE !!\n");
            }
        }while (1==1);
    }

    public boolean search(String login, String mdp)
    {
        String sql = "SELECT * FROM marketeur WHERE email = ? AND password = ?";
        DatabaseHelper bd = DatabaseHelper.getInstance();
        try
        {
            bd.prepareStatement(sql);
            Object[] tab = {login,mdp};
            bd.bindParams(tab);
            ResultSet rs = bd.executeQuery();
            while (rs.next())
            {
                return true;
            }
            return false;
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return false;
    }
}
