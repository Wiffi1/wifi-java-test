package editorFx.program;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SimpleEditorMain extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            // den Root des Scene Graph aus dem FXML-File laden
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/editorFx/views/SimpleEditor.fxml"));
            Parent root = loader.load();
            SimpleEditorController controller = loader.getController();

            Scene scene = new Scene(root, 400, 300);
            // das ganze anzeigen
            primaryStage.setScene(scene);
            primaryStage.setTitle("Neues Dokument");

            // den Close-Request abfangen und close-Methode des Controllers
            // aufrufen
            primaryStage.setOnCloseRequest(event -> {
                // unsere eigenen close-Routine ausführen
                controller.doQuit();
                // verhindern dass das Event weiter verarbeitet wird
                event.consume();

            });
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
