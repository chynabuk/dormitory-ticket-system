package com.ticketsystem.services;

import com.ticketsystem.models.dto.IssueStatusHistoryRequest;
import com.ticketsystem.models.dto.IssueStatusHistoryResponse;

import java.util.List;

public interface IssueStatusHistoryService {
//    IssueStatusHistoryResponse create(IssueStatusHistoryRequest request);
    IssueStatusHistoryResponse getById(Long id);
    List<IssueStatusHistoryResponse> getAll();
    List<IssueStatusHistoryResponse> getByTicketId(Long ticketId);
    void delete(Long id);
}

