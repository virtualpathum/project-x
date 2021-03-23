package com.lk.project.x.service;

import com.lk.project.x.resource.BlogPostResource;


public interface BlogPostService {

    BlogPostResource saveOrUpdate (BlogPostResource resource);

    /**
     * Delete.
     *
     * @param id the id
     */
    void delete(Long id);
}
