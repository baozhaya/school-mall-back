package com.example.service;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.example.entity.Account;
import com.example.entity.User;
import com.example.exception.CustomException;
import com.example.mapper.UserMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
    public PageInfo<User> selectPage(Integer pageNum, Integer pageSize, String name, BigDecimal account) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> list =userMapper.selectAll(name,account);
        return PageInfo.of(list);
    }


    public void deleteById(Integer id) {
    userMapper.deleteById(id);
    }

    public void add(User user) {
        String username = user.getUsername();
        //检测账号是否重复
        User dbUser = userMapper.selectByUsername(username);
        if (dbUser != null) {
            throw new CustomException("新增失败，帐号重复复");
        }
        if(StrUtil.isBlank(user.getPassword())){
            //默认密码
            user.setPassword("123");
        }
        if(StrUtil.isBlank(user.getName())){
            //默认姓名
            user.setName(user.getUsername());
        }
        user.setRole("普通用户");//默认用户的角色
        user.getAccount(BigDecimal.ZERO);//默认用户的余额
        userMapper.insert(user);
    }

    public void update(User user) {
        //user用户里必须包含ID，否则无法更新数据
        userMapper.updateById(user);
    }
    public Account login(Account account) {
        User dbUser =userMapper.selectByUsername(account.getUsername());
        if (ObjectUtil.isNull(dbUser)) {
            throw new CustomException("用户不存在");
        }
        if (!account.getPassword().equals(dbUser.getPassword())) {
            throw new CustomException("账号或密码错误");
        }
        return dbUser;
    }

}
