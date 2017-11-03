package com.demo.email.service;

import com.demo.email.dao.IUserDao;
import com.demo.email.entity.User;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * 用户注册 业务层
 */
@Service
public class UserService {
    @Resource
    private IUserDao userDao;

    /**
     * 用户注册业务 将用户信息存入数据库
     * @param user 待存储的用户信息
     * @param req 反馈信息
     * @return true 存储成功 false存储失败
     */
    public boolean registerService(User user, HttpServletRequest req, MultipartFile file){
       //验证用户是否已存在 根据用户邮箱名查询
        User userEmail = userDao.selectByUserEmail(user.getEmail());
        if (userEmail!=null){
            req.setAttribute("error","用户名已存在");
            return false;
        }
        //文件上传相关
        //得到上传的文件
        String uploadName = file.getOriginalFilename();
        //新的文件名
        String newUploadName= UUID.randomUUID()+uploadName;
        //获取项目路径
        ServletContext servletContext = req.getSession().getServletContext();
        //得到保存的路径
        String path = servletContext.getRealPath("/newImg" + "/");
        //读取文件
        File f=new File(path);
        //判断文件是否存在
        if (!f.exists()){
            f.mkdirs();//不存在创建文件目录
        }
        if (!file.isEmpty()){
            try {
                FileUtils.copyInputStreamToFile(file.getInputStream(),new File(path,newUploadName));
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("上传文件失败");
            }
        }
        user.setImg(newUploadName);
        //存储到数据库
        return userDao.saveUseEmail(user);

    }

    /**
     * 用户登录 验证
     * @param user 待验证的用户及密码
     * @param req 得到会话变量
     * @return 返回的信息
     */
    public String loginService(User user,HttpServletRequest req){
        User userEmail = userDao.selectByUserEmail(user.getEmail());
        if (userEmail==null){//用户不存在
            return "邮箱不存在";
        }
        if (!userEmail.getPassword().equals(user.getPassword())){//密码不正确
            return "输入的密码不正确";
        }
        //得到会话变量 保存用户信息
        HttpSession session = req.getSession();
        //保护密码 防止泄露
        userEmail.setPassword("");
        session.setAttribute("user",userEmail);
        user.setId(userEmail.getId());
        return "success";
    }

    /**
     * 根据输入的收件人名查询数据库
     * @param collectEmailName 待查询的收件人
     * @return null无此用户
     */
    public User emailSelect(String collectEmailName){
        User user = userDao.selectByUserEmail(collectEmailName);
        if (user==null){
            return null;
        }
        return user;
    }

    /**
     * 通过id查询数据库用户
     * @param id 查询条件
     * @return null不存在
     */
    public User queryUserById(Integer id){
        User user = userDao.selectById(id);
        if (user==null){//用户不存在
            return null;
        }
        return user;

    }
}
