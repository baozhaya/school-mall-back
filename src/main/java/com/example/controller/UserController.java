package com.example.controller;

import com.example.common.Result;
import com.example.entity.User;
import com.example.mapper.UserMapper;
import com.example.service.UserService;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserMapper userMapper;
    @Autowired
    private UserService userService;

    /**
     *
     * @param pageNum 当前页码
     * @param pageSize 每页展示的个数
     *@return 分页数据
     * 接口的请求方式：http://localhost:8081/user/selectPage?PageNum=1&Pagesize=10
     */
    @GetMapping("/selectPage")
    public Result selectPage(@RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize,
                             @RequestParam(required = false) String name){
       PageInfo<User> pageInfo =userService.selectPage(pageNum,pageSize,name);
       return Result.success(pageInfo);
    }
}
