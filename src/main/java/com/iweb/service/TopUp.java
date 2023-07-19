package com.iweb.service;

import com.iweb.pojo.User;

/**
 * @author zhao
 * @date
 */
//账户充值
public interface TopUp {
    boolean topup(User user , int money);


}
