package bankverwaltung.oopSort;

import animals.sort.Animal;

import java.util.Arrays;
import java.util.Comparator;

public class BankProgramCompare {
    static Bankkonto[] bankkonten = new Bankkonto[10];

    public static void main(String[] args) {
        makeEndTestBankkonten();
    }

    private static void makeEndTestBankkonten() {
        bankkonten = new  Bankkonto[] {
            eroeffnen("Angestellter2", 1.5, 100),
            eroeffnen("Tick", 1.5, 1000),
            eroeffnen("Trick", 2000),
            eroeffnen("Track", 2000),
            eroeffnen("Zzzack", 1.5, 100),
            eroeffnen("Angestellter1", 1.5, 200),
            eroeffnen("Bausparer", 20000),
        };

        bankkonten[2].einzahlen(123);
        bankkonten[3].einzahlen(1123);

        System.out.println();
        System.out.println("unsortiert");
        alleKontenAusgeben(true);
        System.out.println();

        System.out.println("sortiert nach Namen");
        Arrays.sort(bankkonten);
        alleKontenAusgeben(true);
        System.out.println();
        System.out.println("sortiert nach Type und Kontostand");
        Arrays.sort(bankkonten, new SortiereNachTypUndKontostand());
        alleKontenAusgeben(false);

        System.out.println();
        System.out.println("sortiert nach Type und Kontostand mit Lambda-Comparator, geänderte Reihenfolge");
        Comparator<Bankkonto> comparatorLambda1 = (o1, o2) -> {
            int ret = -o1.getKontotyp().compareTo(o2.getKontotyp());
            if (ret == 0) {
                ret = -Double.compare(o2.getKontostand(), o1.getKontostand());
            }
            return ret;
        };
        Arrays.sort(bankkonten, comparatorLambda1);
        alleKontenAusgeben(false);

        System.out.println();
        System.out.println("Anonyme interface implementierung");
        Comparator<Bankkonto> comparatorAnonym = new Comparator<Bankkonto>() {
            @Override
            public int compare(Bankkonto o1, Bankkonto o2) {
                return 0;
            }
        };
        Arrays.sort(bankkonten, comparatorAnonym);
        alleKontenAusgeben(false);

        System.out.println();
        System.out.println("Anonyme interface implementierung - direkt");
        Arrays.sort(bankkonten, new Comparator<Bankkonto>() {
            @Override
            public int compare(Bankkonto o1, Bankkonto o2) {
                return 0;
            }
        });
        alleKontenAusgeben(false);

        System.out.println();
        System.out.println("sortiert nach Type (aufsteigend) und Kontostand (absteigend) mit Methoden-Referenzen");
        Comparator<Bankkonto> comparator;
        comparator = Comparator
                .comparing(Bankkonto::getKontotyp)
                .reversed()
                .thenComparing(Bankkonto::getKontostand)
                .reversed();
        // Frage an Michaele, gibt es eine Möglichkeit, das reversed() nur auf das thenComparing::getKontostand anzuwenden?
        // Das zweite reverse() ändert nämlich natürlich die Gesamtreihenfolge, ich wollte aber den Kontotyp aufsteigend
        // und den Kontostand absteigend sortieren. Das scheint zwar mit den beiden reverse() zu funktionieren,
        // ist aber nicht sehr schön

        Arrays.sort(bankkonten, comparator);
        alleKontenAusgeben(false);


        System.out.println();
        System.out.println("sortiert nach Kontonummer absteigend");
        Comparator<Bankkonto> comparatorLambda2 = (o1, o2) -> -Double.compare(o1.getKontonummer(), o2.getKontonummer());
        Arrays.sort(bankkonten, comparatorLambda2);
        alleKontenAusgeben(false);
    }

    private static void alleKontenAusgeben(boolean isNaturalSort) {
        for (Bankkonto bankkonto: bankkonten) {
            zeigeAnShort(bankkonto, isNaturalSort);
        }
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

    private static void zeigeAnShort(Bankkonto konto, boolean isNaturalSort){
        if (isNaturalSort) {
            System.out.printf("\t%-20s %-20s Nr: %d   %10.3f \n", konto.getInhaber(), konto.getKontotyp(), konto.getKontonummer(), konto.getKontostand());
        } else  {
            System.out.printf("\t%-20s %10.3f   Nr: %d   %-20s   \n", konto.getKontotyp(), konto.getKontostand(), konto.getKontonummer(), konto.getInhaber());
        }
    }

}

