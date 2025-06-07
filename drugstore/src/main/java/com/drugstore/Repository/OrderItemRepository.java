package com.drugstore.Repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.drugstore.DTO.SalesReportDTO;
import com.drugstore.Model.OrderItem;


@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
	@Query("SELECT new com.drugstore.DTO.SalesReportDTO(oi.drug.name, SUM(oi.quantity), SUM(oi.quantity * oi.price)) " +
		       "FROM OrderItem oi " +
		       "WHERE oi.order.orderDate BETWEEN :start AND :end " +
		       "GROUP BY oi.drug.name")
		List<SalesReportDTO> getSalesSummary(@Param("start") LocalDate start, @Param("end") LocalDate end);

	@Query("SELECT oi.drug.name, SUM(oi.quantity) FROM OrderItem oi GROUP BY oi.drug.name ORDER BY SUM(oi.quantity) DESC")
	List<Object[]> findTopSellingDrugs();


  
}