package org.cts.housingaid.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cts.housingaid.enums.NotificationCategory;
import org.cts.housingaid.enums.NotificationStatus;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notificationId;

    private Long usersId;

    private Long entityId;

    private String notificationMessage;

    @Enumerated(EnumType.STRING)
    private NotificationCategory notificationCategory;

    @Enumerated(EnumType.STRING)
    private NotificationStatus notificationStatus;

    private LocalDate notificationCreatedDate;

}