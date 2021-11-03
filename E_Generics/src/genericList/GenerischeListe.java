package genericList;

/**
 * Diese Klasse kapselt eine Liste von Objekten vom Typ T in einem Array
 * @param <T> Der Typ der Elemente die im Array abgelegt werden
 *
 * @author Michaela
 */
public class GenerischeListe<T> {

    // Array für die Elemente
    private Object[] gruppe;
    // Anzahl der Elemente
    private int anzahl;

    public GenerischeListe(int maxAnzahl) {

        // Deklaration eines T[] ist möglich, aber die Instanziierung ist nicht erlaubt.
//        private  T[] typisiertesArray;

        // Daher müssen wir für die Elente ein Object-Array verwenden.
        gruppe = new Object[maxAnzahl];
    }

    /**
     * Ein Element hinzufügen. Es wird im Array am nächsten freien Platz
     * hinzugefügt
     *
     * @param p das Element
     */
    // Neuer Name: hinzufuegen statt anmelden
    // public void anmelden(Person p) {
    public void hinzufuegen(T p) {
        // wenn alle Plätze belegt sind
        if (anzahl == gruppe.length) {
            throw new IllegalStateException("Die Liste ist voll!");
        }

        // sonst: das Element eintragen
        gruppe[anzahl++] = p;
    }

    /**
     * alle Element anzeigen
     */
    public void alleAnzeigen() {
        System.out.printf("In der List sind %d Elemente: \n", anzahl);
        // nur so viele Elemente anzeigen wie angemeldet sind
        for (int i = 0; i < anzahl; i++) {
            System.out.printf("\t%s\n", gruppe[i]/*.toString()*/);
        }
    }

    /**
     * das Element an einem Index zurückliefern
     *
     * @param index
     */
    // neue Signatur und Bedeutung:
    // statt public void anzeigen(int nr):
    public T elementAnIndex(int index) {
        if (index < 0 || index >= anzahl) {
            throw new IndexOutOfBoundsException("Es gibt kein Element anb Index " + index);
        } else {
            @SuppressWarnings("unchecked")
            T tmp = (T) gruppe[index];
            return tmp;
        }
    }

    /**
     * eie Element entfernen. Es wird aus dem Array entfernt. Dahinterliegede
     * Elemente werden nach vorne verschoben
     *
     * @param index der Index des Elements
     * @return das Element
     */
    // statt  public Person abmelden(int nr)
    public T entfernen(int index) {

        if (index < 0 || index >= anzahl) {
            throw new IndexOutOfBoundsException("Es gibt kein Element anb Index " + index);
        }

        @SuppressWarnings("unchecked")
        T geloescht = (T)gruppe[index];
        // die im Array dahinter liegenden Referenzen um 1 Platz nach vor verschieben
        for (int i = index; i < anzahl - 1; i++) {
            gruppe[i] = gruppe[i + 1];
        }

        // die Anzahl vermindern und das nicht mehr benötigte Element initialisieren
        gruppe[--anzahl] = null;

        return geloescht;

    }

    // diese Methode wird nicht benötigt, da die Elemente per Index angesprochen werden
    //  den Index der Person im Array suchen
    //  private int findePersonIndex(int nr) {...}

    /**
     * liefert die Anzahl der Elemente, die aktuell imm Array abgelegt sind.
     * @return
     */
    public int getAnzahl() {
        return anzahl;
    }

}
