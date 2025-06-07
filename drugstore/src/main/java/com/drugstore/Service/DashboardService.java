package com.drugstore.Service;

import java.util.List;

import com.drugstore.DTO.OrderSummaryDTO;
import com.drugstore.DTO.TopDrugDTO;

public interface DashboardService {

    String getUserRole(String username);

    double getTotalSales();

    long getTotalStockCount();

    int getRecentOrdersCount();

    TopDrugDTO getTopSellingDrug();

    List<String> getLowStockAlerts();

    List<String> getExpiryAlerts();

    List<OrderSummaryDTO> getPendingOrders();

    List<String> getSalesChartDates();

    List<Double> getSalesChartValues();

	void sendAllAlerts();
}
