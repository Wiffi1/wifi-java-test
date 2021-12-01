package students.program;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import students.model.Student;
import students.model.Gender;

public class EditStudentController {
    @FXML private ToggleGroup grpGeschlecht;
    @FXML private TextField txtId;
    @FXML private TextField txtName;
    @FXML private TextField txtPlz;
    @FXML private TextField txtStadt;
    @FXML private RadioButton rbMaennlich;
    @FXML private RadioButton rbWeiblich;
    @FXML private RadioButton rbDivers;
    @FXML private DatePicker dtpGeburtstag;
    @FXML private TextArea txtKommentar;
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

        // Den ok button enablen disablen je nach eingaben
        txtName.textProperty().addListener((o, oldval, newval) -> checkGueltig());
        txtStadt.textProperty().addListener((o, oldval, newval) -> checkGueltig());
        txtPlz.textProperty().addListener((o, oldval, newval) -> checkGueltig());
        // Bei Radiobuttons auf Änderungen der selectedToggle-Eigenschaft der ButtonGroup reagieren
        grpGeschlecht.selectedToggleProperty().addListener((o, oldval, newval) -> checkGueltig());
        // Bei Datepicker und Combobox die value-Eigenschaft
        dtpGeburtstag.valueProperty().addListener((o, oldval, newval) -> checkGueltig());
        cmbSprache.valueProperty().addListener((o, oldval, newval) -> checkGueltig());
    }

    private void checkGueltig() {
        boolean gueltig = txtName.getText() != null && !txtName.getText().isBlank()
                && txtStadt.getText() != null && !txtStadt.getText() .isBlank()
                && txtPlz.getText() != null && !txtPlz.getText() .isBlank()
                && grpGeschlecht.getSelectedToggle() != null
                && dtpGeburtstag.getValue() != null
                && cmbSprache.getValue() != null;
        System.out.println("Gültig; " + gueltig);
        // Den Button je nach Gültigkeit enablen/disablen
        btnOK.setDisable(!gueltig);
    }


    @FXML
    private void onOK(ActionEvent ae) {
        // Die Daten auslesen und in ein Student-Objekt übernehmen
        Student student = new Student();
        student.setId(Integer.parseInt(txtId.getText()));
        student.setName(txtName.getText().trim());
        student.setCity(txtStadt.getText().trim());
        student.setComment(txtKommentar.getText().trim());
        student.setAreaCode(Integer.parseInt(txtPlz.getText().trim()));

        // Radiobuttons: je nach Button das passende Geschlecht
//        Gender gender =

        Gender gender;
         if (grpGeschlecht.getSelectedToggle() == rbMaennlich) {
             gender = Gender.MALE;
         } else if (grpGeschlecht.getSelectedToggle() == rbWeiblich) {
             gender = Gender.FEMALE;
         } else {
             gender = Gender.OTHER;
         }
         student.setGender(gender);

        // Datepicker
        student.setBirthDate(dtpGeburtstag.getValue());

        if (txtKommentar.getText() != null) {
            student.setComment(txtKommentar.getText().trim());
        }

        // Checkboxen
        student.setHtml(cbHtml.isSelected());
        student.setXml(cbXml.isSelected());
        student.setFxml(cbFXML.isSelected());

        // Combobox
        String language = cmbSprache.getValue();

        // todo Sprache
//        student.setLanguage(language);

        System.out.println("Studentn erfasst " + student);
        // todo später das Fenster schließen
    }

    @FXML
    private void onCancel(ActionEvent ae) {
        System.out.println("Student unverändert");
        // todo später das Fenster schließen
    }

    // den Studenten übergeben (vorläufig nur string)

    public void setStudent(Student student) {
        // Wenn es ein neuer Student ist ein neues Objekt erzeugen
        if (student == null) {
          student = new Student();
//          student.setId(0);
        }

        txtId.setText(Integer.toString(student.getId()));
        txtName.setText(student.getName());
        txtStadt.setText(student.getCity());
        txtKommentar.setText(student.getComment());
        txtPlz.setText(Integer.toString(student.getAreaCode()));

        if (student.getGender() != null) {
            // Radiobutton
            switch (student.getGender()) {
                case MALE -> rbMaennlich.setSelected(true);
                case FEMALE -> rbWeiblich.setSelected(true);
                case OTHER -> rbDivers.setSelected(true);
            }
        }

        //Datepicker
        dtpGeburtstag.setValue(student.getBirthDate());

        // Checkboxen
        cbHtml.setSelected(student.isHtml());
        cbXml.setSelected(student.isXml());
        cbFXML.setSelected(student.isFxml());

        //Combobox
        // todo Sprache
//        cmbSprache.setValue(student.getLanguage());
    }

}
