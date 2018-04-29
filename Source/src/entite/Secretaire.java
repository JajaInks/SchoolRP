package entite;

public class Secretaire extends Utilisateur{

	public Secretaire(int i, String n, String p) {
		super(i, n, p);
		this.level = sec;
	}
	public Secretaire() {
		super();
		this.level = sec;
	}
}
