package com.example.controller;

import com.example.common.Result;
import com.example.entity.User;
import com.example.mapper.UserMapper;
import com.example.service.UserService;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

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
                             @RequestParam(required = false) String name,
                             @RequestParam(required = false) BigDecimal account){
       PageInfo<User> pageInfo =userService.selectPage(pageNum,pageSize,name,account);
       return Result.success(pageInfo);
    }

    /**
     * 删除数据
     * 接口路径：user/delete/1
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id){
        userService.deleteById(id);
        return Result.success();
    }

    /**
     * 新增数据
     * @param user 参数的对象
     * @return
     */

    @PostMapping("/add")
    public Result add(@RequestBody User user){
        userService.add(user);
        return Result.success();

    }

    /**
     * 更新数据
     * @param user 参数的对象 编辑后的数据
     */
    @PutMapping("/uodate")
public Result uodate(@RequestBody User user){
        userService.update(user);
        return Result.success();
}
}
