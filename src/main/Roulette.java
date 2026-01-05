package main;

import java.util.*;

public class Roulette extends Jeu{
    private static final int[] NUMBERS = new int[37]; // 0 à 36
    private static final Map<Integer, String> COLORS = new HashMap<>();
    private Random random;
    private double[] mise = new double[50];
    private String[] cases = new String[50];
    private int idxMise=0;
    private int miseTotal;
    private boolean rejoue = false;
    private String[] misePossible = {
        // Numéros pleins
        "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
        "10", "11", "12", "13", "14", "15", "16", "17", "18",
        "19", "20", "21", "22", "23", "24", "25", "26", "27",
        "28", "29", "30", "31", "32", "33", "34", "35", "36",

        //Couleur
        "rouge","noir","vert",

        // Chances simples
        "Pair", "Impair",
        "1r (1-18)", "Passe (19-36)"
    };
    Scanner scanner = new Scanner(System.in);

    static {
        // Remplir la roulette
        for (int i = 0; i <= 36; i++) {
            NUMBERS[i] = i;
        }

        // Définir les couleurs (rouge/noir selon la vraie roulette européenne)
        // Le 0 est vert
        COLORS.put(0, "Vert");

        int[] rouges = {1,3,5,7,9,12,14,16,18,19,21,23,25,27,30,32,34,36};
        for (int r : rouges) {
            COLORS.put(r, "Rouge");
        }

        for (int i = 1; i <= 36; i++) {
            COLORS.putIfAbsent(i, "Noir");
        }
    }

    public Roulette(Player player) {
        this.random = new Random();
        this.player = player;
    }

    /** Lance la roulette et retourne le numéro tiré */
    public int spin() {
        int index = random.nextInt(NUMBERS.length); // tirage de 0 à 36
        return NUMBERS[index];
    }

    /** Retourne la couleur du numéro */
    public String getColor(int number) {
        return COLORS.getOrDefault(number, "Inconnu");
    }

    public boolean bonneMise(String mise){
        for(String m:misePossible){
            if(m.equalsIgnoreCase(mise)){
                return true;
            }
        }
        return false;
    }

    public void bet() {
        boolean miseOK = false;
        String caseTemp;
        int miseTemp;
        String answer;

        while(!miseOK && idxMise<40){
            ScreenGestion.printTmp("\nSur quoi souhaitez vous miser ?");
            caseTemp= scanner.next();
            if(!bonneMise(caseTemp)){
                while(!bonneMise(caseTemp)){
                    ScreenGestion.printTmp(ScreenGestion.strRougeGras("Choix invalide... "));
                    ScreenGestion.printTmp(ScreenGestion.strRougeGras("Merci de le refaire."));
                    caseTemp = scanner.next();
                }
            }
            cases[idxMise]=caseTemp;
            ScreenGestion.printTmp("\nSuper, à présent combien souhaitez vous miser ? (Solde actuel : "+ player.getSolde() +" )");
            miseTemp = -1;
            boolean saisieValide = false;

            while (!saisieValide) {
                ScreenGestion.printTmp("Entrez votre mise (entre 1 et " + player.getSolde() + "): ");

                if (scanner.hasNextInt()) {
                    miseTemp = scanner.nextInt();
                    if (miseTemp > 0 && miseTemp <= player.solde.getSolde()) {
                        saisieValide = true; // ✅ la saisie est correcte
                        player.solde.retirerSolde(miseTemp);
                    } else {
                        ScreenGestion.printTmp(ScreenGestion.strRougeGras("Montant invalide ! Il doit être compris entre 1 et " + player.getSolde() + "."));
                    }
                } else {
                    // ⚠️ on consomme l'entrée non-numérique pour éviter une boucle infinie
                    ScreenGestion.printTmp(ScreenGestion.strRougeGras("Erreur : vous devez entrer un nombre entier !"));
                    scanner.next();
                }
            }
            mise[idxMise]=miseTemp;
            miseTotal+=miseTemp;
            ScreenGestion.printTmp("Très bien vous venez de miser "+miseTemp+"€ sur le "+cases[idxMise]);
            idxMise++;
            if(player.getSolde().getSolde() > 0) {
                ScreenGestion.printTmp("\nSouhaitez vous miser sur autre choses ? ( Solde actuel : "+ player.getSolde() +" )\n[1] Oui\n[2] Non\n");
                answer = "";
                boolean validAnswer = false;
                while (!validAnswer) {
                    System.out.print("Votre choix: ");
                    answer = scanner.next();
                    answer = answer.toUpperCase();
                    if (answer.equalsIgnoreCase("1") || answer.equalsIgnoreCase("oui")) {
                        validAnswer = true; // l'utilisateur continue
                    } else if (answer.equalsIgnoreCase("2") || answer.equalsIgnoreCase("non")) {
                        validAnswer = true;
                        miseOK = true; // il arrête les mises
                    } else {
                        ScreenGestion.printTmp(ScreenGestion.strRougeGras("Choix invalide, merci de taper 1, 2, Oui ou Non !"));
                    }
                }
            }else{
                miseOK = true;
                ScreenGestion.printTmp(ScreenGestion.strRougeGras("Vous n'avez plus d'argent pour continuer à miser."));
            }
        }
        ScreenGestion.clearScreen();
    }

