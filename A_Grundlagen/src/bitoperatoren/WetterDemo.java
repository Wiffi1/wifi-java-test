package bitoperatoren;

import java.sql.SQLOutput;
import java.time.LocalDate;

public class WetterDemo {
    public static void main(String[] args) {

        byte bits = Wetter.SONNE | Wetter.WOLKEN;
        Wetter wetterWien = new Wetter("Wien", LocalDate.now(), bits);
        // Testen ob Sonne vorkommt.

        System.out.println(wetterWien.toString());

        if (wetterWien.enthaeltBits(Wetter.SONNE)) {
            System.out.println("Es ist sonnig :-)");
        } else {
            System.out.println("ELeider kine Sonne :-(");
        }

        wetterWien.bitsDazu(Wetter.WWIND);
        System.out.println("wetterWien neu: " + wetterWien);

        Wetter wetterInnsbruck = new Wetter("Innsbruck", LocalDate.of(2012, 10, 13),
                // type cast ist nötig, weil die it-Verknüpfungen alsERgebnistpe int haben
                (byte)(Wetter.SCHNEE | Wetter.WWIND | Wetter.WOLKEN));
        wetterInnsbruck.bitsEntfernen(Wetter.SCHNEE);
        System.out.println("wetterInnsbruck neu: " + wetterInnsbruck);

    }

}
