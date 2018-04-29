package school.view;

import java.net.URL;
import java.util.ResourceBundle;

import entite.Sessions;
import entite.Utilisateur;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import school.MainApp;

public class ControlerAdmin implements Initializable {

	@FXML private TableView<Utilisateur> UtilisateurView;
	@FXML private TableColumn<Utilisateur,String> colId;
	@FXML private TableColumn<Utilisateur,String> colNom;
	@FXML private TableColumn<Utilisateur,String> colPrenom;
	@FXML private TableColumn<Utilisateur,String> colLevel;
	@FXML private ComboBox<String> cbLevel;
	@FXML private TextField txtNom;
	@FXML private TextField txtPrenom;
	@FXML private TextField txtPassword;
	private MainApp mainApp;
	
	
	ObservableList<Utilisateur> UtilisateurData = FXCollections.observableArrayList();

	public void init(MainApp a) {
		this.mainApp = a;
		
		cbLevel.setItems( FXCollections.observableArrayList(
    			"SECRETAIRE",
    			"ADMINISTRATEUR",
    			"ELEVE",
    			"ENSEIGNANT"
        ));
    	
    	
		 update();
		
	}
	
	@FXML public void initialize(URL location, ResourceBundle resources){
        //
		colId.setCellValueFactory(cellData ->  new SimpleStringProperty(cellData.getValue().getId()+"") );
    	colNom.setCellValueFactory(cellData ->  new SimpleStringProperty(cellData.getValue().getNom()) );
    	colPrenom.setCellValueFactory(cellData ->  new SimpleStringProperty(cellData.getValue().getPrenom()) );
    	colLevel.setCellValueFactory(cellData ->  new SimpleStringProperty(cellData.getValue().getLevel()) );
		//
    	UtilisateurView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> MajInput(newValue));
    	
    }
	public void update() {
		//update tableview
    	UtilisateurView.getSelectionModel().clearSelection();
    	UtilisateurData.clear();
    	UtilisateurData.addAll(mainApp.getFct().allUtilisateur());
    	UtilisateurView.setItems(UtilisateurData);
	}
	
	
	public void MajInput(Utilisateur u ) {
		if( u!=null) {
			cbLevel.getSelectionModel().select(u.getLevel());
			txtNom.setText(u.getNom());
			txtPrenom.setText(u.getPrenom());
		}
	}
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
	public void handleAjouter() {
		Utilisateur u = new Utilisateur();
		u.setNom(txtNom.getText());
		u.setPrenom(txtPrenom.getText());
		if(cbLevel.getSelectionModel().getSelectedItem().equals("ELEVE")) {
			mainApp.getFct().addEtudiant(u.getNom(), u.getPrenom(),	txtPassword.getText());	
		}
		if(cbLevel.getSelectionModel().getSelectedItem().equals("SECRETAIRE")) {
			mainApp.getFct().addSecretaire(u.getNom(), u.getPrenom(),	txtPassword.getText());
		}
		if(cbLevel.getSelectionModel().getSelectedItem().equals("ENSEIGNANT")) {
			mainApp.getFct().addEnseignant(u.getNom(), u.getPrenom(),	txtPassword.getText());
		}
		if(cbLevel.getSelectionModel().getSelectedItem().equals("ADMINISTRATEUR")) {
			mainApp.getFct().addAdmin(u.getNom(), u.getPrenom(),	txtPassword.getText());
		}
		update();
	}
	
	public void handleSupp() {
		Utilisateur u = new Utilisateur();
		u.setLevel(cbLevel.getSelectionModel().getSelectedItem());
		u.setId(UtilisateurView.getSelectionModel().getSelectedItem().getId());
		mainApp.getFct().deleteUtilisateur(u);
		update();
	}
}
