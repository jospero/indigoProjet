package projet.control.vue;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.ResourceBundle;

import org.controlsfx.control.textfield.TextFields;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import projet.control.metier.Article;
import projet.control.metier.Client;
import projet.control.metier.Commande;
import projet.control.metier.Connection_Base;
import projet.control.metier.Mode_Reglements;

public class commandecontroller implements Initializable {
	
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
	
	@FXML
	private TextField Total_montant1;
	
	@FXML
	private TextField Code_Client;
	
	@FXML
	private TextField Contact;
	
	@FXML
	private TextField Nom_Client;
	
	@FXML
	private TextField Prenom_Client;
	
	@FXML
	ComboBox combobox;
	
	@FXML
	TextField champ;
	
	@FXML
	Label Montant_Affichage;
	
	
	
	
	private Connection connection=Connection_Base.getconnection();
	private HashMap<String, Article> liste=new Article().lireRecupCRUD();
	private ArrayList<String>listeDesignation=new Article().liste();
	private HashMap<String, Client> listeclient=new Client().liste_Client();
	private HashMap<String, Mode_Reglements> listeModeReglement=new Mode_Reglements().listeMode_Reglement();
	private HashMap<String, Article> listeArticle=new HashMap<>();
	private HashMap<String, Article> listeD=new Article().listeDe();
	private double somme1 =0;
	private int compteur=0;
	private String mot_compte=new String();
	private HashMap<String, Commande>listeC=new Commande().listeCommande();
	private int i;
	private String code_commande;
	
	
	
	public String getCode_commande() {
		return code_commande;
	}

	public void setCode_commande(String code_commande) {
		this.code_commande = code_commande;
	}

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public commandecontroller()
	{
		
	}
	public void setContenneur(Stage primaryStage) {
		
	}
	
	@FXML
	public void Ajouter_commande()
	{
		double montant1=0;
		double montant_total=Double.parseDouble(Quantite1.getText().toString())* liste.get(Code1.getText()).getPrix_unitaire(); 
		
		Article article=new  Article(Code1.getText(),liste.get(Code1.getText()).getCode_catogorie(),liste.get(Code1.getText()).getDesignation(),Integer.parseInt(Quantite1.getText()),liste.get(Code1.getText()).getPrix_unitaire(),montant_total,liste.get(Code1.getText()).getDate());
		
		listeArticle.put(Code1.getText(), article);
		
		 ObservableList<Article> ArticleData=FXCollections.observableArrayList();
		 
			for (Entry<String, Article> article1 : listeArticle.entrySet()) {
				ArticleData.add(article1.getValue());
				}
			
			
			Code.setCellValueFactory(cellData -> cellData.getValue().codeProperty());
			Code_Categorie.setCellValueFactory(cellData -> cellData.getValue().code_categorieProperty());
			Designation.setCellValueFactory(cellData -> cellData.getValue().designationProperty());
			Quantite.setCellValueFactory(cellData -> cellData.getValue().quantiteProperty().asObject());
			Prix_Unitaire.setCellValueFactory(cellData -> cellData.getValue().prix_unitaire().asObject());
			Total_montant.setCellValueFactory(cellData -> cellData.getValue().Montant_ttc().asObject());
			
			
			commande_table.setItems(ArticleData);
			
			/*for (Entry<String, Article> article1 : listeArticle.entrySet())
			{
				montant1+=article1.getValue().getMontant_ttc();
				Montant_Affichage.setText("MONTANT: "+String.valueOf(montant1)+" FCFA");
			}*/
			
		/*	commande_table.getSelectionModel().selectedItemProperty().addListener(
					 (observable, oldValue, newValue) -> iniCLient(newValue));*/
		
			

	}
	
	@FXML
	public void passercommande()
	{
	
	
	double somme = 0;
	int quantite1=0;
	int i=0;
	String code_commande="COM"+0;
	String mot1=Code_Client.getText();
	String code_client="";
	
	while(listeC.containsKey(code_commande))
	{
		i+=1;
		 code_commande="COM"+i;
	}
	
	
	System.out.println(code_commande);
	
		try
		{
			
			
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
			
		    String mot=(String) combobox.getValue();
		    mot=mot.toLowerCase();
		    Mode_Reglements modereglement=listeModeReglement.get(mot);
		   PreparedStatement prepare=connection.prepareStatement("insert into commandes(code,total_ttc,date,code_client,code_mode_reglements) values (?,?,now(),?,?)");
		   prepare.setString(1,code_commande);
		   prepare.setDouble(2,somme );
		   prepare.setString(3,code_client );
		   prepare.setInt(4, listeModeReglement.get(mot).getCode());
		   prepare.executeUpdate();
		   
		   
		   for (Entry<String, Article> article1 : listeArticle.entrySet())
			{
				somme+=article1.getValue().getMontant_ttc();	
			    PreparedStatement prepare1=connection.prepareStatement("insert into lignes_commandes() values(?,?,?,?,?)");
				prepare1.setString(1,code_commande );
				prepare1.setString(2, article1.getValue().getCode());
				prepare1.setInt(3, article1.getValue().getQuantite());
				prepare1.setDouble(4, article1.getValue().getPrix_unitaire());
				prepare1.setDouble(5, article1.getValue().getMontant_ttc());
				prepare1.executeUpdate();
				
			}
		   
		   
		   
		   
		    for(Entry<String, Article>article1:liste.entrySet())
		    {
		    	
		    	for (Entry<String, Article> article2 : listeArticle.entrySet())
				{
					if(article1.getValue().getCode().equals(article2.getValue().getCode()))	
					{
						quantite1=article1.getValue().getQuantite()-article2.getValue().getQuantite();
						Statement state=connection.createStatement();
						int res=state.executeUpdate("update articles set quantite='"+quantite1+"' where code='"+article2.getValue().getCode()+"' ");
					}
				}
		    	
		    }
		   
		     
			
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		listeC=new Commande().listeCommande();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		combobox.getItems().add("ESPECE");
		combobox.getItems().add("CHEQUE");
		combobox.getItems().add("CARTE");
		String[] client=new String[listeclient.size()];
		for(int i=0;i<client.length;i++)
		{
			int t=i+1;
			String mot="CL"+t;
			client[i]=listeclient.get(mot).getCode()+" "+listeclient.get(mot).getNom()+" "+listeclient.get(mot).getPrenom()+" "+listeclient.get(mot).getTel_fixe();
		}
		
		
		
		
	TextFields.bindAutoCompletion(Designation1, listeDesignation).getCompletionTarget();

   TextFields.bindAutoCompletion(Code_Client, client);
   
   Code1.setOnMouseReleased(new EventHandler<MouseEvent>() 
   {
	public void handle(MouseEvent me) {
		String mot_code=Code1.getText();
		if(!mot_code.equals(""))
		{
			Designation1.setText(liste.get(mot_code).getDesignation());
			Code_Categorie1.setText(liste.get(mot_code).getDesignation());
		}
		
		
	}

	
    }
   );
   
   Designation1.setOnMouseReleased(new EventHandler<MouseEvent>() {
      public void handle(MouseEvent me)
      {
    	  String mot_designation=Designation1.getText();
    	  if(!mot_designation.equals(""))
    	  {
    		  Code1.setText(listeD.get(mot_designation).getCode());
    		  Code_Categorie1.setText(listeD.get(mot_designation).getCode_catogorie());
    	  }
      }
   });
		
	}
	
	

	
	
	
	
}
