package mitarbeiterverwaltungSerialisierung;

import mitarbeiterverwaltungSerialisierung.model.*;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

public class MitarbeiterRepositoryDemo {

    private static String fileName = "maSerialze.bin";
    private static MitarbeiterRepository maRepository = new MitarbeiterRepository(fileName);
    
    public static void main(String[] args) {
        // gespeicherte Daten einlesen
        File maFile = new File(fileName);
        try {
            if (!maFile.exists()) {
                // Erst-speicherung
                initalSaveData();
            } else {
                diverseTests();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void diverseTests() {

        maRepository.alleAnzeigen(OrderBy.NO_ORDER);


        System.out.println("\n** Mitarbeiter Experte2 wird ausgeschieden **");
        maAusscheiden(1);
        maAusscheiden(3);
        maAusscheiden(4);
        maAusscheiden(5);

        System.out.println("\n** Ein neuer Experte wird eingestellt **");
        Mitarbeiter ersatzExperte = new Experte("Franz III Manager2",  LocalDate.of(2002, 2, 2), LocalDate.of(2012, 2, 2), 2000.0, "Typescript");
        maHinzufuegen(ersatzExperte);
        maRepository.alleAnzeigen(OrderBy.NO_ORDER);

 /*       System.out.println("\n** Mitarbeiter mit nicht vorhandener ID wird versucht, auszuscheiden **");
        int idNichtVorhandenerMa = experte2.getId();
        maAusscheiden(idNichtVorhandenerMa);

*//*        System.out.println("\n** Lohnerhoehung für Mitarbeiter mit nicht vorhandener ID wird versucht **");
        maGehaltErhoehen(idNichtVorhandenerMa, 13);*//*

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
        maAusgeben(2);*/
    }

    private static void initalSaveData() throws IOException {
/*        maRepository.add(new Auto("Mercedes", 30000, LocalDate.of(2019, 12, 15), 110, "Silber"));
        maRepository.add(new LKW("MAN", 150000, LocalDate.of(2020, 10, 2), 320, "Rot", 8000, 100, 3));
        maRepository.add(new Fahrrad("KTM", 2000, LocalDate.of(2020, 5, 21), 27, "Mountainbike"));

        System.out.println("Folgende Fahrzeuge werden gespeichert");
        maRepository.showAll();
        maRepository.saveData();*/


        Mitarbeiter mitarbeiter = new Mitarbeiter("Georg Angestellter", LocalDate.of(2002, 2, 2), LocalDate.of(2012, 2, 2), 1500.0);
        Mitarbeiter experte1 = new Experte("Gustav Expert", LocalDate.of(2002, 2, 2), LocalDate.of(2012, 2, 2), 2000.0, "c#");
        Mitarbeiter experte2 = new Experte("Daniel Düsentrieb", LocalDate.of(2001, 1, 1), LocalDate.of(2010, 1, 1), 1000.0, "Java1");
        Mitarbeiter manager1 = new Manager("Franz I Manager1",  LocalDate.of(2002, 2, 2), LocalDate.of(2012, 2, 2), 2000.0, 1000);
        Mitarbeiter manager2 = new Manager("Franz III Manager2",  LocalDate.of(2002, 2, 2), LocalDate.of(2012, 2, 2), 2000.0, 2000);

        maRepository.maHinzufuegen(mitarbeiter);
        maRepository.maHinzufuegen(experte1);
        maRepository.maHinzufuegen(experte2);
        maRepository.maHinzufuegen(manager1);
        maRepository.maHinzufuegen(manager2);
    }


/*    public static void maGehaltErhoehen(int mitarbeiterID, double prozent) {
        try {
            double neuerGehalt = maRepository.maGehaltErhoehen(mitarbeiterID, prozent);
            System.out.printf("\tNeuer Gehalt für id: %d: %f\n", mitarbeiterID, neuerGehalt);
            maAusgeben(mitarbeiterID);
        } catch (Exception e) {
            System.out.println("\t-- ACHTUNG! Fehler bei der Lohnerhöhung für einen Mitarbeiter --");
            System.out.printf("\t-- Mitarbeiter mit id %d wurde nicht gefunden (und ist möglicheweise bereits ausgeschieden) \n", mitarbeiterID);
        }
    }*/

    public static void maHinzufuegen(Mitarbeiter ersatzExperte) {
        maRepository.maHinzufuegen(ersatzExperte);
        System.out.printf("\tfolgender Esperte wurde eingstellt: \t%s\n", ersatzExperte);
    }

/*    public static void maAusgeben(int mitarbeiterID) {
        try {
            maVerwaltung.maAusgeben(mitarbeiterID);
        } catch (Exception e) {
            System.out.println("\t-- ACHTUNG! Fehler bei der Ausgabe für einen Mitarbeiter --");
            System.out.printf("\t-- Mitarbeiter mit id %d wurde nicht gefunden (und ist möglicheweise bereits ausgeschieden) \n", mitarbeiterID);
        }
    }*/

    public static void maAusscheiden(int mitarbeiterID) {
        try {
            Mitarbeiter tmpMa = maRepository.maAusscheiden(mitarbeiterID);
            System.out.println("\tfolgender Mitarbeiter wurde ausgeschieden:");
            System.out.printf("\t\t%s\n", tmpMa);
        } catch (Exception e) {
            System.out.println("\t-- ACHTUNG! Fehler beim Ausscheiden eines Mitarbeiter --");
            System.out.printf("\t-- Mitarbeiter mit id %d wurde nicht gefunden (und ist möglicheweise bereits ausgeschieden) \n", mitarbeiterID);
        }
    }
}
