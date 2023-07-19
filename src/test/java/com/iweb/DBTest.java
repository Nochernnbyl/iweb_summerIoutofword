package com.iweb;

import com.iweb.util.DBPool;

/**
 * @author ASUS
 * @Date 2023/7/19 16:18
 * @Version 1.8
 */
public class DBTest {


    public static void main(String[] args) throws Exception {
        System.out.println(DBPool.getConnection());
    }

}
