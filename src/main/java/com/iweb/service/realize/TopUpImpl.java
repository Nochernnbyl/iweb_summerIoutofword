package com.iweb.service.realize;
import com.iweb.DAO.Impl.UserDAOImpl;
import com.iweb.DAO.UserDAO;
import com.iweb.pojo.User;
import com.iweb.service.TopUp;

import java.util.ArrayList;
import java.util.List;

//账户加钱
public class TopUpImpl implements TopUp {

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
