package genericStack;

// TODO als generische Klasse defineiren
// T ist der Typ der Elemente, die auf den Stapel gelegt werden
// bei der Deklaration muss für T eine Klasse oder ein Interface angegeben werden
public class MyStack<T> {
    // Geht nicht in Java!
//    private T[] array = new T[10];

    // array vom unbekannen Typ kann nicht erezgut werden,
    // daher muss man sich mit einem Object-Array behelfen
    private Object[] array;

    private int anzahl = 0;
    private int maxAnzahl = 0;

    public void push(T element) {
        array[0] = element;
    }

    public  T pop() {
        // TODO das Element von der obersten Position holen
        // TODO das Element su dem Array entfernen (null setzen und anzahl verminderrn)
        // unccol
//        Object element = array[0];
//        return (T) element;
        @SuppressWarnings("unchecked")
        T element = (T)array[0];
        return element;
    }
    public  T peek() {
        // TODO das Element von der obersten Position holen
        return null;
    }
    public int size() {
        // Anazahl der Elemente am Stapel lieferen
        return 0;
    }

    // TODO: Attribute für das Array und die Anzahl
    // TODO: die Methoden-Signatuen definieren


/*    Erzeugung des Stapels unter Angabe der Kapazität
    push: ein Element wird zuoberst auf den Stapel gestellt. Wenn die Kapazität erschöpft ist, soll eine Exception geworfen werden.
            pop: das oberste Element wird zurückgeliefert (und vom Stapel entfernt). Wenn kein oberstes Element existiert, soll eine Exception geworfen werden
    peek: das oberste Element wird zurückgeliefert (und am Stapel belassen).
    size: die Anzahl der Elemente am Stapel wird zurückgeliefert*/

    // Erzeugung des Stapels unter Angabe der Kapazität
    public MyStack(int anzahl) {
        array = new Object[anzahl];
        this.maxAnzahl = 0;
    }
}
