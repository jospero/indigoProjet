package projet.control.vue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class categoriecontroller {

	@FXML
	private TextField code;
	
	@FXML
	private TextField designation;
	
	private Stage primaryStage;
	
	
	
	
	public categoriecontroller() {
		
		// TODO Auto-generated constructor stub
	}

	public void setContenneur(Stage primaryStage) {
		this.primaryStage=primaryStage;
		
	}
	
/*	methode d'ajout de categorie
*/	@FXML
	private void Handlecategorie()
	{
		System.out.println("bonjour");
		try {
			Class.forName( "com.mysql.jdbc.Driver") ;
			System.out. println( "Driver O.K. ") ;
			String url = "jdbc:mysql://localhost:3306/indigo";
			String user = "root";
			String passwd = "jospin";
			Connection conn = DriverManager.getConnection( url, user,
			passwd) ;
			
			Statement statement=conn.createStatement();
			int i=statement.executeUpdate("insert into categories(code,designation) values ('"+code.getText()+"','"+designation.getText()+"')");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
@FXML
private void modifcation_categorie()
{
	System.out.println("bonjour");
	try {
		Class.forName( "com.mysql.jdbc.Driver") ;
		System.out. println( "Driver O.K. ") ;
		String url = "jdbc:mysql://localhost:3306/indigo";
		String user = "root";
		String passwd = "jospin";
		Connection conn = DriverManager.getConnection( url, user,
		passwd) ;
		
		Statement statement=conn.createStatement();
		int i=statement.executeUpdate("update categories set designation='"+designation.getText()+"' where code='"+code.getText()+"'");
		
	} catch (Exception e) {
		e.printStackTrace();
	}
}

@FXML
private void remise_a_zero_des_champs()
{
	code.setText("");
	designation.setText("");
}

}
