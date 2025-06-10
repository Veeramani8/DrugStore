package com.drugstore.Controller;

import com.drugstore.DTO.DistributorReqDTO;
import com.drugstore.DTO.DistributorResDTO;
import com.drugstore.Model.Distributor;
import com.drugstore.Service.DistributorService;
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
@RequestMapping("/distributors")
public class DistributorController {

    @Autowired
    private DistributorService distributorService;

    @GetMapping
    public String listDistributors(Model model) {
        List<DistributorResDTO> distributors = distributorService.getAllDistributors();
        model.addAttribute("distributors", distributors); // ✅ Use ResDTO here
        return "distributor-list";
    }


    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("distributor", new DistributorReqDTO());
        return "distributor-form"; // Thymeleaf page
    }

    @PostMapping("/save")
    public String saveDistributor(@ModelAttribute("distributor") DistributorReqDTO dto) {
        distributorService.saveDistributor(dto);
        return "redirect:/distributors";
    }

   

    @GetMapping("/search")
    public String searchDistributors(@RequestParam String name, Model model) {
        List<DistributorResDTO> list = distributorService.searchDistributors(name);
        model.addAttribute("distributors", list);
        return "distributor-list";
    }
    @GetMapping("/download-pdf")
    public void downloadDrugsPdf(HttpServletResponse response) throws Exception {
        List<DistributorResDTO> distributors = distributorService.getAllDistributors();

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=distributor-list.pdf");

        Document document = new Document();
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);
        Paragraph title = new Paragraph("Distributor List", font);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);
        document.add(new Paragraph(" "));

        PdfPTable table = new PdfPTable(6); // 6 columns
        table.setWidthPercentage(100);
        table.addCell("ID");
        table.addCell("Name");
        table.addCell("Email");
        table.addCell("Phone");
        table.addCell("Address");
        table.addCell("LIcenseNumber");

        for (DistributorResDTO distributor : distributors) {
            table.addCell(String.valueOf(distributor.getId()));
            table.addCell(distributor.getName());
            table.addCell(distributor.getEmail());
            table.addCell(distributor.getPhone());
            table.addCell(distributor.getAddress());
            table.addCell(distributor.getLicenseNumber());
        }

        document.add(table);
        document.close();
    }
   


}
