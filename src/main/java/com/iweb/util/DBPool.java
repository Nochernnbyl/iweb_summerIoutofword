package com.iweb.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

/**
 * @author ASUS
 * @Date 2023/7/17 22:32
 * @Version 1.8
 */
public class DBPool {

    /**获取连接池
     * @return  通过封装在DBPool的方法，可以通过方法返回一个连接
     */
    public static Connection getConnection(){
        Properties properties = new Properties();

        try {
            properties.load(new FileInputStream("D:\\idea_workspace\\d0717_javaFoundation_maven\\src\\main\\java\\com\\iweb\\ruid.properties"));
            DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
            return dataSource.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }


}
