package com.www.platform.util;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

/**
 * Created by upsmart on 17-8-28.
 *
 * @author wss
 * @version 0.0
 * @desc
 * @modified by  上午9:36
 */
public class MailUtil {
    protected static String myEmail="shaosen_blank@163.com";
    protected static String pass="99AZXLMN";
    protected static String smtpHost="smtp.163.com";
    protected static String toEmail="1467319877@qq.com";
//    protected static String toEmail="shaosen_blank@163.com";
    public static void main(String[] args) throws Exception{
        //创建一封邮件
        Properties prop = new Properties();
        prop.setProperty("mail.debug","true");
        prop.setProperty("mail.smtp.auth","true");
        prop.setProperty("mail.host",smtpHost);
        prop.setProperty("mail.transport.protocol","smtp");
        Session session = Session.getDefaultInstance(prop);
        session.setDebug(true);
        MimeMessage message =createMimeMessage(session,myEmail,toEmail);
        Transport transport =session.getTransport();

        transport.connect(myEmail,pass);
        transport.sendMessage(message,message.getAllRecipients());

        //
        transport.close();
    }

    /**
     *
     * @param session
     * @param sendMail
     * @param receiveMail
     * @return
     */
        public static MimeMessage createMimeMessage(Session session,String sendMail,String receiveMail) throws Exception{
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(sendMail,"小栗子","UTF-8"));
            message.setRecipient(MimeMessage.RecipientType.TO,new InternetAddress(receiveMail,"wss","utf-8"));
            message.setSubject("情书","utf-8");
            message.setContent("你知道吗？我昨晚又梦到你了，梦中的你一如既往地帅气，你背对着我，坐在那家我们常去的咖啡馆常坐的位置，我进门径直朝着那个位置走去，却看到了你，我就愣在那儿停顿了好久，然后你转过头来看到了我，你朝我笑，我鼓起勇气试着向你走近，却始终走不到那个位置，眼睁睁地看着你近在咫尺，却偏偏难以靠近，最后直到你消失不见。","text/html;charset=utf-8");
            message.setSentDate(new Date());
            message.saveChanges();

            return message;
        }


}
