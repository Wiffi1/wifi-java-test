package genericStack;

// T ist der Typ der Objekte, die auf den Stapel gelegt werden
// bei der Deklaration muss für T eine Klasse oder ein Interface
// angegeben werden
public class MyStack<T> {
    // Array vom unbekannten Typ kann nicht erzeugt werden
    // daher muss man sich mit einem Object-Array behelfen
    private Object[] array ;
    // TODO: Attribut für die Anzahl



    // Erzeugung des Stapels unter Angabe der Kapazität
    public MyStack(int anzahl) {
        array = new Object[anzahl];
    }


    // push: ein Element wird zuoberst auf den Stapel gestellt. Wenn die Kapazität erschöpft ist, soll eine Exception geworfen werden.
    public void push(T elem){
        // TODO: an die oberste Position stellen
        // vorläufig: immer als erstes Element merken
        // implizite Typumwandlung von T nach Object -> geht immer
        array[0] = elem;
    }

    //    pop: das oberste Element wird zurückgeliefert (und vom Stapel entfernt). Wenn kein oberstes Element existiert, soll eine Exception geworfen werden
    public T pop(){
        // TODO: das Element von der obersten Position holen
        // vorläufig: das erste Element holen

        // diese Typumwandlung kann nicht verifiziert werden, sie ist aber
        // auch nicht vermeidbar -> daher mit der Annotation versehen
        @SuppressWarnings("unchecked")
        T element = (T)array[0];

        // TODO: Das Element aus dem Array entfernen

        return element;
    }

    //    peek: das oberste Element wird zurückgeliefert (und am Stapel belassen).
    public T peek(){
        // TODO: analog pop implementieren (ohne entfernen)
        return null;
    }

    //    size: die Anzahl der Elemente am Stapel wird zurückgeliefert
    public int size(){
        // TODO: die Anzahl der Elemente am Stapel liefern
        return 0;
    }

}