package application;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {
   
	private double x = 0;
	private double y = 0;
	@Override
	public void start(Stage primaryStage) {
		
		try {
//			StackPane root = (StackPane)FXMLLoader.load(getClass().getResource("Sample.fxml"));
	        Parent root = FXMLLoader.load(getClass().getResource("Sample.fxml"));
			
			  root.setOnMousePressed((MouseEvent event) ->{
		            x = event.getSceneX();
		            y = event.getSceneY();
		        });
		        
		        root.setOnMouseDragged((MouseEvent event) ->{
		            primaryStage.setX(event.getScreenX() - x);
		            primaryStage.setY(event.getScreenY() - y);
		            
		            primaryStage.setOpacity(.8);
		        });
		        
		        root.setOnMouseReleased((MouseEvent event) ->{
		        	primaryStage.setOpacity(1);
		        });
			
			Scene scene = new Scene(root,700,450);
	        primaryStage.initStyle(StageStyle.TRANSPARENT);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	
	}
	public static void main(String[] args) {
		launch(args);
	}
    
    
    public static void ExecuteStatement(String SQL) throws SQLException {

		try {
			Statement stmt = DataBaseConnector.con.createStatement();
			stmt.executeUpdate(SQL);
			stmt.close();
		
			 
		}
		catch(SQLException s) {
			s.printStackTrace();
			  
		}
		
		
	}
   public static int countActiveVeichles() throws SQLException , ClassNotFoundException{
    	String SQL;	
		DataBaseConnector.connectDB();
		SQL = "SELECT COUNT(V_id) FROM Veichle WHERE stat = 'Active'";
		Statement stmt = DataBaseConnector.con.createStatement();
		ResultSet rs = stmt.executeQuery(SQL);
		Integer r=0;
		while ( rs.next() ) {
    	r = Integer.parseInt(rs.getString(1));
		}
    	rs.close();
		stmt.close();

		DataBaseConnector.con.close();
    	return r;
    	
    }
   public static int countActiveStudents() throws SQLException , ClassNotFoundException{
   
   	String SQL;	
		DataBaseConnector.connectDB();
		SQL = "SELECT COUNT(Sid) AS active_Student_count FROM Student WHERE sstatus = 'Active'";
		Statement stmt = DataBaseConnector.con.createStatement();
		ResultSet rs = stmt.executeQuery(SQL);
		Integer r=0;
		while ( rs.next() ) {
			r = Integer.parseInt(rs.getString(1));
		}
   	rs.close();
		stmt.close();

		DataBaseConnector.con.close();
   	return r;
   	
   }
   public static int countActiveTeachers() throws SQLException , ClassNotFoundException{
	   
	   	String SQL;	
			DataBaseConnector.connectDB();
			SQL = "SELECT COUNT(T_id) AS active_Teacher_count FROM Teacher WHERE stat = 'Active'";
			Statement stmt = DataBaseConnector.con.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);
			Integer r=0;
			while ( rs.next() ) {
				r = Integer.parseInt(rs.getString(1));
			}
	   	rs.close();
			stmt.close();

			DataBaseConnector.con.close();
	   	return r;
	   	
	   }
   
   
 
   
   public static ObservableList<Student> searchStudent(String stName) throws SQLException , ClassNotFoundException{
	   	  
	   	  ArrayList<Student> data=new ArrayList();
		  ObservableList<Student> dataList;
	   		String SQL;	
			DataBaseConnector.connectDB();
			SQL = "SELECT * From Student S "
					+ " WHERE S.sname = '" + stName + "'";
			Statement stmt = DataBaseConnector.con.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);
			Integer r=0;
			while ( rs.next() ) {
				data.add(new Student(
						Integer.parseInt(rs.getString(1)),
						rs.getString(2),
						rs.getString(3),
						Integer.parseInt(rs.getString(4)),
						Integer.parseInt(rs.getString(5))));
			}
	   	rs.close();
			stmt.close();

			DataBaseConnector.con.close();
			  dataList = FXCollections.observableArrayList(data);
	   	return dataList;
	   	
	   }
   
   public static int countBillsCosts() throws SQLException , ClassNotFoundException{
	   
	   	String SQL;	
			DataBaseConnector.connectDB();
			SQL = "SELECT SUM(cost) AS total_cost FROM Bill B , Lesson L , Student S , License Li"
					+ " WHERE B.L_ID = L.L_ID AND "
					+ "S.Sid=L.S_ID AND "
					+ "S.License_ID = Li.License_ID;";
			Statement stmt = DataBaseConnector.con.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);
			Integer r=0;
			while ( rs.next() ) {
				r = Integer.parseInt(rs.getString(1));
			}
	   	rs.close();
			stmt.close();

			DataBaseConnector.con.close();
	   	return r;
	   	
	   }
   
   
   
   
}



