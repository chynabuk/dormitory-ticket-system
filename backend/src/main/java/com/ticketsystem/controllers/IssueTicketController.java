package com.ticketsystem.controllers;


import com.ticketsystem.models.dto.IssueTicketRequest;
import com.ticketsystem.models.dto.IssueTicketResponse;
import com.ticketsystem.services.IssueTicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/issues")
@RequiredArgsConstructor
@CrossOrigin("*")
public class IssueTicketController {

    private final IssueTicketService issueTicketService;

    //tested
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public IssueTicketResponse create(@RequestBody IssueTicketRequest request) {
        return issueTicketService.create(request);
    }

    //tested
    @GetMapping
    public List<IssueTicketResponse> getAll() {
        return issueTicketService.getAll();
    }

    //tested
    @GetMapping("/{id}")
    public IssueTicketResponse getById(@PathVariable Long id) {
        return issueTicketService.getById(id);
    }

    //tested
    @PutMapping("/{id}")
    public IssueTicketResponse update(@PathVariable Long id, @RequestBody IssueTicketRequest request) {
        return issueTicketService.update(id, request);
    }


}

