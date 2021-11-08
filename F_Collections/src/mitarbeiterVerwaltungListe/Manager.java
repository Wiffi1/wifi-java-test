package mitarbeiterVerwaltungListe;

//Manager erhalten jährlich einen Bonus, welcher bei der Gehaltserhöhung (s.u.) um denselben Prozentsatz wie das Gehalt erhöht wird.

import java.time.LocalDate;

public class Manager extends Mitarbeiter {
    // Höhe des Bonus ist Verhandlungssache, deshalb nicht static gnommen.
    private double bonus;

    public Manager(String name, LocalDate geburtsdatum, LocalDate eintrittsdatum, double grundgehalt, double bonus) {
        super(name, geburtsdatum, eintrittsdatum, grundgehalt);
        this.bonus = bonus;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" Einstufung: %-10s Bonus: %8.2f",
                "Manager", bonus);
    }
}
