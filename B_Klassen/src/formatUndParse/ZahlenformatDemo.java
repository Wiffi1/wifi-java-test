package formatUndParse;

public class ZahlenformatDemo {
    public static void main(String[] args) {
        // ohne Format: mit Wrapperklasse
        double wert = Math.PI;
        System.out.println("Die Zahl PI beträgt " + Double.toString(wert));
        // einfacher zu schreiben
        System.out.println("Die Zahl PI beträgt " + wert);

        // primitiven Wert in Zeichnfolge umwandeln
        String strValue = Double.toString(wert);
        System.out.println(strValue);

        // aus String zurück umwandeln
        double wertNeu = Double.parseDouble(strValue);
        System.out.println("Aus String ermittel: " + wertNeu);

        // Wrapper-Objekt aus der Zeichenfolge erzeugen
        Double dObject = Double.valueOf(strValue);
        // den primitiven Wert aus dem Wrapper-Objekt herausholen
        wertNeu = dObject.doubleValue();
        System.out.println("Aus dString ermittlet (Über Wrapper-Objekt): " + wertNeu);

//        Double.MAX_VALUE

        double infinite = Double.POSITIVE_INFINITY;
        System.out.println("Unendlich: " + infinite);
    }
}
