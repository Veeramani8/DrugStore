package com.drugstore.DTO;

import com.drugstore.Model.OrderStatus;

public class OrderSummaryDTO {
    private Long id;
    private OrderStatus status;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public OrderStatus getStatus() {
		return status;
	}
	public void setStatus(OrderStatus status) {
		this.status = status;
	}

}
