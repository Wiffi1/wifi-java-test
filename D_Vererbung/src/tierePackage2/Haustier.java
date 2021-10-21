package tierePackage2;

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

    public void setGeburtsdatum(LocalDate geburtsdatum) {
        this.geburtsdatum = geburtsdatum;
    }

    public String getKosename() {
        return kosename;
    }

    public void setKosename(String kosename) {
        this.kosename = kosename;
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

    public int getAlter() {
        LocalDate heute = LocalDate.now();
        Period spanne = Period.between(geburtsdatum, heute);
        // Die Property Jahre aus dem Ergebnis zurückliefern
        return spanne.getYears();
    }

}
