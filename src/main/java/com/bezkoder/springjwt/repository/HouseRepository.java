package com.bezkoder.springjwt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bezkoder.springjwt.entity.HouseEntity;


@Repository
public interface HouseRepository extends JpaRepository<HouseEntity,String> {   
    
     @Query(value = "select * from house h where h.user_id is null ", nativeQuery = true)
     public List<HouseEntity> findAllFreeHouses();

     @Query(value = "select h.* from house h join user u on h.user_id = u.id where u.name = ?1 and u.surname = ?2 ", nativeQuery = true)
     public List<HouseEntity> findAllHousesByName(String name, String surname);


     public List<HouseEntity> findByUserId(String userId);
     public List<HouseEntity> findByScala(String scala);
     public List<HouseEntity> findByPiano(int piano);
     public List<HouseEntity> findByScalaAndPiano(String scala, int piano);
     public List<HouseEntity> findByScalaAndPianoAndInterno(String scala, int piano,int interno);
     

}
