package com.drugstore.Model;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.*;



@Entity
public class Stock{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private long quantity;
	private String batch;
    private LocalDate expiryDate;
    private LocalDate lastUpdated;
	@OneToOne
    @JoinColumn(name = "drug_id", referencedColumnName = "id")
    private Drug drug;
	public LocalDate getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(LocalDate expiryDate) {
		this.expiryDate = expiryDate;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	
	public long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Drug getDrug() {
		return drug;
	}
	public void setDrug(Drug drug) {
		this.drug = drug;
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
	public void setLastUpdated(LocalDate localDate) {
		this.lastUpdated = localDate;
	}
	public String getBatch() {
		return batch;
	}
	public void setBatch(String batch) {
		this.batch = batch;
	}
	
	

}