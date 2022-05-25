package com.lk.project.x.repo;

import com.lk.project.x.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

// TODO: Auto-generated Javadoc

/**
 * The Interface StudentRepository.
 */
//TODO : Remove below repository methods - extends QueryDslPredicateExecutor and implement predicates
public interface StudentRepository extends JpaRepository<StudentEntity, Long> {

    /**
     * Find by name.
     *
     * @param name the name
     * @return the student entity
     */
    StudentEntity findByName(String name);

}
