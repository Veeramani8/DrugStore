package com.drugstore.Repository;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.drugstore.DTO.RevenueReportDTO;
import com.drugstore.Model.CustomerOrder;



public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Long> {
	@Query("SELECT new com.drugstore.DTO.RevenueReportDTO(o.orderDate, SUM(o.totalAmount), COUNT(o.id), AVG(o.totalAmount)) " +
		       "FROM CustomerOrder o WHERE o.orderDate BETWEEN :start AND :end GROUP BY o.orderDate")
		List<RevenueReportDTO> getRevenueSummary(@Param("start") LocalDate start, @Param("end") LocalDate end);


}
