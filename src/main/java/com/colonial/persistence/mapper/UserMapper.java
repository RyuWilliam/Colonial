package com.colonial.persistence.mapper;

import com.colonial.domain.model.User;
import com.colonial.persistence.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserEntity toEntity(User user);
    List<User> toEntities(List<User> users);

    User toUser(UserEntity user);
    List<User> toUsers(List<UserEntity> users);

}
