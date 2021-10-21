package formatUndParse;

import inputHilfe.InputUtil;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class ZahlenformatDemo {
    public static void main(String[] args) {
        javaInternesFormat();
        numberFormatDemo();
    }

    public static void numberFormatDemo() {
        double wert = Math.PI;
        // printf verwendet Regionaleinstellungen
        System.out.printf("Die Zahl PI beträgt %f\n" , wert);
        System.out.printf("Die Zahl PI beträgt %f.4\n" , wert);

        // Umwandeln nach String
        // format und formatted verwenden auch Regionaleinstellungen
        String strWert = String.format("%.10f", wert);
        System.out.println("Wert mit format" + strWert);
        strWert = "%.10f".formatted(wert);
        System.out.println("Wert mit formatted: " + strWert);

        // default NumberFormat holen
        NumberFormat numFmt = NumberFormat.getNumberInstance();
        // genau 7 Nachkommastellen bei der Formatierung (wird beim Parsen ignoriert).
        numFmt.setMinimumFractionDigits(7);
        numFmt.setMaximumFractionDigits(7);
        strWert = numFmt.format(wert);
        System.out.println("Wert mit NumberFormat: " + strWert);

        //Umwandlung aus String
        try {
            double wertNeu = numFmt.parse(strWert).doubleValue();
            System.out.println("Original-Wert" + wert);
            System.out.println("Wert mit Number-Format geparst" + wertNeu);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        numFmt = NumberFormat.getNumberInstance(Locale.US);
        strWert = numFmt.format(wert);
        System.out.println("Wert mit NumberFormat US" + strWert);

        // InputUtil  neu:
        System.out.println("Gib eine fließkommazahl ein:");
        double input = InputUtil.readDouble();
        System.out.println("Du hast eingegeben: " + input);
    }

    private static void javaInternesFormat() {
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
