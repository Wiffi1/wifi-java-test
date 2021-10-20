package lotto;

import inputHilfe.InputUtil;

import java.util.Arrays;
import java.util.Random;

public class Lottotipp2 {
    // statt einzelner Werte
    //    private int zahl1, zahl2

    // Ein Array für die 6 Lottouahlen erstellen
    private int[] tippZahlen = new int[6];
    private Random zufall = new Random();

//    private int[] tips = new[6];

    public Lottotipp2(char typ) {
        if (typ == 'Q') {
            quicktipp();
        } else {
            manuellerTipp();
        }
    }

    private void quicktipp() {
//    int zufallsWert = zufall.nextInt(45); // Wert zwischn 0 und 49
//    werte[i] = zufallsWert + 20 ; // Werte zwischen 20 und 69
        // TODO: Quicktipp implementieren
        for (int i = 0; i < tippZahlen.length; i++) {
            tippZahlen[i] = zufall.nextInt(45) + 1;

//            if
//            i = --1;
        }
        Arrays.sort(tippZahlen);
        ausgeben();
    }

    private void manuellerTipp() {
        // TODO:  unterschiedliche Zahlen vom Benuzer einlesen
        System.out.println("");
        System.out.println("Manueller Tipp\n");

        for (int i = 0; i < tippZahlen.length; i++) {
            int zahl = InputUtil.readInt();
            tippZahlen[i] = zahl;
        }
        Arrays.sort(tippZahlen);
        ausgeben();
    }

    private void ausgeben() {
        System.out.printf("Die Tipps lauten: ");
        System.out.printf("[");
        for (int tipzahl : tippZahlen) {
            System.out.printf("%d ", tipzahl);
        }
        System.out.printf("]");
    }

    public int[] getTippZahlen() {
        // nicht das Attribut (die Referenz) selber zurückliefern sondern eine Kopie.
        // return tippZahlen
        return Arrays.copyOf(tippZahlen, tippZahlen.length);
    }

    public void gewinnZahlenErmittel(int[] gewinzahlen) {
        int[] richtigeTippzahlen = new int[this.tippZahlen.length];
        int richtigeZahl_index = 0;
        for (int i = 0; i < tippZahlen.length ; i++) {
            for (int j = 0; j < tippZahlen.length ; j++) {
                if (tippZahlen[i] == gewinzahlen[j]) {
                    richtigeTippzahlen[richtigeZahl_index] = gewinzahlen[j];
                    richtigeZahl_index++;
                }
            }
        }

        System.out.println(Arrays.toString(richtigeTippzahlen));
    }
}
