package com.jason.base.utils;

import com.jason.base.exception.ServiceException;
import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;
import com.ql.util.express.IExpressContext;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * {@link com.ql.util.express}
 * QLExpress表达式解析。<br/>
 * 官方文档：<a href="https://github.com/alibaba/QLExpress">QLExpress</a>
 *
 * @author WongChenHoll
 * @description
 * @date 2023-2-22 星期三 16:13
 **/
public class QLExpressUtil {

    public static final String[] PARAM_NONE = new String[]{};
    public static final String[] PARAM_STR = new String[]{"java.lang.String"};
    public static final String[] PARAM_STR_STR = new String[]{"java.lang.String", "java.lang.String"};
    public static final String[] PARAM_STR_INT = new String[]{"java.lang.String", "int"};
    public static final String[] PARAM_INT = new String[]{"int"};
    public static final String[] PARAM_INT_INT = new String[]{"int", "int"};
    public static final String[] PARAM_INT_STR = new String[]{"int", "java.lang.String"};
    public static final String[] PARAM_BOOLEAN = new String[]{"boolean"};
    public static final String[] PARAM_BOOLEAN_BOOLEAN = new String[]{"boolean", "boolean"};
    public static final String[] PARAM_LIST = new String[]{"java.util.List"};
    public static final String[] PARAM_MAP = new String[]{"java.util.Map"};

    private QLExpressUtil() {
    }

    public static Object parseCurrDateStr(String express) throws ServiceException {
        return parseDateUtilClass(express, "now", "currDateStr", new String[]{});
    }


    public static Object parseDateUtilClass(String express, String name, String funcName, String[] p) throws ServiceException {
        return parseFun(express, new DefaultContext<>(), name, DateUtil.class.getName(), funcName, p);
    }

    public static Object parseFun(String express, String name, String className, String funcName, String[] p) throws ServiceException {
        return parseFun(express, new DefaultContext<>(), name, className, funcName, p);
    }

    /**
     * 表达式（表达式中绑定的函数）执行指定的类的函数
     *
     * @param express   QL表达式
     * @param context   函数执行时的上下文，通常把函数的参数定义在里面
     * @param name      QL中的函数名
     * @param className 要执行的类名
     * @param funcName  要执行的方法名
     * @param p         要执行的方法的参数类型
     * @return 执行的结果
     */
    public static Object parseFun(String express, IExpressContext<String, Object> context, String name, String className, String funcName, String[] p) throws ServiceException {
        ExpressRunner runner = new ExpressRunner();
        try {
            runner.addFunctionOfClassMethod(name, className, funcName, p, null);
            return runner.execute(express, context, null, true, false);
        } catch (Exception e) {
            throw ServiceException.serverException("QL解析失败");
        }
    }

    public static void main(String[] args) throws Exception {

        /**
         *  调用{@link DateUtil#currDateStr()}函数
         */
        System.out.println(parseCurrDateStr("now()"));
        /**
         *  调用{@link DateUtil#currDateStr()}函数
         */
        System.out.println(parseDateUtilClass("now()", "now", "currDateStr", PARAM_NONE));
        /**
         *  调用{@link DateUtil#betweenString(String, String, boolean)}函数
         */
        System.out.println(parseDateUtilClass("range('2023-02-01','2023-02-23',true)", "range", "betweenString", new String[]{"java.lang.String", "java.lang.String", "boolean"}));


        /**
         *  调用{@link MapUtil#subMap(Map, int)}函数,参数是：new HashMap(), 2
         */
        System.out.println(parseFun("subMap(new HashMap(),2)", "subMap", MapUtil.class.getName(), "subMap", new String[]{"java.util.Map", "int"}));

        Map<String, Integer> hashMap = new HashMap<>();
        hashMap.put("aa", 11);
        hashMap.put("bb", 22);
        hashMap.put("cc", 33);
        hashMap.put("dd", 44);
        hashMap.put("ee", 55);
        DefaultContext<String, Object> context = new DefaultContext<>();
        context.put("map", hashMap);
        /**
         *  调用{@link MapUtil#subMap(Map, int)}函数,参数1是：上面定义的hashMap，参数2是：3
         */
        System.out.println(parseFun("截取Map(map,3)", context, "截取Map", MapUtil.class.getName(), "subMap", new String[]{"java.util.Map", "int"}));

        System.out.println("============================================================================");
        // 替换表达式中的关键字
        ExpressRunner r1 = new ExpressRunner();
        r1.addOperatorWithAlias("如果", "if", null);
        r1.addOperatorWithAlias("则", "then", null);
        r1.addOperatorWithAlias("否则", "else", null);
        String express = "如果 ( 语文 + 数学 + 英语 > 270) 则 {return '及格';} 否则 {return '不及格';}";
        DefaultContext<String, Object> c1 = new DefaultContext<>();
        c1.put("语文", 100);
        c1.put("数学", 100);
        c1.put("英语", 100);
        System.out.println(r1.execute(express, c1, null, false, false));

        // 表达式中出现多个绑定的函数
        String exp = "打印('你好吗？'); 打印(转换为大写(\"hello world!\")); splitString('aa,bb,cc')";
        ExpressRunner r2 = new ExpressRunner();
        r2.addFunctionOfServiceMethod("打印", System.out, "println", PARAM_STR, null);
        r2.addFunctionOfClassMethod("转换为大写", StringUtils.class.getName(), "upperCase", PARAM_STR, null);
        r2.addFunctionOfClassMethod("splitString", "com.jason.base.utils.StrUtil", "split", PARAM_STR, null);
        System.out.println(r2.execute(exp, new DefaultContext<>(), null, false, false));

    }

}
