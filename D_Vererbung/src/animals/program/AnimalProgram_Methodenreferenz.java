package animals.program;

import animals.model.Animal;
import animals.model.AnimalFilter;
import animals.model.AnimalList;

public class AnimalProgram_Methodenreferenz {

    public static void main(String[] args) {

        System.out.println("Animal Filter mit anonymen Klassen");
        AnimalList animals = new AnimalList();
        System.out.println("alleTiere");
        animals.showAll();
        System.out.println("Vegetarier");

        animals.showAll();

        AnimalFilter filter1;

        // als Lambda-Expression
//        filter1 = a -> !a.isHerbivore();
        // als Method reference
        filter1 = FilterUtil::isCarnivore;

        System.out.println("Fleischfresser:");
        animals.showAnimals(filter1);

        // als Lambda-Expression
//        filter1 = a -> a.isHerbivore();
        // als Method reference
        System.out.println("Schwere Tiere:");
        animals.showAnimals(FilterUtil::isHeavvy);
        // als Lambda-Expression
//        animals.showAnimals(a -> FilterUtil.isHeavvy(a));
    }

}
