
package main;
import java.util.Scanner;

/*
* La classe Mastermind gère le fonctionnement du jeu Mastermind.
 * Elle permet de générer une combinaison secrète, de vérifier les propositions du joueur
 * et de retourner le résultat sous forme de pions bien placés et mal placés.
 * <p>
 * Le but du jeu est de deviner la combinaison secrète en un nombre limité de tentatives.
 * </p>
*/

public class Mastermind extends Jeu{
    
    /**
     * Le code secret à deviner, représenté comme un tableau d'entiers.
     */
    private int[] code;

    public int[] getCode() {
        return code;
    }
    /**
     * Le nombre de tentatives déjà effectuées par le joueur.
     */
    private int attempts;

    /**
     * Le nombre maximum de tentatives autorisées pour deviner le code.
     */
    private int max_attempts;

    /**
     * Indique si le joueur a résolu le code ou non.
     */
    private boolean isSolved;

    /**
     * La mise totale disponible pour le joueur au début de la partie.
     * Initialisée à 100.
     */
    private Argent miseTotale;//en attente des fonds du joueurs

    /**
     * La mise actuelle placée par le joueur pour une tentative.
     */
    private int mise;

    /**
     * Le gain obtenu par le joueur s'il réussit à deviner le code.
     */
    private int gain;


    /**
     * Génère un code secret aléatoire composé de 4 chiffres entre 1 et 9.
     * @return Un tableau d'entiers représentant le code secret.
     */
    public int[] generateCode() {
        code = new int[4];
        for (int i = 0; i < 4; i++) {
            code[i] = (int)(Math.random() * 9) + 1;
        }
        return code;
    }

    private boolean rejoue;
    
    /**
     * Initialise une nouvelle partie de Mastermind en générant un nouveau code
     * et en réinitialisant le nombre de tentatives et l'état de résolution.
     */
    public Mastermind(Player joueur) {
        this.player = joueur;
        this.miseTotale = joueur.getSolde();
        this.code=generateCode();
        this.rejoue = false;
        attempts = 0;
        isSolved = false;
    }
    
    /**
     * Demande à l'utilisateur d'entrer un code à 4 chiffres et valide l'entrée.
     * @return Le code entré par l'utilisateur sous forme de chaîne de caractères.
     */
    public String returnUserCode() {
        try {
            System.out.println("Veuillez entrez un code à 4 chiffres compris entre 1 et 9");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            while(input.length() != 4 || !input.matches("[1-9]{4}")) {
                if(input.length() != 4) {
                    System.out.println(ScreenGestion.strRougeGras("Merci d'entrez un code à 4 chiffres"));
                    input = scanner.nextLine();
                }
                else if(!input.matches("[1-9]{4}")){
                    System.out.println(ScreenGestion.strRougeGras("Merci d'entrez un code à 4 chiffres entre 1 et 9"));
                    input = scanner.nextLine();
                }
            }
            attempts++;

            return input;
        } catch (Exception e){
            System.out.println(ScreenGestion.strRougeGras("Veuillez entrer un code correct"));
            return "";
        }
    }

    /**
     * Convertit un tableau d'entiers en une chaîne de caractères.
     * @param array Le tableau d'entiers à convertir.
     * @return Une chaîne de caractères représentant les entiers du tableau.
     */
    public static String arrayToString(int[] array) {
        StringBuilder sb = new StringBuilder();
        for (int num : array) {
            sb.append(num);
        }
        return sb.toString();
    }

    /* Fonction qui permet de vérifeier si une lettre est bien placée
     * @param codeUser le code entré par l'utilisateur
     * @return le nombre de lettres bien placées
    */
    public void bonnePlaceLettre(String userCode) {
        String[] arrayCodeUser = userCode.split("");
        String[] arrayCodeBot = arrayToString(code).split("");
        String[] casesCouleur = new String[]{"🟥","🟥","🟥","🟥"};
        boolean[] bienPlaces = new boolean[4]; // Pour éviter de compter 2 fois
        boolean[] utiliseDansBot = new boolean[4]; // Pour le repérage jaune


        // Étape 1 : Vérifier les bien placés (verts)
        for (int i = 0; i < code.length; i++) {
            if (arrayCodeBot[i].equals(arrayCodeUser[i])) {
                casesCouleur[i] = "🟩";
                bienPlaces[i] = true;
                utiliseDansBot[i] = true;
            }
        }

        // Étape 2 : Vérifier les mal placés (jaunes)
        for (int i = 0; i < code.length; i++) {
            if (!"🟩".equals(casesCouleur[i])) {
                for (int j = 0; j < code.length; j++) {
                    if (!bienPlaces[j] && !utiliseDansBot[j] && arrayCodeUser[i].equals(arrayCodeBot[j])) {
                        casesCouleur[i] = "🟨";
                        utiliseDansBot[j] = true;
                        break;
                    }
                }
            }
        }

        // Afficher les couleurs
        for (String c : casesCouleur) {
            System.out.print(c);
        }

        System.out.println(); // Pour un retour à la ligne après l'affichage
    }

