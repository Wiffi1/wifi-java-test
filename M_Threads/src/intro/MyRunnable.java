package intro;

import java.util.concurrent.ThreadLocalRandom;

public class MyRunnable implements Runnable {
    private final String name;

    public MyRunnable(String name) {
        this.name = name;
    }

    @Override
    public void run(){
        // Random-Generator für diesen Thread holen
        ThreadLocalRandom random = ThreadLocalRandom.current();
        // Das interrupted-Flag prüfen und ggf abbrechen
        for (int i = 1; i<= 25 && !Thread.interrupted(); i++){
            System.out.printf("%s: Durchlauf %d\n", name, i);
            try {
                Thread.sleep(random.nextInt(500));
            } catch (InterruptedException e) {
                // Wenn exception ausgeölst wird, heißt das, dass der Thread mit interrupt() beendet wurde
//                e.printStackTrace();
                System.out.printf("Runnalbe %s wurde mit InterruptedException aufgeweckt\n", name);
                // Die Schleife verlassen und damit die run-Methode beenden
                break;
            }
        }
    }
}
