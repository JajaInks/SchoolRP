package entite;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Sessions {
	int idSession;
	String libelSession;
	Calendar dateDebutSession;
	Calendar dateFinSession;
	String salle;
	int idGroupe;
	
	public int getIdGroupe() {
		return idGroupe;
	}

	public void setIdGroupe(int idGroupe) {
		this.idGroupe = idGroupe;
	}

	public String getSalle() {
		return salle;
	}

	public void setSalle(String salle) {
		this.salle = salle;
	}

	public Sessions(int idSession, int idAnnee, String libelSession, Calendar dateDebutSession, Calendar dateFinSession) {
		super();
		this.idSession = idSession;
		this.libelSession = libelSession;
		this.dateDebutSession = dateDebutSession;
		this.dateFinSession = dateFinSession;
	}
	
	public Sessions() {
		super();
	}

	public int getIdSession() {
		return idSession;
	}
	public void setIdSession(int idSession) {
		this.idSession = idSession;
	}
	
	public String getLibelSession() {
		return libelSession;
	}
	public void setLibelSession(String libelSession) {
		this.libelSession = libelSession;
	}
	public Calendar getDateDebutSession() {
		return dateDebutSession;
	}
	public void setDateDebutSession(Calendar dateDebutSession) {
		this.dateDebutSession = dateDebutSession;
	}
	public Calendar getDateFinSession() {
		return dateFinSession;
	}
	public void setDateFinSession(Calendar dateFinSession) {
		
		this.dateFinSession = dateFinSession;
	}
	public String toString() {
		return "id:" + this.idSession +" Nom:"+this.libelSession;
	}
	
	
	
}
