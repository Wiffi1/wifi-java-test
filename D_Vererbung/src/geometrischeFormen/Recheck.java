package geometrischeFormen;

public class Recheck extends GeomForm {
    private double laenge;
    private double breite;

     public Recheck(double xKoord, double yKoord, double laenge, double breite) {
         this.xKoord = xKoord;
         this.yKoord = yKoord;
         this.laenge = laenge;
         this.breite = breite;
     }

    @Override
    double getFläche() {
         return laenge * breite;
    }

    @Override
    double getUmfang() {
        return 2 * (laenge + breite);
    }

    @Override
    public String toString() {
         String geoFormInfo = super.toString();
         String subInfo = "Recheck{" +
                "laenge=" + laenge +
                ", breite=" + breite +
                '}';
         return geoFormInfo + "    " + subInfo;
    }
}
