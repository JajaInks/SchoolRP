package school.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import school.MainApp;
import school.view.edt.ControlerCalendar2;

public class ControlerEtudiant implements Initializable {
	@FXML private Button btnEDT;
	@FXML private Button btnGroupe;
	@FXML private Button btnInscription;
	@FXML protected Pane PaneAcc;
	@FXML private Label InfoEtudiant;
	@FXML private AnchorPane paneBas;
	
	
	private String nom;
	private String prenom;
	private String num;
	
	private MainApp mainApp;
	
	public ControlerEtudiant() {
	}
	
	//
	public void init(MainApp mainAp, String name,String prename , String numero) {
		this.setMainApp(mainAp);
		this.setNom(name);
		this.setPrenom(prename);
		this.setNum(numero);
		InfoEtudiant.setText("Bienvenue " + nom + " "+ prenom + " sur votre espace d'acceuil School. Identifiant: " + num);
	}//
	
    @FXML public void initialize(URL location, ResourceBundle resources){
        
    	
    }
    
    @FXML 
    public void handleEdt() {
    	 FXMLLoader loader = new FXMLLoader();
    	 try {
    		loader.setLocation(MainApp.class.getResource("view/edt/InterfaceCalendar2.fxml"));
			AnchorPane nee = (AnchorPane) loader.load();
			paneBas.getChildren().add(nee);
			//PaneAcc.setVisible(false);
			
			ControlerCalendar2 controller = loader.getController();
            controller.setMainApp(this.mainApp);
            controller.setControlerParent(this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    
    public void goACC(AnchorPane nee) {
    	System.out.print("e");
    	PaneAcc.setVisible(true);
    	if(paneBas.getChildren().contains(nee)){
    		System.out.print("e");
    		nee.setVisible(false);
    	}
    }	
    
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
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

	public String getNum() {
		return num;
	}
	
	public Pane getPaneBas() {
		return paneBas;
	}
	
	public void setNum(String num) {
		this.num = num;
	}
    
}
