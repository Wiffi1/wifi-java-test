package mitarbeiterVerwaltungListe;
/*
        Ein Unternehmen beschäftigt unterschiedliche Arten von Mitarbeitern. Zu jedem Mitarbeiter werden folgende Informationen benötigt:

        Fortlaufende eindeutige Mitarbeiter-ID
        Name
        Geburtsdatum
        Eintrittsdatum
        Grundgehalt (wird 14x pro Jahr ausgezahlt)
        Für weitere Mitarbeitertypen gelten folgende Besonderheiten:

        Manager erhalten jährlich einen Bonus, welcher bei der Gehaltserhöhung (s.u.) um denselben Prozentsatz wie das Gehalt erhöht wird.
        Experten verfügen über ein Fachgebiet (String) und erhalten das Gehalt 15x jährlich
        Folgende Funktionen sollen zur Verfügung stehen:

        Monatsgehalt: das Monatsgehalt wird berechnet
        Jahresgehalt: das Jahresgehalt wird berechnet
        Gehaltserhöhung: das Grundgehalt wird um einen Prozentsatz erhöht

        https://www.java-blog-buch.de/d-differenz-zweier-daten-jdk-11/
        https://javabeginners.de/Datum_und_Zeit/Datums-Differenz_berechnen.php

        Anstellungsdauer: die Dauer des Anstellungs-Verhältnisses wird berechnet
        Mitarbeiterblatt: alle Informationen werden ausgegeben (Konsole)
        Erstelle ein Klassendiagramm bevor du mit der Implementierung beginnst.

        Teste die Klassen mit einem passenden Testprogramm!
        */

import java.time.LocalDate;

public class MitarbeiterVerwaltungsDemo {

    private static MitarbeiterListe mitarbeiterListe = new MitarbeiterListe();

    public static void main(String[] args) {
//        MitarbeiterVerwaltung mitarbeiterListe = new MitarbeiterVerwaltung();

        Experte experte1 = new Experte("Gustav Expert1", LocalDate.of(2001, 1, 1), LocalDate.of(2010, 1, 1), 1000.0, "Java1");
        Experte experte2 = new Experte("Gustav Expert2", LocalDate.of(2002, 2, 2), LocalDate.of(2012, 2, 2), 2000.0, "Java2");
        Manager manager1 = new Manager("Franz Manager1",  LocalDate.of(2002, 2, 2), LocalDate.of(2012, 2, 2), 2000.0, 1000);
        Manager manager2 = new Manager("Franz Manager2",  LocalDate.of(2002, 2, 2), LocalDate.of(2012, 2, 2), 2000.0, 1000);

        mitarbeiterListe.test();
        mitarbeiterListe.mAhinzufuegen(experte1);
        mitarbeiterListe.mAabrufen(12);
//        mitarbeiterListe.alleAnzeigen();

    }

}
