package com.bezkoder.springjwt.controllers;


import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.springjwt.models.NotificationDTO;
import com.bezkoder.springjwt.models.ResponseDTO;
import com.bezkoder.springjwt.models.ResponseListDTO;
import com.bezkoder.springjwt.services.NotificationService;


    

@RestController
@CrossOrigin
@RequestMapping("/notification/")
public class NotificationController {

    @Autowired
    NotificationService notificationService;


    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_ADMIN')")
    @PostMapping("save")
    public ResponseDTO newNotification(@RequestBody NotificationDTO notification){
        ResponseDTO response = new ResponseDTO();
        if(notificationService.checkValue(notification)){
            response.setType("save");
            response.setHTTPstatus("200");
            response.setMessage("notification saved");
            response.setO(notification);
            notificationService.newNotification((NotificationDTO) response.getO());
            return response;
        }
        else{  
            response.setType("save");
            response.setHTTPstatus("400");
            response.setMessage("failed. Check inputs.");
            response.setO(notification);
            return response;

        }
    }

    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_ADMIN')")
    @PutMapping("putUpdate")
    public ResponseDTO putUpdateNotification(@RequestBody NotificationDTO notification){
        ResponseDTO response = new ResponseDTO();
        if(notificationService.checkValue(notification)){
            response.setType("put update");
            response.setHTTPstatus("200");
            response.setMessage("notification upated");
            response.setO(notification);
            notificationService.update((NotificationDTO) response.getO());
            return response;}
        else{  
            response.setType("save");
            response.setHTTPstatus("400");
            response.setMessage("failed. Check inputs.");
            response.setO(notification);
            return response;
        }

    }

        @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_ADMIN')")
        @PatchMapping("patchUpdate")
        public ResponseDTO patchUpdateNotification(@RequestBody NotificationDTO notification){
            ResponseDTO response = new ResponseDTO();
            if(notificationService.checkValue(notification)){
                response.setType("patch update");
                response.setHTTPstatus("200");
                response.setMessage("notification updated");
                response.setO(notification);
                notificationService.update((NotificationDTO) response.getO());
                return response;}
            else{  
                response.setType("patch udpate");
                response.setHTTPstatus("400");
                response.setMessage("failed. Check inputs.");
                response.setO(notification);
                return response;
    
            }
    }
    
    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_ADMIN')")
    @DeleteMapping("delete/{id}")
    public ResponseDTO deleteNotificationById(@PathVariable String id){
        ResponseDTO response = new ResponseDTO();
        if(notificationService.checkId(id)){
            response.setType("delete");
            response.setHTTPstatus("200");
            response.setMessage("notification deleted");
            response.setO((NotificationDTO) notificationService.getNotificationById(id));
            notificationService.deleteNotificationById(id);;
            return response;}
        else{  
            response.setType("delete");
            response.setHTTPstatus("400");
            response.setMessage("notification not deleted. Check id");
            return response;

        }
    }

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_MANAGER','ROLE_ADMIN')")
    @GetMapping("get/{id}")
    public ResponseDTO getNotificationById(@PathVariable String id){
         ResponseDTO response = new ResponseDTO();
        if(notificationService.checkId(id)){
            response.setType("get");
            response.setHTTPstatus("200");
            response.setMessage("ok");
            response.setO((NotificationDTO) notificationService.getNotificationById(id));
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
    public ResponseListDTO getAllNotifications() {
         ResponseListDTO response = new ResponseListDTO();
        if(notificationService.getAllNotifications().size()>0){
            response.setType("get");
            response.setHTTPstatus("200");
            response.setMessage("ok");
            response.setLo(notificationService.getAllNotifications().stream()
            .map(obj -> (Object) obj)
            .collect(Collectors.toList()));
            return response;}
        else{  
            response.setType("get");
            response.setHTTPstatus("400");
            response.setMessage("no notifications found");
            return response;
        }
    }


    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_MANAGER','ROLE_ADMIN')")
    @GetMapping("getAll/personal/{userId}")
    public ResponseListDTO getAllPersonalNotification(@PathVariable String userId) {
        ResponseListDTO response = new ResponseListDTO();
        if(notificationService.getNotificationsByUserId(userId).size()>0){
            response.setType("get");
            response.setHTTPstatus("200");
            response.setMessage("ok");
            response.setLo(notificationService.getNotificationsByUserId(userId).stream()
            .map(obj -> (Object) obj)
            .collect(Collectors.toList()));
            return response;}
        else{  
            response.setType("get");
            response.setHTTPstatus("400");
            response.setMessage("no notifications found");
            return response;
        }
    }


    
    
}

