package domaine;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoniteurImplTest {

    private MoniteurImpl moniteur1;
    private SportStub sportStubTrue;
    private SportStub sportStubFalse;
    private StageStub stage1;

    @org.junit.jupiter.api.BeforeEach
    void setUp(){

    moniteur1 = new MoniteurImpl("Nicolas");
    sportStubTrue = new SportStub(true);
    sportStubFalse = new SportStub(false);
    stage1 = new StageStub(8,sportStubTrue,moniteur1);


    }
    private void amenerALEtat(int etat, Moniteur moniteur){

        for (int i = 1; i <= etat; i++) {

             moniteur1.ajouterStage(new StageStub(i,sportStubTrue,null));

        }
    }

    @org.junit.jupiter.api.Test
    void testTc1() {
        assertAll(() -> assertTrue(moniteur1.ajouterStage(stage1)),
                  () -> assertTrue(moniteur1.contientStage(stage1)),
                  () -> assertEquals(1,moniteur1.nombreDeStages())
        );
    }

    @org.junit.jupiter.api.Test
    void testTc2() {
        amenerALEtat(1,moniteur1);
        assertAll(() -> assertTrue(moniteur1.ajouterStage(stage1)),
                  () -> assertTrue(moniteur1.contientStage(stage1)),
                  () -> assertEquals(2,moniteur1.nombreDeStages())
        );
    }

    @org.junit.jupiter.api.Test
    void testTc3() {
        amenerALEtat(2,moniteur1);
        assertAll(() -> assertTrue(moniteur1.ajouterStage(stage1)),
                  () -> assertTrue(moniteur1.contientStage(stage1)),
                  () -> assertEquals(3,moniteur1.nombreDeStages())
        );
    }
    @org.junit.jupiter.api.Test
    void testTc4() {
        amenerALEtat(3,moniteur1);
        assertAll(() -> assertTrue(moniteur1.ajouterStage(stage1)),
                  () -> assertTrue(moniteur1.contientStage(stage1)),
                  () -> assertEquals(4,moniteur1.nombreDeStages())
        );
    }
    @org.junit.jupiter.api.Test
    void testTc5() {
        amenerALEtat(3,moniteur1);
        moniteur1.ajouterStage(stage1);
        assertAll(() -> assertTrue(moniteur1.supprimerStage(stage1)),
                  () -> assertFalse(moniteur1.contientStage(stage1)),
                  () -> assertEquals(3,moniteur1.nombreDeStages())
        );
    }

    @org.junit.jupiter.api.Test
    void testTc6() {
        amenerALEtat(2,moniteur1);
        moniteur1.ajouterStage(stage1);
        assertAll(() -> assertTrue(moniteur1.supprimerStage(stage1)),
                  () -> assertFalse(moniteur1.contientStage(stage1)),
                  () -> assertEquals(2,moniteur1.nombreDeStages())
        );
    }

    @org.junit.jupiter.api.Test
    void testTc7() {
        amenerALEtat(1,moniteur1);
        moniteur1.ajouterStage(stage1);
        assertAll(() -> assertTrue(moniteur1.supprimerStage(stage1)),
                  () -> assertFalse(moniteur1.contientStage(stage1)),
                  () -> assertEquals(1,moniteur1.nombreDeStages())
        );
    }
    @org.junit.jupiter.api.Test
    void testTc8() {
        moniteur1.ajouterStage(stage1);
        assertAll(() -> assertTrue(moniteur1.supprimerStage(stage1)),
                  () -> assertFalse(moniteur1.contientStage(stage1)),
                  () -> assertEquals(0,moniteur1.nombreDeStages())
        );
    }
    @org.junit.jupiter.api.Test
    void testTc9() {
        amenerALEtat(3,moniteur1);
        moniteur1.ajouterStage(stage1);
        assertAll(() -> assertFalse(moniteur1.ajouterStage(stage1)),
                  () -> assertEquals(4,moniteur1.nombreDeStages())
        );
    }
    @org.junit.jupiter.api.Test
    void testTc10() {
        amenerALEtat(4,moniteur1);
        Stage stageMemeSemaine = new StageStub(1, sportStubTrue, null);
        assertAll(() -> assertFalse(moniteur1.supprimerStage(stageMemeSemaine)),
                  () -> assertFalse(moniteur1.contientStage(stage1)),
                  () -> assertEquals(4,moniteur1.nombreDeStages())
        );
    }
    @org.junit.jupiter.api.Test
    void testTc11() {
        amenerALEtat(4,moniteur1);
        assertAll(() -> assertFalse(moniteur1.supprimerStage(stage1)),
                  () -> assertEquals(4,moniteur1.nombreDeStages())
        );
    }
    @org.junit.jupiter.api.Test
    void testTc12() {
        amenerALEtat(4,moniteur1);
        Moniteur moniteur = new MoniteurImpl("moniteur");
        Stage stage = new StageStub(8,sportStubTrue,moniteur);
        assertAll(() -> assertFalse(moniteur1.ajouterStage(stage)),
                  () -> assertFalse(moniteur1.contientStage(stage)),
                  () -> assertEquals(4,moniteur1.nombreDeStages())
        );
    }
    @Test
    void testTc13(){
        amenerALEtat(4,moniteur1);
        Stage stage = new StageStub(8,sportStubTrue,moniteur1);
        assertAll(() -> assertTrue(moniteur1.ajouterStage(stage)),
                  () -> assertEquals(5,moniteur1.nombreDeStages())
        );
    }
    @Test
    void testTc14(){
        amenerALEtat(4,moniteur1);
        Stage stage = new StageStub(8,sportStubFalse,null);
        assertAll(() -> assertFalse(moniteur1.ajouterStage(stage)),
                  () -> assertFalse(moniteur1.contientStage(stage)),
                  () -> assertEquals(4,moniteur1.nombreDeStages())
        );
    }
}
