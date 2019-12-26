/*
package com.lk.student.manager.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface BaseJpaRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {
    default T findOne(ID id) {
        return (T) findById(id).orElse(null);
    }
}
*/
