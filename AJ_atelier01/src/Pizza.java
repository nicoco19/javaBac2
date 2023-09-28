import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

public abstract class Pizza  implements Iterable<Ingredient>{
    // attribut
   public final static double PRIX_BASE = 5.0;
    private String titre;
    private String description;
    private ArrayList<Ingredient> ingredients;


    // constructeur 1
    public Pizza(String titre, String description) {
        this.titre = titre;
        this.description = description;
        this.ingredients = new ArrayList<>();
    }

    // constructeur 2
    public Pizza(String titre, String description, ArrayList<Ingredient> ingredients) {

        this(titre,description);

        this.ingredients = new ArrayList<>();

        for (Ingredient i : ingredients) {

            if (this.ingredients.contains(i)) throw new IllegalArgumentException("Il ne peut pas y avoir deux fois le même ingrédient dans une pizza");

            this.ingredients.add(i);
        }
    }

    public String getTitre() {
        return titre;
    }

    public String getDescription() {
        return description;
    }

    public boolean ajouter(Ingredient ingredient){

        if (this.ingredients.contains(ingredient)) return false;

        this.ingredients.add(ingredient);
        return true;
    }

    public boolean supprimer(Ingredient ingredient){

        if (!this.ingredients.contains(ingredient)) return false;
        this.ingredients.remove(ingredient);
        return true;

    }

    public double calculerPrix(){

        double prixT = PRIX_BASE;

        for (Ingredient ingredient : ingredients) {

            prixT += ingredient.getPrix();
        }
        return prixT;
    }

    @Override
    public Iterator<Ingredient> iterator() {

        return this.ingredients.iterator();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pizza pizza = (Pizza) o;
        return Objects.equals(titre, pizza.titre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titre);
    }

    @Override
    public String toString() {
        String infos = titre + "\n" + description + "\nIngrédients : ";
        for (Ingredient ingredient : ingredients){
            infos +="\n" + ingredient.getNom();
        }
        infos +="\nprix : " + calculerPrix() + " euros";
        return infos;
    }
}
