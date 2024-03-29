package com.lk.project.x.web.resource.finder;

import com.lk.project.x.entity.BlogPostEntity;
//import com.lk.project.x.mapper.BlogPostMapper;
import com.lk.project.x.repo.BlogPostRepository;
import com.lk.project.x.resource.BlogPostResource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

//@Named("blogPostResourceFinder")
public class BlogPostResourceFinderImpl extends AbstractResourceFinder<BlogPostResource, BlogPostEntity, BlogPostRepository, Long> implements BlogPostResourceFinder {


    private ModelMapper mapper;

    @Autowired
    public BlogPostResourceFinderImpl(BlogPostRepository repo, ModelMapper mapper) {
            super(repo);
            this.mapper = mapper;
    }

    @Override
    protected BlogPostResource toResource(BlogPostEntity entity) {

        return mapper.map(entity, BlogPostResource.class);
    }

    @Override
    public BlogPostResource findById(Long id) {
        BlogPostEntity entity = repo.findById(id).orElse(null);
            return mapper.map(entity, BlogPostResource.class);
    }

    @Override
    public List<BlogPostResource> findAllUsers() {
            List<BlogPostEntity> list = repo.findAll();
            return toResources(list);
    }
}
