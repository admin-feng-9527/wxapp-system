package com.example.demo.controller;


import com.example.demo.exception.CustomException;
import com.example.demo.po.Userlogin;
import com.example.demo.service.UserloginService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;


@RestController
public class RestPasswordController {

    @Resource(name = "userloginServiceImpl")
    private UserloginService userloginService;

    // 本账户密码重置
    @RequestMapping(value = "/passwordRest", method = {RequestMethod.POST})
    public String passwordRest(String oldPassword, String password1) throws Exception {
        Subject subject = SecurityUtils.getSubject();
        //获取登录的存储对象
        String username = (String) subject.getPrincipal();

        Userlogin userlogin = userloginService.findByName(username);

        if (!oldPassword.equals(userlogin.getPassword())) {
            throw new CustomException("旧密码不正确");
        } else {
            userlogin.setPassword(password1);
            userloginService.updateByName(username, userlogin);
        }

        return "redirect:/logout";
    }

    //微信密码重置
    @RequestMapping(value = "/wxpasswordRset",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Map<String,Object> wxpasswordRset(String username,String oldPassword,String password) throws Exception{
        Map<String,Object> map = new HashMap<String, Object>();
        Userlogin userlogin = userloginService.findByName(username);
        System.out.println(userlogin.getUsername());
        if (!oldPassword.equals(userlogin.getPassword())) {
            map.put("password","oldexception");
        } else {
            userlogin.setPassword(password);
            userloginService.updateByName(username, userlogin);
            map.put("password","true");
        }
        return map;
    }

}
