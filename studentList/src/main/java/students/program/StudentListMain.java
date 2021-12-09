package students.program;

import common.FxmlHelper;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StudentListMain extends Application {

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {

//		Parent root = (Parent)
//				FXMLLoader.load(getClass().getResource("/students/views/StudentListView.fxml"));
//		Scene scene = new Scene(root, 800, 750);
//		// das CSS-Stylesheet laden und setzen
//		scene.getStylesheets().add(getClass().getResource("/students/views/studentStyles.css").toExternalForm());
//		// das ganze anzeigen
//		primaryStage.setScene(scene);
//		primaryStage.setTitle("Collection Binding Demo");

		// unsere neue schlaue Methode aufrufen
		StudentListController controller =  FxmlHelper.initStage(primaryStage,
				"/students/views/StudentListView.fxml", "Collection Binding Demo");

		// dem Controller den Pfad zum Repository mitteilen:
		controller.setRepositoryPath("studentList/Repository.seri");

		primaryStage.show();

	}

}
