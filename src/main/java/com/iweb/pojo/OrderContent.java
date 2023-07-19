package com.iweb.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhao
 * @date
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderContent {
    private int orderItemId;
    private int orderId;
    private int productId;
    private int productCount;
    private double productAllPrice;
}
