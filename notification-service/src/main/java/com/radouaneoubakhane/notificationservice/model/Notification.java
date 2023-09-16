package com.radouaneoubakhane.notificationservice.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "notification")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id; // explication: id of the notification
    private String title; // explication: title of the notification
    private String message; // explication: message of the notification
    private String userId; // explication: id of the user who will receive the notification
    private String status; // explication: status of the notification
    private String type; // explication: type of the notification
    private String createdAt; // explication: date of creation of the notification
    private String updatedAt; // explication: date of update of the notification
}
