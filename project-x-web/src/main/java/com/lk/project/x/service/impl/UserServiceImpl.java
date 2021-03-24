package com.lk.project.x.service.impl;

import com.lk.project.x.entity.PasswordResetTokenEntity;
import com.lk.project.x.entity.UserEntity;
import com.lk.project.x.entity.VerificationTokenEntity;
import com.lk.project.x.exception.UserAlreadyExistException;
import com.lk.project.x.repo.PasswordResetTokenRepository;
import com.lk.project.x.repo.UserRepository;
import com.lk.project.x.repo.VerificationTokenRepository;
import com.lk.project.x.resource.UserResource;
import com.lk.project.x.service.UserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Named("userService")
public class UserServiceImpl implements UserService {

    @Inject
    UserRepository repo;

    @Inject
    PasswordResetTokenRepository passwordResetTokenRepository;

    @Inject
    ModelMapper mapper;


    @Inject
    private VerificationTokenRepository tokenRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserResource saveOrUpdate(UserResource resource) {
        if (null == resource.getId()) {
            return createUser(resource);
        } else {
            return updateUser(resource);
        }
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }

    private UserResource createUser(UserResource resource) {
       // UserEntity entity = mapper.asEntity(resource);

        //return mapper.asResource(repo.saveAndFlush(entity));
        return new UserResource();

    }

    private UserResource updateUser(UserResource resource) {
        Optional<UserEntity> optionalEntity = Optional.ofNullable(repo.findById(resource.getId()).orElse(null));
        // TODO: perform optimistic locking check
        //UserEntity entity = mapper.updateEntity(resource, optionalEntity.get());
        UserEntity entity = new UserEntity();
        entity = repo.saveAndFlush(entity);
        //return mapper.updateResource(entity, resource);
        return new UserResource();
    }

    @Override
    public void createPasswordResetTokenForUser(UserResource resource, final String token) {
        //UserEntity entity = mapper.asEntity(resource);

        UserEntity entity = new UserEntity();
        final PasswordResetTokenEntity myToken = new PasswordResetTokenEntity(token, entity);
        passwordResetTokenRepository.save(myToken);
    }

    @Override
    public UserResource registerNewUserAccount(UserResource resource) throws UserAlreadyExistException {
        if (emailExist(resource.getEmail())) {
            throw new UserAlreadyExistException(
                    "There is an account with that email adress: "
                            + resource.getEmail());
        }

        UserEntity user = new UserEntity();
        user.setFirstName(resource.getFirstName());
        user.setLastName(resource.getLastName());
        user.setPassword(resource.getPassword());
        user.setEmail(resource.getEmail());
        //user.setRole(new Role(Integer.valueOf(1), user));
        //return mapper.asResource(repo.save(user));
        return new UserResource();
    }

    private boolean emailExist(String email) {
        return repo.findByEmail(email) != null;
    }

    @Override
    public UserResource getUser(String verificationToken) {
        UserEntity userEntity = tokenRepository.findByToken(verificationToken).getUser();
        //return mapper.asResource(userEntity);
        return new UserResource();
    }
/*
    @Override
    public void saveRegisteredUser(UserEntity entity) {
        saveOrUpdate(mapper.asResource(entity));
    }*/

    @Override
    public void saveRegisteredUser(UserEntity entity) {
        saveOrUpdate(new UserResource());
    }



    @Override
    public void createVerificationToken(UserEntity entity, String token) {
        VerificationTokenEntity myToken = new VerificationTokenEntity(token, entity);
        tokenRepository.save(myToken);
    }

    @Override
    public VerificationTokenEntity getVerificationToken(String VerificationToken) {
        return tokenRepository.findByToken(VerificationToken);
    }

    @Override
    public String validatePasswordResetToken(String token) {
        final PasswordResetTokenEntity passToken = passwordResetTokenRepository.findByToken(token);

        return !isTokenFound(passToken) ? "invalidToken"
                : isTokenExpired(passToken) ? "expired"
                : null;
    }

    @Override
    public List<UserResource> findAll() {
        mapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);
        List<UserResource> userList = new ArrayList<UserResource>();
        //mapper.map(repo.findAll(),userList);
        return mapList(repo.findAll(),UserResource.class);
    }

    private boolean isTokenFound(PasswordResetTokenEntity passToken) {
        return passToken != null;
    }

    private boolean isTokenExpired(PasswordResetTokenEntity passToken) {
        final Calendar cal = Calendar.getInstance();
        return passToken.getExpiryDate().before(cal.getTime());
    }

    public void changeUserPassword(UserEntity user, String password) {
        //user.setPassword(passwordEncoder.encode(password));
        repo.save(user);
    }

    private <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
        return source
                .stream()
                .map(element -> mapper.map(element, targetClass))
                .collect(Collectors.toList());
    }
}
