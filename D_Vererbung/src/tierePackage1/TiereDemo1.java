package tierePackage1;

import java.time.LocalDate;

public class TiereDemo1 {
    public static void main(String[] args) {
        Haustier minna =  new Haustier();
        minna.setKosename("Minna");
        minna.setGeburtsdatum(LocalDate.of(2005, 12, 3));
        // Wäre auch möglich, weil im selben Package
//        minna.geburtsdatum = LocalDate.of(2005, 12, 3);
        minna.zeigeDich();
        System.out.printf("%s ist %d Jahre alt\n", minna.getKosename(), minna.getAlter());

        Hund rex = new Hund();
        // Methoden der Basisklasse
        rex.setKosename("Rex");
        rex.setGeburtsdatum(LocalDate.of(2015, 12, 30));
        // Getter aus Hund
        rex.setGewicht(21);
        // Methode der Basisklasse
        rex.zeigeDich();
        // Methode aus der Hund-Klasse
        rex.belle();
        System.out.printf("%s ist %d Jahre alt\n", rex.getKosename(), rex.getAlter());
    }
}
