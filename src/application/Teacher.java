package application;
public class Teacher {
	private int t_id;
	private String t_name;
	private String dob;
	private int salary;
	private String start_date;
	private String email;
	private String status;
	
	public Teacher (int t_id,String t_name,String dob,int salary,String start_date,String email , String status) {
		this.t_id=t_id;
		this.t_name=t_name;
		this.salary=salary;
		this.start_date=start_date;
		this.dob=dob;
		this.dob=dob;
		this.email=email;
		this.status=status;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getT_id() {
		return t_id;
	}
	

	public void setT_id(int t_id) {
		this.t_id = t_id;
	}

	public String getT_name() {
		return t_name;
	}

	public void setT_name(String t_name) {
		this.t_name = t_name;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public String getStart_date() {
		return start_date;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	
	
	

}