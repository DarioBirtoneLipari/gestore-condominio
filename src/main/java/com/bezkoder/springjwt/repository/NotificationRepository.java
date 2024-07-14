package com.bezkoder.springjwt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bezkoder.springjwt.entity.NotificationEntity;


@Repository
public interface NotificationRepository extends JpaRepository<NotificationEntity,String> {

    @Query(value = "select * from notification n where n.user_id = ?1 or n.user_id is null ", nativeQuery = true)
    public List<NotificationEntity> findAllPersonalNotifcation(String userId);
    
}
