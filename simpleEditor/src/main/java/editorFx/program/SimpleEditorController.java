package editorFx.program;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class SimpleEditorController {

    private EditorDocument document;
    @FXML
    private TextArea txtEditor;

    private FileChooser fileDialog;
    private File initialDir;

    public SimpleEditorController() {

        document = new EditorDocument();
        initialDir = new File(".");
        fileDialog = new FileChooser();
        fileDialog.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text files", "*.txt"));
        fileDialog.getExtensionFilters().add(new FileChooser.ExtensionFilter("HTML files", "*.html", "*.htm"));
        fileDialog.getExtensionFilters().add(new FileChooser.ExtensionFilter("All files", "*.*"));
    }

    @FXML
    private void initialize() {
        txtEditor.textProperty().addListener((o, oldval, newval) -> document.setChanged(true));
    }

    @FXML
    private void onNew(ActionEvent actionEvent) {
        System.out.println("onNew");
        // ggf Speichern
        if (askForSave()) {
            // neues Dokument anzeigen
            txtEditor.setText("");
            document.reset();
            // titel aktualisieren
            ((Stage) txtEditor.getScene().getWindow()).setTitle("Unbenannt");
        }
    }

    @FXML
    private void onOpen(ActionEvent actionEvent) {
        System.out.println("onOpen");
        try {
            // ggf speichern
            if (askForSave()) {
                fileDialog.setInitialDirectory(initialDir);
                File file = fileDialog.showOpenDialog(txtEditor.getScene().getWindow());

                //fileDialog.get
                // wenn vorhanden
                if (file != null) {
                    System.out.println("File selektiert (open): " + file);
                    initialDir = file.getParentFile();
                    // das File anzeigen
                    String text = document.open(file.getAbsolutePath());
                    // den Text in der Textarea setzen
                    txtEditor.setText(text);
                    // changed-Flag zurücksetzen
                    document.setChanged(false);
                    // titel aktualisieren
                    ((Stage) txtEditor.getScene().getWindow()).setTitle(file.getAbsolutePath());


                } else {
                    System.out.println("Kein File selektiert (open)");
                }

            } else {
                System.out.println("Aktion abgebrochen");
            }
        } catch (IOException e) {
            // Fehler ausgeben
            showMessage("Fehler beim Öffnen", e.getMessage(), Alert.AlertType.ERROR, ButtonType.OK);
        }

    }

    @FXML
    private void onSave(ActionEvent actionEvent) {
        System.out.println("onSave");
        doSave();

    }

    @FXML
    private void onSaveAs(ActionEvent actionEvent) {
        System.out.println("onSaveAs");
        doSaveAs();
    }

    @FXML
    private void onQuit(ActionEvent actionEvent) {
        System.out.println("onQuit");
        doQuit();
    }

    @FXML
    private void onAbout(ActionEvent actionEvent) {
        System.out.println("onAbout");
        showMessage("Editor", "Simple Editor V1.0\n©Michaela 2021");

    }
    /**
     * Handler für Befehl SAVE
     *
     * @return true, wenn alles ok
     */
    private boolean doSave() {
        // kein Filename vorhanden
        if (document.getFileName() == null) {
            return doSaveAs(); // Speichern unter aufrufen
        } else if (document.isChanged()) {
            try {
                document.save(txtEditor.getText());
                return true;
            } catch (IOException e) {
                // Fehler ausgeben
                showMessage("Fehler beim Speichern", e.getMessage(), Alert.AlertType.ERROR, ButtonType.OK);

            }
        }
        return false;
    }

    /**
     * Handler für Befehl SAVEAS
     *
     * @return true, wenn alles ok
     */
    private boolean doSaveAs() {
        // den Dialog mit dem Hauptfenster als Parent anzeigen
        fileDialog.setInitialDirectory(initialDir);
        File file = fileDialog.showSaveDialog(txtEditor.getScene().getWindow());
        if (file != null) {
            System.out.println("Selected file (save): " + file);
            initialDir = file.getParentFile();
            try {
                document.setChanged(true);
                document.save(txtEditor.getText(), file.getAbsolutePath());
                ((Stage) txtEditor.getScene().getWindow()).setTitle(file.getAbsolutePath());
                // alles gutgegangen
                return true;
            } catch (IOException e) {
                // Fehler anzeigen
                showMessage("Fehler beim Speichern unter...", e.getMessage(), Alert.AlertType.ERROR, ButtonType.OK);
            }
        } else {
            System.out.println("No file selected (save)");
        }

        return false;
    }

    /**
     * Das Programm beenden. Kann auch vom Programm aus aufgerufen werden
     *
     * @return true, wenn alles ok
     */
    public boolean doQuit() {
        System.out.println("doExit");
        if (askForSave()) {
            // Hauptfenster schließen
            ((Stage) txtEditor.getScene().getWindow()).close();
            return true;
        } else
            return false;
    }
    /**
     * Fragt ggf ob gespeichert werden soll
     *
     * @return true, wenn fortgesetzt werden soll; false, wenn abgebrochen wurde oder
     * ein Fehler aufgetreten ist
     */
    private boolean askForSave() {

        if (document.isChanged()) {
            ButtonType result = showMessage("Beenden", "Änderungen speichern?", Alert.AlertType.CONFIRMATION,
                    ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);

            if (result.equals(ButtonType.YES)) {

                // YES-Button ausgewählt
                System.out.println("Speichern und fortsetzen");
                return doSave();
            } else if (result.equals(ButtonType.NO)) {
                // NO-Button ausgewählt
                System.out.println("Ohne Speichern fortsetzen");
                return true;
            } else {
                // Cancel-Button ausgewählt
                System.out.println("Aktion nicht fortsetzen");
                return false;
            }

        } else { // unchanged
            System.out.println("Keine Änderungen, fortsetzen");
            return true;
        }
    }


    private static ButtonType showMessage(String title, String content) {
        return showMessage(title, content, Alert.AlertType.INFORMATION, ButtonType.OK);
    }

    private static ButtonType showMessage(String title, String content, Alert.AlertType type, ButtonType... buttons) {

        Alert msg = new Alert(type, content, buttons);
        msg.setAlertType(type);
        msg.setHeaderText("");
        msg.setTitle(title);
        msg.setContentText(content);
        return msg.showAndWait().orElse(ButtonType.CANCEL);

    }

}
