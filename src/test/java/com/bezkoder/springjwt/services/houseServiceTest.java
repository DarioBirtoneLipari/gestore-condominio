package com.bezkoder.springjwt.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bezkoder.springjwt.models.HouseDTO;
import com.bezkoder.springjwt.models.UserDTO;
import com.bezkoder.springjwt.repository.HouseRepository;

@SpringBootTest
public class houseServiceTest {

    @Autowired
	HouseRepository houseRepository;

	@Autowired
	HouseService houseService;


    @Test
	@Order(1)
	void contextLoads() throws Exception {
		assertThat(this.houseService).isNotNull();
		assertThat(this.houseRepository).isNotNull();
    }

    @Test
    @Order(2)
    void createHouse() throws Exception {
        HouseDTO house = new HouseDTO();
        house.setId("1"); 
        house.setScala("A"); 
        house.setInterno(1);
        house.setPiano(1);
        house.setUser(new UserDTO()) ;
        house.setHouseImg("image");
        HouseDTO houseSaved = this.houseService.newHouse(house);
        assertTrue(houseSaved.getScala().equals(house.getScala()));

    }

    @Test
	@Order(3)
	void getAllFreeHouses() throws Exception{
        List<HouseDTO> houses = houseService.getAllFreeHouses();
        for(HouseDTO house : houses){
            assertThat(house.getUser() == null);
        }
    }

    @Test
    @Order(4)
    void deleteHouse() throws Exception{
        HouseDTO house = new HouseDTO();
        house.setId("1"); 
        house.setScala("A"); 
        house.setInterno(1);
        house.setPiano(1);
        house.setUser(new UserDTO()) ;
        house.setHouseImg("image");
        HouseDTO houseSaved = this.houseService.newHouse(house);
        assertTrue(houseService.deleteHouseById(houseSaved.getId()));
    }

        
    }


