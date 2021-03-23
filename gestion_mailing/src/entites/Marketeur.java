package entites;

import java.util.Scanner;

public class Marketeur {
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String password;
    private String tel;

    public Marketeur() {
    }

    public Marketeur(int id, String nom, String prenom, String email, String password, String tel) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.tel = tel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void affiche()
    {
        System.out.println("MARKETEUR "+this.id+" {"+this.nom+" - "+this.prenom+" - "+this.email+" - "+this.tel+" }");
    }
    public void saisie()
    {
        System.out.println("=========================================");
        System.out.println("              SAISIE CLIENT");
        System.out.println("=========================================\n");
        Scanner sc = new Scanner(System.in);
        System.out.print("NOM : ");
        this.nom = sc.nextLine();
        System.out.print("PRENOM : ");
        this.prenom = sc.nextLine();
        System.out.print("EMAIL : ");
        this.email = sc.nextLine();
        System.out.print("MOT DE PASSE : ");
        this.password = sc.nextLine();
        System.out.print("TELEPHONE : ");
        this.tel = sc.nextLine();
    }
}
