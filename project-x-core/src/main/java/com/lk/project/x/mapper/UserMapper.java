package com.lk.project.x.mapper;

import com.lk.project.x.entity.UserEntity;
import com.lk.project.x.resource.UserResource;
import fr.xebia.extras.selma.IgnoreMissing;
import fr.xebia.extras.selma.IoC;
import fr.xebia.extras.selma.Mapper;
import fr.xebia.extras.selma.Maps;

@Mapper(withIoC = IoC.SPRING)
public interface UserMapper {

    @Maps(withIgnoreMissing = IgnoreMissing.DESTINATION)
    UserResource asResource(UserEntity entity);

    @Maps(withIgnoreMissing = IgnoreMissing.SOURCE)
    UserEntity asEntity(UserResource resource);

    @Maps(withIgnoreMissing = IgnoreMissing.DESTINATION)
    UserResource updateResource(UserEntity entity, UserResource resource);

    @Maps(withIgnoreMissing = IgnoreMissing.SOURCE)
    UserEntity updateEntity(UserResource resource, UserEntity entity);

}
