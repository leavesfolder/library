package com.library.test.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Administrator on 2018/4/13 0013.
 */
public class Server {
    private ServerSocket server;
    public static void main(String[] args){
        Server t = new Server();
        t.start();

    }
    public void start(){
        try {
            server = new ServerSocket(8888);
            this.receive();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stop(){

    }
    private void  receive(){
        String str = "";
        try {
            Socket socket = server.accept();
            byte[] data = new byte[20480];
            int len=socket.getInputStream().read(data);
            str = new String(data,0,len);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(str);
    }
}
