package com.lk.project.x.web.resource.finder;

import com.lk.project.x.resource.UserResource;

import java.util.List;

public interface UserResourceFinder extends ResourceFinder<UserResource, Long> {

    List<UserResource> findAllUsers();

    UserResource findById(Long id);
}
