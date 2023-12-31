package com.tech.bee.postservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StatsDTO {
    private Long likesCount;
    private Long followersCount;
    private Long followingCount;
    private Long commentsCount;
    private Long sharesCount;
}
