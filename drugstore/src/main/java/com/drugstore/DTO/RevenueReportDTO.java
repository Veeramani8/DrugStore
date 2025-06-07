package com.drugstore.DTO;

import java.time.LocalDate;

public class RevenueReportDTO {
    private LocalDate date;
    private Double totalSalesAmount;
    private Long numberOfOrders;
    private Double averageOrderValue;
    
	public RevenueReportDTO(LocalDate date, Double totalSalesAmount, Long numberOfOrders, Double averageOrderValue) {
		this.date = date;
		this.totalSalesAmount = totalSalesAmount;
		this.numberOfOrders = numberOfOrders;
		this.averageOrderValue = averageOrderValue;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public double getTotalSalesAmount() {
		return totalSalesAmount;
	}
	public void setTotalSalesAmount(Double totalSalesAmount) {
		this.totalSalesAmount = totalSalesAmount;
	}
	public Long getNumberOfOrders() {
		return numberOfOrders;
	}
	public void setNumberOfOrders(Long numberOfOrders) {
		this.numberOfOrders = numberOfOrders;
	}
	public Double getAverageOrderValue() {
		return averageOrderValue;
	}
	public void setAverageOrderValue(Double averageOrderValue) {
		this.averageOrderValue = averageOrderValue;
	}
}