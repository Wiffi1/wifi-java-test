package tierePackage2;

import java.time.LocalDate;

public class TiereDemo2b {
    public static void main(String[] args) {
        Haustier minna =  new Haustier("Minna", LocalDate.of(2005, 12, 3));
        Hund rex = new Hund("Rex", LocalDate.of(2015, 12, 30), 21);
        // Methode aus Hund
        rex.belle();

        testeTier(minna);

        // typumwandlung: Hund nach Haustier
        Haustier meinTier = rex;
        testeTier(meinTier);

        Katze minki = new Katze("Minki", LocalDate.of(2014, 8, 15), "Wollknäuel");
        // Typumwandlung: Katze nach Haustier (weil der Parameter vom Typ Haustier ist.)
        testeTier(meinTier);
    }

    static void testeTier(Haustier meinTier) {
        System.out.printf("Teste Tier vom Typ %s mit Namen %s\n",
                meinTier.getClass().getName(),
//                meinTier.getClass(),
                meinTier.getKosename()
        );
        meinTier.zeigeDich();
        System.out.printf("%s ist %d Jahre alt\n", meinTier.getKosename(), meinTier.getAlter());
        // Nicht möglich
        // meinTier.belle();

        // Das Haustier-Objekt an der Konsole anzeigen (es wird die toString-
        System.out.println(meinTier/*toString*/);
    }
}
