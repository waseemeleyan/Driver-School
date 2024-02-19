package application;
import static javafx.stage.Modality.NONE;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Properties;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.converter.IntegerStringConverter;

public class LessonTable extends Application {
	
	public ArrayList<Lesson> data=new ArrayList();
	public ObservableList<Lesson> dataList;
	
	@Override
	public void start(Stage stage) {
		data = new ArrayList<>();
		
		try {
			
			getData();
						
			//convert data from arraylist to observable arraylist
		    dataList = FXCollections.observableArrayList(data);

			//really bad method
		    //tableView(stage);
			
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		

	}


	@SuppressWarnings("unchecked")
	
	public Scene tableView() {
//data = new ArrayList<>();
		
		try {
			
			getData();
						
			//convert data from arraylist to observable arraylist
		    dataList = FXCollections.observableArrayList(data);

			//really bad method
		    //tableView(stage);
			
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
	    TableView<Lesson> myDataTable = new TableView<Lesson>();
		Scene scene = new Scene(new Group());
       
 
        Label label = new Label("Lesson Table");
        label.setFont(new Font("Verdana", 25));
        label.setTextFill(Color.DARKRED);
        label.setTranslateX(150);
 
        myDataTable.setEditable(true);
        myDataTable.setMaxHeight(500);
        myDataTable.setMaxWidth(700);
 
        
        
        
        // name of column for display
        TableColumn<Lesson, Integer> l_idCol = new TableColumn<Lesson, Integer>("L_ID");
        l_idCol.setMinWidth(50);
        // to get the data from specific column
        l_idCol.setCellValueFactory(new PropertyValueFactory<Lesson, Integer>("l_id"));//t_id is the assigned property in teacher class
        
        
   
        TableColumn<Lesson, String> timeCol = new TableColumn<Lesson, String>("Time");
        timeCol.setMinWidth(50);
        timeCol.setCellValueFactory(new PropertyValueFactory<Lesson, String>("time"));//t_name in teacher class
    

        timeCol.setCellFactory(TextFieldTableCell.<Lesson>forTableColumn());


        timeCol.setOnEditCommit(
        		(CellEditEvent<Lesson, String> t) -> {
                       ((Lesson) t.getTableView().getItems().get(
        	                        t.getTablePosition().getRow())
        	                        ).setTime(t.getNewValue()); //display only
                 updateTime( t.getRowValue().getL_id(),t.getNewValue());
        		});

        
        
        
        
        TableColumn<Lesson, String> dateCol = new TableColumn<Lesson, String>("Date");
        dateCol.setMinWidth(100);
        dateCol.setCellValueFactory(new PropertyValueFactory<Lesson, String>("date"));//dob in teacher class
       

        dateCol.setCellFactory(TextFieldTableCell.<Lesson>forTableColumn());


        dateCol.setOnEditCommit(
        		(CellEditEvent<Lesson, String> t) -> {
                       ((Lesson) t.getTableView().getItems().get(
        	                        t.getTablePosition().getRow())
        	                        ).setDate(t.getNewValue()); //display only
                 updateDate( t.getRowValue().getL_id(),t.getNewValue());
        		});

       

        
        TableColumn<Lesson, Integer> s_idCol = new TableColumn<Lesson, Integer>("S_ID");
        s_idCol.setMinWidth(100);
        s_idCol.setCellValueFactory(new PropertyValueFactory<Lesson, Integer>("s_id"));
        
        
        s_idCol.setCellFactory(TextFieldTableCell.<Lesson,Integer>forTableColumn(new IntegerStringConverter()));
        s_idCol.setOnEditCommit(        
        		(CellEditEvent<Lesson, Integer> t) -> {
        			if(   updateS_id( t.getRowValue().getL_id(),t.getNewValue())==0)
        			{
        				Stage Error = new Stage();
                    	Error.setTitle("ERROR");
                    	VBox pane2 = new VBox();
                    	pane2.setAlignment(Pos.CENTER);
                    	Label label2 = new Label("Cannot Update ,Please Check S_ID");
                    	Font font = Font.font("Arial",FontWeight.BOLD, 15);
                        label2.setFont(font);
                    	pane2.getChildren().addAll(label2);
                    	Scene scene2 = new Scene(pane2,350,200) ;//add the pane to the scene 
            			Error.setScene(scene2);//add the scene to the stage
            			Error.show();//show stage
        				myDataTable.refresh();//to refresh table view after doing a forbidden edit
        				
        			}
        			else {
        			((Lesson) t.getTableView().getItems().get(
	                        t.getTablePosition().getRow())
	                        ).setS_id(t.getNewValue()); //display only
        			}

        		});
        
        TableColumn<Lesson, Integer> t_idCol = new TableColumn<Lesson, Integer>("T_ID");
        t_idCol.setMinWidth(100);
        t_idCol.setCellValueFactory(new PropertyValueFactory<Lesson, Integer>("t_id"));
        
        
        t_idCol.setCellFactory(TextFieldTableCell.<Lesson,Integer>forTableColumn(new IntegerStringConverter()));
        t_idCol.setOnEditCommit(        
        		(CellEditEvent<Lesson, Integer> t) -> {
        			if( updateT_id( t.getRowValue().getL_id(),t.getNewValue())==0)
        			{
        				Stage Error = new Stage();
                    	Error.setTitle("ERROR");
                    	VBox pane2 = new VBox();
                    	pane2.setAlignment(Pos.CENTER);
                    	Label label2 = new Label("Cannot Update ,Please Check T_ID");
                    	Font font = Font.font("Arial",FontWeight.BOLD, 15);
                        label2.setFont(font);
                    	pane2.getChildren().addAll(label2);
                    	Scene scene2 = new Scene(pane2,350,200) ;//add the pane to the scene 
            			Error.setScene(scene2);//add the scene to the stage
            			Error.show();//show stage
        				myDataTable.refresh();//to refresh table view after doing a forbidden edit
        				
        			}
        			else {
        			((Lesson) t.getTableView().getItems().get(
	                        t.getTablePosition().getRow())
	                        ).setS_id(t.getNewValue()); //display only
        			}
        		});
        TableColumn<Lesson, Integer> v_idCol = new TableColumn<Lesson, Integer>("V_ID");
        v_idCol.setMinWidth(100);
        v_idCol.setCellValueFactory(new PropertyValueFactory<Lesson, Integer>("v_id"));
        
        
        v_idCol.setCellFactory(TextFieldTableCell.<Lesson,Integer>forTableColumn(new IntegerStringConverter()));
        v_idCol.setOnEditCommit(        
        		(CellEditEvent<Lesson, Integer> t) -> {
        			if(  updateV_id( t.getRowValue().getL_id(),t.getNewValue())==0)
        			{
        				Stage Error = new Stage();
                    	Error.setTitle("ERROR");
                    	VBox pane2 = new VBox();
                    	pane2.setAlignment(Pos.CENTER);
                    	Label label2 = new Label("Cannot Update ,Please Check V_ID");
                    	Font font = Font.font("Arial",FontWeight.BOLD, 15);
                        label2.setFont(font);
                    	pane2.getChildren().addAll(label2);
                    	Scene scene2 = new Scene(pane2,350,200) ;//add the pane to the scene 
            			Error.setScene(scene2);//add the scene to the stage
            			Error.show();//show stage
        				myDataTable.refresh();//to refresh table view after doing a forbidden edit
        				
        			}
        			else {
        			((Lesson) t.getTableView().getItems().get(
	                        t.getTablePosition().getRow())
	                        ).setS_id(t.getNewValue()); //display only
        			}
         
        		});
       
       
        
        
        
        myDataTable.setItems(dataList);
        
        myDataTable.getColumns().addAll(l_idCol, timeCol, dateCol, s_idCol, t_idCol,v_idCol);

        final TextField addL_id = new TextField();
        addL_id.setPromptText("L_ID");
        addL_id.setMaxWidth(l_idCol.getPrefWidth());
        addL_id.setStyle("-fx-border-color: brown;-fx-font-weight: bold");
        final TextField addDate = new TextField();
        addDate.setPrefWidth(100);
        addDate.setPromptText("Date");
        addDate.setStyle("-fx-border-color: brown;-fx-font-weight: bold");
        final TextField addTime = new TextField();
        addTime.setPrefWidth(100);
        addTime.setPromptText("Time");
        addTime.setStyle("-fx-border-color: brown;-fx-font-weight: bold");

        final TextField addT_id = new TextField();
        addT_id.setPromptText("T_ID");
        addT_id.setMaxWidth(l_idCol.getPrefWidth());
        addT_id.setStyle("-fx-border-color: brown;-fx-font-weight: bold");
       
        final TextField addS_id = new TextField();
        addS_id.setPromptText("S_ID");
        addS_id.setMaxWidth(l_idCol.getPrefWidth());
        addS_id.setStyle("-fx-border-color: brown;-fx-font-weight: bold");
        final TextField addV_id = new TextField();
        addV_id.setPromptText("V_ID");
        addV_id.setMaxWidth(l_idCol.getPrefWidth());
        addV_id.setStyle("-fx-border-color: brown;-fx-font-weight: bold");
        
        
        final Button addButton = new Button("Add");
        addButton.setStyle("-fx-font-weight: bold;-fx-background-color: brown;");
        addButton.setOnMouseEntered(event -> addButton.setStyle("-fx-font-weight: bold;-fx-background-color: lightblue;"));
        addButton.setOnMouseExited(event -> addButton.setStyle("-fx-font-weight: bold;-fx-background-color: brown;"));
        addButton.setStyle("-fx-font-weight: bold;-fx-background-color: brown");
        addButton.setOnAction((ActionEvent e) -> {
            
            
            
            		
            		Stage confirm = new Stage();
                	confirm.setTitle("Confirm");
                	VBox pane2 = new VBox();
                	pane2.setAlignment(Pos.CENTER);
                	Label label2 = new Label("Are You Sure From Information Of Lesson?\nNote:Once Added, Some Info Couldn't Be Edited");
                	Font font = Font.font("Arial",FontWeight.BOLD, 15);
                    label2.setFont(font);
                    final Button confirmD = new Button("CONFIRM");
                    confirmD.setStyle("-fx-font-weight: bold;-fx-background-color: brown;");
                    confirmD.setOnMouseEntered(event -> confirmD.setStyle("-fx-font-weight: bold;-fx-background-color: lightblue;"));
                    confirmD.setOnMouseExited(event -> confirmD.setStyle("-fx-font-weight: bold;-fx-background-color: brown;"));
                    confirmD.setStyle("-fx-font-weight: bold;-fx-background-color: brown");
                	pane2.getChildren().addAll(label2,confirmD);
                	Scene scene2 = new Scene(pane2,450,300) ;//add the pane to the scene 
                	confirm.setScene(scene2);//add the scene to the stage
                	confirm.show();//show stage
                	confirmD.setOnAction((ActionEvent o) -> {
                		Lesson rc;
                
                		 String str1=addTime.getText();
                         String str2="20:00:00";
                         
                         LocalTime time1 = LocalTime.parse(str1);
                         LocalTime time2 = LocalTime.parse(str2);
                         int compare = time1.compareTo(time2);
                         if( compare > 0)
                         {
                         	Stage Error = new Stage();
                         	Error.setTitle("ERROR");
                         	VBox pane3 = new VBox();
                         	pane3.setAlignment(Pos.CENTER);
                         	Label label21 = new Label("Cannot Add Lesson , Lesson Time Is Late\n(After 8 PM)");
                         	Font font2 = Font.font("Arial",FontWeight.BOLD, 15);
                              label21.setFont(font2);
                         	pane3.getChildren().addAll(label21);
                         	Scene scene3 = new Scene(pane3,350,200) ;//add the pane to the scene 
                 			Error.setScene(scene3);//add the scene to the stage
                 			Error.show();//show stage
             				
                         	
                         }
                         
                         	else {
                		rc = new Lesson(
                                Integer.valueOf(addL_id.getText()),
                                addTime.getText(),
                                addDate.getText(),
                                Integer.valueOf(addS_id.getText()),
                                Integer.valueOf(addT_id.getText()),
                                Integer.valueOf(addV_id.getText()),"");
                		if(insertData(rc)==0)
                		{
                			Stage Error = new Stage();
                         	Error.setTitle("ERROR");
                         	VBox pane3 = new VBox();
                         	pane3.setAlignment(Pos.CENTER);
                         	Label label21 = new Label("Cannot Add Lesson , Please Check L_ID , S_ID,V_ID");
                         	Font font2 = Font.font("Arial",FontWeight.BOLD, 15);
                              label21.setFont(font2);
                         	pane3.getChildren().addAll(label21);
                         	Scene scene3 = new Scene(pane3,350,200) ;//add the pane to the scene 
                 			Error.setScene(scene3);//add the scene to the stage
                 			Error.show();//show stage
                		}
                		else
                		{
                    	dataList.add(rc);
                        addT_id.clear();
                        addTime.clear();
                        addDate.clear();
                        addS_id.clear();
                        addL_id.clear();
                        addV_id.clear();
                        confirm.close();
                       // addStartDate.clear();
                		}
                         	}
                         
                	});                	
            		
    
            	
            
        });
 
        final HBox hb = new HBox();

 
        final Button deleteButton = new Button("Delete");
        deleteButton.setStyle("-fx-font-weight: bold;-fx-background-color: brown;");
        deleteButton.setOnMouseEntered(event -> deleteButton.setStyle("-fx-font-weight: bold;-fx-background-color: lightgreen;"));
        deleteButton.setOnMouseExited(event -> deleteButton.setStyle("-fx-font-weight: bold;-fx-background-color: brown;"));
        deleteButton.setStyle("-fx-font-weight: bold;-fx-background-color: brown");
        deleteButton.setOnAction((ActionEvent e) -> {        	 
        	 ObservableList<Lesson> selectedRows = myDataTable.getSelectionModel().getSelectedItems();
        	ArrayList<Lesson> rows = new ArrayList<>(selectedRows);
        	rows.forEach(row -> {
        		myDataTable.getItems().remove(row); 
        		deleteRow(row); 
        		myDataTable.refresh();
        		});   
        });
        
 
   
        
        final Button refreshButton = new Button("Refresh");
        refreshButton.setStyle("-fx-font-weight: bold;-fx-background-color: brown;");
        refreshButton.setOnMouseEntered(event -> refreshButton.setStyle("-fx-font-weight: bold;-fx-background-color: lightpink;"));
        refreshButton.setOnMouseExited(event -> refreshButton.setStyle("-fx-font-weight: bold;-fx-background-color: brown;"));
        refreshButton.setStyle("-fx-font-weight: bold;-fx-background-color: brown");
        refreshButton.setOnAction((ActionEvent e) -> {        	 
        	myDataTable.refresh();  
        });
        
//		Button ownedNoneButton = new Button("Owned None");
//		ownedNoneButton.setOnAction(c -> );
		
        final Button clearButton = new Button("Clear All");
        clearButton.setStyle("-fx-font-weight: bold;-fx-background-color: brown;");
        clearButton.setOnMouseEntered(event -> clearButton.setStyle("-fx-font-weight: bold;-fx-background-color: lightyellow;"));
        clearButton.setOnMouseExited(event -> clearButton.setStyle("-fx-font-weight: bold;-fx-background-color: brown;"));
        clearButton.setStyle("-fx-font-weight: bold;-fx-background-color: brown");
        	clearButton.setOnAction((ActionEvent e) -> {
        		Stage stage = new Stage();
        		showDialog(stage, NONE, myDataTable);
        		
        });
        	
        	hb.getChildren().addAll(addL_id, addTime, addDate, addS_id,addT_id,addV_id);
	        hb.setSpacing(3);
        	
       final HBox hb2 = new HBox();
        hb2.getChildren().addAll(addButton, deleteButton);
        hb2.setAlignment(Pos.CENTER);
        hb2.setSpacing(3);
        final HBox hb3 = new HBox();
        hb2.getChildren().addAll(clearButton, refreshButton);
        hb2.setAlignment(Pos.CENTER);
        hb2.setSpacing(3);

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
       // vbox.setAlignment(Pos.CENTER);
        vbox.setTranslateX(100);
        vbox.setTranslateY(20);
        vbox.getChildren().addAll(label, myDataTable, hb,hb2,hb3);
        StackPane root = new StackPane();
        root.getChildren().add(vbox);
        StackPane.setAlignment(vbox, Pos.CENTER);
	//	vbox.getChildren().addAll(label, myDataTable);
        ((Group) scene.getRoot()).getChildren().addAll(root);
          return scene ;
	}
	
	


	public int insertData(Lesson rc) {
		
	try {
			System.out.println("Insert into Lesson (L_Time,L_Date,S_id,T_id,V_id) values("+
						"'"
						+rc.getTime()+"','"
						+ rc.getDate()+"',"
						+ rc.getS_id() +","
						+ rc.getT_id()+","
						+ rc.getV_id() + ");");
			
			DataBaseConnector.connectDB();
			try {
				Statement stmt = DataBaseConnector.con.createStatement();
				stmt.executeUpdate("Insert into Lesson (L_Time,L_Date,S_id,T_id,V_id) values("+
						"'"
						+rc.getTime()+"','"
						+ rc.getDate()+"',"
						+ rc.getS_id() +","
						+ rc.getT_id()+","
						+ rc.getV_id() + ");");
				stmt.close();
			
				 
			}
			catch(SQLException s) {
				s.printStackTrace();
				return 0;
				  
			}
			DataBaseConnector.con.close();
			
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
	return 1;
	}
	
	
	public void getData() throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		
		String SQL;	
		DataBaseConnector.connectDB();
		SQL = "select L_id,L_Time,L_Date,S_id,T_id,V_id,Stat from Lesson order by L_id";
		Statement stmt = DataBaseConnector.con.createStatement();
		ResultSet rs = stmt.executeQuery(SQL);


		while ( rs.next() ) 
			data.add(new Lesson(
					Integer.parseInt(rs.getString(1)),
					rs.getString(2),
					rs.getString(3),
					Integer.parseInt(rs.getString(4)),
					Integer.parseInt(rs.getString(5)),
					Integer.parseInt(rs.getString(6)),rs.getString(7)));
		
		rs.close();
		stmt.close();

		DataBaseConnector.con.close();
		
		
	}


	public void connectDB() throws ClassNotFoundException, SQLException {
		
		DataBaseConnector.dbURL = "jdbc:mysql://" + DataBaseConnector.URL + ":" + DataBaseConnector.port + "/" + DataBaseConnector.dbName + "?verifyServerCertificate=false";
		Properties p = new Properties();
		p.setProperty("user", DataBaseConnector.dbUsername);
		p.setProperty("password", DataBaseConnector.dbPassword);
		p.setProperty("useSSL", "false");
		p.setProperty("autoReconnect", "true");
		Class.forName("com.mysql.jdbc.Driver");
	
		DataBaseConnector.con = DriverManager.getConnection (DataBaseConnector.dbURL, p);

	}


	public void ExecuteStatement(String SQL) throws SQLException {

		try {
			Statement stmt = DataBaseConnector.con.createStatement();
			stmt.executeUpdate(SQL);
			stmt.close();
		
			 
		}
		catch(SQLException s) {
			s.printStackTrace();
			  
		}
		
		
	}
	
	public int updateTime(int l_id, String time) {
				
		try {
			DataBaseConnector.connectDB();
			try {
				Statement stmt = DataBaseConnector.con.createStatement();
				stmt.executeUpdate("update  Lesson set L_time = '"+time + "' where l_id = "+l_id+";");
				stmt.close();
			
				 
			}
			catch(SQLException s) {
				s.printStackTrace();
				return 0;
				  
			}
			DataBaseConnector.con.close();
			System.out.println("Connection closed");
			
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		return 1;
	}
	
	
public int updateS_id(int l_id, int s_id) {
		
		try {
			DataBaseConnector.connectDB();
			try {
				Statement stmt = DataBaseConnector.con.createStatement();
				stmt.executeUpdate("update  Lesson set s_id = "+s_id + " where l_id = "+l_id+";");
				stmt.close();
			
				 
			}
			catch(SQLException s) {
				s.printStackTrace();
				return 0;
				  
			}
			DataBaseConnector.con.close();
			
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		return 1;
	}
public int updateT_id(int l_id, int t_id) {
	
	try {
		DataBaseConnector.connectDB();
		try {
			Statement stmt = DataBaseConnector.con.createStatement();
			stmt.executeUpdate("update  Lesson set t_id = "+t_id + " where l_id = "+l_id+";");
			stmt.close();
		
			 
		}
		catch(SQLException s) {
			s.printStackTrace();
			return 0;
			  
		}
		DataBaseConnector.con.close();
		
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	return 1;
}
public int updateV_id(int l_id, int v_id) {
	
	try {
		DataBaseConnector.connectDB();
		try {
			Statement stmt = DataBaseConnector.con.createStatement();
			stmt.executeUpdate("update  Lesson set v_id = "+v_id + " where l_id = "+l_id+";");
			stmt.close();
		
			 
		}
		catch(SQLException s) {
			s.printStackTrace();
			return 0;
			  
		}
		DataBaseConnector.con.close();
		
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	return 1;
}
	
	
public void updateDate(int l_id, String date) {
		
		try {
			DataBaseConnector.connectDB();
			ExecuteStatement("update  Lesson set L_Date = '"+date + "' where l_id = "+l_id+";");
			DataBaseConnector.con.close();
			
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
	}
public void updateStatus(int l_id, String stat) {
	
	try {
		DataBaseConnector.connectDB();
		ExecuteStatement("update  Lesson set Stat = '"+stat + "' where l_id = "+l_id+";");
		DataBaseConnector.con.close();
		
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
}

public void deleteRow(Lesson row) {
		// TODO Auto-generated method stub
		
		try {
			DataBaseConnector.connectDB();
			ExecuteStatement("Update Lesson set Stat = 'Cancelled' where l_id="+row.getL_id() + ";");
			DataBaseConnector.con.close();
			
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
	}
	
public void showDialog(Window owner, Modality modality,   TableView<Lesson> table) {
		// Create a Stage with specified owner and modality
		Stage stage = new Stage();
		stage.initOwner(owner);
		stage.initModality(modality);
	//	Label modalityLabel = new Label(modality.toString());
		
		Button yesButton = new Button("Confirm");
		yesButton.setOnAction(e -> {
			for (Lesson row:dataList) {
        			deleteRow(row);
        			row.setStatus("Cancelled");
        			table.refresh();
        		}
        		   stage.close();

			});
		
		Button noButton = new Button("Cancel");
		noButton.setOnAction(e -> stage.close());

		HBox root = new HBox();
		root.setPadding(new Insets(10, 10, 10, 10));
		root.setAlignment(Pos.CENTER);
        root.setSpacing(10);

		root.getChildren().addAll(yesButton, noButton);
		Scene scene = new Scene(root, 200, 100);
		stage.setScene(scene);
		stage.setTitle("Confirm Delete?");
		stage.show();
		}
	
	

}
