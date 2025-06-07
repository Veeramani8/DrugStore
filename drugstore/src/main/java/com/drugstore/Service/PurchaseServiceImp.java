package com.drugstore.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drugstore.DTO.PurchaseRequestDTO;
import com.drugstore.DTO.PurchaseResponseDTO;
import com.drugstore.Model.Distributor;
import com.drugstore.Model.Drug;
import com.drugstore.Model.Purchase;
import com.drugstore.Model.Stock;
import com.drugstore.Repository.DistributorRepository;
import com.drugstore.Repository.DrugRepository;
import com.drugstore.Repository.PurchaseRepository;
import com.drugstore.Repository.StockRepository;
@Service
public class PurchaseServiceImp implements PurchaseService{
	@Autowired
	DrugRepository drugRepository;
	@Autowired  
	DistributorRepository distributorRepository;
	@Autowired
	PurchaseRepository purchaseRepository;
	@Autowired
	StockRepository stockRepository;

	@Override
	public PurchaseResponseDTO savePurchase(PurchaseRequestDTO dto) {
		Drug drug=drugRepository.findById(dto.getDrugId()).orElseThrow(() -> new RuntimeException("Drug not found"));
		Distributor distributor=distributorRepository.findById(dto.getDistributorId())
				.orElseThrow(() -> new RuntimeException("Drug not found"));
		
		Purchase purchase=new Purchase();
		purchase.setDrug(drug);
		purchase.setDistributor(distributor);
		purchase.setQuantity(dto.getQuantity());
		purchase.setPurchaseDate(dto.getPurchaseDate());
		purchase.setExpiryDate(dto.getExpiryDate());
		purchase.setBatch(dto.getBatch());
		
        Optional<Stock> existingStock = stockRepository.findByDrug(drug);

		if (existingStock.isPresent()) {
            Stock stock = existingStock.get();
            stock.setQuantity(stock.getQuantity() + dto.getQuantity());
            stock.setLastUpdated(dto.getPurchaseDate());
            stock.setExpiryDate(dto.getExpiryDate());
            stock.setBatch(dto.getBatch());;
            stockRepository.save(stock);
        } else {
            Stock stock = new Stock();
            stock.setDrug(drug);
            stock.setQuantity(dto.getQuantity());
            stock.setLastUpdated(dto.getPurchaseDate());
            stock.setExpiryDate(dto.getExpiryDate());
            stock.setBatch(dto.getBatch());
            stockRepository.save(stock);
        }
		
		Purchase p =purchaseRepository.save(purchase);
		
		return new PurchaseResponseDTO(p.getId(),
				p.getQuantity(),
				p.getPurchaseDate(),
				p.getDrug().getName(),
				p.getDistributor().getName(),
				p.getExpiryDate(),
				p.getBatch());
				
	}

	@Override
	public List<PurchaseResponseDTO> getAllPurchases() {
		return purchaseRepository.findAll().stream().map(p-> new PurchaseResponseDTO(p.getId(),
				p.getQuantity(),
				p.getPurchaseDate(),
				p.getDrug().getName(),
				p.getDistributor().getName(),
				p.getExpiryDate(),
				p.getBatch()))
				.collect(Collectors.toList());	
	}
	

	@Override
	public PurchaseResponseDTO getPurchaseById(long id) {
		
		Purchase p=purchaseRepository.findById(id).orElseThrow(() -> new RuntimeException("Drug not found"));
		return new PurchaseResponseDTO(p.getId(),
				p.getQuantity(),
				p.getPurchaseDate(),
				p.getDrug().getName(),
				p.getDistributor().getName(),
				p.getExpiryDate(),
				p.getBatch());
	}

	@Override
	public void deletePurchase(long id) {
		purchaseRepository.deleteById(id);
		
		
	}
	
}