package com.lijian.test;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLDecoder;

// 读取excel文件中的内容
public class POITest {
//    @Test
//    public void readExcel() throws IOException {
//        FileInputStream fileInputStream = new FileInputStream(URLDecoder.decode("C:\\Users\\27710\\Downloads\\data.xlsx", "UTF-8"));
//        //创建工作簿
//        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
//        //获取工作表，既可以根据工作表的顺序获取，也可以根据工作表的名称获取
//        XSSFSheet sheet = workbook.getSheetAt(0);
//        //遍历工作表获得行对象
//        for (Row row : sheet) {
//            //遍历行对象获取单元格对象
//            for (Cell cell : row) {
//                //获得单元格中的值
//                String value = cell.getStringCellValue(); //注意：数字类型,需要修改excel单元格的类型，否则报错。
//                System.out.println(value);// new String(value.getBytes("UTF-8"),"GBK"));
//            }
//        }
//        //记得关掉这一步，否则就会报错
//        fileInputStream.close();
//        workbook.close();
//    }
}

