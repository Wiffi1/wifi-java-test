package fitnesscenter2;

import inputHilfe.InputUtil;

public class FittnessCenterMitUserInput {
    public static void main(String[] args) {
        System.out.println("1. Karte erzeugen;");
//        Wertkarte karte = new Wertkarte()
        Wertkarte karte1 = erzeugeKarte();
        karte1.anzeigen();
    }

    static Wertkarte erzeugeKarte() {
        System.out.println("Kundennae;");
        String name = InputUtil.readString();
        System.out.println("Kartnnummer;");
        int nr = InputUtil.readInt();
/*        Wertkarte karte = new Wertkarte(nr, name);
        return karte;*/
        return new Wertkarte(nr, name);
    }
}
