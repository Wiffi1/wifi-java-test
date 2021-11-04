package animals.program;

import animals.model.Animal;
public class FilterUtil {
    // diese Methode passt (außer beim Namen zur Signatur vno
    // AnimalFiler.isTrueFor(Animal a)
    // daher kann die Methode als Method Reference als AnimalFilter -Imprlentierung
    //eingesetze werden
    public static boolean isCarnivore(Animal a) {
        return !a.isHerbivore();
    }

    // auch diese Methode passt zur Signatur
    public static boolean isHeavvy(Animal a) {
        return a.getWeight() >= 500;
    }

}
