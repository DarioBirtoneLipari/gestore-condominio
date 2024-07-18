
package com.bezkoder.springjwt.mapper;

import java.util.Set;

//usato grazie a pom modifcato dipendeenza e plugin
import org.mapstruct.Mapper;

import com.bezkoder.springjwt.entity.UserEntity;
import com.bezkoder.springjwt.models.UserDTO;

@Mapper(componentModel = "spring", uses = HouseMapper.class)
public interface UserMapper {

    Set<UserDTO> map(Set<UserEntity> entities);
	UserEntity dtoToEntity(UserDTO dto);
	UserDTO entityToDto(UserEntity entity);
	

    
}
