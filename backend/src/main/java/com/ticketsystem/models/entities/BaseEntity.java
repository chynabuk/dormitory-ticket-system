package com.ticketsystem.models.entities;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@MappedSuperclass
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "is_deleted")
    Boolean isDeleted;

    @Column(name = "create_date_time")
    LocalDateTime createDateTime;

    @Column(name = "update_date_time")
    LocalDateTime updateDateTime;

    @PostPersist
    public void creating(){
        createDateTime = LocalDateTime.now();
        updateDateTime = createDateTime;
        isDeleted = false;
    }

    @PostUpdate
    public void updating(){
        updateDateTime = LocalDateTime.now();
    }
}
