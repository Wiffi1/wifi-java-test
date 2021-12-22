package tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CalcTaskExecutor extends CalcTaskBase {


    // Für Ausführung in mehreren parallelen Threads einen Executor verwenden
    private ExecutorService executor;

    public CalcTaskExecutor(int count) {
        super(count);
    }

    // synchrone Variante, ohne Fortschrittsanzeige
    public double runSync() throws ExecutionException, InterruptedException {
        System.out.printf("CalcTaskExecutor.runSync in Thread %s\n", Thread.currentThread().getName());

        // TODO: Executor erzeugen

        // TODO: zuerst alle Schritte beim Executor hinzufügen
        for (int i = 0; i < count; i++) {
        }

        double sum = 0;
        // TODO: jetzt auf die Ergebnisse warten

        return sum / count;
    }

    //@Override
    protected Double call() throws InterruptedException, ExecutionException {
        System.out.printf("CalcTask.call in Thread %s\n", Thread.currentThread().getName());
        double sum = 0;

//        System.out.printf("CalcTask.call in Thread %s\n", Thread.currentThread().getName());
//
//        // UI aktualisieren (wird am richtigen Thread ausgeführt)
//        updateMessage("Starte Task mit %d Schritten".formatted(count));
//        updateProgress(0, count);
//
//        // Executor erzeugen (das Objekt soll in einem Attribut abgelegt werden,
//        // damit nach dem Cancel noch Zeit bleibt, alle offenen Schritte zu canceln
//        this.executor = Executors.newFixedThreadPool(8);
//
//        // zuerst alle Schritte beim Executor hinzufügen
//        List<Future<Double>> steps = new ArrayList<>();
//        for (int i = 0; i < count; i++) {
//            steps.add(executor.submit(() -> {
//                return calcValue();
//            }));
//        }
//        // jetzt auf die Ergebnisse warten
//        try {
//            for (int i = 0; i < steps.size(); i++) {
//                // wenn die cancel-Methode für den Task ausgeführt wurde, die Berechnung abbrechen
//                if (isCancelled()) {
//                    System.out.println("Task wurde abgebrochen, breche Executor ab");
//                    updateMessage("Task wurde bei Schritt %d abgebrochen".formatted(i + 1));
//                    break;
//                }
//                double value = steps.get(i).get();
//                sum += value;
//                // das UI informieren (Controls werden garantiert am richtigen Thread geändert)
//                // ProgressBar: i von count Schritten sind schon erledigt
//                updateProgress(i + 1, count);
//                // Status-Message setzen
//                updateMessage("Schritt %d von %d ausgeführt".formatted(i + 1, count));
//            }
//        } catch (InterruptedException e) {
//            System.out.println("Task wurde mit InterruptedException abgebrochen");
//
//        }
//        if(isCancelled()){
//            System.out.println("Task gecancelt, breche alle Schritte ab");
//            updateMessage("Task wurde abgebrochen");
//            executor.shutdownNow();
//            return null;
//        }
//
//        // Executor normal beenden
//        executor.shutdown();

        // den Durchschnitt zurückliefern
        return sum / count;

    }

}
