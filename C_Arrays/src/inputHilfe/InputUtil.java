package inputHilfe;

import java.util.Scanner;

public class InputUtil {
    // 1 Scanner Objekt für die Klasse (dh. für die ganze Anwendung)
    private static Scanner input; /*= new Scanner(System.in)*/

    // Alternative Initialisierung:
    static {
        input =  new Scanner(System.in);
        System.out.println(">>>>>>>>>> Achtung : Kommatrennzeichen ist der .");
    }

    // statische Methoden zum Einlesen verschiedener Datnrypen
    public static  String readString() {
        // Eine Zeile lesen, das Ergbnis ist der String ohne Zeilende;
        return input.nextLine();
    }

    public static int readInt() {
        String strZahl = input.nextLine();

        try {
            // aus einer Zeichenfolge eine ganze Zahl ermitteln
            return Integer.parseInt(strZahl);
        }
        // Alle Fehler, die aufeten fangen
        catch (Exception e) {
            System.err.println("Fehlerhafte Eingabe: " + e.toString());
            System.out.println("Neuer Versuch");
            return readInt();
        }
    }

    public static double readDouble() {
        try {
            // aus einer Zeichenfolge ein Double ermitteln
            return Double.parseDouble(input.nextLine());
        }
        // Alle Fehler, die aufeten fangen
        catch (Exception e) {
            System.err.println("Fehlerhafte Eingabe: " + e.toString());
            System.out.println("Neuer Versuch");
            return readDouble();
        }
    }

    public static char readChar() {
        try {
            String text = input.nextLine();

            // aus einer Zeichenfolge ein Double ermitteln
            return text.charAt(0);
        }

        // Alle Fehler, die aufeten fangen
        catch (Exception e) {
            System.err.println("Fehlerhafte Eingabe: " + e.toString());
            System.out.println("Neuer Versuch");
            return readChar();
        }
    }

}
