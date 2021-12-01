package com.jason.test.common.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * 用于生成model实体类的工具，根据文件路径直接执行main方法
 *
 * @author ChenHol.Wong
 * @create 2020/7/31 22:51
 */
public class CreateModelUtil {

    public String a = "E:\\资料";


    public static void main(String[] args) throws IOException {
        String url = "D:\\project\\dataBase Design\\model.txt";
        File file = new File(url);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String content;
        while ((content = bufferedReader.readLine()) != null) {
            String[] split = content.split(",");
            String line = "";
            line += "private ";
            switch (split[2]) {
                case "VARCHAR2":
                case "CHAR":
                case "CLOB":
                    line += "String";
                    break;
                case "DATE":
                    line += "Date";
                    break;
                case "NUMBER":
                    line += "BigDecimal";
                    break;
            }
            String filed = reBuildFiled(split[0]);
            line += " ";
            line += filed;
            line += "; // ";
            line += split[1];
            line += "\r\n";
            System.out.println(line);
        }
    }

    private static String reBuildFiled(String filed) {
        String lowerCase = filed.toLowerCase();
        String[] split = lowerCase.split("_");
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < split.length; i++) {
            String a = split[i];
            stringBuilder.append(a.substring(0, 1).toUpperCase()).append(a.substring(1));
        }
        String toString = stringBuilder.toString();
        return toString.substring(0, 1).toLowerCase() + toString.substring(1);
    }
}
