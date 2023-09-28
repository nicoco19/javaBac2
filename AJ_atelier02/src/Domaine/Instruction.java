package Domaine;

import java.time.Duration;

public class Instruction {

    String desription;
    Duration dureeEnMinutes;


    public Instruction(String desription, int dureeEnMinutes) {

        this.desription = desription;
        this.dureeEnMinutes = Duration.ofMinutes(dureeEnMinutes);

    }

    public String getDesription() {
        return desription;
    }

    public void setDesription(String desription) {
        this.desription = desription;
    }

    public Duration getDureeEnMinutes() {
        return dureeEnMinutes;
    }

    public void setDureeEnMinutes(Duration dureeEnMinutes) {
        this.dureeEnMinutes = dureeEnMinutes;
    }

    @Override
    public String toString() {

        return " (" + String.format("%02d:%02d", (dureeEnMinutes.toMinutes() % 3600) / 60, (dureeEnMinutes.toMinutes()) % 60)+") " + desription;
    }
}
