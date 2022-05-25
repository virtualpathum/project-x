package com.lk.project.x.payload;/* Created by Dell on 30/12/2019 */

import java.util.List;

public class JwtAuthenticationResponse {

    private String accessToken;
    private Long id;
    String userName;
    String email;
    List<String> roles;
    private String tokenType = "Bearer";

    public JwtAuthenticationResponse(String accessToken, Long id, String userName, String email, List<String> roles ) {
        this.accessToken = accessToken;
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.roles = roles;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
