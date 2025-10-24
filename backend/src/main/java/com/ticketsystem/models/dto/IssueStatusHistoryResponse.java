package com.ticketsystem.models.dto;

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
public class IssueStatusHistoryResponse {
    Long id;
    Status status;
    String comment;
    String changedByName;
    Long issueTicketId;
    LocalDateTime createDateTime;
}

