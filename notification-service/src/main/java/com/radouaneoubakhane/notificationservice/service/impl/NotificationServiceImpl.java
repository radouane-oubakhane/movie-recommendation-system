package com.radouaneoubakhane.notificationservice.service.impl;


import com.radouaneoubakhane.notificationservice.repository.NotificationRepository;
import com.radouaneoubakhane.notificationservice.service.NotificationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class NotificationServiceImpl implements NotificationService {
    private final NotificationRepository notificationRepository;

    @Override
    public String getNotifications() {
        return null;
    }
}
