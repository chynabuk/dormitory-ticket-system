package com.ticketsystem.controllers;


import com.ticketsystem.models.dto.IssueTicketGroupedResponse;
import com.ticketsystem.models.dto.IssueTicketRequest;
import com.ticketsystem.models.dto.IssueTicketResponse;
import com.ticketsystem.models.entities.User;
import com.ticketsystem.models.requests.user.UpdateIssueStateRequest;
import com.ticketsystem.services.IssueTicket.IssueTicketService;
import com.ticketsystem.services.user.UserReadService;
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
    private final UserReadService userService;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<IssueTicketResponse> create(
            @RequestPart(value = "createIssueTicket") IssueTicketRequest request,
            @RequestPart(value = "image", required = false) MultipartFile image
    ) {
        return new ResponseEntity<>(issueTicketService.createWithPhoto(request, image),HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}/image", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getImage(@PathVariable Long id) {
        byte[] imageBytes = issueTicketService.getImageByIssueId(id);
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(imageBytes);
    }

    @GetMapping
    public ResponseEntity<List<IssueTicketResponse>> getAll() {
        return new ResponseEntity<>(issueTicketService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<IssueTicketResponse> getById(@PathVariable Long id) {

        return new ResponseEntity<>(issueTicketService.getById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<IssueTicketResponse> update(@PathVariable Long id, @RequestBody IssueTicketRequest request) {
        return new ResponseEntity<>(issueTicketService.update(id, request), HttpStatus.OK);
    }

    @GetMapping("/grouped")
    public ResponseEntity<IssueTicketGroupedResponse> getTicketsGroupedByStatus() {
        return new ResponseEntity<>(issueTicketService.getTicketsGroupedByStatus(), HttpStatus.OK);
    }

    @PutMapping("/update/status")
    public ResponseEntity<IssueTicketResponse> update(@RequestBody UpdateIssueStateRequest request) {
        issueTicketService.updateStatus(request.getId(), request.getStatus());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<IssueTicketResponse>> getUserTickets(@PathVariable Long id) {
        return new ResponseEntity<>(issueTicketService.getAllByUserId(id), HttpStatus.OK);
    }

}

