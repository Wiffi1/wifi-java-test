package methoden;

public class Methodenbeispiel {

	static double mult(double a, double b) {
		double ergebnis = a * b;
		System.out.println("mult für double werte mit zwei params");	
		return ergebnis;
	}

	static double mult(int a, int b) {
		double ergebnis = a * b;
		System.out.println("mult für integer mit zwei params");	
		return ergebnis;
	}

	
	static double mult(double a, double b, double c) {
		double ergebnis = a * b * c;
		a = 7; // Nur für a innerhalb der Methode. Nicht auf das ursprünglich übergeben Argument.
		System.out.println("mult mit drei params");
		return ergebnis;
	}
	
	static void ausgabe(int startwert, int endwert) {
		for (int i = startwert; i <= endwert; i++) {
			System.out.println("Wert: " + i);
		}
	}
	
	
	static void ausgabeRecursiv(int startwert, int endwert) {
		System.out.println(startwert);	
		if (startwert >= endwert) {
			return;
		}
		ausgabeRecursiv(startwert + 1, endwert);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double result;		
		double input = 3.0;  
		
		ausgabeRecursiv(77, 100);
		
//		mult(12, 13, 14.6); // wird hier nicht weiter verwendet => verworfen		
//		result = mult(12, 13, 14.6);
//		
//		mult(12, 34);

		mult(12.4, 34); // double variante		
		mult(12, 34);  // int variante
		mult(12.0, 34);  // double variante, 34 wird implizit auf double gecastet
		
		ausgabe(1, 10);
//		ausgabe(4, 2);	
//		int x;
//		x = ausgabe(1, 10); // geht natürlich nicht
		
		int start = 1;
		int end = 10;
		ausgabe(start, end);
		
	}

}
