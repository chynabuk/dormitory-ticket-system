package com.ticketsystem.services;

import com.ticketsystem.models.dto.IssueTicketRequest;
import com.ticketsystem.models.dto.IssueTicketResponse;
import com.ticketsystem.models.entities.IssueTicket;

import java.util.List;


public interface IssueTicketService {
    IssueTicketResponse create(IssueTicketRequest request);
    IssueTicketResponse getById(Long id);
    List<IssueTicketResponse> getAll();
    IssueTicketResponse update(Long id, IssueTicketRequest request);
}


