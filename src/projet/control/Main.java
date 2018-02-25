package projet.control;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
     
	private Stage primaryStage;
	private AnchorPane root;
	@Override
	public void start(Stage primaryStage) throws IOException {
		this.primaryStage=primaryStage;
		initRoot();
		
		
	}
	
	@FXML
	AnchorPane articleLayout;

	public void initRoot() 
	{
		try {
			FXMLLoader loader= new FXMLLoader();
			loader.setLocation(Main.class.getResource("vue/FenConnexion.fxml"));
			root=(AnchorPane) loader.load();
			Scene scene=new Scene(root);
			
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch (Exception e) {
		 e.printStackTrace();
		}
		
	}
	
	
	public void Afficheclient()
	{
	}

	
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
