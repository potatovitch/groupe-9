package main;

import java.util.Random;
import java.util.Scanner;

public class JeuMine extends Jeu{
    
    private int rows = 5;
    private int cols = 5;
    private boolean[][] mines;
    private boolean[][] revealed;
    private int[] positionBomb;
    private int totalMines;
    private double multiplier = 1.0;
    private boolean gameOver = false;
    private int initialBet;
    private Argent miseTotale;
    private boolean isABomb;

    public JeuMine(Player player) {
        this.player = player;
        this.miseTotale = player.getSolde();
        System.out.println("Bienvenue dans le jeu de Démineur!\n");
        ScreenGestion.printTmp("Vous voici au Jeu des Bombes  !!! \nLes régles sont simples : \n\n - Vous choisissez une case dans une grille de 5x5 \n - Si vous tombez sur une case sans bombe, vous gagnez un multiplicateur \n - Si vous tombez sur une bombe, vous perdez votre mise initiale \n - Vous pouvez choisir de continuer à jouer ou de récupérer vos gains à chaque tour \n - Plus il y a de bombes, plus le multiplicateur augmente ! \nBonne chance !\n");
        System.out.println("Votre solde actuel est de: " + player.getSolde() + " €");
        initialBet = Integer.parseInt(Tools.intervalleEntreeUser(1, miseTotale.getSolde(), "Combien voulez-vous miser ? Entre 1 et " + miseTotale));
        totalMines = Integer.parseInt(Tools.intervalleEntreeUser(1, 24, "Entrez le nombre de mines (entre 1 et 24): "));
        initGame();
        play();
    }

    public JeuMine(Player player,boolean rejoue) {
        this.player = player;
        this.miseTotale = player.getSolde();
        if(!rejoue){
            System.out.println("Bienvenue dans le jeu de Démineur!\n");
            ScreenGestion.printTmp("Vous voici au Jeu de Démineur  !!! \nLes régles sont simples : \n\n - Vous choisissez une case dans une grille de 5x5 \n - Si vous tombez sur une case sans bombe, vous gagnez un multiplicateur \n - Si vous tombez sur une bombe, vous perdez votre mise initiale \n - Vous pouvez choisir de continuer à jouer ou de récupérer vos gains à chaque tour \n - Plus il y a de bombes, plus le multiplicateur augmente ! \nBonne chance !\n");
        }
        System.out.println("Votre solde actuel est de: " + player.getSolde() + " €");
        initialBet = Integer.parseInt(Tools.intervalleEntreeUser(1, miseTotale.getSolde(), "Combien voulez-vous miser ? Entre 1 et " + miseTotale));
        totalMines = Integer.parseInt(Tools.intervalleEntreeUser(1, 24, "Entrez le nombre de mines (entre 1 et 24): "));
        initGame();
        play();
    }


    private void initGame() {
        mines = new boolean[rows][cols];
        revealed = new boolean[rows][cols];
        Random rand = new Random();
        int placed = 0;
        while (placed < totalMines) {
            int r = rand.nextInt(rows);
            int c = rand.nextInt(cols);
            if (!mines[r][c]) {
                mines[r][c] = true;
                placed++;
            }
        }
    }

    public void play() {
        while (!gameOver) {
            Start.clear();
            printBoard();
            Scanner scanner = new Scanner(System.in);
            int r = Tools.intervalleEntreeUserCharUnique(0, 4, "Entrez la ligne (0-" + (rows-1) + "): ").charAt(0) - '0';
            int c = Tools.intervalleEntreeUserCharUnique(0, 4, "Entrez la colonne (0-" + (cols-1) + "): ").charAt(0) - '0';
            if (revealed[r][c]) {
                System.out.println("Case déjà découverte.");
                continue;
            }
            if (mines[r][c]) {
                revealed[r][c] = true;
                isABomb = true;
                positionBomb = new int[] {r,c};
                printBoard();
                System.out.println("BOOM! Vous avez découvert une mine. Perdu!");
                player.getSolde().retirerSolde(initialBet);
                System.out.println("Solde actuel: " + player.getSolde());
                if (miseTotale.getSolde() <= 0) {
                    ScreenGestion.printTmp(ScreenGestion.strRougeGras("Vous n'avez plus d'argent pour continuer à jouer."));
                    ScreenGestion.printTmp(ScreenGestion.strRougeGras("Ce casino n'accepte pas les gens sans argent, désolé !"));
                    gameOver = true;
                    break;
                }
                rejouer();
                gameOver = true;
                break;
            } else {
                revealed[r][c] = true;
                printBoard();
                double increment = (0.1 * totalMines / (rows * cols));
                multiplier += increment;
                System.out.println("Case sûre! Multiplicateur actuel: " + multiplier);
                System.out.print("Voulez-vous continuer ? (o/n): ");
                String cont = scanner.nextLine().toLowerCase();
                while (!cont.equals("o") && !cont.equals("n")) {
                    System.out.println(ScreenGestion.strRougeGras("Veuillez répondre par 'o' ou 'n'."));
                    cont = scanner.nextLine().toLowerCase();   
                }
                if ("n".equalsIgnoreCase(cont)) {
                    double gain = initialBet * multiplier;
                    System.out.println("Bravo! Vous repartez avec: " + (int) gain);
                    player.getSolde().ajouterSolde((int) gain);
                    System.out.println("Solde actuel: " + player.getSolde());
                    rejouer();
                    gameOver = true;
                }
            }
        }
    }

    public void rejouer(){
        try{
            System.out.println("Voulez-vous continuer à jouer ? (oui/non)");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine().toLowerCase();
            while (!input.equals("oui") && !input.equals("non")) {
                System.out.println(ScreenGestion.strRougeGras("Veuillez répondre par 'oui' ou 'non'."));
                input = scanner.nextLine().toLowerCase();   
            }
            if(input.equals("oui")){
                Start.clear();
                JeuMine newGame = new JeuMine(player,true);
                newGame.play();
            }else {
                Start.clear();
                ScreenGestion.printTmp(ScreenGestion.strJauneGras("Merci d'avoir joué au jeu de Démineur ! Vous repartez avec: " + miseTotale + " €.\n"));
                Start.retourMenuPrincipal(player);
            }
        }catch (Exception e){
            System.out.println(ScreenGestion.strRougeGras("Erreur lors de la lecture de l'entrée utilisateur."));
        }
    }

    private void printBoard() {
        System.out.println("Plateau:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (revealed[i][j]) {
                    if (isABomb && positionBomb[0]==i && positionBomb[1]==j) {
                        System.out.print("💣");
                    }else {
                        System.out.print("⭐");
                    }
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        Player joueur = new Player("Joueur1");
        new JeuMine(joueur);  
    }
}
