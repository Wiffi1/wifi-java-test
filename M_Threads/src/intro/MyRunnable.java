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
        for (int i = 1; i<= 50; i++){
            System.out.printf("%s: Durchlauf %d\n", name, i);
            try {
                Thread.sleep(random.nextInt(500));
            } catch (InterruptedException e) {
                // TODO: InterruptedException korrekt behandeln
                e.printStackTrace();
            }
        }
    }
}
