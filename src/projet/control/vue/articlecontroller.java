package projet.control.vue;

import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import projet.control.metier.Article;
import projet.control.metier.Connection_Base;

public class articlecontroller implements Initializable  {

	@FXML
	private TableView<Article> commande_table;
	
	@FXML
	private TableColumn<Article, String> Code;
	
	@FXML
	private TableColumn<Article, String> Code_Categorie;
	
	@FXML
	private TableColumn<Article, String> Designation;
	
	@FXML
	private TableColumn<Article, Integer> Quantite;
	
	
	@FXML
	private TableColumn<Article, Double> Prix_Unitaire;
	
	
	
	

	@FXML private TextField code;
	@FXML private TextField  code_categorie;
	@FXML private TextField quantite;
	@FXML private TextField prix_unitaire ;
	@FXML private TextField designation;
	
	private Stage primaryStage;
	
	private Article article=new Article();
	private ArrayList<Article> listeArticle=new Article().getLesEnreg();
	
	private Connection connection=Connection_Base.getconnection();
	private HashMap<String, Article> liste=new Article().lireRecupCRUD();
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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		for(Entry<String, Article> article1:liste.entrySet())
		{
			article.setCode(article1.getValue().getCode());
			article.setCode_catogorie(article1.getValue().getCode_catogorie());
			article.setDate(article1.getValue().getDate());
			article.setDesignation(article1.getValue().getDesignation());
			article.setQuantite(article1.getValue().getQuantite());
			article.setPrix_unitaire(article1.getValue().getPrix_unitaire());
			
			ArticleData.add(article);
		}
		Code.setCellValueFactory(cellData -> cellData.getValue().codeProperty());
		Code_Categorie.setCellValueFactory(cellData -> cellData.getValue().code_categorieProperty());
		Designation.setCellValueFactory(cellData -> cellData.getValue().designationProperty());
		Quantite.setCellValueFactory(cellData -> cellData.getValue().quantiteProperty().asObject());
		Prix_Unitaire.setCellValueFactory(cellData -> cellData.getValue().prix_unitaire().asObject());
		commande_table.setItems(ArticleData);
		
		}
	
	
	
}
