package com.drugstore.DTO;

import java.time.LocalDate;

public class PurchaseResponseDTO {

    private long id;
    private String drugName;
    private String distributorName;
    private Long quantity;
    private LocalDate purchaseDate;
    private LocalDate expiryDate;
    private String batch;
	public PurchaseResponseDTO(long id, Long quantity, LocalDate purchaseDate, String drugname, String distributorname,LocalDate expiryDate, String batch) {
		this.id=id;
		this.drugName=drugname;
		this.distributorName=distributorname;
		this.quantity=quantity;
		this.purchaseDate=purchaseDate;
		this.expiryDate=expiryDate;
		this.batch=batch;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDrugName() {
		return drugName;
	}
	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}
	public String getDistributorName() {
		return distributorName;
	}
	public void setDistributorName(String distributorName) {
		this.distributorName = distributorName;
	}
	public Long getQuantity() {
		return quantity;
	}
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	public LocalDate getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(LocalDate purchaseDate) {
		this.purchaseDate = purchaseDate;
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
