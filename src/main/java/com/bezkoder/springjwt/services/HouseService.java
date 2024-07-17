package com.bezkoder.springjwt.services;

import java.util.List;

import com.bezkoder.springjwt.models.HouseDTO;

public interface HouseService {

    public HouseDTO newHouse(HouseDTO house);
    public HouseDTO update(HouseDTO house);
    public boolean deleteHouseById(String id );
    public HouseDTO getHouseById(String id);
    public List<HouseDTO> getAllHouses();
    public List<HouseDTO> getHousesByUserId(String id); //get by owner
    public List<HouseDTO> getAllFreeHouses();
    public List<HouseDTO> getAllHousesByScala(String scala);
    public List<HouseDTO> getAllHousesByPiano(int piano);
    public List<HouseDTO> getAllHousesByScalaAndPiano(String scala, int piano);
    public List<HouseDTO> getAllHousesByScalaAndPianoAndInterno(String scala, int piano,int interno);
    public List<HouseDTO>getAllHousesByScalaAndPianoAndInternoAndName(String scala,int piano, int interno,String name);
    public List<HouseDTO>getAllHousesByScalaAndPianoAndInternoAndNameAndSurname(String scala,int piano, int interno,String name,String surname);
    public List<HouseDTO>getAllHousesByInterno(int interno);
    public List<HouseDTO>getAllHousesByInternoAndName(int interno,String name);
    public List<HouseDTO>getAllHousesByInternoAndNameAndSurname(int interno,String name,String surname);
    public List<HouseDTO>getAllHousesByPianoAndInternoAndNameAndSurname(int piano,int interno,String name,String surname);
    public List<HouseDTO>getAllHousesByPianoAndInternoAndName(int piano,int interno,String name);
    public List<HouseDTO>getAllHousesByPianoAndInterno(int piano,int interno);
    public List<HouseDTO>getAllHousesByPianoAndNameAndSurname(int piano,String name,String surname);
    public List<HouseDTO>getAllHousesByPianoAndName(int piano,String name);
    public List<HouseDTO> getAllHousesByName(String name);
    public List<HouseDTO> getAllHousesBySurname(String surname);
    public List<HouseDTO>getAllHousesByNameAndSurname(String name, String surname);
    public List<HouseDTO> commandGetHandler(HouseDTO house);
    
    public boolean checkValue(HouseDTO house);
    public boolean checkId(String id);


    
    
}