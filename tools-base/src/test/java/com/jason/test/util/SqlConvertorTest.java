package com.jason.test.util;

import com.jason.base.exception.ServiceException;
import com.jason.base.utils.MySqlConvertor;
import org.junit.Test;

import java.io.File;

/**
 * @author WongChenHoll
 * @description
 * @date 2024-1-14 星期日 9:59
 **/
public class SqlConvertorTest {
    @Test
    public void testDownloadTemplate() throws ServiceException {
        MySqlConvertor m = new MySqlConvertor();
        m.downloadTemplate("D:\\data\\test-data\\SQLConvertor");

    }

    @Test
    public void testMysqlConvertor() throws ServiceException {
        MySqlConvertor m = new MySqlConvertor();
        File tableExcel = new File("D:\\important\\day-to-day-account\\数据库设计.xlsx");
        m.convertor(tableExcel, "");
    }

    @Test
    public void testStringBuffer(){
        StringBuffer sb = new StringBuffer("1234567890");
        System.out.println(sb);
        StringBuffer deleteCharAt = sb.deleteCharAt(sb.length() - 1);
        System.out.println(deleteCharAt);
        System.out.println(sb);
    }
}
