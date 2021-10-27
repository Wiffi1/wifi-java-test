package exceptions;

public class CalculationException extends RuntimeException {
    // Konstruktor mit Angabe des Fehlertextes.
    public CalculationException(String msg) {
        // Den Fehlertext an die Basisklasse weitergeben.
        super(msg);
    }

    public CalculationException(String msg, Exception cause) {
        // Den Fehlertext und die auslösende Exception an die Basisklasse weitergeben.
        super(msg, cause);
    }

}
