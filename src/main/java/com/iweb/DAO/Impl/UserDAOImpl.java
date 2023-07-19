package com.iweb.DAO.Impl;

import com.iweb.DAO.UserDAO;
import com.iweb.pojo.User;
import com.iweb.util.DBPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class UserDAOImpl implements UserDAO {
    /**
     * 插入数据，注册时使用
     */
    @Override
    public void insert(String name,String password,String phoneNumber,String address) throws Exception {

        String sql = "INSERT INTO USER (uname,upassword,umoney,phoneNumber,address) VALUES (?,?,0,?,?)\n";
        try(
                Connection c = DBPool.getConnection();
                PreparedStatement ps =c.prepareStatement(sql)
                ){
            ps.setString(1,name);
            ps.setString(2,password);
            ps.setString(3,phoneNumber);
            ps.setString(4,address);
            ps.execute();
        }catch (Exception e){
            throw new Exception();
        }
    }

    /**
     * @return 返回所有的用户集合
     */
    @Override
    public Collection<User> selectAll() {
        String sql = "select * from user ";
        List<User> users = new ArrayList<>();
        try(
                Connection c = DBPool.getConnection();
                PreparedStatement ps = c.prepareStatement(sql)
                ){
            ResultSet rs =ps.executeQuery();
            while (rs.next()){
            User user =  new User();
            user.setId(rs.getInt(1));
            user.setName(rs.getString(2));
            user.setPassword(rs.getString(3));
            user.setPhoneNumber(rs.getString(4));
            user.setAddress(rs.getString(5));
            users.add(user);
            }
        }catch (Exception e ){
            e.printStackTrace();
        }
        return (users.isEmpty()?null:users);
    }

     @Override
    public void uppdate(User user, int money) throws Exception {
        String sql = "UPDATE USER SET account = ? WHERE userid = ?\n";
        try (Connection c = DBPool.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setInt(1,user.getAccount()+money);
            ps.setInt(2,user.getId());
            ps.execute();
        } catch (Exception e) {
            throw  new Exception();
        }


    }
}
