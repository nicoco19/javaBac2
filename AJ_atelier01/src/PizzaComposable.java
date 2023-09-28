import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;

public class PizzaComposable extends Pizza{
    //attributs
    private Client createur;
    private LocalDateTime date;

    // constructeur
    public PizzaComposable(Client createur) {

        super("Pizza composable du client " + createur.getNumero(),"pizza de " + createur.getNom()+" "+ createur.getPrenom() );
        this.createur = createur;
        this.date = LocalDateTime.now();

    }

    //getter
    public Client getCreateur() {
        return createur;
    }

    public LocalDateTime getDate() {
        return date;
    }

    @Override
    public String toString() {
        DateTimeFormatter formater = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        return super.toString() + "\nPizza créée le " + formater.format(date);
    }
}
