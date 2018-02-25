package projet.control.metier;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.swing.JOptionPane;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Client extends Personne {
	
	private StringProperty ville;
	private StringProperty tel_fixe;
	private StringProperty mobilis;
	private StringProperty email;
	private StringProperty remarques;
	private static Connection conn=Connection_Base.getconnection();
	
	private ArrayList<Client> lesEnreg=new ArrayList<Client>();
	private HashMap<String, Client> listeClient=new HashMap<>();
	private ArrayList<String> listnumero=new ArrayList<String>();
	private ArrayList<Client>liste_client=new ArrayList<Client>();
	private BooleanProperty carte_fidelite;
	private Date date;
	private StringProperty adresse;
	private StringProperty code_postal;
	

	
	
	
	
	
	
	//les Constructeurs de la class client
	
	public Client(String vCode, String vNom, String vPrenom,boolean vCarte_fidelite,Date vDate_creaction,String vadresse,String vcode_postal,String ville,String vtel_fixe,String vmobilis,String vemail,String vremarques) {
		super(vCode, vNom, vPrenom);
		this.code=new SimpleStringProperty(vCode);
		this.nom=new SimpleStringProperty(vNom);
		this.prenom=new SimpleStringProperty(vPrenom);
		this.carte_fidelite=new SimpleBooleanProperty(vCarte_fidelite);
		this.date=vDate_creaction;
		this.adresse=new SimpleStringProperty(vadresse);
		this.code_postal=new SimpleStringProperty(vcode_postal);
		this.ville=new SimpleStringProperty(ville);
		this.tel_fixe=new SimpleStringProperty(vtel_fixe);
		this.mobilis=new SimpleStringProperty(vmobilis);
		this.email=new SimpleStringProperty(vemail);
		this.remarques=new SimpleStringProperty(vremarques);
		
	}
	
	
	public Client(String vCode) {
		super(vCode);
		this.code=new SimpleStringProperty(vCode);
	}
	
	public Client() {
		lireRecupCRUD(); 
	}

	public Client(String vNom,String vPrenom)
	{
		this.nom=new SimpleStringProperty(vNom);
		this.prenom=new SimpleStringProperty(vPrenom);
	}

	
	
	//les accesseurs de la class cleint (getter et setter)
	public String getAdresse() {
		return this.adresse.get();
	}

	public void setAdresse(String adresse) {
		this.adresse.set(adresse);
	}
	
	public StringProperty adresseProperty() {
		 return adresse;
		 }

	
	public String getCode_postal() {
		return this.code_postal.get();
	}

	public void setCode_postal(String code_postal) {
		this.code_postal.set(code_postal);
	}
	
	public StringProperty code_postalProperty() {
		 return code_postal;
		 }

	public String getVille() {
		return this.ville.get();
	}
	
	public void setVille(String ville) {
		this.ville.set(ville);;
	}
	
	public StringProperty villeProperty() {
		 return ville;
		 }

	public String getTel_fixe() {
		return this.tel_fixe.get();
	}

	public void setTel_fixe(String tel_fixe) {
		this.tel_fixe.set(tel_fixe);
	}
	
	public StringProperty Tel_fixeProperty() {
		 return tel_fixe;
		 }

	public String getMobilis() {
		return this.mobilis.get();
	}

	public void setMobilis(String mobilis) {
		this.mobilis.set(mobilis);
	}
	
	public StringProperty mobilisProperty() {
		 return mobilis;
		 }

	public String getEmail() {
		return this.email.get();
	}

	public void setEmail(String email) {
		this.email.set(email);
	}
	
	public StringProperty emailProperty() {
		 return email;
		 }

	public String getRemarques() {
		return this.remarques.get();
	}

	public void setRemarques(String remarques) {
		this.remarques.set(remarques);
	}
	
	public StringProperty remarquesProperty() {
		 return remarques;
		 }

	

	public boolean isCarte_fidelité() {
		return this.carte_fidelite.get();
	}

	public void setCarte_fidelité(boolean carte_fidelite) {
		this.carte_fidelite.set(carte_fidelite);
	}
	
	public BooleanProperty carte_fideliteProperty() {
		 return carte_fidelite;
		 }

	public Date getDate_creaction() {
		return date;
	}

	public void setDate_creaction(Date date) {
		this.date = date;
	}

	public ArrayList<Client> getLesEnreg() {
		return lesEnreg;
	}
	public void setLesEnreg(ArrayList<Client> lesEnreg) {
		this.lesEnreg = lesEnreg;
	}

	

	
	
	//methode renvoyant une arraylist de client
	public void lireRecupCRUD()
	{
		try {
			Statement state=conn.createStatement();
			ResultSet rs=state.executeQuery("select * from clients order by nom");
			while(rs.next())
			{
				String code=rs.getString("code");
				String nom=rs.getString("nom");
				String prenom=rs.getString("prenom");
				boolean carte_fidelite=rs.getBoolean("carte_fidele");
				Date date_creation=rs.getDate("date");
				String adresse=rs.getString("adresse");
				String code_postal=rs.getString("code_postal");
				String ville=rs.getString("ville");
				String tel_fixe=rs.getString("tel_fixe");
				String mobilis=rs.getString("mobilis");
				String email=rs.getString("email");
				String remarques=rs.getString("remarques");
				
				lesEnreg.add(new Client(code,nom,prenom,carte_fidelite,date_creation,adresse,code_postal,ville,tel_fixe,mobilis,email,remarques));
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	//methode renvoyant un hmap de client pour faciliter la recherche dans la couche vue
	public HashMap<String, Client> liste_Client()
	{
		try {
			Statement state=conn.createStatement();
			ResultSet rs=state.executeQuery("select * from clients order by nom");
			while(rs.next())
			{
				String code=rs.getString("code");
				String nom=rs.getString("nom");
				String prenom=rs.getString("prenom");
				boolean carte_fidelite=rs.getBoolean("carte_fidele");
				Date date_creation=rs.getDate("date");
				String adresse=rs.getString("adresse");
				String code_postal=rs.getString("code_postal");
				String ville=rs.getString("ville");
				String tel_fixe=rs.getString("tel_fixe");
				String mobilis=rs.getString("mobilis");
				String email=rs.getString("email");
				String remarques=rs.getString("remarques");
				
				listeClient.put(code,new Client(code,nom,prenom,carte_fidelite,date_creation,adresse,code_postal,ville,tel_fixe,mobilis,email,remarques));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	return listeClient;
	}
	
	//methode renvoyant une arraylist de client pour l'autcompletion dans la couche vue
	public ArrayList<Client> liste_Cliente()
	{
		try {
			Statement state=conn.createStatement();
			ResultSet rs=state.executeQuery("select * from clients order by nom");
			while(rs.next())
			{
				String code=rs.getString("code");
				String nom=rs.getString("nom");
				String prenom=rs.getString("prenom");
				boolean carte_fidelite=rs.getBoolean("carte_fidele");
				Date date_creation=rs.getDate("date");
				String adresse=rs.getString("adresse");
				String code_postal=rs.getString("code_postal");
				String ville=rs.getString("ville");
				String tel_fixe=rs.getString("tel_fixe");
				String mobilis=rs.getString("mobilis");
				String email=rs.getString("email");
				String remarques=rs.getString("remarques");
				
				//listeClient.put(code,new Client(code,nom,prenom,carte_fidelite,date_creation,adresse,code_postal,ville,tel_fixe,mobilis,email,remarques));
				liste_client.add(new Client(code,nom,prenom,carte_fidelite,date_creation,adresse,code_postal,ville,tel_fixe,mobilis,email,remarques));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	return liste_client;
	}
	
	public boolean creerCRUD(String vCode, String vNom, String vPrenom,boolean vCarte_fidelite,String vadresse,String vcode_postal,String ville,String vtel_fixe,String vmobilis,String vemail,String vremarques)
	{
		int carte_fidele;
		boolean bcreaction=false;
		if(vCarte_fidelite==false) {
			carte_fidele=0;
		}
		else carte_fidele=1;
		
		String requete=null;
		try {
			requete="insert into clients (code,nom,prenom,carte_fidele,date,adresse,code_postal,ville,tel_fixe,mobilis,email,remarques) values";
			requete+="('"+vCode+"','"+vNom+"','"+vPrenom+"','"+carte_fidele+"',now(),'"+vadresse+"','"+vcode_postal+"','"+ville+"','"+vtel_fixe+"','"+vmobilis+"','"+vemail+"','"+vremarques+"')";
			Statement state=conn.createStatement();
			state.executeUpdate(requete);
			bcreaction=true;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Ajout dans la BD non effectué :"+e.getMessage(),"Probleme rencontre ",JOptionPane.ERROR_MESSAGE);
			
		}
		return bcreaction;
	}
	
	public boolean modifierCRUD(String vcode,String vnom,String vprenom,int carte_fidele,String vdate,String vadresse,String vcode_postal,String ville,String vtel_fixe,String vmobilis,String vemail,String vremarques)
	{
		
		boolean bModification=true;
		String requete=null;
		try {
			requete="UPDATE clients set "
					+"nom = '" + vnom + "',"
					+"prenom = '" +vprenom + "',"
					+"carte_fidele= '" + carte_fidele +"',"
					+"date = '" + vdate +"',"
					+"adresse = '" + vadresse +"',"
					+"code_postal = '" + vcode_postal +"',"
					+"ville = '" + ville +"',"
					+"tel_fixe = '" + vtel_fixe +"',"
					+"mobilis = '" + vmobilis +"',"
					+"email = '" + vemail +"',"
					+"remarques = '" + vremarques +"'"
					+"where code = '" +vcode+ "'";
		             
			Statement state =conn.createStatement();
			state.executeUpdate(requete);
			state.close();
		} catch (Exception e) {
			 bModification=false;
			 JOptionPane.showMessageDialog(null,"Modification dans la BD non effectue."+
			 e.getMessage(),"probleme rencontre ",JOptionPane.ERROR_MESSAGE);
		}
		
		return bModification;
	}
	
	public boolean supprimerCRUD(String vcode)
	{
		boolean bsuppression=true;
		String requete =null;
		
		try {
            String requeteClient ="select count(*) as nblignes from commandes where code_client like '"+vcode+"'";
			
			Statement state =conn.createStatement();
			ResultSet jeuEnreg=state.executeQuery(requeteClient);
			int nblignes=0;
			jeuEnreg.next();
			nblignes=jeuEnreg.getInt("nblignes");
			if(nblignes > 0)
			{
				bsuppression =false;
				
			}
			
		} catch (Exception e) {
			bsuppression =false;
			
		}
		
		if(bsuppression==true)
		{
			try {
				conn.setAutoCommit(false);
				requete="delete from clients where code='"+vcode+"'";
				String requete1="delete from commandes where code_client='"+vcode+"'";
				Statement state =conn.createStatement();
				state.addBatch(requete1);
				state.addBatch(requete);
				state.executeBatch();
				
				
			} catch (Exception e) {
				bsuppression =false;
				
			}
		}
		
		return bsuppression;
	}
	
	public Client recherche_par_telephone(String numero)
	{
		Client client = null;
		try {
			Statement state=conn.createStatement();
			ResultSet rs=state.executeQuery("select * from clients where tel_fixe='"+numero+"' or mobilis='"+numero+"' ");
			while(rs.next())
			{
				String code=rs.getString("code");
				String nom=rs.getString("nom");
				String prenom=rs.getString("prenom");
				boolean carte_fidelite=rs.getBoolean("carte_fidele");
				Date date_creation=rs.getDate("date");
				String adresse=rs.getString("adresse");
				String code_postal=rs.getString("code_postal");
				String ville=rs.getString("ville");
				String tel_fixe=rs.getString("tel_fixe");
				String mobilis=rs.getString("mobilis");
				String email=rs.getString("email");
				String remarques=rs.getString("remarques");
				client=new Client(code,nom,prenom,carte_fidelite,date_creation,adresse,code_postal,ville,tel_fixe,mobilis,email,remarques);
				lesEnreg.add(new Client(code,nom,prenom,carte_fidelite,date_creation,adresse,code_postal,ville,tel_fixe,mobilis,email,remarques));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return client;	
		
	}
	
	public Client recherche_par_code(String code1)
	{
		Client client = null;
		try {
			Statement state=conn.createStatement();
			ResultSet rs=state.executeQuery("select * from clients where code='"+code1+"'");
			while(rs.next())
			{
				String code=rs.getString("code");
				String nom=rs.getString("nom");
				String prenom=rs.getString("prenom");
				boolean carte_fidelite=rs.getBoolean("carte_fidele");
				Date date_creation=rs.getDate("date");
				String adresse=rs.getString("adresse");
				String code_postal=rs.getString("code_postal");
				String ville=rs.getString("ville");
				String tel_fixe=rs.getString("tel_fixe");
				String mobilis=rs.getString("mobilis");
				String email=rs.getString("email");
				String remarques=rs.getString("remarques");
				client=new Client(code,nom,prenom,carte_fidelite,date_creation,adresse,code_postal,ville,tel_fixe,mobilis,email,remarques);
				lesEnreg.add(new Client(code,nom,prenom,carte_fidelite,date_creation,adresse,code_postal,ville,tel_fixe,mobilis,email,remarques));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return client;	
		
	}
	
	public ArrayList<String> numeroclient()
	{
		
		try {
			Statement state=conn.createStatement();
			ResultSet rs=state.executeQuery("select tel_fixe from clients ");
			while(rs.next())
			{
				String tele_fixe=rs.getString("tel_fixe");
				listnumero.add(tele_fixe);
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	return listnumero;
	}
	
	/*
	 * Simple méthode utilitaire permettant de faire la correspondance (le
	 * mapping) entre une ligne issue de la table des client (un
	 * ResultSet) et un bean Client.
	 */
	/*private static Client map( ResultSet resultSet ) throws SQLException {
	
      //Recuperation des donnees du resultSet
		String code = resultSet.getString( "code" );
		String nom = resultSet.getString( "nom" );
		String prenom = resultSet.getString( "prenom" );
		Long carte_fidelite = resultSet.getLong( "carte_fidelite" );
		Date  dateInscription = resultSet.getDate("date");
		String adresse = resultSet.getString( "adresse" );
		String code_postal = resultSet.getString( "code_postal" );
		String ville = resultSet.getString( "ville" );
		String tel_fixe = resultSet.getString( "tel_fixe" );
		String mobilis = resultSet.getString( "mobilis" );
		String email = resultSet.getString( "email" );
		String remarques = resultSet.getString( "remarques" );
        
        
		Client client = new Client();
		
		client.setCode(code);
		client.setNom(nom);
		client.setPrenom(prenom);
		client.setCarte_fidelite(carte_fidelite);
		client.setDateInscription(dateInscription);
		client.setAdresse(adresse);
		client.setCode_postal(code_postal);
		client.setVille(ville);
		client.setTel_fixe(tel_fixe);
		client.setMobilis(mobilis);
		client.setEmail(email);
		client.setRemarques(remarques);
		
	    
	    return client;
	}*/
}

