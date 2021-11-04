package animals.sort;

import java.util.Arrays;
import java.util.Comparator;

public class AnimalSortDemo_MethodenReferenzen {
    public static void main(String[] args) {
        Animal[] allAnimals = new Animal[]{
                new Animal("Löwe", "Panthera leo", 250, false),
                new Animal("Waschbär", "Procyon", 12, false),
                new Animal("Bison", "Bison bison", 950, true),
                new Animal("Elefant", "Loxodonta", 4000, true),
                new Animal("Eisbär", "Ursus maritimus", 650, false),
                new Animal("Emu", "Dromaius novaehollandiae", 37, false)

        };

        System.out.println("Sortierung mit Methoden Referenzen");
        Comparator<Animal> comparator;
        // mit Lambda Expression
        // comparator = (a1, a2) -> a1.getWeight() - a2.getWeight();
        // mit Method reference und Hilfsmethode aus Comparator
        // comparator = Comparator.comparing(a -> a.getWeight());
        comparator = Comparator.comparing(Animal::getWeight);

        // mit diesem Vergleicher sortieren
        Arrays.sort(allAnimals, comparator);
        show(allAnimals, "Nach Gewicht sortiert");

        // nach Pflanzenfresser-Flag absteigend und dem lat. Namen aufsteigend
//        comparator = (o1, o2) -> {
//            int ret = -Boolean.compare(o1.isHerbivore(), o2.isHerbivore());
//            // wenn beide Pflanzenfresser oder beide Fleischfresser sind,
//            // dann ist ret jetzt 0 -> dann noch nach Lat. Name sortieren
//            if (ret == 0) {
//                ret = o1.getLatinName().compareTo(o2.getLatinName());
//            }
//            //System.out.printf("compare %s und %s: %d\n", o1, o2, ret);
//            return ret;
//        };
        // neu mit Methoden-Referenzen
        comparator = Comparator
                .comparing(Animal::isHerbivore)
                .reversed()
                .thenComparing(Animal::getLatinName);

        Arrays.sort(allAnimals, comparator);
        show(allAnimals, "Nach Pflanzenfresser absteigend, dann nach lat. Namen");
    }


    static void show(Animal[] animals, String text) {
        System.out.println(text);
        for (Animal a : animals) {
            System.out.printf("\t%s\n", a);
        }
        System.out.println();
    }
}
