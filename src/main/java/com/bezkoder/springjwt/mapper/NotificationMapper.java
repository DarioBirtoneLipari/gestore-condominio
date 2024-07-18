
package com.bezkoder.springjwt.mapper;

import java.util.Set;

import org.mapstruct.Mapper;

import com.bezkoder.springjwt.entity.NotificationEntity;
import com.bezkoder.springjwt.models.NotificationDTO;



@Mapper(componentModel = "spring")
public interface NotificationMapper {

    
    Set<NotificationDTO> map(Set<NotificationEntity> entities);
	NotificationEntity dtoToEntity(NotificationDTO dto);
	NotificationDTO entityToDto(NotificationEntity entity);

    
}
