package com.library.test.reflect;

/**
 * Created by Administrator on 2018/4/13 0013.
 */
public class Person {
    public String name;
    private String sex;
    private String blood;
    private int age;
    private String interest;

    public Person(){

    }
    public Person(String name, String sex, String blood, int age, String interest) {
        this.name = name;
        this.sex = sex;
        this.blood = blood;
        this.age = age;
        this.interest = interest;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBlood() {
        return blood;
    }

    public void setBlood(String blood) {
        this.blood = blood;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }
}
