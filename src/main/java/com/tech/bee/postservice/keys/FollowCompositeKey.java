package com.tech.bee.postservice.keys;

import com.tech.bee.postservice.entity.ProfileEntity;

import java.io.Serializable;
import java.util.Objects;

public class FollowCompositeKey implements Serializable {
    private Long follower;
    private Long followed;

    // Constructors, getters, setters, and equals/hashCode methods

    // Implement equals and hashCode based on both follower and followed fields
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FollowCompositeKey that = (FollowCompositeKey) o;
        return Objects.equals(follower, that.follower) &&
                Objects.equals(followed, that.followed);
    }

    @Override
    public int hashCode() {
        return Objects.hash(follower, followed);
    }
}
