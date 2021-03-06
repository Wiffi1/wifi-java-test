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

package mitarbeiterVerwaltungFxml;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;

public class Mitarbeiter implements Serializable {

    private static final long serialVersionUID = 2L;

    static private int anzMonatsgehaelter = 12;

    private static int idCount = 0;

    private int id;
    private String name;
    private LocalDate geburtsdatum;
    private LocalDate eintrittsdatum;
    private double grundgehalt;

    public static int getNextId() {
        return ++idCount;
    }

    public Mitarbeiter(String name, LocalDate geburtsdatum, LocalDate eintrittsdatum, double grundgehalt) {
        this.id = ++idCount;
        this.name = name;
        this.eintrittsdatum = eintrittsdatum;
        this.geburtsdatum = geburtsdatum;
        this.grundgehalt = grundgehalt;
    }

    public static void initNextId(int readInt) {
        idCount = readInt;
    }

    public String getType() {
//        System.out.println("getClass().getName() " + getClass().getName());
        return getClass().getName();
    }

    public double getMonatsgehalt() {
        return this.grundgehalt;
    }
    double getJahresgehalt() {
        return this.grundgehalt * 12;
    }
    void gehaltserhoehung(double betrag) {}

//    public static int getAnzMonatsgehaelter() {
//        return anzMonatsgehaelter;
//    }

    private void setGrundgehalt(double grundgehalt) {
        this.grundgehalt = grundgehalt;
    }

    /*** getter ****/
    public String getName() {
        return name;
    }
    public LocalDate getGeburtsdatum() {
        return geburtsdatum;
    }
    public LocalDate getEintrittsdatum() {
        return eintrittsdatum;
    }
    public double getGrundgehalt() {
        return grundgehalt;
    }
    public int getId() { return id;}


    public double getMonatsGehalt() {
        return getGrundgehalt();
    }

    public double maGehaltErhoehen(double prozent) {
        return this.grundgehalt = this.grundgehalt * ((100 + prozent) / 100);
    }

    Period getAnstellungsdauer() {
        LocalDate now = LocalDate.now();
        return Period.between(eintrittsdatum, now);
    }

    void mitarbeiterblatt()  {
    }

    @Override
    public String toString() {
        return String.format("id: %2d, Name: %-20s Grundgeh: %8.2f Gebdatum: %s Eintritt: %s",
                id, name, grundgehalt, geburtsdatum, eintrittsdatum);
    }

}
