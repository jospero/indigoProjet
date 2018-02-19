package projet.control.vue;

import java.io.FileOutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.ResourceBundle;

import javax.xml.crypto.dsig.spec.HMACParameterSpec;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import projet.control.metier.Client;
import projet.control.metier.Connection_Base;

public class clientcontroller implements Initializable {
	
	@FXML
	private TableView<Client> clientTable;
	
	@FXML
	private TableColumn<Client, String> NOM;
	
	@FXML
	private TableColumn<Client, String> PRENOM;
	
	@FXML
	private TableColumn<Client, String> ADRESSE;
	
	@FXML
	private TableColumn<Client, String> TELEPHONE;
	
	@FXML
	private TableColumn<Client, String> EMAIL;
	
	@FXML
	private TableColumn<Client, String> VILLE;
	
	@FXML
	private TextField nom;
	
	@FXML
	private TextField prenom;
	
	@FXML
	private TextField code_postale;
	
	@FXML
	private TextField addresse;
	
	@FXML
	private TextField code_postal;
	
	@FXML
	private TextField ville;
	
	@FXML
	private TextField tel_fixe;
	
	@FXML
	private TextField email;
	
	@FXML
	private TextArea remarques;
	
	@FXML
	private TextField telephone;
	
	@FXML
	private String code;
	
	@FXML
	private TextField Recherche_Client;
	
	@FXML
	private  Label information;
	
	private Connection conn=Connection_Base.getconnection();
	private ArrayList<Client>liste=new Client().liste_Cliente();
	private ObservableList<Client> ClientData=FXCollections.observableArrayList(liste);
	private HashMap<String, Client> liste_Client=new Client().liste_Client();
	
	private int i=0;
	
	@FXML
	CheckBox carte_fidelite;
	
	@FXML
	Label informationlabel;
	
	
	private Stage primaryStage;
	
	public clientcontroller() {
		// TODO Auto-generated constructor stub
	}

	public void setContenneur(Stage primaryStage) {
		this.primaryStage=primaryStage;
		
	}
	
	

