package entite;

import java.util.ArrayList;

public class Enseignant extends Utilisateur{
	
	
	
	ArrayList<Matieres> listMatiere = new ArrayList<Matieres>(0);

	public Enseignant(int i, String n,String p) {
		super(i,n,p);
		this.level = ens;
	}
	
	public Enseignant(ArrayList<Matieres> listMatiere) {
		super();
		this.listMatiere = listMatiere;
		this.level = ens;
	}

	public Enseignant() {
		super();
		this.level = ens;
	}
	public ArrayList<Matieres> getListMatiere() {
		return listMatiere;
	}
	public void setListMatiere(ArrayList<Matieres> listMatiere) {
		this.listMatiere = listMatiere;
	}	

}
