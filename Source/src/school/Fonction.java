package school;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import entite.Admin;
import entite.Enseignant;
import entite.Etudiants;
import entite.Groupes;
import entite.Salles;
import entite.Secretaire;
import entite.Sessions;
import entite.Utilisateur;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.util.Callback;


public class Fonction {
	
	
	static Connection con;
	
	
	//Connection
	final String co = "SELECT * FROM authentification WHERE user=? AND password=? AND level =?;";
	final String insertAuthentification = "INSERT INTO authentification (user, password, level) VALUES ( ?, ?, ?);";
	final String deleteAuthentification = "DELETE FROM authentification WHERE user = ? AND level = ?";
	//Utilisateur
	//etudiant
	final String selectEtudiant ="SELECT * FROM etudiant WHERE idetudiant=?;";
	final String insertionEtudiant ="INSERT INTO etudiant (nometudiant,prenometudiant) VALUES ( ?, ?);";
	final String deleteEtudiant = "DELETE FROM etudiant WHERE idetudiant= ?";
	//secretaire
	final String selectSecretaire ="SELECT * FROM secretaire WHERE idSecretaire=?;";
	final String insertionSecretaire ="INSERT INTO secretaire (nomSecretaire,prenomsecretaire) VALUES ( ?, ?);";
	final String deleteSecretaire = "DELETE FROM secretaire WHERE idSecretaire= ?;";
	//enseignant
	final String insertionEnseignant="INSERT INTO Enseignant (nomEnseignant,prenomEnseignant) VALUES ( ?, ?);";
	final String deleteEnseignant = "DELETE FROM enseignant WHERE idenseignant= ?;";
	//admin
	final String insertionAdmin = "INSERT INTO administrateur (nomadmin,prenom) VALUES (?,?);";
	final String deleteAdmin = "DELETE FROM administrateur WHERE idadmin = ?;";
	
	final String allE = "SELECT * FROM etudiant;";
	final String allS = "SELECT * FROM secretaire;";
	final String allEs = "SELECT * FROM enseignant;";
	final String allA = "SELECT * FROM administrateur;";
	
	//entites
	//sessions
	final String selectSession = "SELECT * FROM session WHERE datedebutsession= ? AND groupe_idGroupe= (SELECT groupe_idgroupe FROM groupe_has_inscription WHERE inscription_etudiant_idetudiant=?)";
	final String selectSessionName = "SELECT * FROM session WHERE nomsessions LIKE ? ;";
	final String deleteSession = "DELETE FROM session WHERE idsession=?;";
	final String ModifSession = "UPDATE session SET salle_idsalle=?,datedebutsession=?,datefinsession=?,nomsessions=? WHERE idsession=?";
	final String Sessions = "SELECT * FROM session;";
	final String addSessions = "INSERT INTO session (idsession, groupe_idGroupe, salle_idsalle, nomsessions, datedebutsession, datefinsession) VALUES (NULL, ?, ?, ?, ?, ?);";
	//Salles
	final String selectAllSalle = "SELECT * FROM Salle;";
	final String selectSalleById = "SELECT * FROM Salle WHERE idSalle LIKE ?;";
	
	//Groupe
	final String selectAllNameGroupe = "SELECT * FROM groupe;";
	final String selectGroupeById = "SELECT * FROM groupe WHERE idGroupe = ?;";
	final String deleteGroupe = "DELETE FROM groupe WHERE idGroupe = ?;";
	final String addGroupes = "INSERT INTO Groupe (idGroupe, matiere_idmatiere, nomgroupe) VALUES ( NULL, ?,?);";
	final String updateGroupe = "UPDATE groupe SET matiere_idmatiere=? AND nomgroupe=?;";
	
