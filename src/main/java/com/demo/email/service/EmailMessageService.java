package com.demo.email.service;

import com.demo.email.dao.IEmailMessageDao;
import com.demo.email.dao.IUserDao;
import com.demo.email.entity.EmailMessage;
import com.demo.email.entity.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 邮箱信息业务层
 * @author pgw
 */
@Service
public class EmailMessageService {
    @Resource
    private IEmailMessageDao emailMessage;
    @Resource
    private IUserDao userDao;

    /**
     * 保存写信到数据库 业务
     * @param message 待保存邮箱的对象
     * @return true保存成功 false保存失败
     */
    public boolean saveEmailMessageService(EmailMessage message){
        return emailMessage.saveEmailMessage(message);
    }

    /**
     * 查询登录用户的所有邮件
     * @param req 反馈信息
     * @return null无邮件或者错误 非null所有的邮件
     */
    public List<EmailMessage> seeEmailService(HttpServletRequest req){
        HttpSession session = req.getSession();
        User user =(User)session.getAttribute("user");
        if (user==null){
            req.setAttribute("err","请登录");
            return null;
        }
        //根据登录用户id（收件人）查询它收到的邮件
        return emailMessage.selectEmailByCollectId(user.getId());
    }

    /**
     * 根据id 查询数据库单个邮件信息
     * 注：id 是邮件信息表的主键
     * @param id 查询条件
     * @return null不存在
     */
    public EmailMessage selectOneEmailContent(Integer id){
        EmailMessage message = emailMessage.selectEmailMessageById(id);
        //通过发件人id得到发件人姓名
        User user = userDao.selectById(message.getSendEmailId());
        message.setSendName(user.getEmail());
        //通过收件人id（登录用户）得到收件人姓名
        User userOne = userDao.selectById(message.getCollectEmailId());
        message.setCollectName(userOne.getEmail());
        return message;
    }

}
