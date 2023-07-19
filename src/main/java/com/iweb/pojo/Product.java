package com.iweb.pojo;

import com.iweb.util.Annotation.Excel;
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
    @Excel(name = "商品编号")
    private int productId;
    @Excel (name = "商品名称")
    private String productName;
    @Excel(name = "评价数")
    private int reviews;
    @Excel(name = "属性编号")
    private int statsId;
    @Excel(name = "商品价格")
    private double productPrice;
}
