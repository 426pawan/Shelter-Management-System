package org.cts.housingaid.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cts.housingaid.enums.AuditLogAction;
import org.cts.housingaid.util.AuditLogResource;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long auditId;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private Users users;

    @Enumerated(EnumType.STRING)
    private AuditLogAction auditLogAction;

    @Embedded
    private AuditLogResource auditLogResource;

}