package com.lk.project.x.service.impl;

import com.lk.project.x.entity.PasswordResetTokenEntity;
import com.lk.project.x.entity.UserEntity;
import com.lk.project.x.entity.VerificationTokenEntity;
import com.lk.project.x.exception.UserAlreadyExistException;
import com.lk.project.x.mapper.UserMapper;
import com.lk.project.x.repo.PasswordResetTokenRepository;
import com.lk.project.x.repo.UserRepository;
import com.lk.project.x.repo.VerificationTokenRepository;
import com.lk.project.x.resource.UserResource;
import com.lk.project.x.service.UserService;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Calendar;
import java.util.Optional;

@Named("userService")
public class UserServiceImpl implements UserService {

    @Inject
    UserRepository repo;

    @Inject
    PasswordResetTokenRepository passwordResetTokenRepository;

    @Inject
    UserMapper mapper;


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
        UserEntity entity = mapper.asEntity(resource);

        return mapper.asResource(repo.saveAndFlush(entity));
    }

    private UserResource updateUser(UserResource resource) {
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
        return mapper.asResource(repo.save(user));
    }

    private boolean emailExist(String email) {
        return repo.findByEmail(email) != null;
    }

    @Override
    public UserResource getUser(String verificationToken) {
        UserEntity userEntity = tokenRepository.findByToken(verificationToken).getUser();
        return mapper.asResource(userEntity);
    }

    @Override
    public void saveRegisteredUser(UserEntity entity) {
        saveOrUpdate(mapper.asResource(entity));
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

    private boolean isTokenFound(PasswordResetTokenEntity passToken) {
        return passToken != null;
    }

    private boolean isTokenExpired(PasswordResetTokenEntity passToken) {
        final Calendar cal = Calendar.getInstance();
        return passToken.getExpiryDate().before(cal.getTime());
    }

   /* public void changeUserPassword(UserEntity user, String password) {
        user.setPassword(passwordEncoder.encode(password));
        repo.save(user);
    }*/
}
