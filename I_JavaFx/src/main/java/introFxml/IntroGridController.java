package introFxml;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class IntroGridController {
    public TextField txtName;
    public Button btnOk, btnCancel;
    public ListView<String> lvMessages;

    public void onClickButton(ActionEvent ae) {

        String userData = ((Node) ae.getSource()).getUserData().toString();
        System.out.println("Userdata: " + userData);
        switch (userData) {
            case "OK" -> addEntry("Hallo, " + txtName.getText() + "!");
            case "Cancel" -> addEntry("Abbrechen ...");
        }
    }

    public void addEntry(String msg) {
        lvMessages.getItems().add(
                DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM).format(LocalTime.now()) + ": " + msg);
    }
}
