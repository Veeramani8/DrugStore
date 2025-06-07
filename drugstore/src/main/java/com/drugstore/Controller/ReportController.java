package com.drugstore.Controller;

import com.drugstore.DTO.ExpiryReportDTO;
import com.drugstore.DTO.PurchaseReportDTO;
import com.drugstore.DTO.ReportFilterDTO;
import com.drugstore.DTO.RevenueReportDTO;
import com.drugstore.DTO.SalesReportDTO;
import com.drugstore.DTO.StockResponseDTO;
import com.drugstore.Repository.StockRepository;
import com.drugstore.Service.OrderService;
import com.drugstore.Service.ReportService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private StockRepository stockRepository;
    @GetMapping
    public String reportDashboard() {
        return "report";
    }


    @GetMapping("/expiry")
    public String getExpiryReport(Model model) {
        List<ExpiryReportDTO> report = reportService.getExpiryReport();
        model.addAttribute("report", report);
        return "expiry-report";
    }

    @GetMapping("/sales")
    public String showSalesReportForm(Model model) {
        model.addAttribute("filter", new ReportFilterDTO());
        return "sales-report-form";
    }

    @PostMapping("/sales")
    public String generateSalesReport(@ModelAttribute("filter") ReportFilterDTO filter, Model model) {
        List<SalesReportDTO> report = reportService.getSalesReport(filter);
        model.addAttribute("report", report);
        return "sales-report";
    }

    @GetMapping("/purchases")
    public String showPurchaseReportForm(Model model) {
        model.addAttribute("filter", new ReportFilterDTO());
        return "purchase-report-form";
    }

    @PostMapping("/purchases")
    public String generatePurchaseReport(@ModelAttribute("filter") ReportFilterDTO filter, Model model) {
        List<PurchaseReportDTO> report = reportService.getPurchaseReport(filter);
        model.addAttribute("report", report);
        return "purchase-report";
    }
    @GetMapping("/revenue")
    public String showRevenueReportForm(Model model) {
        model.addAttribute("filter", new ReportFilterDTO());
        return "revenue-report-form";
    }

    @PostMapping("/revenue")
    public String generateRevenueReport(@ModelAttribute("filter") ReportFilterDTO filter, Model model) {
        List<RevenueReportDTO> reportList = reportService.getRevenueReport(filter);
        model.addAttribute("report", reportList);
        model.addAttribute("filter", filter);
        return "revenue-report-view";
    }
    @GetMapping("/stock-distribution")
    @ResponseBody
    public Map<String, Object> getStockDistribution() {
        List<StockResponseDTO> stockData = stockRepository.getStockDistribution();

        List<String> labels = stockData.stream()
                .map(StockResponseDTO::getDrugName)
                .collect(Collectors.toList());

        List<Long> values = stockData.stream()
                .map(StockResponseDTO::getQuantity)
                .collect(Collectors.toList());

        Map<String, Object> response = new HashMap<>();
        response.put("labels", labels);
        response.put("values", values);

        return response;
    }

  
}
