package bankverwaltung.oopSort;

public class Bankkonto implements  Comparable<Bankkonto> {

    public static final double MAX_EINZAHLUNG = 15000;
    final private Kontotypen kontotyp;
    final private int kontonummer;
    private double kontostand, uzRahmen, zinssatz;
    private String inhaber;

    private static int nextNummer = 4711;

    /**
     * Bankkonto als Sparkonto erzeugen
     *
     * @param inhaber  der Name des Inhabers
     * @param zinssatz Zinssatz für das Konto
     * @param einlage  die Eröffnungseinlage
     */
    public Bankkonto(String inhaber, double zinssatz, double einlage) {
        // privaten Konstruktor aufrufen
        this(Kontotypen.SPARKONTO, inhaber, zinssatz, einlage, 0.0);
        // statt doppeltem Code in den Konstruktoren
//        this.kontonummer = nextKontonummer();
//        this.inhaber = inhaber;
//        this.zinssatz = zinssatz;
//        this.kontostand = einlage;
//        this.uzRahmen = 0;
//        this.kontotyp = Kontotypen.SPARKONTO;

    }

    /**
     * Bankkonto als Gehaltskonto erzeugen
     *
     * @param inhaber  der Name des Inhabers
     * @param uzRahmen der Überziehungsrahmen
     */
    public Bankkonto(String inhaber, double uzRahmen) {
        // privaten Konstruktor aufrufen
        this(Kontotypen.GEHALTSKONTO, inhaber, 0.1, 0.0, uzRahmen);
//        this.kontonummer = nextKontonummer();
//        this.inhaber = inhaber;
//        this.zinssatz = 0.1;
//        this.kontostand = 0;
//        this.uzRahmen = uzRahmen;
//        this.kontotyp = Kontotypen.GEHALTSKONTO;


    }

    /**
     * Bankkonto mit allen Informationen erzeugen
     *
     * @param kontotyp der Kontotyp
     * @param inhaber  der Name des Inhabers
     * @param zinssatz Zinssatz für das Konto
     * @param einlage  die Eröffnungseinlage
     * @param uzRahmen der Überziehungsrahmen
     */
    private Bankkonto(Kontotypen kontotyp, String inhaber, double zinssatz, double einlage, double uzRahmen) {
        this.kontonummer = nextKontonummer();
        this.inhaber = inhaber;
        this.zinssatz = zinssatz;
        if (einlage > MAX_EINZAHLUNG) {
            throw new IllegalArgumentException("Eröffnungseinlage zu hoch");
        }
        this.kontostand = einlage;
        this.uzRahmen = uzRahmen;
        this.kontotyp = kontotyp;

    }


    /**
     * @return der Kontotyp
     */
    public Kontotypen getKontotyp() {
        return kontotyp;
    }

    /**
     * @return die Kontonummer
     */
    public int getKontonummer() {
        return kontonummer;
    }

    /**
     * @return der Kontostand
     */
    public double getKontostand() {
        return kontostand;
    }


    /**
     * @return der Inhaber
     */
    public String getInhaber() {
        return inhaber;
    }

    /**
     * neuen Inhaber setzen
     *
     * @param inhaber der neue Inhaber
     */
    public void setInhaber(String inhaber) {
        this.inhaber = inhaber;
    }

    /**
     * @return der Zinssatz
     */
    public double getZinssatz() {
        return zinssatz;
    }

    /**
     * neuen Zinssatz setzen
     * @param zinssatz der neue Zinssatz
     */
    public void setZinssatz(double zinssatz) {
        this.zinssatz = zinssatz;
    }

    /**
     * @return der Überziehungsrahmen
     */
    public double getUeberziehungsRahmen() {
        return uzRahmen;
    }

    /**
     * neuen Überziehungsrahmen setzen
     * @param uzRahmen der neue Überziehungsrahmen
     */
    public void setUeberziehungsRahmen(double uzRahmen) {
        this.uzRahmen = uzRahmen;
    }


    /**
     * generiert die nächste mögliche Kontonummer
     *
     * @return die nächste Kontonummer
     */
    private static int nextKontonummer() {
        return ++nextNummer;
    }

    /**
     * Betrag abheben, wenn der Kontostand es zulässt
     *
     * @param betrag der Betrag welcher abgehoben werden soll
     */
    public void abheben(double betrag) {
        if (betrag > kontostand + uzRahmen) {
            throw new IllegalArgumentException("Betrag für Abhebung zu hoch");
        }
        kontostand -= betrag;
    }

    /**
     * Betrag einzahlen
     *
     * @param betrag der Betrag welcher eingezahlt werden soll
     */
    public void einzahlen(double betrag) {
        if (betrag > MAX_EINZAHLUNG) {
            throw new IllegalArgumentException("Betrag für Einzahlung zu hoch");
        }
        kontostand += betrag;
    }


    /**
     * alle Daten zum Konto anzeigen
     */
    public void kontoInformation() {
        System.out.printf("Nr= %d, Inhaber=%s, Typ=%s, Kontostand=%.2f, Zinssatz=%.2f",
                kontonummer, inhaber, kontotyp, kontostand, zinssatz);
        if (kontotyp == Kontotypen.GEHALTSKONTO) {
            System.out.printf(", ÜZ-Rahmen=%.2f", uzRahmen);

        }
        System.out.println();

    }

    @Override
    public int compareTo(Bankkonto o) {
        int ret = this.getInhaber().compareTo(o.getInhaber());
//        System.out.printf("compareTo  %s mit %s: ret=%d\n", this.getInhaber(), o.getInhaber(), ret);
        return ret;
    }

}
