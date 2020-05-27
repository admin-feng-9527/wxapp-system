package com.example.demo.service.impl;


import com.example.demo.dao.CollegeDao;
import com.example.demo.dao.StudentDao;
import com.example.demo.dao.StudentDaoCustom;
import com.example.demo.po.*;
import com.example.demo.service.StudentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Student
 */
@Service
public class StudentServiceImpl implements StudentService {

    //使用spring 自动注入
    @Autowired
    private StudentDaoCustom studentDaoCustom;

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private CollegeDao collegeDao;

    @Override
    public void updataById(Integer id, StudentCustom studentCustom) throws Exception {
        studentDao.updateByPrimaryKey(studentCustom);
    }

    @Override
    public void removeById(Integer id) throws Exception {
        studentDao.deleteByPrimaryKey(id);
    }

    @Override
    public List<StudentCustom> findByPaging(Integer toPageNo) throws Exception {
        PagingVO pagingVO = new PagingVO();
        pagingVO.setToPageNo(toPageNo);

        List<StudentCustom> list = studentDaoCustom.findByPaging(pagingVO);

        return list;
    }

    @Override
    public Boolean save(StudentCustom studentCustoms) throws Exception {
        Student stu = studentDao.selectByPrimaryKey(studentCustoms.getUserid());
        if (stu == null) {
            studentDao.insert(studentCustoms);
            return true;
        }

        return false;
    }

    //返回学生总数
    @Override
    public int getCountStudent() throws Exception {
        //自定义查询对象
        StudentExample studentExample = new StudentExample();
        //通过criteria构造查询条件
        StudentExample.Criteria criteria = studentExample.createCriteria();
        criteria.andUseridIsNotNull();

        return studentDao.countByExample(studentExample);
    }

    @Override
    public StudentCustom findById(Integer id) throws Exception {

        Student student  = studentDao.selectByPrimaryKey(id);
        StudentCustom studentCustom = null;
        if (student != null) {
            studentCustom = new StudentCustom();
            //类拷贝
            BeanUtils.copyProperties(student, studentCustom);
        }

        return studentCustom;
    }

    //模糊查询
    @Override
    public List<StudentCustom> findByName(String name) throws Exception {

        StudentExample studentExample = new StudentExample();
        //自定义查询条件
        StudentExample.Criteria criteria = studentExample.createCriteria();

        criteria.andUsernameLike("%" + name + "%");

        List<Student> list = studentDao.selectByExample(studentExample);

        List<StudentCustom> studentCustomList = null;

        if (list != null) {
            studentCustomList = new ArrayList<StudentCustom>();
            for (Student s : list) {
                StudentCustom studentCustom = new StudentCustom();
                //类拷贝
                BeanUtils.copyProperties(s, studentCustom);
                //获取课程名
                College college = collegeDao.selectByPrimaryKey(s.getCollegeid());
                studentCustom.setcollegeName(college.getCollegename());

                studentCustomList.add(studentCustom);
            }
        }

        return studentCustomList;
    }

    @Override
    public StudentCustom findStudentAndSelectCourseListByName(String name) throws Exception {

        StudentCustom studentCustom = studentDaoCustom.findStudentAndSelectCourseListById(Integer.parseInt(name));

        List<SelectedCourseCustom> list = studentCustom.getSelectedCourseList();

        // 判断该课程是否修完
        for (SelectedCourseCustom s : list) {
            if (s.getMark() != null) {
                s.setOver(true);
            }
        }
        return studentCustom;
    }

    @Override
    public List<StudentCustom> findAll() throws Exception {
        StudentExample studentExample = new StudentExample();
        StudentExample.Criteria criteria = studentExample.createCriteria();

        criteria.andUseridIsNotNull();

        List<Student> list = studentDao.selectByExample(studentExample);
        List<StudentCustom> studentCustomsList = null;
        if (list != null) {
            studentCustomsList = new ArrayList<StudentCustom>();
            for (Student t: list) {
                StudentCustom studentCustom = new StudentCustom();
                BeanUtils.copyProperties(t, studentCustom);
                studentCustomsList.add(studentCustom);
            }
        }
        return studentCustomsList;
    }
}
