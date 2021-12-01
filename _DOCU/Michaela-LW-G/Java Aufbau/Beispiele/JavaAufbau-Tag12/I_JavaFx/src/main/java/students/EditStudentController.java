package students;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class EditStudentController {

    @FXML private TextField txtId;
    @FXML private TextField txtName;
    @FXML private TextField txtPlz;
    @FXML private TextField txtStadt;
    @FXML private RadioButton rbMaennlich;
    @FXML private ToggleGroup grpGeschlecht;
    @FXML private RadioButton rbWeiblich;
    @FXML private RadioButton rbDivers;
    @FXML private DatePicker dtpGeburtsdatum;
    @FXML private CheckBox cbHtml;
    @FXML private CheckBox cbXml;
    @FXML private CheckBox cbFxml;
    @FXML private ComboBox cmbSprache;
    @FXML private TextArea txtKommentar;
    @FXML private Button btnAbbrechen;
    @FXML private Button btnOK;

    @FXML
    private void initialize(){

    }

    @FXML
    private void onOK(ActionEvent ae){

    }

    @FXML
    private void onCancel(ActionEvent ae){

    }

    // den Studenten übergeben (vorläufig nur den Namen)
    public void setStudentName(String name){

        txtName.setText(name);
    }
}
