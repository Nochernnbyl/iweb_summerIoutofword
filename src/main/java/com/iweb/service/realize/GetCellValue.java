package com.iweb.service.realize;

import com.iweb.util.Annotation.Excel;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;

/**
 * @author ASUS
 * @Date 2023/7/19 18:52
 * @Version 1.8
 */
public class GetCellValue {
    public static  Object getCellValue(Cell cell , Excel excel, String headName){
        if (cell instanceof XSSFCell){
            return getXlsxValue((XSSFCell) cell,excel,headName);
        }else if (cell instanceof HSSFCell){
            return getXlsValue((HSSFCell) cell,excel,headName);
        }else {
            return null;
        }
    }


    private static Object getXlsxValue(XSSFCell cell, Excel excel, String headName) {
        while (excel.name().equals(headName)){
            if (judgeHeadName(headName) instanceof Integer){
                return (int)cell.getNumericCellValue();
            }else if (judgeHeadName(headName) instanceof  String){
                return cell.getStringCellValue();
            }else {
                return null;
            }
        }
        return null;
    }


    private static Object getXlsValue(HSSFCell cell, Excel excel,String headName) {
        while (excel.name().equals(headName)){
            if (judgeHeadName(headName) instanceof Integer){
                return (int)cell.getNumericCellValue();
            }else if (judgeHeadName(headName) instanceof  String){
                return cell.getStringCellValue();
            }else {
                return null;
            }
        }
        return null;
    }

    private static Object judgeHeadName(String headname){
        if (headname.equals("商品编号")){
            return 1;
        }else if (headname.equals("商品名称")){
            return "";
        }else if (headname.equals("销量")){
            return 1;
        }else if (headname.equals("评价数")){
            return 1;
        }else if (headname.equals("属性编号")){
            return 1;
        }else if (headname.equals("商品价格")){
            return 1;
        }else {
            return null;
        }
    }

}
