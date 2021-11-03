package animals.model;

/**
 * Dieses Interface wird für die Animal-Filterung (z.B. bei der Anzeige der Tiere) verwendet.
 * Implementierende Klassen können in der isTrueFor-Implementierung festlegen,
 * ob ds übergebene Animal-Objekt im Ergebnis enthalten sein soll oder nicht
 */
// Es ist ein funtional Inteface, d.h. es hat nur eine Methode
@FunctionalInterface
public interface AnimalFilter {
    /**
     * Testet, ob das Tier im Ergebnis enthalen sein soll oder nicht
     * @param a das Tier
     * @return
     */
    boolean isTrueFor(Animal a);

    // weitere abstrakteMethoden sin in eiem functional Intrface nicht erlabubt
    // boolean isFalseFor(Animal b);
}
