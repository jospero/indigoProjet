package projet.control.metier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.swing.JOptionPane;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Commande {
	
	private StringProperty code;
	private Client code_client;
	private DoubleProperty total_ttc;
	private IntegerProperty codeModereglement;
	private Mode_Reglements mode_reglement;
	private Date date;
	private Connection conn=Connection_Base.getconnection();
	private ArrayList<Commande>lesEnreg=new ArrayList<Commande>();
	private HashMap<String, Commande>liste=new HashMap<>();
	
	public Commande(String code,Client code_client,double total_ttc,Mode_Reglements mode_reglement,Date date)
	{
		this.code=new SimpleStringProperty(code);
		this.code_client=code_client;
		this.total_ttc=new SimpleDoubleProperty(total_ttc);
		this.codeModereglement=codeModereglement;
		this.mode_reglement=mode_reglement;
		this.date=date;
	}
	
	public Commande()
	{
		lireRecupCRUD();
	}
	
	public Commande(String code)
	{
		
	}
	
	public String getCode() {
		return this.code.get();
	}

	public void setCode(String code) {
		this.code.set(code);
	}

	public Client getCode_client() {
		return code_client;
	}

	public void setCode_client(Client code_client) {
		this.code_client = code_client;
	}

	public double getTotal_ttc() {
		return this.total_ttc.get();
	}

	public void setTotal_ttc(double total_ttc) {
		this.total_ttc.set(total_ttc);
	}

	public int getCodeModereglement() {
		return this.codeModereglement.get();
	}

	public void setCodeModereglement(int codeModereglement) {
		this.codeModereglement.set(codeModereglement);
	}

	public Mode_Reglements getMode_reglement() {
		return mode_reglement;
	}

	public void setMode_reglement(Mode_Reglements mode_reglement) {
		this.mode_reglement = mode_reglement;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public ArrayList<Commande> getLesEnreg() {
		return lesEnreg;
	}

	public void setLesEnreg(ArrayList<Commande> lesEnreg) {
		this.lesEnreg = lesEnreg;
	}
	
	public void lireRecupCRUD()
	{
		try {
			Statement state=conn.createStatement();
			ResultSet rs=state.executeQuery("SELECT com.code,com.total_ttc,com.date,cli.nom,cli.prenom,mode.type from commandes as com,clients as cli,mode_reglements as mode where com.code_mode_reglements=mode.code and com.code_client=cli.code");
			while(rs.next())
			{
				String code=rs.getString("code");
				double total_ttc=rs.getDouble("total_ttc");
				Date date=rs.getDate("date");
				
				String nom=rs.getString("nom");
				String prenom=rs.getString("prenom");
				
				String type=rs.getString("type");
				
                lesEnreg.add(new Commande(code,new Client(nom,prenom),total_ttc, new Mode_Reglements(),date));
			}
		} catch (Exception e) {
			//JOptionPane.showMessageDialog(null, "Probleme rencontre","alerte",JOptionPane.ERROR_MESSAGE);
		e.printStackTrace();
		}
	}
	
	public boolean creerCRUD(String vcode,String vcode_client,double vtotalttc,int vcode_mode_reglement)
	{
		boolean bcreaction=false;
		String requete=null;
		try {
			requete="insert into commandes values(?,?,now(),?,?)";
			PreparedStatement prepare=conn.prepareStatement(requete);
			prepare.setString(1, vcode);
			prepare.setDouble(2, vtotalttc);
			prepare.setString(3, vcode_client);
			prepare.setInt(4,vcode_mode_reglement);
			prepare.executeUpdate();
			bcreaction=true;
		} catch (Exception e) {
			bcreaction=false;
			JOptionPane.showMessageDialog(null, "Ajout dans la BD non effectue" +e.getMessage(), "Probleme rencontre",JOptionPane.ERROR_MESSAGE);
		}
		
		return bcreaction;
	}
	
	public boolean modifierCRUD(String vcode,String vcode_client,double vtotalttc,int vcode_mode_reglement)
	{
		boolean bmodification=true;
		String requete=null;
		try {
			requete="update commandes set code_client=?,total_ttc=?,code_mode_reglements=? where code=?";
			PreparedStatement prepare=conn.prepareStatement(requete);
			prepare.setString(1, vcode_client);
			prepare.setDouble(2, vtotalttc);
			prepare.setInt(3, vcode_mode_reglement);
			prepare.setString(4, vcode);
			
			prepare.executeUpdate();
			bmodification=true;
		} catch (Exception e) {
			bmodification=false;
			JOptionPane.showMessageDialog(null, "Modification dans la BD non effectue "+ e.getMessage(),"Probleme rencontre",JOptionPane.ERROR_MESSAGE);
		}
		
		return bmodification;
	}
	
	public boolean supprimerCRUD(String vcode)
	{
		boolean bsupression=true;
		String requete=null;
		
		try {
			requete="delete commandes,lignes_commandes from commandes,lignes_commandes where code_commande=code and code=?";
			PreparedStatement prepare = conn.prepareStatement(requete);
			prepare.setString(1, vcode);
			int nbEnregsup = prepare.executeUpdate();
			
			if(nbEnregsup==0)
			{
				JOptionPane.showMessageDialog(null, "Aucune suppression effectue dans la BD ","Probleme rencontre",JOptionPane.ERROR_MESSAGE);
				
			}
			
		} catch (Exception e) {
			bsupression=false;
			JOptionPane.showMessageDialog(null, "Aucune suppression effectue dans la BD ","Probleme rencontre",JOptionPane.ERROR_MESSAGE);
			
		}
		
		return bsupression;
	}
	
	public ArrayList<Commande> chercherCRUD(String recherche)
	{
		String requete=" ";
		requete +="SELECT com.code,com.total_ttc,com.date,cli.nom,mode.type from commandes as com,clients as cli,mode_reglements as mode";
		requete+="where com.code_mode_reglement=mode.code and com.code_client=cli.code and (";
		requete+="com.code like '%"+recherche+"%'";
		requete+="or cli.nom like '%"+recherche+"%'";
		requete+="or cli.prenom like '%"+recherche+"%'";
		requete+="or com.total_ttc like '%"+ recherche+"%'";
		requete+="or mode.type like '%"+ recherche +"%')";
		
		try {
			Statement state=conn.createStatement();
			ResultSet rs=state.executeQuery(requete);
			while(rs.next())
			{
				String code=rs.getString("code");
				double total_ttc=rs.getDouble("total_ttc");
				Date date=rs.getDate("date");
				
				String nom=rs.getString("nom");
				String prenom=rs.getString("prenom");
				
				
				String type=rs.getString("type");
				
				lesEnreg.add(new Commande(code,new Client(nom,prenom),total_ttc,new Mode_Reglements(type),date));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lesEnreg;
	}
	
	public HashMap<String, Commande> listeCommande()
	{
		try {
			Statement state=conn.createStatement();
			ResultSet res=state.executeQuery("select * from commandes");
			while(res.next())
			{
				String code=res.getString("code");
				double total=res.getDouble("total_ttc");
				Date date=res.getDate("date");
				String code_client=res.getString("code_client");
				int code_reglements=res.getInt("code_mode_reglements");
				liste.put(code,new Commande(code, new Client(code_client), total, new Mode_Reglements(code_reglements), date));
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return liste;
		
	}

}
