package com.drugstore.DTO;

public class OrderItemRequestDTO {
    private Long drugId;
    private Long quantity;
    private Double price;
	public Long getDrugId() {
		
		return drugId;
	}
	public void setDrugId(Long drugId) {
		this.drugId = drugId;
	}
	public Long getQuantity() {
		return quantity;
	}
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
}

