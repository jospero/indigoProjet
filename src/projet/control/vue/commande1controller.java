package projet.control.vue;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.Map.Entry;

import javax.xml.crypto.dsig.spec.HMACParameterSpec;

import org.controlsfx.control.textfield.TextFields;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import projet.control.metier.Article;
import projet.control.metier.Client;
import projet.control.metier.Commande;
import projet.control.metier.Connection_Base;
import projet.control.metier.Mode_Reglements;

public class commande1controller implements Initializable {
	
	
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
	
	public int getU() {
		return u;
	}

	public void setU(int u) {
		this.u = u;
	}

	@FXML
	private void Ajouter_Panier()
	{
	    String quant=Quantite1.getText();
	    boolean verif=true;
	    
	    for(int u=0;u<quant.length();u++)
	    {
	    	if(quant.charAt(u)=='0'|| quant.charAt(u)=='1'||quant.charAt(u)=='2'|| quant.charAt(u)=='3'|| quant.charAt(u)=='4'|| quant.charAt(u)=='5'|| quant.charAt(u)=='6'||quant.charAt(u)=='7'|| quant.charAt(u)=='8'||quant.charAt(u)=='9')
	    	{
	    		verif=true;
	    	}else verif=false;
	    }
	    
	    if(verif==true)
	    {
	    	String mot1=Code1.getText();
			String code_article1="";
			                                for(int o = 0;o<mot1.length();o++)
							{
								if(mot1.charAt(o)==' ')
								{
									break;
								}else
								{
									code_article1+=mot1.charAt(o);
								}
							}
			if(liste.get(code_article1).getQuantite()>Integer.parseInt(Quantite1.getText()) )
			{
				double montant1=0;
				double montant_total=Double.parseDouble(Quantite1.getText().toString())* liste.get(code_article1).getPrix_unitaire(); 
				Article article=new  Article(code_article1,liste.get(code_article1).getCode_catogorie(),liste.get(code_article1).getDesignation(),Integer.parseInt(Quantite1.getText()),liste.get(code_article1).getPrix_unitaire(),montant_total,liste.get(code_article1).getDate());
			
				panier.add(article);
				ArticleData.add(article);
				Code.setCellValueFactory(cellData -> cellData.getValue().codeProperty());
				Code_Categorie.setCellValueFactory(cellData -> cellData.getValue().code_categorieProperty());
				Designation.setCellValueFactory(cellData -> cellData.getValue().designationProperty());
				Quantite.setCellValueFactory(cellData -> cellData.getValue().quantiteProperty().asObject());
				Prix_Unitaire.setCellValueFactory(cellData -> cellData.getValue().prix_unitaire().asObject());
				Total_montant.setCellValueFactory(cellData -> cellData.getValue().Montant_ttc().asObject());
				commande_table.setItems(ArticleData);
				liste.get(code_article1).setQuantite(liste.get(code_article1).getQuantite()-Integer.parseInt(Quantite1.getText()));
				 setTotal_paye(total_paye+montant_total);
					Montant_Affichage.setText("MONTANT: "+getTotal_paye());
			}else
			{
				Montant_Affichage.setText("ARTICLE INDISPONIBLE VEUILLEZ SIGNALER SVP");
			}
	    	
	    }else
	    {
	    	Montant_Affichage.setText("QUANTITE INCORRECTE");
	    }
		
		
		
		
	}
	
	public double getTotal_paye() {
		return total_paye;
	}

	public void setTotal_paye(double total_paye) {
		this.total_paye = total_paye;
	}

