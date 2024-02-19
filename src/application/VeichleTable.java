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

public class VeichleTable extends Application {
	
	public ArrayList<Veichle> data=new ArrayList();
	public ObservableList<Veichle> dataList;
	
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
	    TableView<Veichle> myDataTable = new TableView<Veichle>();
		Scene scene = new Scene(new Group());
       
 
        Label label = new Label("Veichle Table");
        label.setFont(new Font("Verdana", 25));
        label.setTextFill(Color.DARKRED);
        label.setTranslateX(150);
 
        myDataTable.setEditable(true);
        myDataTable.setMaxHeight(400);
        myDataTable.setMaxWidth(500);
 
        
        
        
        // name of column for display
        TableColumn<Veichle, Integer> vidCol = new TableColumn<Veichle, Integer>("VID");
        vidCol.setMinWidth(50);
        // to get the data from specific column
        vidCol.setCellValueFactory(new PropertyValueFactory<Veichle, Integer>("vid"));//t_id is the assigned property in teacher class
        
        TableColumn<Veichle, Integer> vnumCol = new TableColumn<Veichle, Integer>("VNum");
        vnumCol.setMinWidth(50);
        // to get the data from specific column
        vnumCol.setCellValueFactory(new PropertyValueFactory<Veichle, Integer>("vnum"));//t_id is the assigned property in teacher class
   
        TableColumn<Veichle, String> vtypCol = new TableColumn<Veichle, String>("VType");
        vtypCol.setMinWidth(50);
        vtypCol.setCellValueFactory(new PropertyValueFactory<Veichle, String>("vtyp"));//t_name in teacher class
    

        vtypCol.setCellFactory(TextFieldTableCell.<Veichle>forTableColumn());


        vtypCol.setOnEditCommit(
        		(CellEditEvent<Veichle, String> t) -> {
                       ((Veichle) t.getTableView().getItems().get(
        	                        t.getTablePosition().getRow())
        	                        ).setVtyp(t.getNewValue()); //display only
                 updateTyp( t.getRowValue().getVid(),t.getNewValue());
        		});


        TableColumn<Veichle, String> statCol = new TableColumn<Veichle, String>("Status");
        statCol.setMinWidth(50);
        statCol.setCellValueFactory(new PropertyValueFactory<Veichle, String>("stat"));//t_name in teacher class
    

        statCol.setCellFactory(TextFieldTableCell.<Veichle>forTableColumn());


        statCol.setOnEditCommit(
        		(CellEditEvent<Veichle, String> t) -> {
                       ((Veichle) t.getTableView().getItems().get(
        	                        t.getTablePosition().getRow())
        	                        ).setStat(t.getNewValue()); //display only
                 updateStat( t.getRowValue().getVid(),t.getNewValue());
        		});

        
        
        
       
       

        
       
       
       
        
        
        
        myDataTable.setItems(dataList);
        
        myDataTable.getColumns().addAll(vidCol, vnumCol, vtypCol, statCol);



        final TextField addVid = new TextField();
        addVid.setPromptText("VID");
        addVid.setMaxWidth(vidCol.getPrefWidth());
        addVid.setStyle("-fx-border-color: brown;-fx-font-weight: bold");
        
        final TextField addNum = new TextField();
        addNum.setPromptText("Num");
        addNum.setMaxWidth(vidCol.getPrefWidth());
        addNum.setStyle("-fx-border-color: brown;-fx-font-weight: bold");
       
        
        final TextField addTyp = new TextField();
        addTyp.setMaxWidth(vidCol.getPrefWidth());
        addTyp.setPromptText("Type");
        addTyp.setStyle("-fx-border-color: brown;-fx-font-weight: bold");
        
        final ComboBox<String> addStat = new ComboBox<>();
        // Add choices to the ComboBox
        addStat.getItems().addAll("Active", "InActive");

