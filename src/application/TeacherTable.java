package application;

import static javafx.stage.Modality.NONE;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
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
import javafx.scene.control.ComboBox;
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

public class TeacherTable extends Application {
	public ArrayList<Teacher> data=new ArrayList<>();
	public ObservableList<Teacher> dataList;
	
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
	    TableView<Teacher> myDataTable = new TableView<Teacher>();
		Scene scene = new Scene(new Group());
       
 
        Label label = new Label("Teacher Table");
        label.setFont(new Font("Verdana", 25));
        label.setTextFill(Color.DARKRED);
        label.setTranslateX(250);
 
        myDataTable.setEditable(true);
        myDataTable.setMaxHeight(500);
        myDataTable.setMaxWidth(700);
       
        
        
        
        // name of column for display
        TableColumn<Teacher, Integer> t_idCol = new TableColumn<Teacher, Integer>("T_ID");
        t_idCol.setMinWidth(50);
        // to get the data from specific column
        t_idCol.setCellValueFactory(new PropertyValueFactory<Teacher, Integer>("t_id"));//t_id is the assigned property in teacher class
        
        
   
        TableColumn<Teacher, String> t_nameCol = new TableColumn<Teacher, String>("T_Name");
        t_nameCol.setMinWidth(50);
        t_nameCol.setCellValueFactory(new PropertyValueFactory<Teacher, String>("t_name"));//t_name in teacher class
    

        t_nameCol.setCellFactory(TextFieldTableCell.<Teacher>forTableColumn());


        t_nameCol.setOnEditCommit(
        		(CellEditEvent<Teacher, String> t) -> {
                       ((Teacher) t.getTableView().getItems().get(
        	                        t.getTablePosition().getRow())
        	                        ).setT_name(t.getNewValue()); //display only
                 updateName( t.getRowValue().getT_id(),t.getNewValue());
        		});

        
        
        
        
        TableColumn<Teacher, String> DOBCol = new TableColumn<Teacher, String>("Date Of Birth");
        DOBCol.setMinWidth(100);
        DOBCol.setCellValueFactory(new PropertyValueFactory<Teacher, String>("dob"));//dob in teacher class
       

        //update is under start date update due to both depending on each other 
       

        
        TableColumn<Teacher, Integer> salaryCol = new TableColumn<Teacher, Integer>("Salary");
        salaryCol.setMinWidth(100);
        salaryCol.setCellValueFactory(new PropertyValueFactory<Teacher, Integer>("salary"));
        
        
        salaryCol.setCellFactory(TextFieldTableCell.<Teacher,Integer>forTableColumn(new IntegerStringConverter()));
        salaryCol.setOnEditCommit(        
        		(CellEditEvent<Teacher, Integer> t) -> {
        			int oldV=t.getOldValue();
        			
        			if (t.getNewValue() < 2000)
        			{
        				Stage Error = new Stage();
                    	Error.setTitle("ERROR");
                    	VBox pane2 = new VBox();
                    	pane2.setAlignment(Pos.CENTER);
                    	Label label2 = new Label("Cannot Add Teacher ,Wage Is Under Limit\n(2000)");
                    	Font font = Font.font("Arial",FontWeight.BOLD, 15);
                        label2.setFont(font);
                    	pane2.getChildren().addAll(label2);
                    	Scene scene2 = new Scene(pane2,350,200) ;//add the pane to the scene 
            			Error.setScene(scene2);//add the scene to the stage
            			Error.show();//show stage
            			((Teacher) t.getTableView().getItems().get(
    	                        t.getTablePosition().getRow())
    	                        ).setSalary(oldV);
        				myDataTable.refresh();//to refresh table view after doing a forbidden edit
        			}
        			else {
        			
                       ((Teacher) t.getTableView().getItems().get(
        	                        t.getTablePosition().getRow())
        	                        ).setSalary(t.getNewValue());
                 updateSalary( t.getRowValue().getT_id(),t.getNewValue());
        			}
        		});
        
        TableColumn<Teacher, String> startDateCol = new TableColumn<Teacher, String>("Start Date");
        startDateCol.setMinWidth(100);
        startDateCol.setCellValueFactory(new PropertyValueFactory<Teacher, String>("start_date"));
        
       

        TableColumn<Teacher, String> emailCol = new TableColumn<Teacher, String>("Email");
        emailCol.setMinWidth(100);
        emailCol.setCellValueFactory(new PropertyValueFactory<Teacher, String>("email"));
        
        
        emailCol.setCellFactory(TextFieldTableCell.<Teacher>forTableColumn());
        emailCol.setOnEditCommit(        
        		(CellEditEvent<Teacher, String> t) -> {
        			
        			
                       ((Teacher) t.getTableView().getItems().get(
        	                        t.getTablePosition().getRow())
        	                        ).setEmail(t.getNewValue());
                 updateEmail( t.getRowValue().getT_id(),t.getNewValue());
        			
        		});
        TableColumn<Teacher, String> statusCol = new TableColumn<Teacher, String>("Status");
        statusCol.setMinWidth(100);
        statusCol.setCellValueFactory(new PropertyValueFactory<Teacher, String>("status"));
        
        
       statusCol.setCellFactory(TextFieldTableCell.<Teacher>forTableColumn());
       statusCol.setOnEditCommit(        
        		(CellEditEvent<Teacher, String> t) -> {
        			
                       ((Teacher) t.getTableView().getItems().get(
        	                        t.getTablePosition().getRow())
        	                        ).setStatus(t.getNewValue());
                 updateStatus( t.getRowValue().getT_id(),t.getNewValue());
        			
        		});
        
        
        
        
        myDataTable.setItems(dataList);
        
