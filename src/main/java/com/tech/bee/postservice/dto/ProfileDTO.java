package com.tech.bee.postservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProfileDTO {
    private String identifier;
    private String firstName;
    private String lastName;
    private String fullName;
    private String email;
    private String profilePic;
    private String profilePicPath;
    private List<String> interests;
    private StatsDTO statsDTO;
    private String dateOfBirth;
}
