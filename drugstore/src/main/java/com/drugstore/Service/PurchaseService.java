package com.drugstore.Service;


import java.util.List;


import com.drugstore.DTO.PurchaseRequestDTO;
import com.drugstore.DTO.PurchaseResponseDTO;

public interface PurchaseService {
    PurchaseResponseDTO savePurchase(PurchaseRequestDTO dto);
    List<PurchaseResponseDTO> getAllPurchases();
    PurchaseResponseDTO getPurchaseById(long id);
    void deletePurchase(long id);
}
