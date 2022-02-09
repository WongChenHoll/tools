package test.stream;

import test.other.Model;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Stream流的使用
 *
 * @author ChenHol.Wong
 * @create 2020/10/5 16:41
 */
public class TestStream {
    public static void main(String[] args) {
        List<String> strList = Arrays.asList("a", "b", "c", "d", "e", "e");
        List<Integer> sortList = Arrays.asList(4, 2, 10, 1, 8);

        // 构造Stream流的方式
        String[] arr = {"a", "b", "c"};
        Stream<String> stream1 = Stream.of("a", "b", "c");
        Stream<String> stream2 = Stream.of(arr);
        Stream<String> stream3 = Arrays.stream(arr);
        List<String> list = Arrays.asList(arr);
        Stream<String> stream4 = list.stream();

        // Stream流的之间的转换
        // 转换成数组
        Object[] array = Stream.of("a", "b", "c").toArray();
        String[] toArray = Stream.of("a", "b", "c").toArray(String[]::new);
        // 转换成集合
        List<String> list1 = Arrays.asList(arr).stream().collect(Collectors.toList());
        Set<String> set = Arrays.asList(arr).stream().collect(Collectors.toSet());
        ArrayList<String> list2 = Arrays.asList(arr).stream().collect(Collectors.toCollection(ArrayList::new));
        Stack<String> stack = Arrays.asList(arr).stream().collect(Collectors.toCollection(Stack::new));
        // 转换成String
        String collect = Arrays.asList(arr).stream().collect(Collectors.joining());

        // 创建无线循环流
//        Stream.iterate(0, t -> t + 5).forEach(System.out::println); // 从0开始，每隔5个数取一个，会无限循环的
//        Stream.iterate(0, t -> t + 5).limit(10).forEach(System.out::println); // 从0开始，每隔5个数取一个，只取前10个数
//        Stream.generate(Math::random).limit(5).forEach(System.out::println); // 从0-1之间随机取5个数

        // map的使用 将流中每个元素映射成另外一个流
        List<String> list3 = Arrays.asList("Avril Lavigne", "WestLife", "Daniel Powter", "Britney Spears", "Coldplay");
        List<String> collect1 = list3.stream().map(String::toUpperCase).collect(Collectors.toList());
        System.out.println("map后转换数据：" + collect1);

        List<String> numStrList = Arrays.asList("10", "11", "12");
        List<Integer> list5 = numStrList.stream().map(Integer::valueOf).collect(Collectors.toList());
        System.out.println("map转换后的数据：" + list5);

        ArrayList<Model> models = new ArrayList<>();
        models.add(new Model("张三", 20, "上海"));
        models.add(new Model("李四", 32, "北京"));
        models.add(new Model("王五", 41, "深圳"));
        models.add(new Model("tom", 18, "北京"));
        System.out.println("map获取对象中的某个属性值：" + models.stream().map(Model::getAddress).collect(Collectors.toList()));

        // filter 的使用
        List<Model> filter1 = models.stream().filter(model -> model.getAge() > 30).collect(Collectors.toList());
        System.out.println("filter过滤后的数据：" + filter1);

        String orElse1 = strList.stream().filter(s -> "aa".equals(s)).findAny().orElse("找不到");
        String orElse2 = strList.stream().filter(s -> "a".equals(s)).findAny().orElse("找不到");
        System.out.println("orElse1：" + orElse1 + "  orElse2：" + orElse2);

        int sum = models.stream().filter(model -> "北京".equals(model.getAddress())).mapToInt(model -> model.getAge()).sum();
        System.out.println("居住在北京所有人的年龄和：" + sum);

        // flatMap
        List<List<String>> flatMap = Stream.of(strList, numStrList).collect(Collectors.toList());
        System.out.println("不使用flatMap合并多个list：" + flatMap);
        List<String> flatMap1 = Stream.of(strList, numStrList).flatMap(List::stream).collect(Collectors.toList());
        System.out.println("使用flatMap合并多个list：" + flatMap1);
        String[] t1 = {"aaa", "bbb", "ccc"};
        String[] t2 = {"111", "222", "333"};
        String[] t3 = {"@@@", "###", "$$$"};
        List<String> flatMap2 = Stream.of(t1, t2, t3).flatMap(Arrays::stream).collect(Collectors.toList());
        System.out.println("使用flatMap合并多个数组：" + flatMap2);

        // limit skip distinct
        System.out.println("取出前三个元素：" + strList.stream().limit(3).collect(Collectors.toList()));
        System.out.println("跳过前3个人元素：" + strList.stream().skip(3).collect(Collectors.toList()));
        System.out.println("去重：" + strList.stream().distinct().collect(Collectors.toList()));

        // sorted 排序

        System.out.println("自然排序：" + sortList.stream().sorted().collect(Collectors.toList()));
        System.out.println("倒序排序：" + sortList.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList()));
        System.out.println("自然排序：" + models.stream().sorted().collect(Collectors.toList()));
        System.out.println("根据指定规则排序：" + models.stream().sorted(Comparator.comparing(Model::getAge)).collect(Collectors.toList()));

