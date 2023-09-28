import java.util.Objects;

public class Client {

    //attribut
    private static int numeroSuivant = 1;
    private int numero = 0;
    private String nom;
    private String prenom;
    private String telephone;
    private Commande commandeEnCours;

    //constructeur

    public Client(String nom, String prenom, String telephone) {
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.numero = numeroSuivant;
        numeroSuivant++;
    }

    //getter
    public int getNumero() {
        return numero;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getTelephone() {
        return telephone;
    }

    public Commande getCommandeEnCours() {
        return commandeEnCours;
    }

    // methode
    public boolean enregistrer(Commande commande){

        if (commandeEnCours != null) return false;

        if (!this.equals(commande.getClient())) return false;

        commandeEnCours = commande;
        return true;

    }

    public boolean cloturerCommandeEnCours(){

        if(commandeEnCours == null) return false;

        commandeEnCours = null;
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return numero == client.numero;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numero);
    }

    @Override
    public String toString() {
        return "client n° " + numero + " (" + prenom  + " " + nom + ", telephone : " + telephone +")";
    }
}
