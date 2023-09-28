public class LigneDeCommande {

    //attribut
    private Pizza pizza;
    private int quantite;
    private double prixUnitaire;

    //constructeur

    public LigneDeCommande(Pizza pizza, int quantite) {
        this.pizza = pizza;
        this.quantite = quantite;
        prixUnitaire = pizza.calculerPrix();
    }

    //getter

    public Pizza getPizza() {
        return pizza;
    }

    public int getQuantite() {
        return quantite;
    }

    public double getPrixUnitaire() {
        return prixUnitaire;
    }

    // setter

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public double calculerPrixTotal(){

        return prixUnitaire * quantite;
    }

    public String toString() {
        return  quantite + " " + pizza.getTitre() + "  à " + prixUnitaire ;
    }
}
