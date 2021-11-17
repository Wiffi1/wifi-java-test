package student;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class EditStudentController {
    @FXML private TextField txtId;
    @FXML private TextField txtName;
    @FXML private TextField txtPlz;
    @FXML private TextField txtStadt;
    @FXML private RadioButton rbMaennlic;
    @FXML private RadioButton rbWeiblich;
    @FXML private RadioButton rbDivers;
    @FXML private DatePicker dtpGeburtstag;
    @FXML private TextArea txtCommentar;
    @FXML private ComboBox cmbSprache;
    @FXML private CheckBox cbXtml;
    @FXML private CheckBox cbHtml;
    @FXML private CheckBox cbFXML;
    @FXML private Button btnCancel;
    @FXML private Button btnOK;

    @FXML
    private void initialize() {

    }

    @FXML
    private void onOK(ActionEvent ae) {

    }

    @FXML
    private void onCancel(ActionEvent ae) {

    }

}
