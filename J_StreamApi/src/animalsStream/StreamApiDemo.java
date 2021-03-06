package animalsStream;

import fileListingsSets.FileData;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class StreamApiDemo {
    public static void main(String[] args) {
        Animal[] allAnimals = new Animal[] {
            new Animal("Löwe", "Panthera leo", 250, false),
            new Animal("Waschbär", "Procyon", 12, false),
            new Animal("Bison", "Bison bison", 950, true),
            new Animal("Elefant", "Loxodonta", 4000, true),
            new Animal("Eisbär", "Ursus maritimus", 650, false),
            new Animal("Emu", "Dromaius novaehollandiae", 37, false)


        };

        // Stream für ein Array holen
        Stream<Animal> myStream = Stream.of(allAnimals)
            // filter; nur die Tiere, dei dem Predicate entsprechen
            .filter(a -> !a.isHerbivore())
            // in natürlicher Sortierreihenfolge
            .sorted();
        // bisher ist noch nichts verarbeitet worden -< Verarbeitung mit terminal
        System.out.println("Fleischfresser sortiert");
        myStream.forEach(a -> System.out.printf("\t%s \n", a));

        try {
            System.out.println("2. Ausführung");
            myStream.forEach(System.out::println);
        } catch (IllegalStateException e) {
            System.out.println("Fehler bei zweiter Ausführung");
        }

        //            .filter(Predicate.not(a -> a.isHerbivore()))

        Stream.of(allAnimals)
            // statt a -> !a.
            .filter(Predicate.not(Animal::isHerbivore))
            .sorted(Comparator.comparing(Animal::getWeight))
            .forEach(a -> System.out.printf("\t%s", a));

        // Die Namen aller Pflanzenfresser
        System.out.println("Namen aller Pflanzenfresser");
        // Die namen aller pflanzenfresser
        Stream.of(allAnimals).filter(Animal::isHerbivore)
            // ab hier ist es Strean
            .map(Animal::getName)
            .sorted()
            // Consumer ist jetzt Consumer<String>
            .forEach(s -> System.out.printf("\t%s\n", s));

        // den Fleischfresser mit dem kleinsten Gewicht
        Optional<Animal> min = Arrays.stream(allAnimals)
            .filter(Predicate.not(Animal::isHerbivore))
            .min(Comparator.comparing(Animal::getWeight));

        min.ifPresent(a -> System.out.println("Das Tier mit dem kleinsten Gewich ist " + a.getName()));

        int weight = 10;
        Animal max = Arrays.stream(allAnimals)
            .filter(a -> a.getWeight() < weight)
            .max(Comparator.comparing(Animal::getWeight))
            // wenn kein Objekt im ERgebnis ist, soll null geliefert werden
            .orElse(null);
        if (max != null) {
            System.out.println("Schwerste Tier unter " + weight + " x kg ist " + max);
        } else {
            System.out.println("keine Tiere im Stream");
        }
    }

    public void showBiggestSmallest() {

    }

}


/*            .forEach(s -> System.out.printf("\t%s\n", s));

                // den Fleischfresser mit dem kleinsten Gewicht
                Optional<Animal> min = Arrays.stream(allAnimals)
    .filter(Predicate.not(Animal::isHerbivore))
    .min(Comparator.comparing(Animal::getWeight));

    min.ifPresent(a -> System.out.println("Das Tier mit dem kleinsten Gewich ist " + a.getName()));

    int weight = 10;
    Animal max = Arrays.stream(allAnimals)
    .filter(a -> a.getWeight() < weight)
    .max(Comparator.comparing(Animal::getWeight))
    // wenn kein Objekt im ERgebnis ist, soll null geliefert werden
    .orElse(null);
    if (max != null) {
    System.out.println("Schwerste Tier unter " + weight + " x kg ist " + max);
    } else {
    System.out.println("keine Tiere im Stream");
    }
    }*/

/*
    public void showBiggestSmallest() {
        System.out.println("Ältestes/neuestes File ");
        FileData minFile = null, maxFile = null;
        // FileData mit min und max Größe suchen
        for (FileData fileData : files) {
            if (minFile == null || fileData.getSize() < minFile.getSize()) {
                minFile = fileData;
            }
            if (maxFile == null || fileData.getSize() > maxFile.getSize()) {
                maxFile = fileData;
            }
        }

        // Ergebnis anzeigen
        System.out.printf("\tKleinstes File: %s\n", minFile);
        System.out.printf("\tGrößtes   File: %s\n", maxFile);
        System.out.println();
    }*/
