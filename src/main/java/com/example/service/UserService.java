package com.example.service;

import com.example.entity.User;
import com.example.mapper.UserMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Resource
    private UserMapper userMapper;

    /**
     * 分页查询的方法
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<User> selectPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> list =userMapper.selectAll();
        return PageInfo.of(list);
    }


}
