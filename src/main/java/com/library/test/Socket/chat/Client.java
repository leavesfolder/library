package com.library.test.Socket.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by Administrator on 2018/4/12 0012.
 */
public class Client {
    public static void main(String[] args) throws IOException {
        //1.创建客户端
        Socket client = new Socket("localhost",8010);
        System.out.println("请输入昵称：");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String name = br.readLine();
        if (name==null||"".equals(name)){
            return;
        }

        //2.发送数据,采用线程的方式去实现，每输入一条消息(控制台进行输入)，即可发送给服务端
            Send send = new Send(client,name);
            Thread c1 = new Thread(send);
            c1.start();
            //3.接收数据，读取消息，跟发送数据是相互独立的
            Receive receive = new Receive(client);
            Thread c2 = new Thread(receive);
            c2.start();

    }
}
