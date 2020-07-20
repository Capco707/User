package com.capco.controller;

import com.capco.entity.User;
import com.capco.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin //允许跨域
@RequestMapping("user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    //用户登录
    @PostMapping("login")
    public Map<String,Object> login(@RequestBody User user){
        log.info("当前登录用户的信息:[{}]",user.toString());

        Map<String,Object> map = new HashMap<>();
        try {
            User userDB = userService.login(user);
            map.put("state",true);
            map.put("msg","登录成功！");
        }catch (Exception e){
            e.printStackTrace();
            map.put("state",false);
            map.put("msg",e.getMessage());
        }
        return map;
    }


    //用户注册
    @PostMapping("register")
    public Map<String,Object> register(@RequestBody User user){
        log.info("用户信息:[{}]",user.toString());

        Map<String,Object> map = new HashMap<>();
        //调用业务方法
        try {
            userService.register(user);
            map.put("state",true);
            map.put("msg","注册成功！");

        } catch (Exception e){
            e.printStackTrace();
            map.put("state",false);
            map.put("msg","注册失败!"+e.getMessage());
        }

    return map;
}
}
