package com.library.test;


/**
 * Created by Administrator on 2018/4/8 0008.
 * 单例模式：确保一个类只有一个对象，并进行线程优化
 */
//懒汉模式
public class MyJVM {
    private static MyJVM instance = null;
    private MyJVM(){

    }
    public static MyJVM getInstance(){
        if (instance==null){//为了提高创建对象的效率
            synchronized (MyJVM.class){
                if (instance==null){//为了确保实例存在
                    instance = new MyJVM();
                }
            }
        }
        return instance;
    }



}
//饿汉模式
class myJVM2{
    private static myJVM2 instance = new myJVM2();
    private myJVM2(){

    }
    public static myJVM2 getInstance(){
        return instance;
    }
}

class myJVM3{
    private static class myhome{
        private static myJVM3 instance = new myJVM3();
    }
    private myJVM3(){

    }
    public static myJVM3 getInstance(){
        return myhome.instance;
    }
}
