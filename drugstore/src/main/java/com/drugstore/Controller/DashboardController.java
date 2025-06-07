package com.drugstore.Controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.drugstore.DTO.OrderSummaryDTO;
import com.drugstore.DTO.RevenueReportDTO;
import com.drugstore.DTO.TopDrugDTO;
import com.drugstore.Repository.OrderItemRepository;
import com.drugstore.Service.DashboardService;
import com.drugstore.Service.DrugService;
import com.drugstore.Service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderItemRepository orderItemRepo;

    @GetMapping("/dashboard")
    public String showDashboard(Model model, Principal principal) {
        String username = principal.getName();
        String role = dashboardService.getUserRole(username);

        double totalSales = dashboardService.getTotalSales();
        long stockLevel = dashboardService.getTotalStockCount();
        int recentOrdersCount = dashboardService.getRecentOrdersCount();
        TopDrugDTO topDrug = dashboardService.getTopSellingDrug();

        List<String> lowStockAlerts = dashboardService.getLowStockAlerts();
        List<String> expiryAlerts = dashboardService.getExpiryAlerts();
        List<OrderSummaryDTO> pendingOrders = dashboardService.getPendingOrders();

        List<String> salesDates = dashboardService.getSalesChartDates();
        List<Double> salesValues = dashboardService.getSalesChartValues();

        model.addAttribute("username", username);
        model.addAttribute("role", role);

        model.addAttribute("totalSales", totalSales);
        model.addAttribute("stockLevel", stockLevel);
        model.addAttribute("recentOrdersCount", recentOrdersCount);
        model.addAttribute("topDrugName", topDrug != null ? topDrug.getName() : "N/A");

        model.addAttribute("lowStockAlerts", lowStockAlerts);
        model.addAttribute("expiryAlerts", expiryAlerts);
        model.addAttribute("pendingOrders", pendingOrders);

        model.addAttribute("salesDates", salesDates);
        model.addAttribute("salesValues", salesValues);

        return "dashboard";
    }
    @GetMapping("/dashboard-data/revenue")
    @ResponseBody
    public Map<String, Object> getRevenueChartData() {
        List<RevenueReportDTO> report = orderService.getRevenueReportForLast7Days();

        List<String> dates = report.stream()
                .map(r -> r.getDate().toString())
                .collect(Collectors.toList());

        List<Double> revenues = report.stream()
                .map(RevenueReportDTO::getTotalSalesAmount)
                .collect(Collectors.toList());

        Map<String, Object> data = new HashMap<>();
        data.put("labels", dates);
        data.put("revenues", revenues);

        return data;
    }
    @GetMapping("/dashboard-data/top-drugs")
    @ResponseBody
    public Map<String, Object> getTopSellingDrugs() {
        List<Object[]> topDrugs = orderItemRepo.findTopSellingDrugs();

        List<String> drugs = new ArrayList<>();
        List<Long> quantities = new ArrayList<>();

        for (Object[] obj : topDrugs) {
            drugs.add((String) obj[0]);
            quantities.add((Long) obj[1]);
        }

        Map<String, Object> map = new HashMap<>();
        map.put("drugs", drugs);
        map.put("quantities", quantities);
        return map;
    }



}
