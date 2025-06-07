package com.drugstore.Service;

import com.drugstore.DTO.OrderSummaryDTO;
import com.drugstore.DTO.TopDrugDTO;
import com.drugstore.Model.*;
import com.drugstore.Repository.*;

import com.drugstore.Service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DashboardServiceImp implements DashboardService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderItemRepository orderItemRepo;

    @Autowired
    private CustomerOrderRepository orderRepo;


    @Autowired
    private StockRepository stockRepo;
    @Autowired
    private NotificationService notificationService;

    @Override
    public String getUserRole(String username) {
        return userRepository.findByUsername(username)
                .map(user -> user.getRole().name())
                .orElse("USER");
    }

    @Override
    public double getTotalSales() {
        return orderItemRepo.findAll().stream()
                .mapToDouble(item -> item.getQuantity() * item.getPrice())
                .sum();
    }

    @Override
    public long getTotalStockCount() {
        return stockRepo.findAll().stream()
                .mapToLong(Stock::getQuantity)
                .sum();
    }

    @Override
    public int getRecentOrdersCount() {
        return (int) orderRepo.findAll().stream()
                .filter(order -> order.getOrderDate().isAfter(LocalDate.now().minusDays(7)))
                .count();
    }

    @Override
    public TopDrugDTO getTopSellingDrug() {
        Map<String, Long> drugSales = new HashMap<>();

        orderItemRepo.findAll().forEach(item -> {
            String drugName = item.getDrug().getName();
            drugSales.put(drugName, drugSales.getOrDefault(drugName, 0L) + item.getQuantity());
        });

        return drugSales.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(entry -> {
                    TopDrugDTO dto = new TopDrugDTO();
                    dto.setName(entry.getKey());
                    dto.setQuantity(entry.getValue());
                    return dto;
                }).orElse(null);
    }

    @Override
    public List<String> getLowStockAlerts() {
        int threshold = 20; 
        return stockRepo.findAll().stream()
                .filter(stock -> stock.getQuantity() <= threshold)
                .map(stock -> stock.getDrug().getName() + " - Qty: " + stock.getQuantity())
                .collect(Collectors.toList());
    }


    @Override
    public List<String> getExpiryAlerts() {
        LocalDate today = LocalDate.now();
        return stockRepo.findAll().stream()
                .filter(stock -> stock.getExpiryDate() != null && stock.getExpiryDate().isBefore(today.plusDays(30)))
                .map(stock -> stock.getDrug().getName() + " - Exp: " + stock.getExpiryDate())
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderSummaryDTO> getPendingOrders() {
        return orderRepo.findAll().stream()
        		.filter(order -> order.getStatus() == OrderStatus.PENDING)
                .map(order -> {
                    OrderSummaryDTO dto = new OrderSummaryDTO();
                    dto.setId(order.getId());
                    dto.setStatus(order.getStatus());
                    return dto;
                })
                .collect(Collectors.toList());
    }


    @Override
    public List<String> getSalesChartDates() {
        return orderRepo.findAll().stream()
                .sorted(Comparator.comparing(CustomerOrder::getOrderDate))
                .filter(order -> order.getOrderDate().isAfter(LocalDate.now().minusDays(7)))
                .map(order -> order.getOrderDate().toString())
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public List<Double> getSalesChartValues() {
        List<String> dates = getSalesChartDates();
        List<Double> sales = new ArrayList<>();

        for (String date : dates) {
            double total = orderRepo.findAll().stream()
                    .filter(order -> order.getOrderDate().toString().equals(date))
                    .flatMap(order -> order.getItems().stream())
                    .mapToDouble(item -> item.getQuantity() * item.getPrice())
                    .sum();
            sales.add(total);
        }
        return sales;
    }
   
    @Override
    public void sendAllAlerts() {
        List<String> lowStock = getLowStockAlerts();
        List<String> expiry = getExpiryAlerts();
        List<OrderSummaryDTO> pending = getPendingOrders();

        StringBuilder message = new StringBuilder();

        if (!lowStock.isEmpty()) {
            message.append("⚠️ Low Stock Alerts:\n");
            lowStock.forEach(item -> message.append("- ").append(item).append("\n"));
        }

        if (!expiry.isEmpty()) {
            message.append("\n⏳ Expiry Alerts:\n");
            expiry.forEach(item -> message.append("- ").append(item).append("\n"));
        }

        if (!pending.isEmpty()) {
            message.append("\n📦 Pending Orders:\n");
            pending.forEach(order -> message.append("- Order ID: ").append(order.getId()).append("\n"));
        }

        if (message.length() > 0) {
            notificationService.sendNotification("veeramanim2808@gmail.com", "Daily Alerts - Drug Store", message.toString());
        }
    }
    @Scheduled(cron = "0 27 11 * * ?")
    public void sendDailyAlerts() {
    	sendAllAlerts(); 
    }

}
