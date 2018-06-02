package com.library.test.Socket.chat;

/**
 * Created by Administrator on 2018/4/12 0012.
 */

import java.io.*;
import java.net.Socket;

/**
 * 接收数据，转发数据
 */
public class Receive implements Runnable{
    private DataInputStream dis;

    private boolean isRunning = true;

    public Receive(){

    }
    public Receive(Socket socket){
        this();
        try {
            dis = new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
            isRunning = false;
            CloseUtil.closeAll(dis);
        }
    }

    public String receive(){
        String mess = "";
        try {
            mess = dis.readUTF();
        } catch (IOException e) {
            isRunning = false;
            CloseUtil.closeAll(dis);
        }
        return mess;
    }


    public void run() {
        while (isRunning){
           System.out.println(receive());
        }

    }
}
