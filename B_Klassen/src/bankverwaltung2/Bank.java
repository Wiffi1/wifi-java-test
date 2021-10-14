package bankverwaltung2;

//import fitnesscenter.Wertkarte;

public class Bank {
    public static void main(String[] args) {
        System.out.println("Bank wird eröffet");
        System.out.println("");

        try {
            Bankkonto konto1 = new Bankkonto("FI1", 1.1, 300);
            konto1.abheben(200);
            konto1.anzeigen();
        } catch(IllegalArgumentException e) {
            System.err.println("Fehler bei der Sparbucheröffnung " +  e.getMessage());
        }

        try {
            Bankkonto konto2 = new Bankkonto("FI2", 2.2, 200);
        } catch(IllegalArgumentException e) {
            System.err.println("Fehler bei der Sparbucheröffnung " +  e.getMessage());
        }

        try {
            Bankkonto konto3 = new Bankkonto("FI3", 2000);
            konto3.abheben(200);
        } catch(IllegalArgumentException e) {
            System.err.println("Fehler bei der Kontoeröffnung " + e.getMessage());
        }

        try {
            Bankkonto konto2 = new Bankkonto("FI2", 3.3, 200000);
        } catch(IllegalArgumentException e) {
            System.err.println("Fehler bei der Sparbucheröffnung " +  e.getMessage());
        }


    }
}
