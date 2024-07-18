package com.bezkoder.springjwt.controllers;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.bezkoder.springjwt.models.HouseDTO;
import com.bezkoder.springjwt.models.ResponseDTO;
import com.bezkoder.springjwt.models.ResponseListDTO;
import com.bezkoder.springjwt.services.HouseService;



@RestController
@CrossOrigin
@RequestMapping("/house/")
public class HouseController {

    @Autowired
    HouseService houseService;

    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_ADMIN')")
    @PostMapping("save")
    public ResponseDTO newHouse(@RequestBody HouseDTO house){
        ResponseDTO response = new ResponseDTO();
        if(houseService.checkValue(house)){
            response.setType("save");
            response.setHTTPstatus("200");
            response.setMessage("house saved");
            response.setO(house);
            houseService.newHouse((HouseDTO) response.getO());
            return response;}
        else{  
            response.setType("save");
            response.setHTTPstatus("400");
            response.setMessage("failed. Check inputs.");
            response.setO(house);
            return response;

        }
    }

    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_ADMIN')")
    @PutMapping("putUpdate")
    public ResponseDTO updateHouse(@RequestBody HouseDTO house){
        ResponseDTO response = new ResponseDTO();
        if(houseService.checkValue(house)){
            response.setType("put update");
            response.setHTTPstatus("200");
            response.setMessage("house updated");
            response.setO(house);
            houseService.update((HouseDTO) response.getO());
            return response;}
        else{  
            response.setType("update");
            response.setHTTPstatus("400");
            response.setMessage("update house failed. Check inputs.");
            response.setO(house);
            return response;

        }
    }

    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_ADMIN')")
    @PatchMapping("patchUpdate")
    public ResponseDTO patchUpdateHouse(@RequestBody HouseDTO house){
        ResponseDTO response = new ResponseDTO();
        if(houseService.checkId(house.getId()) && houseService.checkTriple(house.getScala(),house.getPiano(),house.getInterno())){
            response.setType("patch update");
            response.setHTTPstatus("200");
            response.setMessage("house updated");
            response.setO(house);
            houseService.update((HouseDTO) response.getO());
            return response;
        }
        else{  
            response.setType("update");
            response.setHTTPstatus("400");
            response.setMessage("update house failed. Check inputs.");
            response.setO(house);
            return response;

        }
    }

    
    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_ADMIN')")
    @DeleteMapping("delete/{id}")
    public ResponseDTO deleteHouseById(@PathVariable String id){
        ResponseDTO response = new ResponseDTO();
        if(houseService.checkId(id)){
            response.setType("delete");
            response.setHTTPstatus("200");
            response.setMessage("house deleted");
            response.setO((HouseDTO) houseService.getHouseById(id));
            houseService.deleteHouseById(id);
            return response;}
        else{  
            response.setType("delete");
            response.setHTTPstatus("400");
            response.setMessage("house not deleted. Check id");
            return response;

        }
    }

    
    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_ADMIN')")
    @GetMapping("get/{id}")
    public ResponseDTO getHouseById(@PathVariable String id){
        ResponseDTO response = new ResponseDTO();
        if(houseService.checkId(id)){
            response.setType("get");
            response.setHTTPstatus("200");
            response.setMessage("ok");
            response.setO((HouseDTO) houseService.getHouseById(id));
            return response;}
        else{  
            response.setType("failed");
            response.setHTTPstatus("400");
            response.setMessage("ko");
            return response;
        }
    }

    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_ADMIN')")
    @GetMapping("getAll")
    public ResponseListDTO getAllHouses() {
        ResponseListDTO response = new ResponseListDTO();
        if(houseService.getAllHouses().size()>0){
            response.setType("get");
            response.setHTTPstatus("200");
            response.setMessage("ok");
            response.setLo(houseService.getAllHouses().stream()
            .map(obj -> (Object) obj)
            .collect(Collectors.toList()));
            return response;}
        else{  
            response.setType("no houses found");
            response.setHTTPstatus("400");
            response.setMessage("ko");
            return response;
        }
    }


    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_MANAGER','ROLE_ADMIN')")
    @GetMapping("get/houses/by/{userId}")
    public ResponseListDTO getHousesByUser(@PathVariable String userId){
        ResponseListDTO response = new ResponseListDTO();
        if(houseService.getHousesByUserId(userId).size()>0){
            response.setType("get");
            response.setHTTPstatus("200");
            response.setMessage("ok");
            response.setLo(houseService.getHousesByUserId(userId).stream()
            .map(obj -> (Object) obj)
            .collect(Collectors.toList()));
            return response;}
        else{  
            response.setType("no houses found");
            response.setHTTPstatus("400");
            response.setMessage("ko");
            return response;
        }
    }


    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_ADMIN')")
    @GetMapping("get/houses/by/scalaPianoInterno/{scala}")
    public ResponseListDTO getAllHousesByScala(@PathVariable String scala){
        ResponseListDTO response = new ResponseListDTO();
        if(houseService.getAllHousesByScala(scala).size()>0){
            response.setType("get");
            response.setHTTPstatus("200");
            response.setMessage("ok");
            response.setLo(houseService.getAllHousesByScala(scala).stream()
            .map(obj -> (Object) obj)
            .collect(Collectors.toList()));
            return response;}
        else{  
            response.setType("no houses found");
            response.setHTTPstatus("400");
            response.setMessage("ko");
            return response;
        }
    }

    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_ADMIN')")
    @GetMapping("get/houses/by/scalaPianoInterno/{piano}")
    public ResponseListDTO getAllHousesByPiano(@PathVariable int piano){
        ResponseListDTO response = new ResponseListDTO();
        if(houseService.getAllHousesByPiano(piano).size()>0){
            response.setType("get");
            response.setHTTPstatus("200");
            response.setMessage("ok");
            response.setLo(houseService.getAllHousesByPiano(piano).stream()
            .map(obj -> (Object) obj)
            .collect(Collectors.toList()));
            return response;}
        else{  
            response.setType("no houses found");
            response.setHTTPstatus("400");
            response.setMessage("ko");
            return response;
        }
    }
    
    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_ADMIN')")
    @GetMapping("get/houses/by/scalaPianoInterno/{scala}/{piano}")
    public ResponseListDTO getAllHousesByScalaAndPiano(@PathVariable String scala, @PathVariable int piano){
        ResponseListDTO response = new ResponseListDTO();
        if(houseService.getAllHousesByScalaAndPiano(scala, piano).size()>0){
            response.setType("get");
            response.setHTTPstatus("200");
            response.setMessage("ok");
            response.setLo(houseService.getAllHousesByScalaAndPiano(scala, piano).stream()
            .map(obj -> (Object) obj)
            .collect(Collectors.toList()));
            return response;}
        else{  
            response.setType("no houses found");
            response.setHTTPstatus("400");
            response.setMessage("ko");
            return response;
        }
    }


    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_ADMIN')")
    @GetMapping("get/houses/by/scalaPianoInterno/{scala}/{piano}/{interno}")
    public ResponseListDTO getAllHousesByScalaAndPianoAndInterno(@PathVariable String scala, @PathVariable int piano,
    @PathVariable int interno){
        ResponseListDTO response = new ResponseListDTO();
        if(houseService.getAllHousesByScalaAndPianoAndInterno(scala, piano, interno).size()>0){
            response.setType("get");
            response.setHTTPstatus("200");
            response.setMessage("ok");
            response.setLo(houseService.getAllHousesByScalaAndPianoAndInterno(scala, piano, interno).stream()
            .map(obj -> (Object) obj)
            .collect(Collectors.toList()));
            return response;}
        else{  
            response.setType("no houses found");
            response.setHTTPstatus("400");
            response.setMessage("ko");
            return response;
        }
    }


    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_ADMIN')")
    @GetMapping("get/houses/by/name/{name}/{surname}")
    public ResponseListDTO getAllHousesByName(@PathVariable String name, @PathVariable String surname){
        ResponseListDTO response = new ResponseListDTO();
        if(houseService.getAllHousesByNameAndSurname(name, surname).size()>0){
            response.setType("get");
            response.setHTTPstatus("200");
            response.setMessage("ok");
            response.setLo(houseService.getAllHousesByNameAndSurname(name, surname).stream()
            .map(obj -> (Object) obj)
            .collect(Collectors.toList()));
            return response;}
        else{  
            response.setType("no houses found");
            response.setHTTPstatus("400");
            response.setMessage("ko");
            return response;
        }
    }
    

    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_ADMIN')")
    @GetMapping("getAll/houses/free")
    public ResponseListDTO getAllFreeHouses(){
        ResponseListDTO response = new ResponseListDTO();
        if(houseService.getAllFreeHouses().size()>0){
            response.setType("get");
            response.setHTTPstatus("200");
            response.setMessage("ok");
            response.setLo(houseService.getAllFreeHouses().stream()
            .map(obj -> (Object) obj)
            .collect(Collectors.toList()));
            return response;}
        else{  
            response.setType("no houses found");
            response.setHTTPstatus("400");
            response.setMessage("ko");
            return response;
        }
    }
    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_ADMIN')")
    @PostMapping("search")
    public ResponseListDTO search(@RequestBody HouseDTO house){
        ResponseListDTO response = new ResponseListDTO();
        if(houseService.commandGetHandler(house).size()>0){
            response.setType("post");
            response.setHTTPstatus("200");
            response.setMessage("ok");
            response.setLo(houseService.commandGetHandler(house).stream()
            .map(obj -> (Object) obj)
            .collect(Collectors.toList()));
            return response;
        }else{  
            response.setType("no houses found");
            response.setHTTPstatus("400");
            response.setMessage("ko");
            return response;
        }
    }


    
}
