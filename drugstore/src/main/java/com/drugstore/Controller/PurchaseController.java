package com.drugstore.Controller;

import com.drugstore.DTO.PurchaseRequestDTO;
import com.drugstore.DTO.PurchaseResponseDTO;
import com.drugstore.Service.DistributorService;
import com.drugstore.Service.DrugService;
import com.drugstore.Service.PurchaseService;
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
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/purchases")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @Autowired
    private DrugService drugService;

    @Autowired
    private DistributorService distributorService;

    @GetMapping
    public String getAllPurchases(Model model) {
        List<PurchaseResponseDTO> purchases = purchaseService.getAllPurchases();
        model.addAttribute("purchases", purchases);
        return "purchase-list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("purchase", new PurchaseRequestDTO());
        model.addAttribute("drugs", drugService.getAllDrugs());
        model.addAttribute("distributors", distributorService.getAllDistributors());
        return "purchase-form";
    }

    @PostMapping("/save")
    public String savePurchase(@ModelAttribute("purchase") PurchaseRequestDTO request) {
        purchaseService.savePurchase(request);
        return "redirect:/purchases";
    }



    

  
    
    @GetMapping("/pdf")
    public void downloadPurchasePdf(HttpServletResponse response) throws Exception {
        List<PurchaseResponseDTO> purchases = purchaseService.getAllPurchases(); // Make sure this method exists

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=purchase-report.pdf");

        Document document = new Document();
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);
        Font normalFont = FontFactory.getFont(FontFactory.HELVETICA, 12);

        document.add(new Paragraph("Purchase Report", titleFont));
        document.add(new Paragraph("Generated on: " + LocalDate.now(), normalFont));
        document.add(new Paragraph(" "));

        PdfPTable table = new PdfPTable(7); // Adjust based on your fields
        table.setWidthPercentage(100);
        table.setSpacingBefore(10);

        table.addCell("ID");
        table.addCell("Drug");
        table.addCell("Quantity");
        table.addCell("Batch");
        table.addCell("Purchase Date");
        table.addCell("ExpiryDate");
        table.addCell("Distributor");

        for (PurchaseResponseDTO purchase : purchases) {
            table.addCell(String.valueOf(purchase.getId()));
            table.addCell(purchase.getDrugName());
            table.addCell(String.valueOf(purchase.getQuantity()));
            table.addCell(purchase.getBatch());
            table.addCell(String.valueOf(purchase.getPurchaseDate()));
            table.addCell(String.valueOf(purchase.getExpiryDate()));
            table.addCell(purchase.getDistributorName());
        }

        document.add(table);
        document.close();
    }

}
