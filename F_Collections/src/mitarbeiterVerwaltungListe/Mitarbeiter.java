package mitarbeiterVerwaltungListe;

import java.time.LocalDate;

public class Mitarbeiter {
//    private int id;
    private String name;
    private LocalDate geburtsdatum;
    private LocalDate eintrittsdatum;
    private double grundgehalt;

    Mitarbeiter(String name, LocalDate geburtsdatum, LocalDate eintrittsdatum, double grundgehalt) {
        this.name = name;
        this.eintrittsdatum = eintrittsdatum;
        this.geburtsdatum = geburtsdatum;
        this.grundgehalt = grundgehalt;
    }

    public double getMonatsgehalt() {
        return -1;
    };
    double getJahresgehalt() {
        return -1;
    };
    void gehaltserhöhung(double betrag) {};
    int getAnstellungsdauer() {
        return 1;
    }
    void mitarbeiterblatt()  {};
}
