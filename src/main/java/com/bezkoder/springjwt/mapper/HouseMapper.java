package com.bezkoder.springjwt.mapper;

import java.util.Set;

import org.mapstruct.Mapper;

import com.bezkoder.springjwt.entity.HouseEntity;
import com.bezkoder.springjwt.models.HouseDTO;


@Mapper(componentModel = "spring")
public interface HouseMapper {

	
	Set<HouseDTO> map(Set<HouseEntity> entities);
	HouseEntity dtoToEntity(HouseDTO dto);
	HouseDTO entityToDto(HouseEntity entity);
	
}

    
