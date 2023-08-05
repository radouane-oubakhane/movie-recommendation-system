package com.radouaneoubakhane.notificationservice.controller.impl;


import com.radouaneoubakhane.notificationservice.controller.NotificationController;
import com.radouaneoubakhane.notificationservice.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/notifications")
public class NotificationControllerImpl implements NotificationController {
    private final NotificationService notificationService;

    @Override
    @GetMapping
    public String getNotifications() {
        return notificationService.getNotifications();
    }
}

