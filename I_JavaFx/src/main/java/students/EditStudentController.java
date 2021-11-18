package students;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class EditStudentController {
    @FXML private ToggleGroup grpGeschlecht;
    @FXML private TextField txtId;
    @FXML private TextField txtName;
    @FXML private TextField txtPlz;
    @FXML private TextField txtStadt;
    @FXML private RadioButton rbMaennlic;
    @FXML private RadioButton rbWeiblich;
    @FXML private RadioButton rbDivers;
    @FXML private DatePicker dtpGeburtstag;
    @FXML private TextArea txtCommentar;
    @FXML private ComboBox<String> cmbSprache;
    @FXML private CheckBox cbXml;
    @FXML private CheckBox cbHtml;
    @FXML private CheckBox cbFXML;
    @FXML private Button btnCancel;
    @FXML private Button btnOK;

    @FXML
    private void initialize() {
        // ein paar Sprachen eintragen
        cmbSprache.getItems().add("Deutsch");
        cmbSprache.getItems().add("Englisch");
    }

    @FXML
    private void onOK(ActionEvent ae) {
        // Die Daten auslesen und in ein Student-Objekt übernehmen
        Student student = new Student();
        student.setId(Integer.parseInt(txtId.getText()));
        student.setName(txtName.getText().trim());
        student.setCity(txtStadt.getText().trim());
        student.setComment(txtCommentar.getText().trim());
        student.setAreaCode(Integer.parseInt(txtPlz.getText().trim()));

        // Radiobuttons: je nach Button das passende Geschlecht
//        Gender gender =

        Gender gender;
         if (grpGeschlecht.getSelectedToggle() == rbMaennlic) {
             gender = Gender.MALE;
         } else if (grpGeschlecht.getSelectedToggle() == rbWeiblich) {
             gender = Gender.FEMALE;
         } else {
             gender = Gender.OTHER;
         }
         student.setGender(gender);

        // Datepicker
        student.setBirthDate(dtpGeburtstag.getValue());

        // Checkboxen
        student.setHtml(cbHtml.isSelected());
        student.setXml(cbXml.isSelected());
        student.setFxml(cbFXML.isSelected());

        // Combobox
        String language = cmbSprache.getValue();
        student.setLanguage(language);

        System.out.println("Studentn erfasst " + student);
        // todo später das Fenster schließen
    }

    @FXML
    private void onCancel(ActionEvent ae) {
        System.out.println("Student unverändert");
        // todo später das Fenster schließen
    }

    // den Studenten übergeben (vorläufig nur string)

    public void setStudentName(String name) {
        txtName.setText(name);
    }

}
