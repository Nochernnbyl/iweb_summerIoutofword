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
public class Product {
    private int productId;
    private String productName;
    private int reviews;
    private int statsId;
    private double productPrice;
}
