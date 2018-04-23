package com.demo.email.controller;

import com.demo.email.entity.EmailMessage;
import com.demo.email.entity.User;
import com.demo.email.service.EmailMessageService;
import com.demo.email.service.UserService;
import com.demo.email.type.EmailState;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 邮箱信息控制层
 */
@Controller("emailController")
public class EmailMessageController {
    @Resource
    private EmailMessageService messageService;
    @Resource
    private UserService userService;

    //处理保存草稿
    @RequestMapping(path = "/saveDraft", method = RequestMethod.POST, produces = "Application/json;charset=utf-8")
    @ResponseBody
    public String saveDraft(String recipients, Integer sendEmailId, String title, String content, HttpServletRequest req) {
        if (sendEmailId == null) {
            return "请登录邮箱";
        }
        String newName = UUID.randomUUID() + title;//UUID+主题名称
        //得到项目路径
        ServletContext servletContext = req.getSession().getServletContext();
        //得到保存地址（保存的文件夹）
        String path = servletContext.getRealPath("/emailText");
        File file = new File(path);
        if (!file.exists()) {//不存在
            file.mkdirs();//创建目录
        }
        //file.getPath()得到路径System.out.println(file.getPath());
        File emailName = new File(file.getPath() + "/" + newName + ".html");
        try {
            emailName.createNewFile();//创建后缀为html的文件
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("创建文件失败");
        }
        try {
            BufferedReader reader = new BufferedReader(new StringReader(content));
            OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(emailName), "UTF-8");
            BufferedWriter writer = new BufferedWriter(out);
           /* writer.write("<html lang=\"en\">");
            writer.write("<head>");
            writer.write("<meta charset=\"UTF-8\">");
            writer.write("</head>");
            writer.write("<body>");*/
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                writer.write(line);
            }
          /*  writer.write("</body>");
            writer.write("</html>");*/
            writer.flush();
            writer.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("缓存文件失败");
        }


        EmailMessage message = new EmailMessage();
        message.setSendEmailId(sendEmailId);
        message.setTitle(title);
        message.setContent(newName + ".html");
        message.setState(EmailState.WAIT.toString());
        User user = userService.emailSelect(recipients);
        if (user == null) {
            return "收件人不存在";
        }
        message.setCollectEmailId(user.getId());
        boolean temp = messageService.saveEmailMessageService(message);
        if (temp) {
            return "success";
        }
        return "其他原因致保存草稿失败，请稍后再试";
    }

    //处理登录
    @RequestMapping(path = "/content.html")
    public String contentJsp() {
        return "content";
    }

    //处理查询所有邮件
    @RequestMapping(path = "/seeEmail", method = RequestMethod.GET)
    public String seeEmail(HttpServletRequest req) {
        List<EmailMessage> emailMessages = messageService.seeEmailService(req);
        req.setAttribute("emailList", emailMessages);
        return "showEmail";
    }

    //处理查询用户名
    @RequestMapping("/queryUserByName")
    @ResponseBody
    public String getUserName(Integer id) {
        User user = userService.queryUserById(id);
        if (user == null) {
            return "未知的用户";
        }
        return user.getEmail();
    }

    //处理保存发送邮件
    @RequestMapping(path = "/saveEmail", method = RequestMethod.POST, produces = "Application/json;charset=utf-8")
    @ResponseBody
    public String saveEmail(String recipients, Integer sendEmailId, String title, String content, HttpServletRequest req) {
        if (sendEmailId == null) {
            return "请登录邮箱";
        }
        //UUID+主题名称
        String newName = UUID.randomUUID() + title;
        //得到项目路径
        ServletContext servletContext = req.getSession().getServletContext();
        //得到邮件内容的保存文件夹
        String path = servletContext.getRealPath("/emailText");
        File file = new File(path);
        if (!file.exists()) {//目录不存在
            file.mkdirs();//创建目录
        }
        File emailName = new File(file.getPath() + "/" + newName + ".html");
        try {
            emailName.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("创建以html为后缀的文件失败");
        }

        try {
            BufferedReader reader = new BufferedReader(new StringReader(content));
            OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(emailName), "UTF-8");
            BufferedWriter writer = new BufferedWriter(out);
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                writer.write(line);
            }
            writer.flush();
            writer.close();
            reader.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        User user = userService.emailSelect(recipients);
        if (user == null) {
            return "收件人不存在";
        }
        EmailMessage message = new EmailMessage();
        message.setCollectEmailId(user.getId());//收件人id
        message.setSendEmailId(sendEmailId);
        message.setTitle(title);
        message.setContent(newName + ".html");

        message.setState(EmailState.SUCCESS.toString());
        Date date = new Date();
      /*  Format format=new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss");
        String s = format.format(date);*/
        message.setSendDate(date);
        boolean b = messageService.saveEmailMessageService(message);
        return "success";
    }

    //处理查看单个信件内容(根据id查看)
    @RequestMapping(path = "/emailMessageContent", method = RequestMethod.GET)
    public String emailMessageContent(Integer id, HttpServletRequest req) {
        EmailMessage message = messageService.selectOneEmailContent(id);
        req.setAttribute("emailMessageContent", message);
        return "showOneMessage";
    }
}
