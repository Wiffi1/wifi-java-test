package animals.program;

import animals.model.Animal;

public class FilterHelper {

    // diese Methode passt (außer beim Namen zur Signatur vno
    // AnimalFiler.isTrueFor(Animal a)
    // daher kann die Methode als Method Reference als AnimalFilter -Imprlentierung
    //eingesetze werden (wir brauchen für den Aufruf aber ein Objekt)
    public boolean isVegetarian(Animal a) {
        return a.isHerbivore();
    }

    public boolean isLightWeight(Animal a) {
        return a.getWeight() < 500;
    }
}
