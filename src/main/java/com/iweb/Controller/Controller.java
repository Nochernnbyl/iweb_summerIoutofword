package com.iweb.Controller;

import com.iweb.DAO.Impl.UserDAOImpl;
import com.iweb.DAO.UserDAO;
import com.iweb.pojo.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ASUS
 * @Date 2023/7/17 18:51
 * @Version 1.8
 */
public class Controller {

    /**普通用户登录控制
     * @param username  用户名
     * @param password 用户密码
     * @param return 返回一个布尔值，在控制端中进行与数据库用户比对，返回用户是否登陆成功
     */
    public static boolean normalLogin(String username,String password){
        List<User> users = (List<User>) new UserDAOImpl().selectAll();
        for (User u :users){
            if (u.getName().equals(username)){
                if (u.getPassword().equals(password)){
                    return true;
                }
            }
        }
        return false;
    }


    /**管理员登录控制
     * @param username  用户名
     * @param password 用户密码
     * @return 返回一个是否正确登录的布尔值
     */
    public static boolean administratorLogin(String username,String password){
    return true;
    }

    /** 用于注册新用户
     * @param registerName  用户注册名
     * @param password 用户注册密码
     * @return 返回一个注册成功与失败的值，失败只考虑了用户名重复
     */
    public static boolean register(String registerName,String password,String phoneNumber,String address){
      try{
          UserDAO userDAO = new UserDAOImpl();
          userDAO.insert(registerName,password,phoneNumber,address);
      } catch (Exception e) {
          return false;
      }
      return true;
    }


}
