package com.example.demo.dao;


import com.example.demo.po.CourseCustom;
import com.example.demo.po.PagingVO;

import java.util.List;

public interface CourseDaoCustom {

    //分页查询学生信息
    List<CourseCustom> findByPaging(PagingVO pagingVO) throws Exception;

}
