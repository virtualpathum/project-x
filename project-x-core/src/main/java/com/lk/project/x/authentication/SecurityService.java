package com.lk.project.x.authentication;

public interface SecurityService {

    String findLoggedInUsername();

    void autoLogin(String username, String password);
}
