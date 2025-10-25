package com.ticketsystem.services.IssueTicket;


import com.ticketsystem.exceptions.impl.ResourceNotFoundException;
import com.ticketsystem.models.dto.IssueTicketGroupedResponse;
import com.ticketsystem.models.dto.IssueTicketRequest;
import com.ticketsystem.models.dto.IssueTicketResponse;
import com.ticketsystem.models.entities.IssueTicket;
import com.ticketsystem.models.entities.User;
import com.ticketsystem.models.enums.Status;
import com.ticketsystem.repositories.IssueTicketRepository;
import com.ticketsystem.repositories.UserRepository;
import com.ticketsystem.services.user.UserReadService;
import com.ticketsystem.utils.ImageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IssueTicketServiceImpl implements IssueTicketService {


    private final IssueTicketRepository issueTicketRepository;
    private final UserRepository userRepository;
    private final UserReadService userReadService;
    private final ImageUtil imageUtil;

    @Override
    public IssueTicketResponse create(IssueTicketRequest request) {
        IssueTicket issue = new IssueTicket();
        mapRequestToEntity(request, issue);
        if (issue.getCurrentStatus() == null) issue.setCurrentStatus(Status.CREATED);
        issue.setCreateDateTime(LocalDateTime.now());
        return mapToResponse(issueTicketRepository.save(issue));
    }

    @Override
    public IssueTicketResponse createWithPhoto(IssueTicketRequest request, MultipartFile image) {
        IssueTicket issueTicket = new IssueTicket();
        mapRequestToEntity(request, issueTicket);
        if (issueTicket.getCurrentStatus() == null) issueTicket.setCurrentStatus(Status.CREATED);

        IssueTicket issueTicketCreated = issueTicketRepository.save(issueTicket);
        initImage(issueTicketCreated, image);
        return mapToResponse(issueTicketCreated);
    }

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


    @Override
    public IssueTicketResponse update(Long id, IssueTicketRequest request) {
        IssueTicket ticket = issueTicketRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket not found with id: " + id));


        Status newStatus = request.getCurrentStatus();
        if (newStatus != null && newStatus != ticket.getCurrentStatus()) {
            ticket.setCurrentStatus(newStatus);

            switch (newStatus) {
                case RESOLVED -> ticket.setResolvedAt(LocalDateTime.now());
                case IN_PROGRESS, DISCARDED -> ticket.setUpdateDateTime(LocalDateTime.now());
                default -> {}
            }
        }
        mapRequestToEntity(request, ticket);

        IssueTicket saved = issueTicketRepository.save(ticket);
        return mapToResponse(saved);
    }
    private void mapRequestToEntity(IssueTicketRequest req, IssueTicket entity) {
        entity.setDescription(req.getDescription());
        entity.setCity(req.getCity());
        entity.setApartmentNumber(req.getApartmentNumber());
        entity.setRoomNumber(req.getRoomNumber());
        entity.setImageUrl(req.getImageUrl());
        entity.setAuthorizationAccepted(req.getAuthorizationAccepted());
        entity.setPriority(req.getPriority());
        entity.setCurrentStatus(req.getCurrentStatus());
        entity.setExternalCompanyName(req.getExternalCompanyName());

        if (req.getAssignedToId() != null) {
            User assigned = userRepository.findById(req.getAssignedToId())
                    .orElseThrow(() -> new ResourceNotFoundException("Assigned user not found"));
            entity.setAssignedTo(assigned);
        }

        if (req.getCreatedById() != null) {
            User creator = userRepository.findById(req.getCreatedById())
                    .orElseThrow(() -> new ResourceNotFoundException("Creator user not found"));
            entity.setCreatedBy(creator);
        }
    }

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

    @Override
    public IssueTicketGroupedResponse getTicketsGroupedByStatus() {

        List<IssueTicket> created = issueTicketRepository.findByCurrentStatus(Status.CREATED);
        List<IssueTicket> inProgress = issueTicketRepository.findByCurrentStatus(Status.IN_PROGRESS);
        List<IssueTicket> resolved = issueTicketRepository.findByCurrentStatus(Status.RESOLVED);
        List<IssueTicket> discarded = issueTicketRepository.findByCurrentStatus(Status.DISCARDED);

        Comparator<IssueTicket> priorityComparator = Comparator
                .comparing(IssueTicket::getPriority, Comparator.nullsLast(Comparator.naturalOrder()));

        created.sort(priorityComparator);
        inProgress.sort(priorityComparator);
        resolved.sort(priorityComparator);
        discarded.sort(priorityComparator);


        return IssueTicketGroupedResponse.builder()
                .created(created.stream().map(this::mapToResponse).toList())
                .inProgress(inProgress.stream().map(this::mapToResponse).toList())
                .resolved(resolved.stream().map(this::mapToResponse).toList())
                .discarded(discarded.stream().map(this::mapToResponse).toList())
                .build();
    }

    @Override
    public void updateStatus(Long issueTicketId, String status) {
        Optional<IssueTicket> issueTicketOptional = issueTicketRepository.findById(issueTicketId);
        if (issueTicketOptional.isEmpty()) {
            throw new ResourceNotFoundException("Issue with the given id doesn't exist!");
        }

        IssueTicket issueTicket = issueTicketOptional.get();
        issueTicket.setCurrentStatus(Status.valueOf(status));
        issueTicketRepository.save(issueTicket);
    }

    private boolean initImage(IssueTicket issueTicket, MultipartFile image){
        if (image != null){
            try {
                issueTicket.setImageUrl(imageUtil.saveImage(image, issueTicket));
                return true;
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        return false;
    }

}
