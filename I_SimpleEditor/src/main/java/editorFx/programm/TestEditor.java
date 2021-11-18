package editorFx.programm;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TestEditor extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        // FXML loader, damit wir Zugriff auf das Controllerobjekt bekommen
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/editorFx/views/EditorView.fxml"));

        // den Scene Graph laden
        Parent root = loader.load();
        // den Controller holen
        EditorController controller = loader.getController();
        // und konfigurieren
        // ein neues Objekt erfassen
//        controller.setStudent(null);

        // DenContainer für unser View-Objekt erzeugen (=Scene Graph)
        Scene scene = new Scene(root, 400, 400);
        // den Scene graph im Hauptfenster
        stage.setScene(scene);
        stage.setTitle("Editor");

        // den close-request abfangen und die schliesen-Methode ads cController aufrufen
        stage.setOnCloseRequest(we -> {
            controller.schliessen();
            // verhindern, dass das Event weier verarbeitet wird.
            we.consume();
        });

        // Hauptfenster anzeigen
        stage.show();
    }
}
