package com.library.test.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Administrator on 2018/4/13 0013.
 */
public class SocketTest {
    private ServerSocket server;
    public static void main(String[] args){
        SocketTest t = new SocketTest();
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
        StringBuffer sb = new StringBuffer();
        try {
            Socket socket = server.accept();
            String mess = "";
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            while (null!=(mess=br.readLine())){
                sb.append(mess);
                sb.append("\t\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(sb.toString().trim());
    }
}
