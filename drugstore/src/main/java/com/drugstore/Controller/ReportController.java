package com.drugstore.Controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.w3c.dom.css.RGBColor;

import com.drugstore.DTO.ExpiryReportDTO;
import com.drugstore.DTO.PurchaseReportDTO;
import com.drugstore.DTO.ReportFilterDTO;
import com.drugstore.DTO.RevenueReportDTO;
import com.drugstore.DTO.SalesReportDTO;
import com.drugstore.Repository.StockRepository;
import com.drugstore.Service.OrderService;
import com.drugstore.Service.ReportService;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

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
    @GetMapping("/download-revenue")
    public void downloadRevenueReportPdf(
            @ModelAttribute ReportFilterDTO filter,
            HttpServletResponse response) throws IOException, DocumentException {

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=RevenueReport.pdf");

        List<RevenueReportDTO> report = reportService.getRevenueReport(filter);

        Document document = new Document();
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

        document.add(new Paragraph("Revenue Report"));
        document.add(new Paragraph("From: " + filter.getStartDate() + " To: " + filter.getEndDate()));
        document.add(new Paragraph(" "));

        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);

        Stream.of("Date", "Total Sales Amount", "Number of Orders", "Average Order Value")
                .forEach(header -> {
                    PdfPCell cell = new PdfPCell(new Phrase(header));
                    table.addCell(cell);
                });

        for (RevenueReportDTO row : report) {
            table.addCell(row.getDate().toString());
            table.addCell(String.valueOf(row.getTotalSalesAmount()));
            table.addCell(String.valueOf(row.getNumberOfOrders()));
            table.addCell(String.valueOf(row.getAverageOrderValue()));
        }

        document.add(table);
        document.close();
    }
    @GetMapping("/download-sales")
    public void downloadSalesReportPdf(
            @ModelAttribute ReportFilterDTO filter,
            HttpServletResponse response) throws IOException, DocumentException {

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=SalesReport.pdf");

        List<SalesReportDTO> report = reportService.getSalesReport(filter);

        Document document = new Document();
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

        document.add(new Paragraph("Sales Report"));
        document.add(new Paragraph("From: " + filter.getStartDate() + " To: " + filter.getEndDate()));
        document.add(new Paragraph(" "));

        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);

        Stream.of( "Drug Name", "Total drugs Sold", "Total Revenue")
                .forEach(header -> {
                    PdfPCell cell = new PdfPCell(new Phrase(header));
                    table.addCell(cell);
                });

        for (SalesReportDTO row : report) {
            table.addCell(row.getDrugName());
            table.addCell(String.valueOf(row.getTotalQuantitySold()));
            table.addCell(String.valueOf(row.getTotalRevenue()));
            
        }

        document.add(table);
        document.close();
    }
    @GetMapping("/download-purchase")
    public void downloadPurchaseReportPdf(
            @ModelAttribute ReportFilterDTO filter,
            HttpServletResponse response) throws IOException, DocumentException {

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=PurchaseReport.pdf");

        List<PurchaseReportDTO> report = reportService.getPurchaseReport(filter);

        Document document = new Document();
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

        document.add(new Paragraph("Purchase Report"));
        document.add(new Paragraph("From: " + filter.getStartDate() + " To: " + filter.getEndDate()));
        document.add(new Paragraph(" "));

        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);

        Stream.of( "Drug Name", "Quantity purchased ", "Purchase Date","Total Cost")
                .forEach(header -> {
                    PdfPCell cell = new PdfPCell(new Phrase(header));
                    table.addCell(cell);
                });

        for (PurchaseReportDTO row : report) {
            table.addCell(row.getDrugName());
            table.addCell(String.valueOf(row.getQuantityPurchased()));
            table.addCell(String.valueOf(row.getPurchaseDate()));
            table.addCell(String.valueOf(row.getTotalCost()));

            
        }

        document.add(table);
        document.close();
    }
  

    
   
 


    }

    
   
  

