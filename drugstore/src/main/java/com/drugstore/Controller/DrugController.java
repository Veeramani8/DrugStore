package com.drugstore.Controller;

import com.drugstore.DTO.DrugRequestDTO;
import com.drugstore.DTO.DrugResponseDTO;
import com.drugstore.Service.DrugService;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
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

import java.util.List;

@Controller
@RequestMapping("/drugs")
public class DrugController {

    @Autowired
    private DrugService drugService;

    @GetMapping
    public String listDrugs(Model model) {
        List<DrugResponseDTO> drugs = drugService.getAllDrugs();
        model.addAttribute("drugs", drugs);
        return "drug-list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("drug", new DrugRequestDTO());
        return "drug-form"; 
    }
    @PostMapping("/add")
    public String addDrug(@ModelAttribute("drug") DrugRequestDTO dto) {
        drugService.saveDrug(dto);
        return "redirect:/drugs";
    }
  
  

   
    @GetMapping("/download-pdf")
    public void downloadDrugsPdf(HttpServletResponse response) throws Exception {
        List<DrugResponseDTO> drugs = drugService.getAllDrugs();

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=drug-list.pdf");

        Document document = new Document();
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);
        Paragraph title = new Paragraph("Drug List", font);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);
        document.add(new Paragraph(" "));

        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(100);
        table.addCell("ID");
        table.addCell("Name");
        table.addCell("Composition");
        table.addCell("Dosage");
        table.addCell("Manufacturer");
        table.addCell("Price");

        for (DrugResponseDTO drug : drugs) {
            table.addCell(String.valueOf(drug.getId()));
            table.addCell(drug.getName());
            table.addCell(drug.getComposition());
            table.addCell(drug.getDosage());
            table.addCell(drug.getManufacturer());
            table.addCell(String.valueOf(drug.getPrice()));
        }

        document.add(table);
        document.close();
    }

}
