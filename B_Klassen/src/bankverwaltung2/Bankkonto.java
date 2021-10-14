package bankverwaltung2;

/*
Schreibe eine Klasse, die ein Bankkonto kapselt.

        Ein Bankkonto enthält eine Kontonummer, den Kontostand sowie weitere Informationen wie den Namen des Inhabers, den Zinssatz und den Überziehungsrahmen.
        Außerdem verfügt ein Bankkonto über ein Feld für den Kontotyp (Sparkonto oder Gehaltskonto).
        Bei Sparkonten wird der Zinssatz im Zuge der Eröffnung festgelegt, bei Gehaltskonten beträgt der Zinssatz immer 0,1%.
        Bei Gehaltskonten kann bei der Eröffnung ein Überziehungsrahmen festgelegt werden, Sparkonten erlauben keine Kontoüberziehung.

        Folgende Funktionen sollen zur Verfügung stehen:

        Sparbuch eröffnen: Die Kontonummer, der Name des Inhabers, der Zinssatz und die Höhe der Ersteinlage werden übergeben.
        Gehaltskonto eröffnen: Die Kontonummer, der Name des Inhabers und die Höhe des Überziehungsrahmens werden übergeben.
        Einzahlen: Ein anzugebender Betrag wird dem Kontostand gutgeschrieben. Der Betrag darf 15.000 €,- nicht überschreiten.
        Abheben: Ein anzugebender Betrag wird vom Kontostand abgezogen, wenn der Kontostand (inkl. Überziehungsrahmen) hoch genug ist.
        Kontoinformation: Es werden die Konto-Informationen (Nummer, Inhaber, Typ, aktueller Kontostand usw.) ausgegeben.

        Wenn eine Aktion nicht ausgeführt werden kann (Betrag zu hoch, Kontonummer nicht vorhanden usw.), muss ein Fehler ausgelöst und dem Benutzer angezeigt werden.

        Erstelle ein Klassen-Design für die Klasse Bankkonto. Implementiere anschließend die Klasse und teste die Funktionen mit einem geeigneten Testprogramm.
*/

public class Bankkonto {
    private int kontonummer;
    private String inhaber;
    private double kontostand;
    private double zinssatz;
    private double ueberziehungsrahmen;
    private String kontotyp;


    public Bankkonto(int kontonummer, String  inhaber, double zinssatz, double ersteinlage) {
        this.sparbuchEroeffnen(kontonummer, inhaber, zinssatz, ersteinlage);
    }

    public Bankkonto(int kontonummer, String  inhaber, double ueberziehungsrahmen) {
        this.gehaltskontoEroeffnen(kontonummer, inhaber, ueberziehungsrahmen);
    }

    public void anzeigen() {
        if (kontotyp.equals("Sparbuch")) {
            System.out.printf("Konto Nr=%d, Inhaber=%s, kontotyp=%s, Kontostand=%.2f EUR, zinssatz=.2f \n", kontonummer, inhaber, kontotyp, kontostand, zinssatz);
        } else {
            System.out.printf("Konto Nr=%d, Inhaber=%s, kontotyp=%s, Kontostand=%.2f EUR ueberziehungsrahmen=.2f\n", kontonummer, inhaber, kontotyp, kontostand, ueberziehungsrahmen);
        }
    }



    public void sparbuchEroeffnen(int kontonummer, String  inhaber, double zinssatz, double ersteinlage) {
        this.kontonummer = kontonummer;
        this.inhaber = inhaber;
        this.zinssatz = zinssatz;
        this.einzahlen(ersteinlage);
        this.kontotyp = "Sparbuch";
        anzeigen();
    }

    public void gehaltskontoEroeffnen(int kontonummer, String  inhaber, double ueberziehungsrahmen) {
        this.kontonummer = kontonummer;
        this.inhaber = inhaber;
        this.zinssatz = 0.1;
        this.ueberziehungsrahmen = ueberziehungsrahmen;
        this.kontotyp = "Gehaltskonto";
        anzeigen();
    }

    public void einzahlen(double betrag) {
        if (betrag > 15000) {
            throw new IllegalArgumentException("KontoNr: " + kontonummer + " Betrag für Aufbuchung zu hoch (> 15000)");
        }
        this.kontostand += betrag;
    }

    public void abheben(double betrag) {
        if (betrag > kontostand) {
            throw new IllegalArgumentException("KontoNr: " + kontonummer + "Betrag für Abbuchung zu hoch (> Kontostand)");
        }
        this.kontostand -= betrag;
    }
}
