package school.view;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;

import entite.Groupes;
import entite.Salles;
import entite.Sessions;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import school.MainApp;

public class ControlerSecretaire implements Initializable {
	@FXML private Pane paneAcc;
	@FXML private SplitPane paneSessions;
	@FXML private TableView<Sessions> SessionView;
	@FXML private TableColumn<Sessions,String> ColDateFin;
	@FXML private TableColumn<Sessions,String> colDateDebut;
	@FXML private TableColumn<Sessions,String> colNom;
	@FXML private TableColumn<Sessions,String> colId;
	@FXML private TableColumn<Sessions,String> colSalle;
	@FXML private Button btnSupp;
	@FXML private Button btnModif;
	@FXML private Button btnRecherche;
	@FXML private TextField inName;
	@FXML private DatePicker inDebutPicker;
	@FXML private DatePicker inFinPicker;
	@FXML private ComboBox<Salles> cbSalle;
	@FXML private ComboBox<Groupes> cbGroupe;
	@FXML private Button btnAjouter;
	
	//panel Groupe
	@FXML private SplitPane paneGroupes;
	@FXML private Button btnAjouter2;
	@FXML private Button btnModifier2;
	@FXML private Button btnSupp2;
	@FXML private Button btnRetour2;
	@FXML private ComboBox cbGroupe2;

	@FXML private TextField txtMatiere2;
	
	@FXML private TableView<Groupes> GroupesView;
	@FXML private TableColumn<Groupes,String> GroupesId;
	@FXML private TableColumn<Groupes,String> GroupesMatiere;
	@FXML private TableColumn<Groupes,String> GroupesNom;
	
	private String nom;
	private String prenom;
	private String num;
	ObservableList<Sessions> SessionsData = FXCollections.observableArrayList();
	ObservableList<Groupes> GroupesData = FXCollections.observableArrayList();
	
	
	
	private Calendar h = Calendar.getInstance();
	private MainApp mainApp;
	
	
	//
	public void init(MainApp mainAp, String name,String prename , String numero) {
		this.setMainApp(mainAp);
		this.setNom(name);
		this.setPrenom(prename);
		this.setNum(numero);
		
		cbSalle.getItems().addAll(mainApp.getFct().selectAllNameSalle());
		cbGroupe.getItems().addAll(mainApp.getFct().selectAllNameGroupe());
		
		cbGroupe2.getItems().addAll(mainApp.getFct().selectAllNameGroupe());
		
	}//



	public ControlerSecretaire(){
	}
	
	
	
