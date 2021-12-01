package com.jason.test.common.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 自定义字节长度校验注解
 *
 * @author ChenHol.Wong
 * @create 2020/10/12 21:49
 */
@Documented
@Constraint(validatedBy = ByteLengthValid.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ByteLength {

    int length() default 0;  // 定义属性值长度

    String message() default "字段长度超长"; // 定义错误提示信息

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
