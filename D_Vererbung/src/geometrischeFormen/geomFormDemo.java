package geometrischeFormen;

public class geomFormDemo {
    public static void main(String[] args) {
        GeomForm rechtEck1 = new Recheck(0, 0, 4, 3);
        System.out.println("## Rechteck erstellt   "  + rechtEck1.toString());
        System.out.printf("Fläche: %f, Unfang %f\n", rechtEck1.getFläche(), rechtEck1.getUmfang());

        GeomForm kreis1 = new Kreis(0, 0, 1.4);
        System.out.println("## Kreis erstellt   "  + kreis1.toString());
        System.out.printf("Fläche: %f, Unfang %f\n", kreis1.getFläche(), kreis1.getUmfang());

    }
}
