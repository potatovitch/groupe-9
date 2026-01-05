package main;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Blackjack {

    private ArrayList<Card> deck;
    private Player player;
    private ArrayList<Card> mainPlayer;
    private ArrayList<Card> mainDealer;
    private int scorePlayer;
    private boolean rejoue = false;
    private int scoreDealer;

    private int mise;
    private Argent miseTotale;
    private int gain;

    public Blackjack(Player player) {
        this.deck = Card.createDeck();
        mixeDeck();
        this.player = player;
        this.mainPlayer = createEmptyMain();
        this.mainDealer = createEmptyMain();
        this.scorePlayer = 0;
        this.scoreDealer = 0;

        this.miseTotale = player.getSolde();
    }

    public Player createDealer() {
        return new Player("Dealer");
    }

    public ArrayList<Card> createEmptyMain() {
        return new ArrayList<Card>();
    }

    public void mixeDeck() {
        ArrayList<Card> newDeck = new ArrayList<Card>();
        while (deck.size() > 0) {
            int index = (int) (Math.random() * deck.size());
            newDeck.add(deck.get(index));
            deck.remove(index);
        }
        deck.addAll(newDeck);
        this.deck = newDeck;
    }

    public void afficherlogo() {
        Start.afficher_Txt("../resources/blackjack/blackJack.txt");
    }

    ////// toString //////

    public String mainToString(ArrayList<Card> main) {
        StringBuilder sb = new StringBuilder();
        for (Card card : main) {
            sb.append(card.toString() + " ");
        }
        return sb.toString().trim();
    }

    public String toStringColorList(CardColor color, ArrayList<Integer> values) {
        StringBuilder sb = new StringBuilder();
        sb.append(color.toString()).append(" : ");
        for (Integer cardValues : values) {
            sb.append(cardValues.toString()).append(" ");
        }
        return sb.toString().trim();
    }

    ////// getters & setters //////
    public ArrayList<Card> getDeck() {
        return deck;
    }

    public void setDeck(ArrayList<Card> deck) {
        this.deck = deck;
    }

    ////// implements Jeu //////
    public void play() {
        ScreenGestion.clear();


        afficherlogo();
        // Start.afficher_Txt("../resources/blackjack/blackjack.txt");

        
        if(!rejoue){
            System.out.println("Bienvenue dans le jeu du Blackjack!\n");
            ScreenGestion.printTmp("Vous voici au BlackJack !!! \nLes régles sont simples : \n\n - Vous commencez avec une carte \n - Le but est d'avoir une main plus proche de 21 que le dealer sans dépasser 21 \n - Vous pouvez choisir de tirer une carte ('t') ou de rester ('r') \n - Si vous dépassez 21 ou faite une égalités avec le dealer, vous perdez automatiquement \n - Si vous battez le dealer, vous gagnez le double de votre mise \nBonne chance !\n");
        }

        setPlayerMise();

        // jeu
        boolean continuer = true;
        boolean aGagne = false;

        getCard(mainPlayer);
        getCard(mainDealer);

        while (continuer) {
            ScreenGestion.clear();
            afficherlogo();

            afficherCards();

            char choix = getChoix();

            if (choix == 't') {
                getCard(mainPlayer);
                scorePlayer = getScore(mainPlayer);
                continuer = !estFini(continuer);
            } else if (choix == 'r') {
                while (scoreDealer < 17) {
                    getCard(mainDealer);
                    scoreDealer = getScore(mainDealer);
                }
                continuer = false;
            }

        }

        ScreenGestion.clearScreen();
        afficherlogo();

        aGagne = aGagne();

        if (!aGagne) {
            miseTotale.AppliquerMalus(mise);
            afficherCards();
            ScreenGestion.printTmp("\tDommage, vous n'avez pas battu la maison. Vous perdez votre mise de: " + mise+ " €. \n\tIl vous reste: " + miseTotale + " €.");
            if (miseTotale.getSolde() <= 0) {
                    ScreenGestion.printTmp(ScreenGestion.strRougeGras("Vous n'avez plus d'argent pour continuer à jouer."));
                    ScreenGestion.printTmp(ScreenGestion.strRougeGras("Ce casino n'accepte pas les gens sans argent, désolé !"));
            } else {
                rejouer();
            }
        } else {
            gain = mise * 2;
            miseTotale.AppliquerBonus(gain);
            System.out.println("\n");
            afficherCards();
            ScreenGestion.printTmp(ScreenGestion.strJauneGras("\n\tFélicitations, vous avez battu la maison! Vous gagnez: " + gain + " €.\n\tVous avez maintenant: " + miseTotale + " €.\n"));
            rejouer();
        }
    }

public char getChoix() {
    Scanner scanner = new Scanner(System.in);
    String ligne;

    while (true) {
        System.out.println("\tVoulez-vous tirer une carte 't' ou rester 'r' ?\n");

        // Vérifie si l'utilisateur a entré CTRL+D (EOF)
        if (!scanner.hasNextLine()) {
            System.out.println(ScreenGestion.strRougeGras("\tSaisie obligatoire. Fin de saisie non autorisée (CTRL+D détecté)."));
            scanner = new Scanner(System.in); // Réinitialisation si nécessaire
            continue;
        }

        ligne = scanner.nextLine().trim().toLowerCase();

        // Vérifie que la ligne n'est pas vide
        if (ligne.isEmpty()) {
            System.out.println(ScreenGestion.strRougeGras("\tEntrée vide non autorisée. Veuillez taper 't' ou 'r'."));
            continue;
        }

        // Prend uniquement le premier caractère
        char input = ligne.charAt(0);

        if (input == 't' || input == 'r') {
            return input; // ✅ Choix valide
        } else {
            System.out.println(ScreenGestion.strRougeGras("\tVeuillez entrer 't' pour tirer une carte ou 'r' pour rester."));
        }
    }
}


    public void afficherCards() {
        System.out.println("\tMain du joueur : " + mainToString(mainPlayer) + " | Score : " + getScore(mainPlayer));
        System.out.println();
        System.out.println("\tMain du dealer : " + mainToString(mainDealer) + " | Score : " + getScore(mainDealer));
    }

    public boolean estFini(boolean continuer) {

        if (continuer && (scoreDealer > 21 || scorePlayer > 21)) {
            return true;
        }
        return false;
    }

    public boolean aGagne() {
        boolean dGagne = true;
        boolean pGagne = true;

        if (scoreDealer > 21) {
            dGagne = false;
        }
        if (scorePlayer > 21) {
            pGagne = false;
        }
        if (scoreDealer > scorePlayer && scoreDealer <= 21) {
            dGagne = true;
            pGagne = false;
        } else if (scorePlayer > scoreDealer && scorePlayer <= 21) {
            pGagne = true;
            dGagne = false;
        }

        return pGagne && !dGagne;
    }

    public void getCard(ArrayList<Card> main) {
        if (deck.size() > 0) {
            main.add(deck.get(0));
            deck.remove(0);
        } else {
            System.out.println(ScreenGestion.strRougeGras("\tLe deck est vide !"));
        }
    }

    public int getScore(ArrayList<Card> main) {
        int score = 0;
        int nbAs = 0;
        for (Card card : main) {

            if (card.getValue() == 1) { // Si c'est un As
                nbAs++;
                score += 11; // Compter l'As comme 11 pour l'instant
            } else if (card.getValue() >= 10) { // Si c'est un 10, Valet, Dame ou Roi
                score += 10;
            } else {
                score += card.getValue();
            }
        }
        // Ajuster le score si on a des As et que le score dépasse 21
        while (score > 21 && nbAs > 0) {
            score -= 10; // Compter l'As comme 1 au lieu de 11
            nbAs--;
        }
        return score;
    }

public int setPlayerMise() {
    System.out.println("\tVous avez actuellement : " + miseTotale);
    Scanner scanner = new Scanner(System.in);
    String ligne;

    while (true) {
        System.out.println("\tCombien voulez-vous miser ?\n");

        // Gestion du CTRL+D (EOF)
        if (!scanner.hasNextLine()) {
            System.out.println(ScreenGestion.strRougeGras("\tSaisie obligatoire. Fin de saisie non autorisée (CTRL+D détecté)."));
            scanner = new Scanner(System.in); // Réinitialisation du scanner si nécessaire
            continue;
        }

        ligne = scanner.nextLine().trim();

        // Entrée vide ?
        if (ligne.isEmpty()) {
            System.out.println(ScreenGestion.strRougeGras("\tEntrée vide non autorisée. Veuillez entrer un montant valide."));
            continue;
        }

        try {
            int input = Integer.parseInt(ligne);

            if (input > 0 && input <= miseTotale.getSolde()) {
                mise = input;
                return mise; // ✅ Mise acceptée
            } else {
                System.out.println(ScreenGestion.strRougeGras("\tVeuillez entrer une mise valide (positive et ≤ à votre argent : " + miseTotale + ")."));
            }
        } catch (NumberFormatException e) {
            System.out.println(ScreenGestion.strRougeGras("\tEntrée invalide. Veuillez entrer un nombre entier."));
        }
    }
}


    public void rejouer() {
        try {
            System.out.println("\tVoulez-vous continuer à jouer ? (oui/non)");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine().toLowerCase();
            while (!input.equals("oui") && !input.equals("non")) {
                System.out.println(ScreenGestion.strRougeGras("\tVeuillez répondre par 'oui' ou 'non'."));
                input = scanner.nextLine().toLowerCase();
            }
            if (input.equals("oui")) {
                Blackjack newGame = new Blackjack(player);
                Start.clear();
                newGame.rejoue = true;
                newGame.play();
            } else {
                Start.clear();
                ScreenGestion.printTmp(ScreenGestion.strJauneGras("\tMerci d'avoir joué au Blackjack! Vous repartez avec: " + miseTotale + " €.\n"));
                Start.retourMenuPrincipal(player);
            }
        } catch (Exception e) {
            System.out.println(ScreenGestion.strRougeGras("\tErreur lors de la lecture de l'entrée utilisateur."));
        }
    }

    public void afficherRegles() {
    }

    public void afficherIntroJeu() {
    }

    public static void main(String[] args) {
        Player joueur = new Player("Joueur1");
        Blackjack game = new Blackjack(joueur);
        game.play();
    }

}
