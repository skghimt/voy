package cn.wondervoy.utils.email;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("MailRegisterHtmlFormat")
public class MailRegisterHtmlFormat implements MailHtmlFormat {

    @Value("${email.register.header}")
    private String header;

    @Value("${email.register.tail}")
    private String tail;

    @Value("${email.register.url}")
    private String urlStart;

    // 邮件主题
    @Value("${email.register.subject}")
    private String subject;

    @Value("${email.findPasswd.url}")
    private String findPasswd;

    @Value("${email.findPasswd.header}")
    private String findPasswdHeader;

    @Value("${email.findPasswd.tail}")
    private String findPasswdTail;

    // 邮件主题
    @Value("${email.findPasswd.subject}")
    private String findPasswdSubject;

    // 邮件附件的文件名
    private String[] attachFileNames;

    public String getMailContent(String url){
        return header +String.format(urlStart,url) + tail;
    }

    public String getFindPasswdMailContent(String url, String email){
        return findPasswdHeader +String.format(findPasswd, url, email) + findPasswdTail;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String[] getAttachFileNames() {
        return attachFileNames;
    }

    public void setAttachFileNames(String[] attachFileNames) {
        this.attachFileNames = attachFileNames;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getTail() {
        return tail;
    }

    public void setTail(String tail) {
        this.tail = tail;
    }

    public String getFindPasswdSubject() {
        return findPasswdSubject;
    }

    public void setFindPasswdSubject(String findPasswdSubject) {
        this.findPasswdSubject = findPasswdSubject;
    }

    public String getUrlStart() {
        return urlStart;
    }

    public void setUrlStart(String urlStart) {
        this.urlStart = urlStart;
    }

    public String getFindPasswd() {
        return findPasswd;
    }

    public void setFindPasswd(String findPasswd) {
        this.findPasswd = findPasswd;
    }

    public String getFindPasswdHeader() {
        return findPasswdHeader;
    }

    public void setFindPasswdHeader(String findPasswdHeader) {
        this.findPasswdHeader = findPasswdHeader;
    }

    public String getFindPasswdTail() {
        return findPasswdTail;
    }

    public void setFindPasswdTail(String findPasswdTail) {
        this.findPasswdTail = findPasswdTail;
    }
}

