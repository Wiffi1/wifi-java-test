package mitarbeiterVerwaltungListe;

import java.time.LocalDate;
import java.time.Period;

public class MitarbeiterDemo {
    // Basis-Mitarbeiterfunktionalität aus Zeitmangel nur rudimentär ausprogrammiert - siehe auch MitarbeiterVerwaltungsDemo
    public static void main(String[] args) {
        Mitarbeiter experte1 = new Experte("Gustav Expert", LocalDate.of(2002, 2, 2), LocalDate.of(2020, 11, 2), 2000.0, "c#");
        Period p = experte1.getAnstellungsdauer();

        int y = p.getYears();
        int m = p.getMonths();
        int d = p.getDays();

        System.out.printf("Der Mitarbeiter war %d Jahre, %d Monate und %d Tage angestellt", y, m, d);
    }
}
