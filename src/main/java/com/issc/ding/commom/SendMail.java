package com.issc.ding.commom;

import com.issc.ding.util.Propertiesmail;

import javax.mail.*;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class SendMail {
    public static void sendMail(String jsonstr) throws IOException {
        Properties pro= Propertiesmail.getProperties();
        String addresser=pro.getProperty("addresser");//发件人地址
        String password=pro.getProperty("password");//发件人地址
        //注：建议使用主流邮箱，我曾遇到用sohu发出邮件丢失的情况，
        //不仅仅是在程序这里，包括在sohu邮箱客户端测试也发生了邮件丢失
        Properties prop = new Properties();
        prop.setProperty("mail.host", "smtp.qq.com");
        prop.setProperty("mail.transport.protocol", "smtp");
        prop.setProperty("mail.smtp.auth", "true");

        //使用STARTTLS,对于其它协议（如pop3，imap），只需要将smtp改为相应协议即可（pop3要改为pop）
        // 若要使用SSL，只需要设置mail.smtp.ssl.enable为true
        prop.put("mail.smtp.starttls.enable", "true");
        //使用JavaMail发送邮件的5个步骤
        //1、创建session
        Session session = Session.getInstance(prop);
        //开启Session的debug模式，便于看到发送Email的运行状态
        session.setDebug(true);
        //2、通过session得到transport对象
        Transport ts = null;
        try {
            ts = session.getTransport();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        }
        //3、发件邮箱的帐号和密码。
        try {
            if (ts != null) {
                ts.connect(addresser, password);
            }
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        //4、创建邮件（Java用Message对象封装（代表）邮件），其中，最后一个参数含义：
        //（收件人<-->RecipientType.TO，抄送<-->RecipientType.CC，密送<-->RecipientType.BBC）
        Message message = null;
        try {
            message = CreatSimpleMail.createSimpleMail(session, "nice dream", jsonstr, Message.RecipientType.TO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //5、发送邮件
        try {
            if (ts != null) {
                ts.sendMessage(message, Objects.requireNonNull(message).getAllRecipients());
            }
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        try {
            if (ts != null) {
                ts.close();
            }
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
