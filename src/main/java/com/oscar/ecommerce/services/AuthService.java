package com.oscar.ecommerce.services;

import com.auth0.client.auth.AuthAPI;
import com.auth0.exception.Auth0Exception;
import com.auth0.net.Request;
import com.auth0.json.auth.UserInfo;
import com.auth0.net.Response;
import com.oscar.ecommerce.models.SturshkUser;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final AuthAPI authAPI;
    public AuthService() {
        this.authAPI = new AuthAPI("${spring.security.oauth2.resourceserver.jwt.issuer-uri}", "${spring.security.oauth2.resourceserver.opaquetoken.client-id}", "${spring.security.oauth2.resourceserver.opaquetoken.client-secret}");
    }

    public Response<UserInfo> getUserInfo(String accessToken) throws Auth0Exception {
        Request<UserInfo> request = authAPI.userInfo(accessToken);
        return request.execute();
    }

    public SturshkUser createUserFromUserInfo(UserInfo userInfo) {
        SturshkUser sturshkUser = new SturshkUser();
        sturshkUser.setAuth0Id(userInfo.getValues().get("sub").toString());
        sturshkUser.setEmail(userInfo.getValues().get("email").toString());
        sturshkUser.setName(userInfo.getValues().get("name").toString());
        return sturshkUser;
    }

}
