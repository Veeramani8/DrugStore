package com.drugstore.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drugstore.DTO.DrugRequestDTO;
import com.drugstore.DTO.DrugResponseDTO;
import com.drugstore.Model.Drug;
import com.drugstore.Repository.DrugRepository;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class DrugServiceImp implements DrugService {

    @Autowired
    private DrugRepository drugRepository;
    


    

    

    @Override
    public DrugResponseDTO saveDrug(DrugRequestDTO dto) {
    	Drug d = new Drug();
        d.setName(dto.getName());
        d.setComposition(dto.getComposition());
        d.setDosage(dto.getDosage());
        d.setManufacturer(dto.getManufacturer());
        d.setPrice(dto.getPrice());
        Drug saved = drugRepository.save(d);
        
        return new DrugResponseDTO(
        		saved.getId(),
        		saved.getName(),
        		saved.getComposition(),
        		saved.getDosage(),
        		saved.getManufacturer(),
        		saved.getPrice());
    }

    @Override
    public DrugResponseDTO getDrugById(long id) {
        Drug saved = drugRepository.getReferenceById(id);
        System.out.println(saved);
		return new DrugResponseDTO(
				saved.getId(),
				saved.getName(),
				saved.getComposition(),
				saved.getDosage(),
				saved.getManufacturer(),
				saved.getPrice());

    }

    @Override
    public List<DrugResponseDTO> getAllDrugs() {
         List<Drug> d=drugRepository.findAll();

		
         return d.stream()
                 .map(dru -> new DrugResponseDTO(
                         dru.getId(),
                         dru.getName(),
                         dru.getComposition(),
                         dru.getDosage(),
                         dru.getManufacturer(),
                         dru.getPrice())).collect(Collectors.toList());
    }

    

    @Override
    public List<DrugResponseDTO> searchDrugs(String name) {
        return drugRepository.findByNameContainingIgnoreCase(name).stream().map(dru -> new DrugResponseDTO(
                dru.getId(),
                dru.getName(),
                dru.getComposition(),
                dru.getDosage(),
                dru.getManufacturer(),
                dru.getPrice()
        )).collect(Collectors.toList());
    }
    @Override
    public void deleteDrug(long id) {
        drugRepository.deleteById(id);
    }

	@Override
	public DrugResponseDTO updateDrug(long id,DrugRequestDTO request) {
		Drug existingDrug = drugRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Drug not found with ID"));

	    existingDrug.setName(request.getName());
	    existingDrug.setComposition(request.getComposition());
	    existingDrug.setDosage(request.getDosage());
	    existingDrug.setManufacturer(request.getManufacturer());
	    existingDrug.setPrice(request.getPrice());

	    Drug saved = drugRepository.save(existingDrug);
		return new DrugResponseDTO(
				saved.getId(),
				saved.getName(),
				saved.getComposition(),
				saved.getDosage(),
				saved.getManufacturer(),
				saved.getPrice());

	}
	
}
