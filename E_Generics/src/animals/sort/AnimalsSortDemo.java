package animals.sort;

import java.util.Arrays;

public class AnimalsSortDemo {
    public static void main(String[] args) {
        Animal[] allAnimals = new Animal[] {
                new Animal("Löwe", "Panthera leo", 250, false),
                new Animal("Waschbär", "Procyon", 12, false),
                new Animal("Bison", "Bison bison", 950, true),
                new Animal("Elefant", "Loxodonta", 4000, true),
                new Animal("Eisbär", "Ursus maritimus", 650, false),
                new Animal("Emu", "Dromaius novaehollandiae", 37, false)
        };
        show(allAnimals, "Tiee unsortiert");
        // das Array sortieren (dabei wird die Standarsortierreihenfolge verwendet)
        // dh. die Comparable-Implementierung aus Animal
        // dh. es muss folgende Umwandlung möglich sein
/*        Comparable<Animal> x = allAnimals[1];
        int cmp = x.compareTo(allAnimals[0]);*/
        Arrays.sort(allAnimals);
        show(allAnimals, "Tiee sortiert");
    }

    static void show(Animal[] animals, String text) {
        System.out.println(text);
        for (Animal a : animals) {
            System.out.printf("\t%s\n", a);
        }
    }

}
