package geometrischeFormen;

public class Kreis extends GeomForm{
    double radius;

    public Kreis(double xKoord, double yKoord, double radius) {
        this.xKoord = xKoord;
        this.yKoord = yKoord;
        this.radius = radius;
    }

    @Override
    double getFläche() {
        return Math.PI * radius * radius;
    }

    @Override
    double getUmfang() {
        return 2 * Math.PI * radius;
    }

    @Override
    public String toString() {
        String geoFormInfo = super.toString();
        String subInfo = "Kreis{" +
                "radius=" + radius +
                '}';
        return geoFormInfo + "    " + subInfo;
    }
}
