package com.library.test.reflect;


import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2018/4/13 0013.
 */
public class Reflect {
    public static void main(String[] args){
        Class<?> classType = Student.class;
        //1.获取类的属性
        //获取类的所有属性
        Field[] fields=classType.getFields();
        //返回所有public的属性，包含父类的
        for (Field fi:fields) {
            System.out.println(fi);

        }
        fields = classType.getDeclaredFields();
        System.out.println();
        //返回自己的所有属性，包含private属性的，但是不含父类的
        for (Field f : fields)
        {
            System.out.println(f);
        }

        System.out.println();
        //2.获取类的方法
        Method[] methods = classType.getMethods();
        for (Method me:methods){
            System.out.println(me);
        }


        System.out.println();
        Constructor<?>[] constructors=classType.getConstructors();
        for (Constructor con:constructors) {
            System.out.println(con);
        }

        constructors = classType.getDeclaredConstructors();
        for (Constructor<?> m : constructors)
        {
            System.out.println(m);
        }

        try {
            Student st = (Student) classType.newInstance();
            System.out.println(st);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


    }


}
