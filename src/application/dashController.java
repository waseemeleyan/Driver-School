package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.converter.DateStringConverter;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.LocalDateStringConverter;

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

public class dashController implements Initializable {
	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;
	private double x = 0;
	private double y = 0;
	@FXML
	private Button addStudent;

	@FXML
	private Label availableCar;

	@FXML
	private Label availableStudent;

	@FXML
	private Label availableTeacher;

	@FXML
	private Button signOut;

	@FXML
	private Button statistic;

	@FXML
	private AnchorPane statistic_form;

	@FXML
	private TableColumn<Student, Integer> studentID_col;

	@FXML
	private TableColumn<Student, Integer> studentLicenseId_col;

	@FXML
	private TextField studentName;

	@FXML
	private TableColumn<Student, String> studentName_col;

	@FXML
	private TextField studentNumber;

	@FXML
	private TableColumn<Student, Integer> studentNumber_col;

	@FXML
	private ComboBox<String> studentStatus;

	@FXML
	private TableColumn<Student, String> studentStaus_col;

	@FXML
	private TableView<Student> studentTable;

	@FXML
	private AnchorPane student_form;

	@FXML
	private Button studentadd;

	@FXML
	private Button studentclear;
	@FXML
	private AnchorPane teacher_form;

	@FXML
	private Button studentdelete;

	@FXML
	private TextField studentlicenseid;

	@FXML
	private TextField studentsearch;
	@FXML
	private Button addTeacherButton;
	@FXML
	private TableColumn<Teacher, String> DOBCol;
	@FXML
	private TableColumn<Teacher, String> emailCol;

	@FXML
	private TableColumn<Teacher, Integer> salaryCol;
	@FXML
	private TableColumn<Teacher, String> startDateCol;
	@FXML
	private TableColumn<Teacher, String> tStatusCol;
	@FXML
	private TableColumn<Teacher, Integer> t_idCol;

	@FXML
	private TableColumn<Teacher, String> t_nameCol;
	@FXML
	private TableView<Teacher> myDataTableT;
	@FXML
	private TextField addDOB;

	@FXML
	private TextField addEmail;

	@FXML
	private TextField addSalary;
	@FXML
	private TextField addTName;

	@FXML
	private ComboBox<String> addTStat;

	@FXML
	private Button taddButton;

	@FXML
	private Button tclearButton;

	@FXML
	private Button tdeleteButton;
	@FXML
	private Button trefreshButton;
	@FXML
	private TableColumn<Veichle, String> vStatCol;

	@FXML
	private TableColumn<Veichle, Integer> vidCol;

	@FXML
	private TableColumn<Veichle, Integer> vnumCol;

	@FXML
	private TableColumn<Veichle, String> vtypCol;
	@FXML
	private TableView<Veichle> myDataTableV;
	@FXML
	private Button addCarButton;
	@FXML
	private AnchorPane car_form;
	@FXML
	private ComboBox<String> addVStat;

	@FXML
	private TextField addVnum;

	@FXML
	private TextField addVtyp;
	@FXML
	private Button vaddButton;

	@FXML
	private Button vclearButton;

	@FXML
	private Button vdeleteButton;
	@FXML
	private Button vrefreshButton;
	@FXML
	private Button addLessonButton;
	@FXML
	private AnchorPane lesson_form;
	@FXML
	private TableView<Lesson> myDataTableL;
	@FXML
	private TableColumn<Lesson, String> l_dateCol;

	@FXML
	private TableColumn<Lesson, Integer> l_idCol;

	@FXML
	private TableColumn<Lesson, Integer> l_sidCol;

	@FXML
	private TableColumn<Lesson, Integer> l_tidCol;

	@FXML
	private TableColumn<Lesson, String> l_timeCol;

	@FXML
	private TableColumn<Lesson, Integer> l_vidCol;
	@FXML
	private TextField addLDate;

	@FXML
	private TextField addLSid;

	@FXML
	private TextField addLTid;

	@FXML
	private TextField addLTime;

	@FXML
	private TextField addLVid;

	@FXML
	private Button laddButton;

	@FXML
	private Button lclearButton;

	@FXML
	private Button ldeleteButton;
	@FXML
	private Button lrefreshButton;
	@FXML
	private TableColumn<Lesson, String> lStatusCol;
	@FXML
	private TableColumn<Bill, String> bDateCol;

	@FXML
	private TableColumn<Bill, Integer> bLidCol;

	@FXML
	private TableColumn<Bill, Integer> bidCol;
	@FXML
	private AnchorPane bill_form;
	@FXML
	private Button addBillButton;
	@FXML
	private TableView<Bill> myDataTableB;
	@FXML
	private TextField addBLid;
	@FXML
	private Button baddButton;
	@FXML
	private Button brefreshButton;
	@FXML
	private Button billLessonButton;
	@FXML
	private TableView<Lesson> billLessonTable;
	@FXML
	private TableColumn<Lesson, Integer> BL2;
	@FXML
	private TableColumn<Lesson, String> LD2;
	@FXML
	private TextField billLessonText;
	@FXML
	private TextField LicenseCost;

	@FXML
	private TableColumn<License, Integer> LicenseID_col;

	@FXML
	private TextField LicenseMinimumNoOfTests;

	@FXML
	private TableView<License> LicenseTable;

	@FXML
	private Button Licenseadd;

	@FXML
	private Button Licenseclear;

	@FXML
	private TableColumn<License, Integer> Licensecost_col;

	@FXML
	private Button Licensedelete;

	@FXML
	private TextField Licenselicensetype;

	@FXML
	private TableColumn<License, Integer> Licenseminimumtest_col;

	@FXML
	private TextField Licensesearch;

	@FXML
	private TableColumn<License, String> Licensetype_col;
	@FXML
	private Button addLicenseButton;
	@FXML
	private AnchorPane license_form;

	@FXML
	private Button addTestButton;

	@FXML
	private TextField TestCarId;

	@FXML
	private TableColumn<Test, Integer> TestID_col;

	@FXML
	private TextField TestNO;

	@FXML
	private TableColumn<Test, Integer> TestNO_col;

	@FXML
	private TextField TestStudentId;

	@FXML
	private TableView<Test> TestTable;

	@FXML
	private Button Testadd;

	@FXML
	private TableColumn<Test, Integer> Testscard_col;

	@FXML
	private TextField Testsearch;

	@FXML
	private TableColumn<Test, Integer> TeststudentId_col;
	@FXML
	private AnchorPane test_form;
	@FXML
	private DatePicker Testdate;
	@FXML
	private TableColumn<Test, String> Testsdate_col1;
	@FXML
	private BarChart<?, ?> cost;
	@FXML
	private TableColumn<Student, LocalDate> StudentdateOfBairthcol11;

	@FXML
	private TableColumn<Student, LocalDate> Studentdate_start_col1;
	@FXML
	private DatePicker studentDateOfBirth;

