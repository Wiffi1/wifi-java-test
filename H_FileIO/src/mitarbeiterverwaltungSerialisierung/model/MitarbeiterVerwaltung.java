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
import java.security.InvalidParameterException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MitarbeiterVerwaltung {

    private RepositoryInterface<Mitarbeiter> repository;

    public MitarbeiterVerwaltung(String filename) {
         this.repository = new Repository<Mitarbeiter>(filename);
    }

    public void maHinzufuegen(Mitarbeiter mitarbeiter) throws IOException {
        int foundIndex = -1;
        try {
            // https://stackoverflow.com/questions/17526608/how-to-find-an-object-in-an-arraylist-by-property
            List<Mitarbeiter> maList = repository.getAll(true);

            if (maList != null) {
                for (int i = 0; i < maList.size(); i++) {
                    Mitarbeiter mTemp = maList.get(i);
                    // Einfacher Vergleich auf den Namen des Mitarbeiters, um zu verhindern,
                    // dass es doppelte Einträge gibt (die sind beim Testen lästig).
                    if (mTemp.getName().equals(mitarbeiter.getName())) {
                        // den Index zurückliefern
                        foundIndex = i;
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (foundIndex > -1) {
//            System.out.println("Den Mitarbeiter gibt es schon");
            throw new InvalidParameterException("Den Mitarbeiter gibt es schon");
        } else {
            System.out.println("nach hinzufügen getCurrentId %d" + Mitarbeiter.getCurrentId());
            repository.add(mitarbeiter);
        }
        System.out.println("nach hinzufügen getCurrentId %d" + Mitarbeiter.getCurrentId());
    }

    public double maGehaltErhoehen(int mitarbeiterID , double prozent) throws IOException {
        Mitarbeiter ma = repository.getById(mitarbeiterID);
        // todo gesamten ma zurückgeben statt nur Gehalt?
        ma.maGehaltErhoehen(prozent);
        Mitarbeiter tmpMa = repository.updateById(mitarbeiterID, ma);
        return tmpMa.getGrundgehalt();
    }

    public void alleGehaltErhoehen(double prozent) throws IOException, ClassNotFoundException {
        List<Mitarbeiter> mitarbeiterListe = repository.getAll(true);
        for (Mitarbeiter mitarbeiter : mitarbeiterListe) {
            maGehaltErhoehen(mitarbeiter.getId(), prozent);
        }
    }

    public void maAusgeben(int MitarbeiterID) {
        Mitarbeiter ma = repository.getById(MitarbeiterID);
        System.out.printf("\t%s", ma);
    }

    public void alleAnzeigen(OrderBy orderBy) throws IOException, ClassNotFoundException {
        System.out.println("alleAnzeigen getCurrentId %d" + Mitarbeiter.getCurrentId());
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

        List<Mitarbeiter> mitarbeiterListe = repository.getAll(false);

        if (mitarbeiterListe == null || mitarbeiterListe.size() <= 0 ) {
            System.out.println("Es sind noch keine Mitarbeiter vorhanden");
        } else {
            Collections.sort(mitarbeiterListe, comparator);

            System.out.printf("\n***** Mitarbeiterliste sortiert nach %s *****\n", sortiertNach);
            System.out.printf("In der Gruppe sind %d Personen\n", mitarbeiterListe.size());
            for (Mitarbeiter mitarbeiter : mitarbeiterListe) {
                System.out.printf("\t%s\n", mitarbeiter);
            }
        }

    }

    public Mitarbeiter maAusscheiden(int mitarbeiterID) throws IOException {
        return repository.removeById(mitarbeiterID);
    }


}
