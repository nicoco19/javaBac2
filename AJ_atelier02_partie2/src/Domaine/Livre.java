package Domaine;

import java.util.Comparator;
import java.util.HashMap;
import java.util.SortedSet;
import java.util.TreeSet;

public class Livre {

   private HashMap<Plat.Type, SortedSet<Plat>> plats;


    public Livre() {

        plats = new HashMap<Plat.Type, SortedSet<Plat>>();
    }

    /**
     * Ajoute un plat dans le livre, s'il n'existe pas déjà dedans.
     * Il faut ajouter correctement le plat en fonction de son type.
     * @param plat le plat à ajouter
     * @return true si le plat a été ajouté, false sinon.
     */
    public boolean ajouterPlat(Plat plat) {

        if (!plats.containsKey(plat.getType())){

            TreeSet<Plat> treeSet = new TreeSet<>(new Comparator<Plat>() {
                @Override
                public int compare(Plat o1, Plat o2) {

                    int compareDifficulte = o1.getNiveauDeDifficulte().compareTo(o2.getNiveauDeDifficulte());

                    if ( compareDifficulte == 0 ) {

                        return o1.getNom().compareTo(o2.getNom());

                    }
                    return compareDifficulte;
                }
            });
            plats.put(plat.getType(),treeSet);

        }

        return plats.get(plat.getType()).add(plat);

    }

    /**
     * Supprime un plat du livre, s'il est dedans.
     * Si le plat supprimé est le dernier de ce type de plat, il faut supprimer ce type de plat de la Map.
     * @param plat le plat à supprimer * @return true si le plat a été supprimé, false sinon.
     */
    public boolean supprimerPlat (Plat plat){

        return false;
    }
}