	@FXML
	private DatePicker studentDateOfstart;

//	    @FXML
//	    private TextField studentsearch;
	public void studentAvlable() {

		DataBaseConnection dp = new DataBaseConnection();
		String SQL = "SELECT COUNT(sid) FROM student WHERE sstatus = 'Active'";
		System.out.println(SQL);

		int countAC = 0;
		try {

			Connection con = dp.getConnection().connectDB();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);

			while (rs.next()) {
				countAC = rs.getInt("COUNT(sid)");
			}

			availableStudent.setText(String.valueOf(countAC));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void teacherAvlable() {

		DataBaseConnection dp = new DataBaseConnection();
		String SQL = "SELECT COUNT(T_id) FROM Teacher WHERE Stat = 'Active'";
		System.out.println(SQL);

		int countAC = 0;
		try {

			Connection con = dp.getConnection().connectDB();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);

			while (rs.next()) {
				countAC = rs.getInt("COUNT(T_id)");
			}

			availableTeacher.setText(String.valueOf(countAC));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void carAvlable() {

		DataBaseConnection dp = new DataBaseConnection();
		String SQL = "SELECT COUNT(V_id) FROM veichle WHERE Stat = 'Active'";
		System.out.println(SQL);

		int countAC = 0;
		try {

			Connection con = dp.getConnection().connectDB();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);

			while (rs.next()) {
				countAC = rs.getInt("COUNT(V_id)");
			}

			availableCar.setText(String.valueOf(countAC));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void close() {
		System.exit(0);
	}

	public void switchForm(ActionEvent event) {

		if (event.getSource() == statistic) {
			statistic_form.setVisible(true);
			student_form.setVisible(false);
			teacher_form.setVisible(false);
			car_form.setVisible(false);
			lesson_form.setVisible(false);
			bill_form.setVisible(false);
			license_form.setVisible(false);
			test_form.setVisible(false);
			addTestButton.setStyle("-fx-background-color:transparent");
			addBillButton.setStyle("-fx-background-color:transparent");
			addCarButton.setStyle("-fx-background-color:transparent");
			statistic.setStyle("-fx-background-color:linear-gradient(to bottom right, #686f86, #8e9296);");
			addStudent.setStyle("-fx-background-color:transparent");
			addLessonButton.setStyle("-fx-background-color:transparent");
			addTeacherButton.setStyle("-fx-background-color:transparent");
			license_form.setStyle("-fx-background-color:transparent");
			studentAvlable();
			teacherAvlable();
			carAvlable();
			homeStudentChart();

		} else if (event.getSource() == addStudent) {
			statistic_form.setVisible(false);
			student_form.setVisible(true);
			teacher_form.setVisible(false);
			car_form.setVisible(false);
			lesson_form.setVisible(false);
			bill_form.setVisible(false);
			license_form.setVisible(false);
			test_form.setVisible(false);
			addTestButton.setStyle("-fx-background-color:transparent");
			addBillButton.setStyle("-fx-background-color:transparent");
			addCarButton.setStyle("-fx-background-color:transparent");
			addStudent.setStyle("-fx-background-color:linear-gradient(to bottom right, #686f86, #8e9296);");
			statistic.setStyle("-fx-background-color:transparent");
			addTeacherButton.setStyle("-fx-background-color:transparent");
			addLessonButton.setStyle("-fx-background-color:transparent");
			license_form.setStyle("-fx-background-color:transparent");
			studentAvlable();
			teacherAvlable();
			carAvlable();
			homeStudentChart();

		} else if (event.getSource() == addTeacherButton) {
			statistic_form.setVisible(false);
			student_form.setVisible(false);
			teacher_form.setVisible(true);
			car_form.setVisible(false);
			lesson_form.setVisible(false);
			bill_form.setVisible(false);
			license_form.setVisible(false);
			test_form.setVisible(false);
			addTestButton.setStyle("-fx-background-color:transparent");
			addBillButton.setStyle("-fx-background-color:transparent");
			addCarButton.setStyle("-fx-background-color:transparent");
			addStudent.setStyle("-fx-background-color:transparent");
			statistic.setStyle("-fx-background-color:transparent");
			addTeacherButton.setStyle("-fx-background-color:linear-gradient(to bottom right, #686f86, #8e9296);");
			addLessonButton.setStyle("-fx-background-color:transparent");
			license_form.setStyle("-fx-background-color:transparent");

			showTeacherView();
			studentAvlable();
			teacherAvlable();
			carAvlable();
			homeStudentChart();

		} else if (event.getSource() == addCarButton) {
			statistic_form.setVisible(false);
			student_form.setVisible(false);
			teacher_form.setVisible(false);
			lesson_form.setVisible(false);
			car_form.setVisible(true);
			bill_form.setVisible(false);
			license_form.setVisible(false);
			test_form.setVisible(false);
			addTestButton.setStyle("-fx-background-color:transparent");
			addBillButton.setStyle("-fx-background-color:transparent");
			addStudent.setStyle("-fx-background-color:transparent");
			statistic.setStyle("-fx-background-color:transparent");
			addTeacherButton.setStyle("-fx-background-color:transparent");
			addCarButton.setStyle("-fx-background-color:linear-gradient(to bottom right, #686f86, #8e9296);");
			addLessonButton.setStyle("-fx-background-color:transparent");
			license_form.setStyle("-fx-background-color:transparent");

			showVeichleView();
			studentAvlable();
			teacherAvlable();
			carAvlable();
			homeStudentChart();

		} else if (event.getSource() == addLessonButton) {
			statistic_form.setVisible(false);
			student_form.setVisible(false);
			teacher_form.setVisible(false);
			car_form.setVisible(false);
			lesson_form.setVisible(true);
			bill_form.setVisible(false);
			license_form.setVisible(false);
			test_form.setVisible(false);
			addTestButton.setStyle("-fx-background-color:transparent");
			addBillButton.setStyle("-fx-background-color:transparent");
			addStudent.setStyle("-fx-background-color:transparent");
			statistic.setStyle("-fx-background-color:transparent");
			addTeacherButton.setStyle("-fx-background-color:transparent");
			addCarButton.setStyle("-fx-background-color:transparent");
			addLessonButton.setStyle("-fx-background-color:linear-gradient(to bottom right, #686f86, #8e9296);");
			license_form.setStyle("-fx-background-color:transparent");

			showLessonView();
			studentAvlable();
			teacherAvlable();
			carAvlable();
			homeStudentChart();

		} else if (event.getSource() == addBillButton) {
			statistic_form.setVisible(false);
			student_form.setVisible(false);
			teacher_form.setVisible(false);
			car_form.setVisible(false);
			lesson_form.setVisible(false);
			bill_form.setVisible(true);
			license_form.setVisible(false);
			test_form.setVisible(false);
			addTestButton.setStyle("-fx-background-color:transparent");
			addBillButton.setStyle("-fx-background-color:linear-gradient(to bottom right, #686f86, #8e9296);");
			addStudent.setStyle("-fx-background-color:transparent");
			statistic.setStyle("-fx-background-color:transparent");
			addTeacherButton.setStyle("-fx-background-color:transparent");
			addCarButton.setStyle("-fx-background-color:transparent");
			addLessonButton.setStyle("-fx-background-color:transparent");
			license_form.setStyle("-fx-background-color:transparent");

			showBillView();
			studentAvlable();
			teacherAvlable();
			carAvlable();
			homeStudentChart();

		} else if (event.getSource() == addLicenseButton) {
			statistic_form.setVisible(false);
			student_form.setVisible(false);
			teacher_form.setVisible(false);
			car_form.setVisible(false);
			lesson_form.setVisible(false);
			bill_form.setVisible(false);
			license_form.setVisible(true);
			test_form.setVisible(false);
			addTestButton.setStyle("-fx-background-color:transparent");
			addBillButton.setStyle("-fx-background-color:transparent");
			addStudent.setStyle("-fx-background-color:transparent");
			statistic.setStyle("-fx-background-color:transparent");
			addTeacherButton.setStyle("-fx-background-color:transparent");
			addCarButton.setStyle("-fx-background-color:transparent");
			addLessonButton.setStyle("-fx-background-color:transparent");
			license_form.setStyle("-fx-background-color:linear-gradient(to bottom right, #686f86, #8e9296);");
			studentAvlable();
			teacherAvlable();
			carAvlable();
			homeStudentChart();

		} else if (event.getSource() == addTestButton) {
			statistic_form.setVisible(false);
			student_form.setVisible(false);
			teacher_form.setVisible(false);
			car_form.setVisible(false);
			lesson_form.setVisible(false);
			bill_form.setVisible(false);
			license_form.setVisible(false);
			test_form.setVisible(true);
			addLicenseButton.setStyle("-fx-background-color:transparent");
			addBillButton.setStyle("-fx-background-color:transparent");
			addStudent.setStyle("-fx-background-color:transparent");
			statistic.setStyle("-fx-background-color:transparent");
			addTeacherButton.setStyle("-fx-background-color:transparent");
			addCarButton.setStyle("-fx-background-color:transparent");
			addLessonButton.setStyle("-fx-background-color:transparent");
			addTestButton.setStyle("-fx-background-color:linear-gradient(to bottom right, #686f86, #8e9296);");
			studentAvlable();
			teacherAvlable();
			carAvlable();
			homeStudentChart();
		}

	}
	// *********************-Get data Student -*********************

	public ObservableList<Student> showStudentView() {

		ObservableList<Student> listData = FXCollections.observableArrayList();

		String SQL = "SELECT * FROM student";
		System.out.println(SQL);
		

		try {
			DataBaseConnection dp = new DataBaseConnection();
			Connection con = dp.getConnection().connectDB();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);

			Student student;

			while (rs.next()) {
				LocalDate datestart = LocalDate.parse(rs.getString(6));
				LocalDate dateofbirth = LocalDate.parse(rs.getString(7));

				student = new Student(Integer.parseInt(rs.getString(1)), rs.getString(2), rs.getString(3),
						Integer.parseInt(rs.getString(4)), Integer.parseInt(rs.getString(5)), datestart, dateofbirth);

				listData.add(student);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listData;
	}

	private ObservableList<Student> availableStudentList;

	public void showStudentViewListData() {
//*********************-Add Table Student View-*********************	    	
		availableStudentList = showStudentView();

		studentID_col.setCellValueFactory(new PropertyValueFactory<>("sid"));

		studentName_col.setCellValueFactory(new PropertyValueFactory<>("sname"));
		studentName_col.setCellFactory(TextFieldTableCell.forTableColumn());

		studentNumber_col.setCellValueFactory(new PropertyValueFactory<>("smobilenumber"));
		studentNumber_col.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

		studentStaus_col.setCellValueFactory(new PropertyValueFactory<>("sstatus"));
		studentStaus_col.setCellFactory(TextFieldTableCell.forTableColumn());

		studentLicenseId_col.setCellValueFactory(new PropertyValueFactory<>("licenseId"));

		Studentdate_start_col1.setCellValueFactory(new PropertyValueFactory<>("date_start"));
		Studentdate_start_col1.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateStringConverter()));

		StudentdateOfBairthcol11.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
		StudentdateOfBairthcol11.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateStringConverter()));

		//name
		studentName_col.setOnEditCommit((CellEditEvent<Student, String> t) -> {

			try {
				((Student) t.getTableView().getItems().get(t.getTablePosition().getRow())).setSname(t.getNewValue());
				DataBaseConnector.connectDB();
				ExecuteStatement("update  student set sname = '" + t.getNewValue() + "' where sid = "
						+ t.getRowValue().getSid() + ";");
				DataBaseConnector.con.close();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		//number
		
		studentNumber_col.setOnEditCommit((CellEditEvent<Student, Integer> t) -> {

			try {
				((Student) t.getTableView().getItems().get(t.getTablePosition().getRow())).setSmobilenumber(t.getNewValue());
				DataBaseConnector.connectDB();
				ExecuteStatement("update  student set smobilenumber = '" + t.getNewValue() + "' where sid = "
						+ t.getRowValue().getSid() + ";");
				DataBaseConnector.con.close();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		//Staus
		studentStaus_col.setOnEditCommit((CellEditEvent<Student, String> t) -> {

			try {
				((Student) t.getTableView().getItems().get(t.getTablePosition().getRow())).setSstatus(t.getNewValue());
				DataBaseConnector.connectDB();
				ExecuteStatement("update  student set sstatus = '" + t.getNewValue() + "' where sid = "
						+ t.getRowValue().getSid() + ";");
				DataBaseConnector.con.close();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		//date_start
		
		Studentdate_start_col1.setOnEditCommit((CellEditEvent<Student, LocalDate> t) -> {

			try {
				((Student) t.getTableView().getItems().get(t.getTablePosition().getRow())).setDate_start(t.getNewValue());
				DataBaseConnector.connectDB();
				ExecuteStatement("update  student set date_start = '" + t.getNewValue() + "' where sid = "
						+ t.getRowValue().getSid() + ";");
				DataBaseConnector.con.close();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});


		studentTable.setItems(availableStudentList);

	}

//*********************-add Insert Button to Student-*********************	 
	public void availableStudentAdd() {

		try {

			if (studentName.getText().isEmpty() || studentNumber.getText().isEmpty()
					|| studentlicenseid.getText().isEmpty()
					|| studentStatus.getSelectionModel().getSelectedItem() == null
					|| studentDateOfstart.getValue() == null || studentDateOfBirth.getValue() == null) {
				showError("Please fill all blank fields");
			} else {
				ButtonType confirmButton = new ButtonType("Confirm");
				ButtonType cancelButton = new ButtonType("Cancel");

				Alert alerts = new Alert(AlertType.CONFIRMATION,
						"Are you sure you want to perform this action \n Note:Once Added, Some Info Couldn't Be Edited?",
						confirmButton, cancelButton);
				alerts.setTitle("Confirmation Dialog");

				alerts.showAndWait().ifPresent(response -> {
					if (response == confirmButton) {
						try {

							DataBaseConnector.connectDB();
							Student student = new Student(studentName.getText(), (String) studentStatus.getValue(),
									Integer.valueOf(studentNumber.getText()),
									Integer.valueOf(studentlicenseid.getText()), studentDateOfstart.getValue(),
									studentDateOfBirth.getValue());

							ExecuteStatement(
									"INSERT INTO student (sname, smobilenumber, sstatus, License_Id, date_start, date_of_birth) VALUES ('"
											+ student.getSname() + "'," + student.getSmobilenumber() + ",'"
											+ student.getSstatus() + "'," + student.getLicenseId() + ",'"
											+ student.getDate_start() + "','" + student.getDateOfBirth() + "' );");
							System.out.println("INSERT INTO student (sname, smobilenumber, sstatus, License_Id, date_start, date_of_birth) VALUES ('"
											+ student.getSname() + "'," + student.getSmobilenumber() + ",'"
										+ student.getSstatus() + "'," + student.getLicenseId() + ",'"
											+ student.getDate_start() + "','" + student.getDateOfBirth() + "' );");

							DataBaseConnector.con.close();
						} catch (ClassNotFoundException e) {
							e.printStackTrace();
							String errorMessage = "Cannot add or update a child row: a foreign key constraint fails. Please check the entered data.";
							showError(errorMessage);

						} catch (MySQLIntegrityConstraintViolationException e) {

							String errorMessage = "Cannot add or update a child row: a foreign key constraint fails. Please check the entered data.";
							showError(errorMessage);
						} catch (SQLException e) {
							e.printStackTrace();
							String errorMessage = "Cannot add or update a child row: a foreign key constraint fails. Please check the entered data.";
							showError(errorMessage);

						}

						showStudentViewListData();
					} else if (response == cancelButton) {

						showInformationAlert("Canceled!");
					}
				});

			}

		} catch (Exception e) {

			String errorMessage = "An unexpected error occurred. Please try again.";
			showError(errorMessage);
		}

	}

	// *********************-add Delete Button to Student-*********************
	public void availableStudentDelete() {
		ObservableList<Student> selectedRows = studentTable.getSelectionModel().getSelectedItems();
		ArrayList<Student> rows = new ArrayList<>(selectedRows);
		rows.forEach(row -> {
			try {

				DataBaseConnector.connectDB();
				ExecuteStatement(
						"update  student set sstatus = '" + "InActive" + "' where sid = " + row.getSid() + ";");
				System.out.println("delet::"+"update  student set sstatus = '" + "InActive" + "' where sid = " + row.getSid() + ";");
				DataBaseConnector.con.close();
				row.setSstatus("InActive");
				myDataTableT.refresh();

			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		showStudentViewListData();
	}

	// *********************-add Delete All Button-*********************
	public void availableStudentDeleteAll() {
		availableStudentList = showStudentView();
		ArrayList<Student> rows = new ArrayList<>(availableStudentList);
		for (Student row : rows) {
			try {

				DataBaseConnector.connectDB();
				ExecuteStatement(
						"update  student set sstatus = '" + "InActive" + "' where sid = " + row.getSid() + ";");
				DataBaseConnector.con.close();
				row.setSstatus("InActive");
				myDataTableT.refresh();
				showStudentViewListData();

			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		showStudentViewListData();
	}
	// *********************-add Delete All Search to Student-*********************

	public void availableStudentSearch() {
		availableStudentList = showStudentView();
		FilteredList<Student> filter = new FilteredList<>(availableStudentList, e -> true);

		studentsearch.textProperty().addListener((Observable, oldValue, newValue) -> {

			filter.setPredicate(predicateCarData -> {

				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				

				String searchKey = newValue.toLowerCase();
				if (String.valueOf(predicateCarData.getSid()).contains(searchKey)) {
					return true;
					
				
				}  

				if (predicateCarData.getSname().toLowerCase().contains(searchKey)) {
					return true;
				} else {
					return false;
				}
			});
		});

		SortedList<Student> sortList = new SortedList<>(filter);

		sortList.comparatorProperty().bind(studentTable.comparatorProperty());
		studentTable.setItems(sortList);

	}

	public void ExecuteStatement(String SQL) throws SQLException {
		Statement stmt = DataBaseConnector.con.createStatement();
		stmt.executeUpdate(SQL);

	}
	// *********************-Get data License -*********************

	public ObservableList<License> showLicensView() {

		ObservableList<License> listData = FXCollections.observableArrayList();

		String SQL = "SELECT * FROM license";
		System.out.println(SQL);

		try {
			DataBaseConnection dp = new DataBaseConnection();
			Connection con = dp.getConnection().connectDB();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);

			License license;

			while (rs.next()) {

				license = new License(Integer.parseInt(rs.getString(1)), rs.getString(2),
						Integer.parseInt(rs.getString(3)), Integer.parseInt(rs.getString(4)));

				listData.add(license);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listData;
	}

	private ObservableList<License> availableLicensetList;

	public void showLicenseViewListData() {
//*********************-Add Table License View-*********************	    	
		availableLicensetList = showLicensView();

		LicenseID_col.setCellValueFactory(new PropertyValueFactory<>("licenseId"));

		Licenseminimumtest_col.setCellValueFactory(new PropertyValueFactory<>("minimumNoOfTests"));
		Licenseminimumtest_col.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

		Licensetype_col.setCellValueFactory(new PropertyValueFactory<>("licenseType"));
		Licensetype_col.setCellFactory(TextFieldTableCell.forTableColumn());

		Licensecost_col.setCellValueFactory(new PropertyValueFactory<>("Cost"));
		Licensecost_col.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

	      


			//minimumtest
		Licenseminimumtest_col.setOnEditCommit((CellEditEvent<License, Integer> t) -> {

				try {
					((License) t.getTableView().getItems().get(t.getTablePosition().getRow())).setMinimumNoOfTests(t.getNewValue());
					DataBaseConnector.connectDB();
					ExecuteStatement("update  License set Minimum_No_Of_Tests = '" + t.getNewValue() + "' where License_Id = "
							+ t.getRowValue().getLicenseId() + ";");
					DataBaseConnector.con.close();
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
		
		//Licensecost
		Licensecost_col.setOnEditCommit((CellEditEvent<License, Integer> t) -> {

				try {
					((License) t.getTableView().getItems().get(t.getTablePosition().getRow())).setCost(t.getNewValue());
					DataBaseConnector.connectDB();
					ExecuteStatement("update  License set Cost = '" + t.getNewValue() + "' where License_Id = "
							+ t.getRowValue().getLicenseId() + ";");
					DataBaseConnector.con.close();
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
		
		//Licensetype
		Licensetype_col.setOnEditCommit((CellEditEvent<License, String> t) -> {

						try {
							((License) t.getTableView().getItems().get(t.getTablePosition().getRow())).setLicenseType(t.getNewValue());
							DataBaseConnector.connectDB();
							ExecuteStatement("update  License set License_Type = '" + t.getNewValue() + "' where License_Id = "
									+ t.getRowValue().getLicenseId() + ";");
							DataBaseConnector.con.close();
						} catch (ClassNotFoundException | SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					});
			
		LicenseTable.setItems(availableLicensetList);

	}

	// *********************-add Insert Button to License-*********************
	public void availableLicenseAdd() {

		try {

			if (Licenselicensetype.getText().isEmpty() || LicenseCost.getText().isEmpty()
					|| LicenseMinimumNoOfTests.getText().isEmpty()) {
				showError("Please fill all blank fields");
			} else {
				ButtonType confirmButton = new ButtonType("Confirm");
				ButtonType cancelButton = new ButtonType("Cancel");

				Alert alerts = new Alert(AlertType.CONFIRMATION,
						"Are you sure you want to perform this action \n Note:Once Added, Some Info Couldn't Be Edited?",
						confirmButton, cancelButton);
				alerts.setTitle("Confirmation Dialog");

				alerts.showAndWait().ifPresent(response -> {
					if (response == confirmButton) {
						try {

							DataBaseConnector.connectDB();
							License student = new License(Integer.valueOf(LicenseMinimumNoOfTests.getText()),
									Licenselicensetype.getText(), Integer.valueOf(LicenseCost.getText()));

							ExecuteStatement("INSERT INTO license (License_Type, Cost, Minimum_No_Of_Tests) VALUES ('"
									+ student.getLicenseType() + "'," + student.getCost() + ",'"
									+ student.getMinimumNoOfTests() + "');");

							DataBaseConnector.con.close();
						} catch (ClassNotFoundException e) {
							String errorMessage = "Cannot add or update a child row: a foreign key constraint fails. Please check the entered data.";
							showError(errorMessage);

						} catch (MySQLIntegrityConstraintViolationException e) {
							String errorMessage = "Cannot add or update a child row: a foreign key constraint fails. Please check the entered data.";
							showError(errorMessage);
						} catch (SQLException e) {
							e.printStackTrace();
							String errorMessage = "Cannot add or update a child row: a foreign key constraint fails. Please check the entered data.";
							showError(errorMessage);

						}

						showLicenseViewListData();
					} else if (response == cancelButton) {

						showInformationAlert("Canceled!");
					}
				});

			}

		} catch (Exception e) {

			String errorMessage = "An unexpected error occurred. Please try again.";
			showError(errorMessage);
		}

	}
	// *********************-add Delete All Search to License-*********************

	public void availableLicenseSearch() {
		availableStudentList = showStudentView();
		FilteredList<License> filter = new FilteredList<>(availableLicensetList, e -> true);

		Licensesearch.textProperty().addListener((Observable, oldValue, newValue) -> {

			filter.setPredicate(predicateCarData -> {

				if (newValue == null || newValue.isEmpty()) {
					return true;
				}

				String searchKey = newValue.toLowerCase();
				if (String.valueOf(predicateCarData.getLicenseId()).contains(searchKey)) {
					return true;
				}  
				if (predicateCarData.getLicenseType().toLowerCase().contains(searchKey)) {
					return true;
				} else {
					return false;
				}
			});
		});

		SortedList<License> sortList = new SortedList<>(filter);

		sortList.comparatorProperty().bind(LicenseTable.comparatorProperty());
		LicenseTable.setItems(sortList);

	}
	// *********************-Get data Test -*********************

	public ObservableList<Test> showTestView() {

		ObservableList<Test> listData = FXCollections.observableArrayList();

		String SQL = "SELECT * FROM test";
		System.out.println(SQL);

		try {
			DataBaseConnection dp = new DataBaseConnection();
			Connection con = dp.getConnection().connectDB();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);

			Test test;

			while (rs.next()) {
				LocalDate datePicker = LocalDate.parse(rs.getString(5));

				test = new Test(Integer.parseInt(rs.getString(1)), Integer.parseInt(rs.getString(2)),
						Integer.parseInt(rs.getString(3)), Integer.parseInt(rs.getString(4)), datePicker);

				listData.add(test);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listData;
	}

	private ObservableList<Test> availableTestList;

	// *********************-Add Table Test View-*********************
	public void showTestViewListData() {
		availableTestList = showTestView();

		TestID_col.setCellValueFactory(new PropertyValueFactory<>("TestID"));

		TestNO_col.setCellValueFactory(new PropertyValueFactory<>("TestNO"));
		TestNO_col.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

		TeststudentId_col.setCellValueFactory(new PropertyValueFactory<>("sid"));
		TeststudentId_col.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

		Testscard_col.setCellValueFactory(new PropertyValueFactory<>("vid"));
		Testscard_col.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

		Testsdate_col1.setCellValueFactory(new PropertyValueFactory<>("testDate"));
		//TestNO
		TestNO_col.setOnEditCommit((CellEditEvent<Test, Integer> t) -> {

						try {
							((Test) t.getTableView().getItems().get(t.getTablePosition().getRow())).setTestNO(t.getNewValue());
							DataBaseConnector.connectDB();
							ExecuteStatement("update  Test set Test_No = '" + t.getNewValue() + "' where Test_ID = "
									+ t.getRowValue().getTestID() + ";");
							DataBaseConnector.con.close();
						} catch (ClassNotFoundException | SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					});
		TestTable.setItems(availableTestList);

	}

	// *********************-add Insert Button to Test-*********************
	public void availableTestAdd() {

		try {

			String statuscheck = "select sstatus , stat from student , veichle WHERE sid=" + TestStudentId.getText()
					+ "&& V_id=" + TestCarId.getText() + ";";
			System.out.println(statuscheck);
			DataBaseConnection dp = new DataBaseConnection();
			Connection con = dp.getConnection().connectDB();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(statuscheck);
			if (rs.next()) {

				if (TestCarId.getText().isEmpty() || TestNO.getText().isEmpty() || TestStudentId.getText().isEmpty()
						|| Testdate.getValue() == null) {

					showError("Please fill all blank fields");
				} else if (rs.getString(2).equals("InActive")) {
					showError("veichle with ID " + TestCarId.getText() + " does not exist.");

				} else if (rs.getString(1).equals("InActive")) {

					showError("Student with ID " + TestStudentId.getText() + " does not exist.");
				}

				else {
					ButtonType confirmButton = new ButtonType("Confirm");
					ButtonType cancelButton = new ButtonType("Cancel");

					Alert alerts = new Alert(AlertType.CONFIRMATION,
							"Are you sure you want to perform this action \n Note:Once Added, Some Info Couldn't Be Edited?",
							confirmButton, cancelButton);
					alerts.setTitle("Confirmation Dialog");

					alerts.showAndWait().ifPresent(response -> {
						if (response == confirmButton) {
							try {

								DataBaseConnector.connectDB();
								Test test = new Test(Integer.valueOf(TestNO.getText()),
										Integer.valueOf(TestStudentId.getText()), Integer.valueOf(TestCarId.getText()),
										Testdate.getValue());

								ExecuteStatement("INSERT INTO test (Test_No, sid, V_id,test_date) VALUES ("
										+ test.getTestNO() + "," + test.getSid() + "," + test.getVid() + ",'"
										+ java.sql.Date.valueOf(test.getTestDate()) + "');");

								DataBaseConnector.con.close();
							} catch (ClassNotFoundException e) {
								String errorMessage = "Cannot add or update a child row: a foreign key constraint fails. Please check the entered data.";
								showError(errorMessage);

							} catch (MySQLIntegrityConstraintViolationException e) {
								String errorMessage = "Cannot add or update a child row: a foreign key constraint fails. Please check the entered data.";
								showError(errorMessage);
							} catch (SQLException e) {
								e.printStackTrace();
								String errorMessage = "Cannot add or update a child row: a foreign key constraint fails. Please check the entered data.";
								showError(errorMessage);

							}

							showTestViewListData();
						} else if (response == cancelButton) {

							showInformationAlert("Canceled!");
						}

					});

				}
			} else {
				showError("Student with ID " + TestStudentId.getText() + " does not exist.");
			}

			rs.close();
			stmt.close();
			con.close();

		} catch (Exception e) {
			e.printStackTrace();

			String errorMessage = "An unexpected error occurred. Please try again.";
			showError(errorMessage);
		}

	}

	// *********************-add Delete All Search to Test-*********************

	public void availableTestSearch() {
		availableStudentList = showStudentView();
		FilteredList<Test> filter = new FilteredList<>(availableTestList, e -> true);

		Testsearch.textProperty().addListener((Observable, oldValue, newValue) -> {

			filter.setPredicate(predicateCarData -> {
				String searchKey = newValue.toLowerCase();

				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				  if (String.valueOf(predicateCarData.getTestID()).contains(searchKey)) {
						return true;
					}

				if (predicateCarData.getTestDate().toString().contains(searchKey)) {
					return true;
				} if (String.valueOf(predicateCarData.getSid()).contains(searchKey)) {
					return true;
					
				
				}  
				 if (String.valueOf(predicateCarData.getTestNO()).contains(searchKey)) {
					return true;

				}
				else {
					return false;
				}
			});
		});

		SortedList<Test> sortList = new SortedList<>(filter);

		sortList.comparatorProperty().bind(TestTable.comparatorProperty());
		TestTable.setItems(sortList);

	}

	private Connection connect;
	private PreparedStatement prepare;
	private ResultSet result;
	private Statement statement;

	public void homeStudentChart() {
		cost.getData().clear();

		String sql = "SELECT YEAR(date_start) AS Student_Year, COUNT(sid) AS Number_of_Students\r\n" + "FROM student\r\n"
				+ "GROUP BY Student_Year\r\n" + "ORDER BY Student_Year ASC;";
		System.out.println(sql);

		DataBaseConnection dp = new DataBaseConnection();
		Connection con;
		try {
			con = dp.getConnection().connectDB();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			XYChart.Series chart = new XYChart.Series();
			while (rs.next()) {
				chart.getData().add(new XYChart.Data(rs.getString("Student_Year"), rs.getInt("Number_of_Students")));
			}

			cost.getData().add(chart);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	///////////////////////////////////////////////// Abed Work
	///////////////////////////////////////////////// ////////////////////////////////////////////////////////////////////
	public void showTeacherView() {

		// Optional: Set a default value
		addTStat.setValue("Active");
		TeacherTable teacher = new TeacherTable();
		try {
			teacher.getData();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		teacher.dataList = FXCollections.observableArrayList(teacher.data);
		t_idCol.setCellValueFactory(new PropertyValueFactory<Teacher, Integer>("t_id"));// t_id is the assigned property
																						// in teacher class
		t_nameCol.setCellValueFactory(new PropertyValueFactory<Teacher, String>("t_name"));// t_name in teacher class

		t_nameCol.setCellFactory(TextFieldTableCell.<Teacher>forTableColumn());

		t_nameCol.setOnEditCommit((CellEditEvent<Teacher, String> t) -> {
			((Teacher) t.getTableView().getItems().get(t.getTablePosition().getRow())).setT_name(t.getNewValue()); // display
																													// only
			teacher.updateName(t.getRowValue().getT_id(), t.getNewValue());
		});
		DOBCol.setCellValueFactory(new PropertyValueFactory<Teacher, String>("dob"));// dob in teacher class
		salaryCol.setCellValueFactory(new PropertyValueFactory<Teacher, Integer>("salary"));

		salaryCol.setCellFactory(TextFieldTableCell.<Teacher, Integer>forTableColumn(new IntegerStringConverter()));
		salaryCol.setOnEditCommit((CellEditEvent<Teacher, Integer> t) -> {
			int oldV = t.getOldValue();

			if (t.getNewValue() < 2000) {
				Stage Error = new Stage();
				Error.setTitle("ERROR");
				VBox pane2 = new VBox();
				pane2.setAlignment(Pos.CENTER);
				Label label2 = new Label("Cannot Add Teacher ,Wage Is Under Limit\n(2000)");
				Font font = Font.font("Arial", FontWeight.BOLD, 15);
				label2.setFont(font);
				pane2.getChildren().addAll(label2);
				Scene scene2 = new Scene(pane2, 350, 200);// add the pane to the scene
				Error.setScene(scene2);// add the scene to the stage
				Error.show();// show stage
				((Teacher) t.getTableView().getItems().get(t.getTablePosition().getRow())).setSalary(oldV);
				myDataTableT.refresh();// to refresh table view after doing a forbidden edit
			} else {

				((Teacher) t.getTableView().getItems().get(t.getTablePosition().getRow())).setSalary(t.getNewValue());
				teacher.updateSalary(t.getRowValue().getT_id(), t.getNewValue());
			}
		});
		startDateCol.setCellValueFactory(new PropertyValueFactory<Teacher, String>("start_date"));
		emailCol.setCellValueFactory(new PropertyValueFactory<Teacher, String>("email"));

		emailCol.setCellFactory(TextFieldTableCell.<Teacher>forTableColumn());
		emailCol.setOnEditCommit((CellEditEvent<Teacher, String> t) -> {

			((Teacher) t.getTableView().getItems().get(t.getTablePosition().getRow())).setEmail(t.getNewValue());
			teacher.updateEmail(t.getRowValue().getT_id(), t.getNewValue());

		});
		tStatusCol.setCellValueFactory(new PropertyValueFactory<Teacher, String>("status"));

		tStatusCol.setCellFactory(TextFieldTableCell.<Teacher>forTableColumn());
		tStatusCol.setOnEditCommit((CellEditEvent<Teacher, String> t) -> {

			
			((Teacher) t.getTableView().getItems().get(t.getTablePosition().getRow())).setStatus(t.getNewValue());
			teacher.updateStatus(t.getRowValue().getT_id(), t.getNewValue());

		});
		myDataTableT.setItems(teacher.dataList);

		taddButton.setOnAction((ActionEvent e) -> {

			Stage confirm = new Stage();
			confirm.setTitle("Confirm");
			VBox pane2 = new VBox();
			pane2.setAlignment(Pos.CENTER);
			Label label2 = new Label(
					"Are You Sure From Date Of Birth Of Teacher\nNote:Once Added, Date Of Birth Couldn't Be Edited");
			Font font = Font.font("Arial", FontWeight.BOLD, 15);
			label2.setFont(font);
			final Button confirmD = new Button("CONFIRM");
			confirmD.setStyle("-fx-font-weight: bold;-fx-background-color: brown;");
			confirmD.setOnMouseEntered(
					event -> confirmD.setStyle("-fx-font-weight: bold;-fx-background-color: lightblue;"));
			confirmD.setOnMouseExited(event -> confirmD.setStyle("-fx-font-weight: bold;-fx-background-color: brown;"));
			confirmD.setStyle("-fx-font-weight: bold;-fx-background-color: brown");
			pane2.getChildren().addAll(label2, confirmD);
			Scene scene2 = new Scene(pane2, 450, 300);// add the pane to the scene
			confirm.setScene(scene2);// add the scene to the stage
			confirm.show();// show stage
			confirmD.setOnAction((ActionEvent o) -> {
				Teacher rc;
				LocalDate currentDate = LocalDate.now();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				String SD = currentDate.format(formatter);
				String str1 = addDOB.getText();
				String str2 = SD;

				int age = teacher.checkAge(str2, str1);
				if (age == 0) {
					Stage Error = new Stage();
					Error.setTitle("ERROR");
					VBox pane3 = new VBox();
					pane3.setAlignment(Pos.CENTER);
					Label label21 = new Label("Cannot Add Teacher , Teacher Under Age\n(25)");
					Font font2 = Font.font("Arial", FontWeight.BOLD, 15);
					label21.setFont(font2);
					pane3.getChildren().addAll(label21);
					Scene scene3 = new Scene(pane3, 350, 200);// add the pane to the scene
					Error.setScene(scene3);// add the scene to the stage
					Error.show();// show stage

				} else {
					if (Integer.valueOf(addSalary.getText()) < 2000) {
						Stage Error = new Stage();
						Error.setTitle("ERROR");
						VBox pane3 = new VBox();
						pane3.setAlignment(Pos.CENTER);
						Label label21 = new Label("Cannot Add Teacher ,Wage Is Under Limit\n(2000)");
						Font font2 = Font.font("Arial", FontWeight.BOLD, 15);
						label21.setFont(font2);
						pane3.getChildren().addAll(label21);
						Scene scene3 = new Scene(pane3, 350, 200);// add the pane to the scene
						Error.setScene(scene3);// add the scene to the stage
						Error.show();// show stage
					} else {
						rc = new Teacher(0, addTName.getText(), addDOB.getText(), Integer.valueOf(addSalary.getText()),
								SD, addEmail.getText(), addTStat.getValue());
						teacher.dataList.add(rc);
						teacher.insertData(rc);
						try {
							teacher.data.clear();
							teacher.getData();
						} catch (ClassNotFoundException p) {
							// TODO Auto-generated catch block
							p.printStackTrace();
						} catch (SQLException p) {
							// TODO Auto-generated catch block
							p.printStackTrace();
						}
						teacher.dataList.clear();
						teacher.dataList = FXCollections.observableArrayList(teacher.data);
						myDataTableT.setItems(teacher.dataList);
						myDataTableT.refresh();
						addTName.clear();
						addSalary.clear();
						addDOB.clear();
						confirm.close();
						addEmail.clear();
						addTStat.setValue("Active");
						// addStartDate.clear();
					}
				}
			});

		});

		tdeleteButton.setOnAction((ActionEvent e) -> {
			ObservableList<Teacher> selectedRows = myDataTableT.getSelectionModel().getSelectedItems();
			ArrayList<Teacher> rows = new ArrayList<>(selectedRows);
			rows.forEach(row -> {
				teacher.deleteRow(row);
				row.setStatus("InActive");
				myDataTableT.refresh();
			});
			myDataTableT.refresh();
		});
		trefreshButton.setOnAction((ActionEvent e) -> {
			myDataTableT.refresh();
		});
		tclearButton.setOnAction((ActionEvent e) -> {
			Stage stage = new Stage();
			teacher.showDialog(stage, NONE, myDataTableT);
			myDataTableT.refresh();
		});

	}

	public void showVeichleView() {

		// Optional: Set a default value
		addVStat.setValue("Active");
		VeichleTable veichle = new VeichleTable();
		try {
			veichle.getData();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		veichle.dataList = FXCollections.observableArrayList(veichle.data);
		vidCol.setCellValueFactory(new PropertyValueFactory<Veichle, Integer>("vid"));// t_id is the assigned property
																						// in teacher class
		vnumCol.setCellValueFactory(new PropertyValueFactory<Veichle, Integer>("vnum"));// t_id is the assigned property
																						// in teacher class
		vtypCol.setCellValueFactory(new PropertyValueFactory<Veichle, String>("vtyp"));// t_name in teacher class
		vtypCol.setCellFactory(TextFieldTableCell.<Veichle>forTableColumn());

		vtypCol.setOnEditCommit((CellEditEvent<Veichle, String> t) -> {
			((Veichle) t.getTableView().getItems().get(t.getTablePosition().getRow())).setVtyp(t.getNewValue()); // display
																													// only
			veichle.updateTyp(t.getRowValue().getVid(), t.getNewValue());
		});
		vStatCol.setCellValueFactory(new PropertyValueFactory<Veichle, String>("stat"));// t_name in teacher class

		vStatCol.setCellFactory(TextFieldTableCell.<Veichle>forTableColumn());

		vStatCol.setOnEditCommit((CellEditEvent<Veichle, String> t) -> {
			((Veichle) t.getTableView().getItems().get(t.getTablePosition().getRow())).setStat(t.getNewValue()); // display
																													// only
			veichle.updateStat(t.getRowValue().getVid(), t.getNewValue());
		});
		myDataTableV.setItems(veichle.dataList);
		vaddButton.setOnAction((ActionEvent e) -> {

			Stage confirm = new Stage();
			confirm.setTitle("Confirm");
			VBox pane2 = new VBox();
			pane2.setAlignment(Pos.CENTER);
			Label label2 = new Label(
					"Are You Sure From Information Of Vehicle\nNote:Once Added, Some Info Couldn't Be Edited");
			Font font = Font.font("Arial", FontWeight.BOLD, 15);
			label2.setFont(font);
			final Button confirmD = new Button("CONFIRM");
			confirmD.setStyle("-fx-font-weight: bold;-fx-background-color: brown;");
			confirmD.setOnMouseEntered(
					event -> confirmD.setStyle("-fx-font-weight: bold;-fx-background-color: lightblue;"));
			confirmD.setOnMouseExited(event -> confirmD.setStyle("-fx-font-weight: bold;-fx-background-color: brown;"));
			confirmD.setStyle("-fx-font-weight: bold;-fx-background-color: brown");
			pane2.getChildren().addAll(label2, confirmD);
			Scene scene2 = new Scene(pane2, 450, 300);// add the pane to the scene
			confirm.setScene(scene2);// add the scene to the stage
			confirm.show();// show stage
			confirmD.setOnAction((ActionEvent o) -> {
				Veichle rc;

				rc = new Veichle(0, Integer.valueOf(addVnum.getText()), addVtyp.getText(), addVStat.getValue());
				veichle.dataList.add(rc);
				veichle.insertData(rc);
				veichle.dataList.add(rc);
				try {
					veichle.data.clear();
					veichle.getData();
				} catch (ClassNotFoundException p) {
					// TODO Auto-generated catch block
					p.printStackTrace();
				} catch (SQLException p) {
					// TODO Auto-generated catch block
					p.printStackTrace();
				}
				veichle.dataList.clear();
				veichle.dataList = FXCollections.observableArrayList(veichle.data);
				myDataTableV.setItems(veichle.dataList);
				myDataTableV.refresh();
				addVnum.clear();
				addVtyp.clear();
				addVStat.setValue("Active");
				confirm.close();
				// addStartDate.clear();

			});

		});

		vdeleteButton.setOnAction((ActionEvent e) -> {
			ObservableList<Veichle> selectedRows = myDataTableV.getSelectionModel().getSelectedItems();
			ArrayList<Veichle> rows = new ArrayList<>(selectedRows);
			rows.forEach(row -> {
				veichle.deleteRow(row);
				row.setStat("InActive");
				myDataTableV.refresh();
			});
			myDataTableV.refresh();
		});
		vrefreshButton.setOnAction((ActionEvent e) -> {
			myDataTableV.refresh();
		});
		vclearButton.setOnAction((ActionEvent e) -> {
			Stage stage = new Stage();
			veichle.showDialog(stage, NONE, myDataTableV);
			myDataTableV.refresh();
		});

	}

	public void showLessonView() {
		LessonTable lesson = new LessonTable();
		try {
			lesson.getData();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lesson.dataList = FXCollections.observableArrayList(lesson.data);

		l_idCol.setCellValueFactory(new PropertyValueFactory<Lesson, Integer>("l_id"));// t_id is the assigned property
																						// in teacher class
		l_timeCol.setCellValueFactory(new PropertyValueFactory<Lesson, String>("time"));// t_name in teacher class

		l_timeCol.setCellFactory(TextFieldTableCell.<Lesson>forTableColumn());

		l_timeCol.setOnEditCommit((CellEditEvent<Lesson, String> t) -> {
			((Lesson) t.getTableView().getItems().get(t.getTablePosition().getRow())).setTime(t.getNewValue()); // display
																												// only
			lesson.updateTime(t.getRowValue().getL_id(), t.getNewValue());
		});

		l_dateCol.setCellValueFactory(new PropertyValueFactory<Lesson, String>("date"));// dob in teacher class

		l_dateCol.setCellFactory(TextFieldTableCell.<Lesson>forTableColumn());

		l_dateCol.setOnEditCommit((CellEditEvent<Lesson, String> t) -> {
			((Lesson) t.getTableView().getItems().get(t.getTablePosition().getRow())).setDate(t.getNewValue()); // display
																												// only
			lesson.updateDate(t.getRowValue().getL_id(), t.getNewValue());
		});
		l_sidCol.setCellValueFactory(new PropertyValueFactory<Lesson, Integer>("s_id"));

		l_sidCol.setCellFactory(TextFieldTableCell.<Lesson, Integer>forTableColumn(new IntegerStringConverter()));
		l_sidCol.setOnEditCommit((CellEditEvent<Lesson, Integer> t) -> {
			if (lesson.updateS_id(t.getRowValue().getL_id(), t.getNewValue()) == 0) {
				Stage Error = new Stage();
				Error.setTitle("ERROR");
				VBox pane2 = new VBox();
				pane2.setAlignment(Pos.CENTER);
				Label label2 = new Label("Cannot Update ,Please Check S_ID");
				Font font = Font.font("Arial", FontWeight.BOLD, 15);
				label2.setFont(font);
				pane2.getChildren().addAll(label2);
				Scene scene2 = new Scene(pane2, 350, 200);// add the pane to the scene
				Error.setScene(scene2);// add the scene to the stage
				Error.show();// show stage
				myDataTableL.refresh();// to refresh table view after doing a forbidden edit

			} else {
				((Lesson) t.getTableView().getItems().get(t.getTablePosition().getRow())).setS_id(t.getNewValue()); // display
																													// only
			}

		});

		l_tidCol.setCellValueFactory(new PropertyValueFactory<Lesson, Integer>("t_id"));

		l_tidCol.setCellFactory(TextFieldTableCell.<Lesson, Integer>forTableColumn(new IntegerStringConverter()));
		l_tidCol.setOnEditCommit((CellEditEvent<Lesson, Integer> t) -> {
			if (lesson.updateT_id(t.getRowValue().getL_id(), t.getNewValue()) == 0) {
				Stage Error = new Stage();
				Error.setTitle("ERROR");
				VBox pane2 = new VBox();
				pane2.setAlignment(Pos.CENTER);
				Label label2 = new Label("Cannot Update ,Please Check T_ID");
				Font font = Font.font("Arial", FontWeight.BOLD, 15);
				label2.setFont(font);
				pane2.getChildren().addAll(label2);
				Scene scene2 = new Scene(pane2, 350, 200);// add the pane to the scene
				Error.setScene(scene2);// add the scene to the stage
				Error.show();// show stage
				myDataTableL.refresh();// to refresh table view after doing a forbidden edit

			} else {
				((Lesson) t.getTableView().getItems().get(t.getTablePosition().getRow())).setS_id(t.getNewValue()); // display
																													// only
			}
		});
		l_vidCol.setCellValueFactory(new PropertyValueFactory<Lesson, Integer>("v_id"));
		lStatusCol.setCellValueFactory(new PropertyValueFactory<Lesson, String>("status"));
		lStatusCol.setCellFactory(TextFieldTableCell.<Lesson>forTableColumn());

		lStatusCol.setOnEditCommit((CellEditEvent<Lesson, String> t) -> {
			((Lesson) t.getTableView().getItems().get(t.getTablePosition().getRow())).setStatus(t.getNewValue()); // display
																													// only
			lesson.updateStatus(t.getRowValue().getL_id(), t.getNewValue());
		});

		l_vidCol.setCellFactory(TextFieldTableCell.<Lesson, Integer>forTableColumn(new IntegerStringConverter()));
		l_vidCol.setOnEditCommit((CellEditEvent<Lesson, Integer> t) -> {
			if (lesson.updateV_id(t.getRowValue().getL_id(), t.getNewValue()) == 0) {
				Stage Error = new Stage();
				Error.setTitle("ERROR");
				VBox pane2 = new VBox();
				pane2.setAlignment(Pos.CENTER);
				Label label2 = new Label("Cannot Update ,Please Check V_ID");
				Font font = Font.font("Arial", FontWeight.BOLD, 15);
				label2.setFont(font);
				pane2.getChildren().addAll(label2);
				Scene scene2 = new Scene(pane2, 350, 200);// add the pane to the scene
				Error.setScene(scene2);// add the scene to the stage
				Error.show();// show stage
				myDataTableL.refresh();// to refresh table view after doing a forbidden edit

			} else {
				((Lesson) t.getTableView().getItems().get(t.getTablePosition().getRow())).setS_id(t.getNewValue()); // display
																													// only
			}

		});
		myDataTableL.setItems(lesson.dataList);
		laddButton.setOnAction((ActionEvent e) -> {

			Stage confirm = new Stage();
			confirm.setTitle("Confirm");
			VBox pane2 = new VBox();
			pane2.setAlignment(Pos.CENTER);
			Label label2 = new Label(
					"Are You Sure From Information Of Lesson?\nNote:Once Added, Some Info Couldn't Be Edited");
			Font font = Font.font("Arial", FontWeight.BOLD, 15);
			label2.setFont(font);
			final Button confirmD = new Button("CONFIRM");
			confirmD.setStyle("-fx-font-weight: bold;-fx-background-color: brown;");
			confirmD.setOnMouseEntered(
					event -> confirmD.setStyle("-fx-font-weight: bold;-fx-background-color: lightblue;"));
			confirmD.setOnMouseExited(event -> confirmD.setStyle("-fx-font-weight: bold;-fx-background-color: brown;"));
			confirmD.setStyle("-fx-font-weight: bold;-fx-background-color: brown");
			pane2.getChildren().addAll(label2, confirmD);
			Scene scene2 = new Scene(pane2, 450, 300);// add the pane to the scene
			confirm.setScene(scene2);// add the scene to the stage
			confirm.show();// show stage
			confirmD.setOnAction((ActionEvent o) -> {
				Lesson rc;

				String str1 = addLTime.getText();
				String str2 = "20:00:00";

				LocalTime time1 = LocalTime.parse(str1);
				LocalTime time2 = LocalTime.parse(str2);
				int compare = time1.compareTo(time2);
				if (compare > 0) {
					Stage Error = new Stage();
					Error.setTitle("ERROR");
					VBox pane3 = new VBox();
					pane3.setAlignment(Pos.CENTER);
					Label label21 = new Label("Cannot Add Lesson , Lesson Time Is Late\n(After 8 PM)");
					Font font2 = Font.font("Arial", FontWeight.BOLD, 15);
					label21.setFont(font2);
					pane3.getChildren().addAll(label21);
					Scene scene3 = new Scene(pane3, 350, 200);// add the pane to the scene
					Error.setScene(scene3);// add the scene to the stage
					Error.show();// show stage

				}

				else {
					rc = new Lesson(0, addLTime.getText(), addLDate.getText(), Integer.valueOf(addLSid.getText()),
							Integer.valueOf(addLTid.getText()), Integer.valueOf(addLVid.getText()), "");
					if (lesson.insertData(rc) == 0) {
						Stage Error = new Stage();
						Error.setTitle("ERROR");
						VBox pane3 = new VBox();
						pane3.setAlignment(Pos.CENTER);
						Label label21 = new Label("Cannot Add Lesson , Please Check L_ID , S_ID,V_ID");
						Font font2 = Font.font("Arial", FontWeight.BOLD, 15);
						label21.setFont(font2);
						pane3.getChildren().addAll(label21);
						Scene scene3 = new Scene(pane3, 350, 200);// add the pane to the scene
						Error.setScene(scene3);// add the scene to the stage
						Error.show();// show stage
					} else {
						lesson.dataList.add(rc);
						try {
							lesson.data.clear();
							lesson.getData();
						} catch (ClassNotFoundException p) {
							// TODO Auto-generated catch block
							p.printStackTrace();
						} catch (SQLException p) {
							// TODO Auto-generated catch block
							p.printStackTrace();
						}
						lesson.dataList.clear();
						lesson.dataList = FXCollections.observableArrayList(lesson.data);
						myDataTableL.setItems(lesson.dataList);
						myDataTableL.refresh();
						addLTid.clear();
						addLTime.clear();
						addLDate.clear();
						addLSid.clear();
						addLVid.clear();
						confirm.close();
						// addStartDate.clear();
					}
				}

			});

		});

		ldeleteButton.setOnAction((ActionEvent e) -> {
			ObservableList<Lesson> selectedRows = myDataTableL.getSelectionModel().getSelectedItems();
			ArrayList<Lesson> rows = new ArrayList<>(selectedRows);
			rows.forEach(row -> {
				lesson.deleteRow(row);
				row.setStatus("Cancelled");
				myDataTableL.refresh();
			});
			myDataTableL.refresh();
		});

		lrefreshButton.setOnAction((ActionEvent e) -> {
			myDataTableL.refresh();
		});
		lclearButton.setOnAction((ActionEvent e) -> {
			Stage stage = new Stage();
			lesson.showDialog(stage, NONE, myDataTableL);

		});

	}

	public void showBillView() {
		BillTable bill = new BillTable();
		try {
			bill.getData();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		bill.dataList = FXCollections.observableArrayList(bill.data);

		bidCol.setCellValueFactory(new PropertyValueFactory<Bill, Integer>("bid"));// t_id is the assigned property in
																					// teacher class
		bDateCol.setCellValueFactory(new PropertyValueFactory<Bill, String>("date"));// dob in teacher class
		bLidCol.setCellValueFactory(new PropertyValueFactory<Bill, Integer>("lid"));

		bLidCol.setCellFactory(TextFieldTableCell.<Bill, Integer>forTableColumn(new IntegerStringConverter()));
		bLidCol.setOnEditCommit((CellEditEvent<Bill, Integer> t) -> {
			int oldV = t.getOldValue();
			if (bill.updateLid(t.getRowValue().getBid(), t.getNewValue()) == 0) {
				Stage Error = new Stage();
				Error.setTitle("ERROR");
				VBox pane2 = new VBox();
				pane2.setAlignment(Pos.CENTER);
				Label label2 = new Label("Cannot Update ,Please Check L_ID");
				Font font = Font.font("Arial", FontWeight.BOLD, 15);
				label2.setFont(font);
				pane2.getChildren().addAll(label2);
				Scene scene2 = new Scene(pane2, 350, 200);// add the pane to the scene
				Error.setScene(scene2);// add the scene to the stage
				Error.show();// show stage
				myDataTableB.refresh();// to refresh table view after doing a forbidden edit

			} else {

				((Bill) t.getTableView().getItems().get(t.getTablePosition().getRow())).setLid(t.getNewValue());
			}

		});
		myDataTableB.setItems(bill.dataList);
		BL2.setCellValueFactory(new PropertyValueFactory<Lesson, Integer>("l_id"));
		LD2.setCellValueFactory(new PropertyValueFactory<Lesson, String>("date"));

		billLessonButton.setOnAction((ActionEvent e) -> {

			Integer x = Integer.valueOf(billLessonText.getText());

			try {
				billLessonS(billLessonTable, x);
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		});

		baddButton.setOnAction((ActionEvent e) -> {

			Stage confirm = new Stage();
			confirm.setTitle("Confirm");
			VBox pane2 = new VBox();
			pane2.setAlignment(Pos.CENTER);
			Label label2 = new Label(
					"Are You Sure From Date Of Birth Of Teacher\nNote:Once Added, Date Of Birth Couldn't Be Edited");
			Font font = Font.font("Arial", FontWeight.BOLD, 15);
			label2.setFont(font);
			final Button confirmD = new Button("CONFIRM");
			confirmD.setStyle("-fx-font-weight: bold;-fx-background-color: brown;");
			confirmD.setOnMouseEntered(
					event -> confirmD.setStyle("-fx-font-weight: bold;-fx-background-color: lightblue;"));
			confirmD.setOnMouseExited(event -> confirmD.setStyle("-fx-font-weight: bold;-fx-background-color: brown;"));
			confirmD.setStyle("-fx-font-weight: bold;-fx-background-color: brown");
			pane2.getChildren().addAll(label2, confirmD);
			Scene scene2 = new Scene(pane2, 450, 300);// add the pane to the scene
			confirm.setScene(scene2);// add the scene to the stage
			confirm.show();// show stage
			confirmD.setOnAction((ActionEvent o) -> {
				Bill rc;
				LocalDate currentDate = LocalDate.now();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				String SD = currentDate.format(formatter);

				rc = new Bill(0, SD, Integer.valueOf(addBLid.getText()));
				if (bill.insertData(rc) == 0) {
					Stage Error = new Stage();
					Error.setTitle("ERROR");
					VBox pane3 = new VBox();
					pane3.setAlignment(Pos.CENTER);
					Label label21 = new Label("Cannot Add Bill , Please Check L_ID");
					Font font2 = Font.font("Arial", FontWeight.BOLD, 15);
					label21.setFont(font2);
					pane3.getChildren().addAll(label21);
					Scene scene3 = new Scene(pane3, 350, 200);// add the pane to the scene
					Error.setScene(scene3);// add the scene to the stage
					Error.show();// show stage
				} else {
					bill.dataList.add(rc);
					addBLid.clear();
					confirm.close();
					// addStartDate.clear();
					try {
						bill.data.clear();
						bill.getData();
					} catch (ClassNotFoundException p) {
						// TODO Auto-generated catch block
						p.printStackTrace();
					} catch (SQLException p) {
						// TODO Auto-generated catch block
						p.printStackTrace();
					}
					bill.dataList.clear();
					bill.dataList = FXCollections.observableArrayList(bill.data);
				}

				myDataTableB.setItems(bill.dataList);
				myDataTableB.refresh();

			});

		});

		brefreshButton.setOnAction((ActionEvent e) -> {
			myDataTableB.refresh();
		});

	}

	////////////////////////////////////////////////// End Of Abed Work
	////////////////////////////////////////////////// /////////////////////////////////////////////////////////////
	public void logout() {

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation Message");
		alert.setHeaderText(null);
		alert.setContentText("Are you sure you want to logout?");
		Optional<ButtonType> option = alert.showAndWait();
		try {
			if (option.get().equals(ButtonType.OK)) {
				// HIDE YOUR DASHBOARD FORM
				signOut.getScene().getWindow().hide();

				// LINK YOUR LOGIN FORM
				Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
				Stage stage = new Stage();
				Scene scene = new Scene(root);

				root.setOnMousePressed((MouseEvent event) -> {
					x = event.getSceneX();
					y = event.getSceneY();
				});

				root.setOnMouseDragged((MouseEvent event) -> {
					stage.setX(event.getScreenX() - x);
					stage.setY(event.getScreenY() - y);

					stage.setOpacity(.8);
				});

				root.setOnMouseReleased((MouseEvent event) -> {
					stage.setOpacity(1);
				});

				stage.initStyle(StageStyle.TRANSPARENT);

				stage.setScene(scene);
				stage.show();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void billLessonS(TableView<Lesson> tb, Integer d) throws SQLException, ClassNotFoundException {
		ObservableList<Lesson> dataLists;
		ArrayList<Lesson> arr = new ArrayList<>();
		String SQL;
		DataBaseConnector.connectDB();
		SQL = "select L.L_id,L.L_Date From Lesson L , student S where Not Exists(Select B1.L_ID From Bill B1 where B1.L_id=L.L_id) "
				+ "and L.stat != 'Cancelled' and S.sid=L.s_id and S.sid = " + d;
		Statement stmt = DataBaseConnector.con.createStatement();
		ResultSet rs = stmt.executeQuery(SQL);

		while (rs.next())
			arr.add(new Lesson(Integer.parseInt(rs.getString(1)), "", rs.getString(2), 0, 0, 0, ""));
		dataLists = FXCollections.observableArrayList(arr);
		billLessonTable.setItems(dataLists);
		rs.close();
		stmt.close();

		DataBaseConnector.con.close();

	}

	// Helper method to display an error message
	private void showError(String message) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}
	// Helper method to display an Information message

	private void showInformationAlert(String content) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setContentText(content);
		alert.showAndWait();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		studentAvlable();
		teacherAvlable();
		carAvlable();
		showStudentViewListData();
		showLicenseViewListData();
		showTestViewListData();
		showTeacherView();
		showVeichleView();
		availableStudentSearch();
		availableLicenseSearch();
		availableTestSearch();
		showLessonView();
		showBillView();
		homeStudentChart();
		addTStat.getItems().addAll("Active", "InActive");
		addVStat.getItems().addAll("Active", "InActive");
		studentStatus.getItems().addAll("Active", "InActive");

	}

}
