package com.drugstore.DTO;

public class SalesReportDTO {
    private String drugName;
    private long totalQuantitySold;
    private double totalRevenue;
    
    
	public SalesReportDTO(String drugName, long totalQuantitySold, double totalRevenue) {
		this.drugName = drugName;
		this.totalQuantitySold = totalQuantitySold;
		this.totalRevenue = totalRevenue;
	}
	public String getDrugName() {
		return drugName;
	}
	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}
	public long getTotalQuantitySold() {
		return totalQuantitySold;
	}
	public void setTotalQuantitySold(long totalQuantitySold) {
		this.totalQuantitySold = totalQuantitySold;
	}
	public double getTotalRevenue() {
		return totalRevenue;
	}
	public void setTotalRevenue(double totalRevenue) {
		this.totalRevenue = totalRevenue;
	}
}