package domaine;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mockito;
class MoniteurImpl2Test {

    private MoniteurImpl moniteur;
    private Sport sport;
    private Stage stage;

    @org.junit.jupiter.api.BeforeEach
    void setUp(){

        moniteur = new MoniteurImpl("Nicolas");

        sport = Mockito.mock(Sport.class);
        Mockito.when(sport.contientMoniteur(moniteur)).thenReturn(true);

        stage = Mockito.mock(Stage.class);
        Mockito.when(stage.getNumeroDeSemaine()).thenReturn(8);
        Mockito.when(stage.getMoniteur()).thenReturn(null);
        Mockito.when(stage.getSport()).thenReturn(sport);

    }

    private void amenerALEtat(int etat, Moniteur moniteur){

        for (int i = 1; i <= etat; i++) {

            StageImpl stage1 = Mockito.mock(StageImpl.class);
            Mockito.when(stage1.getNumeroDeSemaine()).thenReturn(i);
            Mockito.when(stage1.getSport()).thenReturn(sport);
            Mockito.when(stage1.getMoniteur()).thenReturn(null);
            moniteur.ajouterStage(stage1);

        }
    }

    @Test
    void testTC1(){
        assertAll(() -> assertTrue(moniteur.ajouterStage(stage)),
                  () -> assertTrue(moniteur.contientStage(stage)),
                  () -> assertEquals(1,moniteur.nombreDeStages()),
                  () -> Mockito.verify(stage).enregistrerMoniteur(moniteur)
        );
    }
    @Test
    void testTC2(){
        amenerALEtat(1,moniteur);
        assertAll(() -> assertTrue(moniteur.ajouterStage(stage)),
                  () -> assertTrue(moniteur.contientStage(stage)),
                  () -> assertEquals(2,moniteur.nombreDeStages()),
                  () -> Mockito.verify(stage).enregistrerMoniteur(moniteur)
      );
    }

    @Test
    void testTC3(){
        amenerALEtat(2,moniteur);
        assertAll(() -> assertTrue(moniteur.ajouterStage(stage)),
                  () -> assertTrue(moniteur.contientStage(stage)),
                  () -> assertEquals(3,moniteur.nombreDeStages()),
                  () -> Mockito.verify(stage).enregistrerMoniteur(moniteur)
        );
    }

    @Test
    void testTC4(){
        amenerALEtat(3,moniteur);
        assertAll(() -> assertTrue(moniteur.ajouterStage(stage)),
                  () -> assertTrue(moniteur.contientStage(stage)),
                  () -> assertEquals(4,moniteur.nombreDeStages()),
                  () -> Mockito.verify(stage).enregistrerMoniteur(moniteur)
        );
    }

    @Test
    void testTC5(){
        amenerALEtat(3,moniteur);
        moniteur.ajouterStage(stage);
        assertAll(() -> assertFalse(moniteur.ajouterStage(stage)),
                () -> assertEquals(4,moniteur.nombreDeStages()),
                () -> Mockito.verify(stage).enregistrerMoniteur(moniteur)
        );
    }

    @Test
    void testTC6(){
        amenerALEtat(4,moniteur);
        Stage stageBad = Mockito.mock(Stage.class);
        Mockito.when(stageBad.getSport()).thenReturn(sport);
        Mockito.when(stageBad.getMoniteur()).thenReturn(null);
        Mockito.when(stageBad.getNumeroDeSemaine()).thenReturn(1);

        assertAll(() -> assertFalse(moniteur.contientStage(stageBad)),
                  () -> assertEquals(4,moniteur.nombreDeStages()),
                  () -> Mockito.verify(stageBad,Mockito.never()).enregistrerMoniteur(moniteur)

        );
    }

    @Test
    void testTC7(){
        amenerALEtat(4,moniteur);
        Moniteur moniteurBad = new MoniteurImpl("badMan");
        Stage badStage = Mockito.mock(Stage.class);
        Mockito.when(badStage.getSport()).thenReturn(sport);
        Mockito.when(badStage.getNumeroDeSemaine()).thenReturn(8);
        Mockito.when(badStage.getMoniteur()).thenReturn(moniteurBad);

        assertAll(() -> assertFalse(moniteurBad.ajouterStage(badStage)),
                  () -> assertFalse(moniteurBad.contientStage(badStage)),
                  () -> assertEquals(4,moniteur.nombreDeStages()),
                  () -> Mockito.verify(badStage,Mockito.never()).enregistrerMoniteur(moniteur)
        );
    }

    @Test
    void testTC8(){
        amenerALEtat(4,moniteur);
        Stage stageBonMoniteur = Mockito.mock(Stage.class);
        Mockito.when(stageBonMoniteur.getSport()).thenReturn(sport);
        Mockito.when(stageBonMoniteur.getMoniteur()).thenReturn(moniteur);
        Mockito.when(stageBonMoniteur.getNumeroDeSemaine()).thenReturn(8);

        assertAll(() -> assertTrue(moniteur.ajouterStage(stageBonMoniteur)),
                  () -> assertTrue(moniteur.contientStage(stageBonMoniteur)),
                  () -> assertEquals(5, moniteur.nombreDeStages()),
                  () -> Mockito.verify(stageBonMoniteur, Mockito.never()).enregistrerMoniteur(moniteur)
        );
    }

    @Test
    void testTC9(){
        amenerALEtat(4, moniteur);
        Sport sportHorsCompetence = Mockito.mock(Sport.class);
        Mockito.when(sportHorsCompetence.contientMoniteur(moniteur)).thenReturn(false);
        Stage stageMauvaisSport = Mockito.mock(Stage.class);
        Mockito.when(stageMauvaisSport.getSport()).thenReturn(sportHorsCompetence);
        Mockito.when(stageMauvaisSport.getMoniteur()).thenReturn(null);
        Mockito.when(stageMauvaisSport.getNumeroDeSemaine()).thenReturn(8);
        assertAll(() -> assertFalse(moniteur.ajouterStage(stageMauvaisSport)),
                  () -> assertFalse(moniteur.contientStage(stageMauvaisSport)),
                  () -> assertEquals(4, moniteur.nombreDeStages()),
                  () -> Mockito.verify(stageMauvaisSport, Mockito.never()).enregistrerMoniteur(moniteur)
        );
    }
}