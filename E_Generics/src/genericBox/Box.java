package genericBox;

// Generische Klasse kann ein element vom Txp Unbekannter_Typ enthalten
// Unbekannter_Typ ist das Typargument, muss bei der Verwendung der Klasse
// angegeben werde
public class Box<Unbekannter_Typ> {
    private Unbekannter_Typ value;

    public Unbekannter_Typ getValue() {
        return value;
    }

    public void setValue(Unbekannter_Typ value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.format("tBox mit Wert %s", value);
    }
}
