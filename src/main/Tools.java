package main;

import java.util.Scanner;

public class Tools {
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

    public static String tailleEntreeUser(int taille, String message, String messageErreur) {
        Scanner scanner = new Scanner(System.in);
        String input;

        while (true) {
            System.out.println(message);

            // Si l'utilisateur entre CTRL+D (EOF)
            if (!scanner.hasNextLine()) {
                System.out.println(ScreenGestion.strRougeGras("Entrée obligatoire. Fin de saisie non autorisée (CTRL+D détecté)."));
                // Clear l'état du scanner (optionnel selon IDE/OS)
                scanner = new Scanner(System.in);
                continue;
            }

            input = scanner.nextLine().trim();

            if (input.length() >= 1 && input.length() <= taille && !input.matches(".*\\d.*")) {
                return input; // ✅ Entrée valide
            } else {
                System.out.println(ScreenGestion.strRougeGras(messageErreur));
            }
        }
    }

    public static String taillemdp(int taille, String message, String messageErreur) {
        Scanner scanner = new Scanner(System.in);
        String input;

        while (true) {
            System.out.println(message);

            // Gestion du CTRL+D (EOF)
            if (!scanner.hasNextLine()) {
                System.out.println(ScreenGestion.strRougeGras("Saisie obligatoire. Fin de saisie non autorisée (CTRL+D détecté)."));
                // Réinitialise le Scanner au besoin (selon environnement)
                scanner = new Scanner(System.in);
                continue;
            }

            input = scanner.nextLine().trim();

            if (input.length() >= 1 && input.length() <= taille) {
                return input; // ✅ mot de passe valide
            } else {
                System.out.println(ScreenGestion.strRougeGras(messageErreur));
            }
        }
    }

    public static String intervalleEntreeUserCharUnique (int debut, int fin, String message) {
        String input = "";
        boolean valide = false;
        Scanner scanner = new Scanner(System.in);
        while (!valide) {
            valide = true;
            System.out.println(message);
            input = scanner.nextLine();
            if (input.length() != 1) {
                System.out.print(ScreenGestion.strRougeGras("veuillez n'entrer qu'un seul caractère, compris entre " + debut + " et " + fin));
                valide = false;

            } else {
                if (input.charAt(0) < (char) ('0' + debut) || input.charAt(0) > (char) ('0' + fin)) {
                    System.out.println(ScreenGestion.strRougeGras("veuillez n'entrer qu'un caractère compris entre " + debut + " et " + fin));
                    valide = false;
                }
            }
            
        }
        return input;
    }

    public static String intervalleEntreeUser(double debut, double fin, String message) {
    String input;
    Scanner scanner = new Scanner(System.in);
    while (true) {
        System.out.println(message);

        // Vérifie si l'utilisateur a fait CTRL+D (EOF)
        if (!scanner.hasNextLine()) {
            System.out.println(ScreenGestion.strRougeGras("Saisie obligatoire. Fin de saisie non autorisée (CTRL+D détecté)."));
            // Réinitialise le scanner (utile dans certains cas)
            scanner = new Scanner(System.in);
            continue;
        }

        input = scanner.nextLine().trim();

        try {
            int valeur = Integer.parseInt(input);
            if (valeur >= debut && valeur <= fin) {
                return input; // ✅ Valeur dans l'intervalle
            } else {
                System.out.println(ScreenGestion.strRougeGras(
                    "Veuillez entrer un nombre entre " + (int) debut + " et " + (int) fin + "."));
            }
        } catch (NumberFormatException e) {
            System.out.println(ScreenGestion.strRougeGras("Entrée invalide. Veuillez entrer un nombre valide."));
        }
    }
}


    public static boolean estUnChiffre (String entree) {
        if (entree.length() != 1) {
            return false;
        } else {
            return entree.charAt(0) >= '0' && entree.charAt(0) <= '9';
        }
    }


}
