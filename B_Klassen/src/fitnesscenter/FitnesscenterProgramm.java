package fitnesscenter;

public class FitnesscenterProgramm {
    public static void main(String[] args) {
        // sout
        // souf ffür printf
        System.out.println("Fitnesszenter");
        System.out.printf("");

        Wertkarte karte1 = new Wertkarte();

        karte1.setzen(1, "Maxi");
        karte1.anzeigen();

        karte1.aufbuchen(300);
//        karte1.anzeigen();

        karte1.abbuchenGymnastik();
        karte1.anzeigen();

        try {
            // zu viel aufbuchen
            // Wenn die Anzeige zu viel ist, geht die Ausführung im catch block weiter
            // Und die Anzeige wird nicht ausgeführt.
            karte1.aufbuchen(150);
            karte1.anzeigen();
        } catch (IllegalArgumentException e) {
            System.out.println("Es ist ein Fehler passiert: " + e.getMessage());
        }
    }
}
