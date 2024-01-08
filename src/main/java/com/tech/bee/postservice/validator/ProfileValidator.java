package com.tech.bee.postservice.validator;

import com.tech.bee.postservice.common.ErrorDTO;
import com.tech.bee.postservice.constants.ApiConstants;
import com.tech.bee.postservice.dto.ProfileDTO;
import com.tech.bee.postservice.enums.Enums;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProfileValidator {

    public List<ErrorDTO> validate(ProfileDTO profileDTO){
        List<ErrorDTO> notificationErrors = new ArrayList<>();
        validateFirstName(profileDTO.getFirstName() , notificationErrors);
        validateLastName(profileDTO.getLastName() , notificationErrors);
        validateEmail(profileDTO.getEmail() , notificationErrors);
        return notificationErrors;
    }

    public void validateFileFormat(final String fileName, List<ErrorDTO> notificationErrors) {
        if (StringUtils.isEmpty(fileName))
            notificationErrors.add(ErrorDTO.builder()
                    .code(ApiConstants.ErrorCodeConstants.CODE_FIELD_CANNOT_BE_EMPTY)
                    .message(ApiConstants.ErrorMsgConstants.MESSAGE_FIELD_CANNOT_BE_EMPTY)
                    .key(ApiConstants.KeyConstants.KEY_FIRST_NAME)
                    .category(Enums.ErrorCategory.VALIDATION_ERROR).build());
        if (StringUtils.isNotEmpty(fileName) && !fileName.contains(".")) {
            notificationErrors.add(ErrorDTO.builder()
                    .code(ApiConstants.ErrorCodeConstants.CODE_RESOURCE_NOT_FOUND)
                    .message(ApiConstants.ErrorMsgConstants.MESSAGE_RESOURCE_NOT_FOUND)
                    .key(ApiConstants.KeyConstants.KEY_FILE_NAME)
                    .category(Enums.ErrorCategory.VALIDATION_ERROR).build());
        }
    }

    public void validateFirstName(final String firstName , List<ErrorDTO> notificationErrors){
        if(StringUtils.isEmpty(firstName))
            notificationErrors.add(ErrorDTO.builder()
                    .code(ApiConstants.ErrorCodeConstants.CODE_FIELD_CANNOT_BE_EMPTY)
                    .message(ApiConstants.ErrorMsgConstants.MESSAGE_FIELD_CANNOT_BE_EMPTY)
                    .key(ApiConstants.KeyConstants.KEY_FIRST_NAME)
                    .category(Enums.ErrorCategory.VALIDATION_ERROR).build());
        if(StringUtils.isNotEmpty(firstName) && StringUtils.length(firstName) > 30)
            notificationErrors.add(ErrorDTO.builder()
                    .code(ApiConstants.ErrorCodeConstants.CODE_FIELD_VALUE_LENGTH_GREATER_THAN_DEFINED_LENGTH)
                    .message(String.format(ApiConstants.ErrorMsgConstants.MESSAGE_FIELD_VALUE_LENGTH_GREATER_THAN_DEFINED_LENGTH, "30"))
                    .key(ApiConstants.KeyConstants.KEY_FIRST_NAME)
                    .category(Enums.ErrorCategory.VALIDATION_ERROR).build());
    }

    public void validateLastName(final String lastName , List<ErrorDTO> notificationErrors){
        if(StringUtils.isEmpty(lastName))
            notificationErrors.add(ErrorDTO.builder()
                    .code(ApiConstants.ErrorCodeConstants.CODE_FIELD_CANNOT_BE_EMPTY)
                    .message(ApiConstants.ErrorMsgConstants.MESSAGE_FIELD_CANNOT_BE_EMPTY)
                    .key(ApiConstants.KeyConstants.KEY_LAST_NAME)
                    .category(Enums.ErrorCategory.VALIDATION_ERROR).build());
        if(StringUtils.isNotEmpty(lastName) && StringUtils.length(lastName) > 40)
            notificationErrors.add(ErrorDTO.builder()
                    .code(ApiConstants.ErrorCodeConstants.CODE_FIELD_VALUE_LENGTH_GREATER_THAN_DEFINED_LENGTH)
                    .message(String.format(ApiConstants.ErrorMsgConstants.MESSAGE_FIELD_VALUE_LENGTH_GREATER_THAN_DEFINED_LENGTH, "40"))
                    .key(ApiConstants.KeyConstants.KEY_LAST_NAME)
                    .category(Enums.ErrorCategory.VALIDATION_ERROR).build());
    }

    public void validateEmail(final String email , List<ErrorDTO> notificationErrors){
        if(StringUtils.isEmpty(email))
            notificationErrors.add(ErrorDTO.builder()
                    .code(ApiConstants.ErrorCodeConstants.CODE_FIELD_CANNOT_BE_EMPTY)
                    .message(ApiConstants.ErrorMsgConstants.MESSAGE_FIELD_CANNOT_BE_EMPTY)
                    .key(ApiConstants.KeyConstants.KEY_EMAIL)
                    .category(Enums.ErrorCategory.VALIDATION_ERROR).build());
    }
}
