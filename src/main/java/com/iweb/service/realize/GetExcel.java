package com.iweb.service.realize;

import com.iweb.pojo.Product;
import com.iweb.util.Annotation.Excel;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ASUS
 * @Date 2023/7/19 18:55
 * @Version 1.8
 */
public class GetExcel {
    public List<Product> getExcel(File excelFile, Class<?> type) {

        List<Product> list = new ArrayList<>();
//        判断版本
        if (type == HSSFWorkbook.class) {
//            文件流输入
            try (FileInputStream fis = new FileInputStream(excelFile)
            ) {
//                基于poi创建excel的workbook
                Workbook workbook = WorkbookFactory.create(fis);
                HSSFWorkbook hb = (HSSFWorkbook) workbook;
//                获取表（sheet）的数量
                int sheets = hb.getNumberOfSheets();
                for (int i = 0; i < sheets; i++) {
//                    获取表
                    HSSFSheet sheet = hb.getSheetAt(i);
                    if (sheet == null) {
                        continue;
                    }
//                    获取每个表里行的数量
                    int rows = sheet.getLastRowNum();
//                    通过注解判断导入的excel是否是按照注解的形式导入
                    HSSFRow firstRow = sheet.getRow(0);
//                    获得第一行的表头名字
                    List<String> headName = new ArrayList<>();
//                    获得excel的第一行，来获取对应的列名
//                    并进行判断，如果与所需的Bill账单不同，那么直接返回
                    if (firstRow == null) {
                        continue;
                    } else {
                        Product neededProduct = new Product();
                        Class neededProductClass = neededProduct.getClass();
                        Field[] neededFields = neededProductClass.getDeclaredFields();
//                        对第一行遍历注意比对
                        for (int j = 0; j < neededFields.length; j++) {
                            HSSFCell cell = firstRow.getCell(j);
                            if (cell == null) {
                                System.out.println("该表不符合账单要求，请重新导入");
                                return null;
                            } else if (neededFields[j].getAnnotation(Excel.class).name().equals(cell.getStringCellValue())) {
                                headName.add(cell.getStringCellValue());
                                continue;
                            } else {
                                System.out.println("该表不符合账单要求，请重新导入");
                                return null;
                            }
                        }
                    }
                    for (int j = 1; j <= rows; j++) {
//                        获取行
                        HSSFRow row = sheet.getRow(j);
//                        每一行对应了一个bill对象
                        Product product = new Product();
                        Class productClass = product.getClass();
                        Field[] fields = productClass.getDeclaredFields();
                        if (row != null) {
//                            获得每个单元格
                            for (int k = 0; k < fields.length; k++) {
                                fields[k].setAccessible(true);
                                HSSFCell cell = row.getCell(k);
                                if (cell == null) {
                                    fields[k].set(product, null);
                                }
                                fields[k].set(product, GetCellValue.getCellValue(cell, fields[k].getAnnotation(Excel.class),headName.get(k)));
                            }
                        }
                        list.add(product);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (type == XSSFWorkbook.class) {
            //            文件流输入
            try (FileInputStream fis = new FileInputStream(excelFile)
            ) {
//                基于poi创建excel的workbook
                Workbook workbook = WorkbookFactory.create(fis);
                XSSFWorkbook hb = (XSSFWorkbook) workbook;
//                获取表（sheet）的数量
                int sheets = hb.getNumberOfSheets();
                for (int i = 0; i < sheets; i++) {
//                    获取表
                    XSSFSheet sheet = hb.getSheetAt(i);
                    if (sheet == null) {
                        continue;
                    }
//                    获取每个表里行的数量
                    int rows = sheet.getLastRowNum();
//                    通过注解判断导入的excel是否是按照注解的形式导入
                    XSSFRow firstRow = sheet.getRow(0);
//                    获得第一行的表头名字
                    List<String> headName = new ArrayList<>();
//                    获得excel的第一行，来获取对应的列名
//                    并进行判断，如果与所需的Bill账单不同，那么直接返回
                    if (firstRow == null) {
                        continue;
                    } else {
                        Product neededProduct = new Product();
                        Class neededProductClass = neededProduct.getClass();
                        Field[] neededFields = neededProductClass.getDeclaredFields();
//                        对第一行遍历注意比对
                        for (int j = 0; j < neededFields.length; j++) {
                            XSSFCell cell = firstRow.getCell(j);
                            if (cell == null) {
                                System.out.println("该表不符合账单要求，请重新导入");
                                return null;
                            } else if (neededFields[j].getAnnotation(Excel.class).name().equals(cell.getStringCellValue())) {
                                headName.add(cell.getStringCellValue());
                                continue;
                            } else {
                                System.out.println("该表不符合账单要求，请重新导入");
                                return null;
                            }
                        }
                    }
                    for (int j = 1; j <= rows; j++) {
//                        获取行
                        XSSFRow row = sheet.getRow(j);
//                        每一行对应了一个bill对象
                        Product product = new Product();
                        Class productClass = product.getClass();
                        Field[] fields = productClass.getDeclaredFields();
                        if (row != null) {
//                            获得每个单元格
                            for (int k = 0; k < fields.length; k++) {
                                fields[k].setAccessible(true);
                                XSSFCell cell = row.getCell(k);
                                if (cell == null) {
                                    fields[k].set(product, null);
                                }
                                fields[k].set(product, GetCellValue.getCellValue(cell, fields[k].getAnnotation(Excel.class),headName.get(k)));
                            }
                        }
                        list.add(product);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            return null;
        }
        return list;
    }


}
