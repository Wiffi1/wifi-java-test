package tasks;

import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.WindowEvent;
import javafx.util.converter.NumberStringConverter;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class TasksController {

    @FXML
    private TextField txtCount;

    @FXML
    private CheckBox chkUseExecutor;

    @FXML
    private Label lblMessage;

    @FXML
    private ProgressBar barProgress;

    @FXML
    private Button btnRunSync;

    @FXML
    private Button btnStartAsync;

    @FXML
    private Button btnCancelAsync;

    @FXML
    private Label lblStatus;

    // Property für die das Textfeld mit der Anzahl
    private IntegerProperty count = new SimpleIntegerProperty(500);

    // Property für den Running-Status
    private SimpleBooleanProperty isRunning = new SimpleBooleanProperty(false);

    // Task-Objekt
    private CalcTaskBase myTask;

    private Timer myTimer;


    private Instant startTime;

    @FXML
    private void initialize() {
        txtCount.textProperty().bindBidirectional(count, new NumberStringConverter());
        barProgress.setProgress(0);

        // enable, disable je nach Status
        // start buttons disablen, wenn der Task läuft
        btnRunSync.disableProperty().bind(isRunning);
        btnStartAsync.disableProperty().bind(isRunning);
        // cancel button disablen, wenn der Task nicht läuft
        btnCancelAsync.disableProperty().bind(isRunning.not());


        // Timer erzeugen und starten
        myTimer = new Timer("My Timer");
        myTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                onTimerBreak();
            }
        }, 2000, 1000); // Timer 1x pro Sekubnde start nach 2

        // Timer beenden, wenn das Fenster geschlossen wird
        Platform.runLater(() -> {
            lblStatus.getScene().getWindow()
                    .addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, we -> {
                        System.out.println("Fenster wird geschlossen -> Timer wird beendet");
                        myTimer.cancel();
                    });
        });

    }

    private void onTimerBreak() {
//        System.out.printf("TimerBreak in Thread %s, Uhrzeit: %s\n",
//                Thread.currentThread().getName(), LocalDateTime.now().toString());

        DateTimeFormatter fmt = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);

        // das Zugreifen auf die Controls ist nur im JavaFX Application-Thread erlaubt
        Platform.runLater(() -> {
            System.out.println("Platform.runLater in Thread " + Thread.currentThread().getName());
            lblStatus.setText(fmt.format(LocalDateTime.now()));
        });

    }

    @FXML
    private void runSync(ActionEvent event) {
        System.out.println("runSync in Thread " + Thread.currentThread().getName());

        // Task erzeugen
        myTask = chkUseExecutor.isSelected() ? new CalcTaskExecutor(count.get()) : new CalcTask(count.get());


        // Anzeige anpassen (Achtung: Das ist nicht zu sehen, weil die Nachrichten erst
        // verarbeitet werden, wenn die Berechnung abgeschlossen ist)
        isRunning.set(true);
        lblMessage.setText(String.format("Führe Task mit %d Teilschritten aus", count.get()));
        startTime = Instant.now();
        try {
            // Berechnung ausführen
            Double result = myTask.runSync();
            Duration elapsed = Duration.between(startTime, Instant.now());
            // Ergebnis anzeigen
            lblMessage.setText("Task in %d ms abgeschlossen, result=%.2f".formatted(
                    elapsed.toMillis(), result));
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        // Property für Bindings anpassen
        isRunning.set(false);
    }

    @FXML
    private void startAsync(ActionEvent event) {
        System.out.println("startAsync in Thread " + Thread.currentThread().getName());

        // Task erzeugen
        myTask = chkUseExecutor.isSelected() ? new CalcTaskExecutor(count.get()) : new CalcTask(count.get());

        // TODO Task konfigurieren und starten
        // TODO: Handler für Success registrieren (wird am richtigen Thread ausgelöst)

        // TODO: Handler für Cancel registrieren
        myTask.setOnSucceeded(e -> {
            System.out.println("onSucceeded in Thread " + Thread.currentThread().getName());
            try {
                // Das Ergebnis der Call-Methode des Task-Objekts abholen
                Double result = myTask.get();
                Duration elapsed = Duration.between(startTime, Instant.now());
                lblMessage.setText("Async. Task in %d ms abgeschlossen, result=%.2f".formatted(
                    elapsed.toMillis(), result));
            } catch (InterruptedException | ExecutionException ex) {
                ex.printStackTrace();
            }
            isRunning.set(false);
        });

        // Listener für die Message-Property (Message im Label anzeigen)
        // nicht mit Binding, weil wir den Text im Label auch von anderen Stellen setzen
        // der Handler wird am Application Main Thread ausgelöst
        myTask.messageProperty().addListener((o, oldval, newval) -> lblMessage.setText(newval));

        // den ProgressBar an den Fortschritt des Tasks binden
        barProgress.progressProperty().bind(myTask.progressProperty());

        // Zeitmessung starten und asynchron ausführen
        startTime = Instant.now();
        // startet den Task in einem eigenen Thread -> damit wird die call-Methode ausgeführt
//        new Thread(myTask).start();
        CompletableFuture.runAsync(myTask);

        // Buttons enablen/disablen
        isRunning.set(true);
    }

    @FXML
    private void cancelAsync(ActionEvent event) {
        // den Task canceln und Buttons enablen/disablen
        if (myTask != null && myTask.isRunning()) {
            // im Task das isCancelled-Flag auf true setzen
            myTask.cancel();
            // Button enablen/disablen
            isRunning.set(false);
        }
    }

}
