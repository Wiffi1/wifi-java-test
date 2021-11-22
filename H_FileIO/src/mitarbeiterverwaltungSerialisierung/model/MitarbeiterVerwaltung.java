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

package mitarbeiterverwaltungSerialisierung.model;

import mitarbeiterverwaltungSerialisierung.OrderBy;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MitarbeiterVerwaltung {

    private static RepositoryInterface<Mitarbeiter> repository;

    public MitarbeiterVerwaltung(String filename) {
         this.repository = new Repository(filename);
    }

    public void maHinzufuegen(Mitarbeiter mitarbeiter) throws IOException {
        repository.add(mitarbeiter);
    }

    public double maGehaltErhoehen(int mitarbeiterID , double prozent) throws IOException, ClassNotFoundException {
        Mitarbeiter ma = repository.getById(mitarbeiterID);
        // todo ma zurückgeben statt Gehalt?
        ma.maGehaltErhoehen(prozent);
        Mitarbeiter tmpMa = repository.updateById(mitarbeiterID, ma);
        return tmpMa.getGrundgehalt();
    }

    public void alleGehaltErhoehen(double prozent) throws IOException, ClassNotFoundException {
        List<Mitarbeiter> mitarbeiterListe = repository.getAll();
        for (Mitarbeiter mitarbeiter : mitarbeiterListe) {
            maGehaltErhoehen(mitarbeiter.getId(), prozent);
        }
    }

    public void maAusgeben(int MitarbeiterID) throws IOException, ClassNotFoundException {
        Mitarbeiter ma = repository.getById(MitarbeiterID);
        System.out.printf("\t%s", ma);
    }

    public void alleAnzeigen(OrderBy orderBy) throws IOException, ClassNotFoundException {

        Comparator<Mitarbeiter> comparator;
        comparator = switch (orderBy){
            case NO_ORDER -> Comparator.comparing(Mitarbeiter::getId);
            case NAME -> Comparator.comparing(Mitarbeiter::getName);
            case TYPE_EINTRITTSDATUM -> Comparator.comparing(Mitarbeiter::getType)
                    .thenComparing(Mitarbeiter::getEintrittsdatum) ;
        };

        String sortiertNach = switch (orderBy){
            case NO_ORDER -> "Id";
            case NAME -> "Name";
            case TYPE_EINTRITTSDATUM -> "Type/Eintrittsdatum";
        };

        List<Mitarbeiter> mitarbeiterListe = repository.getAll();
        Collections.sort(mitarbeiterListe, comparator);

        System.out.printf("\n***** Mitarbeiterliste sortiert nach %s *****\n", sortiertNach);
        System.out.printf("In der Gruppe sind %d Personen\n", mitarbeiterListe.size());
        for (Mitarbeiter mitarbeiter : mitarbeiterListe) {
            System.out.printf("\t%s\n", mitarbeiter);
        }
    }

    public Mitarbeiter maAusscheiden(int mitarbeiterID) throws IOException, ClassNotFoundException {
        return repository.removeById(mitarbeiterID);
    }


}
