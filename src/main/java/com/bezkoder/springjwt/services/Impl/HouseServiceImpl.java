package com.bezkoder.springjwt.services.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bezkoder.springjwt.entity.HouseEntity;
import com.bezkoder.springjwt.mapper.HouseMapper;
import com.bezkoder.springjwt.models.HouseDTO;
import com.bezkoder.springjwt.repository.HouseRepository;
import com.bezkoder.springjwt.services.HouseService;

@Service
public class HouseServiceImpl implements HouseService {


    @Autowired
	HouseRepository houseRepository;

	@Autowired
	HouseMapper houseMapper;
    
    
    @Override
    public HouseDTO newHouse(HouseDTO house) {
        HouseEntity he = houseMapper.dtoToEntity(house);
		he = houseRepository.save(he);
		return houseMapper.entityToDto(he);
    }

    @Override
    public HouseDTO update(HouseDTO house) {
        HouseEntity he = houseMapper.dtoToEntity(house);
		he = houseRepository.save(he);
		return houseMapper.entityToDto(he);
    }

   

    @Override
    public boolean deleteHouseById(String id) {
        if(houseRepository.existsById(id)) {
			houseRepository.deleteById(id);
			return true;
		}
		
		return false;
	}

    @Override
    public HouseDTO getHouseById(String id) {
        Optional<HouseEntity> he = houseRepository.findById(id);
		if(he.isPresent()) {
			return houseMapper.entityToDto(he.get());
		} else {
			  HouseDTO house = new HouseDTO();
			return house;
		}
    }

    @Override
    public List<HouseDTO> getAllHouses() {
        return houseRepository.findAll()
				.stream().map(houseMapper::entityToDto)
				.toList();
    }

    @Override
    public List<HouseDTO> getHousesByUserId(String userId){
        return houseRepository.findByUserId(userId)
        .stream().map(houseMapper::entityToDto)
        .toList();
    }

    @Override
    public List<HouseDTO> getAllFreeHouses() {
        return houseRepository.findAllFreeHouses()
        .stream().map(houseMapper::entityToDto)
        .toList();
    }

    @Override
    public List<HouseDTO>getAllHousesByScala(String scala){
        return houseRepository.findByScala(scala)
        .stream().map(houseMapper::entityToDto)
        .toList();
    }

    @Override
    public List<HouseDTO>getAllHousesByPiano(int piano){
        return houseRepository.findByPiano(piano)
        .stream().map(houseMapper::entityToDto)
        .toList();
    }

    @Override
    public List<HouseDTO>getAllHousesByScalaAndPiano(String scala,int piano){
        return houseRepository.findByScalaAndPiano(scala, piano)
        .stream().map(houseMapper::entityToDto)
        .toList();
    }

    @Override
    public List<HouseDTO>getAllHousesByScalaAndPianoAndInterno(String scala,int piano, int interno){
        return houseRepository.findByScalaAndPianoAndInterno(scala, piano,interno)
        .stream().map(houseMapper::entityToDto)
        .toList();
    }

    @Override
    public List<HouseDTO>getAllHousesByName(String name, String surname){
        return houseRepository.findAllHousesByName(name, surname)
        .stream().map(houseMapper::entityToDto)
        .toList();
    }

    @Override
    public boolean checkValue(HouseDTO house){
        if (house.getInterno()>0 && 
            house.getPiano()>0   &&
            house.getScala().length()>0) {
                return true;
        }
        else{
            return false;
        }
    }

    @Override
    public boolean checkId(String id){
        if(id.length()>0 && houseRepository.existsById(id))
        {return true;}
        else
        {return false;}
    }

}
