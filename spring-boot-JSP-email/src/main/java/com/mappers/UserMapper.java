package com.mappers;

import java.util.List;

import com.entities.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface UserMapper {

	List<User> selectAll();

    Integer addUser(User user);


    User findByCode(@Param("code") String code);

    void updateUser(User user);
}
