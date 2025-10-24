package com.ticketsystem.models.dto;


import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class IssueTicketGroupedResponse {
    private List<IssueTicketResponse> created;
    private List<IssueTicketResponse> inProgress;
    private List<IssueTicketResponse> resolved;
    private List<IssueTicketResponse> discarded;
}