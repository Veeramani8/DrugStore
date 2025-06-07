package com.drugstore.Service;

import java.util.List;
import java.util.Optional;

import com.drugstore.DTO.CustomerOrderRequestDTO;
import com.drugstore.DTO.CustomerOrderResponseDTO;
import com.drugstore.DTO.RevenueReportDTO;
import com.drugstore.Model.CustomerOrder;
import com.drugstore.Model.OrderStatus;

public interface OrderService {
    CustomerOrderResponseDTO placeOrder(CustomerOrderRequestDTO requestDTO);
    List<CustomerOrderResponseDTO> getAllOrders();
    CustomerOrderResponseDTO updateOrderStatus(Long orderId, OrderStatus status);
	CustomerOrder getOrderById(Long id);
	List<RevenueReportDTO> getRevenueReportForLast7Days();
}
