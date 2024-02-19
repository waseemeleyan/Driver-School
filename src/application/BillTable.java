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

public class BillTable extends Application {
	public ArrayList<Bill> data=new ArrayList();
	public ObservableList<Bill> dataList;
	
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
	    TableView<Bill> myDataTable = new TableView<Bill>();
		Scene scene = new Scene(new Group());
       
 
        Label label = new Label("Bill Table");
        label.setFont(new Font("Verdana", 25));
        label.setTextFill(Color.DARKRED);
        label.setTranslateX(80);
 
        myDataTable.setEditable(true);
        myDataTable.setMaxHeight(400);
        myDataTable.setMaxWidth(500);
 
        
        
        
        // name of column for display
        TableColumn<Bill, Integer> bidCol = new TableColumn<Bill, Integer>("BID");
        bidCol.setMinWidth(50);
        // to get the data from specific column
        bidCol.setCellValueFactory(new PropertyValueFactory<Bill, Integer>("bid"));//t_id is the assigned property in teacher class
           
        
        TableColumn<Bill, String> dateCol = new TableColumn<Bill, String>("Date");
        dateCol.setMinWidth(100);
        dateCol.setCellValueFactory(new PropertyValueFactory<Bill, String>("date"));//dob in teacher class
       

        //update is under start date update due to both depending on each other 
       

        
        TableColumn<Bill, Integer> lidCol = new TableColumn<Bill, Integer>("LID");
        lidCol.setMinWidth(100);
        lidCol.setCellValueFactory(new PropertyValueFactory<Bill, Integer>("lid"));
        
        
        lidCol.setCellFactory(TextFieldTableCell.<Bill,Integer>forTableColumn(new IntegerStringConverter()));
        lidCol.setOnEditCommit(        
        		(CellEditEvent<Bill, Integer> t) -> {
        			int oldV=t.getOldValue();
        			if(   updateLid( t.getRowValue().getBid(),t.getNewValue())==0)
        			{
        				Stage Error = new Stage();
                    	Error.setTitle("ERROR");
                    	VBox pane2 = new VBox();
                    	pane2.setAlignment(Pos.CENTER);
                    	Label label2 = new Label("Cannot Update ,Please Check L_ID");
                    	Font font = Font.font("Arial",FontWeight.BOLD, 15);
                        label2.setFont(font);
                    	pane2.getChildren().addAll(label2);
                    	Scene scene2 = new Scene(pane2,350,200) ;//add the pane to the scene 
            			Error.setScene(scene2);//add the scene to the stage
            			Error.show();//show stage
        				myDataTable.refresh();//to refresh table view after doing a forbidden edit
        				
        			}
        			else {

                        ((Bill) t.getTableView().getItems().get(
         	                        t.getTablePosition().getRow())
         	                        ).setLid(t.getNewValue());
        			}
        			
        			
             
        			
        		});
              
        myDataTable.setItems(dataList);
        
        myDataTable.getColumns().addAll(bidCol, dateCol, lidCol);


        final TextField addLid = new TextField();
        addLid.setPromptText("LID");
        addLid.setMaxWidth(lidCol.getPrefWidth());
        addLid.setStyle("-fx-border-color: brown;-fx-font-weight: bold");
       
        
        final TextField addBid = new TextField();
        addBid.setPromptText("BID");
        addBid.setMaxWidth(bidCol.getPrefWidth());
        addBid.setStyle("-fx-border-color: brown;-fx-font-weight: bold");
       
        
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
                		Bill rc;
                		LocalDate currentDate = LocalDate.now();
                		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                		 String SD = currentDate.format(formatter);
                	
                       
                        
                         	
                         	
                		rc = new Bill(
                                Integer.valueOf(addBid.getText()),
                                SD,
                                Integer.valueOf(addLid.getText()));
                		if(insertData(rc)==0)
                		{
                			Stage Error = new Stage();
                         	Error.setTitle("ERROR");
                         	VBox pane3 = new VBox();
                         	pane3.setAlignment(Pos.CENTER);
                         	Label label21 = new Label("Cannot Add Bill , Please Check L_ID");
                         	Font font2 = Font.font("Arial",FontWeight.BOLD, 15);
                              label21.setFont(font2);
                         	pane3.getChildren().addAll(label21);
                         	Scene scene3 = new Scene(pane3,350,200) ;//add the pane to the scene 
                 			Error.setScene(scene3);//add the scene to the stage
                 			Error.show();//show stage
                		}
                		else
                    	dataList.add(rc);
                        addBid.clear();
                        addLid.clear();
                        confirm.close();
                       // addStartDate.clear();
                         	
                         
                	});                	
            		
    
            	
            
        });
 
        final HBox hb = new HBox();

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
		
       
        
        	hb.getChildren().addAll(addBid, addLid,addButton,refreshButton);
	        hb.setSpacing(3);
        	
      

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
       // vbox.setAlignment(Pos.CENTER);
        vbox.setTranslateX(100);
        vbox.setTranslateY(20);
        vbox.getChildren().addAll(label, myDataTable, hb);
        StackPane root = new StackPane();
        root.getChildren().add(vbox);
        StackPane.setAlignment(vbox, Pos.CENTER);
	//	vbox.getChildren().addAll(label, myDataTable);
        ((Group) scene.getRoot()).getChildren().addAll(root);
          return scene ;
	}
	
	


	public int insertData(Bill rc) {
		
	try {
			System.out.println("Insert into Bill (B_date,L_id) values("+
						"'"
						+rc.getDate()+"',"
						+ rc.getLid() +");");
			
			DataBaseConnector.connectDB();
			
			try {
				Statement stmt = DataBaseConnector.con.createStatement();
				stmt.executeUpdate("Insert into Bill (B_date,L_id) values("+
						"'"
						+rc.getDate()+"',"
						+ rc.getLid() +");");
				stmt.close();
			
				 
			}
			catch(SQLException s) {
				s.printStackTrace();
				return 0 ;
				  
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
		SQL = "select B_id,B_date,L_id from Bill order by B_id";
		Statement stmt = DataBaseConnector.con.createStatement();
		ResultSet rs = stmt.executeQuery(SQL);


		while ( rs.next() ) 
			data.add(new Bill(
					Integer.parseInt(rs.getString(1)),
					rs.getString(2),
					Integer.parseInt(rs.getString(3))));
		
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
	
	
	
	

	
	public int updateLid(int B_id, int L_id) {
		
		try {
			DataBaseConnector.connectDB();
			try {
				Statement stmt = DataBaseConnector.con.createStatement();
				stmt.executeUpdate("update  B_id set L_id = "+L_id + " where B_id = "+B_id+";");
				stmt.close();
			
				 
			}
			catch(SQLException s) {
				s.printStackTrace();
				return 0 ;
				  
			}
			DataBaseConnector.con.close();
			
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		return 1;
	}
	

	
	
	
	
	

}
