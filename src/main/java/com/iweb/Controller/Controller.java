package com.iweb.Controller;

import com.iweb.DAO.Impl.UserDAOImpl;
import com.iweb.DAO.UserDAO;
import com.iweb.pojo.User;
import com.iweb.service.TopUp;
import com.iweb.service.realize.TopUpImpl;
import com.iweb.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Controller {

    static Scanner sc = new Scanner(System.in);




    /**普通用户登录控制
     * @param username  用户名
     * @param password 用户密码
     */
    public static User normalLogin(String username,String password){
        List<User> users = (List<User>) new UserDAOImpl().selectAll();
        for (User u :users){
            if (u.getName().equals(username)){
                if (u.getPassword().equals(password)){
                    return u;
                }
            }
        }
        return null;
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

    /**充值功能的接口调用实现
     * @param user 当前登录的用户
     */
    public static void topup(User user){
        System.out.println("请输入你要充值的金额");
        int money = Integer.parseInt( sc.nextLine());
        TopUp topUp = new TopUpImpl();
        if (topUp.topup(user,money)){
            System.out.println("充值成功");
        }else {
            System.out.println("充值失败");
        }
//        跳回用户界面
        View.normalFunction();
    }

    /**
     * 查看商品
     */
    public static void showProduct(){

        System.out.println("在这里写查看商品功能实现");

    }

    /**
     * 查看购物车
     */
    public  static  void showCart(){
        System.out.println("在这里写购物车查看功能实现");
    }



    public static void showOrder(){
        System.out.println("在这里写查看订单功能");
    }


}
