package net.boblog.test.component;

import net.boblog.test.api.MvcTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * Created by dave on 16/5/20.
 */
public class JavaMailTest extends MvcTest {
    @Autowired JavaMailSender mailSender;
    @Value("${app.mail.username}") String username;

    @Test
    public void sendMail() throws MessagingException {
        MimeMessage msg = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg, true, "UTF-8");
        helper.setFrom(username);
        helper.setTo(username);
        helper.setSubject("测试邮件");
        helper.setText("<h3>只是一个测试邮件!</h3>");
        mailSender.send(msg);
    }
}
