package animals.program;

import animals.model.Animal;
import animals.model.AnimalFilter;
import animals.model.AnimalList;

public class AnimalProgram_Lambdas {
    public static void main(String[] args) {
        System.out.println("Animal Filter mit anonymen Klassen");
        AnimalList animals = new AnimalList();
        System.out.println("alleTiere");
        animals.showAll();
        System.out.println("Vegetarier");

        animals.showAll();
        // Lambda eypression mit Parametertyp und Block-Klammern
        AnimalFilter filter1 = (Animal a) -> {
            return !a.isHerbivore();
        };

//        // kürzer:
//        filter1 = a -> !a.isHerbivore();
        System.out.println("Fleischfresser:");
        animals.showAnimals(filter1);

        // Lambda expression ohne Block-Klammer, direkt als Parameter übergeben
        System.out.println("leichte Tiere");
//        filter1 = a -> !a.isHerbivore();
        animals.showAnimals(a -> a.getWeight() < 500);
    }
}
