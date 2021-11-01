/*  Erzeugung des Stapels unter Angabe der Kapazität
    push: ein Element wird zuoberst auf den Stapel gestellt. Wenn die Kapazität erschöpft ist,
            soll eine Exception geworfen werden.
    pop: das oberste Element wird zurückgeliefert (und vom Stapel entfernt). Wenn kein oberstes
            Element existiert, soll eine Exception geworfen werden
    peek: das oberste Element wird zurückgeliefert (und am Stapel belassen).
    size: die Anzahl der Elemente am Stapel wird zurückgeliefert*/

package genericStack;

// T ist der Typ der Elemente, die auf den Stapel gelegt werden
// bei der Deklaration muss für T eine Klasse oder ein Interface angegeben werden
public class MyStack<T> {

    // Array vom unbekanntem Typ kann in Java nicht erzeugt werden.
    // T[] array ist nicht möglich wegen Type erasion. Folgendes ist also nicht möglich:
    // private T[] array = new T[10];

    // daher muss man sich mit einem Object-Array behelfen
    private Object[] array;

    private int anzahl;
    private int maxAnzahl;

    // Erzeugung des Stapels unter Angabe der Kapazität
    public MyStack(int maxAnzahl) {
        array = new Object[maxAnzahl];
        this.anzahl = 0;
        this.maxAnzahl = maxAnzahl;
    }

    public void push(T element) {
        array[anzahl] = element;
        anzahl++;
    }

    public  T pop() {
        if (anzahl <= 0) {
            throw new StackException("Kein Element mehr vorhanden");
        } else if (anzahl > maxAnzahl - 2) {
            throw new StackException("Kein Platz mehr am Stack");
        } else {
            T element = (T)array[anzahl - 1];
            array[anzahl] = null;
            return (T)element;
        }
    }

    public  T peek() {
//        return anzahl <= 0 ? null : (T)array[anzahl - 1];
        var elem = (T)array[anzahl - 1];
        return (T)elem;
    }

    public int size() {
        // Anazahl der Elemente am Stapel liefern
        return anzahl;
    }

/*    private void removeElement(int position) {
        array[anzahl] = null;
        anzahl--;
    }*/

}
