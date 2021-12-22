package competition;

import java.util.concurrent.ThreadLocalRandom;

// TODO: Runnable implementieren, 5 Runden laufen
// TODO: toString, name-Property
public class Runner implements Runnable{
    private  final String name;

    // der Platz, den der Läufer erreicht hat.
    private int place;
    // Zähler für die Platzierungen
    private static int nextPlace = 1;

    // Sync Objekt für den Zugriff auf den Zähler, muss unveränderlich sein.
    private static final Object syncObject = new Object();

    public Runner(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getPlace() {
        return place;
    }

    @Override
    public void run() {
        ThreadLocalRandom rand = ThreadLocalRandom.current();
        for (int i = 1; i <= 5 && !Thread.interrupted(); i++) {
            // Der Lauf soll parallel statfinden, keine Thread Synchronisation
            System.out.printf("%d. Runde %s\n", i, getName());
            try {
                Thread.sleep(rand.nextInt(1000));
            } catch (InterruptedException e) {
                System.out.println("Der Wetllauf wurde unterbrochen");
                e.printStackTrace();
                break;
            }

        }

        // Platzierung festlegen muss synchronisiert werden
        // warten bis das Sync-Objekt frei ist. (weil wir ein statisches
        // Objekt verwenden darf nur ein Thread gleichzeitig diesen Code-Block ausführen)
        synchronized (syncObject) {
            System.out.printf("%s ist im Ziel\n", name);
            // folgender Befehl mindestens 3 Befehle für CPU
            // es gibt nur wenige atomare Operationen
            place = nextPlace;
            try {
                // die Race Condition mit einem Sleep provozieren
                Thread.sleep(200);
            } catch (InterruptedException e) {
//            e.printStackTrace();
                // nichts mehr zu tun wir sind schon fertig
            }
            nextPlace ++;
        }
    }

    @Override
    public String toString() {
        return String.format("Läufer %s", name);
    }
}
