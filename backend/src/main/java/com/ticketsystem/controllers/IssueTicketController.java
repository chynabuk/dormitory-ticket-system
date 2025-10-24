package com.ticketsystem.controllers;


import com.ticketsystem.models.dto.IssueTicketGroupedResponse;
import com.ticketsystem.models.dto.IssueTicketRequest;
import com.ticketsystem.models.dto.IssueTicketResponse;
import com.ticketsystem.services.IssueTicket.IssueTicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/issues")
@RequiredArgsConstructor
@CrossOrigin("*")
public class IssueTicketController {

    private final IssueTicketService issueTicketService;

    //tested
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<IssueTicketResponse> create(
            @RequestPart(value = "createIssueTicket") IssueTicketRequest request,
            @RequestPart(value = "image", required = false) MultipartFile image
    ) {
        return new ResponseEntity<>(issueTicketService.createWithPhoto(request, image),HttpStatus.CREATED);
    }

    //tested
    @GetMapping
    public ResponseEntity<List<IssueTicketResponse>> getAll() {
        return new ResponseEntity<>(issueTicketService.getAll(), HttpStatus.OK);
    }

    //tested
    @GetMapping("/{id}")
    public ResponseEntity<IssueTicketResponse> getById(@PathVariable Long id) {

        return new ResponseEntity<>(issueTicketService.getById(id), HttpStatus.OK);
    }

    //tested
    @PutMapping("/{id}")
    public ResponseEntity<IssueTicketResponse> update(@PathVariable Long id, @RequestBody IssueTicketRequest request) {
        return new ResponseEntity<>(issueTicketService.update(id, request), HttpStatus.OK);
    }

    @GetMapping("/grouped")
    public ResponseEntity<IssueTicketGroupedResponse> getTicketsGroupedByStatus() {
        return new ResponseEntity<>(issueTicketService.getTicketsGroupedByStatus(), HttpStatus.OK);
    }



}

