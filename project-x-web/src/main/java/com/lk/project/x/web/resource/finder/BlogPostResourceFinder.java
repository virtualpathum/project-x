package com.lk.project.x.web.resource.finder;

import com.lk.project.x.resource.BlogPostResource;


import java.util.List;

public interface BlogPostResourceFinder extends ResourceFinder<BlogPostResource, Long> {

    List<BlogPostResource> findAllUsers();

    BlogPostResource findById(Long id);
}