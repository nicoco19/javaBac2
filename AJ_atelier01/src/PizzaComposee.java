import java.util.ArrayList;

public class PizzaComposee extends Pizza{

    // attribut
    public final static int REMISE = 15;

    //constructeur

    public PizzaComposee(String titre, String description, ArrayList<Ingredient> ingredients) {
        super(titre, description, ingredients);
    }

  // methode
    public boolean ajouter(Ingredient ingredient){

        throw new  UnsupportedOperationException("Les ingrédients d'une pizza composée ne peuvent pas être modifiés");

    }

    public boolean supprimer(Ingredient ingredient){

        throw new  UnsupportedOperationException("Les ingrédients d'une pizza composée ne peuvent pas être modifiés");
    }
    public double calculerPrix(){

        double prix = super.calculerPrix();
        return prix - prix * 0.15;
    }

}
