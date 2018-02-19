package projet.control.vue;

import java.util.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import projet.control.entite.ModeleClient;
import projet.control.metier.Client;

public class modification_clientController  {
	
	 //ObservableList<Client> ClientData=FXCollections.observableArrayList();
	
	
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
	private String code;
	
	@FXML
	private TextField nom;
	
	@FXML
	private TextField prenom;
	
	@FXML
	private TextField adresse;
	
	@FXML
	private TextField code_postal;
	
	@FXML
	private TextField telephone;
	
	@FXML
	private TextField email;
	
	@FXML
	private TextField ville;
	
	@FXML
	private TextArea remarque;
	
	
	
	private ModeleClient leModeleClients=new ModeleClient();
	
	
	public modification_clientController()
	{
		
	}
	

	
	
	
	@FXML
	private void tableau_client()
	{
		 ObservableList<Client> ClientData=FXCollections.observableArrayList();
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
	
	@FXML
	private void modification()
	{
		new Client().modifierCRUD(code, nom.getText(), prenom.getText(), 0,"2008-02-01", adresse.getText(), code_postal.getText(), ville.getText(), telephone.getText(), null, email.getText(), remarque.getText());
		
		int indexligne=clientTable.getSelectionModel().getSelectedIndex();
		 Client client1=new Client(code, nom.getText(), prenom.getText(),false,new Date(), adresse.getText(), "code_postal", ville.getText(), telephone.getText(), "vmobilis", email.getText(), remarque.getText());
		 clientTable.getItems().set(indexligne, client1);
			
	}
	
	@FXML
	private void supprimer_client()
	{
		int indexligne=clientTable.getSelectionModel().getSelectedIndex();
		clientTable.getItems().remove(indexligne);
		new Client().supprimerCRUD(code);
		
	}
	
	public void iniCLient(Client cliente)
	{
		if(cliente !=null) {
			code=cliente.getCode();
			nom.setText(cliente.getNom());
			prenom.setText(cliente.getPrenom());
			adresse.setText(cliente.getAdresse());
			code_postal.setText(cliente.getCode_postal());
			telephone.setText(cliente.getTel_fixe());
			email.setText(cliente.getEmail());
			ville.setText(cliente.getVille());
			
		}else
		{
			
			nom.setText("");
			prenom.setText("");
			adresse.setText("");
			code_postal.setText("");
			telephone.setText("");
			email.setText("");
			ville.setText("");
		}
		
	}

	
	

	
	

}
