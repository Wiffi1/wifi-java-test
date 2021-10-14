package format;

import java.util.Locale;

public class FormatBeispiel {

	public static void main(String[] args) {
				
		int stunden = 13;
		int minuten = 4;
			
		// flags:
		// - für linksbündig
		// + immer Vorzeichen dazuschreiben
		// 0 mit führenden Nullen auffüllen
		// Leerzeichen für Vorzeichen-Platzhalter
		// , für Tausenderpunkte
		
		// d..conversion	
		
		String uhrzeit = String.format("%02d:%02d\n", stunden, minuten);
		System.out.printf(uhrzeit);
		
		
		long zahl1 = -1234;
		double temperatur = -10.578;
		String text = "hello world";
		
		System.out.printf("Temp* : %07.1f %07.3f*", temperatur, temperatur);		
		System.out.printf("Temp* : %07.1f '%-20s' %1$07.3f*\n", temperatur, text);
		System.out.printf(Locale.ENGLISH, "%f", temperatur);		
	}

}
