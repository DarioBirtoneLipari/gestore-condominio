package com.bezkoder.springjwt.services;

import java.util.List;

import com.bezkoder.springjwt.models.PaymentDTO;


public interface PaymentService {

    public PaymentDTO newPayment(PaymentDTO payment);
    public PaymentDTO update(PaymentDTO payment);
    public boolean deletePaymentById(String id );
    public PaymentDTO getPaymentById(String id);
    public List<PaymentDTO> getAllPayments();
    public List<PaymentDTO> getAllPaymentsByHouseId(String houseId);
    public List<PaymentDTO> getAllPaymentsByUserId(String userId);


    public boolean checkValue(PaymentDTO payment);
    public boolean checkId(String id);
    
    
}

