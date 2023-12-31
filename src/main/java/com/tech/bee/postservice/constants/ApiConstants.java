package com.tech.bee.postservice.constants;

public class ApiConstants {
    public static final class PathConstants{
        public static final String PATH_PROFILE_RESOURCE="/api/v1/profile";
        public static final String PATH_PREFERENCE_RESOURCE="/api/v1/preferences";
        public static final String PATH_INTEREST_RESOURCE="/api/v1/interests";
        public static final String PATH_DOCUMENT = "/api/v1/document";
        public static final String UPLOAD_DOCUMENT = "/upload";
        public static final String FETCH_DOCUMENT = "/fetch/{key}";
        public static final String PATH_FETCH_DOC = "/api/v1/fetch/image";
    }
    public static final class ErrorCodeConstants{
        public static final String CODE_FIELD_CANNOT_BE_EMPTY="400.001";
        public static final String CODE_FIELD_VALUE_LENGTH_GREATER_THAN_DEFINED_LENGTH="404.002";
        public static final String CODE_RESOURCE_NOT_FOUND="404.001";
        public static final String CODE_RESOURCE_CONFLICT ="409.001";
        public static final String CODE_OPERATION_FORBIDDEN ="403.001";
    }
    public static final class ErrorMsgConstants{
        public static final String MESSAGE_FIELD_CANNOT_BE_EMPTY="Field cannot be empty";
        public static final String MESSAGE_FIELD_VALUE_LENGTH_GREATER_THAN_DEFINED_LENGTH="Field length cannot be greater than %s characters";
        public static final String MESSAGE_RESOURCE_NOT_FOUND="Resource not found";
        public static final String MESSAGE_RESOURCE_CONFLICT="Resource already exists";
        public static final String MESSAGE_OPERATION_FORBIDDEN ="Operation forbidden";
    }
    public static final class KeyConstants{
        public static final String KEY_TITLE="title";
        public static final String KEY_SUB_TITLE="subtitle";
        public static final String KEY_CONTENT="content";
        public static final String KEY_AUTHOR="authorId";
        public static final String KEY_TAG="tag";
        public static final String KEY_TAG_NAME="tag.name";
        public static final String KEY_POST="post";
        public static final String KEY_REACTION="reaction";
        public static final String KEY_SERIES="series";
        public static final String KEY_USER="user";
        public static final String KEY_FIRST_NAME="firstName";
        public static final String KEY_LAST_NAME="lastName";
        public static final String KEY_EMAIL="email";
        public static final String KEY_PROFILE="profile";
    }

    public static final class RoleConstants{
        public static final String ROLE_USER="ROLE_USER";
        public static final String ROLE_AUTHOR="ROLE_AUTHOR";
    }
}
