package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.io.File;

/*import javax.sound.sampled.*;*/

public class SlotMachine {

    /*
     * private final String ADRESSILIES =
     * "ltc1qlx8dp0zmmtr4rq3wwdqhyesut8hst4yv02p2tt";
     */

    private SymboleSlotMachineRoueUn symbole1;
    private SymboleSlotMachineRoueDeux symbole2;
    private SymboleSlotMachineRoueTrois symbole3;

    private final static String PATH = "src/resources/machine_a_sous/";
    /* private Clip clip; */

    private boolean rejoue;

    public SlotMachine() {
        this.symbole1 = getRandomSymbole1();
        this.symbole2 = getRandomSymbole2();
        this.symbole3 = getRandomSymbole3();
    }

    public SymboleSlotMachineRoueUn getSymbole1() {
        return symbole1;
    }

    public void setSymbole1(SymboleSlotMachineRoueUn symbole1) {
        this.symbole1 = symbole1;
    }

    public SymboleSlotMachineRoueDeux getSymbole2() {
        return symbole2;
    }

    public void setSymbole2(SymboleSlotMachineRoueDeux symbole2) {
        this.symbole2 = symbole2;
    }

    public SymboleSlotMachineRoueTrois getSymbole3() {
        return symbole3;
    }

    public void setSymbole3(SymboleSlotMachineRoueTrois symbole3) {
        this.symbole3 = symbole3;
    }

    public List<SymboleSlotMachineRoueUn> getShuffledSymboles1() {
        List<SymboleSlotMachineRoueUn> symbolesList = new ArrayList<>(Arrays.asList(SymboleSlotMachineRoueUn.values()));
        Collections.shuffle(symbolesList);

        return symbolesList;
    }

    public List<SymboleSlotMachineRoueDeux> getShuffledSymboles2() {
        List<SymboleSlotMachineRoueDeux> symbolesList = new ArrayList<>(
                Arrays.asList(SymboleSlotMachineRoueDeux.values()));
        Collections.shuffle(symbolesList);

        return symbolesList;
    }

    public List<SymboleSlotMachineRoueTrois> getShuffledSymboles3() {
        List<SymboleSlotMachineRoueTrois> symbolesList = new ArrayList<>(
                Arrays.asList(SymboleSlotMachineRoueTrois.values()));
        Collections.shuffle(symbolesList);

        return symbolesList;
    }

    public SymboleSlotMachineRoueUn getRandomSymbole1() {
        int randomIndex = (int) (Math.random() * SymboleSlotMachineRoueUn.values().length);

        SymboleSlotMachineRoueUn symbole = getShuffledSymboles1().get(randomIndex);
        return symbole;
    }

    public SymboleSlotMachineRoueDeux getRandomSymbole2() {
        int randomIndex = (int) (Math.random() * SymboleSlotMachineRoueUn.values().length);

        SymboleSlotMachineRoueDeux symbole = getShuffledSymboles2().get(randomIndex);
        return symbole;
    }

    public SymboleSlotMachineRoueTrois getRandomSymbole3() {
        int randomIndex = (int) (Math.random() * SymboleSlotMachineRoueUn.values().length);

        SymboleSlotMachineRoueTrois symbole = getShuffledSymboles3().get(randomIndex);
        return symbole;
    }

    public boolean EstDeuxPareil() {
        if (this.getSymbole1().getId() == this.getSymbole2().getId()
                || this.getSymbole1().getId() == this.getSymbole3().getId()
                || this.getSymbole2().getId() == this.getSymbole3().getId()) {
            return true;
        }
        return false;
    }

    public boolean EstTroisPareil() {
        if (this.getSymbole1().getId() == this.getSymbole2().getId()
                && this.getSymbole2().getId() == this.getSymbole3().getId()) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "=== MACHINE À SOUS ===\n";
    }

    public void spin() {
        this.symbole1 = getRandomSymbole1();
        this.symbole2 = getRandomSymbole2();
        this.symbole3 = getRandomSymbole3();
    }

