package com.library.test;

/**
 * Created by Administrator on 2018/4/7 0007.
 * 静态代理
 * 1.真实角色
 * 2.代理角色 持有真实角色的引用
 * 3.二者实现相同的接口
 */
public class StaticProxy {
    public static void main(String[] args){
        //创建真实角色
        you you = new you();
        //创建代理角色+真实角色的引用
        MarryCompany mc = new MarryCompany(you);
        mc.marry();
    }

}
interface Marry{
    public abstract void marry();
}

class you implements Marry{
    public void marry() {
        System.out.println("you and 嫦娥结婚了。。。");

    }
}

class MarryCompany implements Marry{
    private Marry you;

    public MarryCompany() {
    }

    public MarryCompany(Marry you) {
        this.you = you;
    }

    private void after(){
        System.out.println("收拾下猪窝。。");

    }
    private void before(){
        System.out.println("闹洞房。。");
    }
    public void marry() {
        after();
        you.marry();
        before();
    }

}
