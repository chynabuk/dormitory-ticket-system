package com.ticketsystem.models.entities;

import com.ticketsystem.models.enums.Status;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "issue_status_history")
public class IssueStatusHistory extends BaseEntity {
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    Status status;
    @ManyToOne
    @JoinColumn(name = "issue_ticket_id")
    IssueTicket issueTicket;
    @ManyToOne
    @JoinColumn(name = "user_id")
    User changedBy;

}
