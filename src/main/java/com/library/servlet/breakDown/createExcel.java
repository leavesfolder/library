package com.library.servlet.breakDown;


import com.library.util.DBUtil;
import com.library.util.TbStAsj;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

/**
 * Created by Administrator on 2018/5/8 0008.
 */
public class createExcel {
    public static void main(String[] args){
        createExcel t = new createExcel();
        t.queryResult();
    }

    /**
     * 从库中查询结果
     */
    public List<TbStAsj> queryResult(){
        DBUtil db = new DBUtil();
        Connection conn = db.getConn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<TbStAsj> list = new ArrayList<TbStAsj>();

        try{


            String sqlCount = "select count(*) count from tb_st_asj where jyaq LIKE '%手机%' and ajlbdm!='05000300'";//jyaq LIKE '%手机%' and

            ps = conn.prepareStatement(sqlCount);
            rs = ps.executeQuery();
            int count=0;
            while (rs.next()){
                count = rs.getInt("count");
            }
            String sql = "";
            int beginIndex=0 ,endIndex=0;
            int resultCount = count;
            int fileIndex=1;
            while (count>0){
                List<Map<String, Object>> values = new ArrayList();
                List<String> titles =new ArrayList();
                titles.add("asjbh");
                titles.add("jyaq");
                titles.add("fxasjsj");
                titles.add("fxasjdd_dzmc");
                System.out.println("进入二层");
                count -=60000;
                if (endIndex>=resultCount){
                    break;
                }
                endIndex+=60000;
                sql = "select * from (select t.*,rownum num from (select asjbh,fxasjsj,fxasjdd_dzmc,jyaq from tb_st_asj where jyaq LIKE '%手机%' and ajlbdm!='05000300')t where rownum<="+endIndex+" )where num>"+beginIndex;
                System.out.println(sql);
                beginIndex+=60000;

                ps = conn.prepareStatement(sql);

                rs = ps.executeQuery();
                while (rs.next()){
                    Map<String, Object> map = new HashMap();
                    map.put("asjbh", rs.getString("asjbh"));
                    map.put("jyaq", rs.getString("jyaq"));
                    map.put("fxasjsj", rs.getDate("fxasjsj"));
                    map.put("fxasjdd_dzmc", rs.getString("fxasjdd_dzmc"));
                    values.add(map);
                }
                System.out.println(list.size());
//                WriterExcelUtil we= new WriterExcelUtil();
//                we.writerExcel("G:\\liuys\\createExcel"+fileIndex+".xls", "createExcel", titles, values);
                fileIndex++;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;


    }
}
