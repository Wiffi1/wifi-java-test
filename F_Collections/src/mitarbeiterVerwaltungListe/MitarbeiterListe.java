package mitarbeiterVerwaltungListe;

import personenListe.Person;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MitarbeiterListe {

    private List<Mitarbeiter> mitarbeiterListe = new ArrayList<>();

    public void test() {
    }

    public void mAhinzufuegen (Mitarbeiter mitarbeiter) {
        mitarbeiterListe.add(mitarbeiter);
    }

    public Mitarbeiter mAabrufen(int MitarbeiterID) {
        return mitarbeiterListe.get(MitarbeiterID);
    }

    public void mAgehaltErhoehen(int MitarbeiterID , double betrag) {

    }

    public void alleGehaltErhoehen(double betrag) {

    }

    public Mitarbeiter mAausgeben(int MitarbeiterID) {
        return null;
    }

//    private void alleAnzeigen(enum sortedBy)
    public void alleAnzeigen(String sortedBy) {

    }

    public Mitarbeiter mAausscheiden(int MitarbeiterID) {
        return null;
    }

}
