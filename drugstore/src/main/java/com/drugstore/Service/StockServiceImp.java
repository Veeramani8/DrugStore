package com.drugstore.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drugstore.DTO.StockRequestDTO;
import com.drugstore.DTO.StockResponseDTO;
import com.drugstore.Model.Drug;
import com.drugstore.Model.Stock;
import com.drugstore.Repository.DrugRepository;
import com.drugstore.Repository.StockRepository;

@Service
public class StockServiceImp implements StockService {
	@Autowired
	DrugRepository drugRepository;
	@Autowired
	StockRepository stockRepository;
	

	

	@Override
	public List<StockResponseDTO> getAllStocks() {
		return stockRepository.findAll().stream().map(s->new StockResponseDTO(s.getId(),
				s.getDrug().getId(),
				s.getDrug().getName(),
				s.getQuantity(),
				s.getLastUpdated(),
				s.getExpiryDate(),
				s.getBatch())).collect(Collectors.toList());
	}



	
}