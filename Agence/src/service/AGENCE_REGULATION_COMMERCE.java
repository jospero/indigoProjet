package service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.crypto.dsig.spec.HMACParameterSpec;

@WebService(serviceName="ARCCI")
public class AGENCE_REGULATION_COMMERCE 
{
	
	 public AGENCE_REGULATION_COMMERCE()
	 {
		System.out.println("BINVEnenue sur mon service web");
	}
	
	
	@WebMethod(operationName="PRIX_ARTICLE_PAR_CODE")
	public double getPrix_Code(@WebParam(name="CODE") String code)
	{
		double prix;
		try
		{
			Connection conn=Connect.getDatasource().getConnection();
			Statement state=conn.createStatement();
			ResultSet res=state.executeQuery("select prix_unitaire from article where code='"+code+"'");
			res.next();
			prix=res.getDouble("prix_unitaire");
			
		} catch (Exception e) {
			prix=0;
		}
		return prix;
	}
	
	@WebMethod(operationName="PRIX_ARTICLE_PAR_DESIGNATION")
	public double getPrix_Designation( @WebParam(name="CODE2") String designation)
	{
		double prix;
		try
		{
			Connection conn=Connect.getDatasource().getConnection();
			Statement state=conn.createStatement();
			ResultSet res=state.executeQuery("select prix_unitaire from article where code='"+designation+"'");
			res.next();
			prix=res.getDouble("prix_unitaire");
			
		} catch (Exception e) {
			prix=0;
		}
		return prix;
		
	}
	
	@WebMethod(operationName="TVA_PAYS")
	public int getTva_Pays(@WebParam(name="PAYS") String pays)
	{
		int tva;
		try
		{
			Connection conn=Connect.getDatasource().getConnection();
			Statement state=conn.createStatement();
			ResultSet res=state.executeQuery("select tva from tva_pays where pays='"+pays+"'");
			res.next();
			tva=res.getInt("tva");
			
		} catch (Exception e) {
			tva=0;
		}
		return tva;
		
	}
	
	@WebMethod(operationName="TVA_SIGLE_PAYS")
	public int getTva_Sigle(@WebParam(name="SIGLE_PAYS") String sigle_pays)
	{int tva;
	try
	{
		Connection conn=Connect.getDatasource().getConnection();
		Statement state=conn.createStatement();
		ResultSet res=state.executeQuery("select tva from tva_pays where sigle='"+sigle_pays+"'");
		res.next();
		tva=res.getInt("tva");
		
	} catch (Exception e) {
		tva=0;
	}
	return tva;
	
}
	
@WebMethod(operationName="liste_article")
public ArrayList<Article> lste_Article()
{
	ArrayList<Article> liste=new ArrayList<>();
	try 
	{
		Connection conn=Connect.getDatasource().getConnection();
		Statement state=conn.createStatement();
		ResultSet res=state.executeQuery("select * from article ");
		
		while(res.next())
		{
			String code=res.getString("code");
			String designation=res.getString("designation");
			double prix=res.getDouble("prix_unitaire");
			liste.add(new Article(code,designation,prix));
		}
		
	} catch (Exception e)
	{
		e.printStackTrace();
	}
	return liste;
}



}


