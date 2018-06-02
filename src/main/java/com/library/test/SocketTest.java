package com.library.test;

import java.net.*;
import java.net.UnknownHostException;

/**
 * Created by Administrator on 2018/4/9 0009.
 */
public class SocketTest {
    public static void main(String[] args)throws UnknownHostException {
        try{

//            InetAddress ia = new InetAddress.getLocalHost();
//            System.out.println(ia.getHostAddress());
            InetSocketAddress is = new InetSocketAddress("localhost",8008);
            URL ur= new URL("http:www.baidu.com:80/index.html");
            System.out.println(ur.getPort());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
