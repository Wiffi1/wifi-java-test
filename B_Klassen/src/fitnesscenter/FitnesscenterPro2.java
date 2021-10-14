package fitnesscenter;

public class FitnesscenterPro2 {

    public static void main(String[] args) {
        // sout
        // souf ffür printf
        System.out.println("Fitnesszenter");
        System.out.printf("");

        Wertkarte karte1 = new Wertkarte();

        karte1.setzen(1, "Maxi");
        karte1.anzeigen();

        // abbuchen ei Guthaben 0
        bucheAbFitnes(karte1);

        bucheAuf(karte1, 150);
        bucheAuf(karte1,160);
        bucheAuf(karte1,100);

        bucheAbFitnes(karte1);
    }

    static void bucheAuf(Wertkarte karte, double betrag) {
        try {
            System.out.println("Aufbuchen");
            System.out.print("Vorher");
            karte.anzeigen();
            karte.aufbuchen(betrag);
        } catch(IllegalArgumentException e) {
            System.out.println("Fehler reim Aufbauchen " + e.getMessage());
        }
        System.out.print("Nachher");
        karte.anzeigen();
        System.out.println("");
    }

    static void bucheAbFitnes(Wertkarte karte) {
        try {
            System.out.println("Abbuchen");
            System.out.print("Vorher");
            karte.anzeigen();
            karte.abbuchenFitness2h();
        } catch(IllegalArgumentException e) {
            System.out.println("Fehler reim Abbuchen " + e.getMessage());
        }
        System.out.print("Nachher");
        karte.anzeigen();
        System.out.println("");
    }

}
