package bankverwaltung.oopSort;

import java.util.Arrays;

public class BankProgramm {

    static Bankkonto[] bankkonten = new Bankkonto[10];

    public static void main(String[] args) {

        Bankkonto sparkonto1 = eroeffnen("Tick", 1.5, 1000);
        Bankkonto gehaltskonto1 = eroeffnen("Trick", 2000);

        System.out.println("\nAlle Konten:");
        sparkonto1.kontoInformation();
        gehaltskonto1.kontoInformation();
        System.out.println();

        System.out.println("Tests mit Konto " + sparkonto1.getKontonummer());
        abheben(sparkonto1, 500);
        einzahlen(sparkonto1, 700);
        abheben(sparkonto1, 1250);
        einzahlen(sparkonto1, 15001);
        System.out.println();

        System.out.println("Tests mit Konto " + gehaltskonto1.getKontonummer());
        abheben(gehaltskonto1, 1500);
        einzahlen(gehaltskonto1, 15000);
        einzahlen(gehaltskonto1, 15001);
        System.out.println();

        System.out.println("Getter und Setter");
        sparkonto1.setInhaber("Track");
        zeigeAn(sparkonto1);
        gehaltskonto1.setUeberziehungsRahmen(3000);
        zeigeAn(sparkonto1);


        makeEndTestBankkonten();

    }

    private static void makeEndTestBankkonten() {
        bankkonten = new  Bankkonto[] {
            eroeffnen("Tick", 1.5, 1000),
            eroeffnen("Trick", 2000),
            eroeffnen("Track", 2000),
        };

        System.out.printf("%s\n", bankkonten.toString());
        Arrays.sort(bankkonten);
        System.out.printf("%s\n", bankkonten.toString());
    }

    private static Bankkonto eroeffnen(String inhaber, double zinssatz, double einlage) {
        try {
            System.out.println("Eröffne Sparkonto");
            Bankkonto konto = new Bankkonto(inhaber, zinssatz, einlage);
            konto.kontoInformation();
            return konto;
        } catch (IllegalArgumentException e) {
            System.out.println("FEHLER beim Erzeugen: " + e.getMessage());
            return null;
        }
    }

    private static Bankkonto eroeffnen(String inhaber, double uzRahmen) {
        try {
            System.out.println("Eröffne Gehaltskonto");
            Bankkonto konto = new Bankkonto(inhaber, uzRahmen);
            konto.kontoInformation();
            return konto;
        } catch (IllegalArgumentException e) {
            System.out.println("FEHLER beim Erzeugen: " + e.getMessage());
            return null;
        }
    }

    private static void einzahlen(Bankkonto konto, double betrag) {
        try{
            konto.einzahlen(betrag);
            System.out.printf("Einzahlung von %.2f erfolgreich: \n", betrag);
            konto.kontoInformation();
        }catch (IllegalArgumentException e){
            System.out.println("FEHLER beim Einzahlen:" + e.getMessage());
            System.out.printf("Einzahlung von %.2f fehlgeschlagen! \n", betrag);
        }
        System.out.println();

    }

    private static void abheben(Bankkonto konto, double betrag) {
        try{
            konto.abheben(betrag);
            System.out.printf("Abhebung von %.2f erfolgreich: \n", betrag);
            konto.kontoInformation();
        }catch (IllegalArgumentException e){
            System.out.println("FEHLER beim Einzahlen:" + e.getMessage());
            System.out.printf("Abhebung von %.2f fehlgeschlagen! \n", betrag);
        }
        System.out.println();
    }

    private static void zeigeAn(Bankkonto konto){
        System.out.println(konto.getKontotyp());
        System.out.printf("Nr: %d\n", konto.getKontonummer());
        System.out.printf("Inhaber: %s\n", konto.getInhaber());
        System.out.printf("Kontostand: %.2f\n", konto.getKontostand());
        System.out.printf("Zinssatz: %.2f\n", konto.getZinssatz());
        System.out.printf("Überz.Rahmen: %.2f\n", konto.getUeberziehungsRahmen());
        System.out.println();
    }

}
