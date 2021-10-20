package eindimensional;

//import inputHilfe.InputUtil;

import inputHilfe.InputUtil;

import java.util.Arrays;
import java.util.Random;

public class SortierenUndSuchen {
    public static void main(String[] args) {

        int[] werte = new int[12];

        // Zufallszahlen eintragen
        // Generator für Zufallszahlen
        Random zufall = new Random();
        for (int i = 0; i < werte.length; i++) {
            int zufallsWert = zufall.nextInt(50); // Wert zwischn 0 und 49
            werte[i] = zufallsWert + 20 ; // Werte zwischen 20 und 69
        }

        System.out.println("Werte unsortiert");
        for (int wert : werte){
            System.out.print(wert + " ");
        }
        System.out.println("");

        System.out.println("Welche Zahl suchen?");
        int zahl = InputUtil.readInt();

        // Das Array sortieren Quicksort
        // ACHTUNG, MUSS SORTIERT SEIN, DAMIT quicksearch funktioniert.
        Arrays.sort(werte);
        System.out.println("Werte sortiert: ");
        System.out.println(Arrays.toString(werte));

        // Binärsuche verwenden, um die Zahl zu suchen.
        int index = Arrays.binarySearch(werte, zahl);

        // Binärsuche im sortierten Array
        if (index < 0) {
            System.out.println("Wert nicht gefunden");
        } else {
            System.out.printf("Wert steht am Index %d\n", index);
        }

    }
}
