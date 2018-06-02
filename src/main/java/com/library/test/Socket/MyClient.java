package com.library.test.Socket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

/**
 * Created by Administrator on 2018/4/9 0009.
 */
public class MyClient {
    public static void main(String[] args) throws IOException {
        //1.创建客户端
        DatagramSocket client = new DatagramSocket(6666);
        //2.准备数据
        String str = "UPDsocket测试";
        byte[] data = str.getBytes();
        //3.打包数据
        DatagramPacket packet = new DatagramPacket(data,0,data.length,new InetSocketAddress("localhost",8888));
        //4.发送
        client.send(packet);
        //5.释放
        client.close();


    }
}
