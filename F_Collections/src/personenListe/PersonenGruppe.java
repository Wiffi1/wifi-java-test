package personenListe;

import java.util.ArrayList;
import java.util.List;

/**
 * Diese Klasse kapselt eine Gruppe von Person-Objekten in einem Array
 *
 * @author Michaela
 */
public class PersonenGruppe {

    // Liste für Personen mit einer Standard-Kapazität
    private List<Person> gruppe = new ArrayList<>();



    /**
     * Eine Person anmelden. Die Person wird im Array am nächsten freien Platz
     * hinzugefügt
     *
     * @param p die Person, die angemeldet werden soll
     */
    public void anmelden(Person p) {
        // mit add am Ende hinzufügen
        gruppe.add(p);
    }

    /**
     * alle Personen anzeigen
     */
    public void alleAnzeigen() {
        // alle anzeigen (Iteraton mit foreach)
        System.out.printf("In der Gruppe sind  %d Personen\n", gruppe.size());
        for (Person p : gruppe) {
            System.out.println();
        }
    }

    /**
     * eine Person anzeigen
     *
     * @param nr
     */
    public void anzeigen(int nr) {
        int index = findePersonIndex(nr);
        if (index < 0) {
            System.out.printf("Person mit Nummer %d existiert nicht\n", nr);
        } else {
            Person pTemp = gruppe.get(index);
            System.out.printf("Person gefunden: %s\n", pTemp);
        }
    }

    /**
     * eine Person abmelden. Die Person wird aus dem Array entfernt. Dahinterliegede
     * Objekte werden nach vorne verschoben
     *
     * @param nr die Nummer Person die abgemeldet wird
     * @return das Person-Objekt
     */
    public Person abmelden(int nr) {
        int index = findePersonIndex(nr);
        // wenn nicht gefunden -> Exception werfen
        if (index < 0) {
            throw new IllegalArgumentException(" Person mit Nummer " + nr + " existiert nicht");
        }
        // Person entfernen
        // das Objekt aus der Liste abrufen
        Person geloescht = gruppe.get(index);
        // aus der Liste entfernen

        gruppe.remove(index);

        // und das Objekt zurückenliefern
        return geloescht;

    }

    // den Index der Person im Array suchen
    private int findePersonIndex(int nr) {

        // Person suchen
        // indexOf-Methode der Liste ist nicht zielführend, weil wir nicht eine Zahl und ein Person-objekt vergleichen
        // können.
        // int x = liste.indexOf(nr)
        // Daher selbst das passende Objekt suchen.
        for (int i = 0; i < gruppe.size(); i++) {
            Person pTemp = gruppe.get(i);
            if (pTemp.getNr() == nr) {
                // den Index zurückliefern
                return i;
            }
        }

        // wenn wir die Nummer nicht gefunden haben,
        // einen ungültigen Index zurückliefern
        return -1;
    }
}
