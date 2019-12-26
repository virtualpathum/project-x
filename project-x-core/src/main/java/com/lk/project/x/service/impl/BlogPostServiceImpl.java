package com.lk.project.x.service.impl;

import com.lk.project.x.entity.BlogPostEntity;
import com.lk.project.x.mapper.BlogPostMapper;
import com.lk.project.x.repo.BlogPostRepository;
import com.lk.project.x.resource.BlogPostResource;
import com.lk.project.x.service.BlogPostService;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;

@Named("blogPostService")
public class BlogPostServiceImpl implements BlogPostService {

    @Inject
    BlogPostRepository repo;

    @Inject
    BlogPostMapper mapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BlogPostResource saveOrUpdate(BlogPostResource resource) {
        if (null == resource.getId()) {
            return create(resource);
        } else {
            return update(resource);
        }
    }

    @Override
    public void delete(Long id) {
        repo.delete(id);
    }

    private BlogPostResource create(BlogPostResource resource) {
        BlogPostEntity entity = mapper.asEntity(resource);

        return mapper.asResource(repo.saveAndFlush(entity));
    }

    private BlogPostResource update(BlogPostResource resource) {
        BlogPostEntity entity = repo.findOne(resource.getId());
        // TODO: perform optimistic locking check
        entity = mapper.updateEntity(resource, entity);

        entity = repo.saveAndFlush(entity);
        return mapper.updateResource(entity, resource);
    }
}
