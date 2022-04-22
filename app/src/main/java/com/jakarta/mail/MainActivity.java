package com.jakarta.mail;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import java.io.IOException;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Mail.sendMail("mail title 4", "mail content 4");
                    }
                }).start();
            }
        });
        findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String server = "pop.sina.com";//POP3服务器地址
                        String user = "wshixileilei25";//用户名，填写自己的邮箱用户名
                        String password = "d224592163c2cff1";//密码，填写自己的密码
                        POP3Client pop3Client = null;
                        try {
                            pop3Client = new POP3Client(server, 110);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        pop3Client.recieveMail(user, password);

                    }
                }).start();
            }
        });
    }

}


