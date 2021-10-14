package fitnesscenter3_enum_my;

// import java.util.Scanner;

import fitnesscenter2.Wertkarte;

public class FitnesscenterProgrammEnum {

    public static void main(String[] args) {
        // sout
        // souf ffür printf
        System.out.println("fitnesscenter2.Fitnesszenter2");
        System.out.printf("");

        //        Statt Erzeugen mit Default Konsruktor + setzen(), direkt die Pameer an den Konstuktor übergeben.
        Wertkarte karte1 = new Wertkarte(1, "Maxi");

//        karte1.setzen(1, "Maxi");
        karte1.anzeigen();

        // abbuchen ei Guthaben 0
        bucheAbFitnes(karte1);

        bucheAuf(karte1, 160);
        bucheAuf(karte1,160);
        bucheAuf(karte1,100);

        bucheAbFitnes(karte1);

//        Scanner scanner = new Scanner(System.in);

        System.out.println("Nummer: " + karte1.getKartennummer());
        System.out.println("Guthaben: " + karte1.getGuthaben());
        System.out.println("Inhaber: " + karte1.getInhaber());

//        Inhabername ändern
        karte1.setInhaber("Moritz");
        karte1.anzeigen();
    }

    static void bucheAuf(Wertkarte karte, double betrag) {
        try {
            System.out.println("Aufbuchen");
            System.out.print("Vorher");
            karte.anzeigen();
            karte.aufbuchen(betrag);
        } catch(IllegalArgumentException e) {
            System.out.println("Fehler reim Aufbauchen " + e.getMessage());
        }
        System.out.print("Nachher");
        karte.anzeigen();
        System.out.println("");
    }

    static void bucheAbFitnes(Wertkarte karte) {
        try {
            System.out.println("Abbuchen");
            System.out.print("Vorher");
            karte.anzeigen();
            karte.abbuchenFitness2h();
        } catch(IllegalArgumentException e) {
            System.out.println("Fehler reim Abbuchen " + e.getMessage());
        }
        System.out.print("Nachher");
        karte.anzeigen();
        System.out.println("");
    }

    static void bucheAb(Wertkarte karte, Leistungen leistung) {
        try {
            System.out.println("Abbuchen");
            System.out.print("Vorher");
            karte.anzeigen();
            karte.abbuchenFitness2h();
        } catch(IllegalArgumentException e) {
            System.out.println("Fehler reim Abbuchen " + e.getMessage());
        }
        System.out.print("Nachher");
        karte.anzeigen();
        System.out.println("");
    }

    private void bucheAb( Leistungen leistung) {
        double betrag = switch (leistung) {
            case GYMNASTIk -> 7.0;
            case FINESS_2H -> 14.0;
            case FINESS_TAG -> 19.0;
            case GYMNASTIk_SPECIAL -> 9.0;
            case WELLNESS -> 15.0;
            default -> throw new IllegalArgumentException("unbekannte Leistung");
        };

//        if (guthaben - betrag < 0) {
//
//        }
    }

}
