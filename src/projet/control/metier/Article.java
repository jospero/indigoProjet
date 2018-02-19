package projet.control.metier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.swing.JOptionPane;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Article {
	private StringProperty code=new SimpleStringProperty();
	private StringProperty code_catogorie=new SimpleStringProperty();;
	private StringProperty designation=new SimpleStringProperty();;
	private IntegerProperty quantite=new SimpleIntegerProperty();
	private DoubleProperty prix_unitaire = new SimpleDoubleProperty();
	private DoubleProperty Montan_ttc=new SimpleDoubleProperty();
	private Date date;
	private static Connection conn=Connection_Base.getconnection();
	private ArrayList<Article> lesEnreg=new ArrayList<Article>();
	private static ArrayList<String>listeDesignation=new ArrayList<>();
	private HashMap<String, Article> listeArticle=new HashMap<>();
	private HashMap<String, Article> listeArticleD=new HashMap<>();
	
	public Article(String code,String code_categorie,String designation,int quantite,double prix_unitaire,Date date)
    {
		this.code=new SimpleStringProperty(code);
		this.code_catogorie=new SimpleStringProperty(code_categorie);
		this.designation=new SimpleStringProperty(designation);
		this.quantite=new SimpleIntegerProperty(quantite);
		this.prix_unitaire=new SimpleDoubleProperty(prix_unitaire);
		this.Montan_ttc=new SimpleDoubleProperty();
		this.date=date;
		
	}
	public Article(String code,String code_categorie,String designation,int quantite,double prix_unitaire,double Montan_ttc,Date date)
	{
		this.code=new SimpleStringProperty(code);
		this.code_catogorie=new SimpleStringProperty(code_categorie);
		this.designation=new SimpleStringProperty(designation);
		this.quantite=new SimpleIntegerProperty(quantite);
		this.prix_unitaire=new SimpleDoubleProperty(prix_unitaire);
		this.Montan_ttc=new SimpleDoubleProperty(Montan_ttc);
		this.date=date;
	}
	
	public Article()
	{
		lireRecupCRUD();
	}
	
	public Article(String code)
	{
		this.code=new SimpleStringProperty(code);
	}
	
	public String getCode() {
		return this.code.get();
	}
	
	public void setCode(String code) {
		this.code.set(code);
	}
	
	public StringProperty codeProperty()
	{
		return code;
	}
	
	
	
	public String getCode_catogorie() {
		return this.code_catogorie.get();
	}
	public void setCode_catogorie(String code_catogorie) {
		this.code_catogorie.set(code_catogorie);
	}
	
	public StringProperty code_categorieProperty()
	{
		return code_catogorie;
	}
	
	
	public String getDesignation() {
		return this.designation.get();
	}
	public void setDesignation(String designation) {
		this.designation.set(designation);;
	}
	
	public StringProperty designationProperty()
	{
		return designation;
	}
	
	
	public int getQuantite() {
		return this.quantite.get();
	}
	public void setQuantite(int quantite) {
		this.quantite.set(quantite);
	}
	
	public IntegerProperty quantiteProperty()
	{
		return quantite;
	}
	
	
	public double getPrix_unitaire() {
		return this.prix_unitaire.get();
	}
	public void setPrix_unitaire(double prix_unitaire) {
		this.prix_unitaire.set(prix_unitaire);
	}
	public DoubleProperty prix_unitaire()
	{
		return prix_unitaire;
	}
	
	public double getMontant_ttc()
	{
	 return	this.Montan_ttc.get();
	}
	public void setMontant_ttc(double Montant_ttc )
	{
		this.Montan_ttc.set(Montant_ttc);
	}
	public DoubleProperty Montant_ttc()
	{
		return Montan_ttc;
	}
	
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
	public ArrayList<Article> getLesEnreg() {
		return lesEnreg;
	}
	public void setLesEnreg(ArrayList<Article> lesEnreg) {
		this.lesEnreg = lesEnreg;
	}
	
	
	
	public HashMap<String, Article> lireRecupCRUD()
	{
		try {
			Statement state=conn.createStatement();
			ResultSet rs=state.executeQuery("select * from articles");
			while(rs.next())
			{
				String code=rs.getString("code");
				String code_categorie=rs.getString("code_categorie");
				String designation=rs.getString("designation");
				int quantite=rs.getInt("quantite");
				double prix_unitaire=rs.getDouble("prix_unitaire");
				Date date_creation=rs.getDate("date");
				listeArticle.put(code, new Article(code,code_categorie,designation,quantite,prix_unitaire,date_creation));
				lesEnreg.add(new Article(code,code_categorie,designation,quantite,prix_unitaire,date_creation));
				
			}
		} catch (Exception e) {
		JOptionPane.showMessageDialog(null, "Probleme rencontre : "+e.getMessage(),"Resultat",JOptionPane.ERROR_MESSAGE);
		}
		return listeArticle;
	}
	
	public HashMap<String, Article> listeDe()
	{
		try {
			Statement state=conn.createStatement();
			ResultSet rs=state.executeQuery("select * from articles");
			while(rs.next())
			{
				String code=rs.getString("code");
				String code_categorie=rs.getString("code_categorie");
				String designation=rs.getString("designation");
				int quantite=rs.getInt("quantite");
				double prix_unitaire=rs.getDouble("prix_unitaire");
				Date date_creation=rs.getDate("date");
				
				listeArticleD.put(designation, new Article(code,code_categorie,designation,quantite,prix_unitaire,date_creation));
				
				
			}
		} catch (Exception e) {
		JOptionPane.showMessageDialog(null, "Probleme rencontre : "+e.getMessage(),"Resultat",JOptionPane.ERROR_MESSAGE);
		}
		return listeArticleD;
	}
	
	
	public ArrayList<String> liste()
	{
		try {
			Statement state=conn.createStatement();
			ResultSet rs=state.executeQuery("select * from articles");
			while(rs.next())
			{
				String code=rs.getString("code");
				String code_categorie=rs.getString("code_categorie");
				String designation=rs.getString("designation");
				int quantite=rs.getInt("quantite");
				double prix_unitaire=rs.getDouble("prix_unitaire");
				Date date_creation=rs.getDate("date");
				
				listeDesignation.add(designation);
				
			}
		} catch (Exception e) {
		JOptionPane.showMessageDialog(null, "Probleme rencontre : "+e.getMessage(),"Resultat",JOptionPane.ERROR_MESSAGE);
		}
		return listeDesignation;
	}
	
	
	public boolean creerCRUD(String vcode,String vreference,String vdesignation,int vquantite,double vpu,String vdate)
	{
		boolean bcreation=false;
		String requete=null;
		try {
			requete="insert into articles() values(?,?,?,?,?,now())";
			PreparedStatement prepare=conn.prepareStatement(requete);
			prepare.setString(1, vcode);
			prepare.setString(2, vreference);
			prepare.setString(3, vdesignation);
			prepare.setInt(4, vquantite);
			prepare.setDouble(5, vpu);
			prepare.executeUpdate();
			bcreation=true;
					
		} catch (Exception e) {
		  JOptionPane.showMessageDialog(null, "Ajout dans la bd non effectué :"
				                        +e.getMessage(),"Probleme rencontre",JOptionPane.ERROR_MESSAGE);
		}
		
		return bcreation;
	}
	
	
	
	public void modifierCRUD(String vcode,String vreference,String vdesignation,int vquantite,double vpu)
	{
		boolean bmodification=true;
		String requete=null;
		try {
			requete="UPDATE articles set "
					
					+"code_categorie = '" +vreference + "',"
					+"designation= '" + vdesignation +"',"
					+"quantite = '" + vquantite +"',"
					+"prix_unitaire = '" + vpu +"'"
					+"where code = '" +vcode+ "'";
			
			Statement state=conn.createStatement();
			 state.executeUpdate(requete);
	
			bmodification=true;
		} catch (Exception e) {
			bmodification=false;
			JOptionPane.showMessageDialog(null,"Modification dans la base de donné non effectué: "+e.getMessage(),"Probleme rencontre" ,JOptionPane.ERROR_MESSAGE);
		}
		
		
	}
	
	
	
	
	public boolean supprimerCRUD(String vcode)

	{
		boolean bsuppression =true;
		String requete=null;
		try {
			requete="delete from articles where code=?";
			PreparedStatement prepare=conn.prepareStatement(requete);
			prepare.setString(1, vcode);
			
			int nbenregsup=prepare.executeUpdate();
			
			if(nbenregsup==0)
			{
				JOptionPane.showMessageDialog( null, "Aucune suppression effectué dans la bd","probleme rencontré",JOptionPane.ERROR_MESSAGE);
			}
		} catch (Exception e) {
			bsuppression=false;
			JOptionPane.showMessageDialog(null, "Aucune suppression effecué dans la bd","Probleme rencontre",JOptionPane.ERROR_MESSAGE);
		}
		
		return bsuppression;
	}
	
	public ArrayList<Article> chercherCRUD(String recherche)
	{
		String requete="select * from articles where code like '%"+recherche+"%' or code_categorie like '"+recherche+"' or designation like '"+recherche+"'";
	
		
		try {
			Statement state =conn.createStatement();
			ResultSet rs=state.executeQuery(requete);
			while(rs.next())
			{
				String code=rs.getString("code");
				String code_categorie=rs.getString("code_categorie");
				String designation=rs.getString("designation");
				int quantite=rs.getInt("quantite");
				double prix_unitaire=rs.getDouble("prix_unitaire");
				Date date_creation=rs.getDate("date");
				
				lesEnreg.add(new Article(code,code_categorie,designation,quantite,prix_unitaire,date_creation));
				
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Probleme rencontré: " +e.getMessage(),"Resultat",JOptionPane.ERROR_MESSAGE);
			
		}
		return lesEnreg;
	}
	
	
	
	
	
	public Article chercher_UN_Article(String recherche)
	{
		String requete="select * from articles where code ='"+recherche+"'";
		
		
		Article article = null;
		try {
			Statement state =conn.createStatement();
			ResultSet rs=state.executeQuery(requete);
			while(rs.next())
			{
				String code=rs.getString("code");
				String code_categorie=rs.getString("code_categorie");
				String designation=rs.getString("designation");
				int quantite=rs.getInt("quantite");
				double prix_unitaire=rs.getDouble("prix_unitaire");
				Date date_creation=rs.getDate("date");
				
				 article=new Article(code,code_categorie,designation,quantite,prix_unitaire,date_creation);
				
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Probleme rencontré: " +e.getMessage(),"Resultat",JOptionPane.ERROR_MESSAGE);
			
		}
		return article;
	}
	

}