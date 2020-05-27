package com.example.demo.controller;


import com.example.demo.po.CourseCustom;
import com.example.demo.po.SelectedCourseCustom;
import com.example.demo.service.CourseService;
import com.example.demo.service.SelectedCourseService;
import com.example.demo.service.TeacherService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/wxteacher")
public class WxTeacherController {
    @Resource(name = "teacherServiceImpl")
    private TeacherService teacherService;

    @Resource(name = "courseServiceImpl")
    private CourseService courseService;

    @Resource(name = "selectedCourseServiceImpl")
    private SelectedCourseService selectedCourseService;

    @RequestMapping(value="/wxshowCourse",method = {RequestMethod.POST,RequestMethod.GET})
    public Map<String,Object> wxshowcourse(String username)throws Exception{
        Map<String,Object> map = new HashMap<String, Object>();
     //   Subject subject = SecurityUtils.getSubject();
     //   String username = (String) subject.getPrincipal();

        List<CourseCustom> list = courseService.findByTeacherID(Integer.parseInt(username));
        map.put("courseList", list);
        return map;
    }
    @RequestMapping(value="/wxgradeCourse",method = {RequestMethod.POST,RequestMethod.GET})
    public Map<String,Object> wxgradecourse(Integer courseid)throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        if (courseid == null) {
            map.put("teachergrade","false");
        }
        List<SelectedCourseCustom> list = selectedCourseService.findByCourseID(courseid);
        map.put("selectedCourseList", list);
        return map;
    }
    @RequestMapping(value="/wxmark1",method = {RequestMethod.POST,RequestMethod.GET})
    public Map<String,Object> wxmark1(SelectedCourseCustom scc)throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        SelectedCourseCustom selectedCourseCustom = selectedCourseService.findOne(scc);

        map.put("selectedCourse", selectedCourseCustom);

        return map;
    }
    @RequestMapping(value="/wxmark2",method = {RequestMethod.POST,RequestMethod.GET})
    public Map<String,Object> wxmark2(SelectedCourseCustom scc)throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        selectedCourseService.updataOne(scc);
        map.put("mark","true");
        return map;
    }

}
