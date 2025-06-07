package com.drugstore.Service;


import java.util.List;


import com.drugstore.DTO.DrugRequestDTO;
import com.drugstore.DTO.DrugResponseDTO;

public interface DrugService {
    DrugResponseDTO saveDrug(DrugRequestDTO dto);
    DrugResponseDTO getDrugById(long id);
    List<DrugResponseDTO> getAllDrugs();
    void deleteDrug(long id);
    List<DrugResponseDTO> searchDrugs(String name);
    DrugResponseDTO updateDrug(long id,DrugRequestDTO request);
    
}
