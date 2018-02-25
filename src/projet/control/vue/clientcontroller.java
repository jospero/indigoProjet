package projet.control.vue;

import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.ResourceBundle;

import org.controlsfx.control.textfield.TextFields;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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
	
	

	//methode d'enregistrement du client
	@FXML
	private void handleConnection()
	{
		 boolean verification_individualiter=false;
		 String mot = "";
		 String fidelite="";
		 
		 
		for(Entry<String, Client> client2:liste_Client.entrySet())
		{
			System.out.println(client2.getValue().getEmail()+"  "+client2.getValue().getTel_fixe()+"\n");
			System.out.println(email.getText()+"  "+tel_fixe.getText());
			if(client2.getValue().getEmail().equals(email.getText()) || client2.getValue().getTel_fixe().equals(tel_fixe.getText()))
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
				information.setText("Cet addresse email ou cet numero est deja utilisé  ");
				information.setVisible(true);
			}else
			{
				new Client().creerCRUD("CL"+getI(), nom.getText(), prenom.getText(),carte,addresse.getText() , code_postal.getText(), ville.getText(), tel_fixe.getText(), " ", email.getText(), remarques.getText());
				
				
				
				nom.setText("");prenom.setText("");addresse.setText("");code_postal.setText("");email.setText("");ville.setText("");remarques.setText("");tel_fixe.setText("");carte_fidelite.setSelected(false);	
			
				information.setText("Enregistrement effectué avec succès ");
				information.setVisible(true);
				
			}
			
			
			        
			
		}else
		{
			information.setText("Veuillez remplir correctement tous les champs");
			information.setVisible(true);
		}
		liste_Client=new Client().liste_Client();
		
		initialisation();
		
	}
	public void initialisation()
	{
		String[] client=new String[liste_Client.size()];
		int compteur=0;
		
		for(Entry<String,Client>clienti:liste_Client.entrySet())
		{
			client[compteur]=clienti.getValue().getCode()+"     "+clienti.getValue().getNom()+"     "+clienti.getValue().getPrenom()+"     "+clienti.getValue().getTel_fixe();
			compteur+=1;
		}
		
		TextFields.bindAutoCompletion(Recherche_Client, client);
	}
	
	//methode de remise a niveau de champ de saisie

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
	
	
	

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}
	
	
	
	
	//methode de modification du client
	@FXML
	private void modification()
	{
		int code1=0;
		int indexligne=clientTable.getSelectionModel().getSelectedIndex();
		if(carte_fidelite.isSelected())
		{
			code1=1;
		}else
		{
			code1=0;
		}
		
		String  mot1=Recherche_Client.getText();
		System.out.println(mot1);
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
		//System.out.println(liste_Client.get(code_client).getCode());
		if(liste_Client.containsKey(code_client)||liste_Client.containsKey(code) )
		{
			new Client().modifierCRUD(liste_Client.get(code).getCode(), nom.getText(), prenom.getText(), code1,"2008-02-01", addresse.getText(), code_postal.getText(), ville.getText(), tel_fixe.getText(), "", email.getText(), remarques.getText());
			 Client client1=new Client(code, nom.getText(), prenom.getText(),false,new Date(), addresse.getText(), "code_postal", ville.getText(), tel_fixe.getText(), "vmobilis", email.getText(), remarques.getText());
			 clientTable.getItems().set(indexligne, client1);
			
			information.setText("Modification effectuée");
			information.setVisible(true);
		}
		else
		{
			information.setText("Modification impossible,client non reconnue.");
			information.setVisible(true);
		}
		
		
//new Client().modifierCRUD(code, nom.getText(), prenom.getText(), code1,"2008-02-01", addresse.getText(), code_postal.getText(), ville.getText(), tel_fixe.getText(), null, email.getText(), remarques.getText());
		
		//int indexligne=clientTable.getSelectionModel().getSelectedIndex();
		 /*Client client1=new Client(code, nom.getText(), prenom.getText(),false,new Date(), addresse.getText(), "code_postal", ville.getText(), tel_fixe.getText(), "vmobilis", email.getText(), remarques.getText());
		 clientTable.getItems().set(indexligne, client1);*/
		
			liste_Client=new Client().liste_Client();
		
		
			
	}
	
	//methode de suppression de client
	
	@FXML
	private void supprimer_client()
	{
		
		
		int indexligne=clientTable.getSelectionModel().getSelectedIndex();
		clientTable.getItems().remove(indexligne);
		
		
		String  mot1=Recherche_Client.getText();
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
		if(liste_Client.containsKey(code_client) ||liste_Client.containsKey(code) )
		{
			Alert alert=new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Confirmation de suppression");
			alert.setHeaderText("Suppression");
			alert.setContentText("Etes vous sûre de vouloir supprimer le client");
			Optional<javafx.scene.control.ButtonType> action=alert.showAndWait();
			if(action.get()==javafx.scene.control.ButtonType.OK)
			{
				boolean verif=new Client().supprimerCRUD(code);;
				if(verif==true)
				{
					information.setText("Suppression effectuée.");
					information.setVisible(true);
				}else
				{
					information.setText("Suppression impossible,probleme rencontré.");
					information.setVisible(true);
				}
				
			}
			
		}
		else
		{
			information.setText("Suppression impossible,client non reconnue.");
			information.setVisible(true);
		}
		
		liste_Client=new Client().liste_Client();
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

	
	
	
	@FXML
	private void tableau_client()
	{
		for (Client client : new Client().getLesEnreg()) {
			ClientData.add(client);
		}
		
		NOM.setCellValueFactory(cellData -> cellData.getValue().nomProperty());
		PRENOM.setCellValueFactory(cellData -> cellData.getValue().prenomProperty());
		ADRESSE.setCellValueFactory(cellData -> cellData.getValue().adresseProperty());
		TELEPHONE.setCellValueFactory(cellData -> cellData.getValue().Tel_fixeProperty());
		EMAIL.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
		VILLE.setCellValueFactory(cellData -> cellData.getValue().villeProperty());
		
		clientTable.setItems(ClientData);
		
		
		
		clientTable.getSelectionModel().selectedItemProperty().addListener(
				 (observable, oldValue, newValue) -> iniCLient(newValue));		
	}
	
	
	
	
	
	
	
	//methode de suppression de client
	
	@FXML
	private void supprimer_client1()
	{
		int indexligne=clientTable.getSelectionModel().getSelectedIndex();
		clientTable.getItems().remove(indexligne);
		new Client().supprimerCRUD(code);
		
	}

	
	
	
	

	

	//methode initialisation au demarrage
	@Override
	public void initialize(URL url, ResourceBundle resource) {
		
		
		information.setVisible(false);
		informationlabel.setVisible(false);
		
		String[] client=new String[liste_Client.size()];
		int compteur=0;
		
		for(Entry<String,Client>clienti:liste_Client.entrySet())
		{
			client[compteur]=clienti.getValue().getCode()+"     "+clienti.getValue().getNom()+"     "+clienti.getValue().getPrenom()+"     "+clienti.getValue().getTel_fixe();
			compteur+=1;
		}
		
		TextFields.bindAutoCompletion(Recherche_Client, client);
		
		Recherche_Client.setOnKeyPressed(new EventHandler<javafx.scene.input.KeyEvent>() {

			@Override
			public void handle(javafx.scene.input.KeyEvent keyev) {
				
				
				
				if(keyev.getCode().toString().equals("ENTER") && !Recherche_Client.getText().equals(""))
				{
					String mot1=Recherche_Client.getText();
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
					
					if(liste_Client.containsKey(code_client))
					{
						code=liste_Client.get(code_client).getCode();
						nom.setText(liste_Client.get(code_client).getNom());
						prenom.setText(liste_Client.get(code_client).getPrenom());
						addresse.setText(liste_Client.get(code_client).getAdresse());
						code_postal.setText(liste_Client.get(code_client).getCode_postal());
						tel_fixe.setText(liste_Client.get(code_client).getTel_fixe());
						email.setText(liste_Client.get(code_client).getEmail());
						ville.setText(liste_Client.get(code_client).getVille());
						remarques.setText(liste_Client.get(code_client).getRemarques());
						carte_fidelite.setSelected(liste_Client.get(code_client).isCarte_fidelité());
						informationlabel.setText("    "+liste_Client.get(code_client).getCode());
						informationlabel.setVisible(true);
					}
					else
					{
						information.setText("AUCUN RESULTAT");
						information.setVisible(true);
					}
				}
				
				if(!keyev.getCode().toString().equals("ENTER"))
				{
					information.setVisible(false);
				}
			}
		});
		
		
		
		for (Client client1 : new Client().getLesEnreg()) {
			ClientData.add(client1);
		}
		
		NOM.setCellValueFactory(cellData -> cellData.getValue().nomProperty());
		PRENOM.setCellValueFactory(cellData -> cellData.getValue().prenomProperty());
		ADRESSE.setCellValueFactory(cellData -> cellData.getValue().adresseProperty());
		TELEPHONE.setCellValueFactory(cellData -> cellData.getValue().Tel_fixeProperty());
		EMAIL.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
		VILLE.setCellValueFactory(cellData -> cellData.getValue().villeProperty());
		
		clientTable.setItems(ClientData);
		
		
		
		clientTable.getSelectionModel().selectedItemProperty().addListener(
				 (observable, oldValue, newValue) -> iniCLient(newValue));
		
	}
	


}
