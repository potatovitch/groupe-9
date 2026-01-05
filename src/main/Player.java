package main;

import java.util.Scanner;

public class Player {

    private String name;
    protected Argent solde;
    private String mdp;
    public String getMdp() {
        return mdp;
    }

    private Scanner scanner = new Scanner(System.in);

    public Player(String name) {
        this.name = name;
        this.solde = new Argent();
    }

    public Player(String name, String mdp, int solde) {
        this.name = name;
        this.mdp = mdp;
        this.solde = new Argent();
        this.solde.setSolde(solde);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Argent getSolde() {
        return this.solde;
    }

    public void menu(){
        ScreenGestion.printTmp("\nQue souhaitez-vous faire ?\n");
        ScreenGestion.printTmp("[1] Changer votre nom\n[2] Leaderboard \n[3] Retour menu principal");
        int choix = -1;
        while(choix != 1 && choix != 2 && choix != 3){
            try{
                ScreenGestion.printTmp("\nEntrez votre choix : ");
                choix = scanner.nextInt();
                if(choix != 1 && choix != 2 && choix != 3){
                    ScreenGestion.printTmp(ScreenGestion.strRougeGras("Merci d'entrer un nombre valide"));
                }
            }catch(Exception e){
                ScreenGestion.printTmp(ScreenGestion.strRougeGras("Merci d'entrer un nombre valide"));
                scanner.next();
            }
        }
        if(choix == 1){
            ScreenGestion.clearScreen();
            String newNom =  Tools.tailleEntreeUser(12, "Veuillez entrer votre nouveau nom", "Veuillez entrer un nom de joueur compris entre 1 et 12 caractères sans aucun chiffres ni espaces");;
            this.setName(newNom);
            ScreenGestion.clearScreen();
            this.affichageStats();
            menu();
        }else if(choix == 2){
            ScreenGestion.clearScreen();
            Start.leaderBoard();
            menu();
        }else{
            Start.retourMenuPrincipal(this);
        }
    }

    public void affichageStats(){
        ScreenGestion.printTmp("Voici votre profil :\n");
        ScreenGestion.printTmp("Nom : "+this.getName()+"\nSolde : "+this.getSolde());
    }

    public static void main(String[] args) {
        Player player = new Player("test");
        player.affichageStats();
        player.menu();
    }

}

