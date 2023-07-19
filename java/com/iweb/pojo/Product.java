package com.iweb.pojo;

import com.iweb.util.excelAnnotation.Excel;
import lombok.Data;

@Data
/**
 * @author ASUS
 * @Date 2023/7/18 10:42
 * @Version 1.8
 */
public class Product {
    @Excel (name = "商品编号")
    private int id;
    @Excel (name = "商品名称")
    private String name;
    @Excel(name = "销量")
    private int salesVolume;
    @Excel(name = "评价数")
    private  int evaluate;
    @Excel(name = "属性编号")
    private int statsId;
    @Excel(name = "商品价格")
    private  int productPrice;

}
