package projet.control.metier;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;

public class Mode_Reglements {
	
	protected String type;
	protected int code;
	private HashMap<String, Mode_Reglements> liste_Mode_Reglement=new HashMap<>();
	private Connection connection=Connection_Base.getconnection();
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public Mode_Reglements(String type, int code) {
		
		this.type = type;
		this.code = code;
	}
	public Mode_Reglements(String type) {
		
		this.type = type;
	}
	public Mode_Reglements() {
			
	}
	public Mode_Reglements(int code)
	{
		this.code=code;
	}
	
	public HashMap<String, Mode_Reglements> listeMode_Reglement()
	{
		try
		{
			Statement stat=connection.createStatement();
			ResultSet res=stat.executeQuery("select * from mode_reglements");
			while(res.next())
			{
				code=res.getInt("code");
				type=res.getString("type");
				
				liste_Mode_Reglement.put(type,new Mode_Reglements(type,code) );
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return liste_Mode_Reglement;
	}
	
	public Mode_Reglements rechercher_mode_client(String type)
	{   
		Mode_Reglements reglement=null;
	
		try {
			Statement state=connection.createStatement();
			ResultSet res=state.executeQuery("select code,type from mode_reglements where type='"+type+"'");
			while(res.next())
			{
				int code=res.getInt("code");
				String type1=res.getString("type");
				reglement=new Mode_Reglements(type1, code);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reglement;
	}
	

}
