package com.library.test.Socket.TcpSocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by Administrator on 2018/4/11 0011.
 * 1.创建客户端 必须指定服务端+端口，此时就在连接
 */
public class Client {
    public static void main(String[] args) throws UnknownHostException,IOException{
        Socket client=new Socket("localhost",8888);
        BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
        String mes;
        while (null!=(mes=br.readLine())){
            System.out.println(mes);
        }
    }
}
