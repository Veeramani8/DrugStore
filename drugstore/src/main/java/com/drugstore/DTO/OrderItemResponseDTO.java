package com.drugstore.DTO;

public class OrderItemResponseDTO {
   
	public OrderItemResponseDTO(Long id, Long id2, String drugname, Long quantity, Double drugprice) {
		this.drugId=id;
		this.quantity=quantity;
		this.drugname=drugname;
		this.drugprice=drugprice;
	}
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
	public String getDrugname() {
		return drugname;
	}
	public void setDrugname(String drugname) {
		this.drugname = drugname;
	}
	public Double getDrugprice() {
		return drugprice;
	}
	public void setDrugprice(Double drugprice) {
		this.drugprice = drugprice;
	}
	private Long drugId;
    private Long quantity;
    private String drugname;
    private Double drugprice;
}
