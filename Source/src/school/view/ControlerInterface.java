package school.view;

import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;

import entite.Etudiants;
import entite.Secretaire;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import school.MainApp;


public class ControlerInterface implements Initializable  {
	
	@FXML private Button btnCo;
	@FXML private Button btnInscription;
	@FXML private ComboBox boxLevel;
	@FXML private Label warningLabel;
	@FXML private Label switchRegis;
	@FXML private Label warningLabelInscription;
	@FXML private Pane paneInscription;
	@FXML private Pane paneCo;
	@FXML private PasswordField txtPassword;
	@FXML private TextField txtUsername;
	@FXML private PasswordField inPassword;
	@FXML private PasswordField inCheckPassword;
	@FXML private TextField inSurname;
	@FXML private TextField inName;
	@FXML private Pane paneOut;
	@FXML private Text outText;
	@FXML private Button btnVal;
	
	
	private MainApp mainApp;
	
	public ControlerInterface() {
			
	}
	
    @FXML public void initialize(URL location, ResourceBundle resources){
    	//
    	boxLevel.setItems( FXCollections.observableArrayList(
    			"SECRETAIRE",
    			"ADMINISTRATEUR",
    			"ELEVE",
    			"ENSEIGNANT"
        ));
    	
    	//
    }
    
   
	//
    
    
    public void handleCo(ActionEvent event) {
    	String user = txtUsername.getText();
    	String password = txtPassword.getText();
    	String level = boxLevel.getValue().toString();
        String lvlSelect = (String) boxLevel.getValue();
        warningLabel.setStyle("#ff0303");
        if(!(lvlSelect != null)) {
        	warningLabel.setText("Veuillez séléctionnez votre niveau d'habilitation.");
        }
        else {
        	boolean test = mainApp.getFct().tryConnection(user,password,level);
        	if(!test) {
        		warningLabel.setText("Veuillez vérifier vos identifiants.");
        	}
        	else {
        		if(level == "ELEVE") {
        			Etudiants e = mainApp.getFct().selectEtudiant(user);
        			mainApp.showEtudiant(e.getNom(),e.getPrenom(), e.getId());
        		}
        		if(level == "SECRETAIRE") {
        			Secretaire s = mainApp.getFct().selectSecretaire(user);
        			mainApp.showSecretaire(s.getNom(), s.getPrenom(), s.getId());
        		}
        		if(level == "ADMINISTRATEUR") {
        			mainApp.showAdmin();
        		}
        	}
        }
    }
    
   
    public void handleSwicth(MouseEvent event) {
    	System.out.println("");
    	paneInscription.setVisible(true);
    	paneCo.setVisible(false);
    }	
    public void handleRegister(ActionEvent event) {
    	//
    	if(inPassword.getText() == inCheckPassword.getText()) {
	    	String id = mainApp.getFct().addEtudiant(inName.getText(),inSurname.getText(),inPassword.getText());
	    	paneInscription.setVisible(false);
	    	outText.setText("Votre insciption est terminé. Veuillez notez le numero suivant: " + id 
	    			+ ". Ce numero est votre numeros d'étudiant et votre identifiant de connection. Gardez le.");
	    	paneOut.setVisible(true);
    	}
    	else {
    		warningLabelInscription.setText("Verifier les passwords ");
    	}
    	//
    }
    
    public void outinfo() {
    	paneOut.setVisible(false);
    	paneCo.setVisible(true);	
    }
    //
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
   
    
}
