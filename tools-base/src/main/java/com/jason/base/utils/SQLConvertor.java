package com.jason.base.utils;

import com.jason.base.exception.ServiceException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.*;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;

/**
 * <pre>
 * Excel中的表的设计要符合模板内容。
 * 支持Mysql和Oracle
 * </pre>
 *
 * @author WongChenHoll
 * @description 根据Excel中标的设计自动生成对应的SQL和实体类
 * @date 2024-1-9 星期二 13:54
 **/
public class SQLConvertor {

    private SQLConvertor() {
    }

    public static void main(String[] args) throws ServiceException {
        downloadTemplate("D:\\data\\test-data\\SQLConvertor");
    }

    /**
     * 下载模板
     *
     * @param downloadPath 下载路径，必须要有
     * @throws ServiceException 创建文件异常
     */
    public static void downloadTemplate(String downloadPath) throws ServiceException {
        AssertTool.notBlank(downloadPath, "请选择模板下载路径");
        String fileName = downloadPath + "\\数据库表设计模板（示例）.xlsx";
        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            // 单元格背景色
            // 设置单元格背景颜色需要调用setFillForegroundColor()，而不是setFillBackgroundColor()，这一点也非常坑，
            // 如果不设置setFillPattern()，则默认不填充，直接是空白的，不管设置什么颜色都没用！
            XSSFCellStyle style = workbook.createCellStyle();
            XSSFColor color = new XSSFColor(new Color(255, 255, 0), null);
            style.setFillForegroundColor(color);
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            // 表格信息
            XSSFSheet sheet = workbook.createSheet("示例模板");
            row(sheet, 0, "设计人", System.getenv("USERNAME"), style);
            row(sheet, 1, "设计时间", DateUtil.currDateStr(DateUtil.DEFAULT_FORMAT_YYYY_MM_DD), style);
            row(sheet, 2, "表备注", "用户表", style);
            row(sheet, 3, "表名", "t_user", style);

            // 表格式内容
            XSSFRow row = sheet.createRow(5);
            cell(row, 0, "序号", style);
            cell(row, 1, "字段名", style);
            cell(row, 2, "字段类型", style);
            cell(row, 3, "长度", style);
            cell(row, 4, "是否为空", style);
            cell(row, 5, "是否唯一", style);
            cell(row, 6, "索引名", style);
            cell(row, 7, "默认值", style);
            cell(row, 7, "注释", style);
            File file = new File(fileName);
            if (!file.exists()) {
                if (!file.createNewFile()) {
                    throw ServiceException.fileException("文件创建失败");
                }
            }
            try (FileOutputStream stream = new FileOutputStream(file)) {
                workbook.write(stream);
            }
        } catch (Exception e) {
            throw ServiceException.serverException("创建表格异常", e);
        }
        System.out.println("表格创建完成");
    }

    private static void row(XSSFSheet sheet, int rowNum, String c0, String c1, CellStyle style) {
        XSSFRow r2 = sheet.createRow(rowNum);
        cell(r2, 0, c0, style);
        cell(r2, 1, c1, null);
    }

    private static void cell(Row row, int cellNum, String cellV, CellStyle style) {
        Cell cell = row.createCell(cellNum);
        cell.setCellValue(cellV);
        cell.setCellStyle(style);
    }

    /**
     * 文件转换，在指定的目录中生成SQL文件和实体类Java文件
     *
     * @param tableExcel 数据库表设计文件
     * @param target     指定目录
     * @throws ServiceException 异常
     */
    public static void convertor(File tableExcel, String target) throws ServiceException {
        AssertTool.notNull(tableExcel, "请选择文件");
        AssertTool.isTrue(tableExcel.length() > 0, "文件不能为空");


    }
}
