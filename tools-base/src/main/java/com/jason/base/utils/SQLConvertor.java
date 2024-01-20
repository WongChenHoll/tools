package com.jason.base.utils;

import com.jason.base.exception.ServiceException;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
public abstract class SQLConvertor {
    public static final String EMPTY = " ";

    /**
     * 下载模板
     *
     * @param downloadPath 下载路径，必须要有
     * @throws ServiceException 创建文件异常
     */
    public void downloadTemplate(String downloadPath) throws ServiceException {
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
            row(sheet, 4, "命名空间/数据库", "test_data", style);

            // 表格式内容
            XSSFRow row = sheet.createRow(6);
            cell(row, 0, "序号", style);
            cell(row, 1, "字段名", style);
            cell(row, 2, "字段类型", style);
            cell(row, 3, "长度", style);
            cell(row, 4, "是否为空", style);
            cell(row, 5, "是否唯一", style);
            cell(row, 6, "是否索引", style);
            cell(row, 7, "索引名", style);
            cell(row, 8, "默认值", style);
            cell(row, 9, "注释", style);
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

    private void row(XSSFSheet sheet, int rowNum, String c0, String c1, CellStyle style) {
        XSSFRow r2 = sheet.createRow(rowNum);
        cell(r2, 0, c0, style);
        cell(r2, 1, c1, null);
    }

    private void cell(Row row, int cellNum, String cellV, CellStyle style) {
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
    public void convertor(File tableExcel, String target) throws ServiceException {
        AssertTool.notNull(tableExcel, "请选择文件");
        AssertTool.isTrue(tableExcel.length() > 0, "文件不能为空");
        if (StringUtils.isBlank(target)) {
            target = tableExcel.getParent();
        }
        try (FileInputStream inputStream = new FileInputStream(tableExcel);
             XSSFWorkbook workbook = new XSSFWorkbook(inputStream)) {
            int sheets = workbook.getNumberOfSheets();
            //多线程操作
            CountDownLatch latch = new CountDownLatch(sheets);
            ExecutorService pool = Executors.newFixedThreadPool(1);
            System.out.println("sheet数量：" + sheets);
            for (int i = 0; i < sheets; i++) {
                XSSFSheet sheet = workbook.getSheetAt(i);
                process(pool, latch, sheet, target);
            }
            try {
                latch.await();
            } catch (InterruptedException e) {
                throw ServiceException.threadException(e);
            }
            pool.shutdown();
        } catch (IOException e) {
            throw ServiceException.fileException("表设计Excel操作失败");
        }
    }

    abstract void process(ExecutorService pool, CountDownLatch latch, XSSFSheet sheet, String target);

    public static String getValue(Cell cell) {
        if (cell == null) {
            return EMPTY;
        }
        CellType type = cell.getCellType();
        switch (type) {
            case NUMERIC:
                if (org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(cell)) {
                    Date date = cell.getDateCellValue();
                    return DateUtil.toDateStr(date);
                } else {
                    double value = cell.getNumericCellValue();
                    return String.valueOf((int) value);
                }
            case FORMULA:
                return cell.getCellFormula();
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case STRING:
                return cell.getStringCellValue();
            case ERROR:
            case BLANK:
            case _NONE:
                return EMPTY;
        }
        return EMPTY;
    }

    public static void writeFile(File file, byte[] bytes) {

        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream os = new FileOutputStream(file);
            os.write(bytes);
            os.close();
            System.out.println("文件[" + file.getAbsolutePath() + "]已完成！");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
