package projet.control;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
     
	private Stage primaryStage;
	private BorderPane root;
	@Override
	public void start(Stage primaryStage) throws IOException {
		this.primaryStage=primaryStage;
		initRoot();
		Ajout();
		
	}

	public void initRoot() 
	{
		try {
			FXMLLoader loader= new FXMLLoader();
			loader.setLocation(Main.class.getResource("vue/Root.fxml"));
			root=(BorderPane) loader.load();
			Scene scene=new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch (Exception e) {
		 e.printStackTrace();
		}
		
	}
	
	public void Ajout()
	{
		try {
			FXMLLoader loader=new FXMLLoader(); 
			loader.setLocation(Main.class.getResource("vue/Fenetre.fxml"));
			AnchorPane fenetre=(AnchorPane) loader.load();
			root.setCenter(fenetre);
			
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
