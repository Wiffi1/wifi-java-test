package geometrischeFormen;

public class geomFormDemo {
    public static void main(String[] args) {
/*        GeomForm rechtEck1 = new Recheck(0, 0, 4, 3);
        System.out.println("## Rechteck erstellt   "  + rechtEck1.toString());
        System.out.printf("Fläche: %f, Unfang %f\n", rechtEck1.getFläche(), rechtEck1.getUmfang());

        GeomForm kreis1 = new Kreis(0, 0, 1.4);
        System.out.println("## Kreis erstellt   "  + kreis1.toString());
        System.out.printf("Fläche: %f, Unfang %f\n", kreis1.getFläche(), kreis1.getUmfang());*/

        GeomForm rechtEck1 = new Recheck(0, 0, 4, 3);
        GeomForm kreis1 = new Kreis(0, 0, 1.4);

        GeomForm[] geoForms = new GeomForm[] {
            rechtEck1,
            kreis1
        };


        for (GeomForm geoForm : geoForms) {
//            System.out.println(geoForm.toString());
            System.out.println("");
            System.out.println("## GeoForm erstellt   "  + geoForm.toString());
            System.out.printf("Fläche: %f, Unfang %f\n", geoForm.getFläche(), geoForm.getUmfang());
        }

        rechtEck1.isHit(2, 2);
//        System.out.printf("isHit %f/%f == %b", 2, 2, rechtEck1.isHit(2, 2));
        System.out.println("");
        System.out.printf("isHit %f/%f => %b\n", 2.0, 2.0, rechtEck1.isHit(2, 2));
        System.out.printf("isHit %f/%f => %b\n", 5.0, 2.0, rechtEck1.isHit(5, 2));
        System.out.printf("isHit %f/%f => %b\n", 2.0, 5.0, rechtEck1.isHit(2, 5));
        System.out.printf("isHit %f/%f => %b\n", 7.0, 8.0, rechtEck1.isHit(7, 8));
        System.out.printf("isHit %f/%f => %b\n", -1.0, -1.0, rechtEck1.isHit(-1.0, -1.0));
    }
}
