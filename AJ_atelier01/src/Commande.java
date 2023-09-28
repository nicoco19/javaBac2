import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Iterator;

public class Commande implements Iterable<LigneDeCommande>{
    // attributs
    private static int numeroSuivant = 1;
    private int numero = 0;
    private Client client;
    private LocalDateTime date;
    private ArrayList<LigneDeCommande> ligneDeCommandes;


    // constructeur
    public Commande(Client client) {
        this.client = client;
        if (client.getCommandeEnCours() != null){
            throw new IllegalArgumentException("impossible de créer une commande pour un client ayant encore une commande en cours");
        }else {
           client.enregistrer(this);
        }
        ligneDeCommandes = new ArrayList<LigneDeCommande>();
        this.numero = numeroSuivant;
        numeroSuivant++;
       this.date = LocalDateTime.now();
    }

    //getter
    public int getNumero() {
        return numero;
    }

    public Client getClient() {
        return client;
    }

    public LocalDateTime getDate() {
        return date;
    }

    //methode
    public boolean ajouter(Pizza pizza,int quantite){

        if (client.getCommandeEnCours() != this) return false;

        for (LigneDeCommande ligneDeCommande : ligneDeCommandes) {

           if ( ligneDeCommande.getPizza().equals(pizza)){

               ligneDeCommande.setQuantite(quantite);
               return true;
           }
        }

        LigneDeCommande nouvelleLigneCommande = new LigneDeCommande(pizza,quantite);
        ligneDeCommandes.add(nouvelleLigneCommande);
        return true;
    }

    public boolean ajouter(Pizza pizza){

        return ajouter(pizza,1);
    }

    public double calculerMontantTotal(){

        int prixt = 0;
        for (LigneDeCommande ligneDeCommande : ligneDeCommandes) {

            prixt += ligneDeCommande.calculerPrixTotal();
        }
        return prixt;
    }

    public String detailler(){

        String det = "";

        for (LigneDeCommande ligneDeCommande : ligneDeCommandes) {

            det += ligneDeCommande.toString()+"\n";
        }

        return det;
    }

    @Override
    public Iterator<LigneDeCommande> iterator() {
        return this.ligneDeCommandes.iterator();
    }

    public String toString() {
        DateTimeFormatter formater = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        String encours = "";
        if (client.getCommandeEnCours() == this)
            encours = " (en cours)";
        return "Commande n° " + numero + encours + " du " + client + "\ndate : " + formater.format(date);
    }
}