        // Optional: Set a default value
        addStat.setValue("Active");
        addStat.setPrefWidth(100);
        addStat.setPromptText("Status");
        addStat.setStyle("-fx-border-color: brown;-fx-font-weight: bold");
        
        
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
                	Label label2 = new Label("Are You Sure From Information Of Vehicle\nNote:Once Added, Some Info Couldn't Be Edited");
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
                		Veichle rc;
                		
                         
                		rc = new Veichle(
                                Integer.valueOf(addVid.getText()),
                                Integer.valueOf(addNum.getText()),
                                addTyp.getText(),
                                addStat.getValue()
                        		);
                    	dataList.add(rc);
                    	insertData(rc);
                        addVid.clear();
                        addNum.clear();
                        addTyp.clear();
                        addStat.setValue("Active");
                        confirm.close();
                       // addStartDate.clear();
                         	
                         
                	});                	
            		
    
            	
            
        });
 
        final HBox hb = new HBox();

 
        final Button deleteButton = new Button("Delete");
        deleteButton.setStyle("-fx-font-weight: bold;-fx-background-color: brown;");
        deleteButton.setOnMouseEntered(event -> deleteButton.setStyle("-fx-font-weight: bold;-fx-background-color: lightgreen;"));
        deleteButton.setOnMouseExited(event -> deleteButton.setStyle("-fx-font-weight: bold;-fx-background-color: brown;"));
        deleteButton.setStyle("-fx-font-weight: bold;-fx-background-color: brown");
        deleteButton.setOnAction((ActionEvent e) -> {        	 
        	 ObservableList<Veichle> selectedRows = myDataTable.getSelectionModel().getSelectedItems();
        	ArrayList<Veichle> rows = new ArrayList<>(selectedRows);
        	rows.forEach(row -> {
        		deleteRow(row); 
        		row.setStat("InActive");
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
        
        	hb.getChildren().addAll(addVid, addNum, addTyp, addStat);
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
	
	


	public void insertData(Veichle rc) {
		
	try {
			System.out.println("Insert into Veichle (V_Num,V_Typ, Stat) values("+
						""
						+rc.getVnum()+",'"
						+ rc.getVtyp()+"','"
						+ rc.getStat()+"');");
			
			DataBaseConnector.connectDB();
			ExecuteStatement("Insert into Veichle (V_Num,V_Typ, Stat) values("+
					""
					+rc.getVnum()+",'"
					+ rc.getVtyp()+"','"
					+ rc.getStat()+"');");
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
		SQL = "select V_id,V_Num, V_Typ, stat from Veichle order by V_id";
		Statement stmt = DataBaseConnector.con.createStatement();
		ResultSet rs = stmt.executeQuery(SQL);


		while ( rs.next() ) 
			data.add(new Veichle(
					Integer.parseInt(rs.getString(1)),
					Integer.parseInt(rs.getString(2)),
					rs.getString(3),
					rs.getString(4)));
		
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
	
	public void updateTyp(int V_id, String typ) {
				
		try {
			DataBaseConnector.connectDB();
			ExecuteStatement("update  Veichle set V_Typ = '"+typ + "' where V_id = "+V_id+";");
			DataBaseConnector.con.close();
			
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
	}
	
	public void updateStat(int V_id, String stat) {
		
		try {
			DataBaseConnector.connectDB();
			ExecuteStatement("update  Veichle set Stat = '"+stat + "' where V_id = "+V_id+";");
			DataBaseConnector.con.close();
			
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
	}


	public void deleteRow(Veichle row) {
		// TODO Auto-generated method stub
		String stat = new String("InActive");
		try {
			DataBaseConnector.connectDB();
			ExecuteStatement("update  Veichle set Stat = '"+stat + "' where V_id = "+row.getVid()+";");
			DataBaseConnector.con.close();
			
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
	}
	
	public void showDialog(Window owner, Modality modality,   TableView<Veichle> table) {
		// Create a Stage with specified owner and modality
		Stage stage = new Stage();
		stage.initOwner(owner);
		stage.initModality(modality);
	//	Label modalityLabel = new Label(modality.toString());
		
		Button yesButton = new Button("Confirm");
		yesButton.setOnAction(e -> {
			for (Veichle row:dataList) {
        			deleteRow(row);
        			row.setStat("InActive");
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
