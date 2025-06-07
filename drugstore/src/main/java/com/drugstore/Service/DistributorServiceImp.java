package com.drugstore.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.drugstore.DTO.DistributorReqDTO;
import com.drugstore.DTO.DistributorResDTO;
import com.drugstore.Model.Distributor;
import com.drugstore.Repository.DistributorRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DistributorServiceImp implements DistributorService {

    @Autowired
    private DistributorRepository distributorRepository;

   

    @Override
    public DistributorResDTO saveDistributor(DistributorReqDTO dto) {
    	Distributor d=new Distributor();
    	d.setName(dto.getName());
        d.setEmail(dto.getEmail());
        d.setPhone(dto.getPhone());
        d.setAddress(dto.getAddress());
        d.setLicenseNumber(dto.getLicenseNumber());
    	
        Distributor saved = distributorRepository.save(d);
        
        return new DistributorResDTO(
                saved.getId(),
                saved.getName(),
                saved.getEmail(),
                saved.getPhone(),
                saved.getAddress(),
                saved.getLicenseNumber());
    }

    @Override
    public DistributorResDTO updateDistributor(long id, DistributorReqDTO dto) {
        Distributor existing = distributorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Distributor not found"));

        existing.setName(dto.getName());
        existing.setEmail(dto.getEmail());
        existing.setPhone(dto.getPhone());
        existing.setAddress(dto.getAddress());
        existing.setLicenseNumber(dto.getLicenseNumber());

        Distributor saved = distributorRepository.save(existing);
        return new DistributorResDTO(
                saved.getId(),
                saved.getName(),
                saved.getEmail(),
                saved.getPhone(),
                saved.getAddress(),
                saved.getLicenseNumber());
    }

    @Override
    public DistributorResDTO getDistributorById(long id) {
        Distributor saved= distributorRepository.findById(id).orElseThrow(() -> new RuntimeException("Drug not found with ID: " + id));
        return new DistributorResDTO(
                saved.getId(),
                saved.getName(),
                saved.getEmail(),
                saved.getPhone(),
                saved.getAddress(),
                saved.getLicenseNumber());
    }

    @Override
    public List<DistributorResDTO> getAllDistributors() {
    	List<Distributor> d=distributorRepository.findAll();

		
        return d.stream()
                .map(dis-> new DistributorResDTO(
                		dis.getId(),
                        dis.getName(),
                        dis.getEmail(),
                        dis.getPhone(),
                        dis.getAddress(),
                        dis.getLicenseNumber())).collect(Collectors.toList());
                
    }

    @Override
    public String deleteDistributor(long id) {
        distributorRepository.deleteById(id);
        return "Deleted Successfully....";
    }

    @Override
    public List<DistributorResDTO> searchDistributors(String name) {
            List<Distributor> d=distributorRepository.findByNameContainingIgnoreCase(name);

		
        return d.stream()
                .map(dis-> new DistributorResDTO(
                		dis.getId(),
                        dis.getName(),
                        dis.getEmail(),
                        dis.getPhone(),
                        dis.getAddress(),
                        dis.getLicenseNumber())).collect(Collectors.toList());
    }
    

	
}
