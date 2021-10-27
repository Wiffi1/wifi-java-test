package geometrischeFormen;

public abstract class GeomForm {
    protected double xKoord;
    protected double yKoord;
    abstract double getFläche();
    abstract double getUmfang();
    abstract boolean  isHit(double xHitKoord, double yHitKoord);

    @Override
    public String toString() {
        return "GeomForm{" +
                "xKoord=" + xKoord +
                ", yKoord=" + yKoord +
                '}';
    }
}
