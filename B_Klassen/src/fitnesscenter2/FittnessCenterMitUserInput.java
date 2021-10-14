package fitnesscenter2;

import inputHilfe.InputUtil;

public class FittnessCenterMitUserInput {
    public static void main(String[] args) {
        System.out.println("1. Karte erzeugen;");
        Wertkarte karte1 = erzeugeKarte();
//        karte1.anzeigen();;
        bucheAuf(karte1);

        System.out.println("2F. Karte erzeugen;");
        Wertkarte karte2 = erzeugeKarte();
//        karte2.anzeigen();;
        bucheAuf(karte2);

        System.out.println("Alle Karten;");
        karte1.anzeigen();;
        karte2.anzeigen();;
    }

    static Wertkarte erzeugeKarte() {
        System.out.println("Kundenname: ");
        String name = InputUtil.readString();
        System.out.println("Kartennummer: ");
        int nr = InputUtil.readInt();
/*        Wertkarte karte = new Wertkarte(nr, name);
        return karte;*/
        return new Wertkarte(nr, name);
    }

    static void bucheAuf(Wertkarte karte) {
        try {
            System.out.println("Aufbuchen");
            System.out.print("Vorher");
            karte.anzeigen();

            // Betrag vom User eingeben
            System.out.println("Welchen Betrag aufbuchen?");
            double betrag = InputUtil.readDouble();
            karte.aufbuchen(betrag);
        } catch(IllegalArgumentException e) {
            System.out.println("Fehler reim Aufbauchen " + e.getMessage());
        }
        System.out.print("Nachher");
        karte.anzeigen();
        System.out.println("");
    }
}
