package editorFx.programm;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;

public class EditorController {

    @FXML public TextArea txtEditor;

    private FileChooser fileDialog;

    public EditorController() {
        // Filedialog initialisieren
        fileDialog = new FileChooser();
        fileDialog.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text-Dateien", "*.txt"));
        fileDialog.getExtensionFilters().add(new FileChooser.ExtensionFilter("Alle Dateien", "*.*"));
    }

    @FXML
    private void onOeffnen(ActionEvent actionEvent) {
        // Ein File zum Öffnen auswählen (mit einem Hauptfenster als Paren)
        File file = fileDialog.showOpenDialog(txtEditor.getScene().getWindow());
        if (file != null) {
            // wenn ein File ausgewählt wurde
            String filename = file.getAbsolutePath();
            System.out.println("Selektiert (öffnen" + filename);
            // todo File öffnen, einlesen und in der Textarea anzeigen

//            readLines("Textfile1.txt", "UTF-8");
            System.out.println("Filename: " + filename);
            String text = readChunks(filename, "UTF-8");

            txtEditor.setText(text);
        } else {
            // Dialsog wurde abgerochen
            System.out.println("Es wurde kein File selektiert");
        }
    }

    @FXML
    private void onBeenden(ActionEvent actionEvent) {
    }

    @FXML
    private void onAbout(ActionEvent actionEvent) {
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


    private void saveFile() {

    }
}
