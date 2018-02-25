package service;

public class Article 
{
	
	//les differents parametres de la classe Article de notre service web
private String code;
private String designation;
private double prix;

// generation ou construction de getter et de setter 
public String getCode() {
	return code;
}
public void setCode(String code) {
	this.code = code;
}
public String getDesignation() {
	return designation;
}
public void setDesignation(String designation) {
	this.designation = designation;
}
public double getPrix() {
	return prix;
}
public void setPrix(double prix) {
	this.prix = prix;
}

//creaction de construction



public Article(String code,String designation,double prix)
{
	this.code=code;
	this.designation=designation;
	this.prix=prix;
}





}
