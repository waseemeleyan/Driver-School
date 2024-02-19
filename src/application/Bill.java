package application;

public class Bill {
	private int bid ; 
	private String date ;
	private int lid ; 

	
	public Bill (int bid,String date,int lid) {
		this.bid=bid;
		this.date=date;
		this.lid=lid;
	}


	public int getBid() {
		return bid;
	}


	public void setBid(int bid) {
		this.bid = bid;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public int getLid() {
		return lid;
	}


	public void setLid(int lid) {
		this.lid = lid;
	}
	
	
	
}
