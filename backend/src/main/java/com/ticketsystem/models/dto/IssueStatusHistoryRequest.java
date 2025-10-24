package com.ticketsystem.models.dto;

import com.ticketsystem.models.enums.Status;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class IssueStatusHistoryRequest {
    Status status;
    String comment;
    Long issueTicketId;
    Long changedById;
}
