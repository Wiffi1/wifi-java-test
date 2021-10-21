package eindimensional;

// 1. aus der Entwicklungsumgebung: Die Run-Configuration für das Programm anpassen
// 2. aus einem Command-Fenster:
public class Aufrufargumente {

	// Die Aufrufargumente dürfen auch als var-args deklariert weerden
	public static void main(String ... args) {
		if(args.length == 0) {
			System.out.println("Keine Aufrufargumente!");
		} else {
			System.out.println("Aufrufargumente an das Programm");
			// Die Aufrufargumente anzeigen
			for (String arg: args) {
				System.out.println(arg);
			}
		}
		
		// die Aufrufargumente anzeigen
		for (String arg : args) {
			System.out.println(arg);
		}

	}

}
