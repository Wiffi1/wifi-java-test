package common;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class FxmlHelper {
    // Hilfsmethode, die einen FXML-View in einer Stage anzeigt
    public static <TController> TController initStage(Stage stage, String viewFile, String title)
            throws IOException {
        // Loader-Objekt zum Laden des Views erzeugen
        FXMLLoader loader = new FXMLLoader(FxmlHelper.class.getResource(viewFile));
        // den View laden
        Parent root = loader.load();
        stage.setScene(new Scene(root));
        stage.setTitle(title);
        // Den Controller holen
        TController controller = loader.getController();
        // und zurückliefern
        return controller;
    }

    public static <TController> TController initAsDialog(Stage stage, String viewFile, String title)
            throws IOException {
        // als normales Fenster initialisieren
        TController controller = initStage(stage, viewFile, title);
        // modal anzeigen
        stage.initModality(Modality.APPLICATION_MODAL);
        // mit Rahmen
        stage.initStyle(StageStyle.DECORATED);
        return controller;
    }

}
