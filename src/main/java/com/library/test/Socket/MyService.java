package com.library.test.Socket;

import com.library.user.model.SysLoginLog;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * Created by Administrator on 2018/4/9 0009.
 */
public class MyService {
    public static void main(String[] args) throws IOException {
        //1.创建服务器端
        DatagramSocket service = new DatagramSocket(8888);
        //2.准备接受容器
        byte[] container = new byte[1024];
        //3.打包，封装
        DatagramPacket dp = new DatagramPacket(container,container.length);
        //4.接收数据
        service.receive(dp);
        //5.分析数据
        byte[] data = dp.getData();
        int len = data.length;
        System.out.println(new String(data,0,len));
        //6.释放资源
        service.close();

    }
}
