package intro;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class ThreadDemo {
    public static void main(String[] args) {
        MyRunnable runnable1 = new MyRunnable("Runnable 1"), runnable2 = new MyRunnable("Runnable 2");

//        // run direkt ausführen (= run synchron ausführen) -> nicht multithreaded
//        // wir warten hier, bis die Methode zu ende ist
//        runnable1.run();
//        runnable2.run();
//        System.out.println("Synchroner Aufruf beendet");

        // jeweils in eigenem Thread starten (= run asynchron ausführen)
        // a) Thread-Objekt erzeugen
        Thread t1 = new Thread(runnable1), t2 = new Thread(runnable2);
//        t1.setDaemon(true);
        // nur t2 als Daemon configurieren
        t2.setDaemon(true);

        // b) ... und starten (damit wird die jeweilige run-Implementierung ausgeführt)
        // nach dem Starten kehrt die Methode sofort zurück
        t1.start();
        t2.start();

        // Thread mit Lambda Expression erzeugen und starten
        Thread t3 = new Thread(() -> {
            ThreadLocalRandom random = ThreadLocalRandom.current();
            for (int i = 1; i <= 10 && !Thread.interrupted(); i++) {
                System.out.printf("Lambda 1: Durchlauf %d\n", i);
                try {
                    Thread.sleep(random.nextInt(200));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    // Schleife verlassen und run-Methode beenden.
                    break;
                }
            }
        });
        //t3.setDaemon(true);
        t3.start();

        System.out.println("Alle Threads gestartet!");

        try {
            System.out.println("Warte auf Ende von t3");
            t3.join();
            System.out.println("t3 ist beendet");
        } catch (InterruptedException e) {
            // OK, wird hier nicht vorkommen (beenden von main-Thread)
            e.printStackTrace();
        }

        // das Programm beeden
        System.out.println("Beenden mit Enter");
//        Scanner scan = new Scanner(System.in);
//        scan.nextLine();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // den 1. Thread beenden
        t1.interrupt();

        System.out.println("Main-Thread beendet.");
    }
}
