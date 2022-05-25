package com.lk.project.x.repo;

import com.lk.project.x.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByUserNameOrEmail(String userName, String email);

    UserEntity findByUserName(String userName);

    UserEntity findByEmail(String email);

    Boolean existsByUserName(String userName);

    Boolean existsByEmail(String email);

    List<UserEntity> findByIdIn(List<Long> userIds);
}
