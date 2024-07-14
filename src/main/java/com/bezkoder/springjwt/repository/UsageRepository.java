package com.bezkoder.springjwt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bezkoder.springjwt.entity.UsageEntity;


@Repository
public interface UsageRepository extends JpaRepository<UsageEntity,String> {

    public List<UsageEntity> findByHouseId(String houseId);
    
}
