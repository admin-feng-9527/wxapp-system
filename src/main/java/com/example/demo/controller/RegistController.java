package com.example.demo.controller;


import com.example.demo.po.Userlogin;
import com.example.demo.service.UserloginService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class RegistController {

    @Resource(name = "userloginServiceImpl")
    private UserloginService userloginService;

   @RequestMapping(value = "/doRegist")
    public String doRegist(Userlogin userlogin, Model model){
       System.out.println(userlogin.getUsername());
       userloginService.regist(userlogin);
       return "registsuccess";
   }

   @RequestMapping(value = "/wxuserdoRegist",method = {RequestMethod.POST,RequestMethod.GET})
    public Map<String,Object> wxuserdoRegist(Userlogin userlogin) throws Exception{
       Map<String,Object> map = new HashMap<String, Object>();
       userloginService.regist(userlogin);
       map.put("userdoRegist","true");
       return map;
   }
}