        myDataTable.getColumns().addAll(t_idCol, t_nameCol, DOBCol, salaryCol, startDateCol,emailCol,statusCol);



        final TextField addT_id = new TextField();
        addT_id.setPromptText("T_ID");
        addT_id.setMaxWidth(t_idCol.getPrefWidth());
        addT_id.setStyle("-fx-border-color: brown;-fx-font-weight: bold");
        final ComboBox<String> addStat = new ComboBox<>();
        // Add choices to the ComboBox
        addStat.getItems().addAll("Active", "InActive");

        // Optional: Set a default value
        addStat.setValue("Active");
        addStat.setPrefWidth(100);
        addStat.setPromptText("Status");
        addStat.setStyle("-fx-border-color: brown;-fx-font-weight: bold");
       
        
        final TextField addName = new TextField();
        addName.setMaxWidth(t_nameCol.getPrefWidth());
        addName.setPromptText("Name");
        addName.setStyle("-fx-border-color: brown;-fx-font-weight: bold");
        
        final TextField addDOB = new TextField();
        addDOB.setPrefWidth(100);
        addDOB.setPromptText("DateOfBirth");
        addDOB.setStyle("-fx-border-color: brown;-fx-font-weight: bold");
        final TextField addEmail = new TextField();
        addEmail.setPrefWidth(100);
        addEmail.setPromptText("Email");
        addEmail.setStyle("-fx-border-color: brown;-fx-font-weight: bold");
        
        final TextField addSalary = new TextField();
        addSalary.setMaxWidth(salaryCol.getPrefWidth());
        addSalary.setPromptText("Salary");
        addSalary.setStyle("-fx-border-color: brown;-fx-font-weight: bold");
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
                	Label label2 = new Label("Are You Sure From Date Of Birth Of Teacher\nNote:Once Added, Date Of Birth Couldn't Be Edited");
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
                		Teacher rc;
                		LocalDate currentDate = LocalDate.now();
                		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                		 String SD = currentDate.format(formatter);
                		 String str1=addDOB.getText();
                         String str2=SD;
                         
                         int age = checkAge(str2,str1);
                         if(age == 0)
                         {
                         	Stage Error = new Stage();
                         	Error.setTitle("ERROR");
                         	VBox pane3 = new VBox();
                         	pane3.setAlignment(Pos.CENTER);
                         	Label label21 = new Label("Cannot Add Teacher , Teacher Under Age\n(25)");
                         	Font font2 = Font.font("Arial",FontWeight.BOLD, 15);
                              label21.setFont(font2);
                         	pane3.getChildren().addAll(label21);
                         	Scene scene3 = new Scene(pane3,350,200) ;//add the pane to the scene 
                 			Error.setScene(scene3);//add the scene to the stage
                 			Error.show();//show stage
             				
                         	
                         }
                         else {
                         	if ( Integer.valueOf(addSalary.getText())<2000)
                         	{
                         		Stage Error = new Stage();
                             	Error.setTitle("ERROR");
                             	VBox pane3 = new VBox();
                             	pane3.setAlignment(Pos.CENTER);
                             	Label label21 = new Label("Cannot Add Teacher ,Wage Is Under Limit\n(2000)");
                             	Font font2 = Font.font("Arial",FontWeight.BOLD, 15);
                                 label21.setFont(font2);
                             	pane3.getChildren().addAll(label21);
                             	Scene scene3 = new Scene(pane3,350,200) ;//add the pane to the scene 
                     			Error.setScene(scene3);//add the scene to the stage
                     			Error.show();//show stage
                         	}
                         	else {
                		rc = new Teacher(
                                Integer.valueOf(addT_id.getText()),
                                addName.getText(),
                                addDOB.getText(),
                                Integer.valueOf(addSalary.getText()),
                        		SD,
                        		 addEmail.getText(), addStat.getValue());
                    	dataList.add(rc);
                    	insertData(rc);
                        addT_id.clear();
                        addName.clear();
                        addSalary.clear();
                        addDOB.clear();
                        confirm.close();
                        addEmail.clear();
                        addStat.setValue("Active");
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
        	 ObservableList<Teacher> selectedRows = myDataTable.getSelectionModel().getSelectedItems();
        	ArrayList<Teacher> rows = new ArrayList<>(selectedRows);
        	rows.forEach(row -> {
        		deleteRow(row); 
        		row.setStatus("InActive");
        		myDataTable.refresh();
        		}); 
        	myDataTable.refresh();
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
        		myDataTable.refresh();
        });
        
