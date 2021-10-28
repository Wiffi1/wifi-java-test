package objectBox;

import java.time.LocalDate;

// Variante mit ObjecBox-Klassen für alle Typenn ist praktisch aber nciht sttreng typisiert.
public class ObjectBoxDemo {

	public static void main(String[] args) {
		ObjectBox stringBox = new ObjectBox();
		// implizite Umwandlung von String nach Object
		stringBox.setValue("Hello");

		// explizite Typumwandlung von Object nach String
		String strValue = (String) stringBox.getValue();
		System.out.println("Wert aus Stringbox: " + strValue);

		ObjectBox dateBox = new ObjectBox();
		// implizite Umwandlung von LocalDate nach Object
		dateBox.setValue(LocalDate.now());

		// explizite Typumwandlung von Object nach LocalDate
		LocalDate dateValue = (LocalDate) dateBox.getValue();
		System.out.println("Wert aus Datebox: " + dateValue);

		// Hier machen wir einen "Logischen" Fehler, indem wir den falschen Datentyp
		// verwenden
		dateBox.setValue("2020-10-25");
		System.out.println("Neues Datum gesetzt: " + dateBox.toString());
		// Typumwandlung von Object nach LocalDate
		// Hier zeigt sich unser "logischer" Fehler
		LocalDate dateValue2 = (LocalDate) dateBox.getValue();
		System.out.println("Neuer Wert aus Datebox: " + dateValue2);


	}

}
