package com.library.user.model;


import java.util.Date;

/**
 * Created by Administrator on 2018/4/3 0003.
 */
public class User {
    private String id;
    private String username;
    private String password;
    private String email;
    private String role;
    private Integer status;
    private String regIp;
    private Date regTime;
    private String regCityCode;
    private String regCityName;
    private String phone;
    private String code;
    private String emiORpho; //用来接收前台传递过来的登录名，可能是手机号，也可能是邮箱

    public String getEmiORpho() {
        return emiORpho;
    }

    public void setEmiORpho(String emiORpho) {
        this.emiORpho = emiORpho;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRegIp() {
        return regIp;
    }

    public void setRegIp(String regIp) {
        this.regIp = regIp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getRegTime() {
        return regTime;
    }

    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }

    public String getRegCityCode() {
        return regCityCode;
    }

    public void setRegCityCode(String regCityCode) {
        this.regCityCode = regCityCode;
    }

    public String getRegCityName() {
        return regCityName;
    }

    public void setRegCityName(String regCityName) {
        this.regCityName = regCityName;
    }
}
