package com.radouaneoubakhane.notificationservice.repository;

import com.radouaneoubakhane.notificationservice.domain.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
