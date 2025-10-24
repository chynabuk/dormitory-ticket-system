package com.ticketsystem.services.impl;

import com.ticketsystem.exceptions.impl.ResourceNotFoundException;
import com.ticketsystem.models.dto.IssueStatusHistoryRequest;
import com.ticketsystem.models.dto.IssueStatusHistoryResponse;
import com.ticketsystem.models.entities.IssueStatusHistory;
import com.ticketsystem.models.entities.IssueTicket;
import com.ticketsystem.models.entities.User;
import com.ticketsystem.repositories.IssueStatusHistoryRepository;
import com.ticketsystem.repositories.IssueTicketRepository;
//import com.ticketsystem.repositories.UserRepository;
import com.ticketsystem.services.IssueStatusHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IssueStatusHistoryServiceImpl implements IssueStatusHistoryService {

    private final IssueStatusHistoryRepository historyRepository;
    private final IssueTicketRepository ticketRepository;
//    private final UserRepository userRepository;

//    @Override
//    public IssueStatusHistoryResponse create(IssueStatusHistoryRequest request) {
//        IssueStatusHistory history = new IssueStatusHistory();
//        mapRequestToEntity(request, history);
//        return mapToResponse(historyRepository.save(history));
//    }

    @Override
    public IssueStatusHistoryResponse getById(Long id) {
        IssueStatusHistory history = historyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Status history not found with id: " + id));
        return mapToResponse(history);
    }

    @Override
    public List<IssueStatusHistoryResponse> getAll() {
        return historyRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<IssueStatusHistoryResponse> getByTicketId(Long ticketId) {
        List<IssueStatusHistory> histories = historyRepository.findByIssueTicket_Id(ticketId);
        if (histories.isEmpty()) {
            throw new ResourceNotFoundException("No history found for ticket id: " + ticketId);
        }
        return histories.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        IssueStatusHistory history = historyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Status history not found with id: " + id));
        historyRepository.delete(history);
    }



//    private void mapRequestToEntity(IssueStatusHistoryRequest req, IssueStatusHistory entity) {
//        entity.setStatus(req.getStatus());
//        entity.setComment(req.getComment());
//
//        if (req.getIssueTicketId() != null) {
//            IssueTicket ticket = ticketRepository.findById(req.getIssueTicketId())
//                    .orElseThrow(() -> new ResourceNotFoundException("Ticket not found"));
//            entity.setIssueTicket(ticket);
//        }
//
//        if (req.getChangedById() != null) {
//            User user = userRepository.findById(req.getChangedById())
//                    .orElseThrow(() -> new ResourceNotFoundException("User not found"));
//            entity.setChangedBy(user);
//        }
//    }

    private IssueStatusHistoryResponse mapToResponse(IssueStatusHistory entity) {
        return IssueStatusHistoryResponse.builder()
                .id(entity.getId())
                .status(entity.getStatus())
                .comment(entity.getComment())
                .changedByName(entity.getChangedBy() != null ? entity.getChangedBy().getFirstName() + entity.getChangedBy().getLastName() : null)
                .issueTicketId(entity.getIssueTicket() != null ? entity.getIssueTicket().getId() : null)
                .createDateTime(entity.getCreateDateTime())
                .build();
    }
}

