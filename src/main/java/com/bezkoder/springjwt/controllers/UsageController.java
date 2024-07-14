package com.bezkoder.springjwt.controllers;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.bezkoder.springjwt.models.ResponseDTO;
import com.bezkoder.springjwt.models.ResponseListDTO;
import com.bezkoder.springjwt.models.UsageDTO;
import com.bezkoder.springjwt.services.UsageService;

@RestController
@CrossOrigin
@RequestMapping("/usage/")
public class UsageController {

    @Autowired
    UsageService usageService;

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_MANAGER','ROLE_ADMIN')")
    @PostMapping("save")
    public ResponseDTO newUsage(@RequestBody UsageDTO usage){
         ResponseDTO response = new ResponseDTO();
        if(usageService.checkValue(usage)){
            response.setType("save");
            response.setHTTPstatus("200");
            response.setMessage("usage saved");
            response.setO(usage);
            usageService.newUsage((UsageDTO) response.getO());
            return response;}
        else{  
            response.setType("save");
            response.setHTTPstatus("400");
            response.setMessage("failed. Check inputs.");
            response.setO(usage);
            return response;
        }
    }

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_MANAGER','ROLE_ADMIN')")
    @PutMapping("putUpdate")
    public ResponseDTO putUpdateUsage(@RequestBody UsageDTO usage){
        ResponseDTO response = new ResponseDTO();
        if(usageService.checkValue(usage)){
            response.setType("put update");
            response.setHTTPstatus("200");
            response.setMessage("usage updated");
            response.setO(usage);
            usageService.update((UsageDTO) response.getO());
            return response;}
        else{  
            response.setType("save");
            response.setHTTPstatus("400");
            response.setMessage("Failed: check inputs. ");
            response.setO(usage);
            return response;

        }
    }

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_MANAGER','ROLE_ADMIN')")
    @PatchMapping("update")
    public ResponseDTO patchUpdateUsage(@RequestBody UsageDTO usage){
        ResponseDTO response = new ResponseDTO();
        if(usageService.checkValue(usage)){
            response.setType("patch update");
            response.setHTTPstatus("200");
            response.setMessage("usage updated");
            response.setO(usage);
            usageService.update((UsageDTO) response.getO());
            return response;}
        else{  
            response.setType("patch update");
            response.setHTTPstatus("400");
            response.setMessage("failed. Check inputs.");
            response.setO(usage);
            return response;

        }
    }
 

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_MANAGER','ROLE_ADMIN')")
    @DeleteMapping("delete/{id}")
    public ResponseDTO deleteUsageById(@PathVariable String id){
           ResponseDTO response = new ResponseDTO();
        if(usageService.checkId(id)){
            response.setType("delete");
            response.setHTTPstatus("200");
            response.setMessage("usage deleted");
            response.setO((UsageDTO) usageService.getUsageById(id));
            usageService.deleteUsageById(id);;
            return response;}
        else{  
            response.setType("delete");
            response.setHTTPstatus("400");
            response.setMessage("usage not deleted. Check id");
            return response;

        }
    }

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_MANAGER','ROLE_ADMIN')")
    @GetMapping("get/{id}")
    public ResponseDTO getUsageById(@PathVariable String id){
           ResponseDTO response = new ResponseDTO();
        if(usageService.checkId(id)){
            response.setType("get");
            response.setHTTPstatus("200");
            response.setMessage("ok");
            response.setO((UsageDTO) usageService.getUsageById(id));
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
    public ResponseListDTO getAllUsages() {
          ResponseListDTO response = new ResponseListDTO();
        if(usageService.getAllUsages().size()>0){
            response.setType("get");
            response.setHTTPstatus("200");
            response.setMessage("ok");
            response.setLo(usageService.getAllUsages().stream()
            .map(obj -> (Object) obj)
            .collect(Collectors.toList()));
            return response;}
        else{  
            response.setType("get");
            response.setHTTPstatus("400");
            response.setMessage("no usages found");
            return response;
        }
    }

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_MANAGER','ROLE_ADMIN')")
    @GetMapping("getAll/{houseId}")
    public ResponseListDTO getAllUsages(@PathVariable String houseId) {
        ResponseListDTO response = new ResponseListDTO();
        if(usageService.getAllUsagesByHouseId(houseId).size()>0){
            response.setType("get");
            response.setHTTPstatus("200");
            response.setMessage("ok");
            response.setLo(usageService.getAllUsagesByHouseId(houseId).stream()
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
