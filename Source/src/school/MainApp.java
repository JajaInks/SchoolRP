package school;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import school.view.ControlerAdmin;
import school.view.ControlerEtudiant;
import school.view.ControlerInterface;
import school.view.ControlerSecretaire;
import school.view.ControlerEtudiant;

public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;

    private Fonction fct;
    
    
    
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("School ... APP");

        
        fct = new Fonction();
        
        initRootLayout();
        showConnection();
       
    }

    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/Root.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the person overview inside the root layout.
     */
    public void showConnection() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/Interface.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();
            
            // Set person overview into the center of root layout.
            rootLayout.setCenter(personOverview);
            
            
            // Give the controller access to the main app.
             ControlerInterface controller = loader.getController();
             controller.setMainApp(this);
            
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Shows the studier view inside the root layout.
     * need : first and last name + Id studier
     */
    public void showEtudiant(String nom, String prenom , int num) {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/InterfaceEtudiant.fxml"));
            AnchorPane EtudiantView = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(EtudiantView);
            
            // Give the controller access to the main app.
             ControlerEtudiant controllerE = loader.getController();
             controllerE.init(this,nom, prenom,num+"");
             
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**  
     * Shows the secretaire view inside the root layout.
     * need : first , last name and Id secretaire
    */
   public void showSecretaire(String nom, String prenom , int num) {
       try {
           // Load person overview.
           FXMLLoader loader = new FXMLLoader();
           loader.setLocation(MainApp.class.getResource("view/InterfaceSecretaire.fxml"));
           AnchorPane SecretaireView = (AnchorPane) loader.load();

           // Set person overview into the center of root layout.
           rootLayout.setCenter(SecretaireView);
           
           // Give the controller access to the main app.
            ControlerSecretaire controllerS = loader.getController();
            controllerS.init(this,nom, prenom,num+"");
            
           
       } catch (IOException e) {
           e.printStackTrace();
       }
   }
    //
   public void showAdmin() {
       try {
           // Load person overview.
           FXMLLoader loader = new FXMLLoader();
           loader.setLocation(MainApp.class.getResource("view/InterfaceAdmin.fxml"));
           AnchorPane AdminView = (AnchorPane) loader.load();
           // Set person overview into the center of root layout.
           rootLayout.setCenter(AdminView);
           // Give the controller access to the main app.
            ControlerAdmin controllerS = loader.getController();
            controllerS.init(this);
       } catch (IOException e) {
           e.printStackTrace();
       }
   }
   //
   
    /**
     * Returns the main stage.
     * @return
     */ 
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }

	public Fonction getFct() {
		return this.fct;
	}
}