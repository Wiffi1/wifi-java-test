package animals.sort;

import java.util.Comparator;

// Für die Sortierung nach PflanzenfresserUndLatName
public class SortiereNachPflanzenfresserUndLatName implements Comparator<Animal> {
    @Override
    public int compare(Animal o1, Animal o2) {
        // booleans können nicht sutrahiert werden
        // int ret = o1.isHerbivore(), o2.isHerbivore();
        // Standardvergleich für 2 booleans verwenden
        int ret = Boolean.compare(o1.isHerbivore(), o2.isHerbivore());
        // Wenn beide Vegetarier oder beide Fleischfresser sind.
        // dann ist ret jetzt 0 -> dann noch nach lat Namen sortieren
        if (ret == 0) {
            ret = o1.getLatinName().compareTo(o2.getLatinName());
        }
        System.out.printf("compare %s und %s: %d\n", o1, o2, ret);
        return ret;
    }
}
