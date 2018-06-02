package com.library.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

/**
 * Created by Administrator on 2018/4/8 0008.
 * 连接数据库工具类
 *
 */
public class DBUtil {
    public Connection getConn(){
        Properties ps = new Properties();
        InputStream is = this.getClass().getClassLoader().getResourceAsStream("jdbc.properties");
        Connection conn = null;
        try{
            ps.load(is);
            String driverName = ps.getProperty("jdbc.driver");
            String url = ps.getProperty("jdbc.url");
            String username = ps.getProperty("jdbc.username");
            String password = ps.getProperty("jdbc.password");
            Class.forName(driverName).newInstance();
            conn = DriverManager.getConnection(url,username,password);
        }catch (Exception e){
            e.printStackTrace();
        }
        return conn;
    }

    public void close(Connection conn, PreparedStatement ps, ResultSet rs){
        try {
            if (rs!=null){
                rs.close();
            }
            if (ps!=null){
                ps.close();
            }
            if (conn!=null&&!conn.isClosed()){
                conn.close();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
