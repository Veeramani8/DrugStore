package com.drugstore.DTO;

import java.util.Date;

public class DrugResponseDTO {
    private Long id;
    private String name;
    private String composition;
    private String dosage;
    private String manufacturer;
    private Double price;
    
	public DrugResponseDTO(Long id,String name, String composition, String dosage, String manufacturer, Double price) {
		this.id=id;
		this.name=name;
		this.composition=composition;
		this.dosage=dosage;
		this.manufacturer=manufacturer;
		this.price=price;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getComposition() {
		return composition;
	}
	public void setComposition(String composition) {
		this.composition = composition;
	}
	public String getDosage() {
		return dosage;
	}
	public void setDosage(String dosage) {
		this.dosage = dosage;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

}
