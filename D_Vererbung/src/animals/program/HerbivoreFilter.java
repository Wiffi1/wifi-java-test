package animals.program;

import animals.model.Animal;
import animals.model.AnimalFilter;

public class HerbivoreFilter implements AnimalFilter {
    /**
     * Testet, ob das Tier im Ergebnis enthalen sein soll oder nicht
     *
     * @param a das Tier
     * @return
     */
    @Override
    public boolean isTrueFor(Animal a) {
        return a.isHerbivore();
    }
}
