package editorFx.programm;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.nio.charset.Charset;

public class EditorController {

    @FXML public TextArea txtEditor;
    @FXML public MenuItem menuItem_onNeu;
    @FXML public MenuItem menuItem_onSave;

    private FileChooser fileDialog;
    private String filename;

    // todo je nachdem, ob es Änderungen im angezeigten Dokument gibt oder nicht.
    // Defaultwert eines boolean ist false!!
    private boolean isChanged;

    public EditorController() {
        // Filedialog initialisieren
        fileDialog = new FileChooser();
        fileDialog.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text-Dateien", "*.txt"));
        fileDialog.getExtensionFilters().add(new FileChooser.ExtensionFilter("Alle Dateien", "*.*"));
    }

    @FXML
    private void initialize() {
        // Änderungen im Textfeld reagieren
        txtEditor.textProperty().addListener((o, oldVal, newVal) -> {
            // bei Änderung in der Textarea für später merken, dass sich was geändert hat

//            menuItem_onNeu.setDisable(false);
//            menuItem_onSave.setDisable(false);
            isChanged = true;
        });
    }

    @FXML
    private void onOeffnen(ActionEvent actionEvent) {
        System.out.println("onNeu - neues File");
        if (textSpeichernKeinAbbruch()) {
            // Ein File zum Öffnen auswählen (mit einem Hauptfenster als Parent)
            File file = fileDialog.showOpenDialog(txtEditor.getScene().getWindow());
            if (file != null) {
                // wenn ein File ausgewählt wurde
                filename = file.getAbsolutePath();
                System.out.println("Selektiert (öffnen" + filename);
                // todo File öffnen, einlesen und in der Textarea anzeigen

//            readLines("Textfile1.txt", "UTF-8");
                System.out.println("Filename: " + filename);
                String text = readChunks(filename, "UTF-8");

                txtEditor.setText(text);
            } else {
                // Dialog wurde abgebrochen
                System.out.println("Es wurde kein File selektiert");
            }
        }
    }


    @FXML
    private void onNeu(ActionEvent actionEvent) {
        System.out.println("onNeu - neuer Text");
        if (!isChanged) {
            txtEditor.setText("");
        } else {
            if (textSpeichernKeinAbbruch()) {
                txtEditor.setText("");
            }
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
        if (textSpeichernKeinAbbruch()) {
            ((Stage) txtEditor.getScene().getWindow()).close();
        }
    }

    private boolean textSpeichernKeinAbbruch() {
        Alert msgBox = new Alert(Alert.AlertType.CONFIRMATION, "Änderungen speichern?",
                ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        msgBox.setHeaderText("");
        msgBox.setTitle("Editor");
        ButtonType result = msgBox.showAndWait().get();
        if (result.equals(ButtonType.YES)) {
            if (!(filename == null)) {
                save();
            } else {
                saveAt();
            }
            // Wenn alles ok fortsetzen
            return true;
        } else if (result.equals(ButtonType.NO)) {
            // nicht speichern aber beenden
            return true;
        } else {
            // nicht speichern, nicht beenden
            return false;
        }
    }

    static String readChunks(String filename, String encoding) {
        String text = "";
        // das File zum Lesen öffnen, mit der angegebenen Kodierung
        try (FileReader reader = new FileReader(filename, Charset.forName(encoding))){
            // Puffer für die gelesenen Zeichen
            char[] buffer = new char[16]; // Sehr klein, damit die Schleife mehrmals ausgeführt wird
            // für Anzahl der gelesenen Zeichen
            int count;
            // Speicherplatz für das Gelesene
            StringBuilder content = new StringBuilder();
            //  Lesen
            // solange es mindestens 1 Zeichen zu lesen gab
            while ((count = reader.read(buffer)) > 0){
                System.out.println(count + " Zeichen gelesen");
                // im StringBuilder soviele Zeichen aus dem Puffer hinzufügen,
                // wie wir gerade gelesen haben
                content.append(buffer, 0, count);
            }

            // alles gelesen -> Ausgeben
            text = content.toString();
            System.out.println("Vom File gelesen: ");
            System.out.println(text);
            System.out.printf("Der Text ist %d Zeichen lang\n\n\n", text.length());


        } catch (IOException e) {
            System.out.println("Fehler beim Einlesen: " + e);
        }

        return text;
    }

    @FXML
    private void onSave(ActionEvent actionEvent) {
        if (!(filename == null)) {
            save();
        } else {
            saveAt();
        }
    }

    @FXML
    private void onSaveAt(ActionEvent actionEvent) {
        saveAt();
    }

    void saveAt() {
        File file = fileDialog.showSaveDialog(txtEditor.getScene().getWindow());
        if (file != null) {
            // wenn ein File ausgewählt wurde
            filename = file.getAbsolutePath();
            System.out.println("Selektiert (öffnen" + filename);
            // todo File speichern, einlesen und in der Textarea anzeigen
            System.out.println("Filename: " + filename);
            String textToSave = txtEditor.getText();
            writeFile(filename, textToSave, "UTF-8");
        } else {
            // Dialsog wurde abgebrochen
            System.out.println("Es wurde kein File selektiert");
        }
    }

    void save() {
        String textToSave = txtEditor.getText();
        writeFile(filename, textToSave, "UTF-8");
    }

//    https://docs.oracle.com/javase/tutorial/essential/exceptions/tryResourceClose.html

    void writeFile(String fileName, String textToSave, String encoding) {
        System.out.printf("\nText wird gespeichert in file %s", fileName);
        // todo Fehlerbehandlung
        // schreiben
        // FileWriter verwendet standardmäßig das Encoding der VM,
        // unter Windows ist das ANSI (CP 1252)
        // mit dem Charset legen wir das Encoding selber fest
        // mit try-with-resources wird der Stream automatisch geschlossen
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(fileName, Charset.forName(encoding)))) {

            // das Schließen wird jetzt automatisch im finally-Block gemacht ?????
            // writer.close();*/
            writer.write(textToSave);
//            menuItem_onNeu.setDisable(true);
//            menuItem_onSave.setDisable(true);
        } catch (IOException e) {
            System.out.println("Fehler beim Speichern: " + e);
        }
    }

}
