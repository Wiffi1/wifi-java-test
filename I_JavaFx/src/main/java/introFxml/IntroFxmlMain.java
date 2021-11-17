package introFxml;

import introFx.IntroGridView;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

// Klasse für die Anwendung, die das Hauptfenster startet
public class IntroFxmlMain extends Application {

    public static void main(String[] args) {
        // damit wird ein Objekt unserer Intro-Main-Klasse erzeugt und
        // unsere Start-Implementierung aufgerufen.
        launch(args);
//        IntroMain.class.getConstructor()
    }

    @Override
    public void start(Stage stage) throws Exception {
        // unser View-Objekt erzeugen
        Parent root = (Parent) FXMLLoader.load(
                // das FXML-File liegt relativ zum Classpath des Programms
                // -> Pfad für die Resource vom classpath ausgehend.
                getClass().getResource("/views/introFxml/IntroGridView.fxml"));
        // DenContainer für unser View-Objekt erzeugen (=Scene Graph)
        Scene scene = new Scene(root, 400, 300);
        // den Scene graph im Hauptfenster
        stage.setScene(scene);
        stage.setTitle("Erstes Java FX Programm");
        // Hauptfenster anzeigen
        stage.show();
    }
}
