package projet.control.vue;

import java.io.IOException;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import projet.control.Main;

public class LoginController {
	@FXML
	private Label lbl_infoconnexion;
	
	@FXML
	private JFXTextField tfd_logid;
	
	@FXML
	private JFXPasswordField pwd_passe;
	
	@FXML
	private AnchorPane cmdLayout;

	//fenetre de connection mot de passe=elvis et utilisateur=marius
	Stage primaryStage = new Stage();
	public void login() throws Exception {
		
		if (tfd_logid.getText().equals("marius") && pwd_passe.getText().equals("elvis")) {
			FXMLLoader loader=new FXMLLoader();
			loader.setLocation(Main.class.getResource("vue/Fenetre.fxml"));
			AnchorPane page =(AnchorPane) loader.load();
			Stage contenneur =new Stage();
			Scene scene=new Scene(page);
			contenneur.setScene(scene);
			
			contenneur.show();
			
			
		}
		else {
			lbl_infoconnexion.setText("Connexion echoue");
		}
	}
	/*@FXML
	AnchorPane articleLayout;
	
	@FXML
	public void contenu(ActionEvent event) throws IOException {
		AnchorPane pane = FXMLLoader.load(new Main().getClass().getResource("vue/client.fxml"));
		articleLayout.getChildren().setAll(pane);
		
	}
	
	public void contenuArticle(MouseEvent event) throws IOException {
		AnchorPane pane = FXMLLoader.load(new Main().getClass().getResource("vue/client.fxml"));
		articleLayout.getChildren().setAll(pane);
		
	}
	*/

}
