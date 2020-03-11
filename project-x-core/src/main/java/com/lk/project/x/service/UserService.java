package com.lk.project.x.service;

import com.lk.project.x.entity.UserEntity;
import com.lk.project.x.resource.UserResource;

public interface UserService {

    UserResource saveOrUpdate (UserResource resource);

    /**
     * Delete.
     *
     * @param id the id
     */
    void delete(Long id);

    void createPasswordResetTokenForUser(UserResource resource, String token);
}
