package com.colonial.persistence.mapper;

import com.colonial.domain.model.User;
import com.colonial.persistence.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    User toUser(UserEntity entity);
    List<User> toUsers(List<UserEntity> entities);

    UserEntity toEntity(User user);
    List<UserEntity> toEntities(List<User> users);
}