	@FXML
	private void handleConnection()
	{
		 boolean verification_individualiter=false;
		 String mot = "";
		 String fidelite="";
		 
		for(Entry<String, Client> article2:liste_Client.entrySet())
		{
			if(article2.getValue().getEmail().equals(email.getText()) || article2.getValue().getEmail().equals(tel_fixe.getText()))
			{
				verification_individualiter=true;
			}
		}
		
		boolean carte=true;
		if(!nom.getText().equals("") && !prenom.getText().equals("")  && !addresse.getText().equals("") && !code_postal.getText().equals("") && !ville.getText().equals("") && !tel_fixe.getText().equals("") && !email.getText().equals(""))
		{
			if(carte_fidelite.isSelected())	{carte=true;fidelite="OK";}else{	carte=false; fidelite="AUCUN";}
			setI(i+1);
			 mot="CL"+getI();
			while(liste_Client.containsKey(mot))
			{
				setI(i+1);
				 mot="CL"+getI();
			}
			  
			if(verification_individualiter==true )
			{
				information.setText("Cet addresse email est deja utilisé ");
				information.setVisible(true);
			}else
			{
				new Client().creerCRUD("CL"+getI(), nom.getText(), prenom.getText(),carte,addresse.getText() , code_postal.getText(), ville.getText(), tel_fixe.getText(), " ", email.getText(), remarques.getText());
				
				
				Document documentinscription=new Document();
				try 
				{
					PdfWriter.getInstance(documentinscription, new FileOutputStream("C:\\Users\\MABSON\\eclipse-workspace\\ESSAI\\inscription\\"+mot+".pdf"));
					documentinscription.open();
					documentinscription.add(new Paragraph("CODE: "+mot+""));
					documentinscription.add(new Paragraph("NOM: "+nom.getText().toUpperCase()+""));
					documentinscription.add(new Paragraph("PRENOM: "+prenom.getText().toUpperCase()+""));
					documentinscription.add(new Paragraph("Email: "+email.getText()+""));
					documentinscription.add(new Paragraph("CARTE FIDELITE: "+fidelite+""));
					documentinscription.add(new Paragraph("Cet document doit etre concerver avec soins,les informations sont confidentiel"));
					Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+"C:\\Users\\MABSON\\eclipse-workspace\\ESSAI\\inscription\\"+mot+".pdf");
				} catch (Exception e) {
					
				}
				documentinscription.close();
				
				nom.setText("");prenom.setText("");addresse.setText("");code_postal.setText("");email.setText("");ville.setText("");remarques.setText("");tel_fixe.setText("");carte_fidelite.setSelected(false);	
			
				information.setText("Enregistrement effectué avec succès ");
				information.setVisible(true);
			}
			
			
			        
			
		}else
		{
			information.setText("Veuillez remplir correctement tous les champs");
			information.setVisible(true);
		}
		
		
		
	}

	@FXML
	public void Annuler_Insertion_Client()
	{
		nom.setText("");
		prenom.setText("");
		addresse.setText("");
		code_postal.setText("");
		email.setText("");
		ville.setText("");
		remarques.setText("");
		tel_fixe.setText("");
		carte_fidelite.setSelected(false);
	}
	
	public void iniCLient(Client cliente)
	{
		
		if(cliente !=null) {
			code=cliente.getCode();
			nom.setText(cliente.getNom());
			prenom.setText(cliente.getPrenom());
			addresse.setText(cliente.getAdresse());
			code_postal.setText(cliente.getCode_postal());
			tel_fixe.setText(cliente.getTel_fixe());
			email.setText(cliente.getEmail());
			ville.setText(cliente.getVille());
			remarques.setText(cliente.getRemarques());
			carte_fidelite.setSelected(cliente.isCarte_fidelité());
			
			
		}else
		{
			
			nom.setText("");
			prenom.setText("");
			addresse.setText("");
			code_postal.setText("");
			tel_fixe.setText("");
			email.setText("");
			ville.setText("");
			carte_fidelite.setSelected(false);
		}
		
	}
	

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}
	
	
	@FXML
	private void tableau_client()
	{
		
		
		NOM.setCellValueFactory(cellData -> cellData.getValue().nomProperty());
		PRENOM.setCellValueFactory(cellData -> cellData.getValue().prenomProperty());
		ADRESSE.setCellValueFactory(cellData -> cellData.getValue().adresseProperty());
		TELEPHONE.setCellValueFactory(cellData -> cellData.getValue().Tel_fixeProperty());
		EMAIL.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
		VILLE.setCellValueFactory(cellData -> cellData.getValue().villeProperty());
		
		clientTable.setItems(ClientData);
		
		
		
		clientTable.getSelectionModel().selectedItemProperty().addListener(
				 (observable, oldValue, newValue) -> iniCLient(newValue));	
		
		liste=new Client().liste_Cliente();
	    ClientData=FXCollections.observableArrayList(liste);
	}
	
	
	
	@FXML
	private void modification()
	{
		int code1=0;
		if(carte_fidelite.isSelected())
		{
			code1=1;
		}else
		{
			code1=0;
		}
		
		new Client().modifierCRUD(code, nom.getText(), prenom.getText(), code1,"2008-02-01", addresse.getText(), code_postal.getText(), ville.getText(), tel_fixe.getText(), "", email.getText(), remarques.getText());
		
		int indexligne=clientTable.getSelectionModel().getSelectedIndex();
		 Client client1=new Client(code, nom.getText(), prenom.getText(),false,new Date(), addresse.getText(), "code_postal", ville.getText(), tel_fixe.getText(), "vmobilis", email.getText(), remarques.getText());
		 clientTable.getItems().set(indexligne, client1);
			
	}
	
	//methode de suppression de client
	
	@FXML
	private void supprimer_client()
	{
		int indexligne=clientTable.getSelectionModel().getSelectedIndex();
		clientTable.getItems().remove(indexligne);
		new Client().supprimerCRUD(code);
		
	}
	
	@FXML
	private void impression()
	{
		Document documentinscription=new Document();
		try 
		{
			PdfWriter.getInstance(documentinscription, new FileOutputStream("C:\\Users\\MABSON\\eclipse-workspace\\ESSAI\\inscription\\leo.pdf"));
			documentinscription.open();
			documentinscription.add(new Paragraph("bonjour"));
			
		} catch (Exception e) {
			
		}
		documentinscription.close();
	}

	@Override
	public void initialize(URL url, ResourceBundle resource) {
		information.setVisible(false);
		
	}
	


}
