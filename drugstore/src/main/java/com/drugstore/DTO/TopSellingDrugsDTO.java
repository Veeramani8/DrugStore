package com.drugstore.DTO;

public class TopSellingDrugsDTO {
    private String drugName;
    private long quantitySold;
    public String getDrugName() {
		return drugName;
	}
	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}
	public long getQuantitySold() {
		return quantitySold;
	}
	public void setQuantitySold(long quantitySold) {
		this.quantitySold = quantitySold;
	}
	public double getTotalRevenue() {
		return totalRevenue;
	}
	public void setTotalRevenue(double totalRevenue) {
		this.totalRevenue = totalRevenue;
	}
	private double totalRevenue;
}