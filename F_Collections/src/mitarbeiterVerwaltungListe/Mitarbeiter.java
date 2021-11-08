package mitarbeiterVerwaltungListe;

import java.time.LocalDate;
import java.time.Period;

public class Mitarbeiter {

    static private int anzMonatsgehaelter = 12;

    private static int idCount = 0;

    private int id;
    private String name;
    private LocalDate geburtsdatum;
    private LocalDate eintrittsdatum;
    private double grundgehalt;

    Mitarbeiter(String name, LocalDate geburtsdatum, LocalDate eintrittsdatum, double grundgehalt) {
        this.id = ++idCount;
        this.name = name;
        this.eintrittsdatum = eintrittsdatum;
        this.geburtsdatum = geburtsdatum;
        this.grundgehalt = grundgehalt;
    }

    public double getMonatsgehalt() {
        return this.grundgehalt;
    }
    double getJahresgehalt() {
        return this.grundgehalt * 12;
    }
    void gehaltserhoehung(double betrag) {}

    public static int getAnzMonatsgehaelter() {
        return anzMonatsgehaelter;
    }

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

    Period getAnstellungsdauer() {
        LocalDate now = LocalDate.now();
        LocalDate endDate = LocalDate.of(now.getYear(), 12, 24);
//        Period p = Period.between(now, endDate);
//        int y = p.getYears();
//        int m = p.getMonths();
//        int d = p.getDays();
        return Period.between(now, endDate);
    }

    void mitarbeiterblatt()  {
//        String ma = "name: " + this.getName() + "";
    }

    @Override
    public String toString() {
        return "Mitarbeiter{" +
                "name='" + name + '\'' +
                ", geburtsdatum=" + geburtsdatum +
                ", eintrittsdatum=" + eintrittsdatum +
                ", grundgehalt=" + grundgehalt +
                '}';
    }

}
