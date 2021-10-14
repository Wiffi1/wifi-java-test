package fitnesscenter;

public class Wertkarte {
    // Attribute (=Feld, Membervariable) für Nr, Inhaber, uew
    // Attribute definieren die Daten
    private int nr;
    private String inhaber;
    private double guthaben;

    public void setzen(int nr, String inhaber) {
        // die Wert ins Objekt übernehmen.
        this.nr = nr;
        this.inhaber = inhaber;
    }

    public void anzeigen() {
        System.out.printf("Wertkarte Nr=%d, Inhaber=%s, Guthaben=%.2f EUR\n", nr, inhaber, guthaben);
    }

    // Methoden definieren die Fähigkeiten
    // public Methoden dürfen von überall verwendet werden, und stellen die Schnittstelle der Klasse dar.
    public void aufbuchen(double betrag) {
        if (guthaben + betrag > 300) {
//            System.out.println("Aufbuchen; Betrag zu hoch");
//            statt Ausgabe. Fehler auslösen.
//            damit wird die Ausführung unterbrochen und am Call Stack ein passender Catch-Blcck gesucht.
//            Wenn es einen gibt, geht die Ausführung dort weiter, wenn nicht wird das Programm beendet.
                throw new IllegalArgumentException("Betrag für Aufbuchung zu hoch");
        }
        // Wenn wir hier herkommen wurde der Betrag akzeptiert.
        guthaben += betrag;
    }

    public void abbuchenGymnastik() {
        abbuchen(7, "Gymnastik");
    }

    public void abbuchenFitness2h() {
        abbuchen(14, "Fitness 2 Stunden");
    }

    // Private Methoden dürfen nur von der Klasse selbst aufgerufen werden.
    private void abbuchen(double betrag, String typ) {
        if (guthaben - betrag < 0) {
//            System.out.println("Abbuchen " + typ + ": Guthaben zu klein");
            throw new IllegalArgumentException("Guthaben zu klein für Abbuchung " + typ);
        }
        guthaben -= betrag;
    }

}
