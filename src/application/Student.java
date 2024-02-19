package application;

import java.sql.Date;
import java.time.LocalDate;

public class Student {

	private int sid;
	private String sname;
	private String sstatus;
	private int smobilenumber;
	private int licenseId;
	private LocalDate date_start;
	private LocalDate dateOfBirth;


	public Student(int sid, String sname, String sstatus, int smobilenumber, int licenseId) {
		super();
		this.sid = sid;
		this.sname = sname;
		this.sstatus = sstatus;
		this.smobilenumber = smobilenumber;
		this.licenseId = licenseId;
	}

	public Student(String sname, String sstatus, int smobilenumber, int licenseId) {
		super();
		this.sname = sname;
		this.sstatus = sstatus;
		this.smobilenumber = smobilenumber;
		this.licenseId = licenseId;
	}
	//******* cahng

	

	public Student(int sid, String sname, String sstatus, int smobilenumber, int licenseId, LocalDate date_start,
			LocalDate dateOfBirth) {
		super();
		this.sid = sid;
		this.sname = sname;
		this.sstatus = sstatus;
		this.smobilenumber = smobilenumber;
		this.licenseId = licenseId;
		this.date_start = date_start;
		this.dateOfBirth = dateOfBirth;
	}
	
	
	
	
	
	public Student(String sname, String sstatus, int smobilenumber, int licenseId, LocalDate date_start,
			LocalDate dateOfBirth) {
		super();
		this.sname = sname;
		this.sstatus = sstatus;
		this.smobilenumber = smobilenumber;
		this.licenseId = licenseId;
		this.date_start = date_start;
		this.dateOfBirth = dateOfBirth;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}


	public LocalDate getDate_start() {
		return date_start;
	}


	public void setDate_start(LocalDate date_start) {
		this.date_start = date_start;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getSstatus() {
		return sstatus;
	}

	public void setSstatus(String sstatus) {
		this.sstatus = sstatus;
	}

	public int getSmobilenumber() {
		return smobilenumber;
	}

	public void setSmobilenumber(int smobilenumber) {
		this.smobilenumber = smobilenumber;
	}

	public int getLicenseId() {
		return licenseId;
	}

	public void setLicenseId(int licenseId) {
		this.licenseId = licenseId;
	}

}