package net.boblog.app.config;

import net.boblog.app.external.SmsSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * Created by dave on 16/5/19.
 */
@Configuration
public class AppConfig {
    @Autowired Environment env;

    @Bean
    public SmsSender getSmsSender() {
        return new SmsSender(env.getProperty("app.sms.appKey"),
                            env.getProperty("app.sms.appSecret"),
                            env.getProperty("app.sms.templateId"),
                            env.getProperty("app.sms.signature"));
    }

    @Bean
    public JavaMailSender getMailSender() {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setHost(env.getProperty("app.mail.host"));
        sender.setPort(Integer.valueOf(env.getProperty("app.mail.port")));
        sender.setUsername(env.getProperty("app.mail.username"));
        sender.setPassword(env.getProperty("app.mail.password"));
        return sender;
    }
}
