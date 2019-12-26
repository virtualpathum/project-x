package com.lk.project.x.mapper;

import com.lk.project.x.entity.BlogPostEntity;
import com.lk.project.x.resource.BlogPostResource;
import fr.xebia.extras.selma.IgnoreMissing;
import fr.xebia.extras.selma.IoC;
import fr.xebia.extras.selma.Mapper;
import fr.xebia.extras.selma.Maps;

@Mapper(withIoC = IoC.SPRING)
public interface BlogPostMapper {

    @Maps(withIgnoreMissing = IgnoreMissing.DESTINATION)
    BlogPostResource asResource(BlogPostEntity entity);

    @Maps(withIgnoreMissing = IgnoreMissing.SOURCE)
    BlogPostEntity asEntity(BlogPostResource resource);

    @Maps(withIgnoreMissing = IgnoreMissing.DESTINATION)
    BlogPostResource updateResource(BlogPostEntity entity, BlogPostResource resource);

    @Maps(withIgnoreMissing = IgnoreMissing.SOURCE)
    BlogPostEntity updateEntity(BlogPostResource resource, BlogPostEntity entity);
}
