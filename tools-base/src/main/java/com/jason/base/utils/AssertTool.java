package com.jason.base.utils;

import cn.hutool.core.collection.CollectionUtil;
import com.jason.base.exception.ServiceException;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.Map;

/**
 * @author WongChenHoll
 * @description 断言
 * @date 2023-2-9 星期四 17:10
 **/
public class AssertTool {
    private AssertTool() {
    }

    public static void isTrue(boolean expression, String exceptionMessage) throws ServiceException {
        if (!expression) {
            throw ServiceException.validException(exceptionMessage);
        }
    }

    public static void isFalse(boolean expression, String exceptionMessage) throws ServiceException {
        if (expression) {
            throw ServiceException.validException(exceptionMessage);
        }
    }

    public static void isNull(Object expression, String exceptionMessage) throws ServiceException {
        if (null != expression) {
            throw ServiceException.validException(exceptionMessage);
        }
    }

    public static void notNull(Object expression, String exceptionMessage) throws ServiceException {
        if (null == expression) {
            throw ServiceException.validException(exceptionMessage);
        }
    }

    public static void isBlank(String expression, String exceptionMessage) throws ServiceException {
        if (StringUtils.isNotBlank(expression)) {
            throw ServiceException.validException(exceptionMessage);
        }
    }

    public static void notBlank(String expression, String exceptionMessage) throws ServiceException {
        if (StringUtils.isBlank(expression)) {
            throw ServiceException.validException(exceptionMessage);
        }
    }


    public static void isEmpty(Collection<?> collection, String exceptionMessage) throws ServiceException {
        if (CollectionUtil.isNotEmpty(collection)) {
            throw ServiceException.validException(exceptionMessage);
        }
    }

    public static void notEmpty(Collection<?> collection, String exceptionMessage) throws ServiceException {
        if (CollectionUtil.isEmpty(collection)) {
            throw ServiceException.validException(exceptionMessage);
        }
    }

    public static void isEmpty(Map<?, ?> map, String exceptionMessage) throws ServiceException {
        if (CollectionUtil.isNotEmpty(map)) {
            throw ServiceException.validException(exceptionMessage);
        }
    }

    public static void notEmpty(Map<?, ?> map, String exceptionMessage) throws ServiceException {
        if (CollectionUtil.isEmpty(map)) {
            throw ServiceException.validException(exceptionMessage);
        }
    }

}
