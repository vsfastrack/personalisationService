package com.tech.bee.postservice.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@Data
@Entity
@Table(name = "interest")
public class InterestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String interestIdentifier = UUID.randomUUID().toString();
    private String tagIdentifier;
    private String name;
    @CreationTimestamp
    private LocalDateTime createdWhen;
    @UpdateTimestamp
    private LocalDateTime lastModified;

}
