package com.iweb.DAO.Impl;

import com.iweb.DAO.AdministratorDAO;
import com.iweb.pojo.Administrator;
import com.iweb.util.DBPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class AdministratorDAOImpl implements AdministratorDAO {

    /**
     * @return 返回所有的用户集合
     */
    @Override
    public Collection<Administrator> selectAll() {
        String sql = "select * from administrator ";
        List<Administrator> users = new ArrayList<>();
        try(
                Connection c = DBPool.getConnection();
                PreparedStatement ps = c.prepareStatement(sql)
        ){
            ResultSet rs =ps.executeQuery();
            while (rs.next()){
               Administrator administrator = new Administrator();
                administrator.setId(rs.getInt(1));
                administrator.setName(rs.getString(2));
                administrator.setPassword(rs.getString(3));
                users.add(administrator);
            }
        }catch (Exception e ){
            e.printStackTrace();
        }
        return (users.isEmpty()?null:users);
    }
}
