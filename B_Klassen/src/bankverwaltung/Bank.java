package bankverwaltung;

//import fitnesscenter.Wertkarte;

public class Bank {
    public static void main(String[] args) {
        System.out.println("Bank");
        System.out.printf("");

//        sparbuchEroeffnen(int kontonummer, String  inhaber, double zinssatz, double ersteinlage);

        Bankkonto konto1 = new Bankkonto();
        konto1.sparbuchEroeffnen(111, "Franz Irnberger", 2, 300);

        try {
            Bankkonto konto2 = new Bankkonto();
            konto2.sparbuchEroeffnen(222, "Franz Irnberger", 2, 20000);
        } catch(IllegalArgumentException e) {
            System.out.println("Fehler bei der Sparbucheröffnen " +  e.getMessage());
        }

        try {
            Bankkonto konto3 = new Bankkonto();
            konto3.gehaltskontoEroeffnen(333, "Sebastian Irnberger", 2000);
        } catch(IllegalArgumentException e) {
            System.out.println("Fehler bei der Kontoeröffnung " + e.getMessage());
        }

    }
}
