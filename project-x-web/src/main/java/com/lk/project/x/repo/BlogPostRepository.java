package com.lk.project.x.repo;

import com.lk.project.x.entity.BlogPostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogPostRepository extends JpaRepository<BlogPostEntity, Long> {
}
