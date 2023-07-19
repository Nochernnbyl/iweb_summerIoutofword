package com.iweb;

import com.iweb.service.ProductImport;
import com.iweb.service.productImportImpl.ImportUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ProductImport productImport = new ImportUtil();
        productImport.productImport(new File(""), XSSFWorkbook.class);
    }
}
