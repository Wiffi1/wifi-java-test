package mitarbeiterverwaltungSerialisierung;

import mitarbeiterverwaltungSerialisierung.model.Experte;
import mitarbeiterverwaltungSerialisierung.model.Manager;
import mitarbeiterverwaltungSerialisierung.model.Mitarbeiter;
import mitarbeiterverwaltungSerialisierung.model.MitarbeiterVerwaltung;

import java.io.IOException;
import java.time.LocalDate;

public class MitarbeiterVerwaltungsDemo {

    private static MitarbeiterVerwaltung maVerwaltung = new MitarbeiterVerwaltung("maVerwaltSerialize.bin");

    public static void main(String[] args) {

        try {
            testMaVerwaltungOld();
//            testMaVerwaltung();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void testMaVerwaltung() throws IOException, ClassNotFoundException {
/*        Mitarbeiter mitarbeiter = new Mitarbeiter("Mitarbeiter-Test3", LocalDate.of(2002, 2, 2), LocalDate.of(2012, 2, 2), 1500.0);
        maVerwaltung.maHinzufuegen(mitarbeiter);
        maVerwaltung.alleAnzeigen(OrderBy.NO_ORDER);

        Mitarbeiter experte2 = new Experte("Daniel Düsentrieb", LocalDate.of(2001, 1, 1), LocalDate.of(2010, 1, 1), 1000.0, "Java1");
        maVerwaltung.maHinzufuegen(experte2);
        maVerwaltung.alleAnzeigen(OrderBy.NO_ORDER);
        System.out.printf("id auszuscheiden %d\n", experte2.getId());
        maAusscheiden(experte2.getId());*/

        maAusscheiden(2);
        maAusscheiden(20);

        Mitarbeiter mitarbeiter = new Mitarbeiter("Mitarbeiter-Test4", LocalDate.of(2002, 2, 2), LocalDate.of(2012, 2, 2), 1500.0);
        maVerwaltung.maHinzufuegen(mitarbeiter);

        maVerwaltung.alleAnzeigen(OrderBy.NO_ORDER);

        System.out.println("\n** Lohnerhoehung für Mitarbeiter mit id=5 um 33% **");
        maGehaltErhoehen(17, 33);
    }

    private static void testMaVerwaltungOld() throws IOException, ClassNotFoundException {
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

        maVerwaltung.alleAnzeigen(OrderBy.NO_ORDER);

        System.out.println("\n** Mitarbeiter Experte2 wird ausgeschieden **");
        maAusscheiden(experte2.getId());

        System.out.println("\n** Ein neuer Experte wird eingestellt **");
        Mitarbeiter ersatzExperte = new Experte("Franz III Manager2",  LocalDate.of(2002, 2, 2), LocalDate.of(2012, 2, 2), 2000.0, "Typescript");
        maHinzufuegen(ersatzExperte);
        maVerwaltung.alleAnzeigen(OrderBy.NO_ORDER);

        System.out.println("\n** Mitarbeiter mit nicht vorhandener ID wird versucht, auszuscheiden **");
        int idNichtVorhandenerMa = experte2.getId();
        maAusscheiden(idNichtVorhandenerMa);

/*        System.out.println("\n** Lohnerhoehung für Mitarbeiter mit nicht vorhandener ID wird versucht **");
        maGehaltErhoehen(idNichtVorhandenerMa, 13);*/

        maVerwaltung.alleAnzeigen(OrderBy.NO_ORDER);

        System.out.println("\n** Lohnerhoehung für Mitarbeiter mit id=5 um 33% **");
        maGehaltErhoehen(5, 33);

        maVerwaltung.alleAnzeigen(OrderBy.NO_ORDER);


        System.out.println();
        System.out.println("** alle erhalten eine Gehaltserhöhung um 50% **");
        maVerwaltung.alleGehaltErhoehen(50);
        maVerwaltung.alleAnzeigen(OrderBy.NO_ORDER);

        maVerwaltung.alleAnzeigen(OrderBy.NAME);
        maVerwaltung.alleAnzeigen(OrderBy.TYPE_EINTRITTSDATUM);

        System.out.println("\n** Mitarbeiter mit nicht vorhandener ID ausgeben **");
        maAusgeben(27);

        System.out.println("\n** Mitarbeiter mit id=2 ausgeben **");
        maAusgeben(2);
    }

    public static void maAusscheiden(int mitarbeiterID) {
        try {
            Mitarbeiter tmpMa = maVerwaltung.maAusscheiden(mitarbeiterID);
            System.out.println("\tfolgender Mitarbeiter wurde ausgeschieden:");
            System.out.printf("\t\t%s\n", tmpMa);
        } catch (Exception e) {
            System.out.println("\t-- ACHTUNG! Fehler beim Ausscheiden eines Mitarbeiter --");
            System.out.printf("\t-- Mitarbeiter mit id %d wurde nicht gefunden (und ist möglicheweise bereits ausgeschieden) \n", mitarbeiterID);
        }
    }

    public static void maGehaltErhoehen(int mitarbeiterID, double prozent) {
        try {
            double neuerGehalt = maVerwaltung.maGehaltErhoehen(mitarbeiterID, prozent);
            System.out.printf("\tNeuer Gehalt für id: %d: %f\n", mitarbeiterID, neuerGehalt);
            maAusgeben(mitarbeiterID);
        } catch (Exception e) {
            System.out.println("\t-- ACHTUNG! Fehler bei der Lohnerhöhung für einen Mitarbeiter --");
            System.out.printf("\t-- Mitarbeiter mit id %d wurde nicht gefunden (und ist möglicheweise bereits ausgeschieden) \n", mitarbeiterID);
        }
    }

    public static void maHinzufuegen(Mitarbeiter ersatzExperte) throws IOException {
        maVerwaltung.maHinzufuegen(ersatzExperte);
        System.out.printf("\tfolgender Esperte wurde eingstellt: \t%s\n", ersatzExperte);
    }

    public static void maAusgeben(int mitarbeiterID) {
        try {
            maVerwaltung.maAusgeben(mitarbeiterID);
        } catch (Exception e) {
            System.out.println("\t-- ACHTUNG! Fehler bei der Ausgabe für einen Mitarbeiter --");
            System.out.printf("\t-- Mitarbeiter mit id %d wurde nicht gefunden (und ist möglicheweise bereits ausgeschieden) \n", mitarbeiterID);
        }
    }


}
