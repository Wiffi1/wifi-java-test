/*Geometrische Formen

        Schreibe ein Programm, das zu verschiedenen geometrischen Formen die Fläche und den Umfang ermittelt und (optional) einen Hit-Test für einen beliebigen Punkt durchführt. Definiere eine gemeinsame Basisklasse für alle Geometrischen Formen mit folgender Funktionalität:

        Jede geometrische Form liegt an einer x/y Koordinate
        Ermitteln der Fläche
        Ermitteln des Umfangs
        Optional: Hit-Test für eine Koordinate: damit wird ermittelt, ob die angegebene Koordinate innerhalb der Form liegt

        Folgende konkrete Formen sollen implementiert werden:

        1) Rechteck

        Ein Rechteck hat Werte für die Länge und die Breite
        Fläche = Länge x Breite
        Umfang = 2 * Länge + 2 * Breite
        Optional: Hit-Test: testX muss auf dem x-Achsen-Bereich, testY muss auf dem y-Achsen-Bereich liegen (die Koordinate ist z.B. die linke untere Ecke)

        2) Kreis

        Ein Kreis hat einen Wert für den Radius
        Fläche = Radius * Radius * PI
        Umfang = 2 * Radius * PI
        Optional: Hit-Test: Pythagoras aus x- und y-Distanzen als Katheten und Radius als Hypotenuse (die Koordinate ist der Mittelpunkt des Kreises), s. Skizze unten

        Erstelle vor dem Implementieren ein Klassendiagramm und überlege, welche Methoden virtuell oder abstrakt sein müssen!

        Teste die Klassen mit einem geeigneten Testprogramm.

        Hinweis: Die Konstante PI ist in der Klasse Math definiert.

        Hilfestellung für den Hit-Test Kreis findest du im beigfügten File (dx und dy sind die Abstände zwischen den Koordinaten des getesteten Punktes und denen des Kreises).*/

package geometrischeFormen;

public class geomFormDemo {

    public static void main(String[] args) {

        GeomForm rechtEck1 = new Recheck(0, 0, 4, 3);
        GeomForm kreis1 = new Kreis(0, 0, 2);

        GeomForm[] geoForms = new GeomForm[] {
                rechtEck1,
                kreis1
        };

        for (GeomForm geoForm : geoForms) {
            System.out.println("");
            System.out.println("## GeoForm erstellt   "  + geoForm.toString());
            System.out.printf("Fläche: %f, Unfang %f\n", geoForm.getFläche(), geoForm.getUmfang());
        }

        System.out.println("");
        System.out.println("Test Rechteck");
        System.out.println("Treffer erwartet");
        testeRechteck(rechtEck1, 2.0, 2.0);

        System.out.println("false als Ergebnis erwartet");
        testeRechteck(rechtEck1, 5.0, 2.0);
        testeRechteck(rechtEck1, 2.0, 5.0);
        testeRechteck(rechtEck1, 7.0, 8.0);
        testeRechteck(rechtEck1, -1.0, -1.0);

        System.out.println("");
        System.out.println("Kreis");

        System.out.println("true als Ergebnis erwartet");
        testeKreis(kreis1, 2.0, 0.0);
        testeKreis(kreis1, 0.0, 2.0);


        System.out.println("false als Ergebnis erwartet");
        testeKreis(kreis1, 3.0, 0.0);
        testeKreis(kreis1, 0.0, 3.0);
    }

    private static void testeKreis(GeomForm kreis, double testX, double testY) {
        System.out.printf("!!!kreis, %b \n" , kreis instanceof Kreis);
        System.out.printf("isHit %f/%f => %b\n", testX, testY, kreis.isHit(testX, testY));
    }

    private static void testeRechteck(GeomForm rechteck, double testX, double testY) {
        System.out.printf("isHit %f/%f => %b\n", testX, testY, rechteck.isHit(testX, testY));
    }

    private static void testBeingHit(GeomForm geoForm, double testX, double testY) {
        System.out.printf("isHit %f/%f => %b\n", testX, testY, geoForm.isHit(testX, testY));
    }

}



