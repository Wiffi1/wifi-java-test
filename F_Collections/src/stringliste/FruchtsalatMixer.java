package stringliste;

import inputHilfe.InputUtil;

import java.util.*;

public class FruchtsalatMixer {
    public static void main(String[] args) {
        // Für die Liste das Basisinterface verwenden...
        List<String> fruchtsalat = new ArrayList<>();

        boolean mitArraylist = false;

        // ... und mit einer passenden Implementierung instanziieren
        if (mitArraylist) {
            fruchtsalat = new ArrayList<>();
        } else {
            fruchtsalat = new LinkedList<>();
        }

        System.out.println("Fruchstsalat mit: " + fruchtsalat.getClass().getSimpleName());

        // ein paar Elemente anfügen
        fruchtsalat.add("Banane");
        fruchtsalat.add("Apfel");
        fruchtsalat.add("Birne");
        fruchtsalat.add("Kiwi");

        for (int i = 0; i < fruchtsalat.size(); i++) {
            System.out.printf("%d. Frucht: %s\n", i+1, fruchtsalat.get(i));
        }

        System.out.println("Welche Frucht dazugeben?");
        String input = InputUtil.readString();
        // den Index der Zeichenfolge suchen (case sensitive)
        int index = fruchtsalat.indexOf(input);

        if (index < 0) {
            System.out.println("Die Frucht kommt nicht vor, füge sei dazu...");
            fruchtsalat.add(0, input);
        } else {
            System.out.printf("%s ist schon drin!", input);
        }
//        System.out.printf("%d", index);
        for (String frucht : fruchtsalat) {
            System.out.println(frucht);
        }

        System.out.println("Welche Frucht entfernen?");
        input = InputUtil.readString();

        // Iteration (und entfernen) mit Iterator
        Iterator<String> iterator = fruchtsalat.iterator();
        // solange es ein weiteres Element gibt
        while (iterator.hasNext()) {
            String frucht = iterator.next();

            if (frucht.equalsIgnoreCase(input)) {
                iterator.remove();
                System.out.printf("%s wurde entfernt\n", frucht);
            } else {
                System.out.printf("%s bleibt drin\n", frucht);
            }
        }

        // sortieren (natürliche Reihenfolge, d.h. case sensitive)
        Collections.sort(fruchtsalat);
        // alles anzeigen
        // alles anzeigen mit toString der List-Klasse
        System.out.println("Alle Früchte sortieren");
        System.out.println(fruchtsalat.toString());

        Collections.sort(fruchtsalat, String.CASE_INSENSITIVE_ORDER);
        // Alles anzeigen mit toString der List-Klasse)
        System.out.println("CASE_INSENSITIVE_ORDER)");
        System.out.println(fruchtsalat.toString());
    }
}
