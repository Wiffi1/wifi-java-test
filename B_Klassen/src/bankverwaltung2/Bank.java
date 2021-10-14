package bankverwaltung2;

//import fitnesscenter.Wertkarte;

public class Bank {
    public static void main(String[] args) {
        System.out.println("Bank");
        System.out.printf("");

//        int aktuelleKontoNr = ;

        Bankkonto konto1 = new Bankkonto(Bankkonto.GetObjectCount(), "FI1", 2, 300);

        try {
            Bankkonto konto2 = new Bankkonto(Bankkonto.GetObjectCount(), "FI2", 2, 200);
        } catch(IllegalArgumentException e) {
            System.err.println("Fehler bei der Sparbucheröffnung " +  e.getMessage());
        }

        try {
            Bankkonto konto3 = new Bankkonto(Bankkonto.GetObjectCount(), "FI3", 200000);
        } catch(IllegalArgumentException e) {
            System.err.println("Fehler bei der Kontoeröffnung " + e.getMessage());
        }

    }
}
