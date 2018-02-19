package projet.control.vue;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class essai1controller  {
	
	@FXML
	TextField champ;
	
	private Stage primaryStage;
	String[] tab= {"leo","leonel","leon","bonsoir","bonjour","salut","salutation","saluer"};

	public void setContenneur(Stage primaryStage) {
		this.primaryStage=primaryStage;
		
	}

	/*@Override
	public void initialize(URL url, ResourceBundle resources) {
		
		TextFields.bindAutoCompletion(champ, tab);
	}*/

}
