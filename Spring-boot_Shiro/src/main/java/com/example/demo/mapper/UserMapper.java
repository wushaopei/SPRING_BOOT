package com.example.demo.mapper;


import com.example.demo.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * @ClassName UserMapper
 * @Description TODO
 * @Author wushaopei
 * @Date 2019/9/23 17:17
 * @Version 1.0
 */
@Mapper
@Component
public interface UserMapper {

    User findByUsername(@Param("username") String username);
}
