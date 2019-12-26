package com.lk.project.x.repo;

import com.lk.project.x.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByFirstName(String firstName);

    UserEntity findByUserName(String userName);
}
