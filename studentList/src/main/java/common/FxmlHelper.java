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
        // Loader-Objekt für das Laden von View erzeugen
        FXMLLoader loader = new FXMLLoader(FxmlHelper.class.getResource(viewFile));
        // den view laden
        Parent root = loader.load();
        stage.setScene(new Scene(root));
        stage.setTitle(title);
        TController controller = loader.getController();
        // und zurückliefern
        return controller;
    }

    public static <TController> TController initAsDialog(Stage stage, String viewFile, String title)
                throws IOException {
        TController controller = initStage(stage, viewFile, title);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.DECORATED);
        return controller;
    }
}
