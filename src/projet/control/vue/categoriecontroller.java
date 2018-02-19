package projet.control.vue;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import projet.control.metier.Categorie;

public class categoriecontroller implements Initializable{

	@FXML
	private TextField code;
	
	@FXML
	private TextField designation;
	
	private Stage primaryStage;
	
	@FXML TableView<Categorie> tab_categorie;
	@FXML TableColumn<String, Categorie>code_tab;
	@FXML TableColumn<String, Categorie>designation_tab;
	ArrayList<Categorie>liste=new Categorie().liste_simple_Categorie();
	ObservableList<Categorie>categorieData=FXCollections.observableArrayList(liste);
	
	
	
	
	public categoriecontroller() {
		
		// TODO Auto-generated constructor stub
	}

	public void setContenneur(Stage primaryStage) {
		this.primaryStage=primaryStage;
		
	}
	
	@FXML
	private void insertionCategorie()
	{
		new Categorie().CreerCategorie(code.getText(), designation.getText());
	}
	
	@FXML
	private void supprimerCategorie()
	{
		new Categorie().supprimerCategorie(code.getText());
	}
	
	@FXML
	private void modifierCategorie()
	{
		new Categorie().ModifierCategorie(code.getText(), designation.getText());
	}

	@Override
	public void initialize(URL url, ResourceBundle resource) {
		
		//code_tab.setCellValueFactory(cellData ->cellData.getValue().);
	}
	
}
