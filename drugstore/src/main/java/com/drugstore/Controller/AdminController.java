package com.drugstore.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.drugstore.DTO.DistributorReqDTO;
import com.drugstore.DTO.DistributorResDTO;
import com.drugstore.DTO.DrugRequestDTO;
import com.drugstore.DTO.DrugResponseDTO;
import com.drugstore.Service.DistributorService;
import com.drugstore.Service.DrugService;
import com.drugstore.Service.PurchaseService;

@Controller
@RequestMapping("admin")
public class AdminController{
	 @Autowired
	    private DrugService drugService;
	 @Autowired
	    private DistributorService distributorService;
	 @Autowired
	    private PurchaseService purchaseService;

	  @GetMapping("/editdrug/{id}")
	    public String showEditDrug(@PathVariable Long id, Model model) {
	        DrugResponseDTO drug = drugService.getDrugById(id);
	        DrugRequestDTO dto = new DrugRequestDTO();
	        dto.setName(drug.getName());
	        dto.setComposition(drug.getComposition());
	        dto.setDosage(drug.getDosage());
	        dto.setManufacturer(drug.getManufacturer());
	        dto.setPrice(drug.getPrice());
	        model.addAttribute("drug", dto);
	        model.addAttribute("drugId", id);
	        return "drug-edit-form"; 
	    }

	 @PostMapping("/updatedrug/{id}")
	    public String updateDrug(@PathVariable Long id, @ModelAttribute("drug") DrugRequestDTO dto) {
	        drugService.updateDrug(id, dto);
	        return "redirect:/drugs";
	    }

	    @GetMapping("/deletedrug/{id}")
	    public String deleteDrug(@PathVariable Long id) {
	        drugService.deleteDrug(id);
	        return "redirect:/drugs";
	    }
	    @GetMapping("/editdistributor/{id}")
	    public String showEditDistributor(@PathVariable long id, Model model) {
	        DistributorResDTO dto = distributorService.getDistributorById(id);
	        model.addAttribute("distributor", dto);
	        return "distributor-edit";
	    }

	    @PostMapping("/updatedistributor/{id}")
	    public String updateDistributor(@PathVariable long id, @ModelAttribute("distributor") DistributorReqDTO dto) {
	        distributorService.updateDistributor(id, dto);
	        return "redirect:/distributors";
	    }

	    @GetMapping("/deletedistributor/{id}")
	    public String deleteDistributor(@PathVariable long id) {
	        distributorService.deleteDistributor(id);
	        return "redirect:/distributors";
	    }
	    @GetMapping("/deletepurchase/{id}")
	    public String deletePurchase(@PathVariable long id) {
	        purchaseService.deletePurchase(id);
	        return "redirect:/purchases";
	    }
	
}