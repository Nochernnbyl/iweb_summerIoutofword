package com.iweb.service.TopUpImpl;

import com.iweb.DAO.Impl.UserDAOImpl;
import com.iweb.DAO.UserDAO;
import com.iweb.pojo.User;
import com.iweb.service.TopUp;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ASUS
 * @Date 2023/7/18 16:18
 * @Version 1.8
 */
public class TopUpUtil implements TopUp {

    @Override
    public boolean topup(User user, int money) {
        List<User> list = new ArrayList<>();
        UserDAO userDAO = new UserDAOImpl();
        list = (List<User>) userDAO.selectAll();
        for (User u : list ){
            if (u.getName().equals(user.getName())){
                if (u.getPassword().equals(user.getPassword())){
                    try {
                        new UserDAOImpl().uppdate(user,money);
                        return true;
                    } catch (Exception e) {
                        return false;
                    }
                }
            }
        }
        return false;
    }
}
