package school.view.edt;

import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;

import entite.Sessions;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import school.MainApp;
import school.view.ControlerEtudiant;
import school.view.ControlerInterface;

public class ControlerCalendar2 implements Initializable {
		@FXML private AnchorPane Root;
		@FXML private DatePicker picker;
		@FXML private TableView SessionView;
		@FXML private TableColumn<Sessions,String> ColDateFin;
		@FXML private TableColumn<Sessions,String> colDateDebut;
		@FXML private TableColumn<Sessions,String> colNom;
		
		private Calendar h = Calendar.getInstance();
		private MainApp mainApp;
		private ControlerEtudiant parent;
		
	
	
		
		
		public ControlerCalendar2(){
		}
		
		//cellData.getValue().getDateDebutSession().get(h.YEAR) +"-"+ cellData.getValue().getDateDebutSession().get(h.MONTH)+"-"+ cellData.getValue().getDateDebutSession().get(h.DAY_OF_MONTH)
	    @FXML public void initialize(URL location, ResourceBundle resources){
	        //
	    	colNom.setCellValueFactory(cellData ->  new SimpleStringProperty(cellData.getValue().getLibelSession()) );
	    	colDateDebut.setCellValueFactory(cellData ->  new SimpleStringProperty(cellData.getValue().getDateDebutSession().get(h.YEAR) +"-"+ cellData.getValue().getDateDebutSession().get(h.MONTH)+"-"+ cellData.getValue().getDateDebutSession().get(h.DAY_OF_MONTH)));
	    	ColDateFin.setCellValueFactory(cellData -> new SimpleObjectProperty(  cellData.getValue().getDateFinSession().get(h.YEAR) +"-"+ cellData.getValue().getDateFinSession().get(h.MONTH)+"-"+ cellData.getValue().getDateFinSession().get(h.DAY_OF_MONTH)));
	    	
	    	//
	    	
	    }
	    
	    
	    @FXML public void handlePicker() {
	    	
	    	h.set(h.DAY_OF_MONTH, picker.getValue().getDayOfMonth());
	    	h.set(h.MONTH,picker.getValue().getMonth().getValue());
	    	h.set(h.YEAR,picker.getValue().getYear());
	    	ArrayList<Sessions> c = mainApp.getFct().selectSessionDeE(Integer.parseInt(parent.getNum()), h);
	    	ObservableList<Sessions> SessionsData = FXCollections.observableArrayList();
	    	for(Sessions s : c) {
	    		SessionsData.add(s);
	    	}
	    	SessionView.setItems(SessionsData);
	    }
	    
	    
	    @FXML
	    public void backAcc() {
	    	parent.goACC(Root);
	    }
	    
	    
	    public void setMainApp(MainApp mainApp) {
	        this.mainApp = mainApp;
	    }
	    
	    public void setControlerParent(ControlerEtudiant e ) {
	        this.parent = e;
	    }
}
