package mitarbeiterVerwaltungFxml;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import students.EditStudentController;

import static javafx.application.Application.launch;

public class TestMitarbeiterVerwaltung extends Application  {
    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage stage) throws Exception {
        // FXML loader, damit wir Zugriff auf das Controllerobjekt bekommen
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/mitarbeiter/MitarbeiterGridView.fxml"));

        // den Scene Graph laden
        Parent root = loader.load();
        // den Controller holen
        MitArbeiterController controller = loader.getController();
        // und konfigurieren
        // ein neues Objekt erfassen
//        controller.setMitarbeiter(null);

        // DenContainer für unser View-Objekt erzeugen (=Scene Graph)
        Scene scene = new Scene(root, 400, 400);
        // den Scene graph im Hauptfenster
        stage.setScene(scene);
        stage.setTitle("Mitarbeiter*in bearbeiten");
        // Hauptfenster anzeigen
        stage.show();
    }
}
