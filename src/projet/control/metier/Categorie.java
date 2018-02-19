package projet.control.metier;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Categorie {
	
	private StringProperty code;
	private StringProperty designation;
	
	Connection connection=Connection_Base.getconnection();
	
	public Categorie(String code,String designaion)
	{
		this.code=new SimpleStringProperty(code);
		this.designation=new SimpleStringProperty(designaion);
	}
	
	public Categorie()
	{
		
	}
	
	public String getCode()
	{
		return this.code.get();
	}
	public void setCode(String code)
	{
		this.code.set(code);
	}
	public StringProperty CodeProperty()
	{
		return code;
	}
	
	public String getDesignation()
	{
		return this.designation.get();
	}
	public void setDesignation(String designation)
	{
		this.code.set(designation);
	}
	public StringProperty DesignationProperty()
	{
		return designation;
	}
	
	public void CreerCategorie(String code,String designation)
	{
		try {
			Statement state=connection.createStatement();
			int i=state.executeUpdate("insert into categories(code,designation) values ('"+code+"','"+designation+"')");
			
		} catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, "Ajout dans la BD non effectué :"+e.getMessage(),"Probleme rencontre ",JOptionPane.ERROR_MESSAGE);
			
		}
		
	}
	public void ModifierCategorie(String code,String designation)
	{
		try 
		{
			Statement state=connection.createStatement();
			int i=state.executeUpdate("update categories set code='"+code+"',designation='"+designation+"' where code='"+code+"'");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Modification dans la BD non effectue."+
					 e.getMessage(),"probleme rencontre ",JOptionPane.ERROR_MESSAGE);
		}
	}
	public void supprimerCategorie(String code)
	{
        String requete =null;
		boolean bsuppression=true;
		try {
            String requeteClient ="select count(*) as nblignes from categories where code like '"+code+"'";
			
			Statement state =connection.createStatement();
			ResultSet jeuEnreg=state.executeQuery(requeteClient);
			int nblignes=0;
			jeuEnreg.next();
			nblignes=jeuEnreg.getInt("nblignes");
			if(nblignes > 0)
			{
				bsuppression =false;
				JOptionPane.showMessageDialog(null, "Il existe des commandes pour ce client."+"suppression interdite","Resultat",JOptionPane.INFORMATION_MESSAGE);	
			}
			else {
				JOptionPane.showMessageDialog(null,"Aucune commande pour ce client ."+" suppression autorisé.","Resultat",JOptionPane.INFORMATION_MESSAGE);
			}	
		} catch (Exception e) {
			bsuppression =false;
			JOptionPane.showMessageDialog(null,"Aucune suppession effectué  dans la BD : " +e.getMessage(),
					"probleme rencontre",JOptionPane.ERROR_MESSAGE);
		}
		
		if(bsuppression==true)
		{
			try {
				requete="delete from categories where code='"+code+"'";
				Statement state =connection.createStatement();
				int nbEnregsup=state.executeUpdate(requete);
				
				if(nbEnregsup==0)
				{
					JOptionPane.showMessageDialog(null, "Aucun enregistrement correspondant"," Resultat",JOptionPane.ERROR_MESSAGE);
				}
			} catch (Exception e) {
				bsuppression =false;
				JOptionPane.showMessageDialog(null,"Aucune suppression dans la base de donnée");
			}
		}
		
	
	}
	
	public HashMap<String,Categorie> liste_Categorie()
	{
		HashMap<String,Categorie>liste=new HashMap<>();
		try {
			Statement state=connection.createStatement();
			ResultSet res=state.executeQuery("select * from categories");
			while(res.next())
			{
				String code1=res.getString("code");
				String designation1=res.getString("designation");
				liste.put(code1, new Categorie(code1, designation1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return liste;
	}
	
	public ArrayList<Categorie> liste_simple_Categorie()
	{
		ArrayList<Categorie>liste=new ArrayList<>();
		try {
			Statement state=connection.createStatement();
			ResultSet res=state.executeQuery("select * from categories");
			while(res.next())
			{
				String code1=res.getString("code");
				String designation1=res.getString("designation");
				liste.add(new Categorie(code1, designation1));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return liste;
	}

}
