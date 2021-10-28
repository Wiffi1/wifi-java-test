package lotto;

import java.util.Arrays;

public class LottoProgramm {

    public static void main(String[] args) {
        int[] gewinnzahlen = {11, 12, 13, 14, 15, 16};

        // 1. Lottotipp als quicktipp
        Lottotipp tipp1 = new Lottotipp();
        tipp1.quicktipp();
        tipp1.ausgeben();

        int[] richtige = tipp1.gewinnPruefung(gewinnzahlen);
        System.out.println("Richtige im Quicktipp: " + Arrays.toString(richtige));

        // 2. Lottotipp als manueller Tipp
        Lottotipp tipp2 = new Lottotipp();
        tipp2.manuellerTipp();
        tipp2.ausgeben();
        richtige = tipp2.gewinnPruefung(gewinnzahlen);
        System.out.println("Richtige im manuellen Tipp: " + Arrays.toString(richtige));

    }

}
