package com.bezkoder.springjwt.services;

import java.util.List;

import com.bezkoder.springjwt.models.UsageDTO;



public interface UsageService {

    public UsageDTO newUsage(UsageDTO usage);
    public UsageDTO update(UsageDTO usage);
    public boolean deleteUsageById(String id );
    public UsageDTO getUsageById(String id);
    public List<UsageDTO> getAllUsages();
    public List<UsageDTO> getAllUsagesByHouseId(String houseId);

    public boolean checkValue(UsageDTO usage);
    public boolean checkId(String id);
    
}
