package com.example.demo.dao;


import com.example.demo.po.Userlogin;
import com.example.demo.po.UserloginExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserloginDao {
    int countByExample(UserloginExample example);

    int deleteByExample(UserloginExample example);

    int deleteByPrimaryKey(Integer userid);

    int insert(Userlogin record);

    int insertSelective(Userlogin record);

    List<Userlogin> selectByExample(UserloginExample example);

    Userlogin selectByPrimaryKey(Integer userid);

    int updateByExampleSelective(@Param("record") Userlogin record, @Param("example") UserloginExample example);

    int updateByExample(@Param("record") Userlogin record, @Param("example") UserloginExample example);

    int updateByPrimaryKeySelective(Userlogin record);

    int updateByPrimaryKey(Userlogin record);

    void regist(@Param("username") String username, @Param("password") String password);


}