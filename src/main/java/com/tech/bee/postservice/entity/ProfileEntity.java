package com.tech.bee.postservice.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


@NoArgsConstructor
@Data
@Entity
@Table(name = "profile")
public class ProfileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String profileIdentifier = UUID.randomUUID().toString();
    @Column(nullable = false)
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private String profilePicPath;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "profile_interests",
            joinColumns = @JoinColumn(name = "profile_id"),
            inverseJoinColumns = @JoinColumn(name = "interest_id")
    )
    private Set<InterestEntity> interests = new HashSet<>();
    @ManyToMany
    @JoinTable(
            name = "profile_followers",
            joinColumns = @JoinColumn(name = "profile_id"),
            inverseJoinColumns = @JoinColumn(name = "follower_id")
    )
    private Set<ProfileEntity> followers = new HashSet<>();
    @OneToOne(mappedBy = "profile", cascade = CascadeType.ALL, orphanRemoval = true)
    private StatsEntity statistics;
    @ManyToMany(mappedBy = "followers")
    private Set<ProfileEntity> following = new HashSet<>();
    private LocalDate dateOfBirth;
    @CreationTimestamp
    private LocalDateTime createdWhen;
    @UpdateTimestamp
    private LocalDateTime lastModified;
    public String getFullName(){
        StringBuilder stringBuilder = new StringBuilder();
        return stringBuilder.append(this.getFirstName()).append(" ").append(this.getLastName()).toString();
    }
}
