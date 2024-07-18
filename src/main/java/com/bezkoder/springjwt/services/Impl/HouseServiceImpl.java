package com.bezkoder.springjwt.services.Impl;

import java.lang.reflect.Field;
import java.util.Arrays;
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
    public HouseDTO update(HouseDTO House) {
        HouseEntity oe = houseMapper.dtoToEntity(House); // utente con alcuni campi null
        HouseEntity entity = houseMapper.dtoToEntity(getHouseById(oe.getId())); // utente con tutti i campi non nulli
        List<String> stringFields = Arrays.asList("scala", "houseImg");
        List<String> intFields = Arrays.asList("piano","interno");
        //TODO user
        for (Field field : HouseEntity.class.getDeclaredFields()) {
            try {
                field.setAccessible(true);
                if (stringFields.contains(field.getName())) {
                    String value = (String) field.get(oe);
                    if (value != null && value.length() > 0) {
                        field.set(entity, value);
                    }
                } else if (intFields.contains(field.getName())) {
                    int value = (int) field.get(oe);
                    if (value > 0) {
                        field.set(entity, value);
                    }
                }
                    
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        entity = houseRepository.save(entity);
        return houseMapper.entityToDto(entity);
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
    public List<HouseDTO>getAllHousesByScalaAndPianoAndInternoAndName(String scala,int piano, int interno,String name){
        return houseRepository.findByScalaAndPianoAndInternoAndName(scala, piano,interno,name)
        .stream().map(houseMapper::entityToDto)
        .toList();
    }
    public List<HouseDTO>getAllHousesByScalaAndPianoAndInternoAndNameAndSurname(String scala,int piano, int interno,String name,String surname){
        return houseRepository.findByScalaAndPianoAndInternoAndNameAndSurname(scala, piano,interno,name,surname)
        .stream().map(houseMapper::entityToDto)
        .toList();
    }
    public List<HouseDTO>getAllHousesByScalaAndPianoAndNameAndSurname(String scala,int piano,String name,String surname){
        return houseRepository.findByScalaAndPianoAndNameAndSurname(scala, piano,name,surname)
        .stream().map(houseMapper::entityToDto)
        .toList();
    }
    public List<HouseDTO>getAllHousesByScalaAndPianoAndName(String scala,int piano,String name){
        return houseRepository.findByScalaAndPianoAndName(scala,piano,name)
        .stream().map(houseMapper::entityToDto)
        .toList();
    }
    public List<HouseDTO>getAllHousesByScalaAndPianoAndSurname(String scala,int piano,String surname){
        return houseRepository.findByScalaAndPianoAndName(scala,piano,surname)
        .stream().map(houseMapper::entityToDto)
        .toList();
    }

    public List<HouseDTO>getAllHousesByScalaAndInternoAndNameAndSurname(String scala,int interno,String name,String surname){
        return houseRepository.findByScalaAndInternoAndNameAndSurname(scala,interno,name,surname)
        .stream().map(houseMapper::entityToDto)
        .toList();
    }

    public List<HouseDTO> getAllHousesByScalaAndInternoAndName(String scala, int interno, String name){
        return houseRepository.findByScalaAndInternoAndName(scala,interno,name)
        .stream().map(houseMapper::entityToDto)
        .toList();
    }
    public List<HouseDTO> getAllHousesByScalaAndInternoAndSurname(String scala, int interno, String surname){
        return houseRepository.findByScalaAndInternoAndSurname(scala,interno,surname)
        .stream().map(houseMapper::entityToDto)
        .toList();
    }
    public List<HouseDTO>getAllHousesByScalaAndPianoAndInternoAndSurname(String scala,int piano,int interno,String surname){
        return houseRepository.findByScalaAndPianoAndInternoAndSurname(scala,piano,interno,surname)
        .stream().map(houseMapper::entityToDto)
        .toList();
    }

    public List<HouseDTO>getAllHousesByScalaAndNameAndSurname(String scala,String name,String surname){
        return houseRepository.findByScalaAndNameAndSurname(scala,name,surname)
        .stream().map(houseMapper::entityToDto)
        .toList();
    }
    public List<HouseDTO> getAllHousesByScalaAndName(String scala,String name){
        return houseRepository.findByScalaAndName(scala,name)
        .stream().map(houseMapper::entityToDto)
        .toList();
    }
    public List<HouseDTO> getAllHousesByScalaAndSurname(String scala,String surname){
        return houseRepository.findByScalaAndSurname(scala,surname)
        .stream().map(houseMapper::entityToDto)
        .toList();
    }
    public List<HouseDTO> getAllHousesByScalaAndInterno(String scala,int interno){
        return houseRepository.findByScalaAndInterno(scala,interno)
        .stream().map(houseMapper::entityToDto)
        .toList();
    }
    public List<HouseDTO>getAllHousesByInterno(int interno){
        return houseRepository.findByInterno(interno)
        .stream().map(houseMapper::entityToDto)
        .toList();
    }
    public List<HouseDTO>getAllHousesByInternoAndName(int interno,String name){
        return houseRepository.findByInternoAndName(interno,name)
        .stream().map(houseMapper::entityToDto)
        .toList();
    }
    public List<HouseDTO>getAllHousesByInternoAndSurname(int interno,String surname){
        return houseRepository.findByInternoAndSurname(interno,surname)
        .stream().map(houseMapper::entityToDto)
        .toList();
    }
    public List<HouseDTO>getAllHousesByInternoAndNameAndSurname(int interno,String name,String surname){
        return houseRepository.findByInternoAndNameAndSurname(interno,name,surname)
        .stream().map(houseMapper::entityToDto)
        .toList();
    }
    public List<HouseDTO>getAllHousesByPianoAndInternoAndNameAndSurname(int piano,int interno,String name,String surname){
        return houseRepository.findByPianoAndInternoAndNameAndSurname(piano,interno,name,surname)
        .stream().map(houseMapper::entityToDto)
        .toList();
    }
    public List<HouseDTO>getAllHousesByPianoAndInternoAndName(int piano,int interno,String name){
        return houseRepository.findByPianoAndInternoAndName(piano,interno,name)
        .stream().map(houseMapper::entityToDto)
        .toList();
    }
    public List<HouseDTO>getAllHousesByPianoAndInterno(int piano,int interno){
        return houseRepository.findByPianoAndInterno(piano,interno)
        .stream().map(houseMapper::entityToDto)
        .toList();
    }
    public List<HouseDTO>getAllHousesByPianoAndNameAndSurname(int piano,String name,String surname){
        return houseRepository.findByPianoAndNameAndSurname(piano,name,surname)
        .stream().map(houseMapper::entityToDto)
        .toList();
    }
    public List<HouseDTO>getAllHousesByPianoAndName(int piano,String name){
        return houseRepository.findByPianoAndName(piano,name)
        .stream().map(houseMapper::entityToDto)
        .toList();
    }
    public List<HouseDTO>getAllHousesByPianoAndSurname(int piano,String surname){
        return houseRepository.findByPianoAndSurname(piano,surname)
        .stream().map(houseMapper::entityToDto)
        .toList();
    }
    public List<HouseDTO>getAllHousesByPianoAndInternoAndSurname(int piano,int interno,String surname){
        return houseRepository.findByPianoAndInternoAndSurname(piano,interno,surname)
        .stream().map(houseMapper::entityToDto)
        .toList();
    }
    
    
    
    public List<HouseDTO> commandGetHandler(HouseDTO house) {
        // Se nessun campo è stato impostato, restituisci tutte le case
        if (house.getScala() == null && house.getPiano() == 0 && house.getInterno() == 0 && 
            (house.getUser() == null || (house.getUser().getName() == null && house.getUser().getSurname() == null))) {
            return getAllHouses();
        }
    
        // Controlla combinazioni di campi e restituisci le case corrispondenti
        if (house.getScala() != null) {
            if (house.getPiano() == 0) {
                if (house.getInterno() == 0) {
                    if (house.getUser() == null || (house.getUser().getName() == null && house.getUser().getSurname() == null)) {
                        return getAllHousesByScala(house.getScala());
                    }
                    if (house.getUser() != null) {
                        if (house.getUser().getName() != null && house.getUser().getSurname() != null) {
                            return getAllHousesByScalaAndNameAndSurname(house.getScala(), house.getUser().getName(), house.getUser().getSurname());
                        }
                        if (house.getUser().getName() != null) {
                            return getAllHousesByScalaAndName(house.getScala(), house.getUser().getName());
                        }
                        if (house.getUser().getSurname() != null) {
                            return getAllHousesByScalaAndSurname(house.getScala(), house.getUser().getSurname());
                        }
                    }
                } else {
                    if (house.getUser() == null || (house.getUser().getName() == null && house.getUser().getSurname() == null)) {
                        return getAllHousesByScalaAndInterno(house.getScala(), house.getInterno());
                    }
                    if (house.getUser() != null) {
                        if (house.getUser().getName() != null && house.getUser().getSurname() != null) {
                            return getAllHousesByScalaAndInternoAndNameAndSurname(house.getScala(), house.getInterno(), house.getUser().getName(), house.getUser().getSurname());
                        }
                        if (house.getUser().getName() != null) {
                            return getAllHousesByScalaAndInternoAndName(house.getScala(), house.getInterno(), house.getUser().getName());
                        }
                        if (house.getUser().getSurname() != null) {
                            return getAllHousesByScalaAndInternoAndSurname(house.getScala(), house.getInterno(), house.getUser().getSurname());
                        }
                    }
                }
            } else {
                if (house.getInterno() == 0) {
                    if (house.getUser() == null || (house.getUser().getName() == null && house.getUser().getSurname() == null)) {
                        return getAllHousesByScalaAndPiano(house.getScala(), house.getPiano());
                    }
                    if (house.getUser() != null) {
                        if (house.getUser().getName() != null && house.getUser().getSurname() != null) {
                            return getAllHousesByScalaAndPianoAndNameAndSurname(house.getScala(), house.getPiano(), house.getUser().getName(), house.getUser().getSurname());
                        }
                        if (house.getUser().getName() != null) {
                            return getAllHousesByScalaAndPianoAndName(house.getScala(), house.getPiano(), house.getUser().getName());
                        }
                        if (house.getUser().getSurname() != null) {
                            return getAllHousesByScalaAndPianoAndSurname(house.getScala(), house.getPiano(), house.getUser().getSurname());
                        }
                    }
                } else {
                    if (house.getUser() == null || (house.getUser().getName() == null && house.getUser().getSurname() == null)) {
                        return getAllHousesByScalaAndPianoAndInterno(house.getScala(), house.getPiano(), house.getInterno());
                    }
                    if (house.getUser() != null) {
                        if (house.getUser().getName() != null && house.getUser().getSurname() != null) {
                            return getAllHousesByScalaAndPianoAndInternoAndNameAndSurname(house.getScala(), house.getPiano(), house.getInterno(), house.getUser().getName(), house.getUser().getSurname());
                        }
                        if (house.getUser().getName() != null) {
                            return getAllHousesByScalaAndPianoAndInternoAndName(house.getScala(), house.getPiano(), house.getInterno(), house.getUser().getName());
                        }
                        if (house.getUser().getSurname() != null) {
                            return getAllHousesByScalaAndPianoAndInternoAndSurname(house.getScala(), house.getPiano(), house.getInterno(), house.getUser().getSurname());
                        }
                    }
                }
            }
        } else {
            if (house.getPiano() != 0) {
                if (house.getInterno() == 0) {
                    if (house.getUser() == null || (house.getUser().getName() == null && house.getUser().getSurname() == null)) {
                        return getAllHousesByPiano(house.getPiano());
                    }
                    if (house.getUser() != null) {
                        if (house.getUser().getName() != null && house.getUser().getSurname() != null) {
                            return getAllHousesByPianoAndNameAndSurname(house.getPiano(), house.getUser().getName(), house.getUser().getSurname());
                        }
                        if (house.getUser().getName() != null) {
                            return getAllHousesByPianoAndName(house.getPiano(), house.getUser().getName());
                        }
                        if (house.getUser().getSurname() != null) {
                            return getAllHousesByPianoAndSurname(house.getPiano(), house.getUser().getSurname());
                        }
                    }
                } else {
                    if (house.getUser() == null || (house.getUser().getName() == null && house.getUser().getSurname() == null)) {
                        return getAllHousesByPianoAndInterno(house.getPiano(), house.getInterno());
                    }
                    if (house.getUser() != null) {
                        if (house.getUser().getName() != null && house.getUser().getSurname() != null) {
                            return getAllHousesByPianoAndInternoAndNameAndSurname(house.getPiano(), house.getInterno(), house.getUser().getName(), house.getUser().getSurname());
                        }
                        if (house.getUser().getName() != null) {
                            return getAllHousesByPianoAndInternoAndName(house.getPiano(), house.getInterno(), house.getUser().getName());
                        }
                        if (house.getUser().getSurname() != null) {
                            return getAllHousesByPianoAndInternoAndSurname(house.getPiano(), house.getInterno(), house.getUser().getSurname());
                        }
                    }
                }
            } else {
                if (house.getInterno() != 0) {
                    if (house.getUser() == null || (house.getUser().getName() == null && house.getUser().getSurname() == null)) {
                        return getAllHousesByInterno(house.getInterno());
                    }
                    if (house.getUser() != null) {
                        if (house.getUser().getName() != null && house.getUser().getSurname() != null) {
                            return getAllHousesByInternoAndNameAndSurname(house.getInterno(), house.getUser().getName(), house.getUser().getSurname());
                        }
                        if (house.getUser().getName() != null) {
                            return getAllHousesByInternoAndName(house.getInterno(), house.getUser().getName());
                        }
                        if (house.getUser().getSurname() != null) {
                            return getAllHousesByInternoAndSurname(house.getInterno(), house.getUser().getSurname());
                        }
                    }
                } else {
                    if (house.getUser() != null) {
                        if (house.getUser().getName() != null && house.getUser().getSurname() != null) {
                            return getAllHousesByNameAndSurname(house.getUser().getName(), house.getUser().getSurname());
                        }
                        if (house.getUser().getName() != null) {
                            return getAllHousesByName(house.getUser().getName());
                        }
                        if (house.getUser().getSurname() != null) {
                            return getAllHousesBySurname(house.getUser().getSurname());
                        }
                    }
                }
            }
        }
    
        // Se nessuna condizione è soddisfatta, restituisci tutte le case
        return getAllHouses();
    }
    


    @Override
    public List<HouseDTO>getAllHousesByName(String name){
        return houseRepository.findAllHousesByName(name)
        .stream().map(houseMapper::entityToDto)
        .toList();
    }
    public List<HouseDTO>getAllHousesBySurname(String surname){
        return houseRepository.findAllHousesBySurname(surname)
        .stream().map(houseMapper::entityToDto)
        .toList();
    }
    public List<HouseDTO>getAllHousesByNameAndSurname(String name, String surname){
        return houseRepository.findAllHousesByNameAndSurname(name,surname)
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
    public boolean checkTriple(String scala, int piano, int interno) {
        List<HouseDTO> houses = getAllHouses();
        for (HouseDTO house : houses) {
            if (house.getScala().equals(scala) && house.getPiano() == piano && house.getInterno() == interno) {
                return false; 
            }
        }
    
        return true;
    }
    

   

    

}
