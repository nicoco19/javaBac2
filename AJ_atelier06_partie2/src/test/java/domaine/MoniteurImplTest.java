package domaine;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mockito;
class MoniteurImplTest {

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

    }
}