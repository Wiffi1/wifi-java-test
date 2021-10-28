package genericBox;

import otherBoxes.LocalDateBox;
import otherBoxes.StringBox;

import java.time.LocalDate;

public class genericBoxDemo {
    public static void main(String[] args) {
        Box<String> stringBox = new Box<String>();
        stringBox.setValue("Hallo");

        String stringValue = stringBox.getValue();
        System.out.println("Wert aus Stringbox: " + stringValue);

        Box<LocalDate> dateBox = new Box/*<LocalDate>*/();
        dateBox.setValue(LocalDate.now());
        LocalDate dateValue = dateBox.getValue();
        System.out.println("Wert aus Datebox: " + dateValue);

        // Hier wird der "logische" Fehler bereits als Compiler Error angezeigt
        // DerKompiler verhindeert den logischen Fehler
/*		dateBox.setValue("2020-10-25");
		System.out.println("Neues Datum gesetzt: " + dateBox.toString());*/
		// Typumwandlung von Object nach LocalDate
		LocalDate dateValue2 = (LocalDate) dateBox.getValue();
		System.out.println("Neuer Wert aus Datebox: " + dateValue2);

        // mit falsch verwendeter Klasse können wir den "logischen" Fehler trotzdem machen.
        // ACHTUNG: raw Deklaration von generischen Klassen sollten wir unbedingt vermeiden
/*        Box rawBox = dateBox;
        rawBox.setValue("2020-10-25");
        System.out.println("Wert über Raw Box geändert");

        LocalDate dateValue3 = dateBox.getValue();
        System.out.println("Wert aus Datebox: " + dateValue3);*/
    }
}
