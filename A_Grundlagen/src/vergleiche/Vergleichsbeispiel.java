package vergleiche;

public class Vergleichsbeispiel {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Beispiele zu Vergleichsoperatoren");
		int nr1 = 10;
		int nr2 = 7;
		boolean istNr1kleinerNr2;
		
		istNr1kleinerNr2 = nr1 < nr2;
		
		int minimum =  7;
		int maximum =  13;
		
		int messwert = 3;
		
		boolean wertLiegtDazwischen = messwert > minimum && messwert < maximum; 
		
		if (messwert < minimum || messwert > maximum) {
			System.out.println("liegt ausserhalbe");
		}
		
		if (messwert < minimum ^ messwert > maximum) {
			System.out.println("liegt ausserhalbe");
		}

	}

}
