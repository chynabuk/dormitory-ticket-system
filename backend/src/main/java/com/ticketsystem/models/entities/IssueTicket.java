package com.ticketsystem.models.entities;

import com.ticketsystem.models.enums.Priority;
import com.ticketsystem.models.enums.Status;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "issue")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class IssueTicket extends BaseEntity {

    @Column(columnDefinition = "TEXT", nullable = false)
    String description;

    @Column(length = 100, nullable = false)
    String city = "Zwickau";

    @Column(length = 50, nullable = false)
    String apartmentNumber;

    @Column(length = 50, nullable = false)
    String roomNumber;

    @Column(name = "image_url")
    String imageUrl;

    @Column(name = "authorization_accepted", nullable = false)
    Boolean authorizationAccepted;

    @Column(length = 20, nullable = false)
    @Enumerated(EnumType.STRING)
    Priority priority;

    @Column(name = "current_status", length = 50, nullable = false)
    @Enumerated(EnumType.STRING)
    Status currentStatus;

    @Column(name = "resolved_at")
    LocalDateTime resolvedAt;

    @Column(name = "external_company_name")
    String externalCompanyName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by")
    User createdBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assigned_to")
    User assignedTo;

}
