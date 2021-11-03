package animals.sort;

import java.util.Arrays;
import java.util.Comparator;

public class AnimalSortDemoLambda {
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

        System.out.println("Sortierung mit Lambda Expression");

        Comparator<Animal> comparator = (a1, a2) -> a1.getWeight() - a2.getWeight();
        // mit diesem Vergleicher sortieren
        Arrays.sort(allAnimals, comparator);
        show(allAnimals, "nach Gewicht sortiert");

        // nach Pflanzenfresser-flag absteigend und dem lat. Namen
        comparator = (o1, o2) -> {
            int ret = -Boolean.compare(o1.isHerbivore(), o2.isHerbivore());
            // Wenn beide Vegetarier oder beide Fleischfresser sind.
            // dann ist ret jetzt 0 -> dann noch nach lat Namen sortieren
            if (ret == 0) {
                ret = o1.getLatinName().compareTo(o2.getLatinName());
            }
//            System.out.printf("compare %s und %s: %d\n", o1, o2, ret);
            return ret;
        };

        Arrays.sort(allAnimals, comparator);
        show(allAnimals, "Nach Pflanzenfresser absteigend, dann nach lat. Namen");
    }

    static void show(Animal[] animals, String text) {
        System.out.println(text);
        for (Animal a : animals) {
            System.out.printf("\t%s\n", a);
        }
    }
}
