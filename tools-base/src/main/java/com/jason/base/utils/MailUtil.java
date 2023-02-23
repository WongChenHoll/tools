package com.jason.base.utils;

import com.jason.base.exception.ServiceException;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * <pre>
 *     说明：
 *          1.需要提供发件人该邮箱的账户和密码。
 *          2.在实现之前必须先保证邮箱的SMTP是开启的，163邮箱默认是开启的，QQ邮箱需要手动开启。
 *          3.对于有些邮箱会提供独立的密码（授权码），比如QQ邮箱，在开启SMTP时就会提供一个授权码，这里登陆此邮箱时必须使用授权码作为邮箱的密码。
 * </pre>
 *
 * @author WongChenHoll
 * @description 邮件工具
 * @date 2023-2-21 星期二 13:24
 **/
public class MailUtil {
    /**
     * 163服务器地址
     */
    public static final String HOST_163 = "smtp.163.com";
    /**
     * QQ邮箱服务器地址
     */
    public static final String HOST_QQ = "smtp.qq.com";
    /**
     * 服务器名称smtp
     */
    public static final String SERVERNAME_SMTP = "smtp";

    /**
     * QQ邮箱的SMTP(SLL)端口为465或587。
     * <pre>
     *     非SSL连接的端口一般默认为 25, 可以不添加。
     *     如果开启了 SSL 连接,需要改为对应邮箱的 SMTP 服务器的端口。
     *     QQ邮箱的SMTP(SLL)端口为465或587。
     * </pre>
     */
    public static final String PORT_465 = "465";
    public static final String PORT_587 = "587";
    public static final String PORT_25 = "25";
    /**
     * 邮件编码格式
     */
    public static final String PATTERN = "text/plain;charset='UTF-8'";
    /**
     * 发件人邮箱账号
     */
    private final String emailAccount;
    /**
     * 邮箱授权码
     */
    private final String authorizationCode;
    /**
     * 用户名，如果不设置，默认为{@link MailUtil#emailAccount}
     */
    private String username;

    /**
     * 发送人地址
     */
    private String senderAddress;
    /**
     * 收件人邮箱地址
     */
    private List<String> receiverAddressList = new ArrayList<>();
    /**
     * 抄送人邮箱地址
     */
    private List<String> ccAddressList = new ArrayList<>();

    /**
     * 邮件标题
     */
    private String emailTitle;
    /**
     * 邮件内容
     */
    private String emailContent;

    /**
     * 附件
     */
    private List<File> attachmentList = new ArrayList<>();

    private long timeout = 2000;

    /**
     * 构建邮箱的工具对象
     *
     * @param emailAccount      邮箱账号
     * @param authorizationCode 邮箱授权码
     */
    public MailUtil(String emailAccount, String authorizationCode) {
        this.emailAccount = emailAccount;
        this.authorizationCode = authorizationCode;
    }

    public MailUtil senderAddress(String senderAddress) {
        this.senderAddress = senderAddress;
        return this;
    }


    public MailUtil receiverAddress(String receiverAddress) {
        this.receiverAddressList.add(receiverAddress);
        return this;
    }

    public MailUtil receiverAddress(List<String> receiverAddressList) {
        this.receiverAddressList = receiverAddressList;
        return this;
    }

    public MailUtil ccAddress(String ccAddress) {
        this.ccAddressList.add(ccAddress);
        return this;
    }

    public MailUtil ccAddressList(List<String> ccAddressList) {
        this.ccAddressList = ccAddressList;
        return this;
    }

    public MailUtil emailTitle(String emailTitle) {
        this.emailTitle = emailTitle;
        return this;
    }

    public MailUtil emailContent(String emailContent) {
        this.emailContent = emailContent;
        return this;
    }

    public MailUtil attachmentList(List<File> attachmentList) {
        this.attachmentList = attachmentList;
        return this;
    }

    public MailUtil attachment(File attachment) {
        this.attachmentList.add(attachment);
        return this;
    }

    public MailUtil timeout(long timeout) {
        this.timeout = timeout;
        return this;
    }

    public MailUtil username(String username) {
        this.username = username;
        return this;
    }

    public void sendQQEmail() throws ServiceException {
        sendEmail(SERVERNAME_SMTP, HOST_QQ, PORT_465, false);
    }

