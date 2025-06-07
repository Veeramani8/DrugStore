package com.drugstore.DTO;

import java.time.LocalDate;

public class ExpiryReportDTO{
	private String drugName;
	private LocalDate expiryDate;
    private long quantityRemaining;
    private String batch;
    public ExpiryReportDTO(String drugName, LocalDate expiryDate, long quantity, String batch) {
        this.drugName = drugName;
        this.expiryDate = expiryDate;
        this.quantityRemaining = quantity;
        this.batch=batch;
    }

	public String getDrugName() {
		return drugName;
	}
	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}
	public LocalDate getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(LocalDate expiryDate) {
		this.expiryDate = expiryDate;
	}
	public long getQuantityRemaining() {
		return quantityRemaining;
	}
	public void setQuantityRemaining(long quantityRemaining) {
		this.quantityRemaining = quantityRemaining;
	}

	public String getBatch() {
		return batch;
	}

	public void setBatch(String batch) {
		this.batch = batch;
	}

}