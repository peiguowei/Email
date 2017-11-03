package com.demo.email.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 绑定在class的RequestMapping的注解
 * 代表该class下所有的RequestMapping
 * 都会加一个虚拟路径
 */
/*@Controller
@RequestMapping("/ajax")*/
public class test {
    //真正的服务地址就变成了是/ajax/register
   /* @RequestMapping("/register")*/
}
