package mitarbeiterverwaltungSerialisierung;

import serialization.model.Fahrzeug;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MitarbeiterRepository {

    // Version von unserem Dokument-Format
    private static final long serialVersionUID = 1L;
    private List<Mitarbeiter> mitarbeiterListe = new ArrayList<>();

    private String fileName;

    public MitarbeiterRepository(String fileName) {
//        this.mitarbeiterListe = mitarbeiterListe;
        this.fileName = fileName;
    }

    /***** Mitarbeiterverwaltung Anfang***/
    public void maHinzufuegen(Mitarbeiter mitarbeiter) {
        mitarbeiterListe.add(mitarbeiter);
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
        System.out.printf("\t%s", ma);
    }

    public void alleAnzeigen(OrderBy orderBy) {

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

        Collections.sort(mitarbeiterListe, comparator);

        System.out.printf("\n***** Mitarbeiterliste sortiert nach %s *****\n", sortiertNach);
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
/***** Mitarbeiterverwaltung Ende***/

}
