package com.jason.test.common.utils;

import cn.hutool.poi.excel.WorkbookUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

/**
 * @author ChenHol.Wong
 * @create 2020/10/9 20:46
 */
public class ExcleUtil {

    public static void main(String[] args) throws IOException {
        createExcle("D:\\账户密码\\Oracle数据库系统操作SQL.xlsx");
    }

    public static void createExcle(String filePath) throws IOException {
        File file = new File(filePath);
        SXSSFWorkbook sxssfBook = WorkbookUtil.createSXSSFBook(file);
        Sheet orCreateSheet = WorkbookUtil.getOrCreateSheet(sxssfBook, 1);
        FileInputStream fileInputStream = new FileInputStream(file);
//        HSSFWorkbook book = new HSSFWorkbook(fileInputStream);
        XSSFWorkbook book = new XSSFWorkbook(fileInputStream);
//        Workbook book = WorkbookUtil.createBook(filePath);
        Sheet sheet = book.getSheetAt(0);
        Iterator<Row> iterator = sheet.iterator();
        while (iterator.hasNext()) {
            Row row = iterator.next();
            Iterator<Cell> rowIterator = row.iterator();
            while (rowIterator.hasNext()) {
                Cell cell = rowIterator.next();
                String value = cell.getStringCellValue();
                System.out.print(value + " ");
            }
            System.out.print("\r\n");
        }


    }


}
