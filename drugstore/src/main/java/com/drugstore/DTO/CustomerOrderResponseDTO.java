package com.drugstore.DTO;

import java.time.LocalDate;
import java.util.List;

import com.drugstore.Model.OrderStatus;

public class CustomerOrderResponseDTO {
    public CustomerOrderResponseDTO(long id, String customerName2, LocalDate orderDate2, OrderStatus status2,
			double totalAmount2, List<OrderItemResponseDTO> itemDTOs) {
    	this.id=id;
    	this.customerName=customerName2;
    	this.orderDate=orderDate2;
    	this.status = status2 ;
    	this.totalAmount=totalAmount2;
    	this.items=itemDTOs;
	}

	

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public List<OrderItemResponseDTO> getItems() {
		return items;
	}

	public void setItems(List<OrderItemResponseDTO> items) {
		this.items = items;
	}

	private Long id;
    private LocalDate orderDate;
    private OrderStatus status;
    private Double totalAmount;
    private List<OrderItemResponseDTO> items;
    private String customerName;


	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}
}
