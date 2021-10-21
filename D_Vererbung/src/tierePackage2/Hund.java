package tierePackage2;

import java.time.LocalDate;

// Klasse Hund leittet von Haustier ab
public class Hund extends Haustier {
    private int gewicht;

    public int getGewicht() {
        return gewicht;
    }

    public void setGewicht(int gewicht) {
        this.gewicht = gewicht;
    }

    public Hund(String name, LocalDate datum, int gewicht) {
        super(name, datum); // Aufruf des Basisklassen-Konstruktors, wird auch implizit ausgeführt.
        this.gewicht = gewicht;
        System.out.println("Konstructor von Hund");
    }

    public void belle() {
        System.out.printf("%s macht wau wau! \n", getKosename());
    }


}
