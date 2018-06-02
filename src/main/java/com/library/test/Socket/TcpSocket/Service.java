package com.library.test.Socket.TcpSocket;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Administrator on 2018/4/11 0011.
 */
public class Service {
    public static void main(String[] args) throws IOException{
        //1.创建服务器指定端口
        ServerSocket server = new ServerSocket(8888);
        //2.接收客户端连接，阻塞式
        Socket socket = server.accept();
        System.out.println("一个客户端建立连接");
        String Str="欢迎光临";
        //3.发送数据
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        bw.write(Str);
        bw.newLine();
        bw.flush();

    }
}
