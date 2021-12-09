package students.program;

import common.FxmlHelper;
import common.MessageBox;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import students.model.Language;
import students.model.Student;
import students.repository.LanguageRepository;
import students.repository.StudentRepository;
import students.repository.StudentRepositoryException;
import students.repository.serialization.LanguageSerializeRepository;
import students.repository.serialization.StudentSerializeRepository;

import java.io.IOException;
import java.util.List;

public class StudentListController {

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
    private ListView<Student> lstStudents;

    @FXML
    // Node ist Basisklasse von allen Controls und Containern -> damit könnte ich im FXML-File
    // auch einen anderen Container verwenden ohne den Controller zu ändern
    private Node boxSelStudent;

    public StudentListController() {
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

        // unsere ListCell-Factory-Methode verwenden
        lstStudents.setCellFactory(this::createStudentListCell);

        // Handler für Änderungen im Listview
        lstStudents.getSelectionModel().selectedItemProperty().addListener(
                // in der ObservableProperty das Objekt eintragen, das jetzt selektiert ist
                (o,oldVal,newVal)-> {
                    setSelectedStudent(newVal);

                });


        // Anzeige anpassen je nachdem ob ein Item selektiert ist oder nicht
        // Variante 1: Listener registrieren
        // bei unserer Property einen Listener hinzufügen
//        selectedStudent.addListener((o, oldVal, newVal)->{
//            boxSelStudent.setVisible(newVal != null);
//            btnEdit.setDisable(newVal == null);
//            btnDelete.setDisable(newVal == null);
//        });
//        // und Properties initialisieren
//        boxSelStudent.setVisible(false);
//        btnEdit.setDisable(true);
//        btnDelete.setDisable(true);

        // Variante 2: die Properties an Bindings binden
        boxSelStudent.visibleProperty().bind(Bindings.isNotNull(selectedStudentProperty()));
        btnEdit.disableProperty().bind(Bindings.isNull(selectedStudentProperty()));
        btnDelete.disableProperty().bind(Bindings.isNull(selectedStudentProperty()));

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
                int index = lstStudents.getItems().indexOf(getSelectedStudent());
                lstStudents.getItems().set(index, changedStudent);

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
            // aus dem Repository löschen
            studentRepository.deleteStudent(getSelectedStudent().getId());
            // aus dem ListView entfernen
            lstStudents.getItems().remove(getSelectedStudent());
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
            lstStudents.getItems().clear();
            lstStudents.getItems().addAll(list);
        } catch (StudentRepositoryException e) {
			// für uns, während wir das Programm entwickeln, alles anzeigen
            e.printStackTrace();
            // für den Benutzer den Fehler in einem Alert anzeigen
            MessageBox.show("Laden", "Fehler beim Laden: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }


    // Methode die wir als callFactory für den ListView verwenden können
    private ListCell<Student> createStudentListCell(ListView<Student> listView){
        // eine (anonyme) Klasse von ListCell ableiten und eine Instanz davon zurückliefern
        return new ListCell<Student>(){
            @Override
            protected void updateItem(Student student, boolean empty) {
                // Styles usw. richtig setzen
                super.updateItem(student, empty);
                if(empty || student == null){
                    setText(null); // in der Zelle nichts anzeigen
                }else{
                    String text = String.format("%s (%d %s)",
                            student.getName(), student.getAreaCode(), student.getCity());
                    System.out.println("Cell-Factory für " + text);
                    setText(text);
                }

            }
        };
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


}
