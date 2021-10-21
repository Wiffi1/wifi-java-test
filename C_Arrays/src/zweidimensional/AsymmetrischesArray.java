package zweidimensional;

import java.util.Random;

public class AsymmetrischesArray {
    public static void main(String[] args) {
        // Array mit drei Sub-Arrays erzeugen
        int[][] zellen = new int[3][];
        Random zufall = new Random();

        // Die Sub-Arrays erzeugen
        for (int i = 0; i < zellen.length; i++) {
            zellen[i] = new int[zufall.nextInt(5) + 2];  // 2 bis 6 Spalten pro Zeile
        }

        // alles andere bleibt gleich
        // zufällige Werte eintragen
        // äußere Schleife: für jede "Zeile" laufen lassen
        for (int i = 0; i < zellen.length; i++) {
            // innere Schleife: für jede "Spalte" laufen lassen
            for (int j = 0; j < zellen[i].length; j++) {
                int wert = zufall.nextInt(100) + (i + 1) * 100;
                zellen[i][j] = wert;
                System.out.printf("wert[%d][%d]=%d    ", i, j, wert);
            }
            System.out.println("");
        }
        // Iteration mit foreach
        System.out.println("Alle Werte:");
        // äußere Schleife iteriert über die Sub-Arrays (int[])
        for (int[] zeile : zellen) {
            // innere Schleife iteriert über die Werte im jeweiligen Sub-Array
            for (int wert : zeile) {
                System.out.printf("%d ", wert);
            }
            //nach jeder "Zeile" ein newLine.
        }
    }
}
