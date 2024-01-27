package com.oscar.ecommerce.services;

import com.auth0.client.auth.AuthAPI;
import com.auth0.exception.Auth0Exception;
import com.auth0.net.Request;
import com.auth0.json.auth.UserInfo;
import com.oscar.ecommerce.models.SturshkUser;
import com.oscar.ecommerce.repositories.SturshkUserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final AuthAPI authAPI;
    private final SturshkUserRepository sturshkUserRepository;

    public AuthService(@Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}") String issuerUri,
                       @Value("${spring.security.oauth2.resourceserver.opaquetoken.client-id}") String clientId,
                       @Value("${spring.security.oauth2.resourceserver.opaquetoken.client-secret}") String clientSecret,
                       SturshkUserRepository sturshkUserRepository) {
        this.authAPI = AuthAPI.newBuilder(issuerUri, clientId, clientSecret).build();
        this.sturshkUserRepository = sturshkUserRepository;
    }
    public UserInfo getUserInfo(String accessToken) throws Auth0Exception {
        Request<UserInfo> request = authAPI.userInfo(accessToken);
        return request.execute().getBody();
    }

    public void createUserFromUserInfo(UserInfo userInfo) {
        SturshkUser sturshkUser = new SturshkUser();
        sturshkUser.setAuth0Id(userInfo.getValues().get("sub").toString());
        sturshkUser.setEmail(userInfo.getValues().get("email").toString());
        sturshkUser.setName(userInfo.getValues().get("name").toString());
        sturshkUserRepository.findById(sturshkUser.getAuth0Id()).orElse(sturshkUserRepository.save(sturshkUser));
    }
}
