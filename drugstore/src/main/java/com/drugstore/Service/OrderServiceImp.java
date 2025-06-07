package com.drugstore.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drugstore.DTO.CustomerOrderRequestDTO;
import com.drugstore.DTO.CustomerOrderResponseDTO;
import com.drugstore.DTO.OrderItemRequestDTO;
import com.drugstore.DTO.OrderItemResponseDTO;
import com.drugstore.DTO.RevenueReportDTO;
import com.drugstore.Model.CustomerOrder;
import com.drugstore.Model.Drug;
import com.drugstore.Model.OrderItem;
import com.drugstore.Model.OrderStatus;
import com.drugstore.Model.Stock;
import com.drugstore.Repository.CustomerOrderRepository;
import com.drugstore.Repository.DrugRepository;
import com.drugstore.Repository.StockRepository;

@Service
public class OrderServiceImp implements OrderService {

    @Autowired
    private DrugRepository drugRepository;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private CustomerOrderRepository orderRepository;

    @Override
    public CustomerOrderResponseDTO placeOrder(CustomerOrderRequestDTO dto) {
        CustomerOrder order = new CustomerOrder();
        order.setCustomerName(dto.getCustomerName());
        order.setOrderDate(LocalDate.now());
        order.setStatus(OrderStatus.PENDING);

        List<OrderItem> items = new ArrayList<>();
        double totalAmount = 0;

        if (dto.getItems() == null || dto.getItems().isEmpty()) {
            throw new RuntimeException("Order must contain at least one item.");
        }

        for (OrderItemRequestDTO itemDTO : dto.getItems()) {

            Drug drug = drugRepository.findById(itemDTO.getDrugId())
                .orElseThrow(() -> new RuntimeException("Drug not found"));

            Stock stock = stockRepository.findByDrug(drug)
                .orElseThrow(() -> new RuntimeException("Stock not found"));

            if (stock.getQuantity() < itemDTO.getQuantity()) {
                throw new RuntimeException("Insufficient stock for drug: " + drug.getName());
            }

            stock.setQuantity(stock.getQuantity() - itemDTO.getQuantity());
            stock.setLastUpdated(LocalDate.now());
            stockRepository.save(stock);

            OrderItem item = new OrderItem();
            item.setDrug(drug);
            item.setQuantity(itemDTO.getQuantity());
            item.setPrice(drug.getPrice());
            item.setOrder(order);

            totalAmount += drug.getPrice() * itemDTO.getQuantity();
            items.add(item);
        }

        order.setItems(items);
        order.setTotalAmount(totalAmount);

        CustomerOrder savedOrder = orderRepository.save(order);

        return convertToResponseDTO(savedOrder);
    }

    @Override
    public List<CustomerOrderResponseDTO> getAllOrders() {
        return orderRepository.findAll().stream()
            .map(this::convertToResponseDTO)
            .collect(Collectors.toList());
    }

    @Override
    public CustomerOrderResponseDTO updateOrderStatus(Long orderId, OrderStatus status) {
        CustomerOrder order = orderRepository.findById(orderId)
            .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setStatus(status);
        return convertToResponseDTO(orderRepository.save(order));
    }

    private CustomerOrderResponseDTO convertToResponseDTO(CustomerOrder order) {
        List<OrderItemResponseDTO> itemDTOs = order.getItems().stream().map(item -> new OrderItemResponseDTO(
            item.getId(),
            item.getDrug().getId(),
            item.getDrug().getName(),
            item.getQuantity(),
            item.getPrice()
        )).collect(Collectors.toList());

        return new CustomerOrderResponseDTO(
            order.getId(),
            order.getCustomerName(),
            order.getOrderDate(),
            order.getStatus(),
            order.getTotalAmount(),
            itemDTOs
        );
    }

	@Override
	public CustomerOrder getOrderById(Long id) {
		return orderRepository.getById(id);
	}

	@Override
	public List<RevenueReportDTO> getRevenueReportForLast7Days() {
		LocalDate endDate = LocalDate.now();
	    LocalDate startDate = endDate.minusDays(6); // Includes today

	    return orderRepository.getRevenueSummary(startDate, endDate);
	}
}
