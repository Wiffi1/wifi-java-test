package mitarbeiterVerwaltungListe;

import java.time.LocalDate;

public class MitarbeiterVerwaltungsDemo {

    private static MitarbeiterListe mitarbeiterListe = new MitarbeiterListe();

    public static void main(String[] args) {

        Mitarbeiter experte1 = new Experte("Gustav Expert", LocalDate.of(2002, 2, 2), LocalDate.of(2012, 2, 2), 2000.0, "c#");
        Mitarbeiter experte2 = new Experte("Daniel Düsentrieb", LocalDate.of(2001, 1, 1), LocalDate.of(2010, 1, 1), 1000.0, "Java1");
        Mitarbeiter manager1 = new Manager("Franz I Manager1",  LocalDate.of(2002, 2, 2), LocalDate.of(2012, 2, 2), 2000.0, 1000);
        Mitarbeiter manager2 = new Manager("Franz II Manager2",  LocalDate.of(2002, 2, 2), LocalDate.of(2012, 2, 2), 2000.0, 2000);

        mitarbeiterListe.test();
        mitarbeiterListe.mAhinzufuegen(experte1);

        mitarbeiterListe.alleAnzeigen("");
        mitarbeiterListe.mAhinzufuegen(experte2);
        mitarbeiterListe.mAhinzufuegen(manager1);
        mitarbeiterListe.mAhinzufuegen(manager2);

        mitarbeiterListe.alleAnzeigen("");

//        mitarbeiterListe.mAausgeben(2);

        System.out.println("Mitarbeiter Experte2 wird ausgeschieden");
        Mitarbeiter ausgeschieden = mitarbeiterListe.maAusscheiden(experte2.getId());
        System.out.printf("\nfolgender Mitarbeiter wurde ausgeschieden: %s\n", ausgeschieden);

        Mitarbeiter ersatzExperte = new Experte("Franz III Manager2",  LocalDate.of(2002, 2, 2), LocalDate.of(2012, 2, 2), 2000.0, "Typescript");
        mitarbeiterListe.mAhinzufuegen(ersatzExperte);

        mitarbeiterListe.maGehaltErhoehen(experte2.getId(),10);

        // id == -1, da Mitarbeiter bereits ausgeschieden ist
        // mitarbeiterListe.mAausgeben(ausgeschieden.getId());
        System.out.println("dddddddddddddd");
        mitarbeiterListe.alleAnzeigen("");

        //        Mitarbeiter ma = mitarbeiterListe.testGetMaByIndex(0);
        //        System.out.printf("%s", ma);

    }

}
