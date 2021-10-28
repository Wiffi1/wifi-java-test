package otherBoxes;

import java.time.LocalDate;

public class BoxDemo1 {

	public static void main(String[] args) {
		StringBox stringBox = new StringBox();
		stringBox.setValue("Hallo");
		
		String stringValue = stringBox.getValue();
		System.out.println("Wert aus Stringbox: " + stringValue);
		
		LocalDateBox dateBox = new LocalDateBox();
		dateBox.setValue(LocalDate.now());
		LocalDate dateValue = dateBox.getValue();
		System.out.println("Wert aus Datebox: " + dateValue);

		// Hier wird der "logische" Fehler bereits als Compiler Error angezeigt
/*		dateBox.setValue("2020-10-25");
		System.out.println("Neues Datum gesetzt: " + dateBox.toString());
		// Typumwandlung von Object nach LocalDate
		LocalDate dateValue2 = (LocalDate) dateBox.getValue();
		System.out.println("Neuer Wert aus Datebox: " + dateValue2);*/

	}

}
