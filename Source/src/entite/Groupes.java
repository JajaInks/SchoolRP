package entite;

import java.util.Date;

public class Groupes {
	int idGroupe;
	int idMatiere;
	String libelGroupe;
	
	public int getIdGroupe() {
		return idGroupe;
	}
	public void setIdGroupe(int idGroupe) {
		this.idGroupe = idGroupe;
	}
	public int getIdMatiere() {
		return idMatiere;
	}
	public void setIdMatiere(int idMatiere) {
		this.idMatiere = idMatiere;
	}
	public String getLibelGroupe() {
		return libelGroupe;
	}
	public void setLibelGroupe(String libelGroupe) {
		this.libelGroupe = libelGroupe;
	}
	
	public String toString() {
		return libelGroupe;
	}
}
