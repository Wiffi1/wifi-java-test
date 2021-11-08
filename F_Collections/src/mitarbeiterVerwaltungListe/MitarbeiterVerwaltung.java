/*Erstelle eine Verwaltungsklasse, welche alle Mitarbeiter (Mitarbeiter, Manager, Experten) in einer einzigen Liste führt. Die Klasse soll folgende Funktionalität zur Verfügung stellen:
        •	Hinzufügen eines Mitarbeiters / Managers / Experten
        •	Abrufen eines Mitarbeiters per Mitarbeiter-Nummer
        •	Gehaltserhöhung für alle Mitarbeiter durchführen
        •	Gehaltserhöhung für 1 Mitarbeiter durchführen
        •	Ausgabe eines Mitarbeiters
        •	Anzeige aller Mitarbeiter (optional: sortiert nach Name)
        •	Optional: Anzeige aller Mitarbeiter, sortiert nach Typ/Eintrittsdatum
        •	Austritt eines Mitarbeiters (Identifizierung über Mitarbeiter-Nummer)
        Bis auf die Ausgabefunktionen soll die Verwaltungsklasse keine Ein- oder Ausgaben an der Konsole enthalten.
        Wenn ein Problem oder Fehler auftritt, soll eine Exception geworfen werden. Im Main-Programm muss jeweils ein try-catch-Block die möglichen Exceptions behandeln.
        Teste die Klasse in einem geeigneten Testprogramm.*/

package mitarbeiterVerwaltungListe;

import java.util.ArrayList;
import java.util.List;

public class MitarbeiterVerwaltung {

    private List<Mitarbeiter> mitarbeiterListe = new ArrayList<>();

    public void maHinzufuegen(Mitarbeiter mitarbeiter) {
        mitarbeiterListe.add(mitarbeiter);
//        return mitarbeiterListe.get(mitarbeiterListe.size() - 1);
    }

    public Mitarbeiter testGetMaByIndex(int index) {
        return mitarbeiterListe.get(index);
    }

    public Mitarbeiter mAabrufen(int MitarbeiterID) {
        return mitarbeiterListe.get(MitarbeiterID);
    }

    public double maGehaltErhoehen(int mitarbeiterID , double prozent) {
        Mitarbeiter ma = getMitarbeiterById(mitarbeiterID);
        return ma.maGehaltErhoehen(prozent);
    }

    public void alleGehaltErhoehen(double prozent) {
        for (Mitarbeiter mitarbeiter : mitarbeiterListe) {
            maGehaltErhoehen(mitarbeiter.getId(), prozent);
        }
    }

    public void maAusgeben(int MitarbeiterID) {
        Mitarbeiter ma = getMitarbeiterById(MitarbeiterID);
        System.out.printf("%s", ma);
    }

//    private void alleAnzeigen(enum sortedBy)
    public void alleAnzeigen(String sortedBy) {
        // size liefert die Anzahl der Elemente in der Liste
        System.out.println("\n***** Mitarbeiterliste *****");
        System.out.printf("In der Gruppe sind %d Personen\n", mitarbeiterListe.size());
        for (Mitarbeiter mitarbeiter : mitarbeiterListe) {
            System.out.printf("\t%s\n", mitarbeiter);
        }
    }

    public Mitarbeiter maAusscheiden(int mitarbeiterID) {
        Mitarbeiter ma = getMitarbeiterById(mitarbeiterID);
        mitarbeiterListe.remove(ma);
        return ma;
    }

    // Ist es da eigentlich besser -1 oder error?
    private Mitarbeiter getMitarbeiterById(int mitarbeiterID) {
        int index = findeMitarbeiterIndex(mitarbeiterID);
        if (index == -1) {
            throw new IndexOutOfBoundsException();
        } else {
            return mitarbeiterListe.get(index);
        }
    }

    // den Index des Mitarbeiters im Array suchen
    private int findeMitarbeiterIndex(int mitarbeiterID) {
        // Mitarbeiter suchen
        // indexOf-Methode der Liste ist nicht zielführend, weil wir nicht
        // eine Zahl mit einem Mitarbeiter-Objekt vergleichen können
        //int x = liste.indexOf(nr)
        // daher selber das passende Objekt suchen
        for (int i = 0; i < mitarbeiterListe.size(); i++) {
            Mitarbeiter mTemp = mitarbeiterListe.get(i);
            // wenn die Nummer gleich der gesuchten Nummer ist
            if(mTemp.getId() == mitarbeiterID){
                // den Index zurückliefern
                return i;
            }
        }

        // wenn wir die Nummer nicht gefunden haben,
        // einen ungültigen Index zurückliefern
        return -1;
    }
}
