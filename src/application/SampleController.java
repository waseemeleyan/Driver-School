package application;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SampleController implements Initializable {
	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;
	   private double x = 0;
	    private double y = 0;

		@FXML
		private AnchorPane main_form;

		@FXML
		private TextField username;

		@FXML
		private PasswordField password;

		@FXML
		private Button login;

		@FXML
		private Button close;
	    private ResultSet result;
	    private String name= "1";
	    private  String pasword="1";

		public void close() {
	        System.exit(0);
		}

		@FXML
		void om(ActionEvent event) {
		}
		
		public void loginAdmin(){
	        
	        
	        
	        try{
	          
	            
	            Alert alert;
	            
	            if(username.getText().isEmpty() || password.getText().isEmpty()){
	                alert = new Alert(AlertType.ERROR);
	                alert.setHeaderText(null);
	                alert.setContentText("Please fill all blank fields");
	                alert.showAndWait();
	            }else{
	                if(username.getText().equals(name)&&password.getText().equals(pasword)){
	                    
//	                    getData.username = username.getText();
	                    
	                    alert = new Alert(AlertType.INFORMATION);
	                    alert.setHeaderText(null);
	                    alert.setContentText("Successfully Login!");
	                    alert.showAndWait();
	                    // HIDE YOUR LOGIN FORM
	                    login.getScene().getWindow().hide();
	                    
	                    Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
	                    Stage stage = new Stage();
	                    Scene scene = new Scene(root);
	                    root.setOnMousePressed((MouseEvent event) ->{
	                        x = event.getSceneX();
	                        y = event.getSceneY();
	                    });
	                    
	                    root.setOnMouseDragged((MouseEvent event) ->{
	                        stage.setX(event.getScreenX() - x);
	                        stage.setY(event.getScreenY() - y);
	                        
	                        stage.setOpacity(.8);
	                    });
	                    
	                    root.setOnMouseReleased((MouseEvent event) ->{
	                        stage.setOpacity(1);
	                    });
	                    stage.initStyle(StageStyle.TRANSPARENT);      
	                    stage.setScene(scene);
	                    stage.show();
	                }else{
	                    alert = new Alert(AlertType.ERROR);
	                    alert.setHeaderText(null);
	                    alert.setContentText("Wrong Username/Password");
	                    alert.showAndWait();
	                }
	            }
	        }catch(Exception e){e.printStackTrace();}
	        
	    }
		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			// TODO Auto-generated method stub

		}

	}

