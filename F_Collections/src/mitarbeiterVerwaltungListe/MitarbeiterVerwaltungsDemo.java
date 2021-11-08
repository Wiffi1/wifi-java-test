package mitarbeiterVerwaltungListe;

import java.time.LocalDate;

public class MitarbeiterVerwaltungsDemo {

    private static MitarbeiterVerwaltung maVerwaltung = new MitarbeiterVerwaltung();

    public static void main(String[] args) {

        Mitarbeiter mitarbeiter = new Mitarbeiter("Georg Angestellter", LocalDate.of(2002, 2, 2), LocalDate.of(2012, 2, 2), 1500.0);
        Mitarbeiter experte1 = new Experte("Gustav Expert", LocalDate.of(2002, 2, 2), LocalDate.of(2012, 2, 2), 2000.0, "c#");
        Mitarbeiter experte2 = new Experte("Daniel Düsentrieb", LocalDate.of(2001, 1, 1), LocalDate.of(2010, 1, 1), 1000.0, "Java1");
        Mitarbeiter manager1 = new Manager("Franz I Manager1",  LocalDate.of(2002, 2, 2), LocalDate.of(2012, 2, 2), 2000.0, 1000);
        Mitarbeiter manager2 = new Manager("Franz II Manager2",  LocalDate.of(2002, 2, 2), LocalDate.of(2012, 2, 2), 2000.0, 2000);

        maVerwaltung.maHinzufuegen(mitarbeiter);
        maVerwaltung.maHinzufuegen(experte1);
        maVerwaltung.maHinzufuegen(experte2);
        maVerwaltung.maHinzufuegen(manager1);
        maVerwaltung.maHinzufuegen(manager2);

        maVerwaltung.alleAnzeigen("");

        System.out.println("Mitarbeiter Experte2 wird ausgeschieden");
        // id == -1, da Mitarbeiter bereits ausgeschieden ist
        // mitarbeiterListe.mAausgeben(ausgeschieden.getId());
        Mitarbeiter ausgeschieden = maVerwaltung.maAusscheiden(experte2.getId());
        System.out.printf("\nfolgender Mitarbeiter wurde ausgeschieden: %s\n", ausgeschieden);

        Mitarbeiter ersatzExperte = new Experte("Franz III Manager2",  LocalDate.of(2002, 2, 2), LocalDate.of(2012, 2, 2), 2000.0, "Typescript");
        maVerwaltung.maHinzufuegen(ersatzExperte);

        int idNichtVorhandenerMa = experte2.getId();
        maAusscheiden(idNichtVorhandenerMa);
        maGehaltErhoehen(idNichtVorhandenerMa, 13);

        System.out.println();
        System.out.println("**** alle erhalten eine Gehaltserhöhung um 50% ****");
        maVerwaltung.alleGehaltErhoehen(50);
        maVerwaltung.alleAnzeigen("");

        //        mitarbeiterListe.mAausgeben(2);
        //        Mitarbeiter ma = mitarbeiterListe.testGetMaByIndex(0);
        //        System.out.printf("%s", ma);
    }

    public static void maAusscheiden(int mitarbeiterID) {
        try {
            maVerwaltung.maAusscheiden(mitarbeiterID);
        } catch (Exception e) {
            System.out.println();
            System.out.println("-- ACHTUNG! Fehler beim Ausscheiden eines Mitarbeiter --");
            System.out.printf("\t\tMitarbeiter mit id %d wurde nicht gefunden (und ist möglicheweise bereits ausgeschieden) \n", mitarbeiterID);
        }
    }

    public static void maGehaltErhoehen(int mitarbeiterID, double prozent) {
        try {
            maVerwaltung.maGehaltErhoehen(mitarbeiterID, prozent);
        } catch (Exception e) {
            System.out.println();
            System.out.println("-- ACHTUNG! Fehler bei der Lohnerhöhung für einen Mitarbeiter --");
            System.out.printf("\t\tMitarbeiter mit id %d wurde nicht gefunden (und ist möglicheweise bereits ausgeschieden) \n", mitarbeiterID);
        }
    }

}
