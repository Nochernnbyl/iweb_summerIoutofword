package com.iweb.view;

import com.iweb.Controller.Controller;

import java.util.Scanner;

/**
 * @author ASUS
 * @Date 2023/7/17 16:44
 * @Version 1.8
 */
public class MainView {

    Scanner sc = new Scanner(System.in);

    /**
     * 主页面
     */
    public void mainView(){
        System.out.println("请选择你要的业务");
        System.out.println("1.登录     2.注册");
        if ("1".equals(sc.nextLine())){
//            选择登录，跳转之登录界面
            loginView();
        } else if ("2".equals(sc.nextLine())) {
//            选择注册，跳转注册页面
            registerView();
        }else {
            System.out.println("输入有误，重新选择");
            mainView();
        }

    }

    /**
     * 用户选择登录界面
     */
    public void loginView(){
        System.out.println("欢迎来到登录界面");
        System.out.println("1.用户登录      2.管理员登录");
        if ("1".equals(sc.nextLine())){
//           跳转之普通用户登录界面
            normalLogin();
        }else if ("2".equals(sc.nextLine())){
//            跳转至管理员登录页面
            administratorLogin();
        }else {
            System.out.println("输入有误，请重新输入");
        }
    }

    /**
     * 普通用户登录界面
     */
    public void normalLogin(){
        System.out.println("请输入你的账号");
        String inputUsername = sc.nextLine();
        System.out.println("输入你的密码");
        String inputPassword = sc.nextLine();
        if (Controller.normalLogin(inputUsername,inputPassword)){
//            跳转用户登录成功的功能页面
            System.out.println("登录成功");
            normalFunction();
        }else {
//            登录失败跳转用户登录页面
            System.out.println("用户名或密码输入错误");
            loginView();
        }
    }

    /**
     * 普通用户登录成功的功能选择页面
     */
    public void normalFunction(){
        System.out.println("请选择你要的功能");
        System.out.println("1.查看商品   2.退出登录   ");
    }

    public void administratorLogin(){
        System.out.println("请输入你的账号");
        String inputUsername = sc.nextLine();
        System.out.println("输入你的密码");
        String inputPassword = sc.nextLine();
        if (Controller.administratorLogin(inputUsername,inputPassword)){
//            管理员认证成功，跳转管理员功能界面
            System.out.println("登陆成功");
            administratorFunction();
        }else {
            System.out.println("用户名或密码输入错误");
            loginView();
        }

    }

    /**
     * 管理员登陆成功的功能页面
     */
    public void administratorFunction(){
        System.out.println("请选择你要的功能");
        System.out.println("1.查看商品     2.查看订单     3.退出登录   ");
    }


    /**
     * 注册页面
     */
    public void registerView(){
        System.out.println("请输入你要注册的用户名");
        String inputName = sc.nextLine();
        System.out.println("请输入你的密码");
        String inputPassword = sc.nextLine();
        System.out.println("输入你的电话号码");
        String phoneNumber = sc.nextLine();
        try{
            Long.parseLong(phoneNumber);
        }catch (Exception e ){
            System.out.println("你的电话号码输入有问题，请重新尝试");
            registerView();
        }
        if (phoneNumber.length()!=11){
            System.out.println("你的电话号码格式有问题，请重新输入");
            registerView();
        }
        System.out.println("请输入你的地址");
        String address =  sc.nextLine();
        if (Controller.register(inputName,inputPassword,phoneNumber,address)){
            System.out.println("注册成功，你可以登录了");
            loginView();
        }else {
            System.out.println("注册失败，用户名重复，请重新输入");
        }
    }
}
