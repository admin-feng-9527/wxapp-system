package com.example.demo.dao;

import com.example.demo.po.PagingVO;
import com.example.demo.po.TeacherCustom;

import java.util.List;

public interface TeacherDaoCustom {

    //分页查询老师信息
    List<TeacherCustom> findByPaging(PagingVO var1) throws Exception;

}
