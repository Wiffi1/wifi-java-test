package myTierePlayground;

import java.time.LocalDate;
import java.time.Period;

// Basisklasse für Haustier (Hund, Katze, etc...)
public class Haustier {
    // protected: Klasse, abgeleiete Klassen und alle Klassen im selben Package können zugreifen.
    protected String kosename;
    protected LocalDate geburtsdatum;

    public LocalDate getGeburtsdatum() {
        return geburtsdatum;
    }
    public final String getKosename() {
        return kosename;
    }

    public static String ichEsse() {
        return "unbekannte Nahrung";
    }

    // Attribute bei der Erzeugung initialsieren
    public Haustier(String name, LocalDate datum) {
        System.out.println("Konstructor von Haustier");
        this.geburtsdatum = datum;
        this.kosename = name;
    }

    public void zeigeDich() {
        System.out.printf("Hallo. mein Name ist %s, mein Geburtsdatum ist %s\n", kosename, geburtsdatum);
    }

    public final int getAlter() {
        LocalDate heute = LocalDate.now();
        Period spanne = Period.between(geburtsdatum, heute);
        // Die Property Jahre aus dem Ergebnis zurückliefern
        return spanne.getYears();
    }

    @Override
    public String toString() {
/*        return "Haustier{" +
                "kosename='" + kosename + '\'' +
                ", geburtsdatum=" + geburtsdatum +
                '}';*/
        return "!!!kosename='" + kosename + '\'' + " geburtsdatum=" + geburtsdatum;
    }

    private void schreie() {
        System.out.printf("Unbekannter Laut %s\n", kosename, geburtsdatum);
    }
}
