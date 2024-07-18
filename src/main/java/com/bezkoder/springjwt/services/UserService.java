package com.bezkoder.springjwt.services;

import java.util.List;

import com.bezkoder.springjwt.models.UserDTO;

public interface UserService {

    public UserDTO newUser(UserDTO user);
    public UserDTO update(UserDTO user);
    public boolean deleteUserById(String id );
    public UserDTO getUserById(String id);
    public List<UserDTO> getAllUsers();
    public UserDTO getUserByEmail(String email);

   
    public boolean checkValue(UserDTO user);
    public boolean checkId(String id);
    
}