        // reduce 归纳 把 Stream 元素组合起来进行操作
        System.out.println("将集合中的每个元素通过' # '拼接起来：" + strList.stream().reduce((a, b) -> a + " # " + b));
        System.out.println("将结合中每个元素拼接起来，并在前面加上 # ：" + strList.stream().reduce(" # ", String::concat));
        System.out.println("求流中最大值：" + sortList.stream().reduce(Integer.MAX_VALUE, Integer::min));

        // sum count max min
        System.out.println("总数：" + models.stream().count());
        System.out.println("过滤后总数：" + models.stream().filter(model -> model.getAge() > 30).count());
        System.out.println("年龄总和方式一：" + models.stream().map(Model::getAge).reduce(Integer::sum).get());
        System.out.println("年龄总和方式二：" + models.stream().map(Model::getAge).reduce(0, Integer::sum)); //起始值为0，然后开始求总和
        System.out.println("年龄总和方式三：" + models.stream().mapToInt(Model::getAge).sum());
        System.out.println("最大年龄：" + models.stream().mapToInt(Model::getAge).max().getAsInt());
        System.out.println("最小年龄：" + models.stream().mapToInt(Model::getAge).min().getAsInt());
        System.out.println("最大年龄：" + models.stream().map(Model::getAge).reduce(Integer::max).get());
        System.out.println("最小年龄：" + models.stream().map(Model::getAge).reduce(Integer::min).get());
        System.out.println("最大值：" + sortList.stream().reduce(Integer::max).get());
        System.out.println("最小值：" + sortList.stream().reduce(Integer::min).get());

        // findFirst、findAny allMatch anyMatch noneMatch
        System.out.println("findFirst：" + strList.stream().findFirst().get());
        System.out.println("findAny：" + strList.stream().filter(a -> "哈哈".equals(a)).findAny().orElse("没有\"哈哈\""));
        System.out.println("findAny：" + strList.stream().filter(a -> "c".equals(a)).findAny().orElse("没有\"c\""));
        System.out.println("allMatch：" + sortList.stream().allMatch(a -> a > 5));
        System.out.println("allMatch：" + models.stream().allMatch(a -> a.getAge() > 15));
        System.out.println("anyMatch：" + models.stream().anyMatch(model -> "张三".equals(model.getName())));
        System.out.println("anyMatch：" + models.stream().anyMatch(model -> model.getAge() > 20));
        System.out.println("noneMatch：" + sortList.stream().noneMatch(s -> s > 10));

        // iterate
        System.out.println("从10开始生成一个等差队列，取其前五个：" + Stream.iterate(10, n -> n + 5).limit(5).collect(Collectors.toList()));
        System.out.println("创建一个大小是10的数组，元素数据都是5：" + Stream.iterate(5, n -> n).limit(10).collect(Collectors.toList()));

    }


}
