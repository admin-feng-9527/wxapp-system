package com.example.demo.dao;


import com.example.demo.po.UserloginCustom;

/**
 *  UserloginMapper扩展类
 */
public interface UserloginDaoCustom {

    UserloginCustom findOneByName(String name) throws Exception;

}
