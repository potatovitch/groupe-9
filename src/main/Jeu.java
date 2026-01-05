package main;

public abstract class Jeu {

    Player player;

    public double getSolde() {
        return player.solde.getSolde();
    }

    public boolean perte(double mise) {
        return player.solde.retirerSolde(mise);
    }

    public void gain(double mise) {
        player.solde.ajouterSolde(mise);
    }

    // void ajout_xp();

}
