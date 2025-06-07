package com.drugstore.DTO;

import java.time.LocalDate;

public class StockRequestDTO {
    private long drugId;
    private long quantity;
    private LocalDate lastUpdated;
    private LocalDate expiryDate;
	private String batch;


    public long getDrugId() {
        return drugId;
    }
    public void setDrugId(long drugId) {
        this.drugId = drugId;
    }

    public long getQuantity() {
        return quantity;
    }
    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }
	public LocalDate getLastUpdated() {
		return lastUpdated;
	}
	public void setLastUpdated(LocalDate lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	public LocalDate getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(LocalDate expiryDate) {
		this.expiryDate = expiryDate;
	}
	public String getBatch() {
		return batch;
	}
	public void setBatch(String batch) {
		this.batch = batch;
	}
}
