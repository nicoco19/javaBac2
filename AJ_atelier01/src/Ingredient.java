import java.util.Objects;

public class Ingredient {
    // attribut
    private String nom;
    private double prix;

    //constructeur
    public Ingredient(String nom, double prix) {
        this.nom = nom;
        this.prix = prix;
    }

    // getter and setter
    public String getNom() {
        return nom;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient that = (Ingredient) o;
        return Objects.equals(nom, that.nom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom);
    }
}
