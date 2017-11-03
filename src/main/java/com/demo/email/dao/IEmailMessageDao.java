package com.demo.email.dao;


import com.demo.email.entity.EmailMessage;

import java.util.List;

/**
 * 邮箱dao层 与数据库交互
 */
public interface IEmailMessageDao {
    /**
     * 保存邮箱信息到数据库
     * @param message 待保存信息的对象
     * @return true保存成功 false保存失败
     */
    boolean saveEmailMessage(EmailMessage message);

    /**
     * 根据登录用户的id查询个人所有的信件
     * @param loginId 登录用户id
     * @return null无信件
     */
    List<EmailMessage> selectEmailByCollectId(Integer loginId);

    /**
     * 根据id 单个邮件的内容
     * 注：这里的id是主键
     * @param id 查询条件
     * @return null不存在
     */
    EmailMessage selectEmailMessageById(Integer id);

}
