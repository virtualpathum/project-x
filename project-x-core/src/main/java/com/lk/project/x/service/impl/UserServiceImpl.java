package com.lk.project.x.service.impl;

import com.lk.project.x.entity.PasswordResetTokenEntity;
import com.lk.project.x.entity.UserEntity;
import com.lk.project.x.mapper.UserMapper;
import com.lk.project.x.repo.PasswordResetTokenRepository;
import com.lk.project.x.repo.UserRepository;
import com.lk.project.x.resource.UserResource;
import com.lk.project.x.service.UserService;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Optional;

@Named("userService")
public class UserServiceImpl implements UserService {

    @Inject
    UserRepository repo;

    @Inject
    PasswordResetTokenRepository passwordResetTokenRepository;

    @Inject
    UserMapper mapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserResource saveOrUpdate(UserResource resource) {
        if (null == resource.getId()) {
            return createStudent(resource);
        } else {
            return updateStudent(resource);
        }
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }

    private UserResource createStudent(UserResource resource) {
        UserEntity entity = mapper.asEntity(resource);

        return mapper.asResource(repo.saveAndFlush(entity));
    }

    private UserResource updateStudent(UserResource resource) {
        Optional<UserEntity> optionalEntity = Optional.ofNullable(repo.findById(resource.getId()).orElse(null));
        // TODO: perform optimistic locking check
        UserEntity entity = mapper.updateEntity(resource, optionalEntity.get());

        entity = repo.saveAndFlush(entity);
        return mapper.updateResource(entity, resource);
    }

    @Override
    public void createPasswordResetTokenForUser(UserResource resource, final String token) {
        UserEntity entity = mapper.asEntity(resource);
        final PasswordResetTokenEntity myToken = new PasswordResetTokenEntity(token, entity);
        passwordResetTokenRepository.save(myToken);
    }
}
