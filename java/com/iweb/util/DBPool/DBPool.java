package com.iweb.util.DBPool;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;

/**
 * @author ASUS
 * @Date 2023/7/18 14:02
 * @Version 1.8
 */
public class DBPool {

    public  static Connection getConnection(){
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("D:\\idea_workspace\\_0718_javaFundationGroupExcel_maven\\src\\main\\java\\com\\iweb\\ruid.properties"));
            DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
            return dataSource.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
