package test.other;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 测试lambda表达式
 * <p>
 * Lambda介绍
 * Lambda 表达式(lambda expression)是一个匿名函数，Lambda表达式基于数学中的λ演算得名，直接对应于其中的lambda抽象(lambda abstraction)，是一个匿名函数，即没有函数名的函数。
 * <p>
 * Lambda表达式的结构
 * 一个 Lambda 表达式可以有零个或多个参数
 * 参数的类型既可以明确声明，也可以根据上下文来推断。例如：(int a)与(a)效果相同
 * 所有参数需包含在圆括号内，参数之间用逗号相隔。例如：(a, b) 或 (int a, int b) 或 (String a, int b, float c)
 * 空圆括号代表参数集为空。例如：() -> 42
 * 当只有一个参数，且其类型可推导时，圆括号（）可省略。例如：a -> return a*a
 * Lambda 表达式的主体可包含零条或多条语句
 * 如果 Lambda 表达式的主体只有一条语句，花括号{}可省略。匿名函数的返回类型与该主体表达式一致
 * 如果 Lambda 表达式的主体包含一条以上语句，则表达式必须包含在花括号{}中（形成代码块）。匿名函数的返回类型与代码块的返回类型一致，若没有返回则为空
 *
 * @author ChenHol.Wong
 * @create 2020/10/5 16:00
 */
public class TestLambda {

    public static void main(String[] args) {
        // 测试map
        HashMap<String, String> map = new HashMap<>();
        map.put("1", "a");
        map.put("2", "b");
        map.put("3", "c");
        map.put("4", "d");
        System.out.println("普通map遍历");
        for (String key : map.keySet()) {
            System.out.println("key:" + key + " value:" + map.get(key));
        }
        System.out.println("lambda表达式遍历");
        map.forEach((k, v) -> {
            System.out.println("key:" + k + " value:" + v);
        });

        // 使用冒号运算符
        ArrayList<Model> list = new ArrayList<>();
        list.add(new Model("张三", 20, "上海"));
        list.add(new Model("李四", 32, "北京"));
        list.add(new Model("王五", 41, "深圳"));
        System.out.println("Lambda表达式遍历List");
        list.forEach(v -> {
            System.out.println(v);
        });
        System.out.println("使用冒号遍历");
        list.forEach(System.out::println);
        System.out.println("Lambda主体中带有条件判断");
        list.forEach(model -> {
            if (model.getAge() > 30) {
                System.out.println(model.getName());
            }
        });

        // 匿名内部类
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("普通方式创建多线程");
            }
        };
        Runnable r2=()-> System.out.println("Lambda表达式创建多线程");
        new Thread(r1).start();
        new Thread(r2).start();
    }
}
