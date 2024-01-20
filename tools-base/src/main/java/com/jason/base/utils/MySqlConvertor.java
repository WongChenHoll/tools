package com.jason.base.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.status.StatusLogger;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

/**
 * @author WongChenHoll
 * @description MYSQL
 * @date 2024-1-14 星期日 9:58
 **/
public class MySqlConvertor extends SQLConvertor {
    private static final Logger log = StatusLogger.getLogger();

    /**
     * KEY : Mysql中列类型
     * VALUE : java中的字段类型
     */
    private static final HashMap<String, String> map = new HashMap<>();

    static {
        map.put("TINYINT", "byte");
        map.put("SMALLINT", "short");
        map.put("INT", "int");
        map.put("BIGINT", "long");
        map.put("MEDIUMINT", "int");
        map.put("FLOAT", "float");
        map.put("DOUBLE", "double");
        map.put("VARCHAR", "String");
        map.put("TEXT", "String");
        map.put("CHAR", "String");
        map.put("ENUM", "String");
        map.put("SET", "String");
        map.put("DATETIME", "LocalDate");
        map.put("TIMESTAMP", "Timestamp");
        map.put("DATE", "LocalDate");
        map.put("TIME", "LocalDateTime");
        map.put("BOOLEAN", "boolean");
        map.put("BOOL", "boolean");
        map.put("BLOB", "byte[]");
    }

    @Override
    void process(ExecutorService pool, CountDownLatch latch, XSSFSheet sheet, String target) {
        pool.execute(new ReadSheet(sheet, latch, target));
    }

    static class ReadSheet implements Runnable {

        private final XSSFSheet sheet;
        private final CountDownLatch latch;
        private final String filePath;
        private final List<String> uniques = new ArrayList<>();
        private final Map<String, String> indexMap = new HashMap<>();

        public ReadSheet(XSSFSheet sheet, CountDownLatch latch, String target) {
            this.sheet = sheet;
            this.latch = latch;
            this.filePath = target;
        }

        @Override
        public void run() {
            StringBuilder sql = new StringBuilder("CREATE TABLE ");
            StringBuilder modelClass = new StringBuilder("import com.baomidou.mybatisplus.annotation.TableField;\n" + "import com.baomidou.mybatisplus.annotation.TableId;\n" + "import com.baomidou.mybatisplus.annotation.TableName;\n" + "import lombok.Getter;\nimport lombok.Setter;\n" + "import org.apache.commons.lang3.builder.ToStringBuilder;\n" + "import org.apache.commons.lang3.builder.ToStringStyle;\n\n");
            // 实体类的注释
            modelClass.append("/**\n * @author ").append(System.getenv("USERNAME")).append("\n * @date ").append(DateUtil.currDateStr(DateUtil.DEFAULT_FULL_FORMAT)).append("\n **/\n");
            String tableName = sheet.getRow(3).getCell(1).getStringCellValue(); // 表名
            if (StringUtils.isBlank(tableName)) {
                log.info("sheet页（" + sheet.getSheetName() + "）未设置表名!");
                return;
            }

            modelClass.append("@Getter\n@Setter\n");
            modelClass.append("@TableName(value = \"").append(tableName).append("\")\n");
            modelClass.append("public class ");
            sql.append(tableName).append(" ( \r\n");
            modelClass.append(StrUtil.toClassName(tableName)).append(" {\r\n");

            int rowNums = sheet.getLastRowNum() + 1; // 总行数（包括空行）
            String column;
            for (int i = 7; i < rowNums; i++) {
                XSSFRow row = sheet.getRow(i);
                column = row.getCell(1).getStringCellValue();
                if ("id".equalsIgnoreCase(column)) {
                    sql.append(EMPTY).append(column).append(EMPTY).append(SQLConvertor.getValue(row.getCell(2))).append(" NOT NULL AUTO_INCREMENT COMMENT ").append("'").append(SQLConvertor.getValue(row.getCell(9))).append("',\n");
                    modelClass.append("\t@TableId(\"").append(column).append("\")\n").append("\tprivate ").append(getFieldType(SQLConvertor.getValue(row.getCell(2)))).append(EMPTY).append(StrUtil.toFieldName(column)).append(";\n\n");
                } else {
                    // 找出唯一性和索引
                    if ("Y".equals(SQLConvertor.getValue(row.getCell(5)))) {
                        uniques.add(column);
                    }
                    if ("Y".equals(SQLConvertor.getValue(row.getCell(6)))) {
                        indexMap.put(column, row.getCell(7).getStringCellValue());
                    }
                    // 添加字段sql
                    sql.append(EMPTY).append(column).append(EMPTY).append(SQLConvertor.getValue(row.getCell(2)));
                    if (row.getCell(3) != null && StringUtils.isNotBlank(SQLConvertor.getValue(row.getCell(3)))) {
                        sql.append("(").append(SQLConvertor.getValue(row.getCell(3))).append(")");
                    }
                    if ("N".equals(SQLConvertor.getValue(row.getCell(4)))) {
                        sql.append(" NOT NULL");
                    }
                    sql.append(" COMMENT '").append(SQLConvertor.getValue(row.getCell(9))).append("',\n");
                    // 实体类中添加字段
                    modelClass.append("\t@TableField(value = \"").append(column).append("\")\n");
                    modelClass.append("\tprivate ").append(getFieldType(SQLConvertor.getValue(row.getCell(2)))).append(EMPTY).append(StrUtil.toFieldName(column)).append(";").append(" //").append(SQLConvertor.getValue(row.getCell(9))).append("\n\n");
                }
            }
            // 添加主键、唯一性、索引 sql
            if ("id".equalsIgnoreCase(SQLConvertor.getValue(sheet.getRow(7).getCell(1)))) {
                sql.append(" PRIMARY KEY (id),\n");
            }
            if (!uniques.isEmpty()) {
                for (String unique : uniques) {
                    sql.append(" UNIQUE KEY ").append(unique).append("(").append(unique).append("),\n");
                }
            }
            if (!indexMap.isEmpty()) {
                for (String index : indexMap.keySet()) {
                    if (!uniques.contains(index)) {
                        sql.append(" KEY ").append(indexMap.get(index)).append("(").append(index).append("),\n");
                    }
                }
            }
            // 添加剩余sql和实体类中的代码
            sql.deleteCharAt(sql.length() - 2);
            String tableComment = sheet.getRow(2).getCell(1).getStringCellValue(); // 表的备注
            sql.append(")ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='").append(tableComment).append("';");
            modelClass.append("\t@Override").append("\n").append("\tpublic String toString() {\n").append("\t\treturn ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);\n").append("\t}\n");
            modelClass.append("}");
            // 写入文件
            SQLConvertor.writeFile(new File(filePath + "\\c_" + tableName + ".sql"), sql.toString().getBytes());
            SQLConvertor.writeFile(new File(filePath + "\\" + StrUtil.toClassName(tableName) + ".java"), modelClass.toString().getBytes());
            latch.countDown();
        }

    }

    private static String getFieldType(String columnType) {
        columnType = columnType.toUpperCase();
        return map.get(columnType);
    }
}
