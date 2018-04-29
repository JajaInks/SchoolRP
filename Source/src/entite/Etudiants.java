package entite;

public class Etudiants extends Utilisateur{
	
	
	public Etudiants(int i, String n, String p) {
		super(i, n, p);
		this.level = etu;
	}
	public Etudiants() {
		super();
		this.level = etu;
	}

}
