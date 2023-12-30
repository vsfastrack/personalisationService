package com.tech.bee.postservice.resources;

import com.tech.bee.postservice.constants.ApiConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = ApiConstants.PathConstants.PATH_PROFILE_RESOURCE)
@Slf4j
public class ProfileResource {

}
