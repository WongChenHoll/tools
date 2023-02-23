package com.jason.base.utils;

import cn.hutool.core.date.DateUtil;
import com.jason.base.exception.ServiceException;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author WongChenHoll
 * @description CURL命令执行
 * @date 2023-2-1 星期三 15:36
 **/
public class CurlUtil {
    private CurlUtil() {
    }

    /**
     * 执行CURL命令
     *
     * @param curl CURL命令数组
     * @return 执行结果
     */
    public static String executeCURL(String[] curl) throws ServiceException {
        System.out.println("CURL开始执行时间:" + DateUtil.now());
        System.out.println("CURL执行命令：" + StringUtils.join(curl, " "));
        StringBuilder outPut = new StringBuilder();
        try {
            Process exec = Runtime.getRuntime().exec(curl);
            try (BufferedInputStream stream = new BufferedInputStream(exec.getInputStream())) {
                byte[] bytes = new byte[1024];
                int len, count = 0;
                while ((len = stream.read(bytes, 0, bytes.length)) > -1) {
                    if (count >= 2048) {
                        break;
                    }
                    outPut.append(new String(bytes, 0, len));
                    count++;
                }
            }
            return StringUtils.substring(outPut.toString(), 0, 10000);
        } catch (IOException e) {
            throw ServiceException.serverException("CURL命令执行异常", e);
        } finally {
            System.out.println("CURL执行结束时间:" + DateUtil.now());
        }
    }

    /**
     * 生成curl命令的数组
     *
     * @param curl     curl命令
     * @param bodyData 请求体数据
     * @param formData from表单数据
     * @param timeout  超时时间，单位秒（s）
     * @return 命令数组
     */
    public static String[] parse(String curl, String bodyData, String formData, long timeout) throws ServiceException {
        if (StringUtils.isBlank(curl)) {
            throw ServiceException.paramsException("CURL命令不能为空");
        }
        List<String> curlList = Arrays.stream(curl.split(" ")).collect(Collectors.toList());
        // 添加超时时间，默认3秒
        curlList.add("-m");
        curlList.add(timeout <= 0 ? "3" : String.valueOf(timeout));
        fillData("-d", curlList, bodyData);
        fillData("-f", curlList, formData);
        return curlList.toArray(new String[0]);
    }

    private static void fillData(String curlType, List<String> curlList, String data) {
        if (StringUtils.isBlank(data)) {
            return;
        }
        data = StrUtil.replaceSpace(data);
        if (System.getProperty("os.name").contains("Windows")) {
            data = data.replace("\"", "\\\\\"");
        }
        curlList.add(curlType);
        curlList.add(data);
    }

}
