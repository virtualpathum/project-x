
package com.lk.project.x.web.resource.finder;

import com.lk.project.x.entity.UserEntity;
import com.lk.project.x.repo.UserRepository;
import com.lk.project.x.resource.UserResource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named("userResourceFinder")
public class UserResourceFinderImpl extends AbstractResourceFinder<UserResource, UserEntity, UserRepository, Long> implements UserResourceFinder {

    //private UserMapper mapper;
    @Autowired
    private ModelMapper mapper;

    @Inject
    public UserResourceFinderImpl(UserRepository repo, ModelMapper mapper) {
        super(repo);
        this.mapper = mapper;
    }

    @Override
    protected UserResource toResource(UserEntity entity) {

        return mapper.map(entity,UserResource.class);
    }

    @Override
    public UserResource findById(Long id) {

        UserEntity entity = repo.findById(id).orElse(null);
        return mapper.map(entity,UserResource.class);

    }

    @Override
    public UserResource findUserByEmail(String email) {
        UserEntity entity = repo.findByEmail(email);
        return mapper.map(entity,UserResource.class);
    }

    @Override
    public List<UserResource> findAllUsers() {
        List<UserEntity> list = repo.findAll();
        return toResources(list);

    }

}

