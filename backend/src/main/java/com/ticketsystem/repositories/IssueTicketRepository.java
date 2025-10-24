package com.ticketsystem.repositories;

import com.ticketsystem.models.entities.IssueTicket;
import com.ticketsystem.models.enums.Status;
import com.ticketsystem.models.enums.Priority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

@Repository
public interface IssueTicketRepository extends
        JpaRepository<IssueTicket, Long>,
        JpaSpecificationExecutor<IssueTicket> {

    List<IssueTicket> findByPriority(Priority priority);

    List<IssueTicket> findByCurrentStatus(Status status);
}