    public void spinInterface() {
        /* playMusic(PATH); */

        Start.clear();
        for (int i = 1; i < 6; i++) {
            Start.afficher_Txt("machine_a_sous/slotMachine" + i + ".txt");
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Start.clear();
        }
        for (int i = 5; i > 0; i--) {
            Start.afficher_Txt("machine_a_sous/slotMachine" + i + ".txt");
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Start.clear();
        }

        /* stopMusic(); */
    }

    /*
     * public void playMusic(String filePath) {
     * try {
     * File audioFile = new File(filePath);
     * AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
     * 
     * clip = AudioSystem.getClip();
     * clip.open(audioStream);
     * clip.start();
     * } catch (Exception e) {
     * System.err.println("Erreur audio : " + e.getMessage());
     * }
     * }
     * 
     * public void stopMusic() {
     * if (clip != null && clip.isRunning()) {
     * clip.stop();
     * clip.close();
     * }
     * }
     */

    public void afficherJackpot() {
        Start.clear();
        for (int i = 1; i < 6; i++) {
            Start.afficher_Txt("machine_a_sous/Jackpot1.txt");
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Start.clear();
            Start.afficher_Txt("machine_a_sous/Jackpot2.txt");
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Start.clear();
        }
    }

    public void alignerTxtFiles(String filename1, String filename2, String filename3) {

        List<String> ascii1 = new ArrayList<>();
        List<String> ascii2 = new ArrayList<>();
        List<String> ascii3 = new ArrayList<>();

        try {
            ascii1 = readFile(filename1);
            ascii2 = readFile(filename2);
            ascii3 = readFile(filename3);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int maxLines = Math.max(ascii1.size(), Math.max(ascii2.size(), ascii3.size()));

        padLines(ascii1, maxLines);
        padLines(ascii2, maxLines);
        padLines(ascii3, maxLines);

        for (int i = 0; i < maxLines; i++) {
            System.out.println(ascii1.get(i) + "   " + ascii2.get(i) + "   " + ascii3.get(i));
        }
    }

    private static List<String> readFile(String filename) throws IOException {
        List<String> lines = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(PATH + filename));
        String line;
        while ((line = br.readLine()) != null) {
            lines.add(line);
        }
        br.close();
        return lines;
    }

    private static void padLines(List<String> lines, int totalLines) {
        while (lines.size() < totalLines) {
            lines.add("");
        }
    }

    public double changerMise(Player player, double miseTotale) {
        double mise = 0;
        try {
            System.out.println("Votre mise totale est de : " + miseTotale + " €.");
            System.out.println("Combien voulez-vous miser ?");
            Scanner scanner = new Scanner(System.in);
            try {
                int input = scanner.nextInt();
                if (input <= miseTotale) {
                    mise = input;
                } else {
                    System.out.println(
                            ScreenGestion.strRougeGras("La mise jouée doit être inférieure ou égale à votre mise totale. Pour rappel, votre mise totale est de: "
                                    + miseTotale + " €"));
                }
            } catch (Exception e) {
                System.out.println(ScreenGestion.strRougeGras("Veuillez entrer un nombre valide pour la mise."));
            }

        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }
        return mise;
    }

