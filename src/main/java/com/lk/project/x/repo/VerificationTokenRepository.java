package com.lk.project.x.repo;

import com.lk.project.x.entity.UserEntity;
import com.lk.project.x.entity.VerificationTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationTokenRepository
        extends JpaRepository<VerificationTokenEntity, Long> {

    VerificationTokenEntity findByToken(String token);

    VerificationTokenEntity findByUser(UserEntity user);
}
