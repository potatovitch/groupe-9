package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ScreenGestion {

    private final static String PATH = "src/resources/";

    public static String strJaune(String str) {
        return "\33[33m" + str + "\33[0m";
    }

    public static String strJauneGras(String str) {
        return "\33[1;33m" + str + "\33[0m";
    }

    public static String strRouge(String str) {
        return "\33[31m" + str + "\33[0m";
    }

    public static String strRougeGras(String str) {
        return "\33[1;31m" + str + "\33[0m";
    }




    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
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

    public static void clear() {
        try {
            new ProcessBuilder("clear").inheritIO().start().waitFor();
        } catch (Exception e) {
            for (int i = 0; i < 50; i++) {
                System.out.println();
            }
        }
    }

    public static void afficher_Txt(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(PATH + fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println(ScreenGestion.strRougeGras("Impossible d'ouvrir le fichier " + fileName + "... Veuillez vérifier !"));
            e.printStackTrace();
        }
    }

    public void afficher_TxtTmp(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(PATH + fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                printTmp(line);
            }
        } catch (IOException e) {
            System.out.println(ScreenGestion.strRougeGras("Impossible d'ouvrir le fichier " + fileName + "... Veuillez vérifier !"));
            e.printStackTrace();
        }
    }
}
