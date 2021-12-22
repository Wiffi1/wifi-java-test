package tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import javafx.concurrent.Task;

public class CalcTask extends CalcTaskBase {


    public CalcTask(int count) {
        super(count);
    }

    // synchrone Variante, ohne Fortschrittsanzeige
    @Override
    public double runSync() throws ExecutionException, InterruptedException {
        System.out.printf("CalcTask.runSync in Thread %s\n", Thread.currentThread().getName());

        double sum = 0;
        for (int i = 0; i < count; i++) {
            double value = calcValue();
            sum += value;
            // Ausgabe an der Konsole, damit wir sehen, dass sich etwas tut
            if (i > 0 && i % 100 == 0) {
                System.out.printf("Schritt %d von %d abgeschlossen\n", i + 1, count);
            }
        }
        System.out.println("Alle Schritte abgeschlossen");
        return sum / count;
    }

    //@Override
    protected Double call() throws InterruptedException, ExecutionException {
        System.out.printf("CalcTask.call in Thread %s\n", Thread.currentThread().getName());

        double sum = 0;
        for (int i = 0; i < count; i++) {
            // TODO abbrechen

            double value = calcValue();
            sum += value;

            // TODO: UI updaten
        }
        // den Durchschnitt zurückliefern
        return sum / count;
    }


}