    /**
     * Gère le déroulement d'une partie de Mastermind, y compris la gestion des mises,
     * des tentatives et de l'affichage des résultats.
     */
    public void play() {
        Start.afficher_Txt("../resources/mastermind/mastermind.txt");

        if(!rejoue){
            ScreenGestion.printTmp("Vous voici au Mastermind  !!! \nLes régles sont simples, vous devez deviner un code composé de 4 chiffres entre 1 et 9.\nVous avez le choix entre 1, 5 ou 10 tentatives pour trouver le code.\nPlus vous choisissez de tentatives moins votre gain sera élevé.\nSi vous trouvez le code en une tentative vous gagnez votre mise X 10, en 5 tentatives X 5 et en 10 tentatives votre mise.");
            ScreenGestion.printTmp("Pour vous aider, après chaque tentative, vous recevrez des indices sous forme de couleurs :\n🟩 signifie qu'un chiffre est bien placé.\n🟨 signifie qu'un chiffre est dans le code mais mal placé.\n🟥 signifie qu'un chiffre n'est pas dans le code.\nAttention aux chiffres doublons ;)\n");
        }

        System.out.println("Vous possédez actuellement :"+miseTotale);
        mise = Integer.parseInt(Tools.intervalleEntreeUser(1, miseTotale.getSolde(), "Combien voulez-vous misez ? Entre 1 et " + miseTotale));
        boolean valide = false;
        Scanner scanner = new Scanner(System.in);
        String input = "";
        int valeur = 0;
        while (!valide) {
            System.out.println("Combien de tentatives voulez-vous faire ?(1/5/10)");
            input = scanner.nextLine();

            try {
                valeur = Integer.parseInt(input);
                if (valeur == 1 || valeur == 5 || valeur == 10) {
                    valide = true;
                } else {
                    System.out.println(ScreenGestion.strRougeGras("Veuillez entrer un nombre de tentatives valide (1, 5 ou 10) :"));
                }
            } catch (NumberFormatException e) {
                System.out.println(ScreenGestion.strRougeGras("Entrée invalide. Veuillez entrer un nombre valide."));
            }
        }
        max_attempts = valeur;

    
        
        String codeUser = "";
        while (attempts < max_attempts && !isSolved) {
            codeUser = returnUserCode();
            bonnePlaceLettre(codeUser);
            System.out.println("Tentative " + attempts + ": " + codeUser);
            if (codeUser.equals(arrayToString(code))) {
                isSolved = true;
                System.out.println("Félicitations! Vous avez trouvé le code.");
                if(max_attempts == 1){
                    gain = mise * 10;
                } else if (max_attempts <= 5){
                    gain = mise * 5;
                } else {
                    gain = mise;
                }
                System.out.println("Vous gagnez: " + gain + " €");
                miseTotale.ajouterSolde(gain);
                System.out.println("Vous avez maintenant: " + miseTotale + ".");
            }else {
                if(attempts < max_attempts) {
                    System.out.println("Essayez encore.");
                }
                else{
                    System.out.println("Vous avez utilisé toutes vos tentatives.");
                }
            }
        }
        if (!isSolved) {
            miseTotale.AppliquerMalus(mise);
            System.out.println("Dommage, vous n'avez pas trouvé le code. Vous perdez votre mise de: " + mise + " €. Il vous reste: " + miseTotale + " €.");
            System.out.println("Le code était: " + arrayToString(code));
        }
        rejouer();
    }

    public void rejouer(){
        if (miseTotale.getSolde() <= 0) {
            ScreenGestion.printTmp(ScreenGestion.strRougeGras("Vous n'avez plus d'argent pour continuer à jouer."));
            ScreenGestion.printTmp(ScreenGestion.strRougeGras("Ce casino n'accepte pas les gens sans argent, désolé !"));
        }
        else {
            try{
                System.out.println("Voulez-vous continuer à jouer ? (oui/non)");
                Scanner scanner = new Scanner(System.in);
                String input = scanner.nextLine().toLowerCase();
                while (!input.equals("oui") && !input.equals("non")) {
                    System.out.println(ScreenGestion.strRougeGras("Veuillez répondre par 'oui' ou 'non'."));
                    input = scanner.nextLine().toLowerCase();   
                }
                if(input.equals("oui")){
                    Mastermind newGame = new Mastermind(player);
                    Start.clear();
                    newGame.rejoue = true;
                    newGame.play();
                }else {
                    Start.clear();
                    ScreenGestion.printTmp(ScreenGestion.strJauneGras("Merci d'avoir joué au Mastermind! Vous repartez avec: " + miseTotale + " €.\n"));
                    Start.retourMenuPrincipal(player);
                }
            }catch (Exception e){
                System.out.println(ScreenGestion.strRougeGras("Erreur lors de la lecture de l'entrée utilisateur."));
            }
        }
    }
    public static void main(String[] args) {
        Player joueur = new Player("Joueur1");
        Mastermind game = new Mastermind(joueur);
        game.play();

    }
}
