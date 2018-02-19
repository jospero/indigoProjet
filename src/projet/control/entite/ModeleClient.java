package projet.control.entite;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import projet.control.metier.Client;

public class ModeleClient extends AbstractTableModel {
  
	Client instanceclient=new Client();
	private ArrayList<Client> lesDonnees=instanceclient.getLesEnreg();
	private final String[] lesTitres= {"Code","Nom","Prenom","Carte Fidelite","Date de Creation"};
	
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return lesTitres.length;
	}

	public int getRowCount() {
		// TODO Auto-generated method stub
		return lesDonnees.size();
	}
	
	public String getColumnName(int columnindex)
	{
		return lesTitres[columnindex];
	}

	public Object getValueAt(int rowindex, int columnindex) {
		switch (columnindex) {
		case 0:
			return lesDonnees.get(rowindex).getCode();
		case 1:
			return lesDonnees.get(rowindex).getNom();
			
		case 2: 
			return lesDonnees.get(rowindex).getPrenom();
		case 3: 
			return lesDonnees.get(rowindex).getDate_creaction();
		default:
			return null;
		}
		
	}
	
	public void creerMOD(Client client)
	{
		lesDonnees.add(client);
	}
	
	public void supprimerclient(int rowindex)
	{
		lesDonnees.remove(rowindex);
	}
	
	public void modifieClient(int firstRow,int lastRow,Client leclient)
	{
		lesDonnees.set(firstRow, leclient);
	}
	

}
