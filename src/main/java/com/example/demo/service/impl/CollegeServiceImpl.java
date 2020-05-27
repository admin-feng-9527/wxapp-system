package com.example.demo.service.impl;



import com.example.demo.mapper.CollegeMapper;
import com.example.demo.po.College;
import com.example.demo.po.CollegeExample;
import com.example.demo.service.CollegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollegeServiceImpl implements CollegeService {

    @Autowired
    private CollegeMapper collegeMapper;
    @Override
    public List<College> finAll() throws Exception {
        CollegeExample collegeExample = new CollegeExample();
        CollegeExample.Criteria criteria = collegeExample.createCriteria();
        criteria.andCollegeidIsNotNull();
        return collegeMapper.selectByExample(collegeExample);
    }
}
