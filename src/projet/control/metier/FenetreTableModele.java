package projet.control.metier;

import javax.swing.JTable;

import projet.control.entite.ModeleClient;

public class FenetreTableModele {
	
	private ModeleClient lemodeleclient=new ModeleClient();
	
	JTable table=new JTable();
	private JTable getJTable() {
		if (table==null) {
			table=new JTable(lemodeleclient);
	}
	return table;
}
	public FenetreTableModele()
	{
		getJTable();
	}
}
