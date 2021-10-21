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
    }

    static void testeTier(Haustier meinTier) {
        System.out.printf("Teste Tier mit Namen %s\n", meinTier.getKosename());
        meinTier.zeigeDich();
        System.out.printf("%s ist %d Jahre alt\n", meinTier.getKosename(), meinTier.getAlter());
        // Nicht möglich
        // meinTier.belle();
    }
}
