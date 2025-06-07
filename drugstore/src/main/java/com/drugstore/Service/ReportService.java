package com.drugstore.Service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.drugstore.DTO.ExpiryReportDTO;
import com.drugstore.DTO.PurchaseReportDTO;
import com.drugstore.DTO.ReportFilterDTO;
import com.drugstore.DTO.RevenueReportDTO;
import com.drugstore.DTO.SalesReportDTO;

@Service
public interface ReportService {
    List<ExpiryReportDTO> getExpiryReport();
	List<SalesReportDTO> getSalesReport(ReportFilterDTO filter);
	List<PurchaseReportDTO> getPurchaseReport(ReportFilterDTO filter);
	List<RevenueReportDTO> getRevenueReport(ReportFilterDTO filter);

	
}