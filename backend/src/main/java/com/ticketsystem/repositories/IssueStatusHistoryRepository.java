package com.ticketsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IssueStatusHistoryRepository extends JpaRepository<IssueStatusHistory, Long> {
    List<IssueStatusHistory> findByIssueTicket_Id(Long issueTicketId);
}

