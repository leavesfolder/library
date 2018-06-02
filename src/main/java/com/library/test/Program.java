package com.library.test;

/**
 * Created by Administrator on 2018/4/7 0007.
 * 使用静态代理实现多线程
 * 1.创建真实角色
 * 2.创建代理角色，代理角色持有真实角色的引用
 * 3.调用start（）启动线程
 *
 */
public class Program implements Runnable {
    public void run() {
        for (int i=0;i<100;i++){
            System.out.println("执行到第"+i+"步");
        }
    }
}
