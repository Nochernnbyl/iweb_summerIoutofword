package com.iweb.util;


import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;


public class DBPool {
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "123456";
    //ip地址 端口 访问数据库 请求参数(配置信息)
    private static final String URL =
            "jdbc:mysql://localhost:3306/task?characterEncoding=utf8";
    static{
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection() throws Exception{
        return DriverManager.getConnection(URL,USER_NAME,PASSWORD);
    }


}
