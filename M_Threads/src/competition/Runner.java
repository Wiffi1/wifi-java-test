package competition;

import java.util.concurrent.ThreadLocalRandom;

// TODO: Runnable implementieren, 10 Runden laufen
// TODO: toString, name-Property
public class Runner implements Runnable{
    private  final String name;

    public Runner(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void run() {
        ThreadLocalRandom rand = ThreadLocalRandom.current();
        for (int i = 1; i <= 5 && !Thread.interrupted(); i++) {
            System.out.printf("%d. Runde %s\n", i, getName());
            try {
                Thread.sleep(rand.nextInt(1000));
            } catch (InterruptedException e) {
                System.out.println("Der Wetllauf wurde unterbrochen");
                e.printStackTrace();
                break;
            }

        }

        System.out.printf("%s ist im Ziel", name);

    }

    @Override
    public String toString() {
        return String.format("Läufer %s", name);
    }
}
