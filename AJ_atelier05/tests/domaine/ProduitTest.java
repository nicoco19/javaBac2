package domaine;

import exceptions.DateDejaPresenteException;
import exceptions.PrixNonDisponibleException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ProduitTest {

    private Prix prixAucune;
    private Prix prixPub;
    private Prix prixSolde;
    private  Produit produit1;
    private Produit produit2;

    @BeforeEach
    @DisplayName("verification des arguments")
    void setUp() {
        // definir prix
        prixAucune = new Prix();
        prixPub = new Prix(TypePromo.PUB,10);
        prixSolde = new Prix(TypePromo.SOLDE,20);
        prixAucune.definirPrix(1,20);
        prixAucune.definirPrix(10,10);
        prixPub.definirPrix(3,15);

        // definir produit
        produit1 = new Produit("produit1","Nicolas","rayon2");
        produit2 = new Produit("produit2","Nicolas","rayon1");
        produit1.ajouterPrix(LocalDate.of(2003,10,18),prixAucune);
        produit1.ajouterPrix(LocalDate.MAX,prixPub);
        produit1.ajouterPrix(LocalDate.of(2003,9,17),prixSolde);
       // produit2.ajouterPrix(LocalDate.of(2003,1,26),prixSolde);

    }

    @Test
    @DisplayName("verification du constructeur si il y a des parametres invalides")
    void constructeur() {
        assertThrows(IllegalArgumentException.class,() -> new Produit("","",""));
        assertThrows(IllegalArgumentException.class,() -> new Produit(null,null,null));
    }

    @Test
    @DisplayName("verification des getters sui produit1")
    void getterProduit1() {

        assertEquals("produit1",produit1.getNom());
        assertEquals("Nicolas",produit1.getMarque());
        assertEquals("rayon2",produit1.getRayon());
        //assertEquals(prixAucune,produit1.getPrix(LocalDate.MIN));
        assertEquals(prixPub,produit1.getPrix(LocalDate.MAX));
        assertEquals(prixSolde,produit1.getPrix(LocalDate.of(2003,9,17)));

    }

    @Test
    @DisplayName("verification des getters du produit2")
    void getterProduit2() {

        assertEquals("produit2",produit2.getNom());
        assertEquals("Nicolas",produit2.getMarque());
        assertEquals("rayon1",produit2.getRayon());
        //assertEquals(prixSolde,produit2.getPrix(LocalDate.of(2003,1,26)));

    }

    @Test
    @DisplayName("verification si on ajoute un parametre des arguments null il revoit une IllegalArgumentException")
    void ajouterPrixVerifNullArgument() {

        assertThrows(IllegalArgumentException.class,() -> produit1.ajouterPrix(null,null));
        assertThrows(IllegalArgumentException.class,() -> produit2.ajouterPrix(null,null));
    }

    @Test
    void ajouterPrixVerifDate() {

        //assertThrows(DateDejaPresenteException.class,() -> produit2.ajouterPrix(LocalDate.of(2003,1,26),prixSolde));
        assertThrows(DateDejaPresenteException.class,() -> produit1.ajouterPrix(LocalDate.of(2003,9,17),prixSolde));
    }


    @Test
    void ajouterPrixVerifGetPrix() {
        LocalDate date1 = LocalDate.of(11,1,1);
        produit1.ajouterPrix(date1, prixAucune);
        assertEquals(prixAucune,produit1.getPrix(date1));


        LocalDate date2 = LocalDate.of(11,1,1);
        produit2.ajouterPrix(date2, prixAucune);
        assertEquals(prixAucune,produit2.getPrix(date2));
    }
    @Test
    @DisplayName("verifie que si on appel un produit qui n'a pas de date lance une PrixNonDisponibleException")
    void ajouterPrixVerifDateAnterieur() {
        LocalDate dateNot = LocalDate.of(2000,2,3);
        assertThrows(PrixNonDisponibleException.class,() -> produit2.getPrix(dateNot));
        assertThrows(PrixNonDisponibleException.class,() -> produit1.getPrix(dateNot));
    }

    @Test
    @DisplayName("verifie que produit qui n'a pas de prix lance une PrixNonDisponibleException")
    void ajouterPrixVerifNotPrix() {

        LocalDate dateNot = LocalDate.of(2000,2,3);
        assertThrows(PrixNonDisponibleException.class,() -> produit2.getPrix(dateNot));
    }

    @Test
    @DisplayName("Test equals pour deux instances")
    void testEqualDeuxInstances(){
        Produit produit3 =new Produit("produit1","Nicolas","rayon2");
        assertEquals(produit1,produit3);
    }

    @Test
    @DisplayName("Test equals pour deux instances avec un nom différent")
    void testEqualDeuxInstancesNomDifferent(){
        Produit produit3 = new Produit("PC portable","Legion","Appareil");
        assertNotEquals(produit1,produit3);
    }

    @Test
    @DisplayName("Test equals pour deux instances avec une marque différente")
    void testEqualDeuxInstancesMarquesDifferent(){
        Produit produit3 = new Produit("PC Gamer","PH","Appareil");
        assertNotEquals(produit1,produit3);
    }

    @Test
    @DisplayName("Test equals pour deux instances avec un rayon différent")
    void testEqualDeuxInstancesRayonsDifferent(){
        Produit produit3 = new Produit("PC Gamer","PH","Electronique");
        assertNotEquals(produit1,produit3);
    }

    @Test
    @DisplayName("Test hashcode pour deux instances")
    void testHashcodeDeuxInstance(){
        Produit produit3 = new Produit("produit1","Nicolas","rayon2");
        assertEquals(produit1.hashCode(),produit3.hashCode());
    }

}