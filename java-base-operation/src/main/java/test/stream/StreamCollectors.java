package test.stream;

import test.other.Model;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author WongChenHoll
 * @description Collectors 的作用
 * @date 2023-1-3 星期二 11:03
 **/
public class StreamCollectors {
    static String[] s = new String[]{"Java", "Tomcat", "Maven", "Idea", "Quartz", "Java"};
    static List<String> strList = new ArrayList<>(Arrays.asList(s));
    static List<Model> models = new ArrayList<>();

    static {
        models.add(new Model("张三", 20, "上海"));
        models.add(new Model("李四", 32, "北京"));
        models.add(new Model("王五", 41, "深圳"));
        models.add(new Model("tom", 18, "北京"));
        models.add(new Model("赵六", 20, "北京"));
        models.add(new Model("刘七", 32, "上海"));
    }

    public static void main(String[] args) {
        System.out.println("========== to...()归纳 ==========");
        toOtherCollection();
        System.out.println("========== joining()拼接 ==========");
        joining();
        System.out.println("========== 统计计算 ==========");
        calculation();
        System.out.println("========== groupingBy()分组 ==========");
        groupingBy();
    }

    /**
     * <pre>
     *     Collectors.toList()
     *     Collectors.toSet()
     *     Collectors.toMap()
     * </pre>
     */
    public static void toOtherCollection() {
        // 归纳为其它容器
        List<String> toList = models.stream().map(Model::getAddress).collect(Collectors.toList());
        System.out.println("Collectors.toList()：" + toList);
        Set<String> toSet = models.stream().map(Model::getAddress).collect(Collectors.toSet());
        System.out.println("Collectors.toSet()：" + toSet);
        // 将集合转换成Map
        Map<String, String> toMap1 = models.stream().collect(Collectors.toMap(Model::getName, Model::getAddress));
        System.out.println("Collectors.toMap()：" + toMap1);
        // 当map中的key冲突时，使用第一个key
        Map<String, String> toMap2 = models.stream().collect(Collectors.toMap(Model::getAddress, Model::getName, (k1, k2) -> k1));
        System.out.println("Collectors.toMap()：" + toMap2);
        Map<String, Model> toMap3 = models.stream().collect(Collectors.toMap(Model::getName, model -> model, (a, b) -> a));
        System.out.println("Collectors.toMap()：" + toMap3);
        // 当map中的key冲突时，使用第一个key，并将结果放在一个空的Map中
        HashMap<String, String> toMap4 = models.stream().collect(Collectors.toMap(Model::getAddress, Model::getName, (k1, k2) -> k1, HashMap::new));
        System.out.println("Collectors.toMap()：" + toMap4);
    }

    /**
     * 拼接集合中的元素
     */
    public static void joining() {
        // 将容器中的元素拼接起来，拼接符号为空字符串，类似于String.join()
        String collect = strList.stream().collect(Collectors.joining()); // 等价于：String.join("", strList)
        System.out.println("joining，默认拼接符：" + collect);
        String c2 = strList.stream().collect(Collectors.joining(","));
        System.out.println("joining，自定义拼接符：" + c2);
        String c3 = strList.stream().collect(Collectors.joining(",", "{", "}"));
        System.out.println("joining，自定义拼接符，加上前后符号：" + c3);

        // 也可以通过reduce方法实现joining的功能，不过还是建议通过joining来实现。
        String r1 = strList.stream().reduce((a, b) -> a + "," + b).get();
        System.out.println("通过reduce将集合中的元素归纳起来：" + r1);
        String r2 = strList.stream().reduce("----->", (a, b) -> a + "," + b);
        System.out.println("通过reduce将集合中的元素归纳起来：" + r2);
    }

    /**
     * 计算.
     * <pre>
     *     counting()
     *     minBy()
     *     maxBy()
     *     summingInt()
     *     summingLong()
     *     summingDouble()
     *     sumWithCompensation()
     *     computeFinalSum()
     *     averagingInt()
     *     averagingLong()
     *     averagingDouble()
     * </pre>
     */
    public static void calculation() {

    }

    /**
     * 分组
     */
    public static void groupingBy() {
        Map<String, List<String>> c1 = strList.stream().collect(Collectors.groupingBy(m -> m)); // 根据字符串的内容分组
        System.out.println("指定分组的key：" + c1);
        Map<Integer, List<String>> c2 = strList.stream().collect(Collectors.groupingBy(String::length)); // 根据字符串的长度分组
        System.out.println("指定分组的key：" + c2);
        Map<String, List<Model>> c3 = models.stream().collect(Collectors.groupingBy(Model::getAddress)); // 根据用户地址分组
        System.out.println("指定分组的key：" + c3);

        Map<String, Set<Model>> c4 = models.stream().collect(Collectors.groupingBy(Model::getAddress, Collectors.toSet()));
        System.out.println("指定分组后的容器：" + c4);
    }
}
