package com.drugstore.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.drugstore.Model.Distributor;

import java.util.List;

public interface DistributorRepository extends JpaRepository<Distributor, Long> {
    List<Distributor> findByNameContainingIgnoreCase(String name);
}
