package timeApi;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.stream.Stream;

public class TimeApiDemo {

    public static void main(String[] args) {

        klassenUndUmwandlungen();
        zeitSpannen();
        berechnungen();
        formatierenUndParsen();
    }

    public static void klassenUndUmwandlungen() {
        System.out.println("**************** Erzeugung ****************");
        // Aktueller UTC-Zeitpunkt
        Instant now = Instant.now();
        System.out.println("Instant: " + now);

        // Aktuelles Datum
        LocalDate today = LocalDate.now();
        System.out.println("LocalDate: " + today);

        // Aktuelles Datum mit Uhrzeit
        LocalDateTime localNow = LocalDateTime.now();
        System.out.println("LocalDateTime: " + localNow);

        // aktuelles Datum mit Zeitzone
        // jetzt in aktueller Zeitzone
        ZonedDateTime zonedNow = ZonedDateTime.now();

        System.out.println("ZonedDateTime: " + zonedNow);

        System.out.println("**************** Umwandlung ****************");
        // Umwandlungen:
        // 1. Instant <-> ZonedDateTime // Umrechnung der Zeitzone wird vorgenommen
        //ZoneId timeZone = ZoneId.systemDefault();
        ZoneId timeZone = ZoneId.systemDefault();
        ZonedDateTime zdt = now.atZone(timeZone);
        System.out.println("Instant <--> ZonedDateTime mit Zeitzone " + timeZone);
        System.out.printf("%s -> %s%n", now, zdt);
        // Gegenrichtung:
        Instant inst = zdt.toInstant();
        System.out.printf("%s -> %s%n", zdt, inst);
        System.out.println();

        // 2. ZoneDateTime <-> LocalDateTime (es wird nur die Zeitzone
        // abgeschnitten / hinzugefügt, keine Umrechnung !!!!)
        LocalDateTime ldt = zonedNow.toLocalDateTime();
        ZonedDateTime zdt2 = ldt.atZone(timeZone);
        System.out.println("ZonedDateTime <--> LocalDateTime mit Zeitzone " + timeZone);
        System.out.printf("%s -> %s%n", zonedNow, ldt);
        System.out.printf("%s -> %s%n", ldt, zdt2);

        // 3. Instant <-> LocalDateTime geht nicht direkt, nur über ZonedDateTime

    }

    static void zeitSpannen() {
        System.out.println("**************** Zeitspanne Instant ****************");
        // Instant
        System.out.println("toEpochMilli: " + Instant.now().toEpochMilli());
        Instant inst1 = Instant.now();
        // rechenintensive Operation
        long z = 0;
        for (int i = 0; i < 100000; i++) {
            for (int j = i; j < 100000; j++) {
                z += i * i + j;
            }
        }
        System.out.println("Ergebnis: " + z);
        Instant inst2 = Instant.now();
        // Differenz berechnen
        Duration span = Duration.between(inst1, inst2);
        System.out.printf("Die Berechnung dauerte %s (%d ms) %n", span, span.toMillis());


        // LocalDateTime
        System.out.println("**************** Zeitspanne LocalDateTime ****************");
        System.out.println("Period und Duration");
        // Letzter Tag der Sommerzeit 2015
        LocalDateTime ldt1 = LocalDateTime.of(2015, 10, 24, 10, 30);
        LocalDateTime ldt2 = LocalDateTime.of(2015, 10, 25, 10, 30);
        // period und Duration ausrechnen
        Period span1 = Period.between(ldt1.toLocalDate(), ldt2.toLocalDate());
        Duration span2 = Duration.between(ldt1, ldt2);
        System.out.printf("Period zwischn %s und %s -> %s %n", ldt1.toLocalDate(), ldt2.toLocalDate(), span1);
        System.out.printf("Duration zwischen %s und %s -> %s %n", ldt1, ldt2, span2);


        // ZonedDateTime
        System.out.println("**************** Zeitspanne ZonedDateTime ****************");
        ZonedDateTime zdt1 = ldt1.atZone(ZoneId.systemDefault());
        ZonedDateTime zdt2 = ldt2.atZone(ZoneId.systemDefault());
        // Duration ausrechnen
        span2 = Duration.between(zdt1, zdt2);
        System.out.printf("Duration zwischen %s und %s -> %s %n", zdt1, zdt2, span2);
    }

    static void berechnungen() {

        // Instant
        System.out.println("**************** Berechnungen Instant****************");
        Instant now = Instant.now();
        Instant inst2 = now.plus(Duration.ofDays(10));
        System.out.println("Instant: \t" + now);
        System.out.println("plus 2 days:\t" + inst2);

        // LocalDate
        System.out.println("**************** Berechnungen LocalDate****************");
        LocalDate ld1 = LocalDate.of(1992, 5, 31);
        LocalDate ld2 = ld1.plusMonths(4);
        System.out.println("LocalDate: \t" + ld1);
        System.out.println("plus 4 Months:\t" + ld2);

        ld1 = LocalDate.of(1992, 2, 29);
        ld2 = ld1.plusYears(2);
        System.out.println("LocalDate: \t" + ld1);
        System.out.println("plus 2 Years: \t" + ld2);

        try {

            ld2 = ChronoUnit.HOURS.addTo(ld1, 1);
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        // LocalDateTime
        System.out.println("**************** Berechnungen LocalDateTime****************");
        LocalDateTime ldt1 = LocalDateTime.of(2015, 10, 24, 10, 30);
        LocalDateTime ldt2 = ldt1.plusDays(1);
        System.out.println("LocalDateTime: \t" + ldt1);
        System.out.println("plus 1 day: \t" + ldt2);
        ldt2 = ChronoUnit.WEEKS.addTo(ldt1, 1);
        System.out.println("plus 1 week: \t" + ldt2);

        // ZonedDateTime
        System.out.println("**************** Berechnungen ZonedDateTime****************");
        ZonedDateTime zdt1 = ZonedDateTime.of(ldt1, ZoneId.systemDefault());
        ZonedDateTime zdt2 = zdt1.plusDays(1);

        System.out.println("ZonedDateTime: \t" + zdt1);
        System.out.println("plus 1 day: \t" + zdt2);
        zdt2 = ChronoUnit.WEEKS.addTo(zdt1, 1);
        System.out.println("plus 1 week: \t" + zdt2);


    }

    static void formatierenUndParsen() {
        System.out.println("**************** Formatieren und Parsen ****************");

        DateTimeFormatter fmt;

        fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        System.out.println("Local : " + fmt.format(LocalDateTime.now()));

        // Mit Zeitzonen-ID
        fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm VV");
        System.out.println("Zoned : " + fmt.format(ZonedDateTime.now()));

        // diverse FormatStyles verwenden
        fmt = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG);
        System.out.println("Long: " + fmt.format(ZonedDateTime.now().minusMonths(1)));

        // LONG mit Regional-Einstellungen der USA
        fmt = fmt.withLocale(Locale.US);
        System.out.println("Long US: " + fmt.format(ZonedDateTime.now().minusMonths(1)));

        // Parsen
        fmt = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM);
        String input = "25.04.2017";
        LocalDate ld = LocalDate.parse(input, fmt);
        System.out.println("Parsed: " + ld);
    }
}
