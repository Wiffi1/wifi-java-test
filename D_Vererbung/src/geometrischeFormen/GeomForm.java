package geometrischeFormen;

public abstract class GeomForm {
    protected double xKoord;
    protected double yKoord;
    abstract double getFläche();
    abstract double getUmfang();

    @Override
    public String toString() {
        return "GeomForm{" +
                "xKoord=" + xKoord +
                ", yKoord=" + yKoord +
                '}';
    }
}
