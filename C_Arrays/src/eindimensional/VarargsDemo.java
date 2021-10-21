package eindimensional;

public class VarargsDemo {

	// Die Aufrufargumente dürfen auch als var-args deklariert weerden
	public static void main(String ... args) {
		

		// 1. Aufrufart: Übergabe eines Arrays
		// So könnten wir auch calcAverage_Orig aufrufen
		double average = calcAverage( new double[] {3.5, 2.7, 4.2});
		System.out.printf("Der Durchschnitt beträgt %.2f\n", average);

		// 2. Aufrufart: Übergabe als kommagetrennte Werte
		// dh. der Compiler erzeugt den Code für die Array-Erzeugung
		// calcAverage_Orig könnten wir so nicht aufrufen
		double average2 = calcAverage(3.5, 2.7, 4.2);
		System.out.printf("Der Durchschnitt beträgt %.2f\n", average2);

		
	}

	// an diese Methode kann der Aufruferr das Array auf zwei Arten übergeben
	// 1. als Array
	// 2. als kommagetrennte Argumente-Liste
	public static double calcAverage(double ... values) {
		if(values.length > 0) {
			double sum = 0;
			for (int i = 0; i < values.length; i++) {
				sum += values[i];
			}
			return sum / values.length;
		}else {
			throw new IllegalArgumentException("Das Array muss mindestens ein Element enthalten!");
		}
	}

	public static double calcAverage_Orig(double [] values) {
		if(values.length > 0) {
			double sum = 0;
			for (int i = 0; i < values.length; i++) {
				sum += values[i];
			}
			return sum / values.length;
		}else {
			throw new IllegalArgumentException("Das Array muss mindestens ein Element enthalten!");
		}
	}


}
