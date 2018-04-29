package entite;

public class Utilisateur {
	private int id;
	private String nom;
	private String prenom;
	protected String level;
	
	final public static String admin = "ADMINISTRATEUR";
	final public static String ens = "ENSEIGNANT";
	final public static String etu = "ELEVE";
	final public static String sec = "SECRETAIRE";

	public Utilisateur(int i, String n, String p) {
		this.id = i;
		this.nom = n;
		this.prenom = p;
	}
	
	public Utilisateur() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}
	
	public String toString() {
		return this.level+ " id :" + this.nom;
	}
}
