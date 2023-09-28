package Domaine;

import Util.Util;

import java.time.Duration;
import java.util.*;


public class Plat {

    public enum Difficulte{

        X, XX, XXX, XXXX,XXXXX;


        public String toString() {

            return super.toString().replace("X","*");
        }
    }

    public enum Cout{

        $, $$, $$$, $$$$, $$$$$;


        public String toString() {

            return super.toString().replace("$","€");
        }
    }

    String nom;
    int nbPersonnes;
    Difficulte niveauDeDifficulte;
    Cout cout;
    Duration dureeEnMinutes;
    private List<Instruction> instructionList;
    private Set<IngredientQuantifie> ingredientQuantifieSet;


    public Plat(String nom, int nbPersonnes, Difficulte niveauDeDifficulte, Cout cout) {
        this.nom = nom;
        this.nbPersonnes = nbPersonnes;
        this.niveauDeDifficulte = niveauDeDifficulte;
        this.cout = cout;
        this.dureeEnMinutes = Duration.ofMinutes(0);
        instructionList = new ArrayList<>();
        ingredientQuantifieSet = new HashSet<>();
    }

    public String getNom() {
        return nom;
    }

    public int getNbPersonnes() {
        return nbPersonnes;
    }

    public Difficulte getNiveauDeDifficulte() {
        return niveauDeDifficulte;
    }

    public Cout getCout() {
        return cout;
    }

    public Duration getDureeEnMinutes() {
        return dureeEnMinutes;
    }

    public void insererInstruction(int position, Instruction instruction){

        if (position <= 0 || position > instructionList.size() || instruction == null) throw new IllegalArgumentException();
        dureeEnMinutes = dureeEnMinutes.plusMinutes(instruction.getDureeEnMinutes().toMinutes());
        instructionList.add(position-1,instruction);
    }
    public void ajouterInstruction (Instruction instruction){

        if (instruction == null) throw new IllegalArgumentException();
        dureeEnMinutes = dureeEnMinutes.plusMinutes(instruction.getDureeEnMinutes().toMinutes());
        instructionList.add(instruction);

    }

    public Instruction remplacerInstruction (int position, Instruction instruction){

        if (position <= 0 || position > instructionList.size() || instruction == null) throw new IllegalArgumentException();

        dureeEnMinutes = dureeEnMinutes.minus(instructionList.get(position-1).dureeEnMinutes);
        dureeEnMinutes = dureeEnMinutes.plusMinutes(instruction.getDureeEnMinutes().toMinutes());


        return instructionList.set(position-1,instruction);

    }

    public Instruction supprimerInstruction (int position){

        if (position <= 0 || position > instructionList.size()) throw new IllegalArgumentException();
        dureeEnMinutes = dureeEnMinutes.minusMinutes(instructionList.get(position-1).dureeEnMinutes.toMinutes());

        return instructionList.remove(position-1);
    }

    public Iterator<Instruction> instructions() {

        return Collections.unmodifiableList(instructionList).iterator();
    }

    public boolean ajouterIngredient(Ingredient ingredient, int quantite, Unite unite){

        Util.checkObject(ingredient);
        Util.checkStrictlyPositive(quantite);
        IngredientQuantifie ingredientQuantifie = new IngredientQuantifie(ingredient,quantite,unite);

        if (ingredientQuantifieSet.contains(ingredientQuantifie)) return false;
        ingredientQuantifieSet.add(ingredientQuantifie);
        return true;
    }
    public boolean ajouterIngredient(Ingredient ingredient, int quantite){

        Util.checkObject(ingredient);
        Util.checkStrictlyPositive(quantite);

        IngredientQuantifie ingredientQuantifie = new IngredientQuantifie(ingredient,quantite,Unite.NEANT);
        if (ingredientQuantifieSet.contains(ingredientQuantifie)) return false;
        ingredientQuantifieSet.add(ingredientQuantifie);
        return true;
    }

    public boolean modifierIngredient(Ingredient ingredient, int quantite, Unite unite){

        Util.checkObject(ingredient);
        Util.checkStrictlyPositive(quantite);


        for (IngredientQuantifie i : ingredientQuantifieSet) {

            if (i.getIngredient().equals(ingredient)){

                i.setQuantite(quantite);
                i.setUnite(unite);
                return true;
            }

        }
        return false;
    }

    public boolean supprimerIngredient(Ingredient ingredient){

        Util.checkObject(ingredient);

        for (IngredientQuantifie i : ingredientQuantifieSet) {

            if (i.getIngredient().equals(ingredient)){

                ingredientQuantifieSet.remove(i);
                return true;
            }

        }

        return false;

    }

    public IngredientQuantifie trouverIngredientQuantifie(Ingredient ingredient){

        Util.checkObject(ingredient);

        for (IngredientQuantifie i : ingredientQuantifieSet) {

            if (i.getIngredient().equals(ingredient)){

                return i;

            }

        }

        return null;

    }

    @Override
    public String toString() {
        String hms = String.format("%d h %02d m", dureeEnMinutes.toHours(), dureeEnMinutes.toMinutes()%60);
        String res = this.nom + "\n\n";
        res += "Pour " + this.nbPersonnes + " personnes\n";
        res += "Difficulté : " + this.niveauDeDifficulte + "\n";
        res += "Coût : " + this.cout + "\n";
        res += "Durée : " + hms + " \n\n";
        res += "Ingrédients :\n";
        for (IngredientQuantifie ing : this.ingredientQuantifieSet) {
            res += ing + "\n";
        }
        int i = 1;
        res += "\n";
        for (Instruction instruction : this.instructionList) {
            res += i++ + ". " + instruction + "\n";
        }
        return res;
    }


}

