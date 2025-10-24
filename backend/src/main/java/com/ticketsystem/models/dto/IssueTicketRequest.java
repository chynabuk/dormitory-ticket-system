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
public class IssueTicketRequest {
    String description;
    String city;
    String apartmentNumber;
    String roomNumber;
    String imageUrl;
    Boolean authorizationAccepted;
    Priority priority;
    Status currentStatus;
    String externalCompanyName;
    Long assignedToId;
    Long createdById;
}