    public void play(Player player) {
        Start.clear();

        if(!rejoue){
            ScreenGestion.printTmp("Vous voici à la Machine à sous !!! \nLes régles sont simple, vous devez aligner 2 ou 3 symboles identiques pour gagner.\nRécompenses : le multiplicateur va entirement dépendre des signes qui vont être alignés !\n");
        }

        boolean jackpot = false;
        boolean end = false;
        double gain = 0;
        Scanner scanner = new Scanner(System.in);
        double miseTotale = player.getSolde().getSolde();
        double mise = 0;
        while (mise <= 0) {
            mise = changerMise(player, miseTotale);
        }

        while (!end && mise <= player.getSolde().getSolde()) {
            Start.clear();
            if (jackpot == false) {
                spin();
            } else {
                symbole1 = SymboleSlotMachineRoueUn.SEVEN;
                symbole2 = SymboleSlotMachineRoueDeux.SEVEN;
                symbole3 = SymboleSlotMachineRoueTrois.SEVEN;
                jackpot = false;
            }

            spinInterface();
            gain = 0;
            player.getSolde().AppliquerMalus(mise);
            System.out.println(this.toString());
            alignerTxtFiles(symbole1.getEmoji() + ".txt", symbole2.getEmoji() + ".txt", symbole3.getEmoji() + ".txt");

            if (EstTroisPareil()) {
                if (symbole1.getId() == 6) {// Si le symbole est le 7
                    afficherJackpot();

                    System.out.println(this.toString());
                    alignerTxtFiles(symbole1.getEmoji() + ".txt", symbole2.getEmoji() + ".txt",
                            symbole3.getEmoji() + ".txt");
                }
                System.out.println("Félicitations ! Vous avez gagné le jackpot avec trois symboles identiques : "
                        + symbole1.toString());
                gain = mise * symbole1.getMulti3();
                System.out.println("Vous gagnez " + gain + " € !");
                player.getSolde().AppliquerBonus(gain);

                miseTotale = player.getSolde().getSolde();
                System.out.println("Il vous reste " + player.getSolde().getSolde() + " €.");
                System.out.println("Votre mise est de : " + mise + " €.");

                try{
                    System.out.println("appuyez sur Entrée pour rejouer, tapez 'q' pour quitter ou tapez 'm' pour changer de mise.");
                    String input = scanner.nextLine().toLowerCase();
                    while (!input.equals("") && !input.equals("q") && !input.equals("m") && !input.equals("motcommun")) {
                        System.out.println(ScreenGestion.strRougeGras("Veuillez appuyer sur 'Entrée', 'q' ou 'm'."));
                        input = scanner.nextLine().toLowerCase();   
                    }
                    if (input.equalsIgnoreCase("q")) {
                        end = true;
                    } else {
                        end = false;
                        rejoue = true;
                    }
                    if (input.equalsIgnoreCase("m")) {
                        Start.clear();
                        mise = changerMise(player, miseTotale);
                        while (mise <= 0) {
                            mise = changerMise(player, miseTotale);
                        }
                    }
                    if (input.equalsIgnoreCase("motcommun")) {
                        jackpot = true;
                    }
                }catch (Exception e){
                    System.out.println(ScreenGestion.strRougeGras("Erreur lors de la lecture de l'entrée utilisateur."));
                }
                    

            } else if (EstDeuxPareil()) {
                System.out.println("Bravo ! Vous avez gagné avec deux symboles identiques.");
                if (this.getSymbole1().getId() == this.getSymbole2().getId()
                        || this.getSymbole1().getId() == this.getSymbole3().getId()) {
                    gain = mise * symbole1.getMulti2();
                } else {
                    gain = mise * symbole2.getMulti2();
                }
                System.out.println("Vous gagnez " + gain + " € !");
                player.getSolde().AppliquerBonus(gain);

                miseTotale = player.getSolde().getSolde();
                System.out.println("Il vous reste " + player.getSolde().getSolde() + " €.");
                System.out.println("Votre mise est de : " + mise + " €.");

               
                try{
                    System.out.println("appuyez sur Entrée pour rejouer, tapez 'q' pour quitter ou tapez 'm' pour changer de mise.");
                    String input = scanner.nextLine().toLowerCase();
                    while (!input.equals("") && !input.equals("q") && !input.equals("m") && !input.equals("motcommun")) {
                        System.out.println(ScreenGestion.strRougeGras("Veuillez appuyer sur 'Entrée', 'q' ou 'm'."));
                        input = scanner.nextLine().toLowerCase();   
                    }
                    if (input.equalsIgnoreCase("q")) {
                        end = true;
                    } else {
                        end = false;
                        rejoue = true;
                    }
                    if (input.equalsIgnoreCase("m")) {
                        Start.clear();
                        mise = changerMise(player, miseTotale);
                        while (mise <= 0) {
                            mise = changerMise(player, miseTotale);
                        }
                    }
                    if (input.equalsIgnoreCase("motcommun")) {
                        jackpot = true;
                    }
                }catch (Exception e){
                    System.out.println(ScreenGestion.strRougeGras("Erreur lors de la lecture de l'entrée utilisateur."));
                }
                    

            } else {
                System.out.println("Dommage, vous n'avez pas gagné cette fois. Essayez encore !");
                miseTotale = player.getSolde().getSolde();
                System.out.println("Il vous reste " + player.getSolde().getSolde() + " €.");
                if (player.getSolde().getSolde() <= 0) {
                    end = true;
                    break;
                }
                System.out.println("Votre mise est de : " + mise + " €.");
                if (player.getSolde().getSolde() < mise) {
                    System.out.println("Vous n'avez plus assez d'argent pour continuer à jouer.");

                    
                try{
                    System.out.println("Tapez 'q' pour quitter ou tapez 'm' pour changer de mise.");
                    String input = scanner.nextLine().toLowerCase();
                    while (!input.equals("q") && !input.equals("m") && !input.equals("motcommun")) {
                        System.out.println(ScreenGestion.strRougeGras("Veuillez appuyer sur 'Entrée', 'q' ou 'm'."));
                        input = scanner.nextLine().toLowerCase();   
                    }
                    if (input.equalsIgnoreCase("q")) {
                        end = true;
                    } else {
                        end = false;
                    }
                    if (input.equalsIgnoreCase("m")) {
                        Start.clear();
                        mise = changerMise(player, miseTotale);
                        while (mise <= 0) {
                            mise = changerMise(player, miseTotale);
                        }
                    }
                }catch (Exception e){
                    System.out.println(ScreenGestion.strRougeGras("Erreur lors de la lecture de l'entrée utilisateur."));
                }
                } else {
                    try{
                        System.out.println("appuyez sur Entrée pour rejouer, tapez 'q' pour quitter ou tapez 'm' pour changer de mise.");
                        String input = scanner.nextLine().toLowerCase();
                        while (!input.equals("") && !input.equals("q") && !input.equals("m") && !input.equals("motcommun")) {
                            System.out.println(ScreenGestion.strRougeGras("Veuillez appuyer sur 'Entrée', 'q' ou 'm'."));
                            input = scanner.nextLine().toLowerCase();   
                        }
                        if (input.equalsIgnoreCase("q")) {
                            end = true;
                        } else {
                            end = false;
                            rejoue = true;
                        }
                        if (input.equalsIgnoreCase("m")) {
                            Start.clear();
                            mise = changerMise(player, miseTotale);
                            while (mise <= 0) {
                                mise = changerMise(player, miseTotale);
                            }
                        }
                        if (input.equalsIgnoreCase("motcommun")) {
                            jackpot = true;
                        }
                    }catch (Exception e){
                        System.out.println(ScreenGestion.strRougeGras("Erreur lors de la lecture de l'entrée utilisateur."));
                    }

                }
            }
        }
        if (player.getSolde().getSolde() <= 0) {
            ScreenGestion.printTmp(ScreenGestion.strRougeGras("Vous n'avez plus d'argent pour continuer à jouer."));
            ScreenGestion.printTmp(ScreenGestion.strRougeGras("Ce casino n'accepte pas les gens sans argent, désolé !"));
        }else{
            ScreenGestion.printTmp(ScreenGestion.strJauneGras("\tMerci d'avoir joué à la Machine à Sous! Vous repartez avec: " +player.getSolde().getSolde() + " €."));
            Start.retourMenuPrincipal(player);
        }
        

    }
    
    public static void main(String[] args) {
        Player player = new Player("John");
        SlotMachine slotMachine = new SlotMachine();
        slotMachine.play(player);
    }

}