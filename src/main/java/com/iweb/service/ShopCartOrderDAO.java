package com.iweb.service;

import com.iweb.pojo.ShopCar;

import java.util.List;

/**
 * @author zhao
 * @date
 */
//购物车结算
public interface ShopCartOrderDAO {
    List<ShopCar> getCartItemsByUserID(int userID);

    int addCartItemToOrderContent(ShopCar cartItem);

    void deleteCartItemByProductId(int userID, int productID);

    ShopCar getCartItemByProductId(int userID, int productID);

    int generateOrder(int userID, int addressID, double totalPrice);

    void updateAccount(int userID, double totalPrice);
}
