package main;

import domaine.Employe;
import domaine.Trader;
import domaine.Transaction;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class ExercicesDeBase {

    /**
     * La liste de base de toutes les transactions.
     */
    private List<Transaction> transactions;

    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
        System.out.println("Les transactions " + transactions);
        ExercicesDeBase main = new ExercicesDeBase(transactions);
        main.run();
    }



    /**
     * Crée un objet comprenant toutes les transactions afin de faciliter leur usage pour chaque point de l'énoncé
     *
     * @param transactions la liste des transactions
     */
    public ExercicesDeBase(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    /**
     * Exécute chaque point de l'énoncé
     */
    public void run() {
        this.predicats1();
        this.predicats2();
        this.predicats3();
        this.map1();
        this.map2();
        this.map3();
        this.sort1();
        this.sort2();
        this.reduce1();
        this.reduce2();
    }

    private void predicats1() {
        System.out.println("predicats1");
        Stream<Transaction> s = transactions
                .stream()
                .filter(t -> t.getYear() == 2011); // TODO filtrer
        System.out.println("sout du Stream brut " + s);
        s.forEach(System.out::println);
    }

    private void predicats2() {

        System.out.println("predicats2");
        var s = transactions
                .stream().filter(e -> e.getValue() > 600); // TODO filtrer

        s.forEach(System.out::println);
    }


    private void predicats3() {

        System.out.println("predicats3");
        var s = transactions
                .stream()
                .filter(e -> e.getTrader().getName() == "Raoul"); // TODO filtrer
        s.forEach(System.out::println);
    }

    private void map1() {
        System.out.println("---map1---");

        Stream<String> listeVilles = transactions
                .stream()
                .map(e -> e.getTrader().getCity())
                .distinct();
        listeVilles.forEach(System.out::println);

    }

    private void map2() {
        System.out.println("---map2---");
        Stream<Trader> listeCourtier = transactions
                .stream()
                .filter(e -> e.getTrader().getCity().equals("Cambridge"))
                .map(e -> e.getTrader())
                .distinct();
        listeCourtier.forEach(System.out::println);

    }

    private void map3() {

        System.out.println("---map3---");
        Stream<String> listetraders = transactions
                .stream()
                .map(e -> e.getTrader().getName())
                .distinct()
                .collect(Collectors.joining(",")).lines();
        listetraders.forEach(System.out::println);

    }
    private void sort1() {
        System.out.println("sort1");
        var transcTriees = transactions
                .stream()
                .sorted(Comparator.comparingInt(Transaction::getValue)); // TODO trier
        transcTriees.forEach(System.out::println);
    }

    private void sort2() {
        System.out.println("sort2");
        var nomsTries = transactions
                .stream()
                .sorted(Comparator.comparing(e -> e.getTrader().getName()))
                .map(e -> e.getTrader().getName())
                .distinct()
                .collect(Collectors.joining(",")); // TODO trier
		System.out.println(nomsTries);
    }
    private void reduce1() {
        System.out.println("---reduce1---");

        var valeurMax = transactions
                .stream()
                .map(a -> a.getValue())
                .reduce( 0 ,Integer::max );
        System.out.println(valeurMax);
    }

    private void reduce2() {
        System.out.println("---reduce2---");

        var transMin = transactions
                .stream()
                .reduce((a,b) -> {
                    if(a.getValue() > b.getValue()) {
                        return b;
                    }else {
                        return a;
                    }
                })
                .get();

        System.out.println(transMin);

    }

}