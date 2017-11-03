package com.demo.email.dao;

import com.demo.email.BaseTest;
import com.demo.email.entity.User;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * 用户注册测试
 */
public class IUserDaoTest extends BaseTest{
    @Resource
    private IUserDao userDao;
    @Test//根据用户名查询数据库测试
    public void selectByUserEmailTest(){
        String s="12345";
        User user = userDao.selectByUserEmail(s);
        Assert.assertFalse("根据用户名查询数据库失败",user!=null);
        s="1187678230@qq.com";
        User userOne = userDao.selectByUserEmail(s);
        Assert.assertTrue("根据用户名查询数据库失败",userOne!=null);
    }
    @Test//保存用户信息到数据库测试
    public void saveUseEmailTest(){
        User user=new User();
        user.setEmail("1@qq.com");
        user.setImg("aaaff");
        user.setPassword("12345");
        user.setHobbies("look");
        user.setGender(true);
        user.setAge(18);
        boolean save = userDao.saveUseEmail(user);
        Assert.assertTrue("存储数据到数据库失败",save);
    }
    @Test//通过id查询用户测试
    public void selectByIdTest(){
        User user = userDao.selectById(3);
        Assert.assertTrue("通过id查询用户失败",user!=null);
    }
}
