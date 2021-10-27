package tierePackageAbstract;

import java.time.LocalDate;

public class TiereDemoAbstract {
    public static void main(String[] args) {
        System.out.println("Haustiere mit abstrakrer Basisklasse");
        testeTier(new Hund("Rex", LocalDate.of(2006, 12, 17), 22));
        testeTier(new Katze("Minki", LocalDate.of(2006, 12, 17), "Wollknäuel"));
    }

    static void testeTier(Haustier meinTier) {
        System.out.printf("Teste Tier vom Typ %s mit Namen %s\n",
                meinTier.getClass().getName(),
                meinTier.getKosename()
        );
        meinTier.bewegDich();
        meinTier.zeigeDich();
        System.out.printf("%s ist %d Jahre alt\n", meinTier.getKosename(), meinTier.getAlter());
        // Nicht möglich
        // meinTier.belle();
        // Das Haustier-Objekt an der Konsole anzeigen (es wird die toString-
        System.out.println(meinTier/*toString*/);

        System.out.println("typischw e Bewegung");
//        meinTier.belle();

        System.out.println();
    }
}
