package projet.control.vue;

import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import org.controlsfx.control.textfield.TextFields;

import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import projet.control.metier.Article;
import projet.control.metier.Client;
import projet.control.metier.Commande;
import projet.control.metier.Connection_Base;
import projet.control.metier.Mode_Reglements;

public class article1controlleur implements Initializable {
	
	
	@FXML private TableView<Article> commande_table;
	
	@FXML private TableColumn<Article, String> Code;
	
	@FXML private TableColumn<Article, String> Code_Categorie;
	
	@FXML private TableColumn<Article, String> Designation;
	
	@FXML private TableColumn<Article, Integer> Quantite;
	
	
	@FXML private TableColumn<Article, Double> Prix_Unitaire;
	
	@FXML
	private TableColumn<Article, Double> Total_montant;
	
	@FXML
	private TextField Code1;
	
	@FXML
	private TextField Code_Categorie1;
	
	@FXML
	private TextField Designation1;
	
	@FXML
	private TextField  Prix_Unitaire1;
	
	@FXML
	private TextField Quantite1;
	
	@FXML private TextField code;
	@FXML private TextField  code_categorie;
	@FXML private TextField quantite;
	@FXML private TextField prix_unitaire ;
	@FXML private TextField designation;
	
	private Connection connection=Connection_Base.getconnection();
	private HashMap<String, Article> liste=new Article().lireRecupCRUD();
	private HashMap<String, Client> listeclient=new Client().liste_Client();
	private HashMap<String, Mode_Reglements> listeModeReglement=new Mode_Reglements().listeMode_Reglement();
	private HashMap<String, Commande>listeC=new Commande().listeCommande();
	private String code_commande;
	
	private ArrayList<Article> panier=new ArrayList<>();
	private HashMap<String, Article>liste_article_commande=new HashMap<>();
	  ObservableList<Article> ArticleData=FXCollections.observableArrayList();
	  private String Code_Article;
	  private int u=0;
	 double total_paye=0;
	 
	
	@FXML
	private void ajouter_article()
	{
try {
			
			new Article().creerCRUD(code.getText(), code_categorie.getText(), designation.getText(), Integer.parseInt(quantite.getText()),Double.parseDouble(prix_unitaire.getText()) ,"sfd");
			
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
	private void Modifer_article()
	{
		new Article().modifierCRUD(code.getText(), code_categorie.getText(), designation.getText(), Integer.parseInt(quantite.getText()),Double.parseDouble(prix_unitaire.getText()));
		code.setText("");
		code_categorie.setText("");
		quantite.setText("");
		prix_unitaire.setText("");
		designation.setText("");
	}
	@FXML
	private void supprimer_Article()
	{
		new Article().supprimerCRUD(code.getText());
		int indexligne=commande_table.getSelectionModel().getSelectedIndex();
		commande_table.getItems().remove(indexligne);
		
		code.setText("");
		code_categorie.setText("");
		quantite.setText("");
		prix_unitaire.setText("");
		designation.setText("");
	}
	
	@FXML
	private void Annuler_insertion()
	{
		code.setText("");
		code_categorie.setText("");
		quantite.setText("");
		prix_unitaire.setText("");
		designation.setText("");
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		for (Entry<String, Article> article1 :liste.entrySet()) 
		{
			Article article=new  Article(article1.getValue().getCode(),article1.getValue().getCode_catogorie(),article1.getValue().getDesignation(),article1.getValue().getQuantite(),article1.getValue().getPrix_unitaire(),article1.getValue().getDate());
			
			ArticleData.add(article);
		}
		Code.setCellValueFactory(cellData -> cellData.getValue().codeProperty());
		Code_Categorie.setCellValueFactory(cellData -> cellData.getValue().code_categorieProperty());
		Designation.setCellValueFactory(cellData -> cellData.getValue().designationProperty());
		Quantite.setCellValueFactory(cellData -> cellData.getValue().quantiteProperty().asObject());
		Prix_Unitaire.setCellValueFactory(cellData -> cellData.getValue().prix_unitaire().asObject());
		
		commande_table.setItems(ArticleData);
		
		commande_table.getSelectionModel().selectedItemProperty().addListener(
				 (observable, oldValue, newValue) -> iniArticle(newValue));	
		
		int compteur=0;
		String[] article1=new String[liste.size()];
		for(Entry<String, Article> article3:liste.entrySet())
		{
			article1[compteur]=article3.getValue().getCode()+" "+"   "+"   "+article3.getValue().getDesignation()+"      "+article3.getValue().getCode_catogorie()+"      "+article3.getValue().getQuantite()+"      "+article3.getValue().getPrix_unitaire();
			compteur+=1;
		}
	TextFields.bindAutoCompletion(Code1, article1).getCompletionTarget();
		
	}
	public void iniArticle(Article article) {
		if(article!=null)
		{
			code.setText(article.getCode());
			code_categorie.setText(article.getCode_catogorie());
			
			designation.setText(article.getDesignation());
			prix_unitaire.setText(String.valueOf(article.getPrix_unitaire()));
			quantite.setText(String.valueOf(article.getQuantite()));
		}else
		{
			code.setText("");
			code_categorie.setText("");
		
			designation.setText("");
			prix_unitaire.setText("");
			quantite.setText("");
		}
		

		
		
	}
	
	
	@FXML
	private void boutonok()
	{
		String mot1=Code1.getText();
		String code_client="";
		for(int o = 0;o<mot1.length();o++)
		{
			if(mot1.charAt(o)==' ')
			{
				break;
			}else
			{
				code_client+=mot1.charAt(o);
			}
		}
		
		code.setText(liste.get(code_client).getCode());
		code_categorie.setText(liste.get(code_client).getCode_catogorie());
	
		designation.setText(liste.get(code_client).getDesignation());
		prix_unitaire.setText(String.valueOf(liste.get(code_client).getPrix_unitaire()));
		quantite.setText(String.valueOf(liste.get(code_client).getQuantite()));
	}

}
