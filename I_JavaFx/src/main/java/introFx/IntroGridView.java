package introFx;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

// entspricht in etwa dem Inhalt für den ContentPane in Swing
public class IntroGridView extends GridPane {

    // Attribute für die Controls
    private TextField txtName;
    private Button btnOk, btnCancel;
    private ListView<String> lvMessages;

    public IntroGridView() {

        // Titel
        Label lblTitle = new Label("Willkommen bei Java FY!");
        // im Container hinzufügen, in Spalte 0, Zeile 0, 2 Spalten breit, 1 Zeile hoch
        add(lblTitle, 0, 0, 2, 1);

        // Name; Beschriftung und Text
        Label lblName = new Label("Dein Name:");
        // im Container hinzufügen, in Spalte 0, Zeile 1, 1 Spalten breit, 1 Zeile hoch
        add(lblName, 0, 1);

        txtName = new TextField();
        // in Spalte 1/Zeile 1
        add(txtName, 1, 1);

    }

}
