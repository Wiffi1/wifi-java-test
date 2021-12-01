package editorFx.programm;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class EditorController {

    @FXML private TextArea txtEditor;

    private FileChooser fileDialog;

    // TODO: je nachdem ob es Änderungen im angezeigten Dokument gibt, korrekt setzen
    private boolean isChanged;

    public EditorController() {
        // Filedialog intitialisieren
        fileDialog = new FileChooser();
        fileDialog.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text-Dateien", "*.txt"));
        fileDialog.getExtensionFilters().add(new FileChooser.ExtensionFilter("Alle Dateien", "*.*"));
    }

    @FXML
    private void initialize() {
        // bei Änderungen in der Textarea für später merken, dass sich was geändert hat
        txtEditor.textProperty().addListener((o, oldval, newval) -> isChanged = true);
    }

    @FXML
    private void onOeffnen(ActionEvent actionEvent) {
        // File zum Öffnen auswählen (mit dem Hauptfenster als Parent)
        File file = fileDialog.showOpenDialog(txtEditor.getScene().getWindow());
        // wenn ein File ausgewählt wurde
        if (file != null) {
            String fileName = file.getAbsolutePath();
            System.out.println("Selektiert (öffnen): " + fileName);
            // TODO File öffnen, einlesen und in der TextArea anzeigen
        } else {
            // Dialog wurde abgebrochen
            System.out.println("Es wurde kein File selektiert");
        }


    }

    @FXML
    private void onBeenden(ActionEvent actionEvent) {
        schliessen();

    }

    @FXML
    private void onAbout(ActionEvent actionEvent) {
    }


    public void schliessen() {
        if (frageNachAenderungen()) {
            // das Hauptfenster schließen
            ((Stage) txtEditor.getScene().getWindow()).close();
        }
    }


    private boolean frageNachAenderungen() {
        if (isChanged) {
            Alert msgBox = new Alert(Alert.AlertType.CONFIRMATION, "Änderungen speichern?",
                    ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
            msgBox.setHeaderText("");
            msgBox.setTitle("Editor");

            ButtonType result = msgBox.showAndWait().get();
            if (result.equals(ButtonType.YES)) {
                // TODO Dokument speichern
                System.out.println("TODO: Dokument speichern!!!!");
                // wenn alles OK: fortsetzen
                return true;
            } else if (result.equals(ButtonType.NO)) {
                // nicht speichern, aber fortsetzen
                return true;
            } else {
                // nicht speichern, nicht fortsetzen
                return false;
            }

        }
        // keine Änderungen -> fortsetzen
        return true;
    }


}
