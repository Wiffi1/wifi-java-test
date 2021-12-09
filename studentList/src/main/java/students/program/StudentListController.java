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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import students.model.Student;
import students.repository.LanguageRepository;
import students.repository.StudentRepository;
import students.repository.StudentRepositoryException;
import students.repository.serialization.LanguageSerializeRepository;
import students.repository.serialization.StudentSerializeRepository;

import java.io.IOException;
import java.util.List;

public class StudentListController {

	// Attribut für Repository
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
	// Node ist eine Basisklasse von allen Containern -> damit könnte ich im FXML-File
	// auch einen anderen Container verwenden, ohne den Controller zu ändern.
	private Node boxSelStudent;

	public StudentListController() {
		// das Observable-Property-Objekt
		selectedStudent = new SimpleObjectProperty<>();
	}

	// Observable Property implementierieren: bracuht getter, setter unddie Poroperty selber
	public Student getSelectedStudent() {
		// das Objekt aus der Property liefern
		return selectedStudent.get();
	}

	public void setSelectedStudent(Student student) {
		// das Objekt an die Property übergeben
		selectedStudent.set(student);
	}

	public ObjectProperty<Student> selectedStudentProperty() {
		return selectedStudent;
	}

	public void setRepositoryPath(String path) {
		// Repository-Objekte erzeugn
		studentRepository = new StudentSerializeRepository(path);
		languageRepository = new LanguageSerializeRepository();
		reload();
	}

	@FXML
	private void initialize() {
		System.out.println("init studentlist");

		// unsere ListCell-Factory-Methid verwenden
		lstStudents.setCellFactory(this::createStudentListCell);

		// Handler für Änderungen
		lstStudents.getSelectionModel().selectedItemProperty().addListener(
			// in der ObservableProperty das Objekt eintragen, das jetzt selektiert ist
			(o, oldVal, newVal) -> {
				setSelectedStudent(newVal);
			});

		//	Variante 1 Listener initialisieren
		selectedStudent.addListener((o, oldVal, newVal) -> {
			boxSelStudent.setVisible(newVal != null);
			btnEdit.setDisable(newVal == null);
			btnDelete.setDisable(newVal == null);
		});

		boxSelStudent.setVisible(false);
		btnEdit.setDisable(true);
		btnDelete.setDisable(true);

		// Variante 2: Bindings verwenden
/*
		boxSelStudent.visibleProperty().bind(
			Bindings.createBooleanBinding(() -> getSelectedStudent() != null), s);
*/

	}

	@FXML
	public void addStudent() {
		System.out.println("add student");
		try {
			Student newStudent = editStudent(null, "Hinzufügen");
			if (newStudent != null) {
				studentRepository.insertStudent(newStudent);
				// kompletten relaod machen, damit wird die Liste akutaliserun gge.. die krrekte ID für das neue Objekt verwendet
				reload();
			}
		} catch (IOException | StudentRepositoryException e) {
			e.printStackTrace();
			MessageBox.show("Ändern", "Fehler beim Hinzufügen einer Student:in" + e.getMessage(),
				Alert.AlertType.ERROR);
		}
	}

	@FXML
	public void editStudent() {
		System.out.println("edit student");
		try {
			Student changedStudent = editStudent(getSelectedStudent(), "Ändern");
			if (changedStudent != null) {
				// im Repository ersetzen
				studentRepository.updateStudent(changedStudent);
				// und im Listview ersetzen
				int index = lstStudents.getItems().indexOf(getSelectedStudent());
				lstStudents.getItems().set(index, changedStudent);
			}
		} catch (IOException | StudentRepositoryException e) {
			e.printStackTrace();
			MessageBox.show("Ändern", "Fehler beim Ändern einer Student:in" + e.getMessage(),
				Alert.AlertType.ERROR);
		}
	}

	@FXML
	public void deleteStudent() {
		System.out.println("delete student");
		try {
			// aus dem Repository löschen
			studentRepository.deleteStudent(getSelectedStudent().getId());
			// enfernen
			lstStudents.getItems().remove(getSelectedStudent());
		} catch (StudentRepositoryException e) {
			e.printStackTrace();
			MessageBox.show("Löschen", "Fehler beim Löschen einer Student:in" + e.getMessage(),
				Alert.AlertType.ERROR);
		}
	}


	@FXML
	public void reload() {
		System.out.println("reload");
		try	{
			// alle Studenten laden
			List<Student> list = studentRepository.selectAll();
			// im Listview anzeigen
			lstStudents.getItems().clear();
			lstStudents.getItems().addAll(list);
		} catch (StudentRepositoryException e) {
			// für uns, während wie das Programm entwickeln alles anzeigen
			e.printStackTrace();
			// für den Benutzer den Fehler in einem Alert anzeigen
			MessageBox.show("LAden", "Fehler beim Laden" + e.getMessage(), Alert.AlertType.ERROR);
		}
	}

	private ListCell<Student> createStudentListCell(ListView<Student> listView) {
		// einen (annonyme) Klasse von ListCel ableiten und eine Instanz davon zurückliefern
		return new ListCell<Student>() {
			@Override
			protected void updateItem(Student student, boolean empty) {
				// Styles usw. richtig setzten
				super.updateItem(student, empty);
				if (empty || student == null) {
					setText(null); // in der Zelle nichts anzeigen
				} else {
					String text = String.format("%s (%d %s",
						student.getName(), student.getAreaCode(), student.getCity());
					System.out.println("Cell-Factory für " + text);
					setText(text);
				}
			}
		};
	}

	private Student editStudent(Student s, String title) throws IOException, StudentRepositoryException {
		Stage dialogStage = new Stage();
//		FxmlHelper.initStage(dialogStage, "/students/views/EditStudents.fxml", "Student:in erfassen");
//		FxmlHelper.initAsDialog(dialogStage, "/students/views/EditStudents.fxml", "Student:in erfassen");

		EditStudentController_Validation controller = FxmlHelper.initAsDialog(dialogStage,
			"/students/views/EditStudents.fxml", title);

		// dem Controller das Objekt zur Anzeige übergeben
		controller.setStudent(s, languageRepository.selectAll());

		// anzeigen und warten, bis die Maske geschlossen wurde
		dialogStage.showAndWait();

		Student result = controller.getResult();
		System.out.println("Student vom Edit-Dialgo erhalten: " + result);

		return result;
	}

/*	@FXML
	private void onClicked(MouseEvent e) {
		if (e.getClickCount() == 2) {
			// den Callback für den Edit-Button aufrufen
			editStudent();
		}
	}

	@FXML
	private void onKey(KeyEvent e) {
		switch (e.getCode()) {
			case ENTER -> editStudent();
			// ACHTUNG löscht ohne Rückfrage
			case DELETE -> deleteStudent();
		}
	}*/
}