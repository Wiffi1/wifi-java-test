package introPackage;

import javax.swing.*;

public class HelloSwingOld {
    public static void main(String[] args) {
        // Ein Objekt für unser Hauptfenster erzeugen
        JFrame hauptfenster = new JFrame("Erstes Swing-Programm");
        hauptfenster.setSize(300, 400);

        // beim Klick auf Schließen das soll das Fenster geschlossen werden
        hauptfenster.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        // und anzeigen
        hauptfenster.setVisible(true);
    }
}
