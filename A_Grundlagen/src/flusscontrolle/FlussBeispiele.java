package flusscontrolle;

import java.util.Scanner;

public class FlussBeispiele {

	public static void main(String[] args) {
//		int a = 5;
//		int b = 7;
//		
//		if (a < b) 
//			System.out.println("a kleiner b ist wahr");
//		
//		Scanner eingabe = new Scanner(System.in);
//		System.out.print("Note bitte eingben (1-5):");
//		
//		int note = eingabe.nextInt();
//
//		String noteAlstext;		
// switch Anweisung Klassische switch Variante		
//		switch (note) {
//			case 1:
//				noteAlstext = "Sehr gut";
//				break;
//			case 2:
//				noteAlstext = "Gut";
//				break;
//			case 3:
//				noteAlstext = "Befriedigend";
//				break;
//			case 4:
//				noteAlstext = "Gen�gend";
//				break;
//			case 5:
//				noteAlstext = "Nicht gen�gend";
//				break;
//			default:
//				noteAlstext = "unbekannt";
//				break;
//		}
//		
//		System.out.println(noteAlstext);

		// switch Anweisung Kompakte neue Variante ab Java 14
//		switch (note) {
//			case 1 -> noteAlstext = "Sehr gut";
//			case 2-> noteAlstext = "Gut";
//			case 3-> noteAlstext = "Befriedigend";
//			case 4-> noteAlstext = "Gen�gend";
//			case 5-> noteAlstext = "Nicht gen�gend";
//			default -> noteAlstext = "unbekannt";
//		}
		
		
		// switch Ausdruck. ACHTUNG ben�tgt zum Schluss ein ; 
//		noteAlstext = switch(note) {
//			case 1 -> "Sehr gut";
//			case 2 -> "Gut";
//			case 3 -> "Befriedigend";
//			case 4 -> "Gen�gend";
//			case 5 -> "Nicht gen�gend";
//			default -> "unbekannt";
//		};
		
		
//		int x1;	
//		noteAlstext = switch(note) {
//			case 1:  x1 = 10; yield "Sehr gut"; 
//			case 2: yield "Gut";
//			case 3: yield "Befriedigend";
//			case 4: yield "Gen�gend";
//			case 5: yield "Nicht gen�gend";
//			default: yield "unbekannt";
//		};	
		
//		System.out.println(noteAlstext);		
		
		int [] numbers = new int [10];
		
		for (int i = 0; i < numbers.length; i += 1) {
			
			numbers[i] = (int) (Math.random() * 100);
			if (i == 3) {
				continue;
			}
			System.out.println(i + ": " + numbers[i]);
		}

		xschleife:  // Bezeichnung f�r die Schleife
		for (int x = 0; x < 3; ++x) {
			for (int y = 0; y < 3; ++y) {
				System.out.println(x + " * " + y + " = " + x*y);
				if (y == 2) {
					break xschleife;
				}
			}			
		}

		System.out.println("Ausgabe:");
		
		
		for (int number: numbers) {
			System.out.println(number);	
			number = 7; // keinen Einfluss auf das Element im Array
		}
		
	}
	
}
