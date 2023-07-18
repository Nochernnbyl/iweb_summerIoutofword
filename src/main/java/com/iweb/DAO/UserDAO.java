package com.iweb.DAO;

import com.iweb.pojo.User;

import java.util.Collection;

/**
 * @author ASUS
 * @Date 2023/7/17 16:41
 * @Version 1.8
 */
public interface UserDAO {

    /**
     * 插入数据，注册时使用
     */
     void insert(String name,String password,String phoneNumber,String address) throws Exception;

    /**
     * @return 返回所有的用户集合
     */
     Collection<User> selectAll();





}
