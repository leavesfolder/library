package com.library.test;

/**
 * Created by Administrator on 2018/4/8 0008.
 * 合并线程
 */
public class JoinThread extends Thread {
    /**
     * 线程的一些基本知识
     * 1.线程的运行过程：
     *   新生状态---《调用start（）》----就绪状态-----运行状态--阻塞状态---就绪状态----
     *   运行状态-----死亡状态
     *
     * 2.停止线程
     *    1》自然停止，线程正常执行完毕
     *    2》外部干涉：
     *       创建线程停止标识
     *       线程体调用该标识
     *       创建方法改变标识
     *       外部根据条件调用改变标识的方法。
     * 3.线程的阻塞
     *   1》join:合并线程
     *   2》yield:暂停自己的线程，执行其他线程
     * @param args
     */
    public static void main(String[] args){
        JoinThread jt = new JoinThread();
        Thread t=new Thread(jt);
        t.start();
        for (int i=0;i<1000;i++){
            if (i==50){
                try {
                    t.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("老子是Main....."+i);
        }
    }

    @Override
    public void run() {
        for (int i=0;i<100;i++){
            System.out.println("我是running。。。。。"+i);
        }

    }
}
