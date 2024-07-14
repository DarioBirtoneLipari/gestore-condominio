package com.bezkoder.springjwt.controllers;


import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.bezkoder.springjwt.models.PaymentDTO;
import com.bezkoder.springjwt.models.ResponseDTO;
import com.bezkoder.springjwt.models.ResponseListDTO;
import com.bezkoder.springjwt.services.PaymentService;



@RestController
@CrossOrigin
@RequestMapping("/payment/")
public class PaymentController {

    @Autowired
    PaymentService paymentService;


   @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_MANAGER','ROLE_ADMIN')")
    @PostMapping("save")
    public ResponseDTO newPayment(@RequestBody PaymentDTO payment){
       ResponseDTO response = new ResponseDTO();
        if(paymentService.checkValue(payment)){
            response.setType("save");
            response.setHTTPstatus("200");
            response.setMessage("payment saved");
            response.setO(payment);
            paymentService.newPayment((PaymentDTO) response.getO());
            return response;}
        else{  
            response.setType("save");
            response.setHTTPstatus("400");
            response.setMessage("failed. Check inputs.");
            response.setO(payment);
            return response;

        }
    }

   @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_MANAGER','ROLE_ADMIN')")
    @PutMapping("putUpdate")
    public ResponseDTO updatePayment(@RequestBody PaymentDTO payment){
        ResponseDTO response = new ResponseDTO();
        if(paymentService.checkValue(payment)){
            response.setType("put update");
            response.setHTTPstatus("200");
            response.setMessage("payment updated");
            response.setO(payment);
            paymentService.update((PaymentDTO) response.getO());
            return response;}
        else{  
            response.setType("put update");
            response.setHTTPstatus("400");
            response.setMessage("failed. Check inputs.");
            response.setO(payment);
            return response;
        }
    }

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_MANAGER','ROLE_ADMIN')")
    @PatchMapping("patchUpdate")
    public ResponseDTO patchUpdatePayment(@RequestBody PaymentDTO payment){
        ResponseDTO response = new ResponseDTO();
        if(paymentService.checkValue(payment)){
            response.setType("patch update");
            response.setHTTPstatus("200");
            response.setMessage("payment updated");
            response.setO(payment);
            paymentService.update((PaymentDTO) response.getO());
            return response;}
        else{  
            response.setType("patch update");
            response.setHTTPstatus("400");
            response.setMessage("failed. Check inputs.");
            response.setO(payment);
            return response;
        }
    }
 

    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_ADMIN')")
    @DeleteMapping("delete/{id}")
    public ResponseDTO deletePaymentById(@PathVariable String id){
         ResponseDTO response = new ResponseDTO();
        if(paymentService.checkId(id)){
            response.setType("delete");
            response.setHTTPstatus("200");
            response.setMessage("payment deleted");
            response.setO((PaymentDTO) paymentService.getPaymentById(id));
            paymentService.deletePaymentById(id);;
            return response;}
        else{  
            response.setType("delete");
            response.setHTTPstatus("400");
            response.setMessage("payment not deleted. Check id");
            return response;

        }
    }

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_MANAGER','ROLE_ADMIN')")
    @GetMapping("get/{id}")
    public ResponseDTO getPaymentById(@PathVariable String id){
         ResponseDTO response = new ResponseDTO();
        if(paymentService.checkId(id)){
            response.setType("get");
            response.setHTTPstatus("200");
            response.setMessage("ok");
            response.setO((PaymentDTO) paymentService.getPaymentById(id));
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
    public ResponseListDTO getAllPayments() {
           ResponseListDTO response = new ResponseListDTO();
        if(paymentService.getAllPayments().size()>0){
            response.setType("get");
            response.setHTTPstatus("200");
            response.setMessage("ok");
            response.setLo(paymentService.getAllPayments().stream()
            .map(obj -> (Object) obj)
            .collect(Collectors.toList()));
            return response;}
        else{  
            response.setType("get");
            response.setHTTPstatus("400");
            response.setMessage("no payments found");
            return response;
        }
    }

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_MANAGER','ROLE_ADMIN')")
    @GetMapping("getAll/byUserId/{userId}")
    public ResponseListDTO getAllpaymentsByUserId(@PathVariable String userId) {
        ResponseListDTO response = new ResponseListDTO();
        if(paymentService.getAllPaymentsByUserId(userId).size()>0){
            response.setType("get");
            response.setHTTPstatus("200");
            response.setMessage("ok");
            response.setLo(paymentService.getAllPaymentsByUserId(userId).stream()
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
    @GetMapping("getAll/byHouseId/{houseId}")
    public ResponseListDTO getAllPaymentsByHouseId(@PathVariable String houseId) {
        ResponseListDTO response = new ResponseListDTO();
        if(paymentService.getAllPaymentsByHouseId(houseId).size()>0){
            response.setType("get");
            response.setHTTPstatus("200");
            response.setMessage("ok");
            response.setLo(paymentService.getAllPaymentsByHouseId(houseId).stream()
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
