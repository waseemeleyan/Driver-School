package application;

public class Lesson {
	private int l_id ; 
	private String time ;
	private String date ;
	private int s_id;
	private int t_id;
	private int v_id;
	private String status;
	
	
	public Lesson (int l_id,String time,String date,int s_id,int t_id,int v_id,String status) {
		this.l_id=l_id;
		this.time=time;
		this.date=date;
		this.s_id=s_id;
		this.t_id=t_id;
		this.v_id=v_id;
		this.status=status;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public int getL_id() {
		return l_id;
	}


	public void setL_id(int l_id) {
		this.l_id = l_id;
	}


	public String getTime() {
		return time;
	}


	public void setTime(String time) {
		this.time = time;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public int getS_id() {
		return s_id;
	}


	public void setS_id(int s_id) {
		this.s_id = s_id;
	}


	public int getT_id() {
		return t_id;
	}


	public void setT_id(int t_id) {
		this.t_id = t_id;
	}


	public int getV_id() {
		return v_id;
	}


	public void setV_id(int v_id) {
		this.v_id = v_id;
	}

}
