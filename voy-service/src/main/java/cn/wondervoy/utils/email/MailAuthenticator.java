package cn.wondervoy.utils.email;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import java.util.Properties;

/**
 * Created by ckzhang on 14-12-21.
 */
@Component("MailAuthenticator")
public class MailAuthenticator extends Authenticator {

    @Value("${email.userName}")
    private String userName=null;

    @Value("${email.password}")
    private String password=null;

    // 发送邮件的服务器的IP和端口
    @Value("${email.host}")
    private String mailHost;

    @Value("${email.port}")
    private String mailPort = "25";

    // 邮件发送者的地址
    @Value("${email.signedName}")
    private String signedName;

    public Properties getProperties(){
        Properties p = new Properties();
        p.put("mail.smtp.host", this.mailHost);
        p.put("mail.smtp.port", this.mailPort);
        p.put("mail.smtp.auth", "true");
        return p;
    }

    protected PasswordAuthentication getPasswordAuthentication(){
        return new PasswordAuthentication(userName, password);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMailHost() {
        return mailHost;
    }

    public void setMailHost(String mailHost) {
        this.mailHost = mailHost;
    }

    public String getMailPort() {
        return mailPort;
    }

    public void setMailPort(String mailPort) {
        this.mailPort = mailPort;
    }

    public String getSignedName() {
        return signedName;
    }

    public void setSignedName(String signedName) {
        this.signedName = signedName;
    }
}
