package com.www.platform.util;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.sql.*;
import java.util.Date;
import java.util.Properties;


/**
 * Created by upsmart on 17-2-8.
 */

@Component
public class DBUtil {

    private static org.slf4j.Logger logger = LoggerFactory.getLogger(DBUtil.class);
//    @Value("#{configProperties['mysql.url.upsmart']}")
    private String DB_URL="jdbc:mysql://localhost:3306/drip?characterEncoding=utf8";

//    @Value("#{configProperties['mysql.username.upsmart']}")
    private String USER="root";

//    @Value("#{configProperties['mysql.password.upsmart']}")
    private String PASS="root";


    private static Connection conn;
    Savepoint savepoint = null;

    public DBUtil() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ResultSet queryData(String name) {
        logger.info("根据用户名查询密码");

        String sql = "select c.empid,u.upwd from comemp c,user u where c.uid=u.uid and u.uname=?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        close();
        return null;
    }

    public ResultSet queryData() {
        logger.info("查询待提醒数据");
        String sql = "select s.cname, s.cpwd , r.sendType, r.sendId, r.sendWay, r.message from remind r, script s where r.createtime>DATE_SUB(NOW(),INTERVAL 10 minute) and r.ifinfo = 1 and r.sname = s.sname";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        close();
        return null;
    }

    public int querymaid(int acctid) {
        String sql = "select maid from account where acctid=?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, acctid);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("maid");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public boolean insert(int acctid, int empid, int maid, double tamt, int tclass, String tnumber,
        int tstatus) {
        String sql =
            "insert into transaction(acctid,createtime,empid,maid,tamt,tclass,tnumber,tstatus) values (?,?,?,?,?,?,?,?)";
        String macctsql = "update masteracct set maamt = maamt - ? where maid = ?";
        String acctsql = "update account set acctamt = acctamt - ? where acctid = ?";
        boolean issuccess = true;
        try {
            conn.setAutoCommit(false);
            savepoint = conn.setSavepoint("transaction");
            //save date to transaction
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, acctid);
            pstmt.setTimestamp(2, new Timestamp(new Date().getTime()));
            pstmt.setInt(3, empid);
            pstmt.setInt(4, maid);
            pstmt.setDouble(5, tamt);
            pstmt.setInt(6, tclass);
            pstmt.setString(7, tnumber);
            pstmt.setInt(8, tstatus);
            pstmt.executeUpdate();

            PreparedStatement pstmt2 = conn.prepareStatement(macctsql);
            pstmt2.setDouble(1, tamt);
            pstmt2.setInt(2, maid);
            pstmt2.executeUpdate();

            PreparedStatement pstmt3 = conn.prepareStatement(acctsql);
            pstmt3.setDouble(1, tamt);
            pstmt3.setInt(2, acctid);
            pstmt3.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            issuccess = false;
            try {
                conn.rollback(savepoint);
                System.out.println("ca");
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            return false;
        } finally {
            try {
                String osql = "insert into operate(empid, oresult, otime, otype) values (?, ?, ?, ?)";
                PreparedStatement pstmt4 = conn.prepareStatement(osql);
                pstmt4.setInt(1,empid);
                if(issuccess) {
                    pstmt4.setString(2,"扣费成功");
                } else {
                    pstmt4.setString(2,"扣费失败");
                }
                pstmt4.setTimestamp(3, new Timestamp(new Date().getTime()));
                pstmt4.setString(4,"扣费请求");
                pstmt4.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        try {
            conn.commit();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        close();
        return true;
    }


    public boolean insertRemind(String sname,String cname) {
        String sql =
            "insert into remind(createtime,ifInfo,message,result,sendId,sendType,sendWay,sname) values (?,?,?,?,?,?,?,?)";
        String macctsql = "update masteracct set maamt = maamt - ? where maid = ?";
        boolean issuccess = true;
        try {
            PreparedStatement pstmt =
                conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setTimestamp(1, new Timestamp(new Date().getTime()));
            pstmt.setInt(2, 1);
            pstmt.setString(3, "脚本启动失败，原因是脚本文件不存在，请重新传入");
            pstmt.setString(4, "脚本启动失败");
            pstmt.setInt(5, 1);
            pstmt.setInt(6, 1);
            pstmt.setInt(7, 2);
            pstmt.setString(8, sname);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            issuccess = false;
        }
        close();
        return true;
    }

    private void close() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
