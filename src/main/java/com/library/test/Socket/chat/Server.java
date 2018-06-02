package com.library.test.Socket.chat;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/4/12 0012.
 */
public class Server {
    private List<channel> list = new ArrayList<channel>();
    public static void main(String[] args) throws IOException {
        //1.创建服务
        new Server().start();


    }
    public void start() throws IOException {
        ServerSocket server = new ServerSocket(8010);
        while (true) {
            //2.接收数据
            channel chan = new channel(server.accept());
            list.add(chan);
            new Thread(chan).start();

        }

    }
    class  channel implements Runnable{
        private DataInputStream dis;
        private DataOutputStream dos;
        private boolean isRunning = true;
        private String name;


        public channel(){

        }
        public channel(Socket socket){
            this();
            try {
                dis = new DataInputStream(socket.getInputStream());
                dos = new DataOutputStream(socket.getOutputStream());
                this.name = dis.readUTF();
                this.send("欢迎进入聊天室");
                sendOther(name+"先生/女士，进入了聊天室！");
            } catch (IOException e) {
                e.printStackTrace();
                list.remove(this);
            }
        }

        public String recive(){
            String mess="";
            try {
                mess=dis.readUTF();
            } catch (IOException e) {
                isRunning = false;
                CloseUtil.closeAll(dos);
                list.remove(this);
            }
            return mess;
        }

        public void send(String mess){
            if (mess==null||"".equals(mess)){
                return;
            }
            try {
                dos.writeUTF(mess);
                dos.flush();
            } catch (IOException e) {
                isRunning = false;
                CloseUtil.closeAll(dos);
                list.remove(this);
            }
        }

        public void sendOther(String mess){
            //判断是否为私聊
            if (mess.startsWith("@")){
                System.out.println("进入私聊");
                //为私聊
                String name = mess.substring(1,mess.indexOf(":"));
                String content = mess.substring(mess.indexOf(":")+1);
                System.out.println(name+content);
                for (channel chan:list){
                    System.out.println(chan.name);
                    if (chan.name.equals(name)){
                        chan.send(this.name+"对您悄悄的说："+content);
                    }
                }


            }else{
                for (channel chan:list){
                    if (chan==this){
                        continue;
                    }
                    chan.send(chan.name+"对所有人说："+mess);
                }
            }

        }

        public void run() {
            while (isRunning){
                sendOther(recive());
            }

        }
    }

}

