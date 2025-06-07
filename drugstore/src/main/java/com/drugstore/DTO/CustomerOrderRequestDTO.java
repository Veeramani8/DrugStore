package com.drugstore.DTO;

import java.util.ArrayList;
import java.util.List;

public class CustomerOrderRequestDTO {
    public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public List<OrderItemRequestDTO> getItems() {
		return items;
	}
	public void setItems(List<OrderItemRequestDTO> items) {
		this.items = items;
	}
	private String customerName;
    private List<OrderItemRequestDTO> items = new ArrayList<>(); ;

}

