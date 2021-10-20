package com.kingfood.backend.constants;

public interface AppConstant {
    interface O2Constants {
        String CLIEN_ID = "client_id";
        String CLIENT_SECRET = "client_secret";
        String GRANT_TYPE_PASSWORD = "password";
        String AUTHORIZATION_CODE = "authorization_code";
        String REFRESH_TOKEN = "refresh_token";
        String IMPLICIT = "implicit";
        String SCOPE_READ = "read";
        String SCOPE_WRITE = "write";
        String TRUST = "openid";
        int ACCESS_TOKEN_VALIDITY_MILLISECONDS = 1 * 60 * 60;
        int REFRESH_TOKEN_VALIDITY_SECONDS = 6 * 60 * 60;
        int ACCESS_TOKEN_VALIDITY__SUPER_MILLISECONDS = 3 * 60 * 60;
        String HEADER_STRING = "Authorization";
        String TOKEN_PREFIX = "Bearer ";
        //String USER_NAME = "user_name";
        String USER_NAME = "user_name";
        String EMAIL = "email";
    }

    interface TOKEN {
        String USER_ID = "userId";
        String FULL_NAME = "fullName";
        String USER_NAME = "username";
        String EMAIL = "email";
    }

    interface ResourceServer {
        final String RESOURCE_ID = "resource";
    }

    interface ACTIVE {
        final int ACTIVE_STATUS = 1;
        final int INACTIVE_STATUS = 0;
    }

    interface statusSendFile {
        Integer SUCCESS = 1;
        Integer ERROR = 0;
    }

    interface deletedStatus {
        Integer ACTIVE = 1;
        Integer INACTIVE = 0;
    }

    interface MAIL {
        String user = "aeantsoftcu@gmail.com";
    }

    interface principalKey {
        String USER_NAME = "username";
        String USER_ID = "userId";
        String FULL_NAME = "fullName";
        String HEADER_TENANT_ID = "X-TenantId";
    }

    interface sendMail {
        String successPayPal = "success";
    }
}
