package animals.program;

import animals.model.Animal;
import animals.model.AnimalFilter;

public class HeavyAnimalFilter implements AnimalFilter {

    // Schwellwert ab wann ein Tier als schwer gilt
    private int fromWeight;

    public HeavyAnimalFilter(int fromWeight) {
        this.fromWeight = fromWeight;
    }


    /**
     * Testet, ob das Tier im Ergebnis enthalen sein soll oder nicht
     *
     * @param a das Tier
     * @return
     */
    @Override
    public boolean isTrueFor(Animal a) {
        return a.getWeight() >= fromWeight;
    }
}
