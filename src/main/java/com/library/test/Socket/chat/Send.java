package com.library.test.Socket.chat;

/**
 * Created by Administrator on 2018/4/12 0012.
 */

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * 将接收到的消息输出给服务端
 */
public class Send implements Runnable{
    private BufferedReader console ;
    private DataOutputStream dos ;
    private boolean isRunning = true ;
    private String name ;

    public Send(){
        console = new BufferedReader(new InputStreamReader(System.in));
    }

    public Send(Socket socket,String name){
        this();

        try {
            dos = new DataOutputStream(socket.getOutputStream());
            this.name = name;
            send(name);
        } catch (IOException e) {
            isRunning = false;
            CloseUtil.closeAll(dos,console);
        }
    }

    public String getMess(){
        String mess = "";
        try {
            mess = console.readLine();
        } catch (IOException e) {
            isRunning =false;
            CloseUtil.closeAll(dos,console);
        }
        return mess;
    }

    /**
     * 接收数据，发送数据
     */
    public void send(String mess){
        if (null!=mess&&!"".equals(mess)){
            try {
                dos.writeUTF(mess);
                dos.flush();
            } catch (IOException e) {
                isRunning = false;
                CloseUtil.closeAll(dos,console);
            }
        }

    }
    public void run() {
        while (isRunning){
            send(getMess());
        }
    }
}
