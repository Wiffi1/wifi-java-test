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
        return String.format("Kreis: %s Radius: %f", super.toString(), radius);
    }

    @Override
    boolean isHit(double xHitKoord, double yHitKoord) {
        double dx = - xHitKoord - xKoord;
        double dy = - yHitKoord - yKoord;
        double testRad =  Math.sqrt(dx * dx  + dy * dy);
        return testRad <= radius;
    }

}
