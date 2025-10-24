package com.ticketsystem.services.impl;


import com.ticketsystem.exceptions.impl.ResourceNotFoundException;
import com.ticketsystem.models.dto.IssueTicketRequest;
import com.ticketsystem.models.dto.IssueTicketResponse;
import com.ticketsystem.models.entities.IssueTicket;
import com.ticketsystem.models.enums.Status;
import com.ticketsystem.repositories.IssueTicketRepository;
import com.ticketsystem.services.IssueTicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IssueTicketServiceImpl implements IssueTicketService {


    private final IssueTicketRepository issueTicketRepository;
//    private final UserRepository userRepository;

//    @Override
//    public IssueTicketResponse create(IssueTicketRequest request) {
//        IssueTicket issue = new IssueTicket();
//        mapRequestToEntity(request, issue);
//        if (issue.getCurrentStatus() == null) issue.setCurrentStatus(Status.CREATED);
//        return mapToResponse(issueTicketRepository.save(issue));
//    }


    @Override
    public IssueTicketResponse getById(Long id) {
        IssueTicket ticket = issueTicketRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket not found with id: " + id));
        return mapToResponse(ticket);
    }

    @Override
    public List<IssueTicketResponse> getAll() {
        return issueTicketRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

//    @Override
//    public IssueTicketResponse update(Long id, IssueTicketRequest request) {
//        IssueTicket existing = issueTicketRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Ticket not found with id: " + id));
//        mapRequestToEntity(request, existing);
//        return mapToResponse(issueTicketRepository.save(existing));
//    }

//    @Override
//    public void delete(Long id) {
//        IssueTicket ticket = issueTicketRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Ticket not found with id: " + id));
//        issueTicketRepository.delete(ticket);
//    }


//    private void mapRequestToEntity(IssueTicketRequest req, IssueTicket entity) {
//        entity.setDescription(req.getDescription());
//        entity.setCity(req.getCity());
//        entity.setApartmentNumber(req.getApartmentNumber());
//        entity.setRoomNumber(req.getRoomNumber());
//        entity.setImageUrl(req.getImageUrl());
//        entity.setAuthorizationAccepted(req.getAuthorizationAccepted());
//        entity.setPriority(req.getPriority());
//        entity.setCurrentStatus(req.getCurrentStatus());
//        entity.setResolvedAt(req.getResolvedAt());
//        entity.setExternalCompanyName(req.getExternalCompanyName());
//
//        if (req.getAssignedToId() != null) {
//            User assigned = userRepository.findById(req.getAssignedToId())
//                    .orElseThrow(() -> new ResourceNotFoundException("Assigned user not found"));
//            entity.setAssignedTo(assigned);
//        }
//
//        if (req.getCreatedById() != null) {
//            User creator = userRepository.findById(req.getCreatedById())
//                    .orElseThrow(() -> new ResourceNotFoundException("Creator user not found"));
//            entity.setCreatedBy(creator);
//        }
//    }

    private IssueTicketResponse mapToResponse(IssueTicket entity) {
        return IssueTicketResponse.builder()
                .id(entity.getId())
                .description(entity.getDescription())
                .city(entity.getCity())
                .apartmentNumber(entity.getApartmentNumber())
                .roomNumber(entity.getRoomNumber())
                .imageUrl(entity.getImageUrl())
                .authorizationAccepted(entity.getAuthorizationAccepted())
                .priority(entity.getPriority())
                .currentStatus(entity.getCurrentStatus())
                .resolvedAt(entity.getResolvedAt())
                .externalCompanyName(entity.getExternalCompanyName())
                .assignedToName(entity.getAssignedTo() != null ? entity.getAssignedTo().getFirstName() + entity.getAssignedTo().getLastName(): null)
                .createdByName(entity.getCreatedBy() != null ? entity.getCreatedBy().getFirstName() + entity.getCreatedBy().getLastName() : null)
                .build();
    }



}
