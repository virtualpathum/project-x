package com.lk.project.x.events;

import com.lk.project.x.entity.UserEntity;
import org.springframework.context.ApplicationEvent;

import java.util.Locale;

public class OnRegistrationCompleteEvent extends ApplicationEvent {

    private String appUrl;
    private Locale locale;
    private UserEntity userEntity;
    public OnRegistrationCompleteEvent(
            UserEntity user, Locale locale, String appUrl) {
        super(user);

        this.userEntity = user;
        this.locale = locale;
        this.appUrl = appUrl;
    }
    public String getAppUrl() {
        return appUrl;
    }

    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public UserEntity getUser() {
        return userEntity;
    }

    public void setUser(UserEntity userEntity) {
        this.userEntity = userEntity;
    }


}
