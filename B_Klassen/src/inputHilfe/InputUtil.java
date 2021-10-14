package inputHilfe;

import java.util.Scanner;

public class InputUtil {
    // 1 Scanner Objekt für die Klasse (dh. für die ganze Anwendung)
    private static Scanner input; /*= new Scanner(System.in)*/

    // Alternative Initialisierung:
    static {
        input =  new Scanner(System.in);
    }

    // statische Methoden zum Einlesen verschiedener Datnrypen
    public static  String readString() {
        // Eine Zeile lesen, das Ergbnis ist der String ohne Zeilende;
        return input.nextLine();
    }

    public static int readInt() {
        String strZahl = input.nextLine();
        // aus einer Zeichenfolge eine ganze Zahl ermitteln
        int zahl = Integer.parseInt(strZahl);
        return zahl;
    }

}
