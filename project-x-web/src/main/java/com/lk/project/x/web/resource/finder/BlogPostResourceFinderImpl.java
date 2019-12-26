package com.lk.project.x.web.resource.finder;

import com.lk.project.x.entity.BlogPostEntity;
import com.lk.project.x.mapper.BlogPostMapper;
import com.lk.project.x.repo.BlogPostRepository;
import com.lk.project.x.resource.BlogPostResource;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named("blogPostResourceFinder")
public class BlogPostResourceFinderImpl extends AbstractResourceFinder<BlogPostResource, BlogPostEntity, BlogPostRepository, Long> implements BlogPostResourceFinder {

    private BlogPostMapper mapper;

    @Inject
    public BlogPostResourceFinderImpl(BlogPostRepository repo, BlogPostMapper mapper) {
            super(repo);
            this.mapper = mapper;
    }

    @Override
    protected BlogPostResource toResource(BlogPostEntity entity) {

            return mapper.asResource(entity);
    }

    @Override
    public BlogPostResource findById(Long id) {

        BlogPostEntity entity = repo.findOne(id);
            return mapper.asResource(entity);
    }

    @Override
    public List<BlogPostResource> findAllUsers() {
            List<BlogPostEntity> list = repo.findAll();
            return toResources(list);
    }
}
