package com.tech.bee.postservice.entity;

import com.tech.bee.postservice.keys.FollowCompositeKey;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@Entity
@Table(name="follow_relation")
@IdClass(FollowCompositeKey.class)
public class FollowRelationEntity {

    @ManyToOne
    @Id
    private ProfileEntity follower;
    @ManyToOne
    @Id
    private ProfileEntity followed;
    @CreationTimestamp
    private LocalDateTime createdWhen;
    @UpdateTimestamp
    private LocalDateTime lastModified;
}
