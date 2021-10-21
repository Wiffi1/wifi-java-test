package eindimensional;

// 1. aus der Entwicklungsumgebung: Die Run-Configuration für das Programm anpassen
// 2. aus einem Command-Fenster:
// wenn die passende java-Verson nicht im Paht hinerlegt werden kann
// Eine temporäre Umgebungsvariable setzen
// set JAVA="c:\Program Files\Eclipse Foundation\jdk-17.0.0.35-hotspot\bin\java.exe

// Das Programm starten mit
// %JAVA% -cp out\production\C_Arrays eindimensional.Aufrufargumente
// bzw. java -cp out\production\C_Arrays eindimensional.Aufrufargumente
// letztes Argument ist der Name der Klasse, da mdie mao-Methode enthält
// Danach können Argumente an das Programm übergeben werden.
//     %JAVA% -cp out\production\C_Arrays eindimensional.Aufrufargumente d.txt G:\_Temp\Pfad.txt 123 abc "123 abc"
//          d.txt G:\_Temp\Pfad.txt 123 abc "123 abc"    => Das sind die Aufrufargumente
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
