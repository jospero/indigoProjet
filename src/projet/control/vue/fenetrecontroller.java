package projet.control.vue;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import projet.control.Main;

public class fenetrecontroller {
	
	Main main;

	public fenetrecontroller() {
		// TODO Auto-generated constructor stub
	}
	
	@FXML
	private void handleAffiche()
	{

		try {
			
			FXMLLoader loader=new FXMLLoader();
			loader.setLocation(Main.class.getResource("vue/client.fxml"));
			AnchorPane client=(AnchorPane) loader.load();
			Stage contenneur =new Stage();
			
			 contenneur.initModality(Modality.WINDOW_MODAL);
	      
			Scene scene=new Scene(client);
			contenneur.setScene(scene);
			
			//clientcontroller controler=loader.getController();
			//controler.setContenneur(contenneur);
			
			
			contenneur.show();
		} catch (Exception e) {
		e.printStackTrace();
		}
	}
	
	
	public void setMain(Main main) {
		this.main = main;
	}

	@FXML
	private void handleArticle()
	{

		try {
			
			FXMLLoader loader=new FXMLLoader();
			loader.setLocation(Main.class.getResource("vue/article.fxml"));
			AnchorPane article=(AnchorPane) loader.load();
			Stage contenneur =new Stage();
			
			 contenneur.initModality(Modality.WINDOW_MODAL);
	      
			Scene scene=new Scene(article);
			contenneur.setScene(scene);
			
			
			contenneur.show();
		} catch (Exception e) {
		e.printStackTrace();
		}
	
	}
	
	
	@FXML
	private void statistique()
	{

		try {
			
			FXMLLoader loader=new FXMLLoader();
			loader.setLocation(Main.class.getResource("vue/statistique.fxml"));
			AnchorPane statistique=(AnchorPane) loader.load();
			Stage contenneur =new Stage();
			
			 contenneur.initModality(Modality.WINDOW_MODAL);
	      
			Scene scene=new Scene(statistique);
			contenneur.setScene(scene);
			
			
			contenneur.show();
		} catch (Exception e) {
		e.printStackTrace();
		}
	}
	
	@FXML
	private void handleCategorie()
	{

		try {
			
			FXMLLoader loader=new FXMLLoader();
			loader.setLocation(Main.class.getResource("vue/categorie.fxml"));
			AnchorPane categorie=(AnchorPane) loader.load();
			Stage contenneur =new Stage();
			
			 contenneur.initModality(Modality.WINDOW_MODAL);
	      
			Scene scene=new Scene(categorie);
			contenneur.setScene(scene);
			
			
			contenneur.show();
		} catch (Exception e) {
		e.printStackTrace();
		}
	}
	
	
	@FXML
	private void handleCommande()
	{

		try {
			
			FXMLLoader loader=new FXMLLoader();
			loader.setLocation(Main.class.getResource("vue/commande.fxml"));
			AnchorPane commande=(AnchorPane) loader.load();
			Stage contenneur =new Stage();
			
			 contenneur.initModality(Modality.WINDOW_MODAL);
	      
			Scene scene=new Scene(commande);
			contenneur.setScene(scene);
			
			
			contenneur.show();
		} catch (Exception e) {
		e.printStackTrace();
		}
	}
	
	@FXML
	public void modif_client()
	{
try {
			
			FXMLLoader loader=new FXMLLoader();
			loader.setLocation(Main.class.getResource("vue/modification_client.fxml"));
			AnchorPane commande=(AnchorPane) loader.load();
		
			Stage contenneur =new Stage();
			
			 contenneur.initModality(Modality.WINDOW_MODAL);
	      
			Scene scene=new Scene(commande);
			contenneur.setScene(scene);
			
			
			contenneur.show();
		} catch (Exception e) {
		e.printStackTrace();
		}
		
	}
	


	
	
}
