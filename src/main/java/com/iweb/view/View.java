package com.iweb.view;

import com.iweb.Controller.Controller;
import com.iweb.DAO.Impl.OrderDAOImpl;
import com.iweb.DAO.Impl.ProductDAOImpl;
import com.iweb.DAO.OrderDAO;
import com.iweb.DAO.ProductDAO;
import com.iweb.pojo.Product;
import com.iweb.pojo.User;
import com.iweb.service.TopUp;
import com.iweb.service.realize.ImportProExcelImpl;
import com.iweb.service.realize.TopUpImpl;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.sql.SQLException;
import java.util.Scanner;


public class View {
    static   User currentUser  = new User();
    static Scanner sc = new Scanner(System.in);

    /**
     * 主页面
     */
    public static void mainView(){
        System.out.println("正在加载后台资源");
        if (new ImportProExcelImpl().productImport(new File("D:\\idea_workspace\\_0718_javaFundationGroupExcel_maven\\123.xlsx"), XSSFWorkbook.class)){
            System.out.println("资源加载完成");
        }else {
            System.out.println("资源加载失败");
        }
        System.out.println("请选择你要的业务");
        System.out.println("1.登录     2.注册");
        if ("1".equals(sc.nextLine())){
//            选择登录，跳转之登录界面
            loginView();
        } else if ("2".equals(sc.nextLine())) {
//            选择注册，跳转注册页面
            registerView();
        }else {
            System.out.println("错误输入，重新开始,提出到主页面");
            mainView();
        }

    }

    /**
     * 用户选择登录界面
     */
    public static void loginView(){
        System.out.println("欢迎来到登录界面");
        System.out.println("1.用户登录      2.管理员登录");
        if ("1".equals(sc.nextLine())){
//           跳转之普通用户
//           登录界面
            normalLogin();
        }else if ("2".equals(sc.nextLine())){
//            跳转至管理员登录页面
            administratorLogin();
        }else {
            System.out.println("错误输入，重新开始");
        }
    }

    /**
     * 普通用户登录界面
     */
    public static void normalLogin(){
        System.out.println("请输入你的账号");
        String inputUsername = sc.nextLine();
        System.out.println("输入你的密码");
        String inputPassword = sc.nextLine();
        if ((currentUser=Controller.normalLogin(inputUsername,inputPassword))!=null){
//            跳转用户登录成功的功能页面
            System.out.println("登录成功");
            normalFunction();
        }else {
//            登录失败跳转用户登录页面
            System.out.println("用户名或密码输入错误，重新开始登录");
            loginView();
        }
    }

    /**
     * 普通用户登录成功的功能选择页面
     */
    public static void normalFunction() {
        System.out.println("请选择你要的功能 ");
        System.out.println("1.查看商品  2.查看购物车  3.用户充值   4.退出登录   ");
        if ("1".equals(sc.nextLine())) {
//           这里跳转到查看商品功能控制
            System.out.println("这里跳转到查看商品功能控制");
            Controller.showProduct();
        }else if ("2".equals(sc.nextLine())){
//            这里跳转查看购物车功能控制
            System.out.println("这里跳转查看购物车功能控制");
            Controller.showCart();
        }else if ("3".equals(sc.nextLine())){
//            这里跳转用户充值界面
            System.out.println("正在跳转用户充值界面");
            Controller.topup(currentUser);
        }else if ("4".equals(sc.nextLine())){
            mainView();
        }else {
            System.out.println("输入有误，请重试");
            normalFunction();
        }

    }

    public static void administratorLogin(){
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
    public static void administratorFunction(){
        System.out.println("请选择你要的功能");
        System.out.println("1.查看商品     2.查看订单     3.退出登录   ");
        if ("1".equals(sc.nextLine())) {
//            这里也是调用Controller的查看商品功能
            System.out.println("这里也是调用Controller的查看商品功能");
            Controller.showProduct();
        }else if("2".equals(sc.nextLine())){
//           这里调用Controller的查看订单功能
            System.out.println("这里调用Controller的查看订单功能");
            Controller.showOrder();
        }else if("3".equals(sc.nextLine())){
            mainView();
        }
    }

    /**
     * 注册页面
     */
    public static void registerView(){
        System.out.println("请输入你要注册的用户名");
        String inputName = sc.nextLine();
        System.out.println("请输入你的密码");
        String inputPassword = sc.nextLine();
        System.out.println("输入你的电话号码");
        String phoneNumber = sc.nextLine();
        try{
            Long.parseLong(phoneNumber);
        }catch (Exception e ){
            System.out.println("你的电话号码输入有问题，注册失败");
            registerView();
        }
        if (phoneNumber.length()!=11){
            System.out.println("你的电话号码格式有问题，注册失败");
            registerView();
        }
        System.out.println("请输入你的地址");
        String address =  sc.nextLine();
        if (Controller.register(inputName,inputPassword,phoneNumber,address)){
            System.out.println("注册成功，你可以登录了");
            loginView();
        }else {
            System.out.println("注册失败");
        }
    }



}
