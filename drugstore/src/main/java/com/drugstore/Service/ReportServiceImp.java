package com.drugstore.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drugstore.DTO.ExpiryReportDTO;
import com.drugstore.DTO.PurchaseReportDTO;
import com.drugstore.DTO.ReportFilterDTO;
import com.drugstore.DTO.RevenueReportDTO;
import com.drugstore.DTO.SalesReportDTO;
import com.drugstore.Repository.CustomerOrderRepository;
import com.drugstore.Repository.DrugRepository;
import com.drugstore.Repository.OrderItemRepository;
import com.drugstore.Repository.PurchaseRepository;


@Service
public class ReportServiceImp implements ReportService{

    @Autowired
    private DrugRepository drugRepo;
    @Autowired
    private OrderItemRepository orderItemRepo;
    @Autowired
    private CustomerOrderRepository orderRepo;
    @Autowired
    private PurchaseRepository purchaseRepo;

	    @Override
	    public List<ExpiryReportDTO> getExpiryReport() {
	        LocalDate cutoff = LocalDate.now().plusDays(30);
			return  drugRepo.getExpiringDrugs(cutoff);

	        
	    }
	    @Override
	    public List<SalesReportDTO> getSalesReport(ReportFilterDTO filter) {
	        LocalDate start = LocalDate.parse(filter.getStartDate());
	        LocalDate end = LocalDate.parse(filter.getEndDate());
	        return orderItemRepo.getSalesSummary(start, end);
	    }
		@Override
		public List<PurchaseReportDTO> getPurchaseReport(ReportFilterDTO filter) {
			 LocalDate start = LocalDate.parse(filter.getStartDate());
		        LocalDate end = LocalDate.parse(filter.getEndDate());
		        
		        
			return purchaseRepo.getPurchaseSummary(start, end);
		}
		@Override
		public List<RevenueReportDTO> getRevenueReport(ReportFilterDTO filter) {
			 LocalDate start = LocalDate.parse(filter.getStartDate());
		        LocalDate end = LocalDate.parse(filter.getEndDate());
		        
		    return orderRepo.getRevenueSummary(start, end) ;
		}

	
}