        	hb.getChildren().addAll(addT_id, addName, addDOB, addSalary,addEmail,addStat);
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
	
	


	public void insertData(Teacher rc) {
		
	try {
			System.out.println("Insert into Teacher (t_name,DOB, salary, start_date,Email,Stat) values("+
						"'"
						+rc.getT_name()+"','"
						+ rc.getDob()+"',"
						+ rc.getSalary() +",'"
						+ rc.getStart_date()+"','" 
						+ rc.getEmail()+"','"
						+ rc.getStatus()+"');");
			
			DataBaseConnector.connectDB();
			ExecuteStatement("Insert into Teacher (t_name,DOB, salary, start_date,Email,Stat) values("+
					"'"
					+rc.getT_name()+"','"
					+ rc.getDob()+"',"
					+ rc.getSalary() +",'"
					+ rc.getStart_date()+"','" 
					+ rc.getEmail()+"','"
					+ rc.getStatus()+"');");
			DataBaseConnector.con.close();
			
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
	}
	
	
	public void getData() throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		
		String SQL;	
		DataBaseConnector.connectDB();
		SQL = "select t_id,t_name,DOB, salary, start_date,Email,Stat from Teacher order by t_id";
		Statement stmt = DataBaseConnector.con.createStatement();
		ResultSet rs = stmt.executeQuery(SQL);


		while ( rs.next() ) 
			data.add(new Teacher(
					Integer.parseInt(rs.getString(1)),
					rs.getString(2),
					rs.getString(3),
					Integer.parseInt(rs.getString(4)),
					rs.getString(5),
					rs.getString(6),
					rs.getString(7)));
		
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
	
	public void updateName(int t_id, String name) {
				
		try {
			DataBaseConnector.connectDB();
			ExecuteStatement("update  Teacher set t_name = '"+name + "' where t_id = "+t_id+";");
			DataBaseConnector.con.close();
			
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
	}
	
	public void updateDob(int t_id, String DOB) {
		
		try {
			DataBaseConnector.connectDB();
			ExecuteStatement("update  Teacher set age = "+DOB + " where t_id = "+t_id+";");
			DataBaseConnector.con.close();
			
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
	}

	
	public void updateSalary(int t_id, int salary) {
		
		try {
			DataBaseConnector.connectDB();
			ExecuteStatement("update  Teacher set salary = "+salary + " where t_id = "+t_id+";");
			DataBaseConnector.con.close();
			
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
	}
	
public void updateDate(int t_id, String start_date) {
		
		try {
			DataBaseConnector.connectDB();
			ExecuteStatement("update  student set Start Date = '"+start_date + "' where snum = "+t_id+";");
			DataBaseConnector.con.close();
			
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
	}
public void updateStatus(int t_id, String stat) {
	
	try {
		DataBaseConnector.connectDB();
		ExecuteStatement("update  Teacher set Stat = '"+stat + "' where t_id = "+t_id+";");
		DataBaseConnector.con.close();
		
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
}
public void updateEmail(int t_id, String Email) {
	
	try {
		DataBaseConnector.connectDB();
		ExecuteStatement("update  Teacher set Email = '"+Email + "' where t_id = "+t_id+";");
		DataBaseConnector.con.close();
		
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
}

public void deleteRow(Teacher row) {
	// TODO Auto-generated method stub
	String stat = new String("InActive");
	try {
		DataBaseConnector.connectDB();
		ExecuteStatement("update  Teacher set Stat = '"+stat + "' where T_id = "+row.getT_id()+";");
		DataBaseConnector.con.close();
		
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
}
	
public void showDialog(Window owner, Modality modality,   TableView<Teacher> table) {
		// Create a Stage with specified owner and modality
		Stage stage = new Stage();
		stage.initOwner(owner);
		stage.initModality(modality);
	//	Label modalityLabel = new Label(modality.toString());
		
		Button yesButton = new Button("Confirm");
		yesButton.setOnAction(e -> {
			for (Teacher row:dataList) {
        			deleteRow(row);
        			row.setStatus("InActive");
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
	
	
	int checkAge  (String SD , String BD) {
		SD=SD.concat(" ");BD=BD.concat(" ");
		int DayS=Integer.valueOf(SD.substring(8,10));
		int DayB=Integer.valueOf(BD.substring(8, 10));
		int MS=Integer.valueOf(SD.substring(5,7));
		int MB=Integer.valueOf(BD.substring(5,7));
		int YS=Integer.valueOf(SD.substring(0, 4));
		int YB=Integer.valueOf(BD.substring(0, 4));
		if(YS-YB > 25)
			return 1;
		else if (YS-YB == 25)
		{
			if(MS>MB)
				return 1;
			else if(MS==MB)
			{
				if(DayS >= DayB)
					return 1;
				else 
					return 0;
				
			}
			else 
				return 0;
			
		}
		else 
			return 0;
		
		
	}
}
