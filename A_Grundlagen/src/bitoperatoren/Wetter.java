package bitoperatoren;

import java.time.LocalDate;

public class Wetter {

    public static final byte SONNE=1, WOLKEN=2, REGEN=4, SCHNEE=8, WWIND=16, NEBEL=32;

    private String stadt;
    private LocalDate datum;
    private byte wetterBits;

    public Wetter(String stadt, LocalDate datum, byte bits) {
        this.stadt = stadt;
        this.datum = datum;
        this.wetterBits = bits;
    }

    /**
     * tested ob die angegebenen its enthalten sind
     * @param pruefBits die Bits, die getestet werden
     * @return
     */
    public boolean enthaeltBits(byte pruefBits) {
        // wenn in den Weterbits des Objekts alle bits aus dem Prüfwert gesetzt sind, liefern wir true zurück, sonst false
//        if ((wetterBits & pruefBits) == pruefBits) {
//            return true;
//        } else {
//            return false;
//        }
        // kürzer: Das Ergebnis de sVergleich diekt zurückliefern.
        return (wetterBits & pruefBits) == pruefBits;
    }

    /**
     * die angegebenn Bits in das Wetterbit des Objekts hinzufügen
     * @param bits
     */
    public void bitsDazu(byte bits) {
        this.wetterBits |= bits;
    }

    /**
     * die angegebenn Bits aus dem Wetterbit des Objekts entfernen
     * @param bits
     */
    public void bitsEntfernen(byte bits) {
        // bits:        00000100 soll entfernt werden
        // ~bits:       11111011 ist der Kehrwert
        // wetterbots   00001101
        // ergebnis     00001001
//        int neueBits = wetterBits & (~bits);
//        wetterBits =  neueBits;
        wetterBits &= (~bits);
    }

    @Override
    public String toString() {
//        return "Wetter{}";
        return String.format("Wetter in %s am %s ist [%8s]\n", stadt, datum,
                // Binärdarstelllung der Zahl
                Integer.toBinaryString(wetterBits));
    }
}
