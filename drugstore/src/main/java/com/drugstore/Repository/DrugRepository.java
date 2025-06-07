package com.drugstore.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.drugstore.DTO.ExpiryReportDTO;
import com.drugstore.Model.Drug;
import com.drugstore.Model.OrderItem;

import java.time.LocalDate;
import java.util.List;

public interface DrugRepository extends JpaRepository<Drug, Long> {
    List<Drug> findByNameContainingIgnoreCase(String name);

   
    @Query("SELECT new com.drugstore.DTO.ExpiryReportDTO(d.name,s.expiryDate,s.quantity,s.batch) " +
    	       "FROM Stock s JOIN s.drug d " +
    	       "WHERE s.expiryDate <= :cutoffDate")
    	List<ExpiryReportDTO> getExpiringDrugs(@Param("cutoffDate") LocalDate cutoffDate);
    




    }