	public Fonction() {
		this.connection();
	}
	/**
	* Fonction de création de la connection a notre base de donnéees.
	*/
	public static Connection connection() {
		if (con == null) {
            MysqlDataSource data = new MysqlDataSource();
            //  URL local
            data.setURL("jdbc:mysql://localhost:3306/projetschoolv1");
            data.setPort(3306);
            data.setDatabaseName("projetschoolv1");
            data.setUser("root");
            data.setPassword("");
            
            
            try {
                con = data.getConnection();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return con;
	}
	
	/**
	 * 
	 * @param userName
	 * @param password
	 * @param level
	 * @return boolean -> Etat de Connection 
	 */
	
	public boolean tryConnection(String userName, String password, String level){
		
	    try {
	       PreparedStatement st = con.prepareStatement(co);
	       st.setString(1, userName);
	       st.setString(2, password);
	       st.setString(3,level);
	       
           ResultSet rs = st.executeQuery();
          
	       if ( rs.next() ) {
	    	   return true;
	       }
	       else {
	    	
	    	   return false;
	       }
	    } catch (SQLException ex) {
	        Logger.getLogger(Fonction.class.getName()).log(Level.SEVERE, null, ex);
	    }
	    return false;
	} 
	
	/**
	 * Permet l'inscription d'un nouveau etudiant.
	 * Crée un Etudiant ce qui génere une clef ( id_etudiant) qui sert comme "user" pour anthentifiaction
	 * et ajoute cette authentification dans la base. 
	 * @param nometudiant
	 * @param prenom
	 * @param password
	 * @return
	 */
	
	public String addEtudiant(String nometudiant, String prenom,String password){
		PreparedStatement st = null;
	    try {
	       st = con.prepareStatement(insertionEtudiant,st.RETURN_GENERATED_KEYS);
	       st.setString(1,nometudiant);
	       st.setString(2,prenom);
	       st.executeUpdate();
           ResultSet rs = st.getGeneratedKeys();
           if(rs.next()) {
	           int id = rs.getInt(1);
	           PreparedStatement st2 = con.prepareStatement(insertAuthentification);
	           st2.setInt(1, id);
	           st2.setString(2, password);
	           st2.setString(3, "ELEVE");
	           st2.executeUpdate();
	           return ""+id;
           }
	    } catch (SQLException ex) {
	        Logger.getLogger(Fonction.class.getName()).log(Level.SEVERE, null, ex);
	    }
	    finally {
            try {
                st.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
	    return "fail";
	} 
	
	
	public String addEnseignant(String nometudiant, String prenom,String password){
		PreparedStatement st = null;
	    try {
	       st = con.prepareStatement(insertionEnseignant,st.RETURN_GENERATED_KEYS);
	       st.setString(1,nometudiant);
	       st.setString(2,prenom);
	       st.executeUpdate();
           ResultSet rs = st.getGeneratedKeys();
           if(rs.next()) {
	           int id = rs.getInt(1);
	           PreparedStatement st2 = con.prepareStatement(insertAuthentification);
	           st2.setInt(1, id);
	           st2.setString(2, password);
	           st2.setString(3, "ENSEIGNANT");
	           st2.executeUpdate();
	           return ""+id;
           }
	    } catch (SQLException ex) {
	        Logger.getLogger(Fonction.class.getName()).log(Level.SEVERE, null, ex);
	    }
	    finally {
            try {
                st.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
	    return "fail";
	} 
	public String addSecretaire(String nometudiant, String prenom,String password){
		PreparedStatement st = null;
	    try {
	       st = con.prepareStatement(insertionSecretaire,st.RETURN_GENERATED_KEYS);
	       st.setString(1,nometudiant);
	       st.setString(2,prenom);
	       st.executeUpdate();
           ResultSet rs = st.getGeneratedKeys();
           if(rs.next()) {
	           int id = rs.getInt(1);
	           PreparedStatement st2 = con.prepareStatement(insertAuthentification);
	           st2.setInt(1, id);
	           st2.setString(2, password);
	           st2.setString(3, "SECRETAIRE");
	           st2.executeUpdate();
	           return ""+id;
           }
	    } catch (SQLException ex) {
	        Logger.getLogger(Fonction.class.getName()).log(Level.SEVERE, null, ex);
	    }
	    finally {
            try {
                st.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
	    return "fail";
	} 
	
	public String addAdmin(String nometudiant, String prenom,String password){
		PreparedStatement st = null;
	    try {
	       st = con.prepareStatement(insertionAdmin,st.RETURN_GENERATED_KEYS);
	       st.setString(1,nometudiant);
	       st.setString(2,prenom);
	       st.executeUpdate();
           ResultSet rs = st.getGeneratedKeys();
           if(rs.next()) {
	           int id = rs.getInt(1);
	           PreparedStatement st2 = con.prepareStatement(insertAuthentification);
	           st2.setInt(1, id);
	           st2.setString(2, password);
	           st2.setString(3, "ADMINISTRATEUR");
	           st2.executeUpdate();
	           return ""+id;
           }
	    } catch (SQLException ex) {
	        Logger.getLogger(Fonction.class.getName()).log(Level.SEVERE, null, ex);
	    }
	    finally {
            try {
                st.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
	    return "fail";
	} 
	
	
	
	
	
	
	
	
	public Etudiants selectEtudiant(String id){
		PreparedStatement st = null;
		
	    try {
	       st = con.prepareStatement(selectEtudiant);
	       st.setString(1,id);
	       
           ResultSet rs = st.executeQuery();
           
           if(rs.next()) {
        	   Etudiants e = new Etudiants();
        	   e.setId(rs.getInt("idetudiant"));
        	   e.setNom(rs.getString("nometudiant"));
        	   e.setPrenom(rs.getString("prenometudiant"));
        	  
	          return e;
           }
           
	    } catch (SQLException ex) {
	        Logger.getLogger(Fonction.class.getName()).log(Level.SEVERE, null, ex);
	    }
	    
	    return new Etudiants();
	} 
	
	/**
	 * doit recuperer les cours du jours sélectionnez dans le calendrier
	 * @param IdEtudiant
	 * @param Date
	 * 
	 * 1) select les idgroupes de l'etudiant ( avec la relation groupe_has_etudiant ) 
	 * 2) select toutes les session correspondantes.
	 * 
	 */
	public ArrayList<Sessions> selectSessionDeE(int idE, Calendar d) {
		
		PreparedStatement st = null;
		ArrayList<Sessions> c = new ArrayList<Sessions>();
	    try {
	    	
	    	st = con.prepareStatement(selectSession);
	    	String date = d.get(d.YEAR) +"-"+ d.get(d.MONTH)+"-"+ d.get(d.DAY_OF_MONTH);
	    	
	    	st.setDate(1,  java.sql.Date.valueOf(date));
	    	st.setInt(2,idE);
	    	ResultSet rs = st.executeQuery();
	      
	    	while(rs.next()) {
	    		
	    		Sessions e = new Sessions();
        	    e.setIdSession(rs.getInt("idsession"));
        	    
        	    e.setLibelSession(rs.getString("nomsessions"));
        	    
        	    e.setIdGroupe(rs.getInt("groupe_idGroupe"));

        	    
        	    Calendar h =Calendar.getInstance();
        	    LocalDate j = rs.getDate("datedebutsession").toLocalDate();
        	    //
        	    h.set(h.DAY_OF_MONTH, j.getDayOfMonth());
        	    h.set(h.MONTH, j.getMonthValue());
        	    h.set(h.YEAR, j.getYear());
        	    //
        	   
        	    
        	    e.setDateDebutSession(h);
        	    j = rs.getDate("datefinsession").toLocalDate();
        	    h.set(h.DAY_OF_MONTH, j.getDayOfMonth());
        	    h.set(h.MONTH, j.getMonthValue());
        	    h.set(h.YEAR, j.getYear());
        	   
        	    e.setDateFinSession(h);
        	    
        	    e.setSalle(rs.getString("salle_idsalle"));
        	    c.add(e);
            }
           
	    } catch (SQLException ex) {
	        Logger.getLogger(Fonction.class.getName()).log(Level.SEVERE, null, ex);
	    }
	    return c;
	}
	
	/**
	 * Permet de recuperer les informations de la personne ce connectant.
	 * @param id
	 * @return Secretaire
	 */
	
	public Secretaire selectSecretaire(String id){
		PreparedStatement st = null;
	    try {
	       st = con.prepareStatement(selectSecretaire);
	       st.setString(1,id);
           ResultSet rs = st.executeQuery();
           if(rs.next()) {
        	   Secretaire e = new Secretaire();
        	   e.setId(rs.getInt("idSecretaire"));
        	   e.setNom(rs.getString("nomsecretaire"));
        	   e.setPrenom(rs.getString("prenomsecretaire"));
        	   return e;
           }
	    } catch (SQLException ex) {
	        Logger.getLogger(Fonction.class.getName()).log(Level.SEVERE, null, ex);
	    }
	    return new Secretaire();
	} 
	
	/**
	 * Suppression 
	 * @param id
	 * @return
	 */
	public Secretaire suppSessions(String id){
		PreparedStatement st = null;
		
	    try {
	       st = con.prepareStatement(deleteSession);
	       st.setString(1,id);
	       st.executeUpdate();
           
	    } catch (SQLException ex) {
	        Logger.getLogger(Fonction.class.getName()).log(Level.SEVERE, null, ex);
	    }
	    
	    return new Secretaire();
	} 
	
	
	public ArrayList<Sessions> selectSessionName( String name) {
		
		PreparedStatement st = null;
		ArrayList<Sessions> c = new ArrayList<Sessions>();
	    try {
	    	
	    	st = con.prepareStatement(selectSessionName);
	    	st.setString(1, "%"+name+"%");
	    	
	    	ResultSet rs = st.executeQuery();
	      
	    	while(rs.next()) {
	    		
	    		Sessions e = new Sessions();
        	    e.setIdSession(rs.getInt("idsession"));
        	    
        	    e.setIdGroupe(rs.getInt("groupe_idGroupe"));
        	    
        	    e.setLibelSession(rs.getString("nomsessions"));
        	    Calendar h =Calendar.getInstance();
        	    LocalDate j = rs.getDate("datedebutsession").toLocalDate();
        	    h.set(h.DAY_OF_MONTH, j.getDayOfMonth());
        	    h.set(h.MONTH, j.getMonthValue());
        	    h.set(h.YEAR, j.getYear());
        	    e.setDateDebutSession(h);
        	    j = rs.getDate("datefinsession").toLocalDate();
        	    h.set(h.DAY_OF_MONTH, j.getDayOfMonth());
        	    h.set(h.MONTH, j.getMonthValue());
        	    h.set(h.YEAR, j.getYear());
        	    e.setDateFinSession(h);
        	    e.setSalle(rs.getString("salle_idsalle"));
        	    
        	    c.add(e);
            }
           
	    } catch (SQLException ex) {
	        Logger.getLogger(Fonction.class.getName()).log(Level.SEVERE, null, ex);
	    }
	    return c;
	}
	
	
	public void updateSessions(int id, String name, int Salle,LocalDate d1,LocalDate d2){
		PreparedStatement st = null;
		
    	
	    try {
	    	String mydate =d1.getYear() +"-"+ d1.getMonthValue()+"-"+d1.getDayOfMonth()+"";
	    	
	    
	    	st = con.prepareStatement(ModifSession);
	    	st.setInt(1,Salle);
	       st.setDate(2, java.sql.Date.valueOf(mydate));
	       
	       mydate = d2.getYear() +"-"+ d2.getMonthValue()+"-"+ d2.getDayOfMonth();
	       st.setDate(3, java.sql.Date.valueOf(mydate));
	       st.setString(4, name);
	       st.setInt(5, id);
	       
	       System.out.print(st.toString());
	       
	       st.executeUpdate();
           
	    } catch (SQLException ex) {
	        Logger.getLogger(Fonction.class.getName()).log(Level.SEVERE, null, ex);
	    }
	} 
	
	
	public ArrayList<Sessions> allSessions(){
		PreparedStatement st = null;
		ArrayList<Sessions> c = new ArrayList<Sessions>();
	    try {
	    	
	    	st = con.prepareStatement(Sessions);
	    	
	    	ResultSet rs = st.executeQuery();
	      
	    	while(rs.next()) {
	    		
	    		Sessions e = new Sessions();
        	    e.setIdSession(rs.getInt("idsession"));
        	    e.setLibelSession(rs.getString("nomsessions"));
        	    
        	    e.setIdGroupe(rs.getInt("groupe_idGroupe"));
        	    
        	    Calendar h =Calendar.getInstance();
        	    LocalDate j = rs.getDate("datedebutsession").toLocalDate();
        	    h.set(h.DAY_OF_MONTH, j.getDayOfMonth());
        	    h.set(h.MONTH, j.getMonthValue());
        	    h.set(h.YEAR, j.getYear());
        	    e.setDateDebutSession(h);
        	    j = rs.getDate("datefinsession").toLocalDate();
        	    h.set(h.DAY_OF_MONTH, j.getDayOfMonth());
        	    h.set(h.MONTH, j.getMonthValue());
        	    h.set(h.YEAR, j.getYear());
        	    e.setDateFinSession(h);
        	    e.setSalle(rs.getString("salle_idsalle"));
        	    c.add(e);
            }
           
	    } catch (SQLException ex) {
	        Logger.getLogger(Fonction.class.getName()).log(Level.SEVERE, null, ex);
	    }
	    return c;
	}
	
	
	public ObservableList<Salles> selectAllNameSalle() {
		PreparedStatement st = null;
		ObservableList<Salles> c = FXCollections.observableArrayList() ;
	    try {
	    	st = con.prepareStatement(selectAllSalle);
	    	ResultSet rs = st.executeQuery();
	    	while(rs.next()) {
	    		Salles s = new Salles();
	    		s.setLibel(rs.getString("nomsalle"));
	    		s.setIdSalle(rs.getInt("idsalle"));
	    		s.setCapacitesalle(rs.getInt("capacitesalle"));
        	    c.add(s);
            }
	    } catch (SQLException ex) {
	        Logger.getLogger(Fonction.class.getName()).log(Level.SEVERE, null, ex);
	    }
	    
		return c;
	}
	
	public ObservableList<Groupes> selectAllNameGroupe() {
		PreparedStatement st = null;
		ObservableList<Groupes> c = FXCollections.observableArrayList() ;
	    try {
	    	st = con.prepareStatement(selectAllNameGroupe);
	    	ResultSet rs = st.executeQuery();
	    	while(rs.next()) {
	    		Groupes g = new Groupes();
	    		g.setIdGroupe(rs.getInt("idgroupe"));
	    		g.setIdMatiere(rs.getInt("matiere_idmatiere"));
	    		g.setLibelGroupe(rs.getString("nomgroupe"));
        	     c.add(g);
            }
	    } catch (SQLException ex) {
	        Logger.getLogger(Fonction.class.getName()).log(Level.SEVERE, null, ex);
	    }
	    
		return c;
	}
	
	public Groupes selectGroupeById(int id) {
		PreparedStatement st = null;
		Groupes c = new Groupes();
	    try {
	    	st = con.prepareStatement(selectGroupeById);
	    	st.setInt(1, id);
	    	ResultSet rs = st.executeQuery();
	    	while(rs.next()) {
	    		c.setIdGroupe(rs.getInt("idGroupe"));
	    		c.setIdMatiere(rs.getInt("matiere_idmatiere"));
        	    c.setLibelGroupe(rs.getString("nomgroupe"));
            }
	    } catch (SQLException ex) {
	        Logger.getLogger(Fonction.class.getName()).log(Level.SEVERE, null, ex);
	    }
		return c;
	}//
	public void addSessions(int idSalle, int idGroupe, String name, LocalDate value, LocalDate value2) {
		PreparedStatement st = null;
		Groupes c = new Groupes();
	    try {
	    	st = con.prepareStatement(addSessions);
	    	st.setInt(1, idGroupe);
	    	st.setInt(2, idSalle);
	    	st.setString(3, name);
	    	st.setDate(4, java.sql.Date.valueOf(value));
	    	st.setDate(5, java.sql.Date.valueOf(value2));
	    	System.out.println(st.toString());
	    	st.execute();
	    } catch (SQLException ex) {
	        Logger.getLogger(Fonction.class.getName()).log(Level.SEVERE, null, ex);
	    }
	}//
	
	public Salles selectSalleById(String n) {
		PreparedStatement st = null;
		Salles c = new Salles();
	    try {
	    	st = con.prepareStatement(selectSalleById);
	    	st.setString(1, n);
	    	ResultSet rs = st.executeQuery();
	    	while(rs.next()) {
	    		c.setLibel(rs.getString("nomsalle"));
	    		c.setIdSalle(rs.getInt("idsalle"));
	    		c.setCapacitesalle(rs.getInt("capacitesalle"));
            }
	    } catch (SQLException ex) {
	        Logger.getLogger(Fonction.class.getName()).log(Level.SEVERE, null, ex);
	    }
		return c;
	}
	public void deleteGroupe(Groupes g) {
		PreparedStatement st = null;
	    try {
	    	st = con.prepareStatement(deleteGroupe);
	    	st.setInt(1, g.getIdGroupe());
	    	 st.executeUpdate();
	    	
	    } catch (SQLException ex) {
	        Logger.getLogger(Fonction.class.getName()).log(Level.SEVERE, null, ex);
	    }
	}
	public void addGroupe(Groupes g) {
		PreparedStatement st = null;
	    try {
	    	st = con.prepareStatement(addGroupes);
	    	st.setInt(1, g.getIdMatiere());
	    	st.setString(2, g.getLibelGroupe());
	    	st.executeUpdate();
	    	
	    } catch (SQLException ex) {
	        Logger.getLogger(Fonction.class.getName()).log(Level.SEVERE, null, ex);
	    }
	}
	public void updateGroupe(Groupes g) {
		PreparedStatement st = null;
	    try {
	    	st = con.prepareStatement(updateGroupe);
	    	st.setInt(1, g.getIdMatiere());
	    	st.setString(2, g.getLibelGroupe());
	    	st.executeUpdate();
	    	
	    } catch (SQLException ex) {
	        Logger.getLogger(Fonction.class.getName()).log(Level.SEVERE, null, ex);
	    }
	}
	
	public ObservableList<Utilisateur> allUtilisateur() {
		PreparedStatement st = null;
		ObservableList<Utilisateur> c = FXCollections.observableArrayList();
		
	    try {
	    	st = con.prepareStatement(allE);
	    	ResultSet rs = st.executeQuery();
	    	while(rs.next()) {
	    		Etudiants g = new Etudiants();
	    		g.setId(rs.getInt("idetudiant"));
	    		g.setNom(rs.getString("nometudiant"));
	    		g.setPrenom(rs.getString("prenometudiant"));
        	    c.add(g);
	    	}	
	    	st = con.prepareStatement(allEs);
	    	rs = st.executeQuery();
	    	while(rs.next()) {
	    		Enseignant g = new Enseignant();
	    		g.setId(rs.getInt("idEnseignant"));
	    		g.setNom(rs.getString("nomEnseignant"));
	    		g.setPrenom(rs.getString("prenomEnseignant"));
        	    c.add(g);
        	}
	    	st = con.prepareStatement(allA);
	    	rs = st.executeQuery();
	    	while(rs.next()) {
	    		Admin g = new Admin();
	    		g.setId(rs.getInt("idAdmin"));
	    		g.setNom(rs.getString("nomAdmin"));
	    		g.setPrenom(rs.getString("prenom"));
        	    c.add(g);
	    	}
	    	st = con.prepareStatement(allS);
	    	rs = st.executeQuery();
	    	while(rs.next()) {
	    		Secretaire g = new Secretaire();
	    		g.setId(rs.getInt("idSecretaire"));
	    		g.setNom(rs.getString("nomSecretaire"));
	    		g.setPrenom(rs.getString("prenomSecretaire"));
        	    c.add(g);
	    	}
	    } catch (SQLException ex) {
	        Logger.getLogger(Fonction.class.getName()).log(Level.SEVERE, null, ex);
	    }   
		return c;
	}
	
	
	public void deleteUtilisateur(Utilisateur u) {
		PreparedStatement st = null;
	    try {
	    	st = con.prepareStatement(deleteAuthentification);
	    	st.setInt(1, u.getId());
	    	st.setString(2, u.getLevel());
	    	st.executeUpdate();
	    	if(u.getLevel().equals("ELEVE")) {
	    		st = con.prepareStatement(deleteEtudiant);
	    	}
			if(u.getLevel().equals("ENSEIGNANT")) {
				st = con.prepareStatement(deleteEnseignant);
			}
			if(u.getLevel().equals("ADMINISTRATEUR")) {
				st = con.prepareStatement(deleteAdmin);
			}
			if(u.getLevel().equals("SECRETAIRE")) {
				st = con.prepareStatement(deleteSecretaire);
			}	   	
			st.setInt(1, u.getId());
			System.out.println(st.toString());
			st.executeUpdate();
	    } catch (SQLException ex) {
	        Logger.getLogger(Fonction.class.getName()).log(Level.SEVERE, null, ex);
	    }
	}


}
