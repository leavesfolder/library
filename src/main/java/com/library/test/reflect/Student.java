package com.library.test.reflect;

/**
 * Created by Administrator on 2018/4/13 0013.
 */
public class Student extends Person{
    private String grade;
    public String hair;
    public String study(String kecheng){
        return this.getName()+ "今天学习了"+kecheng;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getHair() {
        return hair;
    }

    public void setHair(String hair) {
        this.hair = hair;
    }
}
