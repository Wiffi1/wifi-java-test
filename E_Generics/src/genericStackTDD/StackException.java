package genericStackTDD;

public class StackException extends RuntimeException{
    // Konstruktor mit Angabe des Fehlertextes
    public StackException(String msg) {
        // den Fehlertext an die Basisklasse weitergeben
        super(msg);
    }

    // Konstruktor mit Angabe des Fehlertextes und des auslösenden Fehlers
    public StackException(String msg, Exception cause){
        // den Fehlertext und den auslösenden Fehler an die Basisklasse weitergeben
        super(msg, cause);
    }
}

