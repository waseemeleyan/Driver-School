package application;

import java.sql.Date;

public class License {

	private int licenseId;
	private int minimumNoOfTests;
	private String licenseType;
	private int Cost;

	public License(int licenseId, String licenseType, int cost, int minimumNoOfTests) {
		super();
		this.licenseId = licenseId;
		this.minimumNoOfTests = minimumNoOfTests;
		this.licenseType = licenseType;
		Cost = cost;
	}
	

	public License(int minimumNoOfTests, String licenseType, int cost) {
		super();
		this.minimumNoOfTests = minimumNoOfTests;
		this.licenseType = licenseType;
		Cost = cost;
	}


	public int getLicenseId() {
		return licenseId;
	}

	public void setLicenseId(int licenseId) {
		this.licenseId = licenseId;
	}

	public int getMinimumNoOfTests() {
		return minimumNoOfTests;
	}

	public void setMinimumNoOfTests(int minimumNoOfTests) {
		this.minimumNoOfTests = minimumNoOfTests;
	}

	public String getLicenseType() {
		return licenseType;
	}

	public void setLicenseType(String licenseType) {
		this.licenseType = licenseType;
	}

	public int getCost() {
		return Cost;
	}

	public void setCost(int cost) {
		Cost = cost;
	}

}
