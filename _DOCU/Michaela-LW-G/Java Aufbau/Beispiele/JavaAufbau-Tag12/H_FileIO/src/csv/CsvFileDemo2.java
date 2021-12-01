package csv;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
// Die Werte werden mit eigenen Format-Instanzen formatiert und geparst
public class CsvFileDemo2 {

    private static Scanner scanner = new Scanner(System.in);

    private static NumberFormat preisFormat = NumberFormat.getCurrencyInstance();
    private static DateTimeFormatter dateFormat =
            DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM);

    public static void main(String[] args) {

        System.out.print("Daten speichern? j/n");
        boolean saveData = scanner.nextLine().charAt(0) == 'j';
        boolean readData = saveData ? saveCsv("autos2.csv") : true;

        if (saveData) {
            System.out.println("Weiter mit Return");
            scanner.nextLine();
        }
        if (readData) {
            loadCsv("autos2.csv");
        }

    }

    static boolean saveCsv(String fileName) {

        Auto[] data = {new Auto("Mercedes", 29999.99, LocalDate.of(2020, 12, 15), 130, "Silber"),
                new Auto("Audi", 26599.99, LocalDate.of(2020, 10, 2), 120, "Rot"),
                new Auto("Opel", 20999.99, LocalDate.of(2020, 5, 21), 90, "Weiß")};
        System.out.println("Folgende Daten werden gespeichert:");
        for (Auto auto : data) {
            System.out.println(auto);
        }


        //  Daten speichern
        try (PrintWriter writer = new PrintWriter(
                new FileWriter(fileName, StandardCharsets.UTF_8))) {

            for (Auto item : data) {
                // für CSV müssen wir selber die Reihenfolge definieren
                writer.printf("%d; %s; %s; %s; %d; %s\n",
                        item.getNr(), item.getMarke(),

                        // statt item.getPreis(),
                        // den Preis mit dem NumberFormat in Zeichenfolge umwandeln
                        preisFormat.format(item.getPreis()),
                        //statt item.getErzeugt(),
                        // das Datum mit dem DateTimeFormatter in Zeichenfolge umwandeln
                        dateFormat.format(item.getErzeugt()),
                        item.getLeistung(), item.getFarbe());
            }
            System.out.println("Daten gespeichert");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

    static void loadCsv(String fileName) {
        List<Auto> fahrzeuge = new ArrayList<>();
        // BufferedReader zum zeilenweisen lesen öffnen
        try (BufferedReader reader = new BufferedReader(
                new FileReader(fileName, StandardCharsets.UTF_8))) {
            String line;

            // solange es Zeilen zu lesen gibt
            while ((line = reader.readLine()) != null){
                System.out.println("Zeile gelesen: " + line);
                String [] values = line.split(";");
                // Werte sind in dieser Reihenfolge im Array
//                item.getNr(), item.getMarke(), item.getPreis(),
//                        item.getErzeugt(), item.getLeistung(), item.getFarbe()
                Auto item = new Auto();
                item.setNr(Integer.parseInt(values[0].trim()));
                item.setMarke(values[1].trim());
                item.setPreis(preisFormat.parse(values[2].trim()).doubleValue());
                item.setErzeugt(LocalDate.from(dateFormat.parse(values[3].trim())));
                item.setLeistung(Integer.parseInt(values[4].trim()));
                item.setFarbe(values[5].trim());



                System.out.println("Auto-Objekt erzeugt: " + item);
                fahrzeuge.add(item);
            }

            System.out.println("Daten gelesen:");
            for (Auto auto : fahrzeuge) {
                System.out.println(auto);
            }
        } catch (Exception e) {
            System.out.println("Fehler beim Laden der CSV Daten:");
            System.out.println(e.toString());
        }
    }
}
