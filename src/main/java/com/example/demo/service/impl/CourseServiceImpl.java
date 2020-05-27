package com.example.demo.service.impl;



import com.example.demo.mapper.CollegeMapper;
import com.example.demo.mapper.CourseMapper;
import com.example.demo.mapper.CourseMapperCustom;
import com.example.demo.mapper.SelectedcourseMapper;
import com.example.demo.po.*;
import com.example.demo.service.CourseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseDao;

    @Autowired
    private CourseMapperCustom courseDaoCustom;

    @Autowired
    private CollegeMapper collegeDao;

    @Autowired
    private SelectedcourseMapper selectedcourseDao;

    @Override
    public void updateById(Integer id, CourseCustom courseCustom) throws Exception {
        courseDao.updateByPrimaryKey(courseCustom);
    }

    @Override
    public void removeById(Integer id) throws Exception {
        //自定义查询条件
//        SelectedcourseExample example = new SelectedcourseExample();
//        SelectedcourseExample.Criteria criteria = example.createCriteria();
//        criteria.andCourseidEqualTo(id);
//        List<Selectedcourse> list = selectedcourseMapper.selectByExample(example);
//
//        if (list.size() == 0) {
//            courseMapper.deleteByPrimaryKey(id);
//            return true;
//        }
//
//        return false;
        courseDao.deleteByPrimaryKey(id);
    }

    @Override
    public List<CourseCustom> findByPaging(Integer toPageNo) throws Exception {
        PagingVO pagingVO = new PagingVO();
        pagingVO.setToPageNo(toPageNo);

        List<CourseCustom> list = courseDaoCustom.findByPaging(pagingVO);
        return list;
    }

    @Override
    public Boolean save(CourseCustom courseCustom) {
        Course course = courseDao.selectByPrimaryKey(courseCustom.getCourseid());
        if (course == null) {
            courseDao.insert(courseCustom);
            return true;
        }
        return false;
    }

    @Override
    public int getCountCourse() throws Exception {
        //自定义查询对象
        CourseExample courseExample = new CourseExample();
        //通过criteria构造查询条件
        CourseExample.Criteria criteria = courseExample.createCriteria();
        criteria.andCoursenameIsNotNull();

        return courseDao.countByExample(courseExample);
    }

    @Override
    public CourseCustom findById(Integer id) throws Exception {
        Course course = courseDao.selectByPrimaryKey(id);
        CourseCustom courseCustom = null;
        if (course != null) {
            courseCustom = new CourseCustom();
            BeanUtils.copyProperties(courseCustom, course);
        }

        return courseCustom;
    }

    @Override
    public List<CourseCustom> findByName(String name) throws Exception {
        CourseExample courseExample = new CourseExample();
        //自定义查询条件
        CourseExample.Criteria criteria = courseExample.createCriteria();

        criteria.andCoursenameLike("%" + name + "%");

        List<Course> list = courseDao.selectByExample(courseExample);

        List<CourseCustom> courseCustomList = null;

        if (list != null) {
            courseCustomList = new ArrayList<CourseCustom>();
            for (Course c : list) {
                CourseCustom courseCustom = new CourseCustom();
                //类拷贝
                org.springframework.beans.BeanUtils.copyProperties(c, courseCustom);
                //获取课程名
                College college = collegeDao.selectByPrimaryKey(c.getCollegeid());
                courseCustom.setcollegeName(college.getCollegename());

                courseCustomList.add(courseCustom);
            }
        }

        return courseCustomList;
    }

    @Override
    public List<CourseCustom> findByTeacherID(Integer id) throws Exception {
        CourseExample courseExample = new CourseExample();
        //自定义查询条件
        CourseExample.Criteria criteria = courseExample.createCriteria();
        //根据教师id查课程
        criteria.andTeacheridEqualTo(id);

        List<Course> list = courseDao.selectByExample(courseExample);
        List<CourseCustom> courseCustomList = null;

        if (list.size() > 0) {
            courseCustomList = new ArrayList<CourseCustom>();
            for (Course c : list) {
                CourseCustom courseCustom = new CourseCustom();
                //类拷贝
                BeanUtils.copyProperties(courseCustom, c);
                //获取课程名
                College college = collegeDao.selectByPrimaryKey(c.getCollegeid());
                courseCustom.setcollegeName(college.getCollegename());

                courseCustomList.add(courseCustom);
            }
        }

        return courseCustomList;
    }

    @Override
    public List<CourseCustom> findAll() throws Exception {
        CourseExample courseExample = new CourseExample();
        CourseExample.Criteria criteria = courseExample.createCriteria();

        criteria.andCourseidIsNotNull();

        List<Course> list = courseDao.selectByExample(courseExample);
        List<CourseCustom> courseCustomsList = null;
        if (list != null) {
            courseCustomsList = new ArrayList<CourseCustom>();
            for (Course t: list) {
                CourseCustom courseCustom = new CourseCustom();
                org.springframework.beans.BeanUtils.copyProperties(t, courseCustom);
                courseCustomsList.add(courseCustom);
            }
        }
        return courseCustomsList;
    }
}
