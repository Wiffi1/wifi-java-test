package bankverwaltung2;

//import fitnesscenter.Wertkarte;

public class Bank {
    public static void main(String[] args) {
        System.out.println("Bank");
        System.out.printf("");

        Bankkonto konto1 = new Bankkonto(111, "Franz Irnberger", 2, 300);
//        konto1.sparbuchEroeffnen(111, "Franz Irnberger", 2, 300);

        try {
            Bankkonto konto2 = new Bankkonto(222, "Franz Irnberger", 2, 20000);
//            konto2.sparbuchEroeffnen(222, "Franz Irnberger zu hoch", 2, 20000);
        } catch(IllegalArgumentException e) {
            System.err.println("Fehler bei der Sparbucheröffnung " +  e.getMessage());
        }

        try {
            Bankkonto konto3 = new Bankkonto(333, "Sebastian Irnberger", 2000);
//            konto3.gehaltskontoEroeffnen(333, "Sebastian Irnberger", 2000);
        } catch(IllegalArgumentException e) {
            System.err.println("Fehler bei der Kontoeröffnung " + e.getMessage());
        }

    }
}
