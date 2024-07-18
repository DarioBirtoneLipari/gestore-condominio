package com.bezkoder.springjwt.services.Impl;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bezkoder.springjwt.entity.NotificationEntity;
import com.bezkoder.springjwt.mapper.NotificationMapper;
import com.bezkoder.springjwt.models.NotificationDTO;
import com.bezkoder.springjwt.repository.NotificationRepository;
import com.bezkoder.springjwt.services.NotificationService;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    NotificationMapper notificationMapper;

    @Autowired
    NotificationRepository notificationRepository;

    @Override
    public NotificationDTO newNotification(NotificationDTO notification) {
        NotificationEntity ne = notificationMapper.dtoToEntity(notification);
		ne = notificationRepository.save(ne);
		return notificationMapper.entityToDto(ne);
    }

    @Override
    public NotificationDTO update(NotificationDTO notification) {
        NotificationEntity oe = notificationMapper.dtoToEntity(notification); // utente con alcuni campi null
        NotificationEntity entity = notificationMapper.dtoToEntity(getNotificationById(oe.getId())); // utente con tutti i campi non nulli
        List<String> stringFields = Arrays.asList("text", "creditCard");
        //TODO date type and external key ?
        for (Field field : NotificationEntity.class.getDeclaredFields()) {
            try {
                field.setAccessible(true);
                if (stringFields.contains(field.getName())) {
                    String value = (String) field.get(oe);
                    if (value != null && value.length() > 0) {
                        field.set(entity, value);
                    }
                } 
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        entity = notificationRepository.save(entity);
        return notificationMapper.entityToDto(entity);
    }

    @Override
    public boolean deleteNotificationById(String id) {
        if(notificationRepository.existsById(id)) {
			notificationRepository.deleteById(id);
			return true;
		}
		return false;
    }

    @Override
    public NotificationDTO getNotificationById(String id) {
            Optional<NotificationEntity> ne = notificationRepository.findById(id);
		if(ne.isPresent()) {
			return notificationMapper.entityToDto(ne.get());
		} else {
			  NotificationDTO notification = new NotificationDTO();
			return notification;
		}
    }

    @Override
    public List<NotificationDTO> getAllNotifications() {
        return notificationRepository.findAll()
				.stream().map(notificationMapper::entityToDto)
				.toList();
    }
    

    @Override
    public List<NotificationDTO> getNotificationsByUserId(String id) {
        return notificationRepository.findAllPersonalNotifcation(id)
				.stream().map(notificationMapper::entityToDto)
				.toList();
    }


    @Override
    public boolean checkValue(NotificationDTO notification) {
        //Date today = new Date();
        if (notification.getText().length()>0)

           {
                return true;
        }
        else{
            return false;
        }
    }

    @Override
    public boolean checkId(String id) {
        if(id.length()>0 && notificationRepository.existsById(id))
        {return true;}
        else
        {return false;}
    
    }
    
}
