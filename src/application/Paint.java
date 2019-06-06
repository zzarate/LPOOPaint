package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

// Projeto disponivel em: github.com/zzarate/LPOOPaint

public class Paint extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		Parent root= FXMLLoader.load(getClass().getResource("PaintInterface.fxml"));
		Scene scene= new Scene (root);
		
		stage.setTitle("Paint"); // Define o titulo da aplicação
		Image icone = new Image(getClass().getResourceAsStream("/Icons/icon.png")); // Define a localização do icone
		stage.getIcons().add(icone); // Adciona o icone a aplicação
		stage.setScene(scene);
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
