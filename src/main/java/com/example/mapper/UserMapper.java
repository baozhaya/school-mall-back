package com.example.mapper;

import com.example.entity.User;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface UserMapper {



    List<User> selectAll(String name);
    List<User> selectAll(String name, BigDecimal account);

    void deleteById(Integer id);
    void insert(User user);

    void updateById(User user);

    User selectByUsername(String username);

    User selectById(Integer id);
}
