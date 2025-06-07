package com.drugstore.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.drugstore.DTO.PurchaseReportDTO;
import com.drugstore.DTO.SalesReportDTO;
import com.drugstore.Model.Purchase;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

	@Query("SELECT new com.drugstore.DTO.PurchaseReportDTO(p.drug.name, SUM(p.quantity), SUM(p.drug.price*p.quantity), p.purchaseDate) " +
		       "FROM Purchase p " +
		       "WHERE p.purchaseDate BETWEEN :start AND :end " +
		       "GROUP BY p.drug.name, p.purchaseDate")
		List<PurchaseReportDTO> getPurchaseSummary(@Param("start") LocalDate start, @Param("end") LocalDate end);
}