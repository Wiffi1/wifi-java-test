package animals.program;

import animals.model.Animal;
import animals.model.AnimalFilter;
import animals.model.AnimalList;

public class AnimalProgram_Anonym {
    public static void main(String[] args) {

        System.out.println("Animal Filter mit anonymen Klassen");
        AnimalList animals = new AnimalList();
        System.out.println("alleTiere");
        animals.showAll();
        System.out.println("Vegetarier");

        // AnimalFilter mit anonymer Klasse
        // statt Instanz der separat definierten Klasse
        // Instanz einer anonymen Klasse
        //HerbivoreFilter filter1 = new HerbivoreFilter();
        AnimalFilter filter1 = new AnimalFilter() {
            @Override
            public boolean isTrueFor(Animal a) {
                return a.isHerbivore();
            }
        };

        animals.showAnimals(filter1);

        System.out.println("Schwere Tiere");
        // direkt als Argument übergeben
        animals.showAnimals(new AnimalFilter() {
            @Override
            public boolean isTrueFor(Animal a) {
//                animals.getClass()
                return a.getWeight() >= 500;
            }
        });
    }
}
