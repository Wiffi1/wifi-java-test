package introFxml;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class IntroGridController {
    @FXML
    private TextField txtName;
    @FXML
    private Button btnOk;
    @FXML
    private Button btnCancel;
    @FXML
    private ListView<String> lvMessages;

    @FXML
    private void initialize() {
        addEntry("App startup finished");
    }

    @FXML
    private void onClickButton(ActionEvent ae) {

        String userData = ((Node) ae.getSource()).getUserData().toString();
        System.out.println("Userdata: " + userData);
        switch (userData) {
            case "OK" -> addEntry("Hallo, " + txtName.getText() + "!");
            case "Cancel" -> addEntry("Abbrechen ...");
        }
    }

    private void addEntry(String msg) {
        lvMessages.getItems().add(
                DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM).format(LocalTime.now()) + ": " + msg);
    }
}
