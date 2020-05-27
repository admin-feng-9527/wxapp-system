package com.example.demo.controller;


import com.example.demo.po.CourseCustom;
import com.example.demo.po.SelectedCourseCustom;
import com.example.demo.po.StudentCustom;
import com.example.demo.service.CourseService;
import com.example.demo.service.SelectedCourseService;
import com.example.demo.service.StudentService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/wxstudent")
public class WxStudentController {
    @Resource(name = "courseServiceImpl")
    private CourseService courseService;

    @Resource(name = "studentServiceImpl")
    private StudentService studentService;

    @Resource(name = "selectedCourseServiceImpl")
    private SelectedCourseService selectedCourseService;

    @RequestMapping(value="/wxshowCourse",method = {RequestMethod.POST,RequestMethod.GET})
    public Map<String,Object> wxshowcourse()throws Exception{
        Map<String,Object> map = new HashMap<String, Object>();
        //   Subject subject = SecurityUtils.getSubject();
        //   String username = (String) subject.getPrincipal();

        List<CourseCustom> list = courseService.findAll();
        map.put("courseList", list);
        return map;
    }
    @RequestMapping(value = "/stuSelectedCourse",method = {RequestMethod.POST,RequestMethod.GET})
    public Map<String,Object> stuSelectedCourse(String username,Integer courseid)throws Exception{
        Map<String,Object> map = new HashMap<String,Object>();
        SelectedCourseCustom selectedCourseCustom = new SelectedCourseCustom();
        selectedCourseCustom.setCourseid(courseid);
        selectedCourseCustom.setStudentid(Integer.parseInt(username));

        SelectedCourseCustom s = selectedCourseService.findOne(selectedCourseCustom);

        if (s == null) {
            selectedCourseService.save(selectedCourseCustom);
            map.put("stuSelectedCourse","success");
        } else {
            map.put("stuSelectedCourse","existed");
        }

        return map;
    }
    @RequestMapping(value = "/selectedCourse",method = {RequestMethod.POST,RequestMethod.GET})
    public Map<String,Object> selectedCourse(String username) throws Exception{
        Map<String,Object> map = new HashMap<String, Object>();
        StudentCustom studentCustom = studentService.findStudentAndSelectCourseListByName(username);

        List<SelectedCourseCustom> list = studentCustom.getSelectedCourseList();

        map.put("selectedCourseList", list);

        return map;
    }
    @RequestMapping(value = "/outCourse",method = {RequestMethod.POST,RequestMethod.GET})
    public Map<String,Object> outCourse(String username,Integer courseid) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        SelectedCourseCustom selectedCourseCustom = new SelectedCourseCustom();
        selectedCourseCustom.setCourseid(courseid);
        selectedCourseCustom.setStudentid(Integer.parseInt(username));

        selectedCourseService.remove(selectedCourseCustom);
        map.put("sturemovecourse","success");

        return map;
    }
    @RequestMapping(value = "/overCourse",method = {RequestMethod.POST,RequestMethod.GET})
    public Map<String,Object> overCourse(String username) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        StudentCustom studentCustom = studentService.findStudentAndSelectCourseListByName(username);

        List<SelectedCourseCustom> list = studentCustom.getSelectedCourseList();

        map.put("selectedCourseList", list);

        return map;
    }
}
