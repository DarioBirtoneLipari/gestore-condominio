package com.bezkoder.springjwt.services.Impl;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bezkoder.springjwt.entity.UsageEntity;
import com.bezkoder.springjwt.mapper.UsageMapper;
import com.bezkoder.springjwt.models.UsageDTO;
import com.bezkoder.springjwt.repository.UsageRepository;
import com.bezkoder.springjwt.services.UsageService;

@Service
public class UsageServiceImpl implements UsageService {

    @Autowired
    UsageRepository usageRepository;

    @Autowired
    UsageMapper usageMapper;

    @Override
    public UsageDTO newUsage(UsageDTO usage) {
        UsageEntity ue = usageMapper.dtoToEntity(usage);
        ue = usageRepository.save(ue);
        return usageMapper.entityToDto(ue);
    }

    @Override
    public UsageDTO update(UsageDTO usage) {
        UsageEntity oe = usageMapper.dtoToEntity(usage); // utente con alcuni campi null
        UsageEntity entity = usageMapper.dtoToEntity(getUsageById(oe.getId())); // utente con tutti i campi non nulli
        List<String> intFields = Arrays.asList("water","gas");
        //TODO date type and external key ?
        for (Field field : UsageEntity.class.getDeclaredFields()) {
            try {
                field.setAccessible(true);
                if (intFields.contains(field.getName())) {
                    int value = (int) field.get(oe);
                    if (value > 0) {
                        field.set(entity, value);
                    }
                }
                    
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        entity = usageRepository.save(entity);
        return usageMapper.entityToDto(entity);
    }

    @Override
    public boolean deleteUsageById(String id) {
        if (usageRepository.existsById(id)) {
            usageRepository.deleteById(id);
            return true;
        }
        return false;
    }

    
    @Override
    public UsageDTO getUsageById(String id) {
        Optional<UsageEntity> ue = usageRepository.findById(id);
        if (ue.isPresent()) {
            return usageMapper.entityToDto(ue.get());
        } else {
            UsageDTO usage = new UsageDTO();
            return usage;
        }
    }

    @Override
    public List<UsageDTO> getAllUsages() {
        return usageRepository.findAll()
                .stream().map(usageMapper::entityToDto)
                .toList();
    }

    @Override
    public boolean checkValue(UsageDTO usage) {
        Date today = new Date();
        if (usage.getHouse().getId().length() > 0 &&
                usage.getDate().before(today) &&
                usage.getWater() > 0 &&
                usage.getGas() > 0)
        {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean checkId(String id) {
        if (id.length() > 0 && usageRepository.existsById(id)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<UsageDTO> getAllUsagesByHouseId(String houseId) {
        return usageRepository.findByHouseId(houseId)
        .stream().map(usageMapper::entityToDto)
        .toList();
    }
}
