// GENERATED BY S3LM4
package com.lk.project.x.mapper;


@org.springframework.stereotype.Service("")
public final class UserMapperSelmaGeneratedClass
    implements UserMapper {

  @Override
  public final com.lk.project.x.resource.UserResource asResource(com.lk.project.x.entity.UserEntity inUserEntity) {
    com.lk.project.x.resource.UserResource out = null;
    if (inUserEntity != null) {
      out = new com.lk.project.x.resource.UserResource();
      out.setEmail(inUserEntity.getEmail());
      out.setFirstName(inUserEntity.getFirstName());
      out.setId(inUserEntity.getId());
      out.setLastName(inUserEntity.getLastName());
      out.setPassword(inUserEntity.getPassword());
      out.setUserName(inUserEntity.getUserName());
    }
    return out;
  }


  @Override
  public final com.lk.project.x.entity.UserEntity asEntity(com.lk.project.x.resource.UserResource inUserResource) {
    com.lk.project.x.entity.UserEntity out = null;
    if (inUserResource != null) {
      out = new com.lk.project.x.entity.UserEntity();
      out.setEmail(inUserResource.getEmail());
      out.setFirstName(inUserResource.getFirstName());
      out.setId(inUserResource.getId());
      out.setLastName(inUserResource.getLastName());
      out.setPassword(inUserResource.getPassword());
      out.setUserName(inUserResource.getUserName());
    }
    return out;
  }


  @Override
  public final com.lk.project.x.resource.UserResource updateResource(com.lk.project.x.entity.UserEntity inUserEntity, com.lk.project.x.resource.UserResource out) {
    if (inUserEntity != null) {
      if (out == null) {
        out = new com.lk.project.x.resource.UserResource();
      }
      out.setEmail(inUserEntity.getEmail());
      out.setFirstName(inUserEntity.getFirstName());
      out.setId(inUserEntity.getId());
      out.setLastName(inUserEntity.getLastName());
      out.setPassword(inUserEntity.getPassword());
      out.setUserName(inUserEntity.getUserName());
    }
    if (fr.xebia.extras.selma.SelmaUtils.areNull(inUserEntity)) {
      out = null;
    }
    return out;
  }


  @Override
  public final com.lk.project.x.entity.UserEntity updateEntity(com.lk.project.x.resource.UserResource inUserResource, com.lk.project.x.entity.UserEntity out) {
    if (inUserResource != null) {
      if (out == null) {
        out = new com.lk.project.x.entity.UserEntity();
      }
      out.setEmail(inUserResource.getEmail());
      out.setFirstName(inUserResource.getFirstName());
      out.setId(inUserResource.getId());
      out.setLastName(inUserResource.getLastName());
      out.setPassword(inUserResource.getPassword());
      out.setUserName(inUserResource.getUserName());
    }
    if (fr.xebia.extras.selma.SelmaUtils.areNull(inUserResource)) {
      out = null;
    }
    return out;
  }



  /**
   * Single constructor
   */
  public UserMapperSelmaGeneratedClass() {
  }

}