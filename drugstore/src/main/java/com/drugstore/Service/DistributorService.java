package com.drugstore.Service;


import java.util.List;

import com.drugstore.DTO.DistributorReqDTO;
import com.drugstore.DTO.DistributorResDTO;

public interface DistributorService {
    DistributorResDTO saveDistributor(DistributorReqDTO dto);
    DistributorResDTO updateDistributor(long id, DistributorReqDTO dto);
    DistributorResDTO getDistributorById(long id);
    List<DistributorResDTO> getAllDistributors();
    String deleteDistributor(long id);
    List<DistributorResDTO> searchDistributors(String name);
}