	@FXML
	private void Passer_commande()
	{
		if(!Code1.getText().equals("") && !Quantite1.getText().equals("") && !Code_Client.getText().equals("") )
		{
			int i=0;
			int quantite=0;
			int compteur=0;
			int[] quantitetab=new int[panier.size()];
			double somme = 0;
			for (Article article1 :panier)
			{
				quantite=article1.getQuantite();
				for (Article article2 : panier) 
				{
					if(article1.getCode().equals(article2.getCode()) && panier.indexOf(article1)!=panier.indexOf(article2))
					{
						quantite+=article2.getQuantite();
					}
				}
				
				quantitetab[compteur]=quantite;
				quantite=0;
				compteur+=1;
			}
			
			for(Article article:panier)
			{
				article.setQuantite(quantitetab[i]);
				i+=1;
			}
			
			
			
			for (Article article1 : panier) 
			{
				liste_article_commande.put(article1.getCode(), article1);
			}
			
			
			for(Entry<String, Article> article3:liste_article_commande.entrySet())
			{
				article3.getValue().setMontant_ttc(article3.getValue().getPrix_unitaire()*article3.getValue().getQuantite());
				somme+=article3.getValue().getMontant_ttc();
			}
			
		
			
			int i1=0;
			
			String mot1=Code_Client.getText();
			String code_client="";
			
			setU(u+1);
		    code_commande="COM"+getU();
			while(listeC.containsKey(code_commande))
			{
				setU(u+1);
				 code_commande="COM"+getU();
				
			}
			
			
		
			
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
				    
				   new Commande().creerCRUD(code_commande, code_client, somme, listeModeReglement.get(mot).getCode());
				   
				   for (Entry<String, Article> article1 : liste_article_commande.entrySet())
					{
							
					    PreparedStatement prepare1=connection.prepareStatement("insert into lignes_commandes() values(?,?,?,?,?)");
						prepare1.setString(1,code_commande );
						prepare1.setString(2, article1.getValue().getCode());
						prepare1.setInt(3, article1.getValue().getQuantite());
						prepare1.setDouble(4, article1.getValue().getPrix_unitaire());
						prepare1.setDouble(5, article1.getValue().getMontant_ttc());
						prepare1.executeUpdate();
					
						Document documentinscription=new Document();
						try 
						{
							
							documentinscription.add(new Paragraph(article1.getValue().getDesignation()+": "+article1.getValue().getQuantite()+" x "+article1.getValue().getPrix_unitaire()+" "+article1.getValue().getMontant_ttc()));
								} catch (Exception e) {
							
						}
						
						
					}
				   
				   
				   
				   
				    for(Entry<String, Article>article1:liste.entrySet())
				    {
				    	Statement state=connection.createStatement();
						int res=state.executeUpdate("update articles set quantite='"+article1.getValue().getQuantite()+"' where code='"+article1.getValue().getCode()+"' ");	
				    }
				   	
				} 
				catch (Exception e) {
					e.printStackTrace();
				}
				
				
				liste=new Article().lireRecupCRUD();
				liste_article_commande=new HashMap<>();
				ArticleData=FXCollections.observableArrayList();
				Montant_Affichage.setText("OPERATION REUSSIE");
				commande_table.getItems().removeAll(panier);
				panier.removeAll(panier);
			
				try {
					Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+"C:\\Users\\MABSON\\eclipse-workspace\\ESSAI\\commande\\"+code_commande+".pdf");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		}
	   else
		{
			Montant_Affichage.setText("VEUILLEZ RENSEIGNER TOUS LES CHAMPS SVP");
		}
		
		
		
	}
	
	@FXML
	private void Suppression_Article_Panier()
	{
		int indexligne=commande_table.getSelectionModel().getSelectedIndex();
		commande_table.getItems().remove(indexligne);
	System.out.println(indexligne);
		
		if(indexligne<=0) {
			setTotal_paye(getTotal_paye()-panier.get(indexligne+1).getMontant_ttc());
		}else
		{
			setTotal_paye(getTotal_paye()-panier.get(indexligne-1).getMontant_ttc());
		}
	   
		Montant_Affichage.setText("MONTANT: "+getTotal_paye());
		
	}
	
	@FXML
	private void Suppression_toute_commande()
	{
		commande_table.getItems().removeAll(panier);
		panier.removeAll(panier);
	}
	
	
	
	
	
	
	
	public void initialize(URL location, ResourceBundle resources) {
		combobox.getItems().add("ESPECE");
		combobox.getItems().add("CHEQUE");
		combobox.getItems().add("CARTE");
		String[] client=new String[listeclient.size()];
		int compteur=0;
		for(int i=0;i<client.length;i++)
		{
			int t=i+1;
			String mot="CL"+t;
			client[i]=listeclient.get(mot).getCode()+" "+"|"+listeclient.get(mot).getNom()+" "+listeclient.get(mot).getPrenom()+" "+listeclient.get(mot).getTel_fixe();
		}
		
		TextFields.bindAutoCompletion(Code_Client, client);
		
		String[] article=new String[liste.size()];
		for(Entry<String, Article> article3:liste.entrySet())
		{
			article[compteur]=article3.getValue().getCode()+" "+"|"+"  "+article3.getValue().getDesignation()+"       "+article3.getValue().getCode_catogorie();
			compteur+=1;
		}
	TextFields.bindAutoCompletion(Code1, article).getCompletionTarget();

   
 /*  
   code_article1.setOnMouseReleased(new EventHandler<MouseEvent>() 
   {
	public void handle(MouseEvent me) {
		String mot_code=code_article1.getText();
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
    		  code_article1.setText(listeD.get(mot_designation).getCode());
    		  Code_Categorie1.setText(listeD.get(mot_designation).getCode_catogorie());
    	  }
      }
   });*/
		
	}
	
	
	
	
	

}
