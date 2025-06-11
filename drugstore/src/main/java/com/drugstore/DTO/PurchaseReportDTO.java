package com.drugstore.DTO;

import java.time.LocalDate;

public class PurchaseReportDTO {
    private String drugName;
    private long quantityPurchased;
	private double totalCost;
    private LocalDate purchaseDate;
	public PurchaseReportDTO(String drugName, long quantityPurchased, double totalCost, LocalDate purchaseDate) {
		this.drugName = drugName;
		this.quantityPurchased = quantityPurchased;
		this.totalCost = totalCost;
		this.purchaseDate = purchaseDate;
	}

    public String getDrugName() {
		return drugName;
	}
	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}
	public long getQuantityPurchased() {
		return quantityPurchased;
	}
	public void setQuantityPurchased(long quantityPurchased) {
		this.quantityPurchased = quantityPurchased;
	}
	public double getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}
	public LocalDate getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(LocalDate purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

}