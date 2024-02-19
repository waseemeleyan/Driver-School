package application;

import java.time.LocalDate;

import javafx.scene.control.DatePicker;

public class Test {
	private int TestID;
	private int TestNO;
	private int sid;
	private int vid;
	private LocalDate testDate;

	
	
	
	
	public Test(int testID, int testNO, int sid, int vid, LocalDate testDate) {
		super();
		TestID = testID;
		TestNO = testNO;
		this.sid = sid;
		this.vid = vid;
		this.testDate = testDate;
	}

	public Test(int testNO, int sid, int vid, LocalDate testDate) {
		super();
		TestNO = testNO;
		this.sid = sid;
		this.vid = vid;
		this.testDate = testDate;
	}

	public LocalDate getTestDate() {
		return testDate;
	}

	public void setTestDate(LocalDate testDate) {
		this.testDate = testDate;
	}

	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public int getTestID() {
		return TestID;
	}
	public void setTestID(int testID) {
		TestID = testID;
	}
	public int getTestNO() {
		return TestNO;
	}
	public void setTestNO(int testNO) {
		TestNO = testNO;
	}
	public int getVid() {
		return vid;
	}
	public void setVid(int vid) {
		this.vid = vid;
	}
	

}
