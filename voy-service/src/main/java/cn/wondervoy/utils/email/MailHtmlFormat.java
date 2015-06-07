package cn.wondervoy.utils.email;

/**
 * Created by ckzhang on 14-12-21.
 */
public interface MailHtmlFormat {

    String getMailContent(String url);

    String getFindPasswdMailContent(String url, String email);

    String getSubject();

    String getFindPasswdSubject();
}
