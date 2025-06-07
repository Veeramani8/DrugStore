package com.drugstore.DTO;

import java.time.LocalDate;


public class StockResponseDTO {
    private long id;
    private long drugId;
    private String drugName;
    private long quantity;
    private LocalDate lastUpdated;
    private LocalDate expiryDate;
	private String batch;


    public StockResponseDTO(long id, long drugId, String drugname,long quantity, LocalDate localDate,LocalDate expiryDate, String batch) {
    	this.id=id;
    	this.drugId=drugId;
    	this.drugName=drugname;
    	this.quantity=quantity;
    	this.lastUpdated=localDate;
    	this.expiryDate=expiryDate;
    	this.batch=batch;
    	
    	
	}
    public StockResponseDTO(String drugname,long quantity) {
    	this.drugName=drugname;
    	this.quantity=quantity;
    }
    
	public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public long getDrugId() {
        return drugId;
    }
    public void setDrugId(long drugId) {
        this.drugId = drugId;
    }

    public String getDrugName() {
        return drugName;
    }
    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public Long getQuantity() {
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
