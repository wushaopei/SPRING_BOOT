package com.webcode.springboot.mappers;



import com.webcode.springboot.entities.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ClassName UserMapper
 * @Description TODO
 * @Author wushaopei
 * @Date 2019/9/23 17:17
 * @Version 1.0
 */
@Component
@Mapper
public interface UserMapper {

    User findByUsername(@Param("username") String username);

    List<User> getAll();
}
