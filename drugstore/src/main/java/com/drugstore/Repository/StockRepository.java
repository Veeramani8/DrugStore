package com.drugstore.Repository;

import com.drugstore.DTO.StockResponseDTO;
import com.drugstore.Model.Drug;
import com.drugstore.Model.Stock;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StockRepository extends JpaRepository<Stock, Long> {

	Optional<Stock> findByDrug(Drug drug);

	    @Query("SELECT new com.drugstore.DTO.StockResponseDTO(s.drug.name, SUM(s.quantity)) FROM Stock s GROUP BY s.drug.name")
	    List<StockResponseDTO> getStockDistribution();
	


}
