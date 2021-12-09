package students.program;

import common.FxmlHelper;
import javafx.application.Application;
import javafx.stage.Stage;

public class StudentTableMain extends Application {

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {


		// unsere neue schlaue Methode aufrufen
		StudentTableController controller =  FxmlHelper.initStage(primaryStage,
				"/students/views/StudentTableView.fxml", "TableView Demo");

		// dem Controller den Pfad zum Repository mitteilen:
		controller.setRepositoryPath("studentList/Repository.seri");

		primaryStage.show();

	}

}
