package com.bezkoder.springjwt.controllers;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.bezkoder.springjwt.models.ResponseDTO;
import com.bezkoder.springjwt.models.ResponseListDTO;
import com.bezkoder.springjwt.models.UserDTO;
import com.bezkoder.springjwt.services.HouseService;
import com.bezkoder.springjwt.services.UserService;

@RestController
@CrossOrigin
@RequestMapping("/user/")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    HouseController houseController;

    @Autowired
    HouseService houseService;

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_MANAGER','ROLE_ADMIN')")
    @PutMapping("putUpdate")
    public ResponseDTO putUpdateUser(@RequestBody UserDTO user){
        ResponseDTO response = new ResponseDTO();
        if(userService.checkValue(user)){
            response.setType("put updte");
            response.setHTTPstatus("200");
            response.setMessage("user updted");
            response.setO(user);
            userService.newUser((UserDTO) response.getO());
            return response;}
        else{  
            response.setType("put update");
            response.setHTTPstatus("400");
            response.setMessage("failed. Check inputs.");
            response.setO(user);
            return response;

        }
    }

    
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_MANAGER','ROLE_ADMIN')")
    @PatchMapping("patchUpdate")
    public ResponseDTO updateUser(@RequestBody UserDTO user){
        ResponseDTO response = new ResponseDTO();
        if(userService.checkValue(user)){
            response.setType("patch update");
            response.setHTTPstatus("200");
            response.setMessage("user updated");
            response.setO(user);
            userService.newUser((UserDTO) response.getO());
            return response;}
        else{  
            response.setType("patch update");
            response.setHTTPstatus("400");
            response.setMessage("failed. Check inputs.");
            response.setO(user);
            return response;
        }
    }


    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_ADMIN')")
    @DeleteMapping("delete/{id}")
    public ResponseDTO deleteUserById(@PathVariable String id){ 
        ResponseDTO response = new ResponseDTO();
        if(userService.checkId(id)){
            response.setType("delete");
            response.setHTTPstatus("200");
            response.setMessage("user deleted");
            response.setO((UserDTO) userService.getUserById(id));
            userService.deleteUserById(id);;
            return response;}
        else{  
            response.setType("delete");
            response.setHTTPstatus("400");
            response.setMessage("user not deleted. Check id");
            return response;

        }
      
    }

    
    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_ADMIN')")
    @GetMapping("get/{id}")
    public ResponseDTO getUserById(@PathVariable String id){
        ResponseDTO response = new ResponseDTO();
        if(userService.checkId(id)){
            response.setType("get");
            response.setHTTPstatus("200");
            response.setMessage("ok");
            response.setO((UserDTO) userService.getUserById(id));
            return response;}
        else{  
            response.setType("failed");
            response.setHTTPstatus("400");
            response.setMessage("ko");
            return response;
        }
    }

    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_ADMIN')")
    @GetMapping("getAll")
    public ResponseListDTO getAllUsers() {
         ResponseListDTO response = new ResponseListDTO();
        if(userService.getAllUsers().size()>0){
            response.setType("get");
            response.setHTTPstatus("200");
            response.setMessage("ok");
            response.setLo(userService.getAllUsers().stream()
            .map(obj -> (Object) obj)
            .collect(Collectors.toList()));
            return response;}
        else{  
            response.setType("get");
            response.setHTTPstatus("400");
            response.setMessage("no users found");
            return response;
        }
    }

    
    
}
