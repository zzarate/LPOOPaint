package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *  @author Joao Victor Zarate e Julio Huang
 *  github.com/zzarate/LPOOPaint
 */

public class Paint extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		Parent root= FXMLLoader.load(getClass().getResource("PaintInterface.fxml"));
		Scene scene= new Scene (root);
		
		stage.setTitle("Paint");
		stage.setScene(scene);
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
