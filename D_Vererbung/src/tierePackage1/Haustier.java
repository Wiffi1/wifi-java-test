package tierePackage1;

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

    public Haustier() {
        System.out.println("Konstructor von Haustier");
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
