/*

Übung 1

        Erstelle eine Eingabemaske und einen Controller zum Erzeugen von neuen Mitarbeitern (und ihrer Ableitungen)
        und zeige die Maske als Hauptfenster einer JavaFX Anwendung an.

        Bei Klick auf OK sollen die eingegebenen Daten für die Erzeugung eines neuen Mitarbeiter-,
        Manager- oder Experte-Objekts verwendet werden

        Übung 2
        Passe die Eingabemaske so an, dass damit Mitarbeiter (und ihre Ableitungen) auch bearbeitet werden können.
        Vor dem Anzeigen soll dafür ein Mitarbeiter-Objekt (oder Manager oder Experte) an den Controller übergeben werden.
*/


        package mitarbeiterVerwaltungFxml;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.time.LocalDate;

public class MitArbeiterController {

        @FXML private TextField txtFachgebiet;
        @FXML private TextField txtName;
        @FXML private TextField dbGrundgehalt;
        @FXML private DatePicker dtpGeburtsdatum;
        @FXML private DatePicker dtpEintrittsdatum;
        @FXML private TextField txtBonus;

        @FXML private RadioButton rbMitarbeiter;
        @FXML private RadioButton rbManager;
        @FXML private RadioButton rbExperte;
        @FXML private Button btnCancel;
        @FXML private Button btnAnlegen;
        @FXML private ToggleGroup grpMitarbeiterTyp;

        @FXML
        private void initialize() {
                // Den ok button enablen disablen je nach eingaben
                // Bei Radiobuttons auf Änderungen der selectedToggle-Eigenschaft der ButtonGroup reagieren
                grpMitarbeiterTyp.selectedToggleProperty().addListener((o, oldval, newval) -> checkGueltig());

                txtName.textProperty().addListener((o, oldval, newval) -> checkGueltig());
                dbGrundgehalt.textProperty().addListener((o, oldval, newval) -> checkGueltig());
                txtBonus.textProperty().addListener((o, oldval, newval) -> checkGueltig());

                // Bei Datepicker und Combobox die value-Eigenschaft */
                dtpGeburtsdatum.valueProperty().addListener((o, oldval, newval) -> checkGueltig());
                dtpEintrittsdatum.valueProperty().addListener((o, oldval, newval) -> checkGueltig());
                txtFachgebiet.textProperty().addListener((o, oldval, newval) -> checkGueltig());
                txtBonus.textProperty().addListener((o, oldval, newval) -> checkGueltig());
        }

        @FXML private void onAnlegen(ActionEvent actionEvent) {
                mitarbeiterAnlegen();
        }

        @FXML private void onAbbrechen(ActionEvent actionEvent) {

        }

        private void checkGueltig() {
                boolean gueltig = txtName.getText() != null && !txtName.getText().isBlank()
                    && dtpGeburtsdatum.getValue() != null
                    && grpMitarbeiterTyp.getSelectedToggle() != null;

                if (grpMitarbeiterTyp.getSelectedToggle() == rbExperte) {
                        gueltig = gueltig && txtFachgebiet.getText() != null && !txtFachgebiet.getText().isBlank();
                } else if (grpMitarbeiterTyp.getSelectedToggle() == rbManager) {
//                        NumberFormatException
                        double bonus = Double.parseDouble(txtBonus.getText().trim());
                        gueltig = gueltig && bonus >= 0;
                }

                System.out.println("Gültig; " + gueltig);
                // Den Button je nach Gültigkeit enablen/disablen
                btnAnlegen.setDisable(!gueltig);
        }

        public void mitarbeiterAnlegen() {

//                mitarbeiter.setId(Integer.parseInt(txtId.getText()));
                String name = txtName.getText().trim();
                LocalDate geburtsdatum = dtpGeburtsdatum.getValue();
                LocalDate eintrittsdatum = dtpGeburtsdatum.getValue();
                double grundgehalt = Double.parseDouble(txtBonus.getText().trim());

                Mitarbeiter mitarbeiter;
                if (grpMitarbeiterTyp.getSelectedToggle() == rbMitarbeiter) {
                        mitarbeiter = new Mitarbeiter(name, geburtsdatum, eintrittsdatum, 1000);
                } else if (grpMitarbeiterTyp.getSelectedToggle() == rbExperte) {
                        mitarbeiter = new Experte(name, geburtsdatum, eintrittsdatum, 1000, "Experte");
                } else {
                        double bonus = Double.parseDouble(txtBonus.getText().trim());
                        mitarbeiter = new Manager(name, geburtsdatum, eintrittsdatum, 1000, bonus);
                }
        }

        public void setMitarbeiter(Mitarbeiter mitarbeiter) {
                // Wenn es ein neuer Student ist ein neues Objekt erzeugen
                if (mitarbeiter == null) {
//                        (String name, LocalDate geburtsdatum, LocalDate eintrittsdatum, double grundgehalt)
                        mitarbeiter = new Mitarbeiter("", null, null, 1000);
                }

                // todo
//                txtId.setText(Integer.toString(mitarbeiter.getId()));
                txtName.setText(mitarbeiter.getName());
//                txtBonus.setText(Integer.toString(mitarbeiter.get()));
                dtpGeburtsdatum.setValue(mitarbeiter.getGeburtsdatum());
                dtpEintrittsdatum.setValue(mitarbeiter.getEintrittsdatum());

        }

}
