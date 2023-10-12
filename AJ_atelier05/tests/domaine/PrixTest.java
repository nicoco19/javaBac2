package domaine;

import exceptions.QuantiteNonAutoriseeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PrixTest {

    private Prix prixAucune;
    private Prix prixPub;
    private Prix prixSolde;


    @BeforeEach
    @DisplayName("initialisation des arguments")
    void setUp() {
        prixAucune = new Prix();
        prixPub = new Prix(TypePromo.PUB,10);
        prixSolde = new Prix(TypePromo.SOLDE,20);
        prixAucune.definirPrix(1,20);
        prixAucune.definirPrix(10,10);
        prixPub.definirPrix(3,15);
    }

    @Test
    @DisplayName("verification du constructeur")
    void testConstructeur(){

        assertThrows(IllegalArgumentException.class,() -> new Prix(null,1));
        assertThrows(IllegalArgumentException.class,() -> new Prix(TypePromo.SOLDE,0));
        assertThrows(IllegalArgumentException.class,() -> new Prix(TypePromo.SOLDE,-1));
    }


    @Test
    @DisplayName("verification du getter valeurPromo")
    void testGetterValeurPromo() {
        assertEquals(0,prixAucune.getValeurPromo());
        assertEquals(10,prixPub.getValeurPromo());
        assertEquals(20,prixSolde.getValeurPromo());
    }

    @Test
    @DisplayName("verification du getter typePromo")
    void testGetterTypePromo() {
        assertNull(prixAucune.getTypePromo());
        assertEquals(TypePromo.PUB,prixPub.getTypePromo());
    }

    @Test
    @DisplayName("verification definirPrix avec quantiteMin")
    void definirPrixQuantiteMin() {

        assertThrows(IllegalArgumentException.class,() -> new Prix().definirPrix(-1,10));
        assertThrows(IllegalArgumentException.class,() -> new Prix().definirPrix(0,10));

    }

    @Test
    @DisplayName("verification que les parametres changent bien")
    void verificationChangementParam() {

        prixAucune.definirPrix(10,6);
        assertEquals(6,prixAucune.getPrix(10));
    }

    @Test
    @DisplayName("verification definirPrix avec prixUnitaire")
    void definirPrixUnitaire() {

        assertThrows(IllegalArgumentException.class,() -> new Prix().definirPrix(1,-1));
        assertThrows(IllegalArgumentException.class,() -> new Prix().definirPrix(1,0));
    }

    @Test
    @DisplayName("verification des arguments de get prix si null ou negatif")
    void verificationArgumentGetPrix() {

        assertThrows(IllegalArgumentException.class,() -> new Prix().getPrix(-1));
        assertThrows(IllegalArgumentException.class,() -> new Prix().getPrix(0));

    }

    @Test
    @DisplayName("vérifie que la methode get renvoie les bonnes informations")
    void verificationPrixRenvoyeGetPrix() {

        assertEquals(20,prixAucune.getPrix(1));
        assertEquals(20,prixAucune.getPrix(5));
        assertEquals(20,prixAucune.getPrix(9));
        assertEquals(10,prixAucune.getPrix(10));
        assertEquals(10,prixAucune.getPrix(15));
        assertEquals(10,prixAucune.getPrix(20));
        assertEquals(10,prixAucune.getPrix(25));
    }

    @Test
    @DisplayName("verification que QuantiteNonAutoriseeException se lance bien si on demande un prix pour une unite de 2 pour prixPub")
    void verificationArgumentPrixPubeGetPrix() {

        assertThrows(QuantiteNonAutoriseeException.class,() -> prixPub.getPrix(2));
    }

    @Test
    @DisplayName("verification que QuantiteNonAutoriseeException se lance bien si on demande un prix pour une unite de 1 pour prixSolde")
    void verificationArgumentPrixSoldeGetPrix() {

        assertThrows(QuantiteNonAutoriseeException.class,() -> prixSolde.getPrix(1));
    }

}