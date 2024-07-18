package com.bezkoder.springjwt.services.Impl;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.bezkoder.springjwt.entity.UserEntity;
import com.bezkoder.springjwt.mapper.HouseMapper;
import com.bezkoder.springjwt.mapper.UserMapper;
import com.bezkoder.springjwt.models.UserDTO;
import com.bezkoder.springjwt.repository.HouseRepository;
import com.bezkoder.springjwt.repository.UserRepository;
import com.bezkoder.springjwt.services.UserService;



@Service
public class UserServiceImpl implements UserService {


    @Autowired
	UserRepository userRepository;

	@Autowired
	UserMapper userMapper;

    @Autowired
    HouseRepository houseRepository;

    @Autowired
    HouseMapper houseMapper;
    
    @Override
    public UserDTO newUser(UserDTO user) {
     UserEntity oe = userMapper.dtoToEntity(user);
		oe = userRepository.save(oe);
		return userMapper.entityToDto(oe);
    }

    @Override
    public UserDTO update(UserDTO user) {
        UserEntity oe = userMapper.dtoToEntity(user); // utente con alcuni campi null
        UserEntity entity = userMapper.dtoToEntity(getUserById(oe.getId())); // utente con tutti i campi non nulli
        List<String> stringFields = Arrays.asList("name", "surname", "email", "password", "cell", "profileImg", "creditCard", "holder");
        List<String> intFields = Arrays.asList("cvv");
    
        //TODO date type
        for (Field field : UserEntity.class.getDeclaredFields()) {
            try {
                field.setAccessible(true);
                if (stringFields.contains(field.getName())) {
                    String value = (String) field.get(oe);
                    if (value != null && value.length() > 0) {
                        field.set(entity, value);
                    }
                } else if (intFields.contains(field.getName())) {
                    int value = (int) field.get(oe);
                    if (value > 0) {
                        field.set(entity, value);
                    }
                }
                    
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        entity = userRepository.save(entity);
        return userMapper.entityToDto(entity);
    }

    @Override
    public boolean deleteUserById(String id) {
        if(userRepository.existsById(id)) {
			userRepository.deleteById(id);
			return true;
		}
		
		return false;
    }

    @Override
    public UserDTO getUserById(String id) {
          Optional<UserEntity> oe = userRepository.findById(id);
		if(oe.isPresent()) {
			return userMapper.entityToDto(oe.get());
		} else {
            UserDTO user = new UserDTO();
			return user;
        }
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
        .stream().map(userMapper::entityToDto)
        .toList();
    }



    @Override
    public UserDTO getUserByEmail(String email){
        Optional<UserEntity> oe = userRepository.findByEmail(email);
		if(oe.isPresent()) {
			return userMapper.entityToDto(oe.get());
		} else {
            UserDTO user = new UserDTO();
			return user;  }
        }

    

    @Override
    public boolean checkValue(UserDTO user){

        if (user.getEmail().length()>0 &&
            user.getPassword().length()>0)
           {
                return true;
        }
        else{
            return false;
        }
    }

    @Override
    public boolean checkId(String id){
        if(id.length()>0 && userRepository.existsById(id))
        {return true;}
        else
        {return false;}
    }

}
