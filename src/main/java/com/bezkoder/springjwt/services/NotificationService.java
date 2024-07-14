package com.bezkoder.springjwt.services;

import java.util.List;

import com.bezkoder.springjwt.models.NotificationDTO;

public interface NotificationService {

    public NotificationDTO newNotification(NotificationDTO notification);
    public NotificationDTO update(NotificationDTO notification);
    public boolean deleteNotificationById(String id );
    public NotificationDTO getNotificationById(String id);
    public List<NotificationDTO> getAllNotifications();
    public List<NotificationDTO> getNotificationsByUserId(String id);
    
    public boolean checkValue(NotificationDTO notification);
    public boolean checkId(String id);
    
}
