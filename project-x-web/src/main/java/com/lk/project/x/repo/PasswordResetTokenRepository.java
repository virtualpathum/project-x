package com.lk.project.x.repo;

import com.lk.project.x.entity.PasswordResetTokenEntity;
import com.lk.project.x.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.stream.Stream;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetTokenEntity, Long> {

    PasswordResetTokenEntity findByToken(String token);

    PasswordResetTokenEntity findByUser(UserEntity user);

    Stream<PasswordResetTokenEntity> findAllByExpiryDateLessThan(Date now);

    void deleteByExpiryDateLessThan(Date now);

    @Modifying
    @Query("delete from PasswordResetTokenEntity t where t.expiryDate <= ?1")
    void deleteAllExpiredSince(Date now);
}
