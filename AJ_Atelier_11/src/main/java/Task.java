import java.util.Objects;

public class Task {
    private static String etat;
    public String titre, description;

    public Task(String titre, String description) {
        this.titre = titre;
        this.description = description;
        this.etat = "ENCOURS";
    }

    public String getEtat() {
        return etat;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEtat(String etat) {
        Task.etat = etat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(titre, task.titre) && Objects.equals(description, task.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titre, description);
    }
}
