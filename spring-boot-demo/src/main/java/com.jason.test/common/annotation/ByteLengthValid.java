package com.jason.test.common.annotation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.io.UnsupportedEncodingException;

/**
 * 注解校验规则
 *
 * @author ChenHol.Wong
 * @create 2020/10/12 21:49
 */
public class ByteLengthValid implements ConstraintValidator<ByteLength, String> {

    private static final Logger logger = LoggerFactory.getLogger(ByteLengthValid.class);

    private int length = 0;

    @Override
    public void initialize(ByteLength constraintAnnotation) {
        length = constraintAnnotation.length(); // 初始化方法，获取注解的属性值
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (length == 0) {
            return false;
        }
        try {
            if (value != null) {
                return value.trim().getBytes("UTF-8").length <= this.length;
            }
        } catch (UnsupportedEncodingException e) {
            logger.error("获取字节长度异常：", e);
        }
        return false;
    }
}
