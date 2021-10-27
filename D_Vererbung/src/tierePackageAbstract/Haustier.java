package tierePackageAbstract;

import java.time.LocalDate;
import java.time.Period;

// Basisklasse für Haustier (Hund, Katze, etc...)
public abstract class Haustier {
    // protected: Klasse, abgeleiete Klassen und alle Klassen im selben Package können zugreifen.
    protected String kosename;
    protected LocalDate geburtsdatum;

    public LocalDate getGeburtsdatum() {
        return geburtsdatum;
    }
    public final String getKosename() {
        return kosename;
    }

    // Attribute bei der Erzeugung initialsieren
    protected Haustier(String name, LocalDate datum) {
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

    // bewegDich als abstracte Method
    // hiat hier keien IMprlemenierung, kann aber über eine AHaustier-Referrenz
    // aufgerufen werden
    public abstract void bewegDich();

/*
    public  void bewegDich()  {
        System.out.printf("%s bewegt sich??? \n", kosename);
    }
*/
}
