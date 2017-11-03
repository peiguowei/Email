package com.demo.email;

import org.apache.ibatis.javassist.ClassPath;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 *junit 测试
 */
@Transactional("tmBss")//引入数据库事物管理
@ContextConfiguration(locations ="classpath:spring.xml")//指定上下文文件地址
@RunWith(SpringJUnit4ClassRunner.class)//整合
public class BaseTest {
}
