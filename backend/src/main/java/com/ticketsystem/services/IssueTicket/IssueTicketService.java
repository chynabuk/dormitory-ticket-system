package com.ticketsystem.services.IssueTicket;

import com.ticketsystem.models.dto.IssueTicketRequest;
import com.ticketsystem.models.dto.IssueTicketResponse;

import java.util.List;


public interface IssueTicketService {
    IssueTicketResponse create(IssueTicketRequest request);
    IssueTicketResponse getById(Long id);
    List<IssueTicketResponse> getAll();
    IssueTicketResponse update(Long id, IssueTicketRequest request);
}


