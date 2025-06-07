package com.drugstore.Service;


import com.drugstore.DTO.StockResponseDTO;

import java.util.List;


public interface StockService {
    List<StockResponseDTO> getAllStocks();
}
