package com.demo.email.dao;

import com.demo.email.entity.User;

/**
 *用户的dao层 操作数据库
 */
public interface IUserDao {
    /**
     * 根据用户邮箱名查询用户 2017-1-10
     * @param userEmail 待查询的用户邮箱名
     * @return null不存在
     */
    User selectByUserEmail(String userEmail);

    /**
     * 将数据存储到数据库2017-1-10
     * @param user 待存储的信息 对象
     * @return true 保存成功 false 保存失败
     */
    boolean saveUseEmail(User user);

    /**
     * 通过id查询用户2017-1-10
     * @param id 查询用户条件
     * @return null无此用户
     */
    User selectById(Integer id);
}