    @FXML public void initialize(URL location, ResourceBundle resources){
        colId.setCellValueFactory(cellData ->  new SimpleStringProperty(cellData.getValue().getIdSession()+"") );
    	colNom.setCellValueFactory(cellData ->  new SimpleStringProperty(cellData.getValue().getLibelSession()) );
    	colDateDebut.setCellValueFactory(cellData ->  new SimpleStringProperty(cellData.getValue().getDateDebutSession().get(h.YEAR) +"-"+ cellData.getValue().getDateDebutSession().get(h.MONTH)+"-"+ cellData.getValue().getDateDebutSession().get(h.DAY_OF_MONTH)));
    	ColDateFin.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getDateFinSession().get(h.YEAR) +"-"+ cellData.getValue().getDateFinSession().get(h.MONTH)+"-"+ cellData.getValue().getDateFinSession().get(h.DAY_OF_MONTH)));
    	colSalle.setCellValueFactory(cellData ->  new SimpleStringProperty(cellData.getValue().getSalle() ));
    	
    	SessionView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> MajInput(newValue));
    	
    	
    	GroupesId.setCellValueFactory(cellData ->  new SimpleStringProperty(cellData.getValue().getIdGroupe()+""));
    	GroupesMatiere.setCellValueFactory(cellData ->  new SimpleStringProperty(cellData.getValue().getIdMatiere()+""));
    	GroupesNom.setCellValueFactory(cellData ->  new SimpleStringProperty(cellData.getValue().getLibelGroupe()));
    	GroupesView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> MajInput(newValue));
    	
    }
    //****** PANEL ACC
    
    public void GestionSessions() {
    	paneAcc.setVisible(false);
    	SessionView.getSelectionModel().clearSelection();
    	SessionsData.clear();
    	SessionsData.addAll(mainApp.getFct().allSessions());
    	SessionView.setItems(SessionsData);
    	paneSessions.setVisible(true);
    }
    
    
    
    public void GestionGroupes() {
    	paneAcc.setVisible(false);
    	GroupesView.getSelectionModel().clearSelection();
    	GroupesData.clear();
    	GroupesData.addAll(mainApp.getFct().selectAllNameGroupe());
    	GroupesView.setItems(GroupesData);
    	paneGroupes.setVisible(true);
    }
    
    
    /*
    	PANEL SESSIONS
    */
    /**
     * Lorsqu'un items du tableau est selectionnez permet de mettre a jours les textfields
     */
    public void MajInput(Sessions s) {
    	if( s!=null) {
    		
	    	h = s.getDateDebutSession();
	    	LocalDate hh = LocalDate.of(h.get(h.YEAR), h.get(h.MONTH), h.get(h.DAY_OF_MONTH));
	    	inDebutPicker.setValue(hh);
	    	
	    	
	    	h = s.getDateFinSession();
	    	LocalDate hh2 = LocalDate.of(h.get(h.YEAR), h.get(h.MONTH), h.get(h.DAY_OF_MONTH));
	    	inFinPicker.setValue(hh2);
	    	
	    	cbSalle.getSelectionModel().select(mainApp.getFct().selectSalleById(s.getSalle()));
	    	cbGroupe.getSelectionModel().select(mainApp.getFct().selectGroupeById(s.getIdGroupe()));
	    	
	    	inName.setText(s.getLibelSession());
    	}
    }
    
    public void backAcc() {
    	paneAcc.setVisible(true);
    	paneSessions.setVisible(false);
    }
	
	public void handleRecherche() {
		SessionView.getSelectionModel().clearSelection();
		SessionsData.clear();
    	SessionsData.addAll(mainApp.getFct().selectSessionName(inName.getText()));
    	SessionView.setItems(SessionsData);
	}
    
    public void handleModif() {
    	
    	Sessions s = SessionView.getSelectionModel().getSelectedItem();
    	
    	System.out.println(s.toString());
    	mainApp.getFct().updateSessions(s.getIdSession(),inName.getText(),cbSalle.getSelectionModel().getSelectedItem().getIdSalle(),inDebutPicker.getValue(),inFinPicker.getValue());
    	SessionView.getSelectionModel().clearSelection();
    	SessionsData.clear();
    	SessionsData.addAll(mainApp.getFct().allSessions());
    	SessionView.setItems(SessionsData);
    	
    	
    }
    
    public void handleSupp() {
    	
    	
    	mainApp.getFct().suppSessions(SessionView.getSelectionModel().getSelectedItem().getIdSession()+"");
    	SessionView.getSelectionModel().clearSelection();
    	SessionsData.clear();
    	
    	SessionsData.addAll(mainApp.getFct().allSessions());
    	SessionView.setItems(SessionsData);
    
    }
    
   public void handleAjouter() {
	   mainApp.getFct().addSessions(((Salles) cbSalle.getSelectionModel().getSelectedItem()).getIdSalle(), ((Groupes) cbGroupe.getSelectionModel().getSelectedItem()).getIdGroupe(),inName.getText(),inDebutPicker.getValue(),inFinPicker.getValue());

	   SessionView.getSelectionModel().clearSelection();
	   SessionsData.clear();

	   SessionsData.addAll(mainApp.getFct().allSessions());
	   SessionView.setItems(SessionsData);
   }
    
    /**
   
   		Panel des groupes.
   
   **/
   
   public void backAcc2() {
	   paneAcc.setVisible(true);
	   paneGroupes.setVisible(false);
   }
   
   public void MajInput(Groupes s) {
	   if( s!=null) {
		   txtMatiere2.setText(s.getIdMatiere()+"");
		   cbGroupe2.getSelectionModel().select(mainApp.getFct().selectGroupeById(s.getIdGroupe()));
	   }
   }
   
   public void handleModif2() {
		Groupes g = new Groupes();
		g.setIdMatiere(Integer.valueOf(txtMatiere2.getText()));
		g.setLibelGroupe(((Groupes) cbGroupe2.getValue()).getLibelGroupe());
		mainApp.getFct().updateGroupe(g);
		SessionView.getSelectionModel().clearSelection();
	   	SessionsData.clear();
	   	SessionsData.addAll(mainApp.getFct().allSessions());
	   	SessionView.setItems(SessionsData);
   }
   
   public void handleSupp2() {
   	
   	mainApp.getFct().deleteGroupe(GroupesView.getSelectionModel().getSelectedItem());
   	GroupesView.getSelectionModel().clearSelection();
   	GroupesData.clear();
   	GroupesData.addAll(mainApp.getFct().selectAllNameGroupe());
   	GroupesView.setItems(GroupesData);
   	
   }
   
  public void handleAjouter2() {
		Groupes g = new Groupes();
		g.setIdMatiere(Integer.valueOf(txtMatiere2.getText()));
		g.setLibelGroupe(((Groupes) cbGroupe2.getValue()).getLibelGroupe());
	   mainApp.getFct().addGroupe(g);
	   SessionView.getSelectionModel().clearSelection();
	   SessionsData.clear();
	   SessionsData.addAll(mainApp.getFct().allSessions());
	   SessionView.setItems(SessionsData);
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
	public void setNum(String num) {
		this.num = num;
	}
	public MainApp getMainApp() {
		return mainApp;
	}
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}


}
