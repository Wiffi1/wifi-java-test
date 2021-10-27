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

    @Override
    boolean isHit(double xHitKoord, double yHitKoord) {
        double xKoordMax = xKoord + laenge;
        double yKoordMax = yKoord + breite;
        boolean isInKoordX = xKoord <= xHitKoord && xHitKoord <= xKoordMax;
        boolean isInKoordY = yKoord <= yHitKoord && yHitKoord <= yKoordMax;

        return isInKoordX && isInKoordY;
    }
}
