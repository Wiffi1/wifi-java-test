package students.program;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class StudentListController {


	
	
	@FXML
	private Button btnAdd;

	@FXML
	private Button btnEdit;

	@FXML
	private Button btnDelete;

	@FXML
	private Button btnReload;

	
	public StudentListController() {
	

	}

	public void setRepositoryPath(String path) {
		reload();
	}

	@FXML
	private void initialize() {
		System.out.println("init studentlist");

	}

	@FXML
	public void addStudent() {
		System.out.println("add student");

	}

	@FXML
	public void editStudent() {
		System.out.println("edit student");
	}

	@FXML
	public void deleteStudent() {
		System.out.println("delete student");
	}


	@FXML
	public void reload() {
		System.out.println("reload");

	}


}
