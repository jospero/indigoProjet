package projet.control.vue;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import projet.control.Main;
import projet.control.metier.Article;
import projet.control.metier.Client;

public class articlecontroller   {

	
	@FXML
	private TableView<Article> tab_article;
	
	@FXML
	private TableColumn<String, Client> code_tab;
	
	@FXML
	private TableColumn<String, Article> code_categorie_tab;
	
	@FXML
	private TableColumn<String, Article> designation_tab;
	
	@FXML
	private TableColumn<Integer, Article> quantite_tab;
	
	@FXML
	private TableColumn<Double, Article> prix_unitaire_tab;
	
	

	@FXML
	private TextField code;
	
	
	@FXML
	private TextField  code_categorie;
	
	
	@FXML
	private TextField quantite;
	
	@FXML
	private TextField prix_unitaire ;
	
	
	@FXML
	private TextField designation;
	
	private Stage primaryStage;
	
	private Article article=new Article();
	private ArrayList<Article> listeArticle=new Article().getLesEnreg();
	private ObservableList<Article> ArticleData=FXCollections.observableArrayList();
	
	
	
	
	public articlecontroller() {
		
		// TODO Auto-generated constructor stub
	}

	public void setContenneur(Stage primaryStage) {
		this.primaryStage=primaryStage;
		
	}
	
	@FXML
	private void Handlearticle()
	{
		
		try {
			
			article.creerCRUD(code.getText(), code_categorie.getText(), designation.getText(), Integer.parseInt(quantite.getText()),Double.parseDouble(prix_unitaire.getText()) ,"sfd");
			
			code.setText("");
			code_categorie.setText("");
			quantite.setText("");
			prix_unitaire.setText("");
			designation.setText("");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	private void annulerArticle()
	{
		code.setText("");
		code_categorie.setText("");
		quantite.setText("");
		prix_unitaire.setText("");
		designation.setText("");
	}
	
	@FXML
	private void miseajour()
	{
		article.modifierCRUD(code.getText(), code_categorie.getText(), designation.getText(), Integer.parseInt(quantite.getText()),Double.parseDouble(prix_unitaire.getText()));
		code.setText("");
		code_categorie.setText("");
		quantite.setText("");
		prix_unitaire.setText("");
		designation.setText("");
	}
	
	@FXML
	private void supprimer()
	{
		article.supprimerCRUD(code.getText());
		code.setText("");
		code_categorie.setText("");
		quantite.setText("");
		prix_unitaire.setText("");
		designation.setText("");
	}
	

}
