package students.program;

import common.MessageBox;
import common.ValidationBindings;
import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import students.model.Gender;
import students.model.Language;
import students.model.Student;

import java.util.List;

public class EditStudentController_Validation {

    private Student result;

    @FXML
    private TextField txtId;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtPlz;
    @FXML
    private TextField txtStadt;
    @FXML
    private RadioButton rbMaennlich;
    @FXML
    private ToggleGroup grpGeschlecht;
    @FXML
    private RadioButton rbWeiblich;
    @FXML
    private RadioButton rbDivers;
    @FXML
    private DatePicker dtpGeburtsdatum;
    @FXML
    private CheckBox cbHtml;
    @FXML
    private CheckBox cbXml;
    @FXML
    private CheckBox cbFxml;

    // Combobox soll Language-Objekte anzeigen
    @FXML
    private ComboBox<Language> cmbSprache;
    @FXML
    private TextArea txtKommentar;
    @FXML
    private Button btnAbbrechen;
    @FXML
    private Button btnOK;

    @FXML
    private void initialize() {
        // ein paar Sprachen eintragen
        // nicht mehr nötig, kommt aus der "DB"
//        cmbSprache.getItems().add("Deutsch");
//        cmbSprache.getItems().add("Englisch");

        // Cell-Factory liefert eine LanguageListCell bei jedem Aufruf
        cmbSprache.setCellFactory(cmb -> new LanguageListCell());
        // die LanguageListCell auch für das ausgewählte Item verwenden
        cmbSprache.setButtonCell(new LanguageListCell());

        // den OK button enablen disablen je nach Eingaben
//        txtName.textProperty().addListener((o, oldval, newval) -> checkGueltig());
//        txtStadt.textProperty().addListener((o, oldval, newval) -> checkGueltig());
//        txtPlz.textProperty().addListener((o, oldval, newval) -> checkGueltig());
//        // bei Radiobuttons auf Änderungen der selectedToggle-Eigenschaft der ButtonGroup reagieren
//        grpGeschlecht.selectedToggleProperty().addListener((o, oldval, newval) -> checkGueltig());
//        // Bei DatePicker und ComboBox-> die value-Eigenschaft
//        dtpGeburtsdatum.valueProperty().addListener((o, oldval, newval) -> checkGueltig());
//        cmbSprache.valueProperty().addListener((o, oldval, newval) -> checkGueltig());

        BooleanBinding isValid = ValidationBindings.requiredBinding(txtName, "Name")
                .and(ValidationBindings.integerInRange(txtPlz,"Postleitzahl", 1000, 99999))
                .and(ValidationBindings.requiredBinding(txtStadt, "Ort"))
                .and(ValidationBindings.requiredBinding(dtpGeburtsdatum, "Geburtstag"))
                .and(ValidationBindings.requiredBinding(cmbSprache, "Sprache"))
                .and(ValidationBindings.requiredBinding(grpGeschlecht, rbMaennlich.getParent() ));
        btnOK.disableProperty().bind(isValid.not());
    }

    private void checkGueltig() {
        boolean gueltig = txtName.getText() != null && !txtName.getText().isBlank()
                && txtStadt.getText() != null && !txtStadt.getText().isBlank()
                && txtPlz.getText() != null && !txtPlz.getText().isBlank()
                && grpGeschlecht.getSelectedToggle() != null
                && dtpGeburtsdatum.getValue() != null
                && cmbSprache.getValue() != null;
        System.out.println("Gültig=" + gueltig);
        // den Button je nach Gültigkeit enablen/disablen
        btnOK.setDisable(!gueltig);
    }

