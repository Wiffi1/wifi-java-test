package myTierePlayground;

import java.time.LocalDate;

public class TiereDemo2 {
    public static void main(String[] args) {
        Haustier minna =  new Haustier("Minna", LocalDate.of(2005, 12, 3));
        minna.zeigeDich();
        System.out.printf("%s ist %d Jahre alt\n", minna.getKosename(), minna.getAlter());

        Hund rex = new Hund("Rex", LocalDate.of(2015, 12, 30), 21);
        // Methoden der Basisklasse
        // Getter aus Hund
//        rex.setGewicht(21);
        // Methode der Basisklasse
        rex.zeigeDich();
        // Methode aus der Hund-Klasse
        rex.belle();
        System.out.printf("%s ist %d Jahre alt\n", rex.getKosename(), rex.getAlter());
    }
}