    public void send163Email() throws ServiceException {
        sendEmail(SERVERNAME_SMTP, HOST_163, PORT_465, false);
    }

    /**
     * 发送邮件
     */
    public void sendEmail(String serverName, String host, String port, boolean openSSL) throws ServiceException {
        // 配置邮件环境
        Properties properties = new Properties();
        properties.setProperty("mail.transport.protocol", serverName);// 服务器名称
        properties.setProperty("mail.smtp.host", host);// 服务器地址
        properties.setProperty("mail.smtp.auth", "true");// 验证发件人的账户密码，是必须要有的
        properties.setProperty("mail.smtp.timeout", String.valueOf(timeout));// 邮件超时时间
        // 如果需要SSL安全认证，则需要下面的配置代码
        if (openSSL) {
            properties.setProperty("mail.smtp.ssl.enable", "true");// 开启ssl
            properties.setProperty("mail.smtp.port", port);// 端口
            properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            properties.setProperty("mail.smtp.socketFactory.fallback", "false");
            properties.setProperty("mail.smtp.socketFactory.port", port);
        }

        // 将账户密码交给JVM
        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailAccount, authorizationCode);
            }
        };
        // 创建邮件会话
        Session session = Session.getInstance(properties, authenticator);
        session.setDebug(true);// 可以在控制台看到发送邮件的步骤
        // 开始发送
        try {
            Message message = getMessage(session);
            Transport transport = session.getTransport(SERVERNAME_SMTP);
            transport.connect(emailAccount, authorizationCode);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (NoSuchProviderException e) {
            throw ServiceException.serverException("发送邮件获取服务器异常： ", e);
        } catch (MessagingException e) {
            throw ServiceException.connectionException("发送邮件获取连接异常： ", e);
        }
    }

    /**
     * 创建邮件体
     *
     * @param session 邮件会话
     * @return Message
     */
    private Message getMessage(Session session) throws ServiceException {
        Message message = new MimeMessage(session);
        try {
            // ============================= 发件人 =============================
            message.setFrom(new InternetAddress(this.senderAddress, username, "UTF-8"));

            // ============================== 收件人 ==============================
            if (receiverAddressList.isEmpty()) {
                throw ServiceException.validException("请选择收件人");
            }
            if (receiverAddressList.size() > 1) {
                // 多个收件人
                InternetAddress[] address = new InternetAddress[receiverAddressList.size()];
                for (int i = 0; i < address.length; i++) {
                    address[i] = new InternetAddress(receiverAddressList.get(i));
                }
                message.setRecipients(MimeMessage.RecipientType.TO, address);
            } else {
                // 单个收件人
                message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiverAddressList.get(0)));
            }

            // ============================= 抄送人================================
            if (ccAddressList.size() > 1) {
                // 多个抄送人
                InternetAddress[] address = new InternetAddress[ccAddressList.size()];
                for (int i = 0; i < address.length; i++) {
                    address[i] = new InternetAddress(ccAddressList.get(i));
                }
                message.setRecipients(MimeMessage.RecipientType.CC, address);
            } else if (ccAddressList.size() == 1) {
                // 单个抄送人
                message.setRecipient(MimeMessage.RecipientType.CC, new InternetAddress(ccAddressList.get(0)));
            }
            // =================================邮件文本内容==========================
            Multipart multipart = new MimeMultipart();// 邮件内容对象
            BodyPart bodyPart = new MimeBodyPart();// 邮件文本对象
            bodyPart.setText(emailContent);
            multipart.addBodyPart(bodyPart);
            // ============================= 邮件附件===============================
            if (attachmentList.size() > 0) {
                for (File file : attachmentList) {
                    BodyPart attach = new MimeBodyPart();
                    DataSource dataSource = new FileDataSource(file);
                    attach.setDataHandler(new DataHandler(dataSource));
                    attach.setFileName(MimeUtility.encodeWord(file.getName()));// 避免中文乱码
                    multipart.addBodyPart(attach);
                }
            }
            // ================================ 邮件标题========================
            message.setSubject(this.emailTitle);
            message.setContent(multipart, PATTERN);

        } catch (UnsupportedEncodingException | MessagingException e) {
            throw ServiceException.serverException("邮件发送失败", e);
        }
        return message;
    }
}
