package entite;

import java.util.ArrayList;

public class Matieres {
	int idMatiere;
	ArrayList<Groupes> idModules = new ArrayList<Groupes>(0);
	String libellematiere;
	String nommatiere;
	String loglibellematiere;

	public Matieres(int idMatiere, ArrayList<Groupes> idModules, String libellematiere, String nommatiere,
			String loglibellematiere) {
		super();
		this.idMatiere = idMatiere;
		this.idModules = idModules;
		this.libellematiere = libellematiere;
		this.nommatiere = nommatiere;
		this.loglibellematiere = loglibellematiere;
	}
	
	public Matieres() {
		super();
	}

	public int getIdMatiere() {
		return idMatiere;
	}
	public void setIdMatiere(int idMatiere) {
		this.idMatiere = idMatiere;
	}
	public ArrayList<Groupes> getIdModules() {
		return idModules;
	}
	public void setIdModules(ArrayList<Groupes> idModules) {
		this.idModules = idModules;
	}
	public String getLibellematiere() {
		return libellematiere;
	}
	public void setLibellematiere(String libellematiere) {
		this.libellematiere = libellematiere;
	}
	public String getNommatiere() {
		return nommatiere;
	}
	public void setNommatiere(String nommatiere) {
		this.nommatiere = nommatiere;
	}
	public String getLoglibellematiere() {
		return loglibellematiere;
	}
	public void setLoglibellematiere(String loglibellematiere) {
		this.loglibellematiere = loglibellematiere;
	}
}
