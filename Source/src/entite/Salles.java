package entite;

public class Salles {
	int idSalle;
	String libel;
	int capacitesalle;
	
	public int getIdSalle() {
		return idSalle;
	}
	public void setIdSalle(int idSalle) {
		this.idSalle = idSalle;
	}
	public String getLibel() {
		return libel;
	}
	public void setLibel(String libel) {
		this.libel = libel;
	}
	public int getCapacitesalle() {
		return capacitesalle;
	}
	public void setCapacitesalle(int capacitesalle) {
		this.capacitesalle = capacitesalle;
	}
	
	public String toString() {
		return  libel;
	}
	
}
