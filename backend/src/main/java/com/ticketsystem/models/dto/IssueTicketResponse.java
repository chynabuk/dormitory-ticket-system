package com.ticketsystem.models.dto;

import com.ticketsystem.models.enums.Priority;
import com.ticketsystem.models.enums.Status;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class IssueTicketResponse {
    Long id;
    String description;
    String city;
    String apartmentNumber;
    String roomNumber;
    String imageUrl;
    Boolean authorizationAccepted;
    Priority priority;
    Status currentStatus;
    LocalDateTime resolvedAt;
    LocalDateTime createdDateTime;
    String externalCompanyName;
    String assignedToName;
    String createdByName;

}

