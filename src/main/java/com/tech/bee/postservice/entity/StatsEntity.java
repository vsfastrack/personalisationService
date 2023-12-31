package com.tech.bee.postservice.entity;

import jdk.jfr.Enabled;
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
@Table(name="statistics")
public class StatsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String statsIdentifier = UUID.randomUUID().toString();
    private Long likesCount;
    private Long followersCount;
    private Long followingCount;
    private Long commentsCount;
    private Long sharesCount;
    @OneToOne
    @JoinColumn(name = "profile_id")
    private ProfileEntity profile;
    @CreationTimestamp
    private LocalDateTime createdWhen;
    @UpdateTimestamp
    private LocalDateTime modifiedWhen;
}
