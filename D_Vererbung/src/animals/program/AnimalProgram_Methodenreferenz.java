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

// 1. Art: statische Methode in einer Klasse
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
        // Method reference als AnimalFilter-Implementierung
        animals.showAnimals(FilterUtil::isHeavvy);
        // als Lambda-Expression
    //  animals.showAnimals(a -> FilterUtil.isHeavvy(a));

// 2. Art: Instanzmethode in einer Klasse
        // Objekt erzeugen
        FilterHelper helperObjekt = new FilterHelper();
        System.out.println("Vegetarier: ");
        // Method reference als AnimalFilter-Implementierung
        animals.showAnimals(helperObjekt::isVegetarian);
        // mit Lambda-Expression
//        animals.showAnimals(a -> helperObjekt.isVegetarian(a));

        System.out.println("leichte Tiere: ");
        animals.showAnimals(helperObjekt::isLightWeight);
        // mit Lambda-Expression
//        animals.showAnimals(a -> helperObjekt.isLightWeight(a));

// 3. Art: Instanzmethode in einer Klasse mit arbiträrem Objekt
        // d.h. ein Objekt aus der Parameterliste wird für den Aufruf der Methode verwendet
        System.out.println("Hervivoren:");
        // Method reference als AnimalFilter-Implementierung
        animals.showAnimals(Animal::isHerbivore);
        // mit Lambda-Expression
//        animals.showAnimals(a -> a.isHerbivore());
    }

}
