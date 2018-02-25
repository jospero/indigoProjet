package projet.control.vue;

import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainController  {
	private Stage primarystage;
	

	@FXML
	AnchorPane articleLayout;
	
/*	@FXML
	public void contenu(ActionEvent event) throws IOException {
		AnchorPane pane = FXMLLoader.load(MainController.class.getResource("/vue/client.fxml"));
		articleLayout.getChildren().setAll(pane);
		
	}*/
/*
	@Override
	public void start(Stage primarystage) throws Exception {
		this.primarystage=primarystage;
		AnchorPane pane = FXMLLoader.load(new MainController().getClass().getResource("/vue/client.fxml"));
		articleLayout.getChildren().setAll(pane);
		
	
	}
	public static void main(String[] args) {
		launch(args);
	}*/

}
