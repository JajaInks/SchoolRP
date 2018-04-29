package entite;

import java.util.Date;

public class Inscriptions {
	int idInscription;
	int idEtudiant;
	
	int idGroupe;
	int idSession;
	Date dateInscription;
	public Inscriptions(int idInscription, int idEtudiant, int idGroupe, int idSession,
			Date dateInscription) {
		super();
		this.idInscription = idInscription;
		this.idEtudiant = idEtudiant;
		
		this.idGroupe = idGroupe;
		this.idSession = idSession;
		this.dateInscription = dateInscription;
	}
	
	public Inscriptions() {
		super();
	}

	public int getIdInscription() {
		return idInscription;
	}
	public void setIdInscription(int idInscription) {
		this.idInscription = idInscription;
	}
	public int getIdEtudiant() {
		return idEtudiant;
	}
	public void setIdEtudiant(int idEtudiant) {
		this.idEtudiant = idEtudiant;
	}
	public int getIdGroupe() {
		return idGroupe;
	}
	public void setIdGroupe(int idGroupe) {
		this.idGroupe = idGroupe;
	}
	public int getIdSession() {
		return idSession;
	}
	public void setIdSession(int idSession) {
		this.idSession = idSession;
	}
	public Date getDateInscription() {
		return dateInscription;
	}
	public void setDateInscription(Date dateInscription) {
		this.dateInscription = dateInscription;
	}
	
	
}
