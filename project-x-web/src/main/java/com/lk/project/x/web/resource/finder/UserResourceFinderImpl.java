package com.lk.project.x.web.resource.finder;

import com.lk.project.x.entity.UserEntity;
import com.lk.project.x.mapper.UserMapper;
import com.lk.project.x.repo.UserRepository;
import com.lk.project.x.resource.UserResource;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named("userResourceFinder")
public class UserResourceFinderImpl extends AbstractResourceFinder<UserResource, UserEntity, UserRepository, Long> implements UserResourceFinder {

    private UserMapper mapper;

    @Inject
    public UserResourceFinderImpl(UserRepository repo, UserMapper mapper) {
        super(repo);
        this.mapper = mapper;
    }

    @Override
    protected UserResource toResource(UserEntity entity) {

        return mapper.asResource(entity);
    }

    @Override
    public UserResource findById(Long id) {

        UserEntity entity = repo.findOne(id);
        return mapper.asResource(entity);

    }

    @Override
    public List<UserResource> findAllUsers() {
        List<UserEntity> list = repo.findAll();
        return toResources(list);

    }

}
