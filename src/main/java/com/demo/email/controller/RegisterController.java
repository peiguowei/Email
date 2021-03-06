package com.demo.email.controller;

import com.demo.email.entity.User;
import com.demo.email.service.UserService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 用户注册
 *
 * @author peiguowei
 */
@Controller("userController")
public class RegisterController {
    @Autowired
    private UserService userService;

    /**
     * 用户注册  @RequestParam(required = false)参数可以为空
     *
     * @param email
     * @param password1
     * @param age
     * @param gender
     * @param hobbies
     * @param file
     * @param req
     * @return
     */
    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public String register(String email, String password1, Integer age, Boolean gender,
                           @RequestParam(required = false) String[] hobbies, MultipartFile file, HttpServletRequest req) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password1);
        user.setAge(age);
        user.setGender(gender);
        user.setHobbies(Arrays.toString(hobbies));
        boolean userEmail = userService.registerService(user, req, file);
        if (userEmail) {
            return "content";
        }
        return "register";
    }

    /**
     * 用户登录
     *
     * @param emailName 邮箱名
     * @param password  密码
     * @param req
     * @return
     * @ResponseBody 不归视图管
     */
    @RequestMapping(path = "/login", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public Map login(String emailName, String password, HttpServletRequest req) {
        HashMap<String, Object> result = new HashMap<>();
        User user = new User();
        user.setEmail(emailName);
        user.setPassword(password);
        String userLogin = userService.loginService(user, req);
        result.put("message", userLogin);
        result.put("user", user);
        return result;
    }
}
