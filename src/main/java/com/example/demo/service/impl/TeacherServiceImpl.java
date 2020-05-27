package com.example.demo.service.impl;


import com.example.demo.dao.CollegeDao;
import com.example.demo.dao.CourseDao;
import com.example.demo.dao.TeacherDao;
import com.example.demo.dao.TeacherDaoCustom;
import com.example.demo.po.*;
import com.example.demo.service.TeacherService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherDao teacherDao;

    @Autowired
    private TeacherDaoCustom teacherDaoCustom;

    @Autowired
    private CollegeDao collegeDao;

    @Autowired
    private CourseDao courseDao;

    @Override
    public void updateById(Integer id, TeacherCustom teacherCustom) throws Exception {
        teacherDao.updateByPrimaryKey(teacherCustom);
    }

    @Override
    public void removeById(Integer id) throws Exception {
        CourseExample courseExample = new CourseExample();

        CourseExample.Criteria criteria = courseExample.createCriteria();
        criteria.andTeacheridEqualTo(id);
//        List<Course> list = courseMapper.selectByExample(courseExample);

//        if (list.size() != 0) {
////            throw new CustomException("请先删除该名老师所教授的课程");
////        }

        teacherDao.deleteByPrimaryKey(id);
    }

    @Override
    public List<TeacherCustom> findByPaging(Integer toPageNo) throws Exception {
        PagingVO pagingVO = new PagingVO();
        pagingVO.setToPageNo(toPageNo);

        List<TeacherCustom> list = teacherDaoCustom.findByPaging(pagingVO);

        return list;
    }

    @Override
    public Boolean save(TeacherCustom teacherCustom) throws Exception {

        Teacher tea = teacherDao.selectByPrimaryKey(teacherCustom.getUserid());
        if (tea == null) {
            teacherDao.insert(teacherCustom);
            return true;
        }
        return false;
    }

    @Override
    public int getCountTeacher() throws Exception {
        //自定义查询对象
        TeacherExample teacherExample = new TeacherExample();
        //通过criteria构造查询条件
        TeacherExample.Criteria criteria = teacherExample.createCriteria();
        criteria.andUseridIsNotNull();

        return teacherDao.countByExample(teacherExample);
    }

    @Override
    public TeacherCustom findById(Integer id) throws Exception {
        Teacher teacher = teacherDao.selectByPrimaryKey(id);
        TeacherCustom teacherCustom = null;
        if (teacher != null) {
            teacherCustom = new TeacherCustom();
            BeanUtils.copyProperties(teacher, teacherCustom);
        }

        return teacherCustom;
    }

    @Override
    public List<TeacherCustom> findByName(String name) throws Exception {
        TeacherExample teacherExample = new TeacherExample();
        //自定义查询条件
        TeacherExample.Criteria criteria = teacherExample.createCriteria();

        criteria.andUsernameLike("%" + name + "%");

        List<Teacher> list = teacherDao.selectByExample(teacherExample);

        List<TeacherCustom> teacherCustomList = null;

        if (list != null) {
            teacherCustomList = new ArrayList<TeacherCustom>();
            for (Teacher t : list) {
                TeacherCustom teacherCustom = new TeacherCustom();
                //类拷贝
                BeanUtils.copyProperties(t, teacherCustom);
                //获取课程名
                College college = collegeDao.selectByPrimaryKey(t.getCollegeid());
                teacherCustom.setcollegeName(college.getCollegename());

                teacherCustomList.add(teacherCustom);
            }
        }

        return teacherCustomList;
    }

    @Override
    public List<TeacherCustom> findAll() throws Exception {

        TeacherExample teacherExample = new TeacherExample();
        TeacherExample.Criteria criteria = teacherExample.createCriteria();

        criteria.andUseridIsNotNull();

        List<Teacher> list = teacherDao.selectByExample(teacherExample);
        List<TeacherCustom> teacherCustomsList = null;
        if (list != null) {
            teacherCustomsList = new ArrayList<TeacherCustom>();
            for (Teacher t: list) {
                TeacherCustom teacherCustom = new TeacherCustom();
                BeanUtils.copyProperties(t, teacherCustom);
                teacherCustomsList.add(teacherCustom);
            }
        }
        return teacherCustomsList;
    }
}
