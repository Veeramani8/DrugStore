package com.drugstore.Controller;

import com.drugstore.DTO.ExpiryReportDTO;
import com.drugstore.DTO.StockResponseDTO;
import com.drugstore.Model.Stock;
import com.drugstore.Service.ReportServiceImp;
import com.drugstore.Service.StockService;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/stocks")
public class StockController {

    @Autowired
    private StockService stockService;
    @Autowired
	ReportServiceImp reportServiceImp;

    @GetMapping
    public String viewAllStocks(Model model) {
        List<StockResponseDTO> stockList = stockService.getAllStocks();
        model.addAttribute("stocks", stockList);
        return "stock-list";
    }

    @GetMapping("/expiry")
    public String viewExpiringStocks(Model model) {
        List<ExpiryReportDTO> expiring = reportServiceImp.getExpiryReport();
        model.addAttribute("stocks", expiring);
        return "expiry-list"; 
    }
    @GetMapping("/pdf")
    public void downloadStockPdf(HttpServletResponse response) throws Exception {
        List<StockResponseDTO> stockList = stockService.getAllStocks();

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=stock-report.pdf");

        Document document = new Document();
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);
        Font normalFont = FontFactory.getFont(FontFactory.HELVETICA, 12);

        document.add(new Paragraph("Stock Report", titleFont));
        document.add(new Paragraph("Generated on: " + LocalDate.now(), normalFont));
        document.add(new Paragraph(" "));

        PdfPTable table = new PdfPTable(5); 
        table.setWidthPercentage(100);

        table.addCell("Drug Name");
        table.addCell("Batch No");
        table.addCell("Quantity");
        table.addCell("Expiry Date");
        table.addCell("LastUpdated");

        for (StockResponseDTO stock : stockList) {
            table.addCell(stock.getDrugName());
            table.addCell(stock.getBatch());
            table.addCell(String.valueOf(stock.getQuantity()));
            table.addCell(String.valueOf(stock.getExpiryDate()));
            table.addCell(String.valueOf(stock.getLastUpdated()));

            
        }

        document.add(table);
        document.close();
    }

}
