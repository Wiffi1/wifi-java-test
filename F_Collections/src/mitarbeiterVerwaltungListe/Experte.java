package mitarbeiterVerwaltungListe;

import java.time.LocalDate;

// Experten verfügen über ein Fachgebiet (String) und erhalten das Gehalt 15x jährlich
public class Experte extends Mitarbeiter {
//public class Experte {

    private String fachgebiet;
    static private int anzMonatsgehaelter = 15;

    public Experte(String name, LocalDate geburtsdatum, LocalDate eintrittsdatum, double grundgehalt, String fachgebiet) {
        super(name, geburtsdatum, eintrittsdatum, grundgehalt);
        this.fachgebiet = fachgebiet;
    }

    @Override
    double getJahresgehalt() {
//        return super.getJahresgehalt();
        return getMonatsgehalt() * 14;
    }

    @Override
    public String toString() {
        return super.toString() + " Experte " +
                "fachgebiet='" + fachgebiet + '\'';
    }
}
