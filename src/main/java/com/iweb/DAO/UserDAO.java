package com.iweb.DAO;

import com.iweb.pojo.User;

import java.util.Collection;


public interface UserDAO {

    /**
     * 插入数据，注册时使用
     */
     void insert(String name,String password,String phoneNumber,String address) throws Exception;

    /**
     * @return 返回所有的用户集合
     */
     Collection<User> selectAll();

    void uppdate(User user , int money) throws Exception;



}
