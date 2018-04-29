package entite;

public class Fonctions {
	int idFonction;
	String libelFonction;
	public Fonctions(int idFonction, String libelFonction) {
		super();
		this.idFonction = idFonction;
		this.libelFonction = libelFonction;
	}
	public Fonctions() {
		super();
	}
	public int getIdFonction() {
		return idFonction;
	}
	public void setIdFonction(int idFonction) {
		this.idFonction = idFonction;
	}
	public String getLibelFonction() {
		return libelFonction;
	}
	public void setLibelFonction(String libelFonction) {
		this.libelFonction = libelFonction;
	}
	
}
