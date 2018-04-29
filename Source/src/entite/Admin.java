package entite;

public class Admin extends Utilisateur {
	
	
	public Admin(int i, String n, String p) {
		super(i, n, p);
		this.level = admin;
	}
	public Admin() {
		super();
		this.level = admin;
	}
	
	

}
