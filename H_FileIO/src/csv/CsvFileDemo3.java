package csv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CsvFileDemo3 {

    public static void main(String[] args) {
        readData("autos3.csv");
    }

    private static void readData(String filename) {

        // Für den Preis: Ein NumberFormat mit den passenden Regionaleinstellungen holen
        NumberFormat numFormat = NumberFormat.getInstance(Locale.US);
//        numFormat.

        DateTimeFormatter dateFormat;
        // dateFormat = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).withLocale(Locale.US);
        // alternativ mit Muster (kann ich besser anpassen)
        dateFormat = DateTimeFormatter.ofPattern("MM/dd/yy");

        List<Auto> fahrzeug = new ArrayList<>();

        // File öfnen und Zeile für Zeile lesen
        try (BufferedReader reader = new BufferedReader(new FileReader(filename, StandardCharsets.UTF_8))) {
            System.out.println("Lese Daten ein...");
            String zeile;

            // so lange es Zeilen zu lesen gibt(null bedeutet EOF)
            while ((zeile = reader.readLine()) != null) {
                System.out.println("Zeile gelesen" + zeile);
//                String [] werte = zeile.split(";");   // Genau dieses Zeichen
                String [] werte = zeile.split("[;]");  // Eines von mehreren Zeichen
                System.out.printf("... die Zeile enthält %d Werte\n", werte.length);

                if (werte.length >= 6) {
                    try {
                        Auto item = new Auto();
                        // int: mit Integer.parseInt einlesen
                        item.setNr(Integer.parseInt(werte[0].trim()));
                        // String: nicht speziell verarbeiten
                        item.setMarke(werte[1].trim());
                        // double: je nach Komma-Zeichen mit Double.parse oder NumberFormat
                        item.setPreis(numFormat.parse(werte[2].trim()).doubleValue());

                        // localeDat mit Formatter
                        item.setErzeugt(LocalDate.from(dateFormat.parse(werte[3].trim())));

                        item.setFarbe(werte[5].trim());

                        System.out.println("Auto-Objekt asu der Zeile ermitteln");
                        fahrzeug.add(item);
                    } catch (ParseException | NumberFormatException e) {
                        e.printStackTrace();
                        System.out.printf("Fehler beim Verarbeiten von Zeile %s: %s\n", zeile, e.toString());
                    }
                } else {
                    System.out.println("die Zeile enthält nicht genügend Werte");
                }
            }

            System.out.println("alle ....");
//            for (Auto a : fahrzeug) {
//                System.out.println("folgende Daten wurden eingelesen" + a);
//            }
//            fahrzeug.forEach(a-> System.out.println(a));

            // geht auch mit Methodenreferenz
            fahrzeug.forEach(System.out::println);


        } catch (IOException e) {
            System.out.println("Fehler beim einlsesen der Daten");
            e.printStackTrace();
        }

    }
}
