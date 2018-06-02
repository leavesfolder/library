package com.library.util;

import com.library.user.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Administrator on 2018/4/8 0008.
 */
public class InsertVisitor {
    private DBUtil db;
    private int count;
    public void CreateVisitor(){
        db = new DBUtil();
        Connection conn=db.getConn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            String sql = "SELECT * FROM SYS_USER WHERE STATUS =0 and role='01' ";
            ps=conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                User user = new User();
                user.setUsername(rs.getString("username"));
                user.setId(rs.getString("id"));
                user.setRegCityCode(rs.getString("regCityCode"));
                user.setRegCityName(rs.getString("regCityName"));
                user.setRegIp(rs.getString("regIp"));
                user.setRole(rs.getString("role"));
                insertUser(user);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static Timestamp getAccessTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int dd = (int)(Math.random()*31+1);
        int hh = (int)(Math.random()*24+1);
        int mm = (int)(Math.random()*60+1);
        int ss = (int)(Math.random()*60+1);
        String  time = "2018-03-"+dd+" "+hh+":"+mm+":"+ss;
        System.out.println(time);
        Date now = null;
        Timestamp fwsj = null;
        try{
            now=sdf.parse(time);
            fwsj = new Timestamp(now.getTime());
        }catch (Exception e){
            e.printStackTrace();
        }
        return fwsj;
    }

    public  void insertUser(User user){
        db = new DBUtil();
        Connection conn=db.getConn();
        PreparedStatement ps = null;
        try{
            String inertSql = "INSERT INTO SYS_LOGIN_LOG(XXBH,RZSJ,YHID,YHXM,regCityCode,regCityName,scbz,ip,role)VALUES(?,?,?,?,?,?,0,?,?)";
            ps = conn.prepareStatement(inertSql);
            ps.setString(1, UUID.randomUUID().toString());
            ps.setTimestamp(2,getAccessTime());
            ps.setString(3,user.getId());
            ps.setString(4,user.getUsername());
            ps.setString(5,user.getRegCityCode());
            ps.setString(6,user.getRegCityName());
            ps.setString(7,user.getRegIp());
            ps.setString(8,user.getRole());
            ps.execute();
            count++;
                System.out.println("插入成功"+count+"条！");

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
        //getAccessTime();
        new InsertVisitor().CreateVisitor();
    }

}
