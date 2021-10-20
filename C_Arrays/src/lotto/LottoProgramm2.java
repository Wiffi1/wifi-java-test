package lotto;

import java.util.Arrays;

public class LottoProgramm2 {
    public static void main(String[] args) {
        Lottotipp2 tipp1 = new Lottotipp2('Q');
        // TODO anzeigen

        Lottotipp2 tipp2 = new Lottotipp2('M');
        // TODO anzeigen

        int[] gewinnzahlen = {1, 2, 3, 4, 5, 6};
        tipp2.gewinnZahlenErmittel(gewinnzahlen);

        int[] zahlen = tipp2.getTippZahlen();
        for (int i = 0; i < zahlen.length; i++ ) {
            if (zahlen[i] % 2 == 0) {
                zahlen[i]++;
            }
        }

        int[] zahlen1 = tipp2.getTippZahlen();
        System.out.println("kkk" + Arrays.toString(zahlen1));
    }
}
