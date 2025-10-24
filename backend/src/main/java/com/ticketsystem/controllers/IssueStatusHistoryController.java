package com.ticketsystem.controllers;

import com.ticketsystem.models.dto.IssueStatusHistoryRequest;
import com.ticketsystem.models.dto.IssueStatusHistoryResponse;
import com.ticketsystem.services.IssueStatusHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/status-history")
@RequiredArgsConstructor
@CrossOrigin("*")
public class IssueStatusHistoryController {

    private final IssueStatusHistoryService historyService;

//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public IssueStatusHistoryResponse create(@RequestBody IssueStatusHistoryRequest request) {
//        return historyService.create(request);
//    }

    @GetMapping
    public List<IssueStatusHistoryResponse> getAll() {
        return historyService.getAll();
    }

    @GetMapping("/{id}")
    public IssueStatusHistoryResponse getById(@PathVariable Long id) {
        return historyService.getById(id);
    }

    @GetMapping("/ticket/{ticketId}")
    public List<IssueStatusHistoryResponse> getByTicket(@PathVariable Long ticketId) {
        return historyService.getByTicketId(ticketId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        historyService.delete(id);
    }
}

