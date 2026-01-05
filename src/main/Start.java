
package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Start {
    private Player player;
    private Scanner sc = new Scanner(System.in);
    private final static String PATH = "src/resources/";
    protected static int choixMenu;

    private ArrayList<String> listChoix;
    private String choixJeu;
    private boolean choixJeuValide;

    public Start() {
        listChoix = new ArrayList<String>();
        listChoix.add("Mastermind");
        listChoix.add("Roulette");
        listChoix.add("Machines à Sous");
        listChoix.add("Jeu de Mine");
        listChoix.add("Blackjack");
        listChoix.add("Profil Joueur");
        listChoix.add("Sauvegarder et Quitter");

        choixJeu = listChoix.get(0);
        choixJeuValide = false;
    }

    public void affichageChoixJeu(Player player) {
        char inputCase;

        while (!choixJeuValide) {
            ScreenGestion.clearScreen();
            afficherTouches();
            afficherChoix(listChoix, choixJeu);

            // avoir le choix
            inputCase = getinputCase(); // input pour changer de case (donc de choix de jeu)

            // monter dans la liste
            if (inputCase == 'z' && listChoix.indexOf(choixJeu) > 0) {
                choixJeu = listChoix.get(listChoix.indexOf(choixJeu) - 1);
            }
            // descendre dans la liste
            else if (inputCase == 's' && listChoix.indexOf(choixJeu) < listChoix.size() - 1) {
                choixJeu = listChoix.get(listChoix.indexOf(choixJeu) + 1);
            } else if (inputCase == 'x') { // valider le choix
                choixJeuValide = true;
                if (choixJeu.equals("Mastermind")) {
                    ScreenGestion.clearScreen();
                    Mastermind mastermind = new Mastermind(player);
                    mastermind.play();
                }
                else if (choixJeu.equals("Jeu de Mine")) {
                    ScreenGestion.clearScreen();
                    JeuMine jeuMine = new JeuMine(player);
                    jeuMine.play();
                }
                else if (choixJeu.equals("Roulette")) {
                    ScreenGestion.clearScreen();
                    Roulette roulette = new Roulette(player);
                    roulette.play();
                }
                else if (choixJeu.equals("Machines à Sous")) {
                    ScreenGestion.clearScreen();
                    SlotMachine machineASous = new SlotMachine();
                    machineASous.play(player);
                }
                else if (choixJeu.equals("Blackjack")) {
                    ScreenGestion.clearScreen();
                    Blackjack blackjack = new Blackjack(player);
                    blackjack.play();
                }
                else if (choixJeu.equals("Sauvegarder et Quitter")) {
                    ScreenGestion.clearScreen();
                    save(player);
                    ScreenGestion.printTmp(ScreenGestion.strJauneGras("Merci d'avoir joué ! A bientôt."));
                }
                else if (choixJeu.equals("Profil Joueur")) {
                    ScreenGestion.clearScreen();
                    player.affichageStats();
                    player.menu();
                }
            }
        }
    }

    public static void printTmp(String texte) {
        printTmpSansLn(texte);
        System.out.println();
    }

    public static void printTmpSansLn(String texte) {
        for (int i = 0; i < texte.length(); i++) {
            System.out.print(texte.charAt(i));
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void  afficher_Menu() {
        clear();

        for (int i = 1; i < 10; i++) {
            clear();
            afficher_Txt("intro" + i + ".txt");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public int demander_Choix() {
        int choix = -1;

        while (true) {
            printTmpSansLn("Votre choix : ");

            // Vérifie EOF (CTRL+D)
            if (!sc.hasNextLine()) {
                ScreenGestion.printTmp(ScreenGestion.strRougeGras("Saisie obligatoire. Fin de saisie non autorisée (CTRL+D détecté)."));
                sc = new Scanner(System.in); // Réinitialisation possible du Scanner
                continue;
            }

            String ligne = sc.nextLine().trim();

            // Vérifie si l'entrée est vide
            if (ligne.isEmpty()) {
                ScreenGestion.printTmp(ScreenGestion.strRougeGras("Entrée vide non autorisée. Veuillez saisir un chiffre entre 1 et 4."));
                continue;
            }

            try {
                choix = Integer.parseInt(ligne);
                if (choix >= 1 && choix <= 4) {
                    return choix; // ✅ Choix valide
                } else {
                    ScreenGestion.printTmp(ScreenGestion.strRougeGras("Veuillez entrer un nombre entre 1 et 4 !"));
                }
            } catch (NumberFormatException e) {
                ScreenGestion.printTmp(ScreenGestion.strRougeGras("Veuillez saisir un chiffre parmi ceux disponibles."));
            }
        }
    }


    public void chargement() {
        String texte = ".....";
        for (int i = 0; i < texte.length(); i++) {
            System.out.print(texte.charAt(i));
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void clear() {
        try {
            new ProcessBuilder("clear").inheritIO().start().waitFor();
        } catch (Exception e) {
            for (int i = 0; i < 50; i++) {
                System.out.println();
            }
        }
    }

    public static void loading() {
        clear();
        for (int i = 1; i < 6; i++) {
            afficher_Txt("loading" + i + ".txt");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clear();
        }
    }

    public void choix_selectionner() {
        int choix = demander_Choix();
        switch (choix) {
            case 1:
                printTmp("\nLancement d'une nouvelle partie");
                player = creerPlayer();
                loading();
                clear();
                afficher_TxtTmp("introduction.txt");
                printTmp("Appuyez sur Entrée pour continuer...");
                sc.nextLine();
                sc.nextLine();
                affichageChoixJeu(player);
                
                break;
            case 2:
                printTmp("Chargement d'une sauvegarde en cours");
                load();
            case 3:
                System.out.println();
                afficher_Txt("credits.txt");
                System.out.println();
                retourAccueilDuJeu();
                break;
            case 4:
                printTmpSansLn("Merci d'avoir joué ! A bientôt.");
                break;
        }
    }

    public static void retourMenuPrincipal(Player player) {
        printTmp("Appuyez sur Entrée pour revenir au menu principal...");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            if (!scanner.hasNextLine()) {
                System.out.println(ScreenGestion.strRougeGras("Saisie obligatoire. CTRL+D n'est pas autorisé."));
                scanner = new Scanner(System.in); // Réinitialise si besoin
                continue;
            }

            String ligne = scanner.nextLine(); // On accepte une entrée vide ici
            break;
        }

        Start start = new Start();
        start.affichageChoixJeu(player);
    }

    public static void retourAccueilDuJeu() {
        printTmp("Appuyez sur Entrée pour revenir au menu principal...");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            if (!scanner.hasNextLine()) {
                System.out.println(ScreenGestion.strRougeGras("Saisie obligatoire. CTRL+D n'est pas autorisé."));
                scanner = new Scanner(System.in); // Réinitialisation si nécessaire
                continue;
            }

            String ligne = scanner.nextLine(); // Entrée vide = OK ici
            break;
        }

        Start start = new Start();
        Start.afficher_Menu();
        start.choix_selectionner();
    }


    public static void afficher_Txt(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(PATH + fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println(ScreenGestion.strRougeGras("Impossible d'ouvrir le fichier " + fileName + "... Veuillez vérifier !"));
        }
    }

    public static void afficher_TxtTmp(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(PATH + fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                printTmp(line);
            }
        } catch (IOException e) {
            System.out.println(ScreenGestion.strRougeGras("Impossible d'ouvrir le fichier " + fileName + "... Veuillez vérifier !"));
        }
    }

    public static void afficherTouches() {
        System.out.println("\n");
    }

    public static char getinputCase() {

        char choix = ' ';
        System.out.println("\tAppuyez sur 'z' pour monter, 's' pour descendre, 'x' pour valider.\n");
        try{
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine().toLowerCase();
            while (!input.equals("z") && !input.equals("s") && !input.equals("x")) {
                System.out.println(ScreenGestion.strRougeGras("Merci de rentrer un caractére valide (z, s ou x)"));
                input = scanner.nextLine().toLowerCase();   
            }
            choix = input.charAt(0);
        }catch (Exception e){
            System.out.println(ScreenGestion.strRougeGras("Erreur lors de la lecture de l'entrée utilisateur."));
        }
        return choix;
    }

    public static String boxString(String str, int taille) {
        String temp = "";
        String topBorder = "";
        for (int i = 0; i < taille + 4; i++) { // +4 pour les bords et les espaces
            topBorder += "-";
        }

        temp += '\t' + topBorder + "\n";
        temp += '\t' + borderString(str, taille) + "\n";
        temp += '\t' + topBorder + "\n";

        return temp;
    }

    public static String borderString(String str, int taille) {
        String temp = "";
        int tEspaces = taille - str.length(); // taille totale des escpaces à rajouter

        temp += "| ";
        for (int i = 0; i < tEspaces / 2; i++) {
            temp += " ";
        }
        temp += str;
        for (int i = 0; i < tEspaces / 2; i++) {
            temp += " ";
        }
        if ((taille - str.length()) % 2 != 0) { // ajuste la bordure de droite
            temp += " ";
        }
        temp += " |";

        return temp;
    }

    public static void afficherChoix(ArrayList<String> listChoix, String choixActuel) {
        int tailleMax = 0;
        for (String elt : listChoix) {
            if (elt.length() > tailleMax) {
                tailleMax = elt.length();
            }
        }

        System.out.println("\n\n");

        for (String choix : listChoix) {
            if (choix.equals(choixActuel)) {
                System.out.println(ScreenGestion.strJauneGras(boxString(choix, tailleMax)));
            } else {
                System.out.println(ScreenGestion.strJaune(boxString(choix, tailleMax)));
            }
        }
    }

    public static Player creerPlayer() {
        printTmp("\nCréation du joueur");
        ArrayList<String> existingSaves = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("src/resources/save.csv")))
        {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                existingSaves.add(parts[0]);
            }
        } catch (IOException e) {
            System.out.println(ScreenGestion.strRougeGras("Erreur lors du chargement des sauvegardes existantes."));
        }
        String name = Tools.tailleEntreeUser(12, "Entrez votre nom", "Veuillez entrer un nom de joueur compris entre 1 et 12 caractères sans aucun chiffres ni espaces");
        while (existingSaves.contains(name)) {
            printTmp(ScreenGestion.strRougeGras("Nom déja utilisé."));
            name = Tools.tailleEntreeUser(12, "Veuillez entrer un nouveau nom", "Veuillez entrer un nom de joueur compris entre 1 et 12 caractères sans aucun chiffres ni espaces");
        }
        String mdp = Tools.taillemdp(12, "Veuillez entrer votre mot de passe pour continuer : (Appuyer sur q pour quitter)", "Veuillez entrer un mot de passe compris entre 1 et 12 caractères sans aucun espaces");
        Player player = new Player(name,mdp,1000);
       
        return player;    
    }
    
    public static void save(Player player) {
       FileWriter fw = null;
        try{
            fw = new FileWriter("src/resources/save.csv", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw);
            out.println(player.getName() + "|" + player.getSolde().getSolde() + "|" + player.getMdp());
            out.close();
        } catch (IOException e) {
            System.out.println(ScreenGestion.strRougeGras("Erreur lors de la sauvegarde."));
        }
    }

    public static void load() {
        System.out.println("Entrez le nom de votre sauvegarde :");
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
        try (BufferedReader br = new BufferedReader(new FileReader("src/resources/save.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts[0].equalsIgnoreCase(name)) {
                    ScreenGestion.printTmp(ScreenGestion.strJauneGras("Sauvegarde trouvée !"));
                    String mdp = Tools.taillemdp(12, "Veuillez entrer votre mot de passe pour continuer : (Appuyer sur q pour quitter)", "Veuillez entrer un mot de passe compris entre 1 et 12 caractères sans aucun espaces");
                    while(parts.length < 3 || !parts[2].equals(mdp) || mdp.equals("q")){
                        ScreenGestion.printTmp(ScreenGestion.strRougeGras("Mot de passe incorrect."));
                        if(mdp.equals("q")){
                            retourAccueilDuJeu();
                        }
                        mdp = Tools.taillemdp(12, "Veuillez entrer votre mot de passe pour continuer : (Appuyer sur q pour quitter)", "Veuillez entrer un mot de passe compris entre 1 et 12 caractères sans aucun espaces");
                    }
                    double solde = Double.parseDouble(parts[1]);
                    Player player = new Player(name, mdp, (int) solde);
                    ScreenGestion.printTmp(ScreenGestion.strJauneGras("Mots de passe correct !"));
                    ScreenGestion.printTmp(ScreenGestion.strJauneGras("Sauvegarde chargée avec succès !"));
                    loading();
                    clear();
                    afficher_TxtTmp("introduction.txt");
                    printTmp("Appuyez sur Entrée pour continuer...");
                    sc.nextLine();
                    Start start = new Start();
                    start.affichageChoixJeu(player);
                    return;
                }
            }
            ScreenGestion.printTmp(ScreenGestion.strRougeGras("Aucune sauvegarde trouvée avec ce nom."));
            retourAccueilDuJeu();
        } catch (IOException e) {
            ScreenGestion.printTmp(ScreenGestion.strRougeGras("Erreur lors du chargement de la sauvegarde."));
        }
    } 

    public static void leaderBoard() {
        ArrayList<Player> players = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("src/resources/save.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                String name = parts[0];
                double solde = Double.parseDouble(parts[1]);
                Player player = new Player(name);
                player.getSolde().setSolde(solde);
                players.add(player);
            }
        } catch (IOException e) {
            System.out.println(ScreenGestion.strRougeGras("Erreur lors du chargement des scores."));
            return;
        }

        // Trier les joueurs par solde décroissant
        players.sort((p1, p2) -> Double.compare(p2.getSolde().getSolde(), p1.getSolde().getSolde()));

        System.out.println("\n--- Classement des joueurs ---");
        for (int i = 0; i < Math.min(10, players.size()); i++) { // Afficher seulement les 10 premiers
            Player player = players.get(i);
            System.out.printf("%d. %s - %.2f crédits\n", i + 1, player.getName(), player.getSolde().getSolde());
        }
        System.out.println("------------------------------\n");
    }
}
