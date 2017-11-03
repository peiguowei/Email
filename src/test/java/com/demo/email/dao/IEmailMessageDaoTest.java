package com.demo.email.dao;

import com.demo.email.BaseTest;
import com.demo.email.entity.EmailMessage;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * 邮箱信息 数据库相关测试
 */
public class IEmailMessageDaoTest extends BaseTest{
    @Resource
    private IEmailMessageDao emailMessageDao;
    //保存邮箱信息到数据库测试
    @Test
    public void saveEmailMessageTest(){
        EmailMessage message=new EmailMessage();
        message.setSendEmailId(1);
        message.setCollectEmailId(2);
        message.setTitle("中国人");
        message.setState("WAIT");
        message.setContent("人民万岁");
        boolean emailMessage = emailMessageDao.saveEmailMessage(message);
        Assert.assertTrue("保存邮箱信息到数据库失败",emailMessage);
    }
    @Test
    public void selectEmailByCollectIdTest(){
        List<EmailMessage> emailMessages = emailMessageDao.selectEmailByCollectId(1);
        Assert.assertTrue("根据id查询所有的邮件失败",emailMessages!=null);
    }
}
