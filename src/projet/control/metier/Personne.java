package projet.control.metier;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public abstract class Personne {

	StringProperty code;
	StringProperty nom;
	StringProperty prenom;
	
	
	public String getCode() {
		return this.code.get();
	}
	public void setCode(String code) {
		this.code.set(code);;
	}
	
	public StringProperty codeProperty() {
		 return code;
		 }
	public String getNom() {
		return this.nom.get();
	}
	public void setNom(String nom) {
		this.nom.set(nom);
	}
	
	public StringProperty nomProperty() {
		 return nom;
		 }
	
	public String getPrenom() {
		return this.prenom.get();
	}
	public void setPrenom(String prenom) {
		this.prenom.set(prenom);;
	}
	
	public StringProperty prenomProperty() {
		 return prenom;
		 }
	
	public Personne(String vCode,String vNom,String vPrenom) {
		code=new SimpleStringProperty(vCode);
		nom=new SimpleStringProperty(vNom);
		prenom=new SimpleStringProperty(vPrenom);
	}
	
	public Personne(String vCode)
	{
		code=new SimpleStringProperty(vCode);
	}
	
	public Personne()
	{
		
	}
	
	

}
