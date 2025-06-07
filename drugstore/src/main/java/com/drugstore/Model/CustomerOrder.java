package com.drugstore.Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;


@Entity
public class CustomerOrder{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	 private List<OrderItem> items = new ArrayList<>();	 
	private LocalDate orderDate;
	    private double totalAmount;
	    @Enumerated(EnumType.STRING)
	    private OrderStatus status; 
	    private String customerName;

	    public CustomerOrder() {
	        this.items = new ArrayList<>();
	    }
	    public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public LocalDate getOrderDate() {
			return orderDate;
		}

		public void setOrderDate(LocalDate localDate) {
			this.orderDate = localDate;
		}

		public double getTotalAmount() {
			return totalAmount;
		}

		public void setTotalAmount(double totalAmount) {
			this.totalAmount = totalAmount;
		}

		public OrderStatus getStatus() {
			return status;
		}

		public void setStatus(OrderStatus status) {
			this.status = status;
		}

		public List<OrderItem> getItems() {
			return items;
		}

		public void setItems(List<OrderItem> items) {
			this.items = items;
		}

		public String getCustomerName() {
			return customerName;
		}

		public void setCustomerName(String customerName) {
			this.customerName = customerName;
		}

	

		

		
	
}