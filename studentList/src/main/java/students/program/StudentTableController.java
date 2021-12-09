package students.program;

import common.FxmlHelper;
import common.MessageBox;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import students.model.Gender;
import students.model.Student;
import students.repository.LanguageRepository;
import students.repository.StudentRepository;
import students.repository.StudentRepositoryException;
import students.repository.serialization.LanguageSerializeRepository;
import students.repository.serialization.StudentSerializeRepository;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;

public class StudentTableController {

    // Zugriff auf das Repository:
    private StudentRepository studentRepository;
    private LanguageRepository languageRepository;

    // observable Property für das selektierte StudentObjekt
    private ObjectProperty<Student> selectedStudent;


    @FXML
    private Button btnAdd;

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnReload;

    @FXML
    private TableView<Student> tblStudents;

    // Tabellen-Spalten mit passendem Typ
    @FXML
    TableColumn<Student, Integer> colId;
    @FXML
    TableColumn<Student, String> colName;
    @FXML
    TableColumn<Student, Integer> colAreaCode;
    @FXML
    TableColumn<Student, String> colCity;
    @FXML
    TableColumn<Student, LocalDate> colBirthdate;
    @FXML
    TableColumn<Student, Gender> colGender;
    @FXML
    TableColumn<Student, Boolean> colHtml;
    @FXML
    TableColumn<Student, Boolean> colXml;
    @FXML
    TableColumn<Student, Boolean> colFxml;

    @FXML
    // Node ist Basisklasse von allen Controls und Containern -> damit könnte ich im FXML-File
    // auch einen anderen Container verwenden ohne den Controller zu ändern
    private Node boxSelStudent;

    public StudentTableController() {
        // das Observable-Property-Objekt
        selectedStudent = new SimpleObjectProperty<>();
    }

    // Observable Property implementieren: braucht getter, setter und die Property selber
    public Student getSelectedStudent(){
        // das Objekt aus der Property liefern
        return selectedStudent.get();
    }

    public void setSelectedStudent(Student student){
        // das Objekt an die Property übergeben
        selectedStudent.set(student);
    }

    public ObjectProperty<Student> selectedStudentProperty(){
        return  selectedStudent;
    }



    public void setRepositoryPath(String path) {
        // Repository -Objekte erzeugen
        studentRepository = new StudentSerializeRepository(path);
        languageRepository = new LanguageSerializeRepository();

        reload();
    }

    @FXML
    private void initialize() {
        System.out.println("init StudentList");


        // Spalten korrekt anzeigen
        // PropertyValueFactory ist eine vordefinierte Klasse, die aus einem Objekt eine Property
        // ausliest und anzeigt, z.B. die id aus dem Student-Objekt
        colId.setCellValueFactory(new PropertyValueFactory<Student, Integer>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<Student, String>("name"));
        colAreaCode.setCellValueFactory(new PropertyValueFactory<>("areaCode"));
        colCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        colBirthdate.setCellValueFactory(new PropertyValueFactory<Student, LocalDate>("birthDate"));
        colGender.setCellValueFactory(new PropertyValueFactory<Student, Gender>("gender"));
        colHtml.setCellValueFactory(new PropertyValueFactory<Student, Boolean>("html"));
        colXml.setCellValueFactory(new PropertyValueFactory<>("xml"));
        colFxml.setCellValueFactory(new PropertyValueFactory<>("fxml"));

        //  die CellFactories setzen
        // das Geburtsdatum im lokalen Format anzeigen
        colBirthdate.setCellFactory(this::localDateCell);
        // die Booleschen Spalten als CheckBox anzeigen
        colHtml.setCellFactory(this::checkBoxCell);
        colXml.setCellFactory(this::checkBoxCell);
        colFxml.setCellFactory(this::checkBoxCell);


        // Handler für Änderungen im Listview
        tblStudents.getSelectionModel().selectedItemProperty().addListener(
                // in der ObservableProperty das Objekt eintragen, das jetzt selektiert ist
                (o,oldVal,newVal)-> {
                    setSelectedStudent(newVal);

                });

        // Anzeige anpassen je nachdem ob ein Item selektiert ist oder nicht
        // bei unserer Property einen Listener hinzufügen
        selectedStudent.addListener((o, oldVal, newVal)->{
            boxSelStudent.setVisible(newVal != null);
            btnEdit.setDisable(newVal == null);
            btnDelete.setDisable(newVal == null);
        });

        boxSelStudent.setVisible(false);
        btnEdit.setDisable(true);
        btnDelete.setDisable(true);
    }

