package com.library.test;


/**
 * Created by Administrator on 2018/4/8 0008.
 */
public class GrabTicket implements Runnable{
    private int ticket = 10;
    public void run() {
        try {
            jp();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void jp() throws InterruptedException {
        while (true){
            if (ticket<=0){
                return;
            }
            //Thread.sleep(20);
            System.out.println(Thread.currentThread().getName()+"抢到了"+ticket);
            ticket--;
        }

    }

    public static void main(String[] args){
        GrabTicket gt = new GrabTicket();
        Thread t1 = new Thread(gt,"黄牛甲");
        Thread t2 = new Thread(gt,"攻城狮");
        Thread t3 = new Thread(gt,"前台小姐");
        //t2.setPriority(8);
       // t3.setPriority(Thread.MAX_PRIORITY);
        t1.start();
        t2.start();
        t3.start();
    }
}
