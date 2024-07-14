package com.bezkoder.springjwt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bezkoder.springjwt.entity.PaymentEntity;




@Repository
public interface PaymentRepository extends JpaRepository<PaymentEntity,String>{

    public List<PaymentEntity> findByHouseId(String houseId);
    public List<PaymentEntity> findByUserId(String houseId);
    
}
