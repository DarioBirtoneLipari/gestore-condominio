package com.bezkoder.springjwt.mapper;

import java.util.Set;

import org.mapstruct.Mapper;

import com.bezkoder.springjwt.entity.UsageEntity;
import com.bezkoder.springjwt.models.UsageDTO;

@Mapper(componentModel = "spring")
public interface UsageMapper {

    Set<UsageDTO> map(Set<UsageEntity> entities);
	UsageEntity dtoToEntity(UsageDTO dto);
	UsageDTO entityToDto(UsageEntity entity);
    
}