    @FXML
    private void onOK(ActionEvent ae) {

        try {
            // die Daten auslesen und in Student-Objekt übernehmen
            Student student = new Student();
            // Textfelder auslesen
            student.setId(Integer.parseInt(txtId.getText()));
            student.setName(txtName.getText().trim());
            student.setCity(txtStadt.getText().trim());
            // Kommentar ist optional
            if (txtKommentar.getText() != null) {
                student.setComment(txtKommentar.getText().trim());
            }
            student.setAreaCode(Integer.parseInt(txtPlz.getText().trim()));

            // Radiobuttons: je nach Button das passende Gender ermitteln
            Gender gender;
            if (grpGeschlecht.getSelectedToggle() == rbMaennlich) {
                gender = Gender.MALE;
            } else if (grpGeschlecht.getSelectedToggle() == rbWeiblich) {
                gender = Gender.FEMALE;
            } else {
                gender = Gender.OTHER;
            }
            student.setGender(gender);

            // DatePicker: den Value verwenden
            student.setBirthDate(dtpGeburtsdatum.getValue());

            // Checkboxen: selected-Property
            student.setHtml(cbHtml.isSelected());
            student.setXml(cbXml.isSelected());
            student.setFxml(cbFxml.isSelected());

            // ComboBox: den Value verwenden
            Language language = cmbSprache.getValue();
            // Sprache
            student.setLanguageId(language.getId());

            System.out.println("Student*in erfasst: " + student);

            // hier würden wir am liebsten  dem Aufrufer des Dialogs das StudentObjekt zurückliefern
            // ist hier nicht möglich, daher: das Ergebnis für später merken und das Fenster schließen
            result = student;
            ((Stage) txtId.getScene().getWindow()).close();
        }catch(Exception e){
            e.printStackTrace();
            MessageBox.show("Erfassen", "Fehler beim Erfassen: " + e.getMessage(), Alert.AlertType.ERROR, ButtonType.OK);
        }
    }

    @FXML
    private void onCancel(ActionEvent ae) {
        System.out.println("Erfassen abgebrochen");


        // das Ergebnis zurücksetzen und das Fenster schließen
        result = null;
        ((Stage)txtId.getScene().getWindow()).close();
    }

    // den Studenten übergeben
    public void setStudent(Student student, List<Language> languages) {

        // wenn es ein neuer Student ist: ein "leeres" Objekt erzeugen
        if (student == null) {
            student = new Student();
        }

        // die languages in der Combobox anzeigen
        cmbSprache.getItems().addAll(languages);

        txtId.setText(Integer.toString(student.getId()));
        txtName.setText(student.getName());
        txtStadt.setText(student.getCity());
        txtKommentar.setText(student.getComment());
        txtPlz.setText(Integer.toString(student.getAreaCode()));

        // Radiobuttons je nach Gender
        if (student.getGender() != null) {
            switch (student.getGender()) {
                case MALE -> rbMaennlich.setSelected(true);
                case FEMALE -> rbWeiblich.setSelected(true);
                case OTHER -> rbDivers.setSelected(true);
            }
        }

        // DatePicker: den Value setzen
        dtpGeburtsdatum.setValue(student.getBirthDate());

        // Checkboxen
        cbHtml.setSelected(student.isHtml());
        cbXml.setSelected(student.isXml());
        cbFxml.setSelected(student.isFxml());

        // ComboBox
        // Sprache
        // die Sprache in der Liste suchen
        Language lang = null;
        for (Language l : languages) {
            if (l.getId() == student.getLanguageId()) {
                // diese Sprache selektieren
                lang = l;
                break;
            }

        }
        // die Sprache in der ComboBox selektieren
        cmbSprache.setValue(lang);

    }

    // dem Controller (nach Ende des Dialogs) das Result verfügbar machen
    public Student getResult(){
        return result;
    }
    // Klasse für die ListCell für die Sprachen
    private class LanguageListCell extends ListCell<Language> {
        @Override
        protected void updateItem(Language language, boolean empty) {
            // Styles usw. setzen
            super.updateItem(language, empty);

            if (empty || language == null) {
                setText(null);
            } else {
                setText(String.format("%s (%s)", language.getName(), language.getCode()));
            }
        }
    }

}
