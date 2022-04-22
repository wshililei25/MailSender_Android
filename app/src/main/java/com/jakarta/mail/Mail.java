package com.jakarta.mail;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * 发送邮件
 */
public class Mail {
    static OutputStream ou = null;
    static BufferedReader bff = null;

    static private final String NAME_BASE64 = "d3NoaXhpbGVpbGVpMjU=\r\n";//用户名 base64 编码 ,注意是@前面的部分的base64编码
    static private final String PASS_BASE64 = "ZDIyNDU5MjE2M2MyY2ZmMQ==\r\n";//密码base64编码
    static private final String FROME = "MAIL FROM: <wshixileilei25@sina.com>\r\n";//用户名,注意格式
    static private final String TO = "RCPT TO: <xi_leilei@hoperun.com>\r\n";//收件人，注意格式

    static void sendMail(String subject, String content/*,String info*/) {
        try {
            Socket socket = new Socket();
            String line = null;

            socket.connect(new InetSocketAddress("smtp.sina.com", 25), 3000);//连接服务器，这里用的是新浪邮箱
            bff = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            ou = socket.getOutputStream();

            line = bff.readLine();
            System.out.println("CONNECT:" + line);

            /*ou.write("HELO smtp.sina.com\r\n".getBytes("UTF-8"));//发送问候消息
            line = bff.readLine();
            System.out.println("HELO:" + line);*/

            ou.write("AUTH LOGIN\r\n".getBytes("UTF-8"));//发送登陆命令
            line = bff.readLine();
            System.out.println("AUTH LOGIN:" + line);

            ou.write(NAME_BASE64.getBytes("UTF-8"));//发送用户名的base64编码
            line = bff.readLine();
            System.out.println("NAME:" + line);

            ou.write(PASS_BASE64.getBytes("UTF-8"));//发送密码的base64编码
            line = bff.readLine();
            System.out.println("PASS:" + line);

            ou.write(FROME.getBytes("UTF-8"));//发送用户名，应定要和前面发送的编码一致
            line = bff.readLine();
            System.out.println("FROM:" + line);

            ou.write(TO.getBytes("UTF-8"));//发送收件人地址
            line = bff.readLine();
            System.out.println("TO:" + line);

            ou.write("DATA\r\n".getBytes("UTF-8"));//发送数据命令
            line = bff.readLine();
            System.out.println("DATA:" + line);

            ou.write(("From:wshixileilei25@sina.com\r\n"//发件人，要和前面的一致
                    + "To:xi_leilei@hoperun.com\r\n" //收件人，要和前面的一致
                    + "Subject:" + subject + "\r\n\r\n").getBytes("UTF-8"));//邮件主题

            ou.write(("\r\t" + content).getBytes("UTF-8"));//邮件正文内容

            ou.write("\r\n.\r\n".getBytes("UTF-8"));//结束标志
            line = bff.readLine();
            System.out.println("END:" + line);

            ou.write("QUIT\r\n".getBytes("UTF-8"));//退出登录
            line = bff.readLine();
            System.out.println("QUIT:" + line);

            ou.close(); // 关闭Socket输出流
            bff.close(); // 关闭Socket输入流
            socket.close(); // 关闭Socket
        } catch (Exception e) {
            System.out.println("Error. " + e);
        }
    }

}
