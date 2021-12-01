package csv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
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

    private static void readData(String fileName) {

        // für den Preis: ein NumberFormat mit den passenden Regionaleinstellungen holen
        NumberFormat numFormat = NumberFormat.getInstance(Locale.US);

        // für das Datum:
        DateTimeFormatter dateFormat;
        // US-Amerikanisches kurzes Datumsformat
        // dateFormat = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).withLocale(Locale.US);
        // alternativ mit Muster (können wir besser anpassen)
        dateFormat = DateTimeFormatter.ofPattern("MM/dd/yy");


        List<Auto> fahrzeuge = new ArrayList<>();
        // File öffnen und Zeile für Zeile lesen
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName, StandardCharsets.UTF_8))) {

            System.out.println("Lese Daten ein...");
            String zeile;
            // solange es Zeilen zu lesen gibt (null bedeutet EOF, end-of-file)
            while ((zeile = reader.readLine()) != null) {
                System.out.println("Zeile gelesen: " + zeile);
                //String [] werte = zeile.split(";"); // genau dieses Zeichen
                String[] werte = zeile.split("[;]"); // eines von mehreren Zeichen
                System.out.printf("... die Zeile enthält %d Werte\n", werte.length);

                if (werte.length >= 6) {
                    try {
                        Auto item = new Auto();
                        // int: mit Integer.parseInt einlesen
                        item.setNr(Integer.parseInt(werte[0].trim()));
                        // String: nicht speziell verarbeiten
                        item.setMarke(werte[1].trim());
                        // double: je nach Komma-Zeichen mit Double.parse oder NumberFormat (ist flexibler)
                        item.setPreis(numFormat.parse(werte[2].trim()).doubleValue());
                        // LocalDate: mit Formatter
                        item.setErzeugt(LocalDate.from(dateFormat.parse(werte[3].trim())));
                        // int
                        item.setLeistung(Integer.parseInt(werte[4].trim()));
                        // String
                        item.setFarbe(werte[5].trim());
                        System.out.println("... Auto-Objekt aus der Zeile ermittelt: " + item);
                        fahrzeuge.add(item);
                    } catch (ParseException | NumberFormatException e) {
                        System.out.printf("... Fehler beim Verarbeiten von Zeile %s: %s \n", zeile, e.toString());
                    }

                } else {
                    System.out.println("Die Zeile enthält nicht genügend Werte");
                }

            }
            System.out.println("Folgende Daten wurden eingelesen:");
//            for (Auto a : fahrzeuge) {
//                System.out.println(a);
//            }
            // alternativ: mit forEach-Methode aus List
            // fahrzeuge.forEach(a -> System.out.println(a));
            // geht auch mit Methodenreferenz
            fahrzeuge.forEach(System.out::println);


        } catch (IOException e) {
            System.out.println("Fehler beim Einlesen der Daten: ");
            e.printStackTrace();
        }

    }
}
