package introFx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

// Klasse für die Anwendung, die das Hauptfenster startet
public class IntroMain extends Application {

    public static void main(String[] args) {
        // damit wird ein Objekt unserer Intro-Main-Klasse erzeugt und
        // unsere Start-Implementierung aufgerufen.
        launch(args);
//        IntroMain.class.getConstructor()
    }

    @Override
    public void start(Stage stage) throws Exception {
        // unser View-Objekt erzeugen
        IntroGridView root = new IntroGridView();
        // DenContainer für unser View-Objekt erzeugen (=Scene Graph)
        Scene scene = new Scene(root, 400, 300);
        // den Scene graph im Hauptfenster
        stage.setScene(scene);
        stage.setTitle("Erstes Java FX Programm");
        // Hauptfenster anzeigen
        stage.show();
    }
}
