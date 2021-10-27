package tierePackage2;

import java.time.LocalDate;

// Klasse Hund leittet von Haustier ab
public class Hund extends Haustier {
    private int gewicht;

    public int getGewicht() {
        return gewicht;
    }

    // Das wäre der Kunstuktor,den der Copiler für uns automatisch erzeugen würde,
    // wenn wir keinen Konstruktor deefinieren
/*    public Hund() {
        super();
    }*/

    public Hund(String name, LocalDate datum, int gewicht) {
        // Explizierter Aufruf des Basisklassen-Konstruktors, mit Weitergabe der Parameter.
        super(name, datum); // Aufruf des Basisklassen-Konstruktors, wird auch implizit ausgeführt.
        // Die abgeleitete Klasse initialisiert KEINE Felder der Basisklasse
//        this.kosename = kosename;   // NIE SO MACHEN
//        this.geburtsdatum = geburtsdatum;   // NIE SO MACHEN

        this.gewicht = gewicht;
        System.out.println("Konstructor von Hund");
    }

    public void belle() {
        System.out.printf("%s macht wau wau! \n", getKosename());
    }


    // Methode mit gleicher Signatur wie in der Basisklasse
    // wird auch aufgerufen, wenn eine Haustierreferenz auf ein Hundobjekt verweist
    @Override
    public void zeigeDich() {
        // Methode aus der Basisklasse ausführen
        super.zeigeDich();
        // Jetzt die eigenen Infofmationen anzeigen
        System.out.printf("Ich bin ein Hund, ich habe %d kg\n", gewicht);
        belle();   // !!!!!!
    }

    // final Methode darf nicht überschrieben werden
//    @Override
//    public String getKosename() {
//        return kosename;
//    }


    @Override
    public String toString() {
        return super.toString() + ", gewicht=" + gewicht;
    }
}
