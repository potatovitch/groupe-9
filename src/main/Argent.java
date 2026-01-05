package main;

public class Argent {

    protected double solde = 1000;

    public double getSolde() {
        return solde;
    }

    public void setSolde(double montant) {
        this.solde = montant;
    }

    public void ajouterSolde(double montant) {
        this.solde += montant;
    }

    public boolean retirerSolde(double montant) {
        if (this.solde >= montant) {
            this.solde -= montant;
            return true;
        }
        System.out.println("Solde insuffisant...");
        return false;
    }

    public void AppliquerBonus(double bonus) {
        this.ajouterSolde(bonus);
    }

    public void AppliquerMalus(double malus) {
        this.retirerSolde(malus);
    }

    @Override
    public String toString() {
        return this.solde+"€";
    }
}