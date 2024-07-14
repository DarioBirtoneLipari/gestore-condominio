package com.bezkoder.springjwt.mapper;

import java.util.Set;

import org.mapstruct.Mapper;

import com.bezkoder.springjwt.entity.PaymentEntity;
import com.bezkoder.springjwt.models.PaymentDTO;


@Mapper(componentModel = "spring")
public interface PaymentMapper {

    Set<PaymentDTO> map(Set<PaymentEntity> entities);
	PaymentEntity dtoToEntity(PaymentDTO dto);
	PaymentDTO entityToDto(PaymentEntity entity);
	
    
}