    @FXML
    public void addStudent() {
        System.out.println("addStudent");
        try {
            Student newStudent = editStudent(null, "Hinzufügen");
            if(newStudent != null){
                studentRepository.insertStudent(newStudent);
                // kompletten reload machen, damit wird die Liste aktualisiert
                // und ggf. die korrekte ID für das neue Objekt verwendet
                reload();
            }

        } catch (IOException | StudentRepositoryException e) {
            e.printStackTrace();
            MessageBox.show("Hinzufügen", "Fehler beim Hinzufügen eine*s Student*in: "
                    + e.getMessage(), Alert.AlertType.ERROR);

        }

    }

    @FXML
    public void editStudent() {
        System.out.println("editStudent");
        try {
            Student changedStudent = editStudent(getSelectedStudent(), "Ändern");
            if(changedStudent != null){
                // im Repository ersetzen
                studentRepository.updateStudent(changedStudent);
                // und im ListView ersetzen
                int index = tblStudents.getItems().indexOf(getSelectedStudent());
                tblStudents.getItems().set(index, changedStudent);

            }
        } catch (IOException | StudentRepositoryException e) {
            e.printStackTrace();
            MessageBox.show("Ändern", "Fehler beim Ändern eine*s Student*in: "
                    + e.getMessage(), Alert.AlertType.ERROR);

        }
    }

    @FXML
    public void deleteStudent() {
        System.out.println("deleteStudent");
        try{
            ButtonType antwort = MessageBox.show("Löschen", "Student*in löschen?",
                    Alert.AlertType.CONFIRMATION, ButtonType.OK, ButtonType.CANCEL);
            if(antwort.equals(ButtonType.OK)) {
                // aus dem Repository löschen
                studentRepository.deleteStudent(getSelectedStudent().getId());
                // aus dem ListView entfernen
                tblStudents.getItems().remove(getSelectedStudent());
            }
        }catch (StudentRepositoryException e) {
            e.printStackTrace();
            MessageBox.show("Löschen", "Fehler beim Löschen eine*s Student*in: "
                    + e.getMessage(), Alert.AlertType.ERROR);

        }

    }


    @FXML
    public void reload() {
        System.out.println("reload");
        try {
            // alle Studenten laden
            List<Student> list = studentRepository.selectAll();
            // im Listview anzeigen
            tblStudents.getItems().clear();
            tblStudents.getItems().addAll(list);
        } catch (StudentRepositoryException e) {
			// für uns, während wir das Programm entwickeln, alles anzeigen
            e.printStackTrace();
            // für den Benutzer den Fehler in einem Alert anzeigen
            MessageBox.show("Laden", "Fehler beim Laden: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }



    private Student editStudent(Student s, String title) throws IOException, StudentRepositoryException {
        Stage dialogStage = new Stage();
        EditStudentController_Validation controller = FxmlHelper.initAsDialog(dialogStage,
                "/students/views/EditStudent.fxml", title);
        // dem Controller das Objekt zur Anzeige übergeben
        controller.setStudent(s, languageRepository.selectAll());

        // anzeigen und warten bis die Maske geschlossen wurde
        dialogStage.showAndWait();
        // hierher kommen wir erst, wenn der User den Dialog geschlossen hat
        Student result = controller.getResult();
        System.out.println("Student vom Edit-Dialog erhalten: " + result);
        return result;
    }

    // Methode die wir als cellFactory für den TableView verwenden können
    private TableCell<Student, LocalDate> localDateCell(TableColumn<Student, LocalDate> column) {

        // anonyme Spezialisierung von TableCell
        return new TableCell<Student, LocalDate>() {
            @Override
            protected void updateItem(LocalDate item, boolean empty) {
                // Basisklasse aufrufen, für Selection
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    // das Datum selber formatieren
                    setText(item.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)));
                }
            }
        };

    }

    private TableCell<Student, Boolean> checkBoxCell(TableColumn<Student, Boolean> column) {
        // eine CheckBoxTableCell erstellen, die - wenn der Wert benötigt wird -
        // den Zellwert des Items, das angezeigt wird abruft
        //
        return new CheckBoxTableCell<Student, Boolean>(index -> {

            // dieser Block wird aufgerufen, wenn der Wert für eine Zelle benötigt wird
            // index ist der Zeilenindex im TableView
            System.out.printf("Checkbox für Spalte %s/Index %d: Wert==%b %n",
                    column.getText(), index, column.getCellData(index));
            // eine Property mit dem boolean-Wert in dieser Zeile liefern
            return new SimpleBooleanProperty(column.getCellData(index));
        });

    }

    @FXML
    private void onClicked(MouseEvent e){
        if(e.getClickCount() == 2){
            // den Callback für den Edit-Button aufrufen
            editStudent();
        }
    }

    @FXML
    private void onKey(KeyEvent e){
        switch (e.getCode()){
            case ENTER -> editStudent();
            // Achtung: deleteStudent löscht ohne Rückfrage
            case DELETE -> deleteStudent();
        }
    }

}
