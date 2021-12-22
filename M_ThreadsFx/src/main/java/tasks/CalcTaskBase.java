package tasks;

import javafx.concurrent.Task;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadLocalRandom;

public abstract class CalcTaskBase extends Task<Double> {
    protected final int count;


    protected CalcTaskBase(int count) {
        this.count = count;
    }



    // Methode die relativ lang braucht um einen (zufälligen) Wert zu berechnen
    protected double calcValue() {
        double result = 0;

        ThreadLocalRandom random = ThreadLocalRandom.current();
        for (int i = 0; i < 1000000 && !Thread.interrupted(); i++) {
            if (i % 2 == 0)
                result += random.nextDouble(50);
            else
                result -= random.nextDouble(50);
        }
        return result;

    }

    public abstract double runSync() throws ExecutionException, InterruptedException;
}
