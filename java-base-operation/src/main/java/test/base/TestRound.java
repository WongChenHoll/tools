package test.base;

/**
 * Java随机数
 *
 * @author ChenHol.Wong
 * @create 2020/7/20 20:39
 */
public class TestRound {
    public static void main(String[] args) {
        System.out.println(Math.round(-1.5)); // 等于 -1，因为在数轴上取值时，中间值（0.5）向右取整，所以正 0.5 是往上取整，负 0.5 是直接舍弃。
        System.out.println(Math.random() * 100);
        System.out.println(Math.round(-1.9));
        System.out.println(Math.round(-1.3));
        System.out.println(Math.round(1.3));
        System.out.println(Math.round(1.9));
        System.out.println(Math.round(1.5));
    }
}
