package com.example.demo.service.impl;


import com.example.demo.dao.UserloginDao;
import com.example.demo.po.Userlogin;
import com.example.demo.po.UserloginExample;
import com.example.demo.service.UserloginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserloginServiceImpl implements UserloginService {

    @Autowired
    private UserloginDao userloginDao;


    @Override
    public Userlogin findByName(String name) throws Exception {
        UserloginExample userloginExample = new UserloginExample();

        UserloginExample.Criteria criteria = userloginExample.createCriteria();
        criteria.andUsernameEqualTo(name);

        List<Userlogin> list = userloginDao.selectByExample(userloginExample);

        return list.get(0);
    }

    @Override
    public void save(Userlogin userlogin) throws Exception {
        userloginDao.insert(userlogin);
    }

    @Override
    public void removeByName(String name) throws Exception {
        UserloginExample userloginExample = new UserloginExample();

        UserloginExample.Criteria criteria = userloginExample.createCriteria();
        criteria.andUsernameEqualTo(name);

        userloginDao.deleteByExample(userloginExample);
    }


    @Override
    public void updateByName(String name, Userlogin userlogin) {
        UserloginExample userloginExample = new UserloginExample();

        UserloginExample.Criteria criteria = userloginExample.createCriteria();
        criteria.andUsernameEqualTo(name);

        userloginDao.updateByExample(userlogin, userloginExample);
    }

    @Override
    public void regist(Userlogin userlogin) {
        userloginDao.regist(userlogin.getUsername(),userlogin.getPassword());
    }

}
