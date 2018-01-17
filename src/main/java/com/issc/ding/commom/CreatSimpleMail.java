package com.issc.ding.commom;

import com.issc.ding.util.Propertiesmail;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.util.Properties;

public class CreatSimpleMail {
    public static MimeMessage createSimpleMail(Session session, String title, String content, Message.RecipientType type)
            throws Exception {
        //创建MIME邮件对象
        MimeMessage message = new MimeMessage(session);

        Properties pro= Propertiesmail.getProperties();
        String addressee=pro.getProperty("addressee");//收件人
        String addresser=pro.getProperty("addresser");//发件人
        //指定发件人昵称
        String nick = MimeUtility.encodeText("笑话你懂得", "utf-8", "B");//避免乱码

        //指明邮件的发件人
        message.setFrom(new InternetAddress(nick + "<"+addresser+">"));

        //指明邮件的收件人
        //message.addRecipient(type, new InternetAddress("flower.monk@foxmail.com"));
        message.addRecipient(type, new InternetAddress(addressee+"@139.com"));

        //邮件主题
        message.setSubject(title);

        //邮件的文本内容，加入一些符号让结尾美美哒(^.^)(^.^)
        message.setContent(content + "(^.^)(^.^)晚安!", "text/html;charset=UTF-8");

        //返回创建好的邮件对象
        return message;
    }
}
