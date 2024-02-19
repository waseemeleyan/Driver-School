package application;

public class Veichle {
	private int vid;
	private int vnum;
	private String vtyp;
	private String stat;
	
	
	public Veichle (int vid,int vnum,String vtyp,String stat) {
		this.vid=vid;
		this.vnum=vnum;
		this.vtyp=vtyp;
		this.stat=stat;
		
		
	}


	public int getVid() {
		return vid;
	}


	public void setVid(int vid) {
		this.vid = vid;
	}


	public int getVnum() {
		return vnum;
	}


	public void setVnum(int vnum) {
		this.vnum = vnum;
	}


	public String getVtyp() {
		return vtyp;
	}


	public void setVtyp(String vtyp) {
		this.vtyp = vtyp;
	}


	public String getStat() {
		return stat;
	}


	public void setStat(String stat) {
		this.stat = stat;
	}
	
	

}