    public boolean bonTirage(int numero,String mise){
        return true;
    }

    /** Simule un tirage complet */
    public void tirage() {
        int result = spin();
        String castResult = ""+result;
        String color = getColor(result);
        ScreenGestion.printTmp("La bille est tombée sur " + result + " qui est de couleur "+getColor(result));
        for(int i=0;i<idxMise;i++){
            if(cases[i].equalsIgnoreCase(castResult) || cases[i].equalsIgnoreCase(color) || (cases[i].equalsIgnoreCase("pair") && result%2==0 && result!=0) || (cases[i].equalsIgnoreCase("impair") && result%2!=0) || (cases[i].equalsIgnoreCase("1r (1-18)") && result>=1 && result<=18) || (cases[i].equalsIgnoreCase("passe (19-36)") && result>=19 && result<=36)){
                if(cases[i].equalsIgnoreCase(castResult)){
                    ScreenGestion.printTmp("Félicitation vous avez misé sur "+cases[i]+" vous gagnez "+mise[i]*36+"€");
                    player.solde.ajouterSolde((int)mise[i]*36);
                }if(cases[i].equalsIgnoreCase(color)){
                    ScreenGestion.printTmp("Félicitation vous avez misé sur "+cases[i]+" vous gagnez "+mise[i]*2+"€");
                    player.solde.ajouterSolde((int)mise[i]*2);
                }if((cases[i].equalsIgnoreCase("pair") && result%2==0 && result!=0) || (cases[i].equalsIgnoreCase("impair") && result%2!=0)){
                    ScreenGestion.printTmp("Félicitation vous avez misé sur "+cases[i]+" vous gagnez "+mise[i]*2+"€");
                    player.solde.ajouterSolde((int)mise[i]*2);
                }
            }else{
                ScreenGestion.printTmp("Dommage vous avez misé sur "+cases[i]+" vous perdez votre mise de "+mise[i]+"€");
                
            }
        }
        ScreenGestion.printTmp("Il vous reste "+player.getSolde());
        rejouer();
    }

    
    public void rejouer(){
        if(player.getSolde().getSolde() <= 0) {
            ScreenGestion.printTmp(ScreenGestion.strRougeGras("Vous n'avez plus d'argent pour continuer à jouer."));
            ScreenGestion.printTmp(ScreenGestion.strRougeGras("Ce casino n'accepte pas les gens sans argent, désolé !"));
        }
        else {
            try{
                System.out.println("\nVoulez-vous continuer à jouer ? (oui/non)");
                Scanner scanner = new Scanner(System.in);
                String input = scanner.nextLine().toLowerCase();
                while (!input.equals("oui") && !input.equals("non")) {
                    System.out.println(ScreenGestion.strRougeGras("Veuillez répondre par 'oui' ou 'non'."));
                    input = scanner.nextLine().toLowerCase();   
                }
                if(input.equals("oui")){
                    Start.clear();    
                    Roulette r = new Roulette(player);
                    r.rejoue = true;
                    r.play();
                }else {
                    Start.clear();
                    ScreenGestion.printTmp(ScreenGestion.strJauneGras("Merci d'avoir joué à la roulette ! Vous repartez avec: " + player.getSolde() + "\n"));
                    Start.retourMenuPrincipal(player);
                }
            }catch (Exception e){
                System.out.println(ScreenGestion.strRougeGras("Erreur lors de la lecture de l'entrée utilisateur."));
            }
        }
    }

    public void play(){
        ScreenGestion.clearScreen();
        if(!rejoue){
            ScreenGestion.printTmp("Vous voici à la ROULETTE !!! \nLes régles sont simple, vous pouvez parier sur un numéro en 0 et 36 et/ou une couleur (Rouge ou Noir) et/ou (Pair ou Impair).\nRécompenses : Si vous avez parié sur le bon numéro vous gagnez votre mise X 36 et pour la couleur ainsi que les Pair/Impair vous gagnez votre mise X 2.");
        }
        this.bet();
        this.tirage();
    }

    public static void main(String[] args) {
        Player player = new Player("test");
        Roulette r = new Roulette(player);
        r.play();
    }
}

/* TO-DO list & Idée : 
 * ASCII
 * 
*/