package genericBox;

// Generische Klasse kann ein Element vom Typ Unbekannter_Typ enthalten
// Unbekannter_Typ ist das Typargument, muss bei der Verwendung der Klasse
// angegeben werden
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
        return String.format("Box mit Wert %s", value);
    }
}
