package com.lk.project.x.service;

import com.lk.project.x.entity.UserEntity;
import com.lk.project.x.entity.VerificationTokenEntity;
import com.lk.project.x.exception.UserAlreadyExistException;
import com.lk.project.x.resource.UserResource;

import java.util.List;

public interface UserService {

    UserResource saveOrUpdate (UserResource resource);

    /**
     * Delete.
     *
     * @param id the id
     */
    void delete(Long id);

    void createPasswordResetTokenForUser(UserResource resource, String token);

    UserResource registerNewUserAccount(UserResource resource)
            throws UserAlreadyExistException;

    UserResource getUser(String verificationToken);

    void saveRegisteredUser(UserEntity entity);

    void createVerificationToken(UserEntity entity, String token);

    VerificationTokenEntity getVerificationToken(String VerificationToken);

    String validatePasswordResetToken(String token);

    List<UserResource> findAll();
}
