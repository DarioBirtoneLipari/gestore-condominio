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
     @Query(value = "select h.* from house h join user u on h.user_id = u.id where u.name = ?1 ", nativeQuery = true)
     public List<HouseEntity> findAllHousesByName(String name);
     @Query(value = "select h.* from house h join user u on h.user_id = u.id where u.surname = ?1 ", nativeQuery = true)
     public List<HouseEntity> findAllHousesBySurname(String surname);
     @Query(value = "select h.* from house h join user u on h.user_id = u.id where u.name = ?1 and u.surname = ?2 ", nativeQuery = true)
     public List<HouseEntity> findAllHousesByNameAndSurname(String name, String surname);
     @Query(value = "select h.* from house h join user u on h.user_id = u.id where h.scala = ?1 and h.piano =?2 and h.interno = ?3 and u.name = ?4  ", nativeQuery = true)
     public List<HouseEntity> findByScalaAndPianoAndInternoAndName(String scala, int piano,int interno,String name);
     @Query(value = "select h.* from house h join user u on h.user_id = u.id where h.scala = ?1 and h.piano =?2 and h.interno = ?3 and u.name = ?4 and u.surname =?5 ", nativeQuery = true)
     public List<HouseEntity> findByScalaAndPianoAndInternoAndNameAndSurname(String scala, int piano,int interno,String name,String surname);
     @Query(value = "select h.* from house h join user u on h.user_id = u.id where h.interno = ?1 and u.name = ?2 ", nativeQuery = true)
     public List<HouseEntity> findByInternoAndName(int interno,String name);
     @Query(value = "select h.* from house h join user u on h.user_id = u.id where h.interno = ?1 and u.name = ?2 and u.surname = ?3", nativeQuery = true)
     public List<HouseEntity> findByInternoAndNameAndSurname(int interno,String name,String surname);
     @Query(value = "select h.* from house h join user u on h.user_id = u.id where h.piano = ?1 and h.interno = ?2 and u.name = ?3 and u.surname = ?4", nativeQuery = true)
     public List<HouseEntity> findByPianoAndInternoAndNameAndSurname(int piano,int interno,String name,String surname);
     @Query(value = "select h.* from house h join user u on h.user_id = u.id where h.piano = ?1 and h.interno = ?2 and u.name = ?3", nativeQuery = true)
     public List<HouseEntity> findByPianoAndInternoAndName(int piano,int interno,String name);
     @Query(value = "select h.* from house h join user u on h.user_id = u.id where h.piano = ?1 and h.interno = ?2", nativeQuery = true)
     public List<HouseEntity> findByPianoAndInterno(int piano,int interno);
     @Query(value = "select h.* from house h join user u on h.user_id = u.id where h.piano = ?1  and u.name = ?2 and u.surname = ?3", nativeQuery = true)
     public List<HouseEntity> findByPianoAndNameAndSurname(int piano,String name,String surname);
     @Query(value = "select h.* from house h join user u on h.user_id = u.id where h.piano = ?1  and u.name = ?2", nativeQuery = true)
     public List<HouseEntity> findByPianoAndName(int piano,String name);



     public List<HouseEntity> findByUserId(String userId);
     public List<HouseEntity> findByScala(String scala);
     public List<HouseEntity> findByPiano(int piano);
     public List<HouseEntity> findByInterno(int Interno);
     public List<HouseEntity> findByScalaAndPiano(String scala, int piano);
     public List<HouseEntity> findByScalaAndPianoAndInterno(String scala, int piano,int interno);
     

}
