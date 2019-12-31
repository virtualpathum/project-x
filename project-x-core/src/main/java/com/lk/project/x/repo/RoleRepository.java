package com.lk.project.x.repo;

import com.lk.project.x.entity.RoleEntity;
import com.lk.project.x.entity.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    RoleEntity findByName(RoleName roleName);
    
